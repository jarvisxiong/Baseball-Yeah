/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.converters.LongArrayConverter;

/**
 * 用户管理查询实体
 *
 * @author xl
 */
public class SearchUserManagerBean implements Serializable {

	private static final long serialVersionUID = 3595157905372989368L;
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
	 * 角色
	 */
	private List<RoleBean> roles;
	/**
	 * 用户角色Id集合
	 */
	private List<Long> roleIds=new ArrayList<Long>();
	/**
	 * 用户角色名称集合
	 */
	private List<String> roleNames;
	/**
	 * 用户角色名称字符串
	 */
	private String roleName;
	/**
	 * 用户角色名称集合
	 */
	private String roleNameStr;
	/**
	 * 用户角色Id集合
	 */
	private String roleIdStr;
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<String> getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
	public Long getUserManagerId() {
		return userManagerId;
	}
	public void setUserManagerId(Long userManagerId) {
		this.userManagerId = userManagerId;
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
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getDutyId() {
		return dutyId;
	}
	public void setDutyId(Long dutyId) {
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
	public List<RoleBean> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}
	public SearchUserManagerBean() {
		super();
	}
	public List<Long> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}
	public String getRoleNameStr() {
		return roleNameStr;
	}
	public void setRoleNameStr(String roleNameStr) {
		this.roleNameStr = roleNameStr;
	}
	public String getRoleIdStr() {
		return roleIdStr;
	}
	public void setRoleIdStr(String roleIdStr) {
		this.roleIdStr = roleIdStr;
	}
	public SearchUserManagerBean(Long userManagerId, String loginName,
			String password, String userName, String userCode, Long deptId,
			Long dutyId, String gender, String contactTel, String address,
			Byte beEnabled, Byte beDeleted, String deptCode, String deptName,
			String dutyName, List<RoleBean> roles, List<Long> roleIds,
			List<String> roleNames, String roleName, String roleNameStr,
			String roleIdStr) {
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
		this.roleIds = roleIds;
		this.roleNames = roleNames;
		this.roleName = roleName;
		this.roleNameStr = roleNameStr;
		this.roleIdStr = roleIdStr;
	}


	

}
