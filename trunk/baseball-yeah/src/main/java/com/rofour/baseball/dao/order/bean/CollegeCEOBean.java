package com.rofour.baseball.dao.order.bean;

import java.io.Serializable;

public class CollegeCEOBean implements Serializable {

	/**
	 * 序列号
	 */
	    
	private static final long serialVersionUID = 7191128670836403691L;
	
	private String collegeId;
	
	private String realName;
	
	private String userId;

	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
