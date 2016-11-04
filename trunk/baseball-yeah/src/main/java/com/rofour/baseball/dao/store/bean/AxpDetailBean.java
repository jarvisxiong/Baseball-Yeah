/**  
* @Title: AxpDetailBean.java
* @package com.rofour.baseball.idl.storecenter.dao
* @Project: axp-idl
* @Description: axp门店的细节信息,对应tb_store_axp
* @author WJ
* @date 2016年4月11日 上午11:51:49 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.dao.store.bean;

/**
* @ClassName: AxpDetailBean
* @Description: axp门店的细节信息,对应tb_store_axp
* @author WJ
* @date 2016年4月11日 上午11:51:49 
*
*/

public class AxpDetailBean {
	/**
	 * 主键
	 */
	private Long stoAxpId;
	/**
	 * 负责人号码
	 */
	private String phone;
	/**
	 * 负责人姓名
	 */
	private String supervisorName;
	/**
	 * 营业执照
	 */
	private String licence;
	
	public AxpDetailBean() {
	}

	public AxpDetailBean(Long id,String phone, String supervisorName, String licence) {
		super();
		this.stoAxpId = id;
		this.phone = phone;
		this.supervisorName = supervisorName;
		this.licence = licence;
	}
	
	public Long getStoAxpId() {
		return stoAxpId;
	}

	public void setStoAxpId(Long stoAxpId) {
		this.stoAxpId = stoAxpId;
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

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	@Override
	public String toString() {
		return "AxpDetailBean [phone=" + phone + ", supervisorName=" + supervisorName + ", licence=" + licence + "]";
	}
	
	
}
