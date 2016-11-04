package com.rofour.baseball.controller.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016-06-01.
 */
public class UserManagerLogModel extends BasePage implements Serializable   {

    private static final long serialVersionUID = 3813548124058157384L;

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


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    private  Long menuId;


    /**
     * 表名
     */
    private String tableName;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
    	if(null != endDate && !endDate.trim().equals("") && endDate.trim().length() > 0) {
    		endDate += " 23:59:59";
    	}
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @Fields age :搜索条件开始时间
     */
    private String startDate;
    /**
     * @Fields age :搜索条件结束时间
     */
    private  String endDate;

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
