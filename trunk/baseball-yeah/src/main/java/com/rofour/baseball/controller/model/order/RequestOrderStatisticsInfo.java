package com.rofour.baseball.controller.model.order;

import java.io.Serializable;
import java.util.List;

import com.rofour.baseball.controller.model.BasePage;

public class RequestOrderStatisticsInfo extends BasePage implements Serializable {
	
	
	/**
	 * 序列号
	 */
	    
	private static final long serialVersionUID = -2343850393592833634L;
	
	
	private String createStartDate;//开始日期
	
	private  String createEndDate;//结束日期
	
	private List<Long> collegeId;//校区ID
	
	private String colleges;//拼接的校区字符串
	
	private String createDate;//日期
	
	private String collegeName;//校区全称
	
	private String collegeCEO;//校园ceo
	
	private String  collegeList;
	
	

	public String getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(String createStartDate) {
		this.createStartDate = createStartDate;
	}

	public String getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(String createEndDate) {
		this.createEndDate = createEndDate;
	}


	public List<Long> getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(List<Long> collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCollegeCEO() {
		return collegeCEO;
	}

	public void setCollegeCEO(String collegeCEO) {
		this.collegeCEO = collegeCEO;
	}

	public String getColleges() {
		return colleges;
	}

	public void setColleges(String colleges) {
		this.colleges = colleges;
	}

	public String getCollegeList() {
		return collegeList;
	}

	public void setCollegeList(String collegeList) {
		this.collegeList = collegeList;
	}

	
}
