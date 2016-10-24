package com.zhiduan.axp.dao.user.bean;

import java.io.Serializable;
import java.util.Date;

import com.zhiduan.axp.controller.model.BasePage;

public class UserBean extends BasePage implements Serializable {

	private static final long serialVersionUID = 1170014916672353667L;

	/**
	 * @Fields userId :用户ID
	 */
	    
	private Long userId;
	
	/**
	 * @Fields nickname :用户昵称
	 */

	private String nickname;

	
	/**
	 * @Fields userName :用户名
	 */
	private String userName;

	/**
	 * @Fields accountPwd :密码
	 */
	private String accountPwd;

	/**
	 * @Fields verifyStatus :账户状态
	 */
	private Byte verifyStatus;

	/**
	 * @Fields phone :手机号
	 */
	private String phone;

	/**
	 * @Fields gender :性别
	 */
	private String gender;

	/**
	 * @Fields age :年龄
	 */
	private Byte age;

	/**
	 * @Fields countyId :所属地区
	 */
	private Long countyId;

	/**
	 * @Fields signupTime :注册时间
	 */
	private Date signupTime;

	/**
	 * @Fields signupIp :注册IP
	 */
	private String signupIp;

	/**
	 * @Fields icon :头像
	 */
	private String icon;

	/**
	 * @Fields beDeleted :是否删除
	 */
	private Byte beDeleted;


	public UserBean(Long userId, String nickname, String userName,
			String accountPwd, Byte verifyStatus, String phone, String gender,
			Byte age, Long countyId, Date signupTime, String signupIp,
			String icon, Byte beDeleted) {
		super();
		this.userId = userId;
		this.nickname = nickname;
		this.userName = userName;
		this.accountPwd = accountPwd;
		this.verifyStatus = verifyStatus;
		this.phone = phone;
		this.gender = gender;
		this.age = age;
		this.countyId = countyId;
		this.signupTime = signupTime;
		this.signupIp = signupIp;
		this.icon = icon;
		this.beDeleted = beDeleted;
	}

	public UserBean() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public Byte getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Byte verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Byte getAge() {
		return age;
	}

	public void setAge(Byte age) {
		this.age = age;
	}

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}


	public Date getSignupTime() {
		return signupTime;
	}

	public void setSignupTime(Date signupTime) {
		this.signupTime = signupTime;
	}

	public String getSignupIp() {
		return signupIp;
	}

	public void setSignupIp(String signupIp) {
		this.signupIp = signupIp;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Byte getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Byte beDeleted) {
		this.beDeleted = beDeleted;
	}

}