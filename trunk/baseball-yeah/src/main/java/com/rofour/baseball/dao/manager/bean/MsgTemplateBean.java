/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;

/**
 * @ClassName: MsgTemplateBean
 * @Description: 消息类型实体类
 * @author xzy
 * @date 2016年4月1日 下午2:47:20
 *
 */
public class MsgTemplateBean implements Serializable {

	private static final long serialVersionUID = -4426141241398990143L;
	/**
	 * 编码
	 */
	private String messageTemplateId;
	/**
	 * 名称
	 */
	private String messageTemplateName;
	/**
	 * 短信模版
	 */
	private String messageTemplate;
	/**
	 * 优先级
	 */
	private Byte priority;

	public MsgTemplateBean(String messageTemplateId, String messageTemplateName, String messageTemplate,
			Byte priority) {
		this.messageTemplateId = messageTemplateId;
		this.messageTemplateName = messageTemplateName;
		this.messageTemplate = messageTemplate;
		this.priority = priority;
	}

	public MsgTemplateBean() {
		super();
	}

	public String getMessageTemplateId() {
		return messageTemplateId;
	}

	public void setMessageTemplateId(String messageTemplateId) {
		this.messageTemplateId = messageTemplateId == null ? null : messageTemplateId.trim();
	}

	public String getMessageTemplateName() {
		return messageTemplateName;
	}

	public void setMessageTemplateName(String messageTemplateName) {
		this.messageTemplateName = messageTemplateName == null ? null : messageTemplateName.trim();
	}

	public String getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate == null ? null : messageTemplate.trim();
	}

	public Byte getPriority() {
		return priority;
	}

	public void setPriority(Byte priority) {
		this.priority = priority;
	}
}