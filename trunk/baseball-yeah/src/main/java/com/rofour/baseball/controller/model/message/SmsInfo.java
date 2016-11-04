package com.rofour.baseball.controller.model.message;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wny on 2016-06-16.
 */
public class SmsInfo implements Serializable {

    /**
     * 短信ID
     */
    private Long smsId;
    /**
     * 短信服务商编码
     */
    private String smsVendorId;
    /**
     * 短信
     */
    private String messageTypeId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 短信内容
     */
    private String content;
    /**
     * 提交时间
     */
    private Date submitTime;
    /**
     * 短信状态
     */
    private Byte status;
    /**
     * 推送Code
     */
    private String smsCode;
    /**
     * 推送状态
     */
    private String returnStatus;

    /**
     * 推送说明
     */
    private String returnContent;

    /**
     * 尝试次数
     */
    private Integer retryCount;

    /**
     * 最后获取数据时间
     */
    private Date lastGetDataTime;

    /**
     * 发送人ID
     */
    private Long userId;

    /**
     * 发送人商户ID
     */
    private Long storeId;

    /**
     * 扩展号
     */
    private String extendCode;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 消息业务id（用来存储发消息的业务对应id）
     */
    private Long bizId;


    /**
     * 是否历史无效记录，0 是 1否
     */
    private Byte beDeleted;

//#region 扩展属性

    /**
     * 消息类型名称
     */
    private String messageTypeText;
    /**
     * 发送人名称
     */
    private String sendUserText;

    /**
     * 发送商户名称
     */

    private String sendStoreText;
    /**
     * 短信服务商名称
     */
    private String smsVendorName;


    //#endregion

    public String getSmsVendorName() {
        return smsVendorName;
    }

    public void setSmsVendorName(String smsVendorName) {
        this.smsVendorName = smsVendorName;
    }
    public String getSendStoreText() {
        return sendStoreText;
    }

    public void setSendStoreText(String sendStoreText) {
        this.sendStoreText = sendStoreText;
    }

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public String getSmsVendorId() {
        return smsVendorId;
    }

    public void setSmsVendorId(String smsVendorId) {
        this.smsVendorId = smsVendorId;
    }

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Date getLastGetDataTime() {
        return lastGetDataTime;
    }

    public void setLastGetDataTime(Date lastGetDataTime) {
        this.lastGetDataTime = lastGetDataTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getExtendCode() {
        return extendCode;
    }

    public void setExtendCode(String extendCode) {
        this.extendCode = extendCode;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public Byte getBeDeleted() {
        return beDeleted;
    }

    public void setBeDeleted(Byte beDeleted) {
        this.beDeleted = beDeleted;
    }

    public String getMessageTypeText() {
        return messageTypeText;
    }

    public void setMessageTypeText(String messageTypeText) {
        this.messageTypeText = messageTypeText;
    }

    public String getSendUserText() {
        return sendUserText;
    }

    public void setSendUserText(String sendUserText) {
        this.sendUserText = sendUserText;
    }

    public SmsInfo() {
    }

    public SmsInfo(Long smsId, String smsVendorId, String messageTypeId, String phone, String content, Date submitTime, Byte status, String smsCode, String returnStatus, String returnContent, Integer retryCount, Date lastGetDataTime, Long userId, Long storeId, String extendCode, String batchNo, Long bizId, Byte beDeleted, String messageTypeText, String sendUserText, String sendStoreText, String smsVendorName) {
        this.smsId = smsId;
        this.smsVendorId = smsVendorId;
        this.messageTypeId = messageTypeId;
        this.phone = phone;
        this.content = content;
        this.submitTime = submitTime;
        this.status = status;
        this.smsCode = smsCode;
        this.returnStatus = returnStatus;
        this.returnContent = returnContent;
        this.retryCount = retryCount;
        this.lastGetDataTime = lastGetDataTime;
        this.userId = userId;
        this.storeId = storeId;
        this.extendCode = extendCode;
        this.batchNo = batchNo;
        this.bizId = bizId;
        this.beDeleted = beDeleted;
        this.messageTypeText = messageTypeText;
        this.sendUserText = sendUserText;
        this.sendStoreText = sendStoreText;
        this.smsVendorName = smsVendorName;
    }
}
