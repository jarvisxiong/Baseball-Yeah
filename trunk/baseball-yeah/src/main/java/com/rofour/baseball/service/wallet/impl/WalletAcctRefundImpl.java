package com.rofour.baseball.service.wallet.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofour.baseball.controller.model.wallet.AcctRefundInfo;
import com.rofour.baseball.dao.wallet.bean.AcctRefundBean;
import com.rofour.baseball.dao.wallet.mapper.AcctRefundMapper;
import com.rofour.baseball.service.wallet.WalletAcctRefundService;

/**
 * 
* @ClassName: WalletAcctRefundImpl
* @Description: 钱包退款实现类
* @author WJ
* @date 2016年7月22日 上午10:41:01 
*
 */
@Service("walletAcctRefundService")
public class WalletAcctRefundImpl implements WalletAcctRefundService {
	@Autowired
	private AcctRefundMapper acctRefundMapper;
	/**
	 * 
	 * @Description: 查询全部记录
	 * @return List<AcctRefundInfo>
	 *
	 */
	public List<AcctRefundBean> list(AcctRefundInfo info){
		return acctRefundMapper.queryAll(info);
	}
	/**
	 * 查询总数
	 */
	public int getTotal(AcctRefundInfo info) {
		return acctRefundMapper.getTotal(info);
	}
}
