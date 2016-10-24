/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;

/**
 * @ClassName: ProvinceBean
 * @Description: 省的实体类
 * @author liujingbo
 * @date 2016年3月26日 下午9:29:26
 *
 */

public class ProvinceBean implements Serializable {

	private static final long serialVersionUID = 3767440884840283128L;
	/**
	 * 省的id
	 */

	private Long provinceId;
	/**
	 * 省的名字
	 */
	private String provinceName;
	/**
	 * 序号
	 */
	private Short sortNo;
	/**
	 * 区域id
	 */
	private Long areaId;
	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public ProvinceBean(Long provinceId, String provinceName, Short sortNo,Long areaId) {
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.sortNo = sortNo;
		this.areaId = areaId;
	}

	public ProvinceBean() {
		super();
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
		this.provinceName = provinceName == null ? null : provinceName.trim();
	}

	public Short getSortNo() {
		return sortNo;
	}

	public void setSortNo(Short sortNo) {
		this.sortNo = sortNo;
	}
}