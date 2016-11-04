package com.rofour.baseball.service.store.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.dao.user.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.common.PhoneUtils;
import com.rofour.baseball.common.WebUtils;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.store.AxpStoreInfo;
import com.rofour.baseball.controller.model.store.CustomAxpInfo;
import com.rofour.baseball.controller.model.store.CustomStoreInfo;
import com.rofour.baseball.controller.model.store.ExpStoreInfo;
import com.rofour.baseball.controller.model.store.SearchStoreInfo;
import com.rofour.baseball.controller.model.store.SimpleStoreInfo;
import com.rofour.baseball.controller.model.store.StoreInfo;
import com.rofour.baseball.dao.bean.SmsAccountBean;
import com.rofour.baseball.dao.manager.bean.SysParameterBean;
import com.rofour.baseball.dao.manager.mapper.SysParameterMapper;
import com.rofour.baseball.dao.store.bean.AxpBean;
import com.rofour.baseball.dao.store.bean.AxpStoreBean;
import com.rofour.baseball.dao.store.bean.ExpStoreBean;
import com.rofour.baseball.dao.store.bean.SearchStoreBean;
import com.rofour.baseball.dao.store.bean.StoreBean;
import com.rofour.baseball.dao.store.bean.StoreExpBean;
import com.rofour.baseball.dao.store.bean.StoreSchoolBean;
import com.rofour.baseball.dao.store.bean.StoreSiteBean;
import com.rofour.baseball.dao.store.mapper.StoreMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.store.StoreService;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * @author WJ
 * @ClassName: StoreServiceImpl
 * @Description: 商户中心业务实现类
 * @date 2016年3月25日 下午12:54:36
 */

@Service("storeService")
public class StoreServiceImpl implements StoreService {

    private static final Logger LOG = LoggerFactory.getLogger(StoreServiceImpl.class);

    /**
     * 管理中心 01 用户中心 02 商户中心 03 运单中心 04 消息中心 05 日志中心 06 资源中心 07 报表中心 08
     */
    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    @Qualifier("tbSysParameterMapper")
    private SysParameterMapper tbSysParameterMapper;

    @Resource(name = "userMapper")
    private UserMapper userMapper;
    /**
     * 系统管理日志实例
     */
    @Autowired
    private WebUtils webUtils;

    /**
     * @param expressId
     * @return boolean 返回类型
     * @Method: ifExpressExist
     * @Description: 判断快递信息是否存在
     * @author WJ
     * @date 2016年4月2日 上午10:07:50
     */
    public boolean ifExpressExist(List<Long> expressId) {
        int count = storeMapper.ifExpressExist(expressId);
        return count == expressId.size();
    }

    /**
     * @param axpId
     * @return boolean 返回类型
     * @Method: ifAxpExist
     * @Description: 判断门店信息是否存在
     * @author WJ
     * @date 2016年4月2日 上午10:13:02
     */
    public boolean ifAxpExist(List<Long> axpId) {
        int count = storeMapper.ifAxpExist(axpId);
        return count == axpId.size();
    }

    @Override
    public int removeById(Long id) {
        int result = storeMapper.logicalDeleteStore(id);
        return result;
    }

    /**
     * 根据学校查询门店信息
     */
    @Override
    public List<SimpleStoreInfo> queryByCollegeId(Long id) {
        List<StoreBean> beans = storeMapper.selectStoreByCollegeId(id);
        List<SimpleStoreInfo> result = new ArrayList<>();
        for (StoreBean store : beans) {
            result.add(new SimpleStoreInfo(store.getStoreId(), store.getStoreCode(), store.getStoreName()));
        }
        return result;
    }

    @Override
    public CustomStoreInfo querySiteById(Long id) {
        CustomStoreInfo result = storeMapper.querySiteById(id);
        return result;
    }

    /**
     * @param store
     * @param needPK 是否需要主键,add不需要,update 需要
     * @return boolean 返回类型
     * @Method: isValid
     * @Description: 校验参数
     * @author WJ
     * @date 2016年3月25日 下午2:08:01
     **/

    private boolean isValid(StoreInfo store, boolean needPK) {
        // 更新需要主键
        if (needPK && (store.getStoreId() == 0 || store.getEcList() == null)) {
            return false;
        }
        // 检查电话号码
        if (StringUtils.isBlank(store.getPhone()) || !PhoneUtils.isMobile(store.getPhone())) {
            return false;
        }
        if (StringUtils.isBlank(store.getStoreCode()) || StringUtils.isBlank(store.getStoreName())) {
            return false;
        }
        if (store.getAxpList() != null && !store.getAxpList().isEmpty()) {
            for (Long i : store.getAxpList()) {
                if (i == null) {
                    return false;
                }
            }
        } else {
            return false;
        }
        // 检查快递编码,增加为单个,更新为集合
        if (needPK && !store.getEcList().isEmpty()) {// 更新
            for (Long i : store.getEcList()) {
                if (i == null) {
                    return false;
                }
            }
        } else if (store.getExpressCompanyId() == 0) {// 增加
            return false;
        }
        return true;
    }

    /**
     * 按手机号码查询商户信息
     *
     * @param phone
     * @return SearchStoreInfo 返回类型
     * @author xl
     */
    @Override
    public SearchStoreInfo selectStoreByPhone(String phone) {
        SearchStoreBean storeBean = storeMapper.selectStoreByPhone(phone);
        if (storeBean == null) {
            return null;
        }

        SearchStoreInfo storeInfo = new SearchStoreInfo();
        BeanUtils.copyProperties(storeBean, storeInfo);
        return storeInfo;
    }

    /**
     * 查询爱学派所有门店信息
     *
     * @return List<AxpStoreInfo> 返回类型
     * @author hy
     */
    @Override
    public List<AxpStoreInfo> selectAxpStoreInfo() {
        List<AxpStoreInfo> infoList = new ArrayList<>();
        List<AxpStoreBean> storeBeans = storeMapper.selectAxpStoreInfo();
        for (AxpStoreBean store : storeBeans) {
            AxpStoreInfo storeInfo = new AxpStoreInfo();
            BeanUtils.copyProperties(store, storeInfo);
            infoList.add(storeInfo);
        }
        return infoList;
    }

    /**
     * 查询所有快递站点信息
     *
     * @return List<ExpStoreInfo> 返回类型
     * @author hy
     */
    @Override
    public List<ExpStoreInfo> selectExpStoreInfo() {
        List<ExpStoreInfo> infoList = new ArrayList<>();
        List<ExpStoreBean> storeBeans = storeMapper.selectExpStoreInfo();
        for (ExpStoreBean store : storeBeans) {
            ExpStoreInfo storeInfo = new ExpStoreInfo();
            BeanUtils.copyProperties(store, storeInfo);
            infoList.add(storeInfo);
        }
        return infoList;
    }

    private void checkAxpInfo(CustomAxpInfo axpInfo) {
        // 判断是否已存在那条记录
        if (axpInfo.getEcList() != null && !axpInfo.getEcList().isEmpty() && !ifExpressExist(axpInfo.getEcList())) {
            throw new BusinessException("03001");
        }
        if (axpInfo.getExpList() != null && !axpInfo.getExpList().isEmpty() && !ifStoreExist(axpInfo.getExpList())) {
            throw new BusinessException("03003");
        }
        if (axpInfo.getColList() != null && !axpInfo.getColList().isEmpty() && !ifCollegeExist(axpInfo.getColList())) {
            throw new BusinessException("03004");
        }
    }

    /**
     * @param colList
     * @return boolean 返回类型
     * @Method: ifCollegeExist
     * @Description: 判断学校信息是否存在
     * @author WJ
     * @date 2016年4月11日 下午2:35:59
     **/
    public boolean ifCollegeExist(List<Long> colList) {
        int count = storeMapper.ifCollegeExist(colList);
        return count == colList.size();
    }

    /**
     * @param expList
     * @return boolean 返回类型
     * @Method: ifStoreExist
     * @Description: 判断商户信息是否存在, 不包括逻辑删除的
     * @author WJ
     * @date 2016年4月11日 下午2:31:31
     **/

    public boolean ifStoreExist(List<Long> expList) {
        int count = storeMapper.ifStoreIdExist(expList);
        return count == expList.size();
    }

    private void checkCustomAxpInfo(CustomAxpInfo axpInfo) {
        if (StringUtils.isBlank(axpInfo.getStoreCode()) || StringUtils.isBlank(axpInfo.getStoreName())) {
            throw new BusinessException("03000");
        } else if (StringUtils.isBlank(axpInfo.getSupervisorName())) {
            throw new BusinessException("03005");
        } else if (axpInfo.getType() != 2) {
            throw new BusinessException("03006");
        } else if (axpInfo.getStatus() != 0 && axpInfo.getStatus() != 1) {
            throw new BusinessException("03007");
        } else if (!PhoneUtils.isMobile(axpInfo.getPhone())) {
            throw new BusinessException("03008");
        }
    }

    private void checkExpStoreInfo(ExpStoreInfo store, boolean needPK) {
        if (needPK && (store.getStoreId() == null)) {
            throw new BusinessException("03000");
        }
        if (StringUtils.isBlank(store.getStoreCode()) || StringUtils.isBlank(store.getStoreName())) {
            throw new BusinessException("03010");
        } else if (StringUtils.isBlank(store.getSupervisorName())) {
            throw new BusinessException("03011");
        } else if (store.getType() == null || store.getType() != 1) {
            throw new BusinessException("03012");
        } else if (store.getStatus() == null || (store.getStatus() != 0 && store.getStatus() != 1)) {
            throw new BusinessException("03013");
        } else if (!PhoneUtils.isMobile(store.getPhone())) {
            throw new BusinessException("03008");
        }
    }

    @Override
    public long addAxpInfo(CustomAxpInfo axpInfo) {
        return 0;
    }

    @Override
    public void delete(long storeId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateAxpInfo(CustomAxpInfo axpInfo) {
        // TODO Auto-generated method stub
    }

    @Override
    public ResultInfo addExpInfo(ExpStoreInfo storeInfo, HttpServletRequest request) {
        String error = isExpValid(storeInfo, false);
        if (error != null) {
            return new ResultInfo(-1, "2030010004", error);
        }

        // 新增商户
        StoreBean bean = new StoreBean();
        // bean.setStoreId(seq);
        bean.setStoreCode(storeInfo.getStoreCode());
        bean.setStoreName(storeInfo.getStoreName());
        bean.setLocation(storeInfo.getLocation());
        bean.setStatus(storeInfo.getStatus());
        bean.setBeDeleted(0);
        Long result = storeMapper.insertStore(bean);
        webUtils.userAddLog(request, 14, bean);
        if (result <= 0) {
            return new ResultInfo(-1, "2030010005", "服务器内部原因导致操作失败(sq_store)", null);
        }
        Long seq = null;
        seq = storeMapper.getSeq();
        if (seq == null || seq == 0) {
            return new ResultInfo(-1, "2030010005", "服务器内部原因导致操作失败(sq_store)", null);
        }
        // 新增站点
        StoreSiteBean siteBean = new StoreSiteBean();
        siteBean.setStoExpId(seq);
        siteBean.setPhone(storeInfo.getPhone());
        siteBean.setSupervisorName(storeInfo.getSupervisorName());
        siteBean.setPacketModeMgr(storeInfo.getPacketModeMgr());
        siteBean.setPacketModeSend(storeInfo.getPacketModeSend());
        if (storeInfo.getCloseMode() == null) {
            siteBean.setCloseMode((byte) 0);
        } else {
            siteBean.setCloseMode(storeInfo.getCloseMode());
        }
        storeMapper.insertStoreSite(siteBean);
        webUtils.userAddLog(request, 14, siteBean);
        // 新增快递公司

        insertExpressRelDefault(seq, storeInfo.getDefaultECId());
        if (storeInfo.getEcList() != null && !storeInfo.getEcList().isEmpty()) {
            for (Long expressId : storeInfo.getEcList()) {
                StoreExpBean expBean = new StoreExpBean();
                expBean.setExpressCompanyId(expressId);
                expBean.setStoreId(seq);
                expBean.setBeDefault(0);
                storeMapper.insertStoreExp(expBean);
                webUtils.userAddLog(request, 14, expBean);
            }
        }
        // 插入tb_store_college_rel(可选,多个)
        if (storeInfo.getColList() != null && !storeInfo.getColList().isEmpty()) {
            List<StoreSchoolBean> colList = new ArrayList<>();
            for (Long collegeId : storeInfo.getColList()) {
                colList.add(new StoreSchoolBean(null, seq, collegeId));
            }
            storeMapper.insertStoreSchoolList(colList);
            webUtils.userAddLog(request, 14, colList);
        }
        // 为该商户添加短信账户
        SmsAccountBean SmsAccountbean = new SmsAccountBean();
        SysParameterBean parameterBean = tbSysParameterMapper.selectByParaName("SMS_ACCOUNT_DEFAULT");
        if (parameterBean != null && !StringUtils.isEmpty(parameterBean.getValue())) {
            SmsAccountbean.setBalance(Integer.valueOf(parameterBean.getValue()));
        }
        SmsAccountbean.setAddTime(new Date());
        SmsAccountbean.setBeEnabled(true);
        SmsAccountbean.setStoreId(seq);
        storeMapper.addSmsAccount(SmsAccountbean);
        webUtils.userAddLog(request, 14, SmsAccountbean);
        return new ResultInfo(0, "", "新增快递站点信息成功", null);

    }

    @Override
    public ResultInfo updateExpInfo(ExpStoreInfo store, HttpServletRequest request) {
        String error = isExpValid(store, true);
        StoreBean oldStoreBean = storeMapper.selectStoreNotDeleted(store.getStoreId()).get(0);
        StoreSiteBean oldSiteBean = storeMapper.selectStoreSite(store.getStoreId()).get(0);
        List<StoreExpBean> oldEcList = storeMapper.selectEcListByStoreId(store.getStoreId());
        List<StoreSchoolBean> oldColList = storeMapper.selectColListByStoreId(store.getStoreId());
        if (error != null) {
            return new ResultInfo(-1, "2030010004", error);
        }
        // 更新商户
        StoreBean bean = new StoreBean();
        bean.setStoreId(store.getStoreId());
        bean.setStoreCode(store.getStoreCode());
        bean.setStoreName(store.getStoreName());
        bean.setLocation(store.getLocation());
        bean.setType(1);
        bean.setStatus(store.getStatus());
        storeMapper.updateStore(bean);
        webUtils.userEditLog(request, 14, oldStoreBean, bean);
        // 更新站点
        StoreSiteBean siteBean = new StoreSiteBean();
        siteBean.setStoExpId(store.getStoreId());
        siteBean.setPhone(store.getPhone());
        siteBean.setSupervisorName(store.getSupervisorName());
        siteBean.setPacketModeMgr(store.getPacketModeMgr());
        siteBean.setPacketModeSend(store.getPacketModeSend());
        siteBean.setCloseMode(store.getCloseMode());
        storeMapper.updateStoreSite(siteBean);
        webUtils.userEditLog(request, 14, oldSiteBean, siteBean);
        // 更新快递公司(全部删掉重新插入)
        storeMapper.deleteRelativeExp(store.getStoreId());
        webUtils.userDeleteLog(request, 14, oldEcList);
        insertExpressRelDefault(store.getStoreId(), store.getDefaultECId());
        if (store.getEcList() != null && !store.getEcList().isEmpty()) {
            for (Long expressId : store.getEcList()) {
                StoreExpBean expBean = new StoreExpBean();
                expBean.setExpressCompanyId(expressId);
                expBean.setStoreId(store.getStoreId());
                expBean.setBeDefault(0);
                storeMapper.insertStoreExp(expBean);
                webUtils.userAddLog(request, 14, expBean);
            }
        }
        storeMapper.deleteStoreSchool(store.getStoreId());
        webUtils.userDeleteLog(request, 14, oldColList);
        // 插入tb_store_college_rel(可选,多个)
        if (store.getColList() != null && !store.getColList().isEmpty()) {
            List<StoreSchoolBean> colList = new ArrayList<>();
            for (Long collegeId : store.getColList()) {
                colList.add(new StoreSchoolBean(null, store.getStoreId(), collegeId));
            }
            storeMapper.insertStoreSchoolList(colList);
            webUtils.userAddLog(request, 14, colList);
        }
        return new ResultInfo(0, "", "修改快递站点成功");
    }

    // 主营
    private void insertExpressRelDefault(long storeId, long expressId) {
        StoreExpBean expBean = new StoreExpBean();
        expBean.setExpressCompanyId(expressId);
        expBean.setStoreId(storeId);
        expBean.setBeDefault(1);
        storeMapper.insertStoreExp(expBean);
    }

    /**
     * @param @param  store
     * @param @param  needPK
     * @param @return 参数
     * @return boolean 返回类型
     * @throws @author heyi
     * @Method: isExpValid
     * @Description: TODO(验证快递站点信息)
     * @date 2016年4月12日 下午6:51:01
     */
    private String isExpValid(ExpStoreInfo store, boolean needPK) {

        String msg = null;
        if (needPK && (store.getStoreId() == null)) {
            msg = "参数缺少或不合法";
        }
        if (StringUtils.isBlank(store.getStoreCode()) || StringUtils.isBlank(store.getStoreName())) {
            msg = "名称与编号信息不能为空";
        } /*
             * else if (StringUtils.isBlank(store.getSupervisorName())) { msg =
			 * "负责人不能为空"; }
			 */ else if (store.getStatus() == null || (store.getStatus() != 0 && store.getStatus() != 1)) {
            msg = "快递站点状态为空";
        } else if (store.getDefaultECId() == null) {
            msg = "默认快递公司不能为空";
        } else if (store.getColList() == null) {
            msg = "学校不能为空";
        }
        return msg;
    }

    @Override
    public long add(StoreInfo store) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void update(StoreInfo store) {
        // TODO Auto-generated method stub

    }

    @Override
    public SearchStoreInfo selectStoreExpressByPhone(String phone) throws Exception {
        SearchStoreBean storeBean = storeMapper.selectStoreExpressByPhone(phone);
        if (storeBean == null) {
            return null;
        }
        SearchStoreInfo storeInfo = new SearchStoreInfo();
        BeanUtils.copyProperties(storeBean, storeInfo);
        return storeInfo;
    }

    @Override
    public List<AxpStoreInfo> selectCollegesByStoreId(Long storeId) throws Exception {
        List<AxpStoreInfo> infoList = new ArrayList<>();
        List<AxpBean> storeBeans = storeMapper.selectCollegesByStoreId(storeId);
        for (AxpBean store : storeBeans) {
            AxpStoreInfo storeInfo = new AxpStoreInfo();
            storeInfo.setStoreId(store.getStoAxpId());
            storeInfo.setCollegeId(String.valueOf(store.getCollegeId()));
            storeInfo.setStoreName(store.getStoAxpName());
            storeInfo.setCollegeName(store.getCollegeName());
            infoList.add(storeInfo);
        }
        return infoList;
    }

    /**
     * 批量逻辑删除商户
     */
    @Override
    public int removeByIds(List<Integer> ids, HttpServletRequest request) {
        int result = storeMapper.logicalDeleteStoreByIds(ids);
//		if(result>0)
//		{
//			userMapper.batchLogicDelUser(ids);
//		}
        webUtils.userDeleteLog(request, 14, ids);
        return result;
    }

    @Override
    public int existCoo(List<Integer> ids) {
        return storeMapper.getExistCoo(ids);
    }

    @Override
    public int getStoreUser(List<Integer> ids) {
        return storeMapper.getStoreUser(ids);
    }

    /**
     * 批量更改开启众包模式状态
     */
    @Override
    public int batchCheckPacketMode(Map<String, Object> map, HttpServletRequest request) {
        try {
            int result = storeMapper.batchCheckPacketMode(map);
            // webUtils.userLog(request, "批量更改众包开启状态", 14, ids.toString(),
            // null,"edit");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("操作失败");
        }

    }

    /**
     * 根据条件查询快递站点列表
     */
    @Override
    public List<ExpStoreInfo> selectExpStoreInfo(Map<String, Object> map) {
        try {
            List<ExpStoreInfo> infoList = new ArrayList<>();
            List<ExpStoreBean> storeBeans = storeMapper.selectExpStoreInfoByParames(map);
            for (ExpStoreBean store : storeBeans) {
                ExpStoreInfo storeInfo = new ExpStoreInfo();
                BeanUtils.copyProperties(store, storeInfo);
                infoList.add(storeInfo);
            }
            return infoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
