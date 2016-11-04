package com.rofour.baseball.controller.model.report;

import java.io.Serializable;

/**
 * 
 * @author 张伟
 * @date 2016年6月7日 下午4:54:15
 * @version 1.0
 *
 */
public class ReportOfflinePartyInfo implements Serializable {

 
	/**
	 * 城市名称 
	 */
	private String cityName;
	
	/**
	 * 门店ID
	 */
	private String storeId;
	/**
	 * 门店名称
	 */
	private String storeName;
	
	/**
	 * 日期
	 */
	private String date;
	
	/**
	 * 起始日期
	 */
	private String startDate;
	
	/**
	 * 截止日期
	 */
	private String endDate;
	
	/**
	 * 品牌名称
	 */
	private String expressName;

	/**
	 * 到件数
	 */
	private Long arrivTotal;
	
	/**
	 * 取件数
	 */
	private Long collectTotal;
	
	private Integer offset;
	
	private Integer limit;
	
	/**
	 * 排序方式
	 */
	private String order;
	
	/**
	 * 排序名称
	 */
	private String sort;
	
	
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public Long getArrivTotal() {
		return arrivTotal;
	}

	public void setArrivTotal(Long arrivTotal) {
		this.arrivTotal = arrivTotal;
	}

	public Long getCollectTotal() {
		return collectTotal;
	}

	public void setCollectTotal(Long collectTotal) {
		this.collectTotal = collectTotal;
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
	
}
