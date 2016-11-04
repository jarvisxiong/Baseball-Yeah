/**  
* @Title: OfficeStoreBean.java
* @package com.rofour.baseball.dao.officemanage.bean
* @Project: baseball-yeah
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.dao.officemanage.bean;

import java.io.Serializable;

/**
 * @ClassName: OfficeStoreBean
 * @Description 职务管理属下商户bean
 * @author WJ
 * @date 2016年10月12日 下午1:56:44
 *
 */

public class OfficeStoreBean implements Serializable {

	private static final long serialVersionUID = 2340739107660652242L;
	
	private Long storeId;
	
	private String storeName;

	private String location;

	private String phone;

	private String college;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	
}
