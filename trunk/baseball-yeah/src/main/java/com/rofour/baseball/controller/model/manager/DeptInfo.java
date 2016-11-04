/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;

/**
 * @ClassName: DeptInfo
 * @Description: 管理中心 部门
 * @author 高振
 * @date 2016年3月27日 下午4:52:23
 *
 */
public class DeptInfo implements Serializable {

	private static final long serialVersionUID = 867969852013446911L;
	/**
	 * 编码
	 */
	private Long deptId;
	/**
	 * 部门编号
	 */
	private String deptCode;
	/**
	 * 名称
	 */
	private String deptName;
	/**
	 * 是否删除
	 */
	private Byte beDeleted;

	public DeptInfo(Long deptId, String deptCode, String deptName, Byte beDeleted) {
		this.deptId = deptId;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.beDeleted = beDeleted;
	}

	public DeptInfo() {
		super();
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode == null ? null : deptCode.trim();
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName == null ? null : deptName.trim();
	}

	public Byte getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Byte beDeleted) {
		this.beDeleted = beDeleted;
	}
}