/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;

/**
 * @ClassName: AreaBean
 * @Description: 区域
 * @author cy
 * @date 2016-04-18 10:24:31
 */

public class AreaBean implements Serializable {

	private static final long serialVersionUID = -7378006392269371711L;
	/**
	 * 编号
	 **/
	private Long areaId;
	/**
	 * 区域名称
	 **/
	private String areaName;
	/**
	 * 序号
	 **/
	private Integer sortNo;
	/**
	 * 商务负责人
	 **/
	private String businessPrincipal;
	/**
	 * 联系电话
	 **/
	private String contactPhone;

	public AreaBean() {
		super();
	}

	public AreaBean(Long areaId, String areaName, Integer sortNo, String businessPrincipal, String contactPhone) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.sortNo = sortNo;
		this.businessPrincipal = businessPrincipal;
		this.contactPhone = contactPhone;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo == null ? null : sortNo;
	}

	public String getBusinessPrincipal() {
		return businessPrincipal;
	}

	public void setBusinessPrincipal(String businessPrincipal) {
		this.businessPrincipal = businessPrincipal == null ? null : businessPrincipal.trim();
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone == null ? null : contactPhone.trim();
	}

}