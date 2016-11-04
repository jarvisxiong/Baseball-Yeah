/**  
* @Title: OfficeQueryInfo.java
* @package com.rofour.baseball.controller.model.office
* @Project: baseball-yeah
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.controller.model.office;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rofour.baseball.common.CustomJsonDateDeserializer;
import com.rofour.baseball.controller.model.BasePage;

/**
 * @ClassName: OfficeQueryInfo
 * @Description 查询请求bean
 * @author WJ
 * @date 2016年10月12日 下午1:54:24
 *
 */

public class OfficeQueryInfo extends BasePage implements Serializable {

	private static final long serialVersionUID = 4971352753163560298L;
	
	private Long userId;
	
	private Long cityId;
	
	private String username;
	private String realName;
	/**
	 * p_gender_male ,p_gender_female
	 */
	private String sex;
	/**
	 *  校区id
	 */
	private Long collegeArea;
	/**
	 * 0-待审核 1-审核通过 2-审核拒绝
	 */
	private Byte auditState;
	/**
	 * 1激活,0未激活
	 */
	private Byte beEnabled;
	/**
	 * 职务,2:校园COO,3: 校园CEO
	 */
	private Integer office;
	/**
	 * 审核人
	 */
	private String auditor;
	
	/**
	 * 注册时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	private Date startDate;
	/**
	 * 注册时间
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	private Date endDate;
	
	public Byte getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Byte beEnabled) {
		this.beEnabled = beEnabled;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Long getCollegeArea() {
		return collegeArea;
	}

	public void setCollegeArea(Long collegeArea) {
		this.collegeArea = collegeArea;
	}

	public Byte getAuditState() {
		return auditState;
	}

	public void setAuditState(Byte auditState) {
		this.auditState = auditState;
	}

	public Integer getOffice() {
		return office;
	}

	public void setOffice(Integer office) {
		this.office = office;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
