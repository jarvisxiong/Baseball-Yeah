/**  
 * Copyright (c) 2016, 指端科技.
 */


    
package com.zhiduan.axp.controller.model.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UsableVoucherInfo
 * @Description: 代金券信息
 * @author lpp
 * @date 2016年7月20日 下午3:34:03 
 */
public class UsableVoucherInfo implements Serializable {

	private static final long serialVersionUID = 704484172860873219L;

	private Long voucherId;
	
	private String policyName;
	
	private Integer faceValue;

	private Date effectTime;

	private Date expireTime;
	
	private String phone;
	
	private Byte useState;

	public Long getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Byte getUseState() {
		return useState;
	}

	public void setUseState(Byte useState) {
		this.useState = useState;
	}
	
}
