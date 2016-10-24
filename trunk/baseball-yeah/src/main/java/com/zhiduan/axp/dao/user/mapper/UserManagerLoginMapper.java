/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.user.mapper;


import javax.inject.Named;

import org.apache.ibatis.annotations.Param;

import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;





/**
 * 管理用户数据操作
* @ClassName: UserManagerMapper
* @Description:管理用户数据操作
* @author wny
* @date 2016年4月11日 下午4:06:18 
*
 */
@Named("userManagerLoginMapper")
public interface UserManagerLoginMapper {
  
	
	/**
	 * @Description: 根据登录名获取用户信息
	 * @param loginName 登录名
	 * @return UserManagerBean  
	 *
	 */
	UserManagerLoginBean	 getInfoByLoginName(String loginName);
	
	UserManagerLoginBean loginSelect(@Param("loginName")String loginName);
}