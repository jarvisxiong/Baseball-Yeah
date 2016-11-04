/**  
* @Title: WalletVerifyBean.java
* @package com.rofour.baseball.dao.wallet.bean
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年5月30日 下午2:58:48 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.dao.wallet.bean;

import java.io.Serializable;

/**
* @ClassName: WalletVerifyBean
* @Description: 钱包审核实体
* @author heyi
* @date 2016年5月30日 下午2:58:48 
*
*/

public class WalletVerifyBean implements Serializable { 
	private static final long serialVersionUID = 422185022254819316L;
   private Long userId;
   private String userName;
   private Byte userType;
   private String userTypeName;
   private Byte verifyStatus;
   private String verifyStatusName;
   private String phone;
   private String realName;
   private String identityNumber;
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

   
}
