/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.controller.model.manager;

import java.io.Serializable;
import java.util.List;

import com.zhiduan.axp.controller.model.BasePage;

/**
 * @ClassName: MsgTemplateInfo
 * @Description: 消息类型外部实体类
 * @author xzy
 * @date 2016年3月31日 下午4:49:29
 *
 */
public class MsgTemplateInfo extends BasePage implements Serializable {

	private static final long serialVersionUID = 3275578432659291343L;

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
	
	private List<?> messageTemplateIds;

	public List<?> getMessageTemplateIds() {
		return messageTemplateIds;
	}

	public void setMessageTemplateIds(List<?> messageTemplateIds) {
		this.messageTemplateIds = messageTemplateIds;
	}

	public MsgTemplateInfo(String messageTemplateId, String messageTemplateName, String messageTemplate,
			Byte priority) {
		this.messageTemplateId = messageTemplateId;
		this.messageTemplateName = messageTemplateName;
		this.messageTemplate = messageTemplate;
		this.priority = priority;
	}

	public MsgTemplateInfo() {
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
