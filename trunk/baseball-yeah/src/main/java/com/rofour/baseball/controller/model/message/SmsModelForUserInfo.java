package com.rofour.baseball.controller.model.message;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;

public class SmsModelForUserInfo extends BasePage implements Serializable {
    private static final long serialVersionUID = -4781468874141498617L;
    private Long smsModelId;

    private Long userId;

    private String templateName;

    private String modelContent;

    private byte state;

    private String auditTime;

    private Long auditUserManagerId;

    private String optReason;

    public SmsModelForUserInfo(Long smsModelId, Long userId, String templateName, String modelContent, Byte state,String auditTime,long auditUserManagerId,String optReason) {
        this.smsModelId = smsModelId;
        this.userId = userId;
        this.templateName = templateName;
        this.modelContent = modelContent;
        this.state = state;
        this.auditTime = auditTime;
        this.auditUserManagerId = auditUserManagerId;
        this.optReason = optReason;
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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public Long getAuditUserManagerId() {
        return auditUserManagerId;
    }

    public void setAuditUserManagerId(Long auditUserManagerId) {
        this.auditUserManagerId = auditUserManagerId;
    }

    public String getOptReason() {
        return optReason;
    }

    public void setOptReason(String optReason) {
        this.optReason = optReason;
    }
}