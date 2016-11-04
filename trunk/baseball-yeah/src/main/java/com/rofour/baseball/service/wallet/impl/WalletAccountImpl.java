/**  
* @Title: WalletAccountImpl.java
* @package com.rofour.baseball.service.wallet.impl
* @Project: baseball-yeah
* @Description: 钱包账户管理业务实现类
* @author heyi
* @date 2016年6月13日 下午4:12:00 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.service.wallet.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rofour.baseball.controller.model.CapitalChangeInfo;
import com.rofour.baseball.controller.model.wallet.WalletAccountInfo;
import com.rofour.baseball.controller.model.wallet.WalletCashShowInfo;
import com.rofour.baseball.dao.wallet.mapper.WalletAccountMapper;
import com.rofour.baseball.service.wallet.WalletAccountService;

/**
 * @ClassName: WalletAccountImpl
 * @Description: 钱包账户实现
 * @author heyi
 * @date 2016年6月13日 下午4:12:00
 *
 */
@Service("walletAccountService")
public class WalletAccountImpl implements WalletAccountService {

	@Resource(name = "walletAccountMapper")
	private WalletAccountMapper walletAccountMapper;

	/**
	 * 获取钱包账户列表
	 */
	@Override
	public List<WalletAccountInfo> selectWalletAccount(WalletAccountInfo searchInfo) {
		try {
			return walletAccountMapper.selectWalletAccount(searchInfo);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取提现帐号信息
	 */
	@Override
	public List<WalletCashShowInfo> selectCashShow(Map<String, Object> map) {
		try {
			return walletAccountMapper.selectCashShow(map);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解锁
	 */
	@Override
	public int lockAccount(Integer acctId) {

		try {
			return walletAccountMapper.lockAccount(acctId);
		} catch (Exception e) {
			throw e;
		}
		
	}

	/**
	 * 冻结
	 */
	@Override
	public int ulockAccount(Integer acctId) {
		try {
			return walletAccountMapper.unlockAccount(acctId);
		} catch (Exception e) {
			throw e;
		}
	}

	
	@Override
	public List<CapitalChangeInfo> GetCapitalChangeByPaprames(CapitalChangeInfo info) {
		try {
			return walletAccountMapper.selectCapitalChange(info);
		} catch (Exception e) {
			return null;
		}
	}

}
