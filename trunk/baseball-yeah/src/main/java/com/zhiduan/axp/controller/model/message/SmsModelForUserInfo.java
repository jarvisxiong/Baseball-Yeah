package com.zhiduan.axp.controller.model.message;

import java.io.Serializable;

public class SmsModelForUserInfo implements Serializable {
    private static final long serialVersionUID = -4781468874141498617L;
    private Long smsModelId;

    private Long userId;

    private String templateName;

    private String modelContent;

    public SmsModelForUserInfo(Long smsModelId, Long userId, String templateName, String modelContent) {
        this.smsModelId = smsModelId;
        this.userId = userId;
        this.templateName = templateName;
        this.modelContent = modelContent;
    }

    public SmsModelForUserInfo() {
        super();
    }

    public Long getSmsModelId() {
        return smsModelId;
    }

    public void setSmsModelId(Long smsModelId) {
        this.smsModelId = smsModelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getModelContent() {
        return modelContent;
    }

    public void setModelContent(String modelContent) {
        this.modelContent = modelContent == null ? null : modelContent.trim();
    }
}