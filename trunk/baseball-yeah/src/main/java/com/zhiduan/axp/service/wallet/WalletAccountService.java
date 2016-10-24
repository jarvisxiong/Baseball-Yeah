/**  
* @Title: WalletAccountService.java
* @Package com.zhiduan.axp.service.wallet
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月13日 下午4:09:52 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.service.wallet;

import java.util.List;
import java.util.Map;

import com.zhiduan.axp.controller.model.CapitalChangeInfo;
import com.zhiduan.axp.controller.model.wallet.WalletAccountInfo;
import com.zhiduan.axp.controller.model.wallet.WalletCashShowInfo;
/**
* @ClassName: WalletAccountService
* @Description: 钱包账户
* @author heyi
* @date 2016年6月13日 下午4:09:52 
*
*/
public interface WalletAccountService {
	 List<WalletAccountInfo> selectWalletAccount(WalletAccountInfo searchInfo);
	 List<WalletCashShowInfo> selectCashShow(Map<String,Object> map);
	 int lockAccount(Integer acctId);
	 int ulockAccount(Integer acctId); 
	 List<CapitalChangeInfo> GetCapitalChangeByPaprames(CapitalChangeInfo info);
}
