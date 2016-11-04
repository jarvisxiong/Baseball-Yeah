/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;

/**
 * 
* @ClassName: PropertyDictInfo
* @Description: TODO(属性字典逻辑层实体)
* @author heyi
* @date 2016年4月5日 上午11:34:52 
*
 */
public class PropertyDictInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 编号
	 */
	private String propertyId;
	/**
	 * 属性编码
	 */
	private String propertyShortcode;
	/**
	 * 调用别名
	 */
	private String callAlias;
	/**
	 * 描述信息
	 */
	private String description;
	/**
	 * 属性值
	 */
	private String propertyValue;
	/**
	 * 排序号
	 */
	private Integer sortNo;

	public PropertyDictInfo() {

	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyShortcode() {
		return propertyShortcode;
	}

	public void setPropertyShortcode(String propertyShortcode) {
		this.propertyShortcode = propertyShortcode;
	}

	public String getCallAlias() {
		return callAlias;
	}

	public void setCallAlias(String callAlias) {
		this.callAlias = callAlias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
}
