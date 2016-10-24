package com.zhiduan.axp.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.UserInfo;

/**
 * @ClassName: UserController
 * @Description: 用户中心控制层
 * @author ZhangLei
 * @date 2016年4月25日 下午8:37:26
 *
 */

@Controller
@RequestMapping(value = "/sys")
public class SystemController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value="gotoDuty" , method = RequestMethod.GET)
	public ModelAndView gotoDuty(HttpServletRequest request,UserInfo userInfo){
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/system/dutyManager/dutyManager");
		}else {
			return new ModelAndView("/");
		}
	}
	@RequestMapping(value="/gotopropertyDictManager")
	public ModelAndView gotopropertyDictManager(HttpServletRequest request){
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("system/propertyDictManager/propertyDictManager");
		}else {
			return new ModelAndView("/");
		}
	}
	@RequestMapping(value="/gotosysParameterManager")
	public ModelAndView gotosysParameterManager(HttpServletRequest request){
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("system/sysParameterManager/sysParameterManager");
		}else {
			return new ModelAndView("/");
		}
	}
}