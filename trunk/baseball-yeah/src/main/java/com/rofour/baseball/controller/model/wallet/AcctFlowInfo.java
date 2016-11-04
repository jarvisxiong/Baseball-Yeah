package com.rofour.baseball.controller.model.wallet;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: AcctFlowInfo
 * @Description:
 * @author ZXY
 * @date 2016/10/13 11:01
 */
public class AcctFlowInfo implements Serializable{

    private static final long serialVersionUID = -136090269538450429L;

    private Long acctId;

    private Long flowId;

    private String thdFlowId;

    private Integer thdType;

    private Integer paymentCode;

    private Integer beforeBalance;

    private Integer afterBalance;

    private Integer optAmount;

    private Integer state;

    private Date createTime;

    private Integer balanceState;

    private Long bizId;

    private Integer bizType;

    private String thdAcctNo;

    private String thdAcctName;

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getThdFlowId() {
        return thdFlowId;
    }

    public void setThdFlowId(String thdFlowId) {
        this.thdFlowId = thdFlowId;
    }

    public Integer getThdType() {
        return thdType;
    }

    public void setThdType(Integer thdType) {
        this.thdType = thdType;
    }

    public Integer getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(Integer paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Integer getBeforeBalance() {
        return beforeBalance;
    }

    public void setBeforeBalance(Integer beforeBalance) {
        this.beforeBalance = beforeBalance;
    }

    public Integer getAfterBalance() {
        return afterBalance;
    }

    public void setAfterBalance(Integer afterBalance) {
        this.afterBalance = afterBalance;
    }

    public Integer getOptAmount() {
        return optAmount;
    }

    public void setOptAmount(Integer optAmount) {
        this.optAmount = optAmount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBalanceState() {
        return balanceState;
    }

    public void setBalanceState(Integer balanceState) {
        this.balanceState = balanceState;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getThdAcctNo() {
        return thdAcctNo;
    }

    public void setThdAcctNo(String thdAcctNo) {
        this.thdAcctNo = thdAcctNo;
    }

    public String getThdAcctName() {
        return thdAcctName;
    }

    public void setThdAcctName(String thdAcctName) {
        this.thdAcctName = thdAcctName;
    }
}