/**  
* @Title: WalletVerifyInfo.java
* @Package com.zhiduan.axp.controller.model.wallet
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年5月30日 下午3:42:10 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.controller.model.wallet;

import java.io.Serializable;

/**
* @ClassName: WalletVerifyInfo
* @Description: 钱包审核实体
* @author heyi
* @date 2016年5月30日 下午3:42:10 
*
*/

public class WalletVerifyInfo implements Serializable {
	
	    
	private static final long serialVersionUID = -7935538759383354756L;
	private Long userId;
	   private String userName;
	   private Byte userType;
	   private String userTypeName;
	   private Byte verifyStatus;
	   private String verifyStatusName;
	   private String phone;
	   private String realName;
	   private String identityNumber;
	   private Long verifyUserId;
	   private String verifyUserName;
	   private String verifyTime;
	   private String signupTime;
	   private String verifyStartTime;
	   private String verifyEndTime;
	   private String signupStartTime;
	   private String signupEndTime;
	   private String auditName;
	   
	   /**
		 *验证说明
		 */
		private String verifyInfo;
		/**
		 * 认证备注
		 */
		private String verifyRemark;
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
	public Byte getUserType() {
		return userType;
	}
	public void setUserType(Byte userType) {
		this.userType = userType;
	}
	public String getUseTypeName() {
		return userTypeName;
	}
	public void setUseTypeName(String useTypeName) {
		this.userTypeName = useTypeName;
	}
	public Byte getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(Byte verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public String getVerifyStatusName() {
		return verifyStatusName;
	}
	public void setVerifyStatusName(String verifyStatusName) {
		this.verifyStatusName = verifyStatusName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdentityNumber() {
		return identityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	public String getVerifyInfo() {
		return verifyInfo;
	}
	public void setVerifyInfo(String verifyInfo) {
		this.verifyInfo = verifyInfo;
	}
	public String getVerifyRemark() {
		return verifyRemark;
	}
	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}
	public Long getVerifyUserId() {
		return verifyUserId;
	}
	public void setVerifyUserId(Long verifyUserId) {
		this.verifyUserId = verifyUserId;
	}
	public String getVerifyUserName() {
		return verifyUserName;
	}
	public void setVerifyUserName(String verifyUserName) {
		this.verifyUserName = verifyUserName;
	}
	public String getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}
	public String getSignupTime() {
		return signupTime;
	}
	public void setSignupTime(String signupTime) {
		this.signupTime = signupTime;
	}
	public String getVerifyStartTime() {
		return verifyStartTime;
	}
	public void setVerifyStartTime(String verifyStartTime) {
		this.verifyStartTime = verifyStartTime;
	}
	public String getVerifyEndTime() {
		return verifyEndTime;
	}
	public void setVerifyEndTime(String verifyEndTime) {
		this.verifyEndTime = verifyEndTime;
	}
	public String getSignupStartTime() {
		return signupStartTime;
	}
	public void setSignupStartTime(String signupStartTime) {
		this.signupStartTime = signupStartTime;
	}
	public String getSignupEndTime() {
		return signupEndTime;
	}
	public void setSignupEndTime(String signupEndTime) {
		this.signupEndTime = signupEndTime;
	}
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
}
