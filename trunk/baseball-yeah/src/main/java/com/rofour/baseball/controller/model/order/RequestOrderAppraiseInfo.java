package com.rofour.baseball.controller.model.order;

import java.io.Serializable;
import java.util.Date;

import com.rofour.baseball.controller.model.BasePage;

public class RequestOrderAppraiseInfo extends BasePage implements Serializable {

	/**
	 * 序列号
	 */
	    
	/**
	 * 序列号
	 */
	    
	private static final long serialVersionUID = 6455488613988170973L;

	/**
	 * 订单号  
	 */
	private Long orderId;
	
	/**
	 * 评价账号
	 */
	private String appraiserMobile;
	
	/**
	 * 小派手机号码
	 */
	private String phone;
	
	/**
	 * 开始日期
	 */
	private String createStartDate; 
	
	private String createEndDate; 
	
	
	/**
	 * 综合评分
	 */
	private String overallScore;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getAppraiserMobile() {
		return appraiserMobile;
	}

	public void setAppraiserMobile(String appraiserMobile) {
		this.appraiserMobile = appraiserMobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
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

	public String getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(String overallScore) {
		this.overallScore = overallScore;
	}
	
}
