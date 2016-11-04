/**  
* @Title: Constant.java
* @package com.rofour.baseball.web.common
* @Project: axp-web
* @Description: 常量类
* @author wdx
* @date 2016年4月8日 下午2:31:34 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.common;

import java.util.HashMap;



/**
* @ClassName: Constant
* @Description: 常量类
* @author wdx
* @date 2016年4月10日 下午6:14:40 
*
*/
    
public class Constant {

	public static String appkey;
	public static String appid;
	public static String format;
	public static String version;

	public static  HashMap<String, String> axpurl;

	public static String getAppkey() {
		return appkey;
	}

	public static void setAppkey(String appkey) {
		Constant.appkey = appkey;
	}

	public static String getAppid() {
		return appid;
	}

	public static void setAppid(String appid) {
		Constant.appid = appid;
	}

	public static String getFormat() {
		return format;
	}

	public static void setFormat(String format) {
		Constant.format = format;
	}

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String version) {
		Constant.version = version;
	}

	public static HashMap<String, String> getAxpurl() {
		return axpurl;
	}

	public static void setAxpurl(HashMap<String, String> axpurl) {
		Constant.axpurl = axpurl;
	}
	
	
	
}
