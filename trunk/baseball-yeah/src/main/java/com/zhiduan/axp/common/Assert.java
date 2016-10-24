/**  
 * Copyright (c) 2016, 指端科技.
 */


    
package com.zhiduan.axp.common;

import java.util.Collection;

import com.zhiduan.axp.exception.BusinessException;

/**
 * @ClassName: Assert
 * @Description: 断言工具类
 * @author wq
 * @date 2016年4月22日 下午3:45:23 
 */
public abstract class Assert {
	 
	/**
	 * @Description: 是否为 true
	 * @param expression
	 * @param messageCode
	 */
	public static void isTrue(boolean expression, String messageCode) {
		if (!expression) {
			throw new BusinessException(messageCode);
		}
	}
    /**
     * @Description: 对象为空
     * @param object
     * @param messageCode
     */
	public static void isNull(Object object, String messageCode) {
		if (object != null) {
			throw new BusinessException(messageCode);
		}
	}
    /**
     * @Description: 对象非空
     * @param object
     * @param messageCode
     */
	public static void notNull(Object object, String messageCode) {
		if (object == null) {
			throw new BusinessException(messageCode);
		}
	}
    /**
     * @Description: 数组非空
     * @param array
     * @param messageCode
     */
	public static void notEmpty(Object[] array, String messageCode) {
		if (array == null || array.length == 0) {
			throw new BusinessException(messageCode);
		}
	}
	/**
	 * @Description: 集合非空
	 * @param collection
	 * @param messageCode
	 */
	public static void notEmpty(Collection<?> collection, String messageCode) {
		if (collection == null || collection.size() == 0) {
			throw new BusinessException(messageCode);
		}
	}
	
	/**
	 * @Description: 字符串非空
	 * @param text
	 * @param messageCode
	 */
	public static void hasLength(String text, String messageCode) {
		if (StringUtils.isEmpty(text)) {
			throw new BusinessException(messageCode);
		}
	}
 
	 
	
	
 
}
