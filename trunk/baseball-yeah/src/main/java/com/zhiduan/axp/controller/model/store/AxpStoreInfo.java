/**  
* @Title: AxpStoreInfo.java
* @Package com.zhiduan.axp.idl.storecenter.service
* @Project: axp-idl
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年4月7日 下午9:26:36 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.controller.model.store;

import java.io.Serializable;

/**
* @ClassName: AxpStoreInfo
* @Description: TODO(爱学派门店信息逻辑层实体)
* @author heyi
* @date 2016年4月7日 下午9:26:36 
*
*/

public class AxpStoreInfo implements Serializable {

	    
	private static final long serialVersionUID = 6242228468012470617L;
	/**
	 * 门店编号
	 */
	private Long storeId;
	/**
	 * 门店编码
	 */
	private String storeCode;
	/**
	 * 门店名称
	 */
	private String storeName;
	/**
	 * 联系地址
	 */
	private String location;
	/**
	 * 门店状态 0 禁用 1启用
	 */
	private Byte status;
	/**
	 * 类型 1快递站点 2门店
	 */
	private Byte type;
	/**
	 * 快递站点名称（多个逗号隔开）
	 */
	private String esName;
	/**
	 * 快递站点编号（多个逗号隔开）
	 */
	private String esGcode;
	/**
	 * 物流商编号（多个逗号隔开）
	 */
	private String ecGcode;
	/**
	 * 物流商名称（多个逗号隔开）
	 */
	private String ecName;
	/**
	 * 证书号
	 */
	private String licence;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 负责人名称
	 */
	private String supervisorName;
	/**
	 * 学校编码（多个逗号隔开）
	 */
	private String collegeId;
	/**
	 * 学校名称（多个逗号隔开）
	 */
	private String collegeName;
	public AxpStoreInfo() {
		
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public String getEsName() {
		return esName;
	}
	public void setEsName(String esName) {
		this.esName = esName;
	}
	public String getEsGcode() {
		return esGcode;
	}
	public void setEsGcode(String esGcode) {
		this.esGcode = esGcode;
	}
	public String getEcGcode() {
		return ecGcode;
	}
	public void setEcGcode(String ecGcode) {
		this.ecGcode = ecGcode;
	}
	public String getLicence() {
		return licence;
	}
	public void setLicence(String licence) {
		this.licence = licence;
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
	public String getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getEcName() {
		return ecName;
	}
	public void setEcName(String ecName) {
		this.ecName = ecName;
	}
}
