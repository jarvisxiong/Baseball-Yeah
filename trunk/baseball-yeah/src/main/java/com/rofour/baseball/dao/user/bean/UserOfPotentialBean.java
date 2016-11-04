package com.rofour.baseball.dao.user.bean;

import java.util.Date;

public class UserOfPotentialBean {
    private Long potentialUserId;

    private String phone;

    private Long storeId;

    private String source;

    private Date addTime;

    public UserOfPotentialBean( String phone, Long storeId, String source, Date addTime) {
        this.phone = phone;
        this.storeId = storeId;
        this.source = source;
        this.addTime = addTime;
    }

    public UserOfPotentialBean() {
        super();
    }

    public Long getPotentialUserId() {
        return potentialUserId;
    }

    public void setPotentialUserId(Long potentialUserId) {
        this.potentialUserId = potentialUserId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}