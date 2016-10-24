/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.model.manager;

import java.io.Serializable;

/**
 * 
 * @ClassName: CollegeManageInfo
 * @Description: 学校管理DTO类
 * @author xl
 * @date 2016年4月7日 下午7:14:31
 *
 */
public class CollegeManageInfo implements Serializable {

	private static final long serialVersionUID = 6421102966920975753L;
	/**
	 * 编码
	 */
	private Long collegeId;
	/**
	 * 编号
	 */
	private String collegeCode;
	/**
	 * 全称
	 */
	private String fullName;
	/**
	 * 简称
	 */
	private String simpleName;
	/**
	 * 省编码
	 */
	private Long provinceId;
	/**
	 * 省名称
	 */
	private String provinceName;
	/**
	 * 市编码
	 */
	private Long cityId;
	/**
	 * 市 名称
	 */
	private String cityName;
	/**
	 * 县/区编码
	 */
	private Long countyId;
	/**
	 * 县/区名称
	 */
	private String countyName;
	/**
	 * 办学性质编码
	 */
	private String natureCode;
	/**
	 * 办学性质
	 */
	private String natureValue;
	/**
	 * 所属类型编码
	 */
	private String collegeTypeCode;
	/**
	 * 所属类型
	 */
	private String collegeTypeValue;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态
	 */
	private Byte status;

	/**
	 * 众包模式 0关闭 1开启
	 */
	private Integer packetMode;

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeCode() {
		return collegeCode;
	}

	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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
		this.countyName = countyName;
	}

	public String getNatureCode() {
		return natureCode;
	}

	public void setNatureCode(String natureCode) {
		this.natureCode = natureCode;
	}

	public String getNatureValue() {
		return natureValue;
	}

	public void setNatureValue(String natureValue) {
		this.natureValue = natureValue;
	}

	public String getCollegeTypeCode() {
		return collegeTypeCode;
	}

	public void setCollegeTypeCode(String collegeTypeCode) {
		this.collegeTypeCode = collegeTypeCode;
	}

	public String getCollegeTypeValue() {
		return collegeTypeValue;
	}

	public void setCollegeTypeValue(String collegeTypeValue) {
		this.collegeTypeValue = collegeTypeValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getPacketMode() {
		return packetMode;
	}

	public void setPacketMode(Integer packetMode) {
		this.packetMode = packetMode;
	}

}
