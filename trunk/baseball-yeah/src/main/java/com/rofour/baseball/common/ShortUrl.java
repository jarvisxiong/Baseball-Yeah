/**  
 * Copyright (c) 2016, 指端科技.
 */


    
package com.rofour.baseball.common;

/**
 * @ClassName: ShortUrl
 * @Description: 新浪微博短地址常量
 * @author lpp
 * @date 2016年8月29日 上午10:36:29 
 */
public class ShortUrl {
	
	private static String source;
	
	public static String SINA_SHORTEN_URL = "http://api.t.sina.com.cn/short_url/shorten.json";

	public static String getSource() {
		return source;
	}

	public static void setSource(String source) {
		ShortUrl.source = source;
	}
}
