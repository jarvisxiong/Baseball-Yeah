/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;

import com.rofour.baseball.controller.model.BasePage;

/**
 * @ClassName: TbSmsVendorInfo
 * @Description: 短信供应商实体类DTO,用作接口返回实体
 * @author xl
 * @date 2016年3月28日 上午9:43:40
 *
 */

public class SmsVendorInfo extends BasePage implements Serializable {

	private static final long serialVersionUID = 390181799813650768L;

	/**
	 * 编码
	 */
	private String smsVendorId;
	/**
	 * 编号
	 */
	private String smsVendorCode;
	/**
	 * 供应商名称
	 */
	private String smsVendorName;
	/**
	 * 账号
	 */
	private String loginName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 等级
	 */
	private Byte level;
	/**
	 * 权重
	 */
	private Float weight;
	/**
	 * 阀值
	 */
	private Integer threshold;
	/**
	 * 状态
	 */
	private Byte status;
	/**
	 * 是否删除
	 */
	private Byte beDeleted;
	/**
	 * 通道号
	 */
	private String channelCode;
	/**
	 * 接口地址
	 */
	private String interfaceAddress;

	public SmsVendorInfo(String smsVendorId, String smsVendorCode, String smsVendorName, String loginName,
			String password, Byte level, Float weight, Integer threshold, Byte status, Byte beDeleted,
			String channelCode, String interfaceAddress) {
		this.smsVendorId = smsVendorId;
		this.smsVendorCode = smsVendorCode;
		this.smsVendorName = smsVendorName;
		this.loginName = loginName;
		this.password = password;
		this.level = level;
		this.weight = weight;
		this.threshold = threshold;
		this.status = status;
		this.beDeleted = beDeleted;
		this.channelCode = channelCode;
		this.interfaceAddress = interfaceAddress;
	}

	public SmsVendorInfo() {
		super();
	}

	public String getSmsVendorId() {
		return smsVendorId;
	}

	public void setSmsVendorId(String smsVendorId) {
		this.smsVendorId = smsVendorId;
	}

	public String getSmsVendorCode() {
		return smsVendorCode;
	}

	public void setSmsVendorCode(String smsVendorCode) {
		this.smsVendorCode = smsVendorCode == null ? null : smsVendorCode.trim();
	}

	public String getSmsVendorName() {
		return smsVendorName;
	}

	public void setSmsVendorName(String smsVendorName) {
		this.smsVendorName = smsVendorName == null ? null : smsVendorName.trim();
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Byte beDeleted) {
		this.beDeleted = beDeleted;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode == null ? null : channelCode.trim();
	}

	public String getInterfaceAddress() {
		return interfaceAddress;
	}

	public void setInterfaceAddress(String interfaceAddress) {
		this.interfaceAddress = interfaceAddress == null ? null : interfaceAddress.trim();
	}
}