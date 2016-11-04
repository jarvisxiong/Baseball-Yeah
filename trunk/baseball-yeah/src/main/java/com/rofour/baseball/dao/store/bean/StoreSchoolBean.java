package com.rofour.baseball.dao.store.bean;

import java.io.Serializable;

/**
 * 商户_学校实体类
 * 
 * @author WJ
 *
 */
public class StoreSchoolBean implements Serializable{
	/**
	 * @Fields serialVersionUID 
	 */
	    
	private static final long serialVersionUID = 3238632170061828366L;
	/**
	 * id
	 */
	private Long scrId;
	/**
	 * store_id
	 */
	private Long storeId;
	/**
	 * school_id
	 */
	private Long collegeId;
	
	public StoreSchoolBean() {
	}
	public Long getScrId() {
		return scrId;
	}

	public void setScrId(Long scrId) {
		this.scrId = scrId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public StoreSchoolBean(Long scrId, Long storeId, Long collegeId) {
		super();
		this.scrId = scrId;
		this.storeId = storeId;
		this.collegeId = collegeId;
	}

	@Override
	public String toString() {
		return "StoreSchoolBean [scrId=" + scrId + ", storeId=" + storeId + ", collegeId=" + collegeId + "]";
	}

}
