/**  
* @Title: OfficeBean.java
* @package com.rofour.baseball.dao.officemanage.bean
* @Project: baseball-yeah
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.dao.officemanage.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: OfficeBean
 * @Description 职务管理bean
 * @author WJ
 * @date 2016年10月12日 下午1:56:44
 *
 */

public class OfficeBean implements Serializable {

	private static final long serialVersionUID = -1376639903772814374L;
	
	private Long userId;
	private String username;
	/**
	 * 注册日期
	 */
	private Date createDate;

	private String realName;
	/**
	 * p_gender_male ,p_gender_female
	 */
	private String sex;
	/**
	 * 职务,1:普通小派 ,2:校园COO,3: 校园CEO
	 */
	private Integer office;
	/**
	 * 0-待审核 1-审核通过 2-审核拒绝
	 */
	private Byte auditState;

	private String auditor;

	private String city;

	private String collegeId;

	private String collegeArea;

	private String dormitoryAddress;
	/**
	 * 小派人数
	 */
	private Integer packetUser;
	/**
	 * 商户人数
	 */
	private Integer store;
	/**
	 * 账户状态1激活,0未激活
	 */
	
	private Byte beEnabled;
	
	public Byte getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Byte beEnabled) {
		this.beEnabled = beEnabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Integer getOffice() {
		return office;
	}

	public void setOffice(Integer office) {
		this.office = office;
	}

	public Byte getAuditState() {
		return auditState;
	}

	public void setAuditState(Byte auditState) {
		this.auditState = auditState;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
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

	public Integer getPacketUser() {
		return packetUser;
	}

	public void setPacketUser(Integer packetUser) {
		this.packetUser = packetUser;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}
}
