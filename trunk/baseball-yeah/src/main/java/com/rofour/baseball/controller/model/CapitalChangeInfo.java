/**  
* @Title: CapitalChangeInfo.java
* @package com.rofour.baseball.controller.model
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年7月6日 上午10:19:24 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.controller.model;

import java.io.Serializable;

/**
* @ClassName: CapitalChangeInfo
* @Description: 用户资金变动信息
* @author heyi
* @date 2016年7月6日 上午10:19:24 
*
*/

public class CapitalChangeInfo implements Serializable {
  
	private static final long serialVersionUID = -225834764431939963L;
	private Long acctId;
    private String userName;
    private String paymentName;
    private String typeName;
    private String acctNo;
    private String state;
    private String flowId;
    private String beforeBalance;
    private String afterBalance;

	private  String optAmount;

	public String getOptAmount() {
		return optAmount;
	}

	public void setOptAmount(String optAmount) {
		this.optAmount = optAmount;
	}

	private String createTime;
    private Integer payType;
    private String startTime;
    private String endTime;



    /**
	 * @return acctId
	 */
	
	public Long getAcctId() {
		return acctId;
	}
	/**
	 * @param acctId the acctId to set
	 */
	
	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}
	/**
	 * @return userName
	 */
	
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return paymentName
	 */
	
	public String getPaymentName() {
		return paymentName;
	}
	/**
	 * @param paymentName the paymentName to set
	 */
	
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	/**
	 * @return typeName
	 */
	
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return acctNo
	 */
	
	public String getAcctNo() {
		return acctNo;
	}
	/**
	 * @param acctNo the acctNo to set
	 */
	
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	/**
	 * @return state
	 */
	
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return flowId
	 */
	
	public String getFlowId() {
		return flowId;
	}
	/**
	 * @param flowId the flowId to set
	 */
	
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	/**
	 * @return beforeBalance
	 */
	
	public String getBeforeBalance() {
		return beforeBalance;
	}
	/**
	 * @param beforeBalance the beforeBalance to set
	 */
	
	public void setBeforeBalance(String beforeBalance) {
		this.beforeBalance = beforeBalance;
	}
	/**
	 * @return afterBalance
	 */
	
	public String getAfterBalance() {
		return afterBalance;
	}
	/**
	 * @param afterBalance the afterBalance to set
	 */
	
	public void setAfterBalance(String afterBalance) {
		this.afterBalance = afterBalance;
	}
	/**
	 * @return createTime
	 */
	
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
}
