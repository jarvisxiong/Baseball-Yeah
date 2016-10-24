/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.controller.model.manager;

import java.io.Serializable;

/**
 * @ClassName: CityCollegeBean
 * @Description: 按城市查询学校实体类
 * @author xl
 * @date 2016年4月2日 下午5:27:25
 *
 */

public class CityCollegeInfo implements Serializable {

	private static final long serialVersionUID = -4145520726590064559L;
	/**
	 * 学校编码
	 */
	private String collegeId;
	/**
	 * 学校编号
	 */
	private String collegeCode;
	/**
	 * 学校名称
	 */
	private String fullName;
	/**
	 * 所属区编码
	 */
	private String countyId;
	/**
	 * 所属区
	 */
	private String countyName;

	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
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

	public String getCountyId() {
		return countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
}
