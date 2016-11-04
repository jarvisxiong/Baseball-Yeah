package com.rofour.baseball.controller.model.store;

import java.io.Serializable;
import java.util.List;

/**
 * 商户DTO类,用作接口返回实体
 * 
 * @author WJ
 *
 */
public class StoreInfo implements Serializable{
	/**
	 * @Fields serialVersionUID 
	 */
	    
	private static final long serialVersionUID = 84807292643905542L;

	/**
	 * store_id
	 */
	private long storeId;
	
	/**
	 * 商户编号
	 */
	private String storeCode;
	/**
	 * 商户名称
	 */
	private String storeName;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 负责人姓名
	 */
	private String supervisorName;
	/**
	 * 快递公司编码集合
	 */
	private List<Long> ecList;
	/**
	 * 快递公司编码 增加时需要
	 */
	private long expressCompanyId;
	/**
	 * 门店列表
	 */
	private List<Long> axpList;
	/**
	 * 地址
	 */
	private String location;
	
	public StoreInfo() {
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public List<Long> getEcList() {
		return ecList;
	}

	public void setEcList(List<Long> ecList) {
		this.ecList = ecList;
	}

	public long getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(long expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

	public List<Long> getAxpList() {
		return axpList;
	}

	public void setAxpList(List<Long> axpList) {
		this.axpList = axpList;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "StoreInfo{" +
				"storeId=" + storeId +
				", storeCode='" + storeCode + '\'' +
				", storeName='" + storeName + '\'' +
				", phone='" + phone + '\'' +
				", supervisorName='" + supervisorName + '\'' +
				", ecList=" + ecList +
				", expressCompanyId=" + expressCompanyId +
				", axpList=" + axpList +
				", location='" + location + '\'' +
				'}';
	}
}
