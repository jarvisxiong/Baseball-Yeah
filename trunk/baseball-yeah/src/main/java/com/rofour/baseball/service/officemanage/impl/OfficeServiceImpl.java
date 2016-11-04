/**
 * @Title: OfficeServiceImpl.java
 * @package com.rofour.baseball.service.officemanage.impl
 * @Project: baseball-yeah
 * @author WJ
 * ──────────────────────────────────
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.service.officemanage.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.Constant;
import com.rofour.baseball.common.HttpClientUtils;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.office.OfficeQueryInfo;
import com.rofour.baseball.dao.officemanage.bean.*;
import com.rofour.baseball.dao.officemanage.mapper.OfficeMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.officemanage.OfficeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: OfficeServiceImpl
 * @Description 职务管理实现类
 * @author WJ
 * @date 2016年10月12日 下午1:51:16
 *
 */
@Service("officeService")
public class OfficeServiceImpl implements OfficeService {

    /**
     * 职务,1:普通小派 ,2:校园COO,3: 校园CEO
     */
    private static final int CEO = 3;

    private static final int COO = 2;

    private static final int OPT_TYPE_CEO = 1;
    private static final int OPT_TYPE_COO = 2;
    @Autowired
    private OfficeMapper officeMapper;

    /**
     * @Description 查询COO, CEO
     * @param info
     * @return List<OfficeBean> 返回类型
     * @author WJ
     * @date 2016年10月12日 下午1:56:02
     **/
    @Override
    public List<OfficeBean> list(OfficeQueryInfo info) {
        List<OfficeBean> list = officeMapper.queryAll(info);
        for (OfficeBean bean : list) {
            if (bean.getOffice() == CEO) {
                //统计小派和COO
                bean.setPacketUser(officeMapper.queryUserAndCOOTotal(bean.getUserId()));
                bean.setStore(officeMapper.queryStoreAndCOOTotal(bean.getUserId()));
            } else {
                bean.setPacketUser(officeMapper.queryUserTotal(bean.getUserId()));
                bean.setStore(officeMapper.queryStoreTotal(bean.getUserId()));
            }
        }
        return list;
    }

    /**
     * @Description 查询普通小派
     * @param info
     * @return List<OfficeBean> 返回类型
     * @author WJ
     * @date 2016年10月12日 下午1:56:02
     **/
    @Override
    public List<OfficeBean> listPuser(OfficeQueryInfo info) {
        List<OfficeBean> list = officeMapper.queryAllPuser(info);
        return list;
    }

    /**
     * @Description 统计
     * @param info
     * @return int 返回类型
     * @author WJ
     * @date 2016年10月12日 下午1:56:05
     **/
    @Override
    public int getTotal(OfficeQueryInfo info) {
        return officeMapper.getTotal(info);
    }

    /**
     * @Description 统计普通小派
     * @param info
     * @return int 返回类型
     * @author WJ
     * @date 2016年10月12日 下午1:56:05
     **/
    @Override
    public int getPuserTotal(OfficeQueryInfo info) {
        return officeMapper.queryAllPuserTotal(info);
    }

    /**
     * @Description 解除职务
     * @return ResultInfo<Object>    返回类型
     **/
    @Override
    public ResultInfo<Object> dismiss(Long userId, int office) {
        //step1:更新自身职务属性
        int result = officeMapper.updateRoleType(userId);
        //step2:判断CEO还是COO
        if (office == CEO) {
            officeMapper.updatePuserCEO(userId);
            officeMapper.updateStoreCEO(userId);
        } else {
            //step3:清除属下小派
            officeMapper.updatePuserCOO(userId);
            //step4:清除属下商户
            officeMapper.updateStoreCOO(userId);
        }
        return result > 0 ? new ResultInfo<Object>(0, "", "操作成功") : new ResultInfo<Object>(-1, "", "操作失败");
    }

    /**
     * @Description 更新审核状态
     **/
    @Override
    public void audit(Long auditId, Boolean isPass, Long managerId) throws Exception {
        OfficeAuditBean officeAuditBean = officeMapper.getOfficeAuditById(auditId);
        if (officeAuditBean == null) {
            //未查询到有效审核信息
            throw new BusinessException("06001");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("auditId", auditId);
        map.put("managerId", managerId);
        map.put("userId", officeAuditBean.getPacketUserId());
        if (isPass) {
            map.put("auditState", "1");
            int optType = officeAuditBean.getOptType();
            //1-新建ceo 2-新建coo
            if (optType == OPT_TYPE_CEO) {
                map.put("roleType", "3");
            } else {
                map.put("roleType", "2");
            }
            officeMapper.updatePacketRole(map);
        } else {
            map.put("auditState", "2");
        }
        officeMapper.updateAuditState(map);

    }

    /**
     * @Description 职务编辑细节
     * @param  userId
     * @throws IOException
     **/
    @Override
    public ResultInfo<Object> officeDetail(Long userId) throws IOException {
        OfficeDetailBean bean = officeMapper.queryByUserId(userId);
        return bean != null ? new ResultInfo<Object>(0, "", "操作成功", bean)
                : new ResultInfo<Object>(-1, "", "未查询到此小派详细信息");
    }

    /**
     *
     * @Description 获取头像等图片信息
     * @param  bizId
     * @param  attachType
     *
     */
    public ResultInfo<Object> getUrl(Long bizId, String attachType) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bizId", bizId);
        map.put("attachType", attachType);
        String url = Constant.axpurl.get("resource_getOriginAttachList_serv");
        // 定义反序列化 数据格式
        final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
        };
        List<String> list = new ArrayList<>();
        ResultInfo<?> credentialUrls = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
        if (null != credentialUrls && credentialUrls.getSuccess() == 1) {
            List<?> credentialURLInfo = (List<?>) credentialUrls.getData();
            if (CollectionUtils.isNotEmpty(credentialURLInfo)) {
                for (Object o : credentialURLInfo) {
                    if (o instanceof Map) {
                        Map<?, ?> imageMap = (Map<?, ?>) o;
                        list.add(imageMap.get("fileUrl").toString());
                    }
                }
            }
        }
        return !list.isEmpty() ? new ResultInfo<Object>(0, "", "操作成功", list)
                : new ResultInfo<Object>(-1, "", "未查询到图片信息");
    }

    /**
     *
     * @Description 查询属下小派和coo
     * @param  info
     *
     */
    public List<OfficeBean> queryAttached(OfficeQueryInfo info) {
        return officeMapper.queryAttached(info);
    }

    /**
     *
     * @Description 统计属下小派或者coo人数
     * @param  info
     *
     */
    public int userTotal(OfficeQueryInfo info) {
        int result = 0;
        if (info.getOffice() == CEO) {
            result = officeMapper.queryUserAndCOOTotal(info.getUserId());
        } else if (info.getOffice() == COO) {
            result = officeMapper.queryUserTotal(info.getUserId());
        }
        return result;
    }

    /**
     * @Description 查询属下的商户
     * @param  info
     **/
    @Override
    public List<OfficeStoreBean> queryAttachedStores(OfficeQueryInfo info) {
        return officeMapper.queryAttachedStores(info);
    }

    /**
     *
     * @Description 统计属下商户数
     * @param  info
     *
     */
    @Override
    public int storeTotal(OfficeQueryInfo info) {
        int result = 0;
        if (info.getOffice() == CEO) {
            result = officeMapper.queryStoreAndCOOTotal(info.getUserId());
        } else if (info.getOffice() == COO) {
            result = officeMapper.queryStoreTotal(info.getUserId());
        }
        return result;
    }

    /**
     * @Description 小派删除, 支持批量
     * @param  ids
     **/
    @Override
    public ResultInfo<Object> pDel(Map<String, Object> map) {
        int result = officeMapper.deletePuserBoss(map);
        return result > 0 ? new ResultInfo<Object>(0, "", "删除成功") : new ResultInfo<Object>(-1, "", "删除失败");
    }

    /**
     * @Description 商户删除, 支持批量
     * @param  map
     **/
    @Override
    public ResultInfo<Object> sDel(Map<String, Object> map) {
        int result = officeMapper.deleteStoreBoss(map);
        return result > 0 ? new ResultInfo<Object>(0, "", "删除成功") : new ResultInfo<Object>(-1, "", "删除失败");
    }

    /**
     * @Description 小派增加, 支持批量
     * @param  map
     **/
    @Override
    public ResultInfo<Object> pAdd(Map<String, Object> map) {
        int result = officeMapper.addPuserBoss(map);
        return result > 0 ? new ResultInfo<Object>(0, "", "添加成功") : new ResultInfo<Object>(-1, "", "添加失败");
    }

    /**
     * @Description: 获取审核记录
     * @param queryInfo
     * @return
     * @throws
     * @author ZXY
     */
    @Override
    public Map getOfficeAudit(OfficeQueryInfo queryInfo) throws Exception {
        if (StringUtils.isBlank(queryInfo.getSort())) {
            queryInfo.setSort("signupTime");//默认以时间排序
            queryInfo.setOrder("desc");//默认倒序
        }
        List<OfficeAuditInfo> infoList = officeMapper.getOfficeAudit(queryInfo);
        int count = officeMapper.getOfficeAuditCount(queryInfo);
        Map map = new HashMap();
        map.put("list", infoList);
        map.put("count", count);
        return map;
    }
}
