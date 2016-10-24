package com.zhiduan.axp.service.wallet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.wallet.WalletVerifyInfo;

public interface WalletVerifyService {
	
	 List<WalletVerifyInfo> selectWalletVerify(WalletVerifyInfo searchInfo);
	 
	 ResultInfo getWalletAudit(long userId) throws Exception;
	 
	 ResultInfo walletAudit(WalletVerifyInfo searchInfo,HttpServletRequest request) throws Exception;
}
