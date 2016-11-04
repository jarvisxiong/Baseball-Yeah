/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;

/**
 * @ClassName: City
 * @Description: 市的实体类
 * @author liujingbo
 * @date 2016年3月26日 下午9:31:04
 *
 */

public class CityInfo implements Serializable {
	

	private static final long serialVersionUID = -5693423113264598836L;
	/**
	 * 市的id
	 */

	private Long cityId;
	/**
	 * 省的id
	 */
	private Long provinceId;
	/**
	 * 市的名称
	 */
	private String cityName;
	/**
	 * 电话区号
	 */
	private String telZoneCode;
	/**
	 * 邮编
	 */
	private String postCode;
	/**
	 * 序号
	 */
	private Short sortNo;
	
	

	public CityInfo(Long cityId, Long provinceId, String cityName, String telZoneCode, String postCode, Short sortNo) {
		this.cityId = cityId;
		this.provinceId = provinceId;
		this.cityName = cityName;
		this.telZoneCode = telZoneCode;
		this.postCode = postCode;
		this.sortNo = sortNo;
	}

	public CityInfo() {
		super();
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName == null ? null : cityName.trim();
	}

	public String getTelZoneCode() {
		return telZoneCode;
	}

	public void setTelZoneCode(String telZoneCode) {
		this.telZoneCode = telZoneCode == null ? null : telZoneCode.trim();
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