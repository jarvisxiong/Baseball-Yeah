package com.zhiduan.axp.dao.activity.bean;


import java.io.Serializable;
import java.util.List;

public class AcctActivityPolicyBean implements Serializable {

    private static final long serialVersionUID = -4123266529071234648L;
    private Integer policyId;

    private Integer activityId;

    private String policyName;

    private Byte policyType;

    private Byte policyPriority;

    private Integer effectDays;

    public Integer getBeDeleted() {
        return beDeleted;
    }

    public void setBeDeleted(Integer beDeleted) {
        this.beDeleted = beDeleted;
    }

    private  Integer beDeleted;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    //投放规则
    private List<AcctPolicyPutRuleBean> deliverys;

    //领取规则
    private List<AcctPolicyReceiveRuleBean> receives;

    //使用规则
    private List<AcctPolicyUseRuleBean> uses;

    public List<AcctPolicyPutRuleBean> getDeliverys() {
        return deliverys;
    }

    public void setDeliverys(List<AcctPolicyPutRuleBean> deliverys) {
        this.deliverys = deliverys;
    }

    public List<AcctPolicyReceiveRuleBean> getReceives() {
        return receives;
    }

    public void setReceives(List<AcctPolicyReceiveRuleBean> receives) {
        this.receives = receives;
    }

    public List<AcctPolicyUseRuleBean> getUses() {
        return uses;
    }

    public void setUses(List<AcctPolicyUseRuleBean> uses) {
        this.uses = uses;
    }

    public AcctActivityPolicyBean(Integer policyId, Integer activityId, String policyName, Byte policyType, Byte policyPriority, Integer effectDays) {
        this.policyId = policyId;
        this.activityId = activityId;
        this.policyName = policyName;
        this.policyType = policyType;
        this.policyPriority = policyPriority;
        this.effectDays = effectDays;
    }

    public AcctActivityPolicyBean() {
        super();
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName == null ? null : policyName.trim();
    }

    public Byte getPolicyType() {
        return policyType;
    }

    public void setPolicyType(Byte policyType) {
        this.policyType = policyType;
    }

    public Byte getPolicyPriority() {
        return policyPriority;
    }

    public void setPolicyPriority(Byte policyPriority) {
        this.policyPriority = policyPriority;
    }

    public Integer getEffectDays() {
        return effectDays;
    }

    public void setEffectDays(Integer effectDays) {
        this.effectDays = effectDays;
    }
}