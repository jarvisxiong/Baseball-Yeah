package com.zhiduan.axp.common;
/**
 * 
 * @author zhoujie
 *
 */
public class RedisKeyConstants {
	public final static String REDIS_KEY_PREFIX = "acct.";
	
	public final static String EXCHANGE_COUNT = REDIS_KEY_PREFIX+"exchange.count.";
	
	public final static String EXCHANGE_BALANCE = REDIS_KEY_PREFIX+"exchange.balance.";
	
	public final static String PAYPWD_ERROR_AMOUNT =  REDIS_KEY_PREFIX +"paypwd.error.amount.";
	
	public final static String PAYPWD_ERROR_AMOUNT_FREEZE =  REDIS_KEY_PREFIX +"paypwd.error.amount.freeze.";
}
