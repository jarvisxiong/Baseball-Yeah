/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.user.bean;

import java.io.Serializable;



/**
 * @ClassName: UserManagerMapper
 * @Description: 管理用户数据实体
 * @author wny
 * @date 2016年4月10日 下午9:52:16
 *
 */
public class UserManagerLoginBean implements Serializable {

	private static final long serialVersionUID = 2676995377814476027L;

	
	   public UserManagerLoginBean() {
	       
	    }

	public UserManagerLoginBean(Long userManagerId, String loginName, String password, String userName, String userCode,
			Long deptId, Long dutyId, String gender, String contactTel, String address, Byte beEnabled, Byte beDeleted,
			String deptCode, String deptName,String dutyName) {
		this.userManagerId = userManagerId;
		this.loginName = loginName;
		this.password = password;
		this.userName = userName;
        this.userCode=userCode;
        this.deptId=deptId;
        this.dutyId=dutyId;
        this.gender=gender;
        this.contactTel=contactTel;
        this.address=address;
        this.beEnabled=beEnabled;
        this.beDeleted=beDeleted;
        this.deptCode=deptCode;
        this.deptName=deptName;
        this.dutyName=dutyName;
	}



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
	// #endregion
	
	/**
	 * 前端显示用户名
	 */
	private String showName;
	
	
	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
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


}
