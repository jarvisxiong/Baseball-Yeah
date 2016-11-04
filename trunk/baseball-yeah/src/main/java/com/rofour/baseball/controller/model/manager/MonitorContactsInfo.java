package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: MonitorContactsInfo
 * @Description: 监控联系人传输实体
 * @author: xulang
 * @Date: 2016-08-22 13:48
 */
public class MonitorContactsInfo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -8424956129632988311L;
    /**
     * 主键
     */
    private Long monitorContactId;
    /**
     * 监控项ID
     */
    private Long monitorId;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 产生日期
     */
    private Date createTime;

    public MonitorContactsInfo(Long monitorContactId, Long monitorId, String contactName, String phone, String email, Date createTime) {
        this.monitorContactId = monitorContactId;
        this.monitorId = monitorId;
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
        this.createTime = createTime;
    }

    public MonitorContactsInfo() {
        super();
    }

    public Long getMonitorContactId() {
        return monitorContactId;
    }

    public void setMonitorContactId(Long monitorContactId) {
        this.monitorContactId = monitorContactId;
    }

    public Long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Long monitorId) {
        this.monitorId = monitorId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}