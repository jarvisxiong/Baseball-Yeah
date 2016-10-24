/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;

/**
 * @ClassName: CountyBean
 * @Description: 县的sql语句映射类
 * @author liujingbo
 * @date 2016年3月26日 下午9:28:50
 *
 */

public class CountyBean implements Serializable {

	private static final long serialVersionUID = -7583545675459512261L;
	/**
	 * 县的id
	 */
	private Long countyId;
	/**
	 * 县的名称
	 */
	private String countyName;
	/**
	 * 市的id
	 */
	private Long cityId;
	/**
	 * 邮政编码
	 */
	private String postCode;
	/**
	 * 序号
	 */
	private Short sortNo;

	public CountyBean(Long countyId, String countyName, Long cityId, String postCode, Short sortNo) {
		this.countyId = countyId;
		this.countyName = countyName;
		this.cityId = cityId;
		this.postCode = postCode;
		this.sortNo = sortNo;
	}

	public CountyBean() {
		super();
	}

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName == null ? null : countyName.trim();
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {

		this.postCode = postCode == null ? null : postCode.trim();
	}

	public Short getSortNo() {
		return sortNo;
	}

	public void setSortNo(Short sortNo) {
		this.sortNo = sortNo;
	}
}