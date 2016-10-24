package com.zhiduan.axp.dao.message.bean;

import java.util.Date;

/**
 * @ClassName: MsgTypeParasBean
 * @Description: 消息类型属性实体
 * @author: xulang
 * @Date: 2016-09-02 10:48
 */
public class MsgTypeParasBean {
    /**
     * 主键
     */
    private Long parasId;
    /**
     * 参数编码
     */
    private String parasCode;
    /**
     * 参数名称
     */
    private String parasName;
    /**
     * 消息类型
     */
    private Integer msgType;
    /**
     * 消息类型名称
     */
    private String msgTypeName;
    /**
     * 插入时间
     */
    private Date insertTime;
    /**
     * 状态 0失效1生效
     */
    private Byte state;
    /**
     * 是否可空 0不可空 1可空
     */
    private Byte beNull;

    public MsgTypeParasBean(Long parasId, String parasCode, String parasName, Integer msgType, Date insertTime, Byte state, Byte beNull, String msgTypeName) {
        this.parasId = parasId;
        this.parasCode = parasCode;
        this.parasName = parasName;
        this.msgType = msgType;
        this.insertTime = insertTime;
        this.state = state;
        this.beNull = beNull;
        this.msgTypeName = msgTypeName;
    }

    public MsgTypeParasBean() {
        super();
    }

    public Long getParasId() {
        return parasId;
    }

    public void setParasId(Long parasId) {
        this.parasId = parasId;
    }

    public String getParasCode() {
        return parasCode;
    }

    public void setParasCode(String parasCode) {
        this.parasCode = parasCode == null ? null : parasCode.trim();
    }

    public String getParasName() {
        return parasName;
    }

    public void setParasName(String parasName) {
        this.parasName = parasName == null ? null : parasName.trim();
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getBeNull() {
        return beNull;
    }

    public void setBeNull(Byte beNull) {
        this.beNull = beNull;
    }

    public String getMsgTypeName() {
        return msgTypeName;
    }

    public void setMsgTypeName(String msgTypeName) {
        this.msgTypeName = msgTypeName;
    }
}