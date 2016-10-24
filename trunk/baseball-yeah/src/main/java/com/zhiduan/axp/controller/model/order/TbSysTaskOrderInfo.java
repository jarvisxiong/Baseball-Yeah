package com.zhiduan.axp.controller.model.order;

import java.util.Date;

/**
 * Created by Administrator on 2016-08-25.
 */
public class TbSysTaskOrderInfo {
    private Long totalMoney;
    private Long taskId;
    private Long winnerId;
    private String orderId;

    private Long taskSubId;

    private Long collegeId;

    private Integer overallScore;

    private String auditRemark;

    private Date acceptDate;

    private Date submitAuditDate;

    private Date auditDate;

    private Long auditUser;

    private Date cancelDate;

    /**
     * 接单时间
     */
    private Date receiveTime;

    public TbSysTaskOrderInfo(Long totalMoney,Long taskId,Long winnerId,String orderId, Long taskSubId, Long collegeId, Integer overallScore, String auditRemark, Date acceptDate, Date submitAuditDate, Date auditDate, Long auditUser, Date cancelDate) {
        this.orderId = orderId;
        this.taskSubId = taskSubId;
        this.collegeId = collegeId;
        this.overallScore = overallScore;
        this.auditRemark = auditRemark;
        this.acceptDate = acceptDate;
        this.submitAuditDate = submitAuditDate;
        this.auditDate = auditDate;
        this.auditUser = auditUser;
        this.cancelDate = cancelDate;
        this.winnerId=winnerId;
        this.taskId=taskId;
        this.totalMoney=totalMoney;
    }

    public TbSysTaskOrderInfo() {
        super();
    }



    public Long getTaskSubId() {
        return taskSubId;
    }

    public void setTaskSubId(Long taskSubId) {
        this.taskSubId = taskSubId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Date getSubmitAuditDate() {
        return submitAuditDate;
    }

    public void setSubmitAuditDate(Date submitAuditDate) {
        this.submitAuditDate = submitAuditDate;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Long getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(Long auditUser) {
        this.auditUser = auditUser;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }
}
