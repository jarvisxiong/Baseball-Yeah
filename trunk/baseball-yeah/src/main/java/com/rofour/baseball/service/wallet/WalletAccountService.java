/**  
* @Title: WalletAccountService.java
* @package com.rofour.baseball.service.wallet
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月13日 下午4:09:52 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.service.wallet;

import java.util.List;
import java.util.Map;

import com.rofour.baseball.controller.model.CapitalChangeInfo;
import com.rofour.baseball.controller.model.wallet.WalletAccountInfo;
import com.rofour.baseball.controller.model.wallet.WalletCashShowInfo;
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
