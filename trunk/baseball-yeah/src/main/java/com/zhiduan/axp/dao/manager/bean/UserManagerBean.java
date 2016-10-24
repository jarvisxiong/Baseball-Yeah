/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: UserManagerBean
 * @Description: 用户管理数据类型
 * @author Kevin Zou
 * @date 2016年4月20日 上午11:07:30
 */
public class UserManagerBean implements Serializable {

	private static final long serialVersionUID = -6857565133780243634L;
	/**
	 * ID。
	 */
	private long userManagerId;
	/**
	 * 登录名。
	 */
	private String loginName;
	/**
	 * 登录密码。
	 */
	private String password;
	/**
	 * 用户名称。
	 */
	private String userName;
	/**
	 * 用户编码。
	 */
	private String userCode;
	/**
	 * 部门ID。
	 */
	private long deptId;
	/**
	 * 职务ID。
	 */
	private long dutyId;
	/**
	 * 性别。
	 */
	private String gender;
	/**
	 * 联系电话。
	 */
	private String contactTel;
	/**
	 * 地址。
	 */
	private String address;
	/**
	 * 是否启用，值0为禁用，1为启用。
	 */
	private short beEnabled;
	/**
	 * 是否删除，值0为未删除，1为已删除。
	 */
	private short beDeleted;
	
	
	public UserManagerBean() {
		super();
	}
	
	public long getUserManagerId() {
		return userManagerId;
	}
	public void setUserManagerId(long userManagerId) {
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
	public long getDeptId() {
		return deptId;
	}
	public void setDeptId(long deptId) {
		this.deptId = deptId;
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
	public short getBeEnabled() {
		return beEnabled;
	}
	public void setBeEnabled(short beEnabled) {
		this.beEnabled = beEnabled;
	}
	public short getBeDeleted() {
		return beDeleted;
	}
	public void setBeDeleted(short beDeleted) {
		this.beDeleted = beDeleted;
	}
	public UserManagerBean(long userManagerId, String loginName,
			String password, String userName, String userCode, long deptId,
			long dutyId, String gender, String contactTel, String address,
			short beEnabled, short beDeleted) {
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
	}

	
}
