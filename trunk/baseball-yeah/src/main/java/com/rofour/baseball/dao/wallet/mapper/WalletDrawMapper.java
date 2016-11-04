package com.rofour.baseball.dao.wallet.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.dao.wallet.bean.AcctExchangeCashBean;
import com.rofour.baseball.dao.wallet.bean.UserDrawCheckBean;

@Named("walletDrawMapper")
public interface WalletDrawMapper {

	/**
	 * 
	 * @Description: 查询用户提现申请信息
	 * @param map
	 * @return
	 */
	List<UserDrawCheckBean> getUserDrawList(Map<String, Object> map);

	/**
	 * 
	 * @Description: 查询用户提现申请信息总数
	 * @param map
	 * @return
	 */
	int getUserDrawListCount(Map<String, Object> map);

	/**
	 * 
	 * @Description: 获取交易类型
	 * @return
	 */
	List<Map<String, String>> getThdPaymentType();

	/**
	 * 
	 * @Description: 新增用户提现状态
	 * @param map
	 * @return
	 */
	int insertExchangeState(Map<String, Object> map);

	/**
	 * 
	 * @Description: 批量新增用户提现状态
	 * @param map
	 * @return
	 */
	int BatchInsertExchangeState(Map<String, Object> map);

	/**
	 * 
	 * @Description: 更新用户提现状态
	 * @param map
	 * @return
	 */
	int updateStateById(Map<String, Object> map);

	/**
	 * 
	 * @Description: 批量更新用户提现状态
	 * @param map
	 * @return
	 */
	int BatchUpdateStateById(Map<String, Object> map);

	/**
	 * 
	 * @Description: 查询用户账户兑换记录
	 * @param exchangeId
	 * @return
	 */
	AcctExchangeCashBean getUserDrawById(long exchangeId);

}