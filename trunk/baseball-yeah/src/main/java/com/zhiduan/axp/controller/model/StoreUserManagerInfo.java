


    
package com.zhiduan.axp.controller.model;

import java.io.Serializable;
import java.util.Date;



public class StoreUserManagerInfo implements Serializable{
	

	    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7601296038111423460L;
	/**
	 * @Fields storeName :商户名称
	 */



	private  String realName;

	private  String idNo;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	private String storeName;
	/**
	 * @Fields identityNumber :身份证号
	 */
	    
	private String identityNumber;
	/**
	 * @Fields beSupervisor :是否负责人
	 */
	private Byte beSupervisor;
	
	/**
	 * @Fields storeId :商户ID
	 */
	    
	private Long storeId;
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


	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public Byte getBeSupervisor() {
		return beSupervisor;
	}

	public void setBeSupervisor(Byte beSupervisor) {
		this.beSupervisor = beSupervisor;
	}

	public StoreUserManagerInfo(Byte beSupervisor,String identityNumber,String storeName,Long userId,Long storeId, String nickname, String userName,
			String accountPwd, Byte verifyStatus, String phone, String gender,
			Byte age, Long countyId, Date signupTime, String signupIp,
			String icon, Byte beDeleted) {
		super();
		this.storeId=storeId;
		this.storeName=storeName;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public StoreUserManagerInfo() {
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

