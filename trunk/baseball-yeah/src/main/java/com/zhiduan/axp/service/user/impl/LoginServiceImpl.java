/**  
* @Title: LoginService.java
* @Package com.zhiduan.axp.web.service
* @Project: axp-web
* @Description: (用一句话描述该文件做什么)
* @author 王东勋
* @date 2016年4月8日 下午1:48:46 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.service.user.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhiduan.axp.common.Constant;
import com.zhiduan.axp.common.HttpClientUtils;
import com.zhiduan.axp.controller.model.AxpApiModel;
import com.zhiduan.axp.controller.model.UserModel;
import com.zhiduan.axp.service.user.LoginService;

/**
* @ClassName: LoginService
* @Description: 登录服务
* @author 王东勋
* @date 2016年4月8日 下午1:48:46 
*
*/
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	//定义反序列化 数据格式
	private static final TypeReference<AxpApiModel<HashMap<String, String>>> loginTypeRef=new TypeReference<AxpApiModel<HashMap<String, String>>>() {};
	

	    
	/**
	 * @Description: login
	 * @param user
	 * @param mAndView
	 * @return
	 * @throws IOException 
	 */
	    
	public ModelAndView login(UserModel user,ModelAndView mAndView) throws IOException {
		
		/**1.参数校验*/
		if(StringUtils.isEmpty(user.getName())){
			
		}
		if(StringUtils.isEmpty(user.getPassword())){
			
		}
		
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("accountPwd", user.getPassword());
		map.put("userName", user.getName());
		map.put("imei", "868199020019338");
		
		//"http://114.55.79.162/axp-acl/user/loginin.htm"
		
		//调用axp服务
		AxpApiModel<?> info=(AxpApiModel<?>) HttpClientUtils.post(Constant.axpurl.get("user_login_serv"), map,loginTypeRef);
		
		//System.out.println(info.toString());
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> data = (HashMap<String, String>) info.getData();
		
		//返回参数赋值
		if(data!=null){
			user.setToken(data.get("token"));
			user.setTimeout(data.get("timeout"));
		}
		
		return mAndView.addObject("result", user);
	}
}
