package com.rofour.baseball.controller.model.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016-08-24.
 */
public class DoOrderTaskDetailInfo implements Serializable {
    private static final long serialVersionUID = 5712244999118449837L;

    /**
     * 任务编号
     */
    private Long taskId;
    /**
     * 总金额
     */
    private Long totalMoney;

    private  String rule;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    /**
     * 众包人编号
     */
    private Long winnerId;
    /**
     * 子任务编号
     */
    private  Long taskSubId;
    /**
     * 任务单号
     */
    private String taskNo;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 状态
     */
    private Byte state;
    /**
     * 众包人
     */
    private String winner;
    /**
     * 众包人手机号
     */
    private String phone;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 接单时间
     */
    private String receiveTime;
    /**
     * 提交审核时间
     */
    private String auditTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 发布时间
     */
    private String publishTime;
    /**
     * 任务级别
     */
    private Byte taskLevel;
    /**
     * 要求完成时间
     */
    private String deadLine;
    /**
     * 要求内容
     */
    private String claim;
    /**
     * 学校名称
     */
    private String college;
    /**
     * 众包人备注
     */
    private String winnerRemark;
    /**
     * 评分
     */
    private Integer overallScore;
    /**
     * 评价内容
     */
    private String auditRemark;
    /**
     * 学校编号
     */
    private Long collegeId;
    /**
     * 图片资源信息
     */
    private List<AttachmentInfo> attachmentInfo;
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskSubId() {
        return taskSubId;
    }

    public void setTaskSubId(Long taskSubId) {
        this.taskSubId = taskSubId;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }



    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Byte getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(Byte taskLevel) {
        this.taskLevel = taskLevel;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getWinnerRemark() {
        return winnerRemark;
    }

    public void setWinnerRemark(String winnerRemark) {
        this.winnerRemark = winnerRemark;
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
        this.auditRemark = auditRemark;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }


    public List<AttachmentInfo> getAttachmentInfo() {
        return attachmentInfo;
    }

    public void setAttachmentInfo(List<AttachmentInfo> attachmentInfo) {
        this.attachmentInfo = attachmentInfo;
    }
}
