package com.zhiduan.axp.dao.user.bean;

public class MessageType {
    private Integer messageTypeId;

    private String messageTypeName;

    private String messageTemplate;

    private Byte rankNo;

    public MessageType(Integer messageTypeId, String messageTypeName, String messageTemplate, Byte rankNo) {
        this.messageTypeId = messageTypeId;
        this.messageTypeName = messageTypeName;
        this.messageTemplate = messageTemplate;
        this.rankNo = rankNo;
    }

    public MessageType() {
        super();
    }

    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getMessageTypeName() {
        return messageTypeName;
    }

    public void setMessageTypeName(String messageTypeName) {
        this.messageTypeName = messageTypeName == null ? null : messageTypeName.trim();
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate == null ? null : messageTemplate.trim();
    }

    public Byte getRankNo() {
        return rankNo;
    }

    public void setRankNo(Byte rankNo) {
        this.rankNo = rankNo;
    }
}