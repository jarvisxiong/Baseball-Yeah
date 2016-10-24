package com.zhiduan.axp.controller.wallet;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.wallet.WalletVerifyInfo;
import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;
import com.zhiduan.axp.service.wallet.WalletVerifyService;
import com.zhiduan.axp.controller.base.BaseController;


@Controller
@RequestMapping(value="/wallet/verify")
public class WalletVerifyController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WalletController.class);
	@Autowired
	@Qualifier(value="walletVerifyService")
	WalletVerifyService walletVerifyService;
	
	
	@RequestMapping(value="/postWalletVerifyList")
	@ResponseBody
	public List<WalletVerifyInfo> postWalletVerifyList(HttpServletRequest request,WalletVerifyInfo searchInfo) 
	{   
		/*String data=request.getParameter("data");
		WalletVerifyInfo searchInfo=JsonUtils.readValue(data, WalletVerifyInfo.class);*/
		try {
			
			if (request.getSession().getAttribute("user") != null) {
				 List<WalletVerifyInfo> infoList = walletVerifyService.selectWalletVerify(searchInfo);
				   return infoList;
				}else {
					return null;
				}
		} catch (Exception e) {
			return null;
		}
		
	}
	/**
	 * 钱包用户审核信息
	 * @return
	 */
	@RequestMapping(value = "/getwalletauditinfo")
	@ResponseBody
	public ResultInfo getAudit(HttpServletRequest request, WalletVerifyInfo info){
		try {
			return walletVerifyService.getWalletAudit(info.getUserId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		return new ResultInfo(-1, "", "查询审核信息失败！");
	}
	
	/**
	 * 钱包账户审核
	 * @return
	 */
	@RequestMapping(value = "/walletaudit", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo audit(HttpServletRequest request,WalletVerifyInfo walletVerifyInfo){
		try {
			UserManagerLoginBean userInfo=(UserManagerLoginBean)request.getSession().getAttribute("user");
			walletVerifyInfo.setVerifyUserId(userInfo.getUserManagerId());
			walletVerifyInfo.setVerifyUserName(userInfo.getUserName());
			ResultInfo result = checkAuditParam(walletVerifyInfo);
			if(null != result){
				return result;
			}
			return walletVerifyService.walletAudit(walletVerifyInfo,request);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return processException(e, LOGGER);
		}
	}
	/**
	 * 审核参数校验
	 * @param userAuditInfo
	 * @return
	 */
	private ResultInfo checkAuditParam(WalletVerifyInfo walletVerifyInfo){
		if(null == walletVerifyInfo){
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}else if(null == walletVerifyInfo.getUserId() || null == walletVerifyInfo.getVerifyStatus()){
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}
		return null;
	}
}
