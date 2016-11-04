/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.user;

import com.rofour.baseball.controller.model.UserModel;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;

public interface UserManagerLoginService {

	/**
	 * @Description: 管理员登录
	 * @param user
	 * @return 
	 * @throws Exception 
	 */
	    
	public UserManagerLoginBean login(UserModel user) throws Exception;
	
	
}
