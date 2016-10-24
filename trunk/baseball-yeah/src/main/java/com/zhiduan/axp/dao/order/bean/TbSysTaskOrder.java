package com.zhiduan.axp.dao.order.bean;

import java.util.Date;

public class TbSysTaskOrder {
    private Long orderId;

    private Long taskSubId;

    private Long collegeId;

    private Integer overallScore;

    private String auditRemark;

    private Date acceptDate;

    private Date submitAuditDate;

    private Date auditDate;

    private Long auditUser;

    private Date cancelDate;

    public TbSysTaskOrder(Long orderId, Long taskSubId, Long collegeId, Integer overallScore, String auditRemark, Date acceptDate, Date submitAuditDate, Date auditDate, Long auditUser, Date cancelDate) {
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
    }

    public TbSysTaskOrder() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
}