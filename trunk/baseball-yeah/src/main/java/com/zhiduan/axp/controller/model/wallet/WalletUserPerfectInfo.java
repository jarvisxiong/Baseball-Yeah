/**  
* @Title: WalletUserPerfectInfo.java
* @Package com.zhiduan.axp.controller.model.wallet
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年5月31日 下午1:23:30 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.controller.model.wallet;

import java.io.Serializable;
import java.util.List;

import com.zhiduan.axp.controller.model.resource.CredentialURLInfo;
import com.zhiduan.axp.controller.model.user.ExpUserAuditResultInfo;
import com.zhiduan.axp.dao.manager.bean.CollegeBean;

/**
* @ClassName: WalletUserPerfectInfo
* @Description: TODO(这里用一句话描述这个类的作用)
* @author heyi
* @date 2016年5月31日 下午1:23:30 
*
*/

public class WalletUserPerfectInfo implements Serializable {
	
	private static final long serialVersionUID = 7853492071307844453L;
	/**
	 * 用户ID
	 */
	private Long userId;
	private Byte userType;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 帐号
	 */
	private String userName;
	/**
	 * 身份证号码
	 */
	private String identityNumber;
	/**
	 * 用户认证状态
	 */
	private Byte verifyStatus;
	/**
	 * 认证信息
	 */
	private String verifyInfo;
	/**
	 * 认证备注
	 */
	private String verifyRemark;
	/**
	 * 证件信息
	 */
	private List<CredentialURLInfo> photoList;

	private ExpUserAuditResultInfo auditResultInfo;
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public Byte getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Byte verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	
	public List<CredentialURLInfo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<CredentialURLInfo> photoList) {
		this.photoList = photoList;
	}

	public String getVerifyInfo() {
		return verifyInfo;
	}

	public void setVerifyInfo(String verifyInfo) {
		this.verifyInfo = verifyInfo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ExpUserAuditResultInfo getAuditResultInfo() {
		return auditResultInfo;
	}

	public void setAuditResultInfo(ExpUserAuditResultInfo auditResultInfo) {
		this.auditResultInfo = auditResultInfo;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public Byte getUserType() {
		return userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}
}
