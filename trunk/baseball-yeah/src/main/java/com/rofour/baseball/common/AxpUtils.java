package com.rofour.baseball.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangyu
 * @ClassName:com.rofour.baseball.tools.AxpUtils
 * @Description: 描述：爱学派公共方法类
 * @date 2016/4/27 10:36
 */
public class AxpUtils {
	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AxpUtils.class);
	/**
	 * @param password
	 * @return boolean 返回类型
	 * @author ZhangLei
	 * @Description: 检查密码是否符合要求
	 * @date 2016年3月22日 下午1:30:43
	 **/
	public static boolean checkPwd(String password) {
		Pattern p4 = Pattern.compile("^[^\u4e00-\u9fa5]{0,}$");
		boolean b4 = false;
		int len = 0;
		try {
			len = password.length();
			Matcher m = p4.matcher(password);
			b4 = m.matches();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		if (b4 && 6 <= len && len <= 12) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 属性拷贝
	 *
	 * @param sourceObject 源对象
	 * @param targetClazz  目标对象
	 * @param <T>          范型对象
	 * @return 目标对象
	 */
	public static <T> T copyProperties(Object sourceObject, Class<T> targetClazz) {
		Object object = null;
		try {
			object = targetClazz.newInstance();
			BeanUtils.copyProperties(sourceObject, object);
		} catch (BeansException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (InstantiationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return (T) object;
	}

	/**
	 * 属性复制
	 * @param sourceObj 源对象
	 * @param targetObj 目标对象
	 */
	public static void copyProperties(Object sourceObj, Object targetObj) {
		BeanUtils.copyProperties(sourceObj, targetObj);
	}
}
