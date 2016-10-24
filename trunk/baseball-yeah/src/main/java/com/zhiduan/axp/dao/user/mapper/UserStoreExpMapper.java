package com.zhiduan.axp.dao.user.mapper;

import javax.inject.Named;

import com.zhiduan.axp.dao.user.bean.UserStoreExpBean;

/**
* @ClassName: UserStoreExpMapper
* @Description: 操作用户商户信息接口
* @author ZhangLei
* @date 2016年3月26日 下午5:59:53 
*
*/
    
@Named("userStoreExpMapper")
public interface UserStoreExpMapper {
	
	/**
	 * 根据用户编码删除用户商户信息
	 */
	Integer deleteByPrimaryKey(Long userId);

	/**
	 * 插入用户商户关系信息
	 */
	Integer insert(UserStoreExpBean record);

	/**
	 * 动态插入用户商关系信息
	 */
	Integer insertSelective(UserStoreExpBean record);

	/**
	 * 根据用户编码查询用户信息
	 */
    UserStoreExpBean selectByPrimaryKey(Long userId);

    /**
	 * 根据用户编码动态更新用户商户关系信息
	 */
    Integer updateByPrimaryKeySelective(UserStoreExpBean record);

    /**
	 * 根据用户编码更新用户商户关系信息
	 */
    Integer updateByPrimaryKey(UserStoreExpBean record);

	Integer updateStoreUser(UserStoreExpBean record);
    /**
  	 * 更改负责人状态
  	 */
    Integer updateBeSupervisor(UserStoreExpBean record);
}