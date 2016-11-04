package com.rofour.baseball.controller.model.order;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016-08-24.
 */
public class DoOrderTaskSearchInfo extends BasePage implements Serializable {
    private static final long serialVersionUID = 2811180644586476457L;
    /**
     * 主任务单号
     */
    private String mainTaskNo;
    /**
     * 任务单号
     */
    private String taskNo;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 订单状态 1:创建2:已接单,3:配送中,4: 处理中 ,5:完成,6:取消,7:异常 10:审核
     */
    private Byte state;
    /**
     * 众包人姓名
     */
    private String winnerName;
    /**
     * 众包人手机号码
     */
    private String phone;
    /**
     * 接单查询时间（开始）
     */
    private String startReceiveTime;
    /**
     * 接单查询时间（结束）
     */
    private String endReceiveTime;
    /**
     * 结束查询时间(开始)
     */
    private String startOverTime;
    /**
     * 结束查询时间(结束)
     */
    private String endOverTime;
    /**
     * 审核查询时间(开始)
     */
    private String startAuditTime;
    /**
     * 审核查询时间(结束)
     */
    private String endAuditTime;

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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStartReceiveTime() {
        return startReceiveTime;
    }

    public void setStartReceiveTime(String startReceiveTime) {
        this.startReceiveTime = startReceiveTime;
    }

    public String getEndReceiveTime() {
        return endReceiveTime;
    }

    public void setEndReceiveTime(String endReceiveTime) {
        this.endReceiveTime = endReceiveTime;
    }

    public String getStartOverTime() {
        return startOverTime;
    }

    public void setStartOverTime(String startOverTime) {
        this.startOverTime = startOverTime;
    }

    public String getEndOverTime() {
        return endOverTime;
    }

    public void setEndOverTime(String endOverTime) {
        this.endOverTime = endOverTime;
    }

    public String getStartAuditTime() {
        return startAuditTime;
    }

    public void setStartAuditTime(String startAuditTime) {
        this.startAuditTime = startAuditTime;
    }

    public String getEndAuditTime() {
        return endAuditTime;
    }

    public void setEndAuditTime(String endAuditTime) {
        this.endAuditTime = endAuditTime;
    }

    public String getMainTaskNo() {
        return mainTaskNo;
    }

    public void setMainTaskNo(String mainTaskNo) {
        this.mainTaskNo = mainTaskNo;
    }
}
