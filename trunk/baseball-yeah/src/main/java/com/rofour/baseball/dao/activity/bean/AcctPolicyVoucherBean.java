package com.rofour.baseball.dao.activity.bean;

import java.io.Serializable;
import java.util.Date;

public class AcctPolicyVoucherBean implements Serializable {

	private static final long serialVersionUID = -5123266529071234648L;
	private Long voucherId;

	private Integer policyId;

	private Long userId;

	private Integer faceValue;

	private Date effectTime;

	private Date expireTime;

	private Date receiveTime;

	private Date useTime;

	private Byte state;

	public AcctPolicyVoucherBean(Long voucherId, Integer policyId, Long userId,
			Integer faceValue, Date effectTime, Date expireTime,
			Date receiveTime, Date useTime, Byte state) {
		this.voucherId = voucherId;
		this.policyId = policyId;
		this.userId = userId;
		this.faceValue = faceValue;
		this.effectTime = effectTime;
		this.expireTime = expireTime;
		this.receiveTime = receiveTime;
		this.useTime = useTime;
		this.state = state;
	}

	public AcctPolicyVoucherBean() {
		super();
	}

	public Long getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(Integer faceValue) {
		this.faceValue = faceValue;
	}

	public Date getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	private Integer beDeleted;

	public Integer getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Integer beDeleted) {
		this.beDeleted = beDeleted;
	}
}