package com.rofour.baseball.dao.waybill.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 异常件
 * 
 * @author wuzhiquan
 *
 */
public class WaybillProblemBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3548421686316464595L;
    /**
     * 异常件ID
     */
    private Long problemId;

    /**
     * 运单编码
     */
    private Long waybillId;

    /**
     * 问题件类型编码
     */
    private String problemTypeCode;
    /**
     * 登记时间
     */
    private Date createTime;

    /**
     * 登记人
     */
    private Long createUserId;
    /**
     * 登记人账号
     */
    private String createUserName;

    /**
     * 是否取消问题件
     */
    private Boolean isCanceled;

    /**
     * 是否已退件
     */
    private Boolean isReturned;
    /**
     * 退件时间
     */
    private Date returnedTime;
    /**
     * 退件人
     */
    private Long returnedUserId;
    /**
     * 退件账号
     */
    private String returnedUserName;

    /**
     * 异常件原因
     */
    private String problemReason;

    /**
     * 退件原因
     */
    private String returnReason;

    /**
     * 业务id 11：众包派件 12：众包寄件
     */
    private Byte bizId;

    /**
     * 订单编码
     */
    private Long orderId;

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Long getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Long waybillId) {
        this.waybillId = waybillId;
    }

    public String getProblemTypeCode() {
        return problemTypeCode;
    }

    public void setProblemTypeCode(String problemTypeCode) {
        this.problemTypeCode = problemTypeCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Boolean getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(Boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public Boolean getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }

    public Date getReturnedTime() {
        return returnedTime;
    }

    public void setReturnedTime(Date returnedTime) {
        this.returnedTime = returnedTime;
    }

    public Long getReturnedUserId() {
        return returnedUserId;
    }

    public void setReturnedUserId(Long returnedUserId) {
        this.returnedUserId = returnedUserId;
    }

    public String getReturnedUserName() {
        return returnedUserName;
    }

    public void setReturnedUserName(String returnedUserName) {
        this.returnedUserName = returnedUserName;
    }

    public String getProblemReason() {
        return problemReason;
    }

    public void setProblemReason(String problemReason) {
        this.problemReason = problemReason;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public Byte getBizId() {
        return bizId;
    }

    public void setBizId(Byte bizId) {
        this.bizId = bizId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}
