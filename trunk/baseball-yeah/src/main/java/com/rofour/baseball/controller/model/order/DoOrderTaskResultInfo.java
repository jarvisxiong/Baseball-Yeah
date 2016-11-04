package com.rofour.baseball.controller.model.order;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hy on 2016-08-24.
 */
public class DoOrderTaskResultInfo implements Serializable{
    private static final long serialVersionUID = 340637549883768033L;
    private Long taskSubId;
    private Long taskId;
    /**
     * 学校名称
     */
    private String collegeName;
    /**
     * 任务名称
     */
    private String theme;
    /**
     * 任务单号
     */
    private String taskNo;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 状态
     */
    private Byte state;
    /**
     * 众包人
     */
    private String Winner;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 接单时间
     */
    private Date receiveTime;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 任务单子数量
     */
    private Integer unitTotal;
    /**
     * 每人接单上限
     */
    private Integer upperLimit;
    /**
     * 每单多少件
     */
    private Integer taskUnitNum;
    /**
     * 任务等级 1：绿 2：蓝 3：紫 4：金
     */
    private Byte taskLevel;
    /**
     * 学校编号
     */
    private Long collegeId;
    /**
     * 开始有效日期
     */
    private Date effectiveStartDate;
    /**
     * 结束有效日期
     */
    private Date effectiveEndDate;
    /**
     * 任务截止日期
     */
    private Date deadline;

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

    public Byte getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(Byte taskLevel) {
        this.taskLevel = taskLevel;
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

    /**
     * 备注
     */
    private String remark;
    
    private Integer finalMoney;
    
    private Integer taskUnitPrice;
    
    public Integer getFinalMoney() {
		return finalMoney;
	}

	public void setFinalMoney(Integer finalMoney) {
		this.finalMoney = finalMoney;
	}

	public Integer getTaskUnitPrice() {
		return taskUnitPrice;
	}

	public void setTaskUnitPrice(Integer taskUnitPrice) {
		this.taskUnitPrice = taskUnitPrice;
	}
    public Long getTaskSubId() {
        return taskSubId;
    }

    public void setTaskSubId(Long taskSubId) {
        this.taskSubId = taskSubId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }



    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
