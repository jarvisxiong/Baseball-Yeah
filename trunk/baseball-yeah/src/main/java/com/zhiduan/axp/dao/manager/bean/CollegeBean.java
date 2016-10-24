/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TbCollegeBean
 * @Description: 学校实体类
 * @author xl
 * @date 2016年3月26日 下午1:25:55
 *
 */

public class CollegeBean implements Serializable {

	private static final long serialVersionUID = -5453199978315929101L;
	/**
	 * 编码
	 */
	private Long collegeId;


	/**
	 * 编辑用
	 */
	private  String editCollegeId;

	private List<String> editCollegeList;

	public List<String> getEditCollegeList() {
		return editCollegeList;
	}

	public void setEditCollegeList(List<String> editCollegeList) {
		this.editCollegeList = editCollegeList;
	}

	public String getEditCollegeId() {
		return editCollegeId;
	}

	public void setEditCollegeId(String editCollegeId) {
		this.editCollegeId = editCollegeId;
	}
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
	 * 市编码
	 */
	private Long cityId;
	/**
	 * 县/区编码
	 */
	private Long countyId;
	/**
	 * 办学性质
	 */
	private String nature;
	/**
	 * 所属类型
	 */
	private String collegeType;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态
	 */
	private Byte status;

	public CollegeBean() {
		super();
	}

	public CollegeBean(Long collegeId, String collegeCode, String fullName, String simpleName, Long provinceId,
			Long cityId, Long countyId, String nature, String collegeType, String remark, Byte status) {
		this.collegeId = collegeId;
		this.collegeCode = collegeCode;
		this.fullName = fullName;
		this.simpleName = simpleName;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.countyId = countyId;
		this.nature = nature;
		this.collegeType = collegeType;
		this.remark = remark;
		this.status = status;
	}

	public Long getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeCode() {
		return this.collegeCode;
	}

	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSimpleName() {
		return this.simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public Long getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return this.cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getCountyId() {
		return this.countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	public String getNature() {
		return this.nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getCollegeType() {
		return this.collegeType;
	}

	public void setCollegeType(String collegeType) {
		this.collegeType = collegeType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
}
