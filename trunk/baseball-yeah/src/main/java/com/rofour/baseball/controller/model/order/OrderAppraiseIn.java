package com.rofour.baseball.controller.model.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rofour.baseball.controller.model.BasePage;

public class OrderAppraiseIn extends BasePage implements Serializable {

	/**
	 * 序列号
	 */
	    
	private static final long serialVersionUID = -1877451039463574320L;
	
	private int appraiseId;//评价ID 
	
	/**
	 * 订单号  
	 */
	private Long orderId;
	/**
	 * 订单类型
	 */
	private String orderType;
	/**
	 * 评价账号
	 */
	private String appraiserMobile;
	
	/**
	 * 小派手机号码
	 */
	private String phone;
	
	/**
	 * 创建日期
	 */
	private Date createDate; 
	
	/**
	 * 综合评分
	 */
	private int overallScore;
	
	private int appraiserOther;//被评价人id 
	
	private String overallDesc;//综合描述
	
	private List<String> url=new ArrayList<String>();

	public int getAppraiseId() {
		return appraiseId;
	}

	public void setAppraiseId(int appraiseId) {
		this.appraiseId = appraiseId;
	}

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(int overallScore) {
		this.overallScore = overallScore;
	}

	public String getOverallDesc() {
		return overallDesc;
	}

	public void setOverallDesc(String overallDesc) {
		this.overallDesc = overallDesc;
	}

	public List<String> getUrl() {
		return url;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}

	public int getAppraiserOther() {
		return appraiserOther;
	}

	public void setAppraiserOther(int appraiserOther) {
		this.appraiserOther = appraiserOther;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
