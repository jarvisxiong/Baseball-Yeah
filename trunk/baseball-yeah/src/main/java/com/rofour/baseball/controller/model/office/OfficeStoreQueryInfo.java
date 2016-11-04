/**  
* @Title: OfficeQueryInfo.java
* @package com.rofour.baseball.controller.model.office
* @Project: baseball-yeah
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.controller.model.office;

import java.io.Serializable;

import com.rofour.baseball.controller.model.BasePage;

/**
 * @ClassName: OfficeStoreQueryInfo
 * @Description 商户查询请求bean
 * @author gechao
 * @date 2016年10月14日 上午10:54:24
 *
 */ 
public class OfficeStoreQueryInfo extends BasePage implements Serializable {

	private static final long serialVersionUID = 4971352753163560298L;
	// 用户ID/ceo/coo
	private String userId;
	// 商户名
	private String storeName;
	// 手机
	private String phone;
	// 主ID
	private String stoExpId;
	// 详细地址
	private String location;
	// 学校名
	private String collegeName;
	// 添加时间
	private String pulicTime;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStoExpId() {
		return stoExpId;
	}
	public void setStoExpId(String stoExpId) {
		this.stoExpId = stoExpId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getPulicTime() {
		return pulicTime;
	}
	public void setPulicTime(String pulicTime) {
		this.pulicTime = pulicTime;
	} 
	

}
