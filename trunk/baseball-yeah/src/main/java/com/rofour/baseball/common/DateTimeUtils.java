package com.rofour.baseball.common;

import java.awt.List;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间工具类
 * @author zhoujie
 *
 */
public class DateTimeUtils {
	private final static Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);
	
	public final static String DEFAULT_DATE_FORMAT_PATTERN_SHORT = "yyyy-MM-dd";
	
	public final static String DEFAULT_DATE_FORMAT_PATTERN_NOT_YEAR = "MM-dd";
	
	public final static String DEFAULT_TIME_FORMAT_PATTERN_SHORT = "HH:mm:ss";
	
	public final static String DEFAULT_DATE_FORMAT_PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
	
	public final static String DEFAULT_DATE_TIME = "23:59:59";
	
	private  static  Map<String, FastDateFormat> conMap = new ConcurrentHashMap<String, FastDateFormat>();
	
	static {
		conMap.put(DEFAULT_DATE_FORMAT_PATTERN_SHORT, FastDateFormat.getInstance(DEFAULT_DATE_FORMAT_PATTERN_SHORT));
		conMap.put(DEFAULT_TIME_FORMAT_PATTERN_SHORT, FastDateFormat.getInstance(DEFAULT_DATE_FORMAT_PATTERN_SHORT));
		conMap.put(DEFAULT_DATE_FORMAT_PATTERN_FULL, FastDateFormat.getInstance(DEFAULT_DATE_FORMAT_PATTERN_FULL));
	}
	/**
	 * 获得当前时间的字符串
	 * @param pattern 时间格式
	 * @return 时间字符串
	 */
	public static String getCurrentDateString(String pattern){
		FastDateFormat sdf = getDateFormat(pattern);
		return sdf.format(new Date());
	}
	/**
	 * 获得时间格式对象
	 * @param pattern
	 * @return
	 */
	private static FastDateFormat getDateFormat(String pattern){
		if(StringUtils.isEmpty(pattern)){
			return null;
		}
		FastDateFormat sdf = null;
		if(conMap.containsKey(pattern)){
			sdf = conMap.get(pattern);
		}else{
			//处理没有找到的格式
			try {
				sdf = FastDateFormat.getInstance(pattern);
				conMap.put(pattern, sdf);
			} catch (Exception e) {
				sdf = FastDateFormat.getInstance(DEFAULT_DATE_FORMAT_PATTERN_FULL);
			}
		}
		return sdf;
	}
	/**
	 * 获得时间的字符串
	 * @param date 待转换的日期
	 * @param pattern 时间格式
	 * @return
	 */
	public static String getDateString(Date date, String pattern){
		FastDateFormat sdf = getDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static Date getDateByString(String dateStr, String pattern){
		if(StringUtils.isEmpty(dateStr) ){
			return null;
		}
		FastDateFormat sdf = getDateFormat(pattern);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error("getDateByString e={}",e);
			return null;
		}
	}
	
	/**
	 * 根据时期和时间创建时间
	 * @param date
	 * @param time
	 * @return
	 */
	public static Date creatDate(String date,String time){
		if(StringUtils.isEmpty(date) || StringUtils.isEmpty(time)){
			return null;
		}
		StringBuffer dateSb = new StringBuffer(date);
		return getDateByString(dateSb.append(" ").append(time).toString(), DEFAULT_DATE_FORMAT_PATTERN_FULL);
	}
	
	
	
	
}
