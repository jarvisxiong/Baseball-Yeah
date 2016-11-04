/**  
* @Title: OfficeQueryInfo.java
* @package com.rofour.baseball.controller.model.office
* @Project: baseball-yeah
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.controller.model.office;

import java.io.Serializable;

import com.rofour.baseball.controller.model.BasePage;

/**
 * @ClassName: OfficeAcctQueryInfo
 * @Description 账户查询请求bean
 * @author gechao
 * @date 2016年10月13日 上午8:54:24
 *
 */

public class OfficeAcctQueryInfo extends BasePage implements Serializable {

	private static final long serialVersionUID = 4971352753163560298L;
	// 用户ID
	private String userId;
	// 真实姓名
	private String realName;
	/**
	 * 性别
	 * p_gender_male 男,p_gender_female 女 ,p_gender_secret 保密
	 */
	private String gender;
	// 宿舍
	private String dormitoryAddress;
	// 登录手机号/账号
	private String phone;
	// 公共时间
	private String pulicTime;
	// 城市名
	private String cityName;
	// 学校名
	private String collegeName;
	// 账户状态（0:冻结,1:正常）
	private String state;
	// 余额
	private String balance;
	// 好评率
	private String favorableRate;
	// 订单量
	private int packetNum;
	// 学校ID
	private String collegeId;
	// 标识 0：COO，1：CEO
	private int flag;
	
	/**
	 * 注册时间
	 */
//	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	private String startDate;
	/**
	 * 注册时间
	 */
//	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	private String endDate; 

	  

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDormitoryAddress() {
		return dormitoryAddress;
	}

	public void setDormitoryAddress(String dormitoryAddress) {
		this.dormitoryAddress = dormitoryAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPulicTime() {
		return pulicTime;
	}

	public void setPulicTime(String pulicTime) {
		this.pulicTime = pulicTime;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getFavorableRate() {
		return favorableRate;
	}

	public void setFavorableRate(String favorableRate) {
		this.favorableRate = favorableRate;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getPacketNum() {
		return packetNum;
	}

	public void setPacketNum(int packetNum) {
		this.packetNum = packetNum;
	}

	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
