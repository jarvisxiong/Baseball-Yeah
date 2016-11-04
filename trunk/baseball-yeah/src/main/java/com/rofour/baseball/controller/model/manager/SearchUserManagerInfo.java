/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;
import java.util.List;

/**
 * 用户管理查询实体
 *
 * @author xl
 */
public class SearchUserManagerInfo implements Serializable {

	private static final long serialVersionUID = -7278724407021648456L;
	/**
	 * 管理用户ID
	 */
	private Long userManagerId;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 工号
	 */
	private String userCode;
	/**
	 * 所属部门唯一标识
	 */
	private Long deptId;
	/**
	 * 所属职务ID
	 */
	private Long dutyId;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 联系电话
	 */
	private String contactTel;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 是否启用 1启用 0禁用
	 */
	private Byte beEnabled;
	/**
	 * 是否已删除 0未删除 1已删除
	 */
	private Byte beDeleted;
	// #region 扩展字段
	/**
	 * 部门编码
	 */
	private String deptCode;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 职务名称
	 */
	private String dutyName;
	/**
	 * 角色Id,多个用逗号隔开
	 */
	private List<RoleInfo> roles;


	
	public SearchUserManagerInfo() {
		super();
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public long getDutyId() {
		return dutyId;
	}

	public void setDutyId(long dutyId) {
		this.dutyId = dutyId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public List<RoleInfo> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleInfo> roles) {
		this.roles = roles;
	}

	public Long getUserManagerId() {
		return userManagerId;
	}

	public void setUserManagerId(Long userManagerId) {
		this.userManagerId = userManagerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Byte getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Byte beEnabled) {
		this.beEnabled = beEnabled;
	}

	public Byte getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Byte beDeleted) {
		this.beDeleted = beDeleted;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}

	@Override
	public String toString() {
		return "SearchUserManagerInfo [userManagerId=" + userManagerId
				+ ", loginName=" + loginName + ", password=" + password
				+ ", userName=" + userName + ", userCode=" + userCode
				+ ", deptId=" + deptId + ", dutyId=" + dutyId + ", gender="
				+ gender + ", contactTel=" + contactTel + ", address="
				+ address + ", beEnabled=" + beEnabled + ", beDeleted="
				+ beDeleted + ", deptCode=" + deptCode + ", deptName="
				+ deptName + ", dutyName=" + dutyName + ", roles=" + roles
				+ "]";
	}

	public SearchUserManagerInfo(Long userManagerId, String loginName,
			String password, String userName, String userCode, Long deptId,
			Long dutyId, String gender, String contactTel, String address,
			Byte beEnabled, Byte beDeleted, String deptCode, String deptName,
			String dutyName, List<RoleInfo> roles) {
		super();
		this.userManagerId = userManagerId;
		this.loginName = loginName;
		this.password = password;
		this.userName = userName;
		this.userCode = userCode;
		this.deptId = deptId;
		this.dutyId = dutyId;
		this.gender = gender;
		this.contactTel = contactTel;
		this.address = address;
		this.beEnabled = beEnabled;
		this.beDeleted = beDeleted;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.dutyName = dutyName;
		this.roles = roles;
	}

	
}
