package com.rofour.baseball.controller.wallet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.rofour.baseball.common.CipherPwdUtils;
import com.rofour.baseball.controller.model.UserInfo;
import com.rofour.baseball.controller.model.UserModel;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.manager.MenuService;
import com.rofour.baseball.service.user.LoginService;
import com.rofour.baseball.service.user.UserManagerLoginService;
import com.rofour.baseball.service.user.UserManagerService;


@Controller
@RequestMapping(value="/wallet")
public class WalletController {
	
	@RequestMapping(value = "/gotoWalletDrawMoney", method = RequestMethod.GET)
	public ModelAndView toWalletDrawMoney(HttpServletRequest request,UserModel user) throws Exception {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("wallet/walletDrawMoneyMananger/walletDrawMoneyManager");
		}else {
			return new ModelAndView("/error/noPermission");
		}
	}
	
    @RequestMapping(value = "/gotoWalletVerify", method = RequestMethod.GET)
    public ModelAndView toWalletVerify(HttpServletRequest request, UserInfo userInfo) {
    	if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("wallet/walletVerifyManager/walletVerifyManager");
		}else {
			return new ModelAndView("/error/noPermission");
		}
    }
	
    @RequestMapping(value = "/gotoWalletException", method = RequestMethod.GET)
    public ModelAndView toWalletException(HttpServletRequest request, UserInfo userInfo) {
    	if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("wallet/walletExceptionManager/walletExceptionManager");
		}else {
			return new ModelAndView("/error/noPermission");
		}
    }
    
    @RequestMapping(value = "/gotoWalletAccountManager", method = RequestMethod.GET)
    public ModelAndView gotoWalletManager(HttpServletRequest request, UserInfo userInfo) {
    	if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("wallet/walletAccountManager/walletAccountManager");
		}else {
			return new ModelAndView("/error/noPermission");
		}
    }
    @RequestMapping(value = "/gotoPolicyVoucher", method = RequestMethod.GET)
    public ModelAndView gotoPolicyVoucher(HttpServletRequest request, UserInfo userInfo) {
    	if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("wallet/walletAccountManager/acctPolicyVoucher");
		}else {
			return new ModelAndView("/error/noPermission");
		}
    }
	@RequestMapping(value = "/gotoWalletAcctActivityManager", method = RequestMethod.GET)
	public ModelAndView gotoWalletAcctActivityManager(HttpServletRequest request, UserInfo userInfo) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("wallet/walletAcctActivityManager/walletAcctActivityManager");
		}else {
			return new ModelAndView("/error/noPermission");
		}
	}
	@RequestMapping(value = "/gotoAddWalletAcctActivityManager", method = RequestMethod.GET)
	public ModelAndView gotoAddWalletAcctActivityManager(HttpServletRequest request, UserInfo userInfo) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("wallet/walletAcctActivityManager/addWalletAccActivity");
		}else {
			return new ModelAndView("/error/noPermission");
		}
	}
	@RequestMapping(value = "/gotoEditWalletAcctActivityManager", method = RequestMethod.GET)
	public ModelAndView gotoEditWalletAcctActivityManager(HttpServletRequest request, UserInfo userInfo) {
		if (request.getSession().getAttribute("user") != null) {
			ModelAndView modelAndView=new ModelAndView("wallet/walletAcctActivityManager/editWalletAccActivity");
			String activityId=request.getParameter("activityId");
			if (!StringUtils.isBlank(activityId)) {
				modelAndView.addObject("activityId", activityId);
			}
			return modelAndView;
		}else {
			return new ModelAndView("/error/noPermission");
		}
	}
}
