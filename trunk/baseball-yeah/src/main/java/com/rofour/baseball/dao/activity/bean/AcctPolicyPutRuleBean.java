package com.rofour.baseball.dao.activity.bean;

import java.io.Serializable;

public class AcctPolicyPutRuleBean implements Serializable {

    private static final long serialVersionUID = -4123266529071234648L;
    private Integer ruleId;

    private Integer policyId;

    public Long getScale() {
        return scale;
    }

    public void setScale(Long scale) {
        this.scale = scale;
    }

    private Long scale;

    //面额
    private Integer faceValue;
    //比例
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    private Integer totalAmount;

    private Integer initialAmount;

    private Integer receivedAmount;

    private Integer usedAmount;

    private Integer expiredAmount;

    public AcctPolicyPutRuleBean(Integer ruleId, Integer policyId, Integer faceValue, Integer totalAmount, Integer initialAmount, Integer receivedAmount, Integer usedAmount, Integer expiredAmount) {
        this.ruleId = ruleId;
        this.policyId = policyId;
        this.faceValue = faceValue;
        this.totalAmount = totalAmount;
        this.initialAmount = initialAmount;
        this.receivedAmount = receivedAmount;
        this.usedAmount = usedAmount;
        this.expiredAmount = expiredAmount;
    }

    public AcctPolicyPutRuleBean() {
        super();
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Integer faceValue) {
        this.faceValue = faceValue;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(Integer initialAmount) {
        this.initialAmount = initialAmount;
    }

    public Integer getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Integer receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Integer getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(Integer usedAmount) {
        this.usedAmount = usedAmount;
    }

    public Integer getExpiredAmount() {
        return expiredAmount;
    }

    public void setExpiredAmount(Integer expiredAmount) {
        this.expiredAmount = expiredAmount;
    }
}