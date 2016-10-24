package com.zhiduan.axp.controller.model.message;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息类型业务实体
 * Created by wny on 2016-09-01.
 */
public class MsgTypeInfo implements Serializable {
    /**
     * 类型id
     */
    private Integer id;

    /**
     * 类型编码
     */
    private Integer msgType;

    /**
     * 类型名称
     */
    private String msgTypeName;

    /**
     * 发送开始时间
     */
    private Date sendStartTime;

    /**
     * 发送结束时间
     */
    private Date sendEndTime;

    /**
     * 0:失效,1:生效
     */
    private Byte state;

    /**
     * 类型通知标题，用来在通知界面显示
     */
    private String typeTitle;

    /**
     * 类型级别，为:1、2、3、4级,默认1级为主类别
     */
    private Byte typeLevel;

    /**
     * 父级类型编码
     */
    private Integer parentType;

    /**
     *接收者角色(1:axp所有类型用户,2:非axp用户,3:货源用户,4：众包用户,5：收件人用户)
     */
    private Byte receiveRole;

    /**
     * 类型头像
     */
    private String headUrl;

    public MsgTypeInfo(Integer id, Integer msgType, String msgTypeName, Date sendStartTime, Date sendEndTime, Byte state) {
        this.id = id;
        this.msgType = msgType;
        this.msgTypeName = msgTypeName;
        this.sendStartTime = sendStartTime;
        this.sendEndTime = sendEndTime;
        this.state = state;

    }

    public MsgTypeInfo(Integer id, Integer msgType, String msgTypeName, Date sendStartTime, Date sendEndTime, Byte state, String typeTitle, Byte typeLevel, Integer parentType, Byte receiveRole, String headUrl) {
        this.id = id;
        this.msgType = msgType;
        this.msgTypeName = msgTypeName;
        this.sendStartTime = sendStartTime;
        this.sendEndTime = sendEndTime;
        this.state = state;
        this.typeTitle = typeTitle;
        this.typeLevel = typeLevel;
        this.parentType = parentType;
        this.receiveRole = receiveRole;
        this.headUrl = headUrl;
    }

    public MsgTypeInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        this.msgTypeName = msgTypeName == null ? null : msgTypeName.trim();
    }

    public Date getSendStartTime() {
        return sendStartTime;
    }

    public void setSendStartTime(Date sendStartTime) {
        this.sendStartTime = sendStartTime;
    }

    public Date getSendEndTime() {
        return sendEndTime;
    }

    public void setSendEndTime(Date sendEndTime) {
        this.sendEndTime = sendEndTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public Byte getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(Byte typeLevel) {
        this.typeLevel = typeLevel;
    }

    public Integer getParentType() {
        return parentType;
    }

    public void setParentType(Integer parentType) {
        this.parentType = parentType;
    }

    public Byte getReceiveRole() {
        return receiveRole;
    }

    public void setReceiveRole(Byte receiveRole) {
        this.receiveRole = receiveRole;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
