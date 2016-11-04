/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.bean;

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

	/**
	 * 签到数
	 */
	private Long WorkSignin;

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

	public Short getSortNo() {
		return sortNo;
	}

	public void setSortNo(Short sortNo) {
		this.sortNo = sortNo;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getWorkSignin() {
		return WorkSignin;
	}

	public void setWorkSignin(Long workSignin) {
		WorkSignin = workSignin;
	}
}