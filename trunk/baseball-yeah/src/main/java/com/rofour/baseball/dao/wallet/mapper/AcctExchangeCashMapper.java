package com.rofour.baseball.dao.wallet.mapper;

import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.controller.model.wallet.AcctExchangeCashInfo;
import com.rofour.baseball.dao.wallet.bean.AcctExchangeCashBean;

/**
 * 资金兑换操作
 * @author zhoujie
 *
 */
@Named("acctExchangeCashMapper")
public interface AcctExchangeCashMapper {
	
	/**
	 * * 添加资金兑换记录
	 * @param acctExchangeBalanceBean
	 * @return
	 */
	public  int addExchangeBalance(AcctExchangeCashBean acctExchangeBalanceBean);
	
	/**
	 * 修改状态
	 * @param params
	 * @return
	 */
	public int updateExchangeBalanceFlowId(Map<String, Object> params);
	
	/**
	 * @Description: 查询今日提现流水
	 * @param userId
	 * @return 
	 */
	    
	AcctExchangeCashInfo getExchangeCash(Long userId);
	
	
	/**
	 * 修改状态失效
	 * @param params
	 * @return
	 */
	public int updateExchangeBalanceDel(long exchangId);
	
	
	/**
	 * @Description: 更具交易Id查询用户Id
	 * @param exchangeId
	 * @return 
	 */
	    
	public Long selectByExchangeId(Long exchangeId);
}
