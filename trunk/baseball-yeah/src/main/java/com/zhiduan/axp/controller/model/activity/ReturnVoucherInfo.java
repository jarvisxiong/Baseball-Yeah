/**  
 * 
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.model.activity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ReturnVoucherInfo
 * @Description: 返还用户的代金券
 * @author chenyu
 * @date 2016年7月19日 下午2:16:12 
 */
public class ReturnVoucherInfo implements Serializable {

	private static final long serialVersionUID = -7577051122711539717L;

	private Long voucherId;

	private Date effectTime;

	private Date expireTime;

	private Date receiveTime;

	private Date useTime;

	private Byte state;
	
	private String stateStr;
	

	
	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public Long getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
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
	
	

}
