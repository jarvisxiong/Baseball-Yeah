package com.rofour.baseball.dao.officemanage.bean;

import java.io.Serializable;
import java.util.List;

public class UserAcctBean implements Serializable{ 
		private static final long serialVersionUID = 8587980771430425002L;
		// 用户ID
		private String userId;
		// 手机号，登录账号
		private String phone;
		// 真实姓名
		private String realName;
		// 职务
		private int officeRoleType;
		// 性别
		private String gender;
		// 账号状态
		private int state;
		// 学校名
		private String collegeName;
		// 公共使用的时间
		private String pulicTime;
		// 宿舍地址
		private String dormitoryAddress;
		// 城市名
		private String cityName;
		// 商户名
		private String storeName;
		// 身份
		private int optType;
		// 商户ID
		private String stoExpId;
		// 申请记录ID
		private String auditId;
		private List<String> stoIdList;
		
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getRealName() {
			return realName;
		}
		public void setRealName(String realName) {
			this.realName = realName;
		}
		public int getOfficeRoleType() {
			return officeRoleType;
		}
		public void setOfficeRoleType(int officeRoleType) {
			this.officeRoleType = officeRoleType;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public String getCollegeName() {
			return collegeName;
		}
		public void setCollegeName(String collegeName) {
			this.collegeName = collegeName;
		}
		public String getPulicTime() {
			return pulicTime;
		}
		public void setPulicTime(String pulicTime) {
			this.pulicTime = pulicTime;
		}
		public String getDormitoryAddress() {
			return dormitoryAddress;
		}
		public void setDormitoryAddress(String dormitoryAddress) {
			this.dormitoryAddress = dormitoryAddress;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getStoreName() {
			return storeName;
		}
		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}
		public int getOptType() {
			return optType;
		}
		public void setOptType(int optType) {
			this.optType = optType;
		}
		public String getStoExpId() {
			return stoExpId;
		}
		public void setStoExpId(String stoExpId) {
			this.stoExpId = stoExpId;
		}
		public List<String> getStoIdList() {
			return stoIdList;
		}
		public void setStoIdList(List<String> stoIdList) {
			this.stoIdList = stoIdList;
		}
		public String getAuditId() {
			return auditId;
		}
		public void setAuditId(String auditId) {
			this.auditId = auditId;
		}

}
