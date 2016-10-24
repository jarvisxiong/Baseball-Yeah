/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.controller.model.manager;

import java.io.Serializable;

import com.zhiduan.axp.controller.model.BasePage;

/**
 * @ClassName: MsgConfigInfo
 * @Description: 消息配置表外部实体类
 * @author xzy
 * @date 2016年3月28日 上午9:52:26
 *
 */
public class MsgConfigInfo extends BasePage implements Serializable {

	private static final long serialVersionUID = -1670675734316081409L;

	private Long messageConfigId;
	/**
	 * 消息类型
	 */
	private String messageTypeId;
	/**
	 * 发送类型
	 */
	private String sendTypeId;
	/**
	 * 等级
	 */
	private Byte level;
	/**
	 * 是否及时发送
	 */
	private Byte beImmediateSend;
	/**
	 * 最大长度
	 */
	private Integer maxLength;
	/**
	 * 是否启用
	 */
	private Byte beEnabled;
	/**
	 * 发送角色
	 */
	private Long sendRoleId;
	/**
	 * 扩展码
	 */
	private String extendCode;

	public MsgConfigInfo(Long messageConfigId, String messageTypeId, String sendTypeId, Byte level,
			Byte beImmediateSend, Integer maxLength, Byte beEnabled, Long sendRoleId, String extendCode) {
		this.messageConfigId = messageConfigId;
		this.messageTypeId = messageTypeId;
		this.sendTypeId = sendTypeId;
		this.level = level;
		this.beImmediateSend = beImmediateSend;
		this.maxLength = maxLength;
		this.beEnabled = beEnabled;
		this.sendRoleId = sendRoleId;
		this.extendCode = extendCode;
	}

	public MsgConfigInfo() {
		super();
	}

	public Long getMessageConfigId() {
		return messageConfigId;
	}

	public void setMessageConfigId(Long messageConfigId) {
		this.messageConfigId = messageConfigId;
	}

	public String getMessageTypeId() {
		return messageTypeId;
	}

	public void setMessageTypeId(String messageTypeId) {
		this.messageTypeId = messageTypeId;
	}

	public String getSendTypeId() {
		return sendTypeId;
	}

	public void setSendTypeId(String sendTypeId) {
		this.sendTypeId = sendTypeId;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public Byte getBeImmediateSend() {
		return beImmediateSend;
	}

	public void setBeImmediateSend(Byte beImmediateSend) {
		this.beImmediateSend = beImmediateSend;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public Byte getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Byte beEnabled) {
		this.beEnabled = beEnabled;
	}

	public Long getSendRoleId() {
		return sendRoleId;
	}

	public void setSendRoleId(Long sendRoleId) {
		this.sendRoleId = sendRoleId;
	}

	public String getExtendCode() {
		return extendCode;
	}

	public void setExtendCode(String extendCode) {
		this.extendCode = extendCode == null ? null : extendCode.trim();
	}

}
