package com.zhiduan.axp.controller.model.wallet;

import java.io.Serializable;

/**
 * 用户账户资金兑换记录实体
 * @author zhoujie
 *
 */
public class AcctExchangeCashInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3596378692021293560L;

	/**
	 * 主键id
	 */
	private long exchangeId;
	
	/**
	 * 用户id
	 */
	private long  userId;
	
	/**
	 * 用户账户id
	 */
	private long  acctId;

	/**
	 * 支付流水
	 */
	private long flowId;
	
	/**
	 * 兑换金额
	 */
	private int balance;
	
	
	/**
	 * 类型
	 */
	private  int type;
	
	/**
	 * 提现状态
	 */
	private int state;
	
	/**
	 * 是否失效
	 */
	private int isDel;
	
	/**
	 * 创建时间
	 */
	private  String createTime;
	
	/**
	 * 备注
	 */
	private  String  comment;
	/**
	 * 修改时间
	 */
	private  String modifyTime;
	
	
	/**
	 * 第三方帐户id
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
	 * 提现次数
	 */
	private Integer times;

	/**
	 * 用户当天已提现金额
	 */
	private Integer drawBalance;

	
	public Integer getDrawBalance() {
		return drawBalance;
	}

	public void setDrawBalance(Integer drawBalance) {
		this.drawBalance = drawBalance;
	}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
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

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
	
	
}
