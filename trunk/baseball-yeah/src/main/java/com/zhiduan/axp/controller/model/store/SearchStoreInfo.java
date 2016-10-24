package com.zhiduan.axp.controller.model.store;

import java.io.Serializable;
import java.util.List;

import com.zhiduan.axp.dao.store.bean.SearchCollegeBean;
import com.zhiduan.axp.dao.store.bean.SearchSiteBean;

/**
 * 手机查询商户DTO
 * 
 * @author xl
 *
 */
public class SearchStoreInfo implements Serializable {

	private static final long serialVersionUID = 1356638920855358197L;

	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 商户编码
	 */
	private String storeId;
	/**
	 * 商户编号
	 */
	private String storeCode;
	/**
	 * 商户名称
	 */
	private String storeName;
	/**
	 * 快递id
	 */
	private Long expressCompanyId;
	/**
	 * 快递公司名称
	 */
	private String expressName;
	/**
	 * 地址
	 */
	private String location;
	/**
	 * 门店列表
	 */
	private List<SearchSiteBean> siteList;

	/**
	 * 学校列表
	 */
	private List<SearchCollegeBean> collegeList;

	public SearchStoreInfo() {

	}

	public Long getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(Long expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
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

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public List<SearchSiteBean> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<SearchSiteBean> siteList) {
		this.siteList = siteList;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<SearchCollegeBean> getCollegeList() {
		return collegeList;
	}

	public void setCollegeList(List<SearchCollegeBean> collegeList) {
		this.collegeList = collegeList;
	}
}
