package com.zhiduan.axp.controller.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wangyu
 * @ClassName:com.zhiduan.axp.idl.usercenter.service.entity.ExpUserAuditInfo
 * @Description: 货源用户审核对象：
 * @date 2016/5/5 19:25
 */
public class ExpUserAuditInfo implements Serializable{
	private static final long serialVersionUID = -6223988143848481398L;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 *验证状态
	 * 0:未完善
	 * 1：待审核
	 * 2：审核通过
	 * 3：审核失败
	 */
	private String verifyStatus;
	/**
	 *验证说明
	 */
	private  ExpUserAuditResultInfo verifyInfo;

	public String getVerifyInfoString() {
		return verifyInfoString;
	}

	public void setVerifyInfoString(String verifyInfoString) {
		this.verifyInfoString = verifyInfoString;
	}

	/**
	 *验证说明
	 */
	private  String verifyInfoString;
	/**
	 *验证人
	 */
	private long verifyUserId;
	/**
	 *验证人姓名
	 */
	private String verifyUserName;
	/**
	 *验证时间
	 */
	private String verifyTime;
	/**
	 * 备注
	 */
	private String verifyRemark;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public ExpUserAuditResultInfo getVerifyInfo() {
		return verifyInfo;
	}

	public void setVerifyInfo(ExpUserAuditResultInfo verifyInfo) {
		this.verifyInfo = verifyInfo;
	}

	public long getVerifyUserId() {
		return verifyUserId;
	}

	public void setVerifyUserId(long verifyUserId) {
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

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}
}
