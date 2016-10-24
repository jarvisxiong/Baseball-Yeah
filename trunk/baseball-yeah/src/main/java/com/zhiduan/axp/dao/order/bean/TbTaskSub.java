package com.zhiduan.axp.dao.order.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zhiduan.axp.common.CustomJsonDateDeserializer;

import java.io.Serializable;
import java.util.Date;

public class TbTaskSub implements Serializable {

    private static final long serialVersionUID = -725911111222335299L;
    private Long taskSubId;

    private Long taskNo;

    private Long taskId;

    private  String collegeName;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    private Integer taskUnitPrice;

    private Integer unitTotal;

    private Integer upperLimit;

    private Integer taskUnitNum;

    private Long collegeId;

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date effectiveStartDate;

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date effectiveEndDate;

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date deadline;

    private Long publishUserId;

    private Date publishTime;

    private Byte state;

    public TbTaskSub(Long taskSubId, Long taskNo, Long taskId, Integer taskUnitPrice, Integer unitTotal, Integer upperLimit, Integer taskUnitNum, Long collegeId, Date effectiveStartDate, Date effectiveEndDate, Date deadline, Long publishUserId, Date publishTime, Byte state) {
        this.taskSubId = taskSubId;
        this.taskNo = taskNo;
        this.taskId = taskId;
        this.taskUnitPrice = taskUnitPrice;
        this.unitTotal = unitTotal;
        this.upperLimit = upperLimit;
        this.taskUnitNum = taskUnitNum;
        this.collegeId = collegeId;
        this.effectiveStartDate = effectiveStartDate;
        this.effectiveEndDate = effectiveEndDate;
        this.deadline = deadline;
        this.publishUserId = publishUserId;
        this.publishTime = publishTime;
        this.state = state;
    }

    public TbTaskSub() {
        super();
    }

    public Long getTaskSubId() {
        return taskSubId;
    }

    public void setTaskSubId(Long taskSubId) {
        this.taskSubId = taskSubId;
    }

    public Long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(Long taskNo) {
        this.taskNo = taskNo;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskUnitPrice() {
        return taskUnitPrice;
    }

    public void setTaskUnitPrice(Integer taskUnitPrice) {
        this.taskUnitPrice = taskUnitPrice;
    }

    public Integer getUnitTotal() {
        return unitTotal;
    }

    public void setUnitTotal(Integer unitTotal) {
        this.unitTotal = unitTotal;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Integer getTaskUnitNum() {
        return taskUnitNum;
    }

    public void setTaskUnitNum(Integer taskUnitNum) {
        this.taskUnitNum = taskUnitNum;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Date getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(Date effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Date getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Date effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Long getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Long publishUserId) {
        this.publishUserId = publishUserId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}