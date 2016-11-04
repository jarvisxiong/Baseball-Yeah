package com.rofour.baseball.common;

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @ClassName: IpUtils
 * @Description: ip 工具类
 * @author wjj
 * @date 2016年4月19日 下午4:34:24
 */
public class IpUtils {

	/**
	 * @Description: 获取登录用户的IP地址
	 * @param request
	 * @return ip 地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "127.0.0.1";
		}
		if (ip.split(",").length > 1) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	 

}
