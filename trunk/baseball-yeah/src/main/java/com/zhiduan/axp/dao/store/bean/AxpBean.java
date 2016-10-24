package com.zhiduan.axp.dao.store.bean;

import java.io.Serializable;

/**
 * 爱学派门店dto类
 * 
 * @author WJ
 *
 */
public class AxpBean implements Serializable{
	/**
	 * @Fields serialVersionUID 
	 */
	    
	private static final long serialVersionUID = 6175543863110225702L;
	/**
	 * 就是商户编码Store_id
	 */
	private Long stoAxpId;
	/**
	 * 商户名字store_name
	 */
	private String stoAxpName;
	/**
	 * 学校id
	 */
	private Long collegeId;
	/**
	 * 学校名字
	 */
	private String collegeName;

	public AxpBean() {
	}

	public AxpBean(Long stoAxpId, String stoAxpName,Long collegeId,String collegeName) {
		super();
		this.stoAxpId = stoAxpId;
		this.stoAxpName = stoAxpName;
		this.collegeId = collegeId;
		this.collegeName = collegeName;
	}

	public Long getStoAxpId() {
		return stoAxpId;
	}

	public void setStoAxpId(Long stoAxpId) {
		this.stoAxpId = stoAxpId;
	}

	public String getStoAxpName() {
		return stoAxpName;
	}

	public void setStoAxpName(String stoAxpName) {
		this.stoAxpName = stoAxpName;
	}
	
	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@Override
	public String toString() {
		return "Axp [stoAxpId=" + stoAxpId + ", stoAxpName=" + stoAxpName + "]";
	}
}
