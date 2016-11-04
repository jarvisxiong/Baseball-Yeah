/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.model.wallet;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UserDrawCheckInfo
 * @Description: 用户提现审核实体
 * @author xl
 * @date 2016年5月30日 下午5:05:02
 */
public class UserDrawCheckInfo implements Serializable {

	private static final long serialVersionUID = 1778258184509419802L;
	/**
	 * 账户兑换ID
	 */
	private Long exchangeId;
	/**
	 * 订单号
	 */
	private String flowId;
	/**
	 * 申请日期
	 */
	private Date createTime;
	/**
	 * 订单状态 0：提交申请(审核中) 1： 处理中(审核通过) 2：到账 3:审核不通过
	 */
	private Integer state;
	/**
	 * 交易类型编码 1微信,2支付宝
	 */
	private Integer thdTypeCode;
	/**
	 * 交易类型 1微信,2支付宝
	 */
	private String transType;
	/**
	 * 提现金额
	 */
	private Integer drawAmount;
	/**
	 * 申请人
	 */
	private String userName;
	/**
	 * 提现账号
	 */
	private String acctNo;
	/**
	 * 账户余额
	 */
	private Integer acctBalance;
	/**
	 * 审核人
	 */
	private String checkEmp;
	/**
	 * 审核时间
	 */
	private Date checkTime;
	/**
	 * 到账时间
	 */
	private Date arriTime;
	/**
	 * 联系电话
	 */
	private String phone;

	/**
	 * 交易流水号
	 */
	private String thdFlowId;
	/**
	 * 微信用户openId
	 */
	private String openId;

	/**
	 * 申请人身份证号
	 */
	private String idNo;
	/**
	 * 申请人昵称
	 */
	private String nickname;
	
	/**
	 * 激活状态（1激活,0未激活）
	 */
	private Integer activeState;

	public Long getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(Long exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public int getThdTypeCode() {
		return thdTypeCode;
	}

	public void setThdTypeCode(Integer thdTypeCode) {
		this.thdTypeCode = thdTypeCode;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Integer getDrawAmount() {
		return drawAmount;
	}

	public void setDrawAmount(Integer drawAmount) {
		this.drawAmount = drawAmount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public Integer getAcctBalance() {
		return acctBalance;
	}

	public void setAcctBalance(Integer acctBalance) {
		this.acctBalance = acctBalance;
	}

	public String getCheckEmp() {
		return checkEmp;
	}

	public void setCheckEmp(String checkEmp) {
		this.checkEmp = checkEmp;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getArriTime() {
		return arriTime;
	}

	public void setArriTime(Date arriTime) {
		this.arriTime = arriTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getThdFlowId() {
		return thdFlowId;
	}

	public void setThdFlowId(String thdFlowId) {
		this.thdFlowId = thdFlowId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getActiveState() {
		return activeState;
	}

	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}

	@Override
	public String toString() {
		return "UserDrawCheckInfo [exchangeId=" + exchangeId + ", flowId=" + flowId + ", createTime=" + createTime
				+ ", state=" + state + ", thdTypeCode=" + thdTypeCode + ", transType=" + transType + ", drawAmount="
				+ drawAmount + ", userName=" + userName + ", acctNo=" + acctNo + ", acctBalance=" + acctBalance
				+ ", checkEmp=" + checkEmp + ", checkTime=" + checkTime + ", arriTime=" + arriTime + ", phone=" + phone
				+ ", thdFlowId=" + thdFlowId + ", openId=" + openId + ", idNo=" + idNo + ", nickname=" + nickname + ", activeState=" + activeState + "]";
	}

}
