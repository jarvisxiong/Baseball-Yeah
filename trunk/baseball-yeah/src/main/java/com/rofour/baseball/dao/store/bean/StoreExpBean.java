package com.rofour.baseball.dao.store.bean;

import java.io.Serializable;

import com.rofour.baseball.dao.store.mapper.StoreMapper;

/**
 * 商户_快递公司实体类
 * 
 * @author WJ
 *
 */
public class StoreExpBean implements Serializable{
	/**
	 * @Fields serialVersionUID
	 */
	    
	private static final long serialVersionUID = -7453349858512353232L;
	/**
	 * id
	 */
	private Long serId;
	/**
	 * store_id
	 */
	private Long storeId;
	/**
	 * express_id
	 */
	private Long expressCompanyId;
	/**
	 * default 0
	 */
	private Integer beDefault = StoreMapper.STOREMAPPER_DEFAULT_EXP;

	public StoreExpBean() {
	}

	public Long getSerId() {
		return serId;
	}

	public void setSerId(Long serId) {
		this.serId = serId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(Long expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

	public Integer getBeDefault() {
		return beDefault;
	}

	public void setBeDefault(Integer beDefault) {
		this.beDefault = beDefault;
	}

	public StoreExpBean(Long serId, Long storeId, Long expressCompanyId, Integer beDefault) {
		super();
		this.serId = serId;
		this.storeId = storeId;
		this.expressCompanyId = expressCompanyId;
		this.beDefault = beDefault;
	}

	@Override
	public String toString() {
		return "StoreExpBean [serId=" + serId + ", storeId=" + storeId + ", expressCompanyId=" + expressCompanyId
				+ ", beDefault=" + beDefault + "]";
	}

}
