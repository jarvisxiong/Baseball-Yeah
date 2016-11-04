package com.rofour.baseball.controller.model.store;

import java.io.Serializable;

public class SimpleStoreInfo implements Serializable{
	/**
	 * @Fields serialVersionUID 
	 */
	    
	private static final long serialVersionUID = -3783960628214090649L;
	/**
	 * 商户id
	 */
	private Long storeId;
	/**
	 * 商户编号
	 */
	private String storeCode;
	/**
	 * 商户名
	 */
	private String storeName;

	public SimpleStoreInfo() {
	}
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public SimpleStoreInfo(Long storeId,String storeCode, String storeName) {
		super();
		this.storeId = storeId;
		this.storeCode = storeCode;
		this.storeName = storeName;
	}

	@Override
	public String toString() {
		return "SimpleStore [storeCode=" + storeCode + ", storeName=" + storeName + "]";
	}

}
