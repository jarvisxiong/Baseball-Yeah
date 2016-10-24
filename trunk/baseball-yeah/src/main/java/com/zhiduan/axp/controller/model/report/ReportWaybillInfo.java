package com.zhiduan.axp.controller.model.report;

import java.io.Serializable;

/**
 * 
 * @author 张伟
 * @date 2016年6月8日 下午3:02:54
 * @version 1.0
 *
 */
public class ReportWaybillInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 779942628472411037L;

	private String storeId;
	
	private String waybillNo;
	
	private String arriveStartDate;
	
	private String arriveEndDate;
	
	private String arriveDate;
	
	private String signStartDate;
	
	private String signEndDate;
	
	private String signDate;
	
	private Integer offset;
	
	private Integer limit;
	
	private String sort;
	
	private String order;
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getArriveStartDate() {
		return arriveStartDate;
	}

	public void setArriveStartDate(String arriveStartDate) {
		this.arriveStartDate = arriveStartDate;
	}

	public String getArriveEndDate() {
		return arriveEndDate;
	}

	public void setArriveEndDate(String arriveEndDate) {
		this.arriveEndDate = arriveEndDate;
	}

	public String getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}

	public String getSignStartDate() {
		return signStartDate;
	}

	public void setSignStartDate(String signStartDate) {
		this.signStartDate = signStartDate;
	}

	public String getSignEndDate() {
		return signEndDate;
	}

	public void setSignEndDate(String signEndDate) {
		this.signEndDate = signEndDate;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "ReportWaybillInfo [storeId=" + storeId + ", waybillNo="
				+ waybillNo + ", arriveStartDate=" + arriveStartDate
				+ ", arriveEndDate=" + arriveEndDate + ", arriveDate="
				+ arriveDate + ", signStartDate=" + signStartDate
				+ ", signEndDate=" + signEndDate + ", signDate=" + signDate
				+ ", offset=" + offset + ", limit=" + limit + "]";
	}
	
	
}
