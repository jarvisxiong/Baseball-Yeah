/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.model.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.rofour.baseball.controller.model.BasePage;

/**
 * @ClassName: RecieveOrderRateInfo
 * @Description: 接单率统计
 * @author lpp
 * @date 2016年10月12日 下午4:46:54 
 */
public class RecieveOrderRateInfo extends BasePage implements Serializable {

	private static final long serialVersionUID = 4097281534565016966L;
	
	/**查询开始时间*/
	private String startDate;
	
	/**查询结束时间*/
	private String endDate;
	
	/**校区列表*/
	private String collegeIds;
	
	/**日期*/
	private String day;
	
	/**校区名称*/
	private String collegeName;
	
	/**有效订单数*/
	private Integer totalOrderNum;
	
	/**未接单数*/
	private Integer orderStatusWaittaking;
	
	/**接单率*/
	private String recOrderRate;
	
	/**环比增长*/
	private String recOrderQoq;
	
	/**前一天有效订单数*/
	private Integer totalOrderNumBefore;
	
	/**前一天未接单数*/
	private Integer orderStatusWaittakingBefore;
	/**
	 * 当天订单状态:订单已被小派认领
	 */
	private int  orderStatusPuser;
	
	/**学校列表集合*/
	public List<Long> getCollegeIdList(){
		List<Long> collegeIdList = null;
		if(!StringUtils.isBlank(collegeIds)){
			collegeIdList = new ArrayList<>();
			String[] collegeIdArr = collegeIds.split(",");
			for (String collegeId : collegeIdArr) {
				collegeIdList.add(Long.parseLong(collegeId));
			}
		}
		return collegeIdList;
	}
	
	
	public int getOrderStatusPuser() {
		return orderStatusPuser;
	}

	public void setOrderStatusPuser(int orderStatusPuser) {
		this.orderStatusPuser = orderStatusPuser;
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

	public String getCollegeIds() {
		return collegeIds;
	}

	public void setCollegeIds(String collegeIds) {
		this.collegeIds = collegeIds;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public Integer getTotalOrderNum() {
		return totalOrderNum;
	}

	public void setTotalOrderNum(Integer totalOrderNum) {
		this.totalOrderNum = totalOrderNum;
	}

	public Integer getOrderStatusWaittaking() {
		return orderStatusWaittaking;
	}

	public void setOrderStatusWaittaking(Integer orderStatusWaittaking) {
		this.orderStatusWaittaking = orderStatusWaittaking;
	}

	public String getRecOrderRate() {
		return recOrderRate;
	}

	public void setRecOrderRate(String recOrderRate) {
		this.recOrderRate = recOrderRate;
	}

	public String getRecOrderQoq() {
		return recOrderQoq;
	}

	public void setRecOrderQoq(String recOrderQoq) {
		this.recOrderQoq = recOrderQoq;
	}

	public Integer getTotalOrderNumBefore() {
		return totalOrderNumBefore;
	}

	public void setTotalOrderNumBefore(Integer totalOrderNumBefore) {
		this.totalOrderNumBefore = totalOrderNumBefore;
	}

	public Integer getOrderStatusWaittakingBefore() {
		return orderStatusWaittakingBefore;
	}

	public void setOrderStatusWaittakingBefore(Integer orderStatusWaittakingBefore) {
		this.orderStatusWaittakingBefore = orderStatusWaittakingBefore;
	}

}
