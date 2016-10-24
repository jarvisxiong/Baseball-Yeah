/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;

/**
 * @ClassName: CityBean
 * @Description: 市的实体类
 * @author liujingbo
 * @date 2016年3月26日 下午9:27:19
 *
 */

public class CityBean implements Serializable {

	private static final long serialVersionUID = 4562417269653457844L;

	/**
	 * 市id
	 */

	private Long cityId;

	/**
	 * 省id
	 */

	private Long provinceId;

	/**
	 * 市名
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

	/**
	 * @Constructor: CityBean
	 *
	 * @param cityId
	 * @param provinceId
	 * @param cityName
	 * @param telZoneCode
	 * @param postCode
	 * @param sortNo
	 **/

	public CityBean(Long cityId, Long provinceId, String cityName, String telZoneCode, String postCode, Short sortNo) {
		this.cityId = cityId;
		this.provinceId = provinceId;
		this.cityName = cityName;
		this.telZoneCode = telZoneCode;
		this.postCode = postCode;
		this.sortNo = sortNo;
	}

	/**
	 * @Constructor: CityBean
	 *
	 **/

	public CityBean() {
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

	public String gettelZoneCode() {
		return telZoneCode;
	}

	public void settelZoneCode(String telZoneCode) {
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