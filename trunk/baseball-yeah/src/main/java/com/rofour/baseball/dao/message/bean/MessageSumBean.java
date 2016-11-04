package com.rofour.baseball.dao.message.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wny on 2016-06-16.
 */
public class MessageSumBean implements Serializable {


    /**
     * 未提交 ，状态为0
     */
    private Integer sendingCount;

    /**
     * 已发送未回执数 ，状态为1
     */
    private Integer sentCount;

    /**
     * 发送成功 ，状态为2
     */
    private Integer sucessCount;


    /**
     * 发送失败 ，状态为-1
     */
        private Integer failCount;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 分组时间
     */
    private String groupDate;

    /**
     * 成功率
     */
    private String sucessRate;

    public String getSucessRate() {
        return sucessRate;
    }

    public void setSucessRate(String sucessRate) {
        this.sucessRate = sucessRate;
    }

    public Integer getSendingCount() {
        return sendingCount;
    }

    public void setSendingCount(Integer sendingCount) {
        this.sendingCount = sendingCount;
    }

    public Integer getSentCount() {
        return sentCount;
    }

    public void setSentCount(Integer sentCount) {
        this.sentCount = sentCount;
    }

    public Integer getSucessCount() {
        return sucessCount;
    }

    public void setSucessCount(Integer sucessCount) {
        this.sucessCount = sucessCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(String groupDate) {
        this.groupDate = groupDate;
    }

    public MessageSumBean() {
    }

    public MessageSumBean(Integer sendingCount, Integer sentCount, Integer sucessCount, Integer failCount, String groupName, String groupDate, String sucessRate) {
        this.sendingCount = sendingCount;
        this.sentCount = sentCount;
        this.sucessCount = sucessCount;
        this.failCount = failCount;
        this.groupName = groupName;
        this.groupDate = groupDate;
        this.sucessRate = sucessRate;
    }
}
