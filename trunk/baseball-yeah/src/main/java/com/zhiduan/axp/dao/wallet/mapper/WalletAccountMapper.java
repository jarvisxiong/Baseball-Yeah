/**  
* @Title: WalletAccountMapper.java
* @Package com.zhiduan.axp.dao.wallet.mapper
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月13日 下午4:08:14 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.dao.wallet.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.controller.model.CapitalChangeInfo;
import com.zhiduan.axp.controller.model.wallet.WalletAccountInfo;
import com.zhiduan.axp.controller.model.wallet.WalletCashShowInfo;

/**
* @ClassName: WalletAccountMapper
* @Description:钱包账户
* @author heyi
* @date 2016年6月13日 下午4:08:14 
*
*/
@Named("walletAccountMapper")
public interface WalletAccountMapper {
	List<WalletAccountInfo> selectWalletAccount(WalletAccountInfo userInfo);
	
	List<WalletCashShowInfo> selectCashShow(Map<String,Object> map);
	
	int lockAccount(Integer acctId);
	
	int unlockAccount(Integer acctId);
	
	List<CapitalChangeInfo> selectCapitalChange(CapitalChangeInfo info);
}
