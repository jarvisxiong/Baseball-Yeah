package com.zhiduan.axp.dao.activity.bean;

import java.io.Serializable;

public class AcctQuotaBean implements Serializable {

    private static final long serialVersionUID = -4123266529071234148L;
    private Integer quotaId;

    private String quotaName;

    private String fieldName;

    private Boolean state;

    public AcctQuotaBean(Integer quotaId, String quotaName, String fieldName, Boolean state) {
        this.quotaId = quotaId;
        this.quotaName = quotaName;
        this.fieldName = fieldName;
        this.state = state;
    }

    public AcctQuotaBean() {
        super();
    }

    public Integer getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(Integer quotaId) {
        this.quotaId = quotaId;
    }

    public String getQuotaName() {
        return quotaName;
    }

    public void setQuotaName(String quotaName) {
        this.quotaName = quotaName == null ? null : quotaName.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}