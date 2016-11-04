/**  
 * Copyright (c) 2016, 指端科技.
 */


    
package com.rofour.baseball.service.user;

import java.io.IOException;

import org.springframework.web.servlet.ModelAndView;

import com.rofour.baseball.controller.model.UserModel;



/**
 * @ClassName: LoginService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wdx
 * @date 2016年4月21日 上午11:11:12 
 */
public interface LoginService {
	public ModelAndView login(UserModel user,ModelAndView mAndView) throws IOException;
}
