package com.zhiduan.axp.dao.order.mapper;

import com.zhiduan.axp.controller.model.user.UserCheckInfo;
import com.zhiduan.axp.dao.user.bean.UserCheckBean;
import com.zhiduan.axp.dao.user.bean.UserPExpBean;
import com.zhiduan.axp.dao.user.bean.UserStoreExpBean;

import javax.inject.Named;
import java.util.List;

@Named("orderVerifyMapper")
public interface OrderVerifyMapper {

    /**
     * 获众包用户列表
     */
    List<UserCheckBean> getAuditUsers(UserCheckBean userCheckBean);

    UserCheckBean getAuditUser(UserCheckBean userCheckBean);

    int getAuditUsersCount(UserCheckInfo user);

    /**
     * 根据用户编码查询用户信息
     */
    UserStoreExpBean selectByPrimaryKey(Long userId);

    /**
     * 根据用户编码动态更新用户商户关系信息
     */
    Integer updateByPrimaryKeySelective(UserPExpBean record);

    Integer updateActivation(UserCheckInfo record);


    List<UserCheckBean> getManageUsers(UserCheckBean userCheckBean);

    int getManageUsersCount(UserCheckInfo user);

}