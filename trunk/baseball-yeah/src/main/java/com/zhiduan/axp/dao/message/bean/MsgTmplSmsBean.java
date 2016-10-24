package com.zhiduan.axp.dao.message.bean;

import java.util.Date;

public class MsgTmplSmsBean {
    private Integer id;

    private Integer tmplCode;

    private String tmplName;

    private String tmplContent;

    private String placeSign;

    private Date createTime;

    private Date updateTime;

    private Byte state;

    private Integer msgType;

    private String msgTypeName;

    public MsgTmplSmsBean(Integer id, Integer tmplCode, String tmplName, String tmplContent, String placeSign, Date createTime, Date updateTime, Byte state, Integer msgType, String msgTypeName) {
        this.id = id;
        this.tmplCode = tmplCode;
        this.tmplName = tmplName;
        this.tmplContent = tmplContent;
        this.placeSign = placeSign;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
        this.msgType = msgType;
        this.msgTypeName = msgTypeName;
    }

    public MsgTmplSmsBean() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTmplCode() {
        return tmplCode;
    }

    public void setTmplCode(Integer tmplCode) {
        this.tmplCode = tmplCode;
    }

    public String getTmplName() {
        return tmplName;
    }

    public void setTmplName(String tmplName) {
        this.tmplName = tmplName == null ? null : tmplName.trim();
    }

    public String getTmplContent() {
        return tmplContent;
    }

    public void setTmplContent(String tmplContent) {
        this.tmplContent = tmplContent == null ? null : tmplContent.trim();
    }

    public String getPlaceSign() {
        return placeSign;
    }

    public void setPlaceSign(String placeSign) {
        this.placeSign = placeSign == null ? null : placeSign.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgTypeName() {
        return msgTypeName;
    }

    public void setMsgTypeName(String msgTypeName) {
        this.msgTypeName = msgTypeName;
    }
}