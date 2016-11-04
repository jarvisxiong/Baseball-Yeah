package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016-05-30.
 */
public class UserManagerLogBean implements Serializable {

    private static final long serialVersionUID = 3813548187058157384L;


    /**
     * 编码
     */
    private Long userManagerLogId;
    /**
     * 用户编码
     */
    private Long userManagerId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 表名
     */
    private String tableName;

    private  String  newValue;

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    private  String oldValue;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }



    private  String caption;

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    private  long menuId;
    /**
     * 操作类型
     */
    private String operationType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 用户IP
     */
    private String userIp;
    /**
     * 操作时间
     */
    private Date operationTime;

    public Long getUserManagerLogId() {
        return userManagerLogId;
    }

    public void setUserManagerLogId(Long userManagerLogId) {
        this.userManagerLogId = userManagerLogId;
    }

    public Long getUserManagerId() {
        return userManagerId;
    }

    public void setUserManagerId(Long userManagerId) {
        this.userManagerId = userManagerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

}
