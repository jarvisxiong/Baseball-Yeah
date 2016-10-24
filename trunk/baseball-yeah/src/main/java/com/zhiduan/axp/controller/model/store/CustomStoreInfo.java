package com.zhiduan.axp.controller.model.store;

import java.io.Serializable;
import java.util.List;

import com.zhiduan.axp.dao.manager.bean.ExpressBean;
import com.zhiduan.axp.dao.store.bean.AxpBean;
/**
 * 根据storeId查询站点,返回的dto
 * @author WJ
 *
 */
public class CustomStoreInfo implements Serializable{
	/**
	 * @Fields serialVersionUID 
	 */
	    
	private static final long serialVersionUID = -1489273199135348105L;

	private String storeCode;
	private String storeName;
	private Integer type;
	private Integer status;
	private String location;
	private String supervisorName;
	private String phone;

	/**
	 * 是否已删除 0未删除 1已删除
	 */
	private Byte beDeleted;

	public Byte getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Byte beDeleted) {
		this.beDeleted = beDeleted;
	}

	/**
	 * 关联的快递信息
	 */
	private List<ExpressBean> ecs;
	/**
	 * 关联的axp门店信息
	 */
	private List<AxpBean> axps;

	public CustomStoreInfo() {
	}

	public CustomStoreInfo(String storeCode, String storeName, Integer type, Integer status, String location,
			String supervisorName, String phone, List<ExpressBean> ecs, List<AxpBean> axps) {
		super();
		this.storeCode = storeCode;
		this.storeName = storeName;
		this.type = type;
		this.status = status;
		this.location = location;
		this.supervisorName = supervisorName;
		this.phone = phone;
		this.ecs = ecs;
		this.axps = axps;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<ExpressBean> getEcs() {
		return ecs;
	}

	public void setEcs(List<ExpressBean> ecs) {
		this.ecs = ecs;
	}

	public List<AxpBean> getAxps() {
		return axps;
	}

	public void setAxps(List<AxpBean> axps) {
		this.axps = axps;
	}

	@Override
	public String toString() {
		return "CustomStore [storeCode=" + storeCode + ", storeName=" + storeName + ", type=" + type + ", status="
				+ status + ", location=" + location + ", supervisorName=" + supervisorName + ", phone=" + phone
				+ ", ecs=" + ecs + ", axps=" + axps + "]";
	}

}
