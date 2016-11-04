package com.rofour.baseball.controller.model.user;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.Date;

public class UserCheckInfo extends BasePage implements Serializable{

	private static final long serialVersionUID = 6611851814725639581L;
    /**
	 * @Fields age :昵称
	 */
    private String nickName;
    /**
  	 * @Fields age :年龄
  	 */
    private Byte age;
    /**
  	 * @Fields age :用户名
  	 */
    private String userName;
	/**
	 * @Fields age :搜索条件开始时间
	 */
	private String startDate;

	private  Long cityId;

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	private  String  grabOrderMode;

	public String getGrabOrderMode() {
		return grabOrderMode;
	}

	public void setGrabOrderMode(String grabOrderMode) {
		this.grabOrderMode = grabOrderMode;
	}

	/**
	 * @Fields age :搜索条件结束时间
	 */
	private  String endDate;

	private String idNo;
	
	/**
	 * 审核开始时间
	 */
	private String auditStartDate;
	
	/**
	 * 审核结束时间
	 */
	private String auditEndDate;
	
	public void setAuditEndDate(String auditEndDate) {
		this.auditEndDate = auditEndDate;
	}
	public String getAuditEndDate() {
		return auditEndDate;
	}
	
	public void setAuditStartDate(String auditStartDate) {
		this.auditStartDate = auditStartDate;
	}
	public String getAuditStartDate() {
		return auditStartDate;
	}
	
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	public String getIdNo() {
		return idNo;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Byte getAge() {
		return age;
	}
	public void setAge(Byte age) {
		this.age = age;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @Fields userId :用户ID
	 */
	private Long userId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @Fields storeName :商户名称
	 */
	private String storeName;
	/**
	 * @Fields storeId :商户ID
	 */
	    
	private String storeCode;
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	/**
	 * @Fields age :查询时间类型
	 */
	private Byte timeType;
	/**
	 * @Fields age :大学名称
	 */
	private String collegeFullName;
	/**
	 * @Fields age :开始时间
	 */
	private Date startTime;
	/**
	 * @Fields age :结束时间
	 */
	private Date endTime;
	/**
	 * @Fields age :真实姓名
	 */
    private String realName;
	/**
	 * @Fields age :注册时间
	 */
    private Date signupTime;
	/**
	 * @Fields age :审核时间
	 */
    private Date verifyTime;
	/**
	 * @Fields age :审核状态
	 */
    private Byte verifyStatus;
	/**
	 * @Fields age :是否负责人
	 */
    private Byte beSupervisor;
	/**
	 * @Fields age :电话
	 */
    private String phone;
	/**
	 * @Fields age :负责人电话
	 */
    private String supervisorPhone;
	/**
	 * @Fields age :查询学校名称
	 */
    private Long collegeId;

	private String collegeAddress;

	private String dormitoryAddress;

	/**
	 * @Fileds cityName 城市名称
	 */
	private String cityName;
	
	  /**
     * @Fields gender 性别
     */
    private String gender;

    /**
     * @Fields 备注
     */
    private String remark;
    
    public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDormitoryAddress() {
		return dormitoryAddress;
	}

	public void setDormitoryAddress(String dormitoryAddress) {
		this.dormitoryAddress = dormitoryAddress;
	}

	public String getCollegeAddress() {
		return collegeAddress;
	}

	public void setCollegeAddress(String collegeAddress) {
		this.collegeAddress = collegeAddress;
	}

	public Byte getTimeType() {
		return timeType;
	}
	public void setTimeType(Byte timeType) {
		this.timeType = timeType;
	}
	public String getCollegeFullName() {
		return collegeFullName;
	}
	public void setCollegeFullName(String collegeFullName) {
		this.collegeFullName = collegeFullName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Date getSignupTime() {
		return signupTime;
	}
	public void setSignupTime(Date signupTime) {
		this.signupTime = signupTime;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	public Byte getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(Byte verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public Byte getBeSupervisor() {
		return beSupervisor;
	}
	public void setBeSupervisor(Byte beSupervisor) {
		this.beSupervisor = beSupervisor;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSupervisorPhone() {
		return supervisorPhone;
	}
	public void setSupervisorPhone(String supervisorPhone) {
		this.supervisorPhone = supervisorPhone;
	}
	public Long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(Long collegeId) {
		this.collegeId =collegeId;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
	public String toString() {
		return "UserCheckInfo [nickName=" + nickName + ", age=" + age
				+ ", userName=" + userName + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", idNo=" + idNo
				+ ", auditStartDate=" + auditStartDate + ", auditEndDate="
				+ auditEndDate + ", userId=" + userId + ", storeName="
				+ storeName + ", storeCode=" + storeCode + ", timeType="
				+ timeType + ", collegeFullName=" + collegeFullName
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", realName=" + realName + ", signupTime=" + signupTime
				+ ", verifyTime=" + verifyTime + ", verifyStatus="
				+ verifyStatus + ", beSupervisor=" + beSupervisor + ", phone="
				+ phone + ", supervisorPhone=" + supervisorPhone
				+ ", collegeId=" + collegeId + ", collegeAddress="
				+ collegeAddress + ", dormitoryAddress=" + dormitoryAddress
				+ ", cityName=" + cityName + ", gender=" + gender + "]";
	}
	
	
    
}
