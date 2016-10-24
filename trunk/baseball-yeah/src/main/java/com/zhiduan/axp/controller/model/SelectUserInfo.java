/**  
* @Title: selectUserInfo.java
* @Package com.zhiduan.axp.idl.common.service
* @Project: axp-idl
* @Description: (用一句话描述该文件做什么)
* @author 周琦
* @date 2016年3月27日 下午4:56:05 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.controller.model;

import java.io.Serializable;
import java.util.List;

import com.zhiduan.axp.dao.manager.bean.ExpressBean;
import com.zhiduan.axp.dao.store.bean.AxpBean;

/**
* @ClassName: selectUserInfo
* @Description: 查询用户信息接口
* @author 周琦
* @date 2016年3月27日 下午4:56:05 
*
*/

public class SelectUserInfo implements Serializable {
	private static final long serialVersionUID = 3991395358979746991L;
	/**
	 * 用户Id
	 */
	private Long userId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 头像
	 */
	private String icon;
	/**
	 * 是否负责人
	 */
	private Byte beSupervisor;
	/**
	 * 用户类型
	 */
	private Integer userType;
	/**
	 * 地址
	 */
	private String location;
	/**
	 * 商户ID
	 */
	private Long storeId;
	/**
	 * 商户代码
	 */
	private String storeCode;
	/**
	 * 商户名称
	 */
	private String storeName;
	/**
	 * 商户类型
	 */
	private Integer type;
	/**
	 * 认证状态
	 */
	private Byte verifyStatus;
	/**
	 * 门店信息
	 */
	private List<AxpBean> axps;
	/**
	 * 关联的快递信息
	 */
	private List<ExpressBean> ecs;
	/**
	 * 是否首次登陆 0 否 1 是
	 */
	private Byte firstLogin;
	/**
	 * 短信剩余条数
	 */
	private Integer smsCount; 
	/**
	 * 短信剩余条数
	 */
	private String realName;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Byte getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Byte firstLogin) {
		this.firstLogin = firstLogin;
	}

	public Integer getSmsCount() {
		return smsCount;
	}

	public void setSmsCount(Integer smsCount) {
		this.smsCount = smsCount;
	}

	public List<ExpressBean> getEcs() {
		return ecs;
	}

	public void setEcs(List<ExpressBean> ecs) {
		this.ecs = ecs;
	}

	@Override
	public String toString() {
		return "SelectUserInfo [userId=" + userId + ", userName=" + userName + ", phone=" + phone + ", icon=" + icon
				+ ", beSupervisor=" + beSupervisor + ", userType=" + userType + ", location=" + location + ", storeId="
				+ storeId + ", storeCode=" + storeCode + ", storeName=" + storeName + ", type=" + type
				+ ", verifyStatus=" + verifyStatus + ", axps=" + axps + "]";
	}

	public List<AxpBean> getAxps() {
		return axps;
	}

	public void setAxps(List<AxpBean> axps) {
		this.axps = axps;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Byte getBeSupervisor() {
		return beSupervisor;
	}

	public void setBeSupervisor(Byte beSupervisor) {
		this.beSupervisor = beSupervisor;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Byte getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Byte verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

}
