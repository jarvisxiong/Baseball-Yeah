package com.rofour.baseball.common;
/**
 * 
 * @author zhoujie
 *
 */
public class RedisKeyConstants {
    public final static String REDIS_KEY_PREFIX = "acct.";
    
    public final static String REDIS_KEY_MANAGE_PREFIX = "manage.";

    public final static String EXCHANGE_COUNT = REDIS_KEY_PREFIX + "exchange.count.";

    public final static String EXCHANGE_BALANCE = REDIS_KEY_PREFIX + "exchange.balance.";

    public final static String PAYPWD_ERROR_AMOUNT = REDIS_KEY_PREFIX + "paypwd.error.amount.";

    public final static String PAYPWD_ERROR_AMOUNT_FREEZE = REDIS_KEY_PREFIX + "paypwd.error.amount.freeze.";

    /**
     * 学校map存放
     */
    public final static String COLLEAGE_MAP = REDIS_KEY_MANAGE_PREFIX + "colleage.map";
    /**
     * 学校list存放
     */
    public final static String COLLEAGE_LIST = REDIS_KEY_MANAGE_PREFIX + "colleage.list";

    /**
     * 城市map缓存
     */
    public final static String CITY_MAP = REDIS_KEY_MANAGE_PREFIX + "city.map";

    /**
     * 短信服务商map缓存
     */
    public final static String SMS_VENDOR_MAP = REDIS_KEY_MANAGE_PREFIX + "sms.vendor.map";
    
    /**
	 * 短信服务商list缓存
	 */
	public final static String SMS_VENDOR_LIST = REDIS_KEY_MANAGE_PREFIX + "sms.vendor.list";

    /**
     * 系统设置map缓存
     */
    public final static String SYS_PARAMETER_MAP = REDIS_KEY_MANAGE_PREFIX + "sys.parameter.map";
    
    /**
	 * 系统设置list缓存
	 */
	public final static String SYS_PARAMETER_LIST = REDIS_KEY_MANAGE_PREFIX + "sys.parameter.list";
    
    /**
     * 快递公司map缓存
     */
    public final static String EXPRESS_MAP =  REDIS_KEY_MANAGE_PREFIX +"express.map";
    /**
     * 快递公司list缓存
     */
    public final static String EXPRESS_LIST =  REDIS_KEY_MANAGE_PREFIX +"express.list";
    
    /**
     * 地域map缓存
     */
    public final static String Area_MAP =  REDIS_KEY_MANAGE_PREFIX +"area.map";
    /**
     * 地域list缓存
     */
    public final static String Area_LIST =  REDIS_KEY_MANAGE_PREFIX +"area.list";
	
	/**
	 * 区县map缓存
	 */
	public final static String COUNTY_MAP =  REDIS_KEY_MANAGE_PREFIX +"county.map";
	
	/**
	 * 区县所有记录缓存
	 */
	public final static String COUNTY_ALL_MAP =  REDIS_KEY_MANAGE_PREFIX +"county.all.map";
	
	/**
	 * 城市所有记录缓存
	 */
	public final static String CITY_ALL_MAP =  REDIS_KEY_MANAGE_PREFIX +"city.all.map";
	
	/**
	 * 所有有学校记录城市缓存
	 */
	public final static String CITY_ALL_HASCOLLEGE_MAP =  REDIS_KEY_MANAGE_PREFIX +"city.all.hascollege.map";
	
	/**
	 * 省map缓存
	 */
	public final static String PROVINCE_MAP =  REDIS_KEY_MANAGE_PREFIX +"province.map";
	
	/**
	 * 省所有记录缓存
	 */
	public final static String PROVINCE_ALL_MAP =  REDIS_KEY_MANAGE_PREFIX +"province.all.map";
}
