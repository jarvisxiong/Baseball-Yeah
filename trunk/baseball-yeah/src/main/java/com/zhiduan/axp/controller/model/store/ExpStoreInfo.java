/**  
* @Title: ExpStoreInfo.java
* @Package com.zhiduan.axp.idl.storecenter.service
* @Project: axp-idl
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年4月8日 下午3:48:24 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.controller.model.store;

import java.io.Serializable;
import java.util.List;

/**
* @ClassName: ExpStoreInfo
* @Description: TODO(逻辑层快递站点实体)
* @author heyi
* @date 2016年4月8日 下午3:48:24 
* 
*/

public class ExpStoreInfo implements Serializable{
	
	private static final long serialVersionUID = -3248954995789718518L;
	/**
	 * 快递站点编号
	 */
	private Long storeId;
	/**
	 * 快递站点编码
	 */
	private String storeCode;
	/**
	 * 快递站点名称
	 */
	private String storeName;
	/**
	 * 联系地址
	 */
	private String location;
	/**
	 * 状态 0 禁用 1启用
	 */
	private Integer status;
	/**
	 * 类型 1 快递站点 2 门店
	 */
	private Byte type;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 负责人名称
	 */
	private String supervisorName;
	/**
	 * 物流商名称（多个逗号隔开）
	 */
	private String ecName;
	/**
	 * 物流商编码（多个逗号隔开）
	 */
	private String ecGcode;
	/**
	 * 学校编号，多个逗号隔开
	 */
	private String colCode;
	/**
	 * 学校名称，多个逗号隔开
	 */
	private String colName;
	/**
	 * 是否删除,0:存在   1:删除
	 */
	private Integer beDeleted;
	/**
	 * 快递公司编码集合
	 */
	private List<Long> ecList;
	/**
	 * 学校编码集合
	 */
	private List<Long> colList;
	
	 /**
     * 默认物流商编码
     */
    private Long defaultECId;
    /**
     * 默认物流商名称
     */
    private String defaultECName;
    /**
     * 派件模式  0 关闭  1 开启
     */
    private Byte packetModeMgr;
	/**
	 * 寄件模式  0 关闭  1 开启
	 */
	private Byte packetModeSend;
	
	/**
	 * 打烊模式  0 关闭  1 开启  
	 * wuhongxue 2016.9.18增加打烊状态字段
	 */
	private Byte closeMode;

	public Byte getPacketModeSend() {
		return packetModeSend;
	}

	public void setPacketModeSend(Byte packetModeSend) {
		this.packetModeSend = packetModeSend;
	}

	public ExpStoreInfo() {
		
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
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
	public String getEcName() {
		return ecName;
	}
	public void setEcName(String ecName) {
		this.ecName = ecName;
	}
	public String getEcGcode() {
		return ecGcode;
	}
	public void setEcGcode(String ecGcode) {
		this.ecGcode = ecGcode;
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

	public List<Long> getEcList() {
		return ecList;
	}

	public void setEcList(List<Long> ecList) {
		this.ecList = ecList;
	}

	public Integer getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Integer beDeleted) {
		this.beDeleted = beDeleted;
	}

	public List<Long> getColList() {
		return colList;
	}

	public void setColList(List<Long> colList) {
		this.colList = colList;
	}

	public String getColCode() {
		return colCode;
	}

	public void setColCode(String colCode) {
		this.colCode = colCode;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public Long getDefaultECId() {
		return defaultECId;
	}

	public void setDefaultECId(Long defaultECId) {
		this.defaultECId = defaultECId;
	}

	public String getDefaultECName() {
		return defaultECName;
	}

	public void setDefaultECName(String defaultECName) {
		this.defaultECName = defaultECName;
	}

	public Byte getPacketModeMgr() {
		return packetModeMgr;
	}

	public void setPacketModeMgr(Byte packetModeMgr) {
		this.packetModeMgr = packetModeMgr;
	}

	public Byte getCloseMode() {
		return closeMode;
	}

	public void setCloseMode(Byte closeMode) {
		this.closeMode = closeMode;
	}

	
}
