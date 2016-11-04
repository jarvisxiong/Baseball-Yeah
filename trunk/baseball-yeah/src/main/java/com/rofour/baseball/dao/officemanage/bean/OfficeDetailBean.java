/**  
* @Title: OfficeDetailBean.java
* @package com.rofour.baseball.dao.officemanage.bean
* @Project: baseball-yeah
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.dao.officemanage.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: OfficeDetailBean
 * @Description 职务管理bean
 * @author WJ
 * @date 2016年10月12日 下午1:56:44
 *
 */

public class OfficeDetailBean implements Serializable {

	private static final long serialVersionUID = 3322492263819531038L;
	private String username;

	private String realName;
	/**
	 * p_gender_male ,p_gender_female
	 */
	private String sex;
	/**
	 * 0-待审核 1-审核通过 2-审核拒绝
	 */
	private Byte auditState;

	private String city;

	private String collegeArea;

	private String dormitoryAddress;
	
	private Byte beEnabled;
	
	private String idNo;
	
	private Integer balance;
	
	private Date auditTime;
	
	private int orderNum;
	
	private int commentPercent;
	
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getCommentPercent() {
		return commentPercent;
	}

	public void setCommentPercent(int commentPercent) {
		this.commentPercent = commentPercent;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Byte getAuditState() {
		return auditState;
	}

	public void setAuditState(Byte auditState) {
		this.auditState = auditState;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCollegeArea() {
		return collegeArea;
	}

	public void setCollegeArea(String collegeArea) {
		this.collegeArea = collegeArea;
	}

	public String getDormitoryAddress() {
		return dormitoryAddress;
	}

	public void setDormitoryAddress(String dormitoryAddress) {
		this.dormitoryAddress = dormitoryAddress;
	}

	public Byte getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Byte beEnabled) {
		this.beEnabled = beEnabled;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	
	
}
