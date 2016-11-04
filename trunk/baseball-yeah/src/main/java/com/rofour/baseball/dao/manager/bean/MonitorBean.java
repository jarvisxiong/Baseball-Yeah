package com.rofour.baseball.dao.manager.bean;

import com.rofour.baseball.controller.model.manager.MonitorContactsInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: MonitorBean
 * @Description: 监控项实体
 * @author: xulang
 * @Date: 2016-08-22 13:44
 */
public class MonitorBean implements Serializable {
    private static final long serialVersionUID = -4753617837623886586L;
    /**
     * 主键
     */
    private Long monitorId;
    /**
     * 监控项名称
     */
    private String monitorName;
    /**
     * 监控SQL，查询结果应为数字类型
     */
    private String monitorSql;
    /**
     * 阈值
     */
    private Integer threshold;
    /**
     * 开始监控时间
     */
    private Date startTime;
    /**
     * 结束监控时间
     */
    private Date endTime;
    /**
     * 执行间隔，单位 秒
     */
    private Integer runInterval;
    /**
     * 产生日期
     */
    private Date createTime;
    /**
     * 短信发送时间
     */
    private Date sendsmsTime;

    /**
     * 监控联系人
     */
    private List<MonitorContactsInfo> monitorContactsInfoList;

    public MonitorBean(Long monitorId, String monitorName, String monitorSql, Integer threshold, Date startTime, Date endTime, Integer runInterval, Date createTime, Date sendsmsTime) {
        this.monitorId = monitorId;
        this.monitorName = monitorName;
        this.monitorSql = monitorSql;
        this.threshold = threshold;
        this.startTime = startTime;
        this.endTime = endTime;
        this.runInterval = runInterval;
        this.createTime = createTime;
        this.sendsmsTime = sendsmsTime;
    }

    public MonitorBean() {
        super();
    }

    public Long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Long monitorId) {
        this.monitorId = monitorId;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName == null ? null : monitorName.trim();
    }

    public String getMonitorSql() {
        return monitorSql;
    }

    public void setMonitorSql(String monitorSql) {
        this.monitorSql = monitorSql == null ? null : monitorSql.trim();
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getRunInterval() {
        return runInterval;
    }

    public void setRunInterval(Integer runInterval) {
        this.runInterval = runInterval;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendsmsTime() {
        return sendsmsTime;
    }

    public void setSendsmsTime(Date sendsmsTime) {
        this.sendsmsTime = sendsmsTime;
    }

    public List<MonitorContactsInfo> getMonitorContactsInfoList() {
        return monitorContactsInfoList;
    }

    public void setMonitorContactsInfoList(List<MonitorContactsInfo> monitorContactsInfoList) {
        this.monitorContactsInfoList = monitorContactsInfoList;
    }
}