/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.dao.wallet.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: AcctExchangeCashBean
 * @Description: 用户账户兑换记录实体
 * @author xl
 * @date 2016年6月2日 上午11:15:51
 */
public class AcctExchangeCashBean implements Serializable {

	private static final long serialVersionUID = 195533264932874681L;

	/**
	 * 主键ID
	 */
	private long exchangeId;
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 账户ID
	 */
	private long acctId;
	/**
	 * 收银台交易流水
	 */
	private long flowId;
	/**
	 * 提现金额单位分
	 */
	private int balance;
	/**
	 * 1出2入出入类型
	 */
	private byte type;
	/**
	 * 提现状态：0提交申请，1处理中，2到账，3审核不通过
	 */
	private byte state;
	/**
	 * 0成功1失败，提现是否成功
	 */
	private byte isDel;
	/**
	 * 申请时间
	 */
	private Date createTime;
	/**
	 * 
	 */
	private String comme;
	/**
	 * 审核时间
	 */
	private Date modifyTime;
	/**
	 * 支付时间
	 */
	private Date payTime;
	/**
	 * 第三方帐号id
	 */
	private long thdId;
	/**
	 * ip地址
	 */
	private String ipAddress;
	/**
	 * 设备号
	 */
	private String devId;
	/**
	 * 操作人ID
	 */
	private Long operationId;
	/**
	 * 操作人
	 */
	private String operationName;
	/**
	 * 第三方流水id
	 */
	private String thdFlowId;

	public long getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(long exchangeId) {
		this.exchangeId = exchangeId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getAcctId() {
		return acctId;
	}

	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}

	public long getFlowId() {
		return flowId;
	}

	public void setFlowId(long flowId) {
		this.flowId = flowId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public byte getIsDel() {
		return isDel;
	}

	public void setIsDel(byte isDel) {
		this.isDel = isDel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getComme() {
		return comme;
	}

	public void setComme(String comme) {
		this.comme = comme;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public long getThdId() {
		return thdId;
	}

	public void setThdId(long thdId) {
		this.thdId = thdId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getThdFlowId() {
		return thdFlowId;
	}

	public void setThdFlowId(String thdFlowId) {
		this.thdFlowId = thdFlowId;
	}

	@Override
	public String toString() {
		return "AcctExchangeCashBean [exchangeId=" + exchangeId + ", userId=" + userId + ", acctId=" + acctId
				+ ", flowId=" + flowId + ", balance=" + balance + ", type=" + type + ", state=" + state + ", isDel="
				+ isDel + ", createTime=" + createTime + ", comme=" + comme + ", modifyTime=" + modifyTime
				+ ", payTime=" + payTime + ", thdId=" + thdId + ", ipAddress=" + ipAddress + ", devId=" + devId
				+ ", operationId=" + operationId + ", operationName=" + operationName + ", thdFlowId=" + thdFlowId
				+ "]";
	}

}
