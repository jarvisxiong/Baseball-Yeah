package com.zhiduan.axp.dao.store.bean;

import java.io.Serializable;

import com.zhiduan.axp.dao.store.mapper.StoreMapper;

/**
 * 商户实体类
 * 
 * @author WJ
 *
 */
public class StoreBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4820061817021349046L;
	/**
	 * store_id
	 */
	private Long storeId;
	/**
	 * store_code
	 */
	private String storeCode;
	/**
	 * store_name
	 */
	private String storeName;
	/**
	 * type 1:快递站点(默认)
	 */
	private Integer type = StoreMapper.STOREMAPPER_TYPE_EXP_SITE;
	/**
	 * status 0:启用
	 */
	private Integer status = StoreMapper.STOREMAPPER_STATUS_ENABLED;
	/**
	 * location
	 */
	private String location;
	/**
	 * 是否删除,0:存在   1:删除
	 */
	private Integer beDeleted;
	public Integer getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Integer beDeleted) {
		this.beDeleted = beDeleted;
	}

	public StoreBean() {
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

	public StoreBean(Long storeId, String storeCode, String store_name, Integer type, Integer status,
			String location, Integer deleted) {
		super();
		this.storeId = storeId;
		this.storeCode = storeCode;
		this.storeName = store_name;
		this.type = type;
		this.status = status;
		this.location = location;
		this.beDeleted = deleted;
	}

	@Override
	public String toString() {
		return "StoreBean [storeId=" + storeId + ", storeCode=" + storeCode + ", store_name=" + storeName + ", type="
				+ type + ", status=" + status + ", location=" + location + ", deleted=" + beDeleted + "]";
	}

}
