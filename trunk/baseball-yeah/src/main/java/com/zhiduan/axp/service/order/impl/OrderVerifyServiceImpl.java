package com.zhiduan.axp.service.order.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhiduan.axp.common.*;
import com.zhiduan.axp.controller.model.AxpApiModel;
import com.zhiduan.axp.controller.model.Permission;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.SelectUserInfo;
import com.zhiduan.axp.controller.model.resource.CredentialURLInfo;
import com.zhiduan.axp.controller.model.user.ExpUserAuditInfo;
import com.zhiduan.axp.controller.model.user.ExpUserAuditResultInfo;
import com.zhiduan.axp.controller.model.user.ExpUserPerfectInfo;
import com.zhiduan.axp.controller.model.user.UserCheckInfo;
import com.zhiduan.axp.dao.order.mapper.OrderVerifyMapper;
import com.zhiduan.axp.dao.user.bean.UserCheckBean;
import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;
import com.zhiduan.axp.dao.user.bean.UserPExpBean;
import com.zhiduan.axp.dao.user.bean.UserStoreExpBean;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.order.OrderVerifyService;
import com.zhiduan.axp.service.resource.ResourceService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author ZhangLei
 * @ClassName: OrderVerifyServiceImpl
 * @Description: 众包审核服务实现
 * @date 2016年5月26日 下午5:19:39
 */

@Service("orderVerifyService")
public class OrderVerifyServiceImpl implements OrderVerifyService {

    @Autowired
    @Qualifier("orderVerifyMapper")
    OrderVerifyMapper orderVerifyMapper;


    @Autowired
    private ResourceService resourceService;

    @Autowired
    private WebUtils webUtils;

    @Override
    public List<UserCheckInfo> getAuditUsers(UserCheckInfo user) {
        List<UserCheckInfo> dataList = new ArrayList<UserCheckInfo>();
        UserCheckBean userCheckBean = new UserCheckBean();
        AxpUtils.copyProperties(user, userCheckBean);
        List<UserCheckBean> storeUserManagerBeanList = orderVerifyMapper.getAuditUsers(userCheckBean);
        if (CollectionUtils.isNotEmpty(storeUserManagerBeanList)) {
            for (UserCheckBean item : storeUserManagerBeanList) {
                UserCheckInfo userCheckInfo = new UserCheckInfo();
                AxpUtils.copyProperties(item, userCheckInfo);
                dataList.add(userCheckInfo);
            }
        }
        return dataList;
    }


    @Override
    public List<UserCheckInfo> getManageUsers(UserCheckInfo user) {
        List<UserCheckInfo> dataList = new ArrayList<UserCheckInfo>();
        UserCheckBean userCheckBean = new UserCheckBean();
        AxpUtils.copyProperties(user, userCheckBean);
        List<UserCheckBean> storeUserManagerBeanList = orderVerifyMapper.getManageUsers(userCheckBean);
        if (CollectionUtils.isNotEmpty(storeUserManagerBeanList)) {
            for (UserCheckBean item : storeUserManagerBeanList) {
                UserCheckInfo userCheckInfo = new UserCheckInfo();
                AxpUtils.copyProperties(item, userCheckInfo);
                dataList.add(userCheckInfo);
            }
        }
        return dataList;
    }

    @Override
    public int getAuditUsersCount(UserCheckInfo user) throws Exception {
        UserCheckBean userCheckBean = new UserCheckBean();
        AxpUtils.copyProperties(user, userCheckBean);
        return orderVerifyMapper.getAuditUsersCount(user);
    }

    @Override
    public int getManageUsersCount(UserCheckInfo user) throws Exception {
        UserCheckBean userCheckBean = new UserCheckBean();
        AxpUtils.copyProperties(user, userCheckBean);
        return orderVerifyMapper.getManageUsersCount(user);
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public ResultInfo getAudit(long userId) throws Exception {

        ExpUserPerfectInfo expUserPerfectInfo = new ExpUserPerfectInfo();
        UserCheckBean userCheckBean = new UserCheckBean();
        userCheckBean.setUserId(userId);
        UserCheckBean userInfo = orderVerifyMapper.getAuditUser(userCheckBean);

        if (null != userInfo) {
            AxpUtils.copyProperties(userInfo, expUserPerfectInfo);
            if (StringUtils.isNotEmpty(userInfo.getVerifyMsg())) {
                expUserPerfectInfo.setAuditResultInfo(JsonUtils.readValue(userInfo.getVerifyMsg(), ExpUserAuditResultInfo.class));
            }
            String remark = userInfo.getRemark();
            if (remark != null) {
                expUserPerfectInfo.setVerifyRemark(remark);
            }
            String idNo = userInfo.getIdNo();
            //如果身份证为空 那么取学生证号
            if (idNo == null || idNo.isEmpty()) {
                //设置证件信息
                setCredentialUrlInfo(userId, expUserPerfectInfo, "studentCard");
                expUserPerfectInfo.setIdentityNumber(userInfo.getStuNo());
            } else {
                expUserPerfectInfo.setIdentityNumber(idNo);
                setCredentialUrlInfo(userId, expUserPerfectInfo, "identityCard");
            }
        }
        return new ResultInfo(0, "", "查询用户审核信息成功", expUserPerfectInfo);
    }

    @Override
    public ResultInfo expAudit(ExpUserAuditInfo userAuditInfo, UserManagerLoginBean loginBean, HttpServletRequest request) throws Exception {
        try {

            //查询当前用户的审核状态
            UserStoreExpBean userStoreExp = orderVerifyMapper.selectByPrimaryKey(Long.parseLong(userAuditInfo.getUserId()));
            long vstatus = (long) userStoreExp.getVerifyStatus();
            if (vstatus == 0) {
                throw new BusinessException("02024");
            } else if (vstatus == 2) {
                throw new BusinessException("02023");
            }
            UserPExpBean userPExpBean = new UserPExpBean();
            userPExpBean.setUserId(userStoreExp.getUserId());
            userPExpBean.setState(Byte.parseByte(userAuditInfo.getVerifyStatus()));
            userPExpBean.setUpdateUser(userAuditInfo.getVerifyUserId());
            Date date = new Date();
            userPExpBean.setUpdateDate(date);
            userPExpBean.setUserId(Long.parseLong(userAuditInfo.getUserId()));
            userPExpBean.setVerifyMsg(userAuditInfo.getVerifyInfoString());
            userPExpBean.setRemark(userAuditInfo.getVerifyRemark());
            int result = orderVerifyMapper.updateByPrimaryKeySelective(userPExpBean);
            if (result != 0) {
                webUtils.userLog(request, String.format("用户ID：%s 审核状态由%s 改为：%s", userAuditInfo.getUserId(), vstatus, userAuditInfo.getVerifyStatus()),
                        140, "", "", Permission.AUDIT.name());
                Map<String, String> map = new HashMap<String, String>();
                map.put("userId", userAuditInfo.getUserId());
                String url = Constant.axpurl.get("packet_audit_serv");


                // 定义反序列化 数据格式
                final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
                };

                ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
                // 返回参数赋值
                if (data.getSuccess() < 0) {
                    return new ResultInfo(-1, "0", "调用AXP接口失败", "");
                }


                return new ResultInfo(0, "0", "用户审核信息成功", "");
            } else {
                return new ResultInfo(-1, "-1", "用户审核信息失败", "");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public ResultInfo activation(UserCheckInfo userInfo, HttpServletRequest request) {

        orderVerifyMapper.updateActivation(userInfo);

        if (userInfo.getGrabOrderMode().equals("0")) {
            webUtils.userLog(request, String.format("用户ID：%s 封存", userInfo.getUserId()), 143, "", "", Permission.SEALED.name());
        } else {
            webUtils.userLog(request, String.format("用户ID：%s 激活", userInfo.getUserId()), 143, "", "", Permission.ACTIVATION.name());
        }

        return new ResultInfo(0, "0", "操作成功！", "");
    }

    /**
     * 获取用户照片
     *
     * @param userId
     * @param expUserPerfectInfo
     */
    private void setCredentialUrlInfo(long userId, ExpUserPerfectInfo expUserPerfectInfo) {
        ResultInfo credentialUrls = resourceService.getCredentialUrls(userId);
        if (null != credentialUrls && credentialUrls.getSuccess() == 0) {
            List<CredentialURLInfo> credentialURLInfo = (List<CredentialURLInfo>) credentialUrls.getData();
            if (CollectionUtils.isNotEmpty(credentialURLInfo)) {
                expUserPerfectInfo.setPhotoList(credentialURLInfo);
            }
        }
    }

    /**
     * @param userId
     * @param expUserPerfectInfo
     * @param creType            creType identityCard studentCard
     */
    private void setCredentialUrlInfo(long userId, ExpUserPerfectInfo expUserPerfectInfo, String creType) {

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bizId", userId);
            map.put("attachType", AttachConstant.TYPE_AUTH_PACKET);

            String url = Constant.axpurl.get("resource_getOriginAttachList_serv");
            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };
            ResultInfo credentialUrls = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
            if (null != credentialUrls && credentialUrls.getSuccess() == 1) {
                List<CredentialURLInfo> credentialURLInfo = (List<CredentialURLInfo>) credentialUrls.getData();
                if (CollectionUtils.isNotEmpty(credentialURLInfo)) {
                    expUserPerfectInfo.setPhotoList(credentialURLInfo);
                }
            }
        } catch (Exception e) {

        }


    }
}
