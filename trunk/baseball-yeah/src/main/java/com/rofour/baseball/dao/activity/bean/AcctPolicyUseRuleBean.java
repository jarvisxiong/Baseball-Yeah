package com.rofour.baseball.dao.activity.bean;

import java.io.Serializable;

public class AcctPolicyUseRuleBean implements Serializable {

    private static final long serialVersionUID = -4123266529071236648L;
    private Integer ruleId;

    private Integer policyId;

    private Integer quotaId;

    public String getQuotaField() {
        return quotaField;
    }

    public void setQuotaField(String quotaField) {
        this.quotaField = quotaField;
    }

    private String quotaField;

    private String operator;

    private String quotaValue;

    private String quotaTextValue;

    private String quotaDivOrderTypeValue;

    public String getQuotaDivOrderTypeValue() {
        return quotaDivOrderTypeValue;
    }

    public void setQuotaDivOrderTypeValue(String quotaDivOrderTypeValue) {
        this.quotaDivOrderTypeValue = quotaDivOrderTypeValue;
    }

    public String getQuotaTextValue() {
        return quotaTextValue;
    }

    public void setQuotaTextValue(String quotaTextValue) {
        this.quotaTextValue = quotaTextValue;
    }

    //省
    private String quotaProvinceField;
    //学校
    private String quotaCollegeField;
    //城市
    private String quotaCityField;
    //时间
    private String quotaTimeValue;
    //bool
    private String quotaBoolValue;


    public String getQuotaProvinceField() {
        return quotaProvinceField;
    }

    public void setQuotaProvinceField(String quotaProvinceField) {
        this.quotaProvinceField = quotaProvinceField;
    }

    public String getQuotaCollegeField() {
        return quotaCollegeField;
    }

    public void setQuotaCollegeField(String quotaCollegeField) {
        this.quotaCollegeField = quotaCollegeField;
    }

    public String getQuotaCityField() {
        return quotaCityField;
    }

    public void setQuotaCityField(String quotaCityField) {
        this.quotaCityField = quotaCityField;
    }

    public String getQuotaTimeValue() {
        return quotaTimeValue;
    }

    public void setQuotaTimeValue(String quotaTimeValue) {
        this.quotaTimeValue = quotaTimeValue;
    }

    public String getQuotaBoolValue() {
        return quotaBoolValue;
    }

    public void setQuotaBoolValue(String quotaBoolValue) {
        this.quotaBoolValue = quotaBoolValue;
    }

    public AcctPolicyUseRuleBean(Integer ruleId, Integer policyId, Integer quotaId, String quotaField, String operator, String quotaValue) {
        this.ruleId = ruleId;
        this.quotaField = quotaField;
        this.policyId = policyId;
        this.quotaId = quotaId;
        this.operator = operator;
        this.quotaValue = quotaValue;
    }

    public AcctPolicyUseRuleBean() {
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

    public Integer getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(Integer quotaId) {
        this.quotaId = quotaId;
    }

    public String getQuotaFiled() {
        return quotaField;
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getQuotaValue() {
        return quotaValue;
    }

    public void setQuotaValue(String quotaValue) {
        this.quotaValue = quotaValue == null ? null : quotaValue.trim();
    }
}