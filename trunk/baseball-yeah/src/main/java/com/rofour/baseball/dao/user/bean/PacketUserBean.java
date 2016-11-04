package com.rofour.baseball.dao.user.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangyu
 * @ClassName:com.rofour.baseball.dao.user.bean.PacketUserBean
 * @Description: 描述：
 * @date 2016/10/11 15:14
 */
public class PacketUserBean implements Serializable {
	private static final long serialVersionUID = 5063961239563717250L;
	private Long userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 注册时间
	 */
	private Date signupTime;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 1-普通小派 2-coo 3-ceo
	 */
	private Integer officeRoleType;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 校区名称
	 */
	private String fullName;
	/**
	 * 宿舍
	 */
	private String dormitoryAddress;
	/**
	 * 审核状态
	 */
	private Integer beEnabled;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getSignupTime() {
		return signupTime;
	}

	public void setSignupTime(Date signupTime) {
		this.signupTime = signupTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getOfficeRoleType() {
		return officeRoleType;
	}

	public void setOfficeRoleType(Integer officeRoleType) {
		this.officeRoleType = officeRoleType;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDormitoryAddress() {
		return dormitoryAddress;
	}

	public void setDormitoryAddress(String dormitoryAddress) {
		this.dormitoryAddress = dormitoryAddress;
	}

	public Integer getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Integer beEnabled) {
		this.beEnabled = beEnabled;
	}
}
