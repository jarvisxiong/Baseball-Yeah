/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.user;

import com.zhiduan.axp.controller.model.UserModel;
import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;

public interface UserManagerLoginService {

	/**
	 * @Description: 管理员登录
	 * @param user
	 * @return 
	 * @throws Exception 
	 */
	    
	public UserManagerLoginBean login(UserModel user) throws Exception;
	
	
}
