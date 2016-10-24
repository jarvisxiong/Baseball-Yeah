/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;

/**
 *  
* @ClassName: PropertyDictBean
* @Description: 属性字典数据访问层实体
* @author heyi
* @date 2016年4月3日 下午2:17:49 
*
 */
public class PropertyDictBean implements Serializable {

	    
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

	public PropertyDictBean() {
		super();
	}

	public PropertyDictBean(String propertyId, String propertyShortcode, String callAlias, String description,
			String propertyValue, Integer sortNo) {
		this.propertyId = propertyId;
		this.propertyShortcode = propertyShortcode;
		this.callAlias = callAlias;
		this.description = description;
		this.propertyValue = propertyValue;
		this.sortNo = sortNo;
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
