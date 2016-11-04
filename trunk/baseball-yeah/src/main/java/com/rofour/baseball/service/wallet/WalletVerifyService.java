package com.rofour.baseball.service.wallet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.wallet.WalletVerifyInfo;

public interface WalletVerifyService {
	
	 List<WalletVerifyInfo> selectWalletVerify(WalletVerifyInfo searchInfo);
	 
	 ResultInfo getWalletAudit(long userId) throws Exception;
	 
	 ResultInfo walletAudit(WalletVerifyInfo searchInfo,HttpServletRequest request) throws Exception;
}
