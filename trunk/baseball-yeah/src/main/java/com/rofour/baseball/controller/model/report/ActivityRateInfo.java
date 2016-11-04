package com.rofour.baseball.controller.model.report;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.List;

public class ActivityRateInfo extends BasePage implements Serializable {

    private static final long serialVersionUID = -2802634990692410053L;

    /**
     * 时间
     */
    private String day;
    /**
     * 校区
     */
    private String collegeName;
    /**
     * 小派总数
     */
    private Integer puserNum;
    /**
     * 活跃小派数
     */
    private Integer activePuserNum;
    /**
     * 活跃度
     */
    private String activeRate;
    /**
     * 平均活跃度
     */
    private String averageActiveRate;
    /**
     * 活跃度新增
     */
    private String increaseActiveRate;
    /**
     * 环比增长
     */
    private String packetIncreaseRate;

    /**
     * 搜索月份（yyyy-MM）
     */
    private String searchMonth;
    /**
     * 搜索区域
     */
    private List<Long> searchRegionList;
    /**
     * 搜索校区
     */
    private String searchColleges;
    /**
     * 搜索开始时间
     */
    private String searchStartDate;
    /**
     * 搜索结束时间
     */
    private String searchEndDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Integer getPuserNum() {
        return puserNum;
    }

    public void setPuserNum(Integer puserNum) {
        this.puserNum = puserNum;
    }

    public Integer getActivePuserNum() {
        return activePuserNum;
    }

    public void setActivePuserNum(Integer activePuserNum) {
        this.activePuserNum = activePuserNum;
    }

    public String getActiveRate() {
        return activeRate;
    }

    public void setActiveRate(String activeRate) {
        this.activeRate = activeRate;
    }

    public String getAverageActiveRate() {
        return averageActiveRate;
    }

    public void setAverageActiveRate(String averageActiveRate) {
        this.averageActiveRate = averageActiveRate;
    }

    public String getIncreaseActiveRate() {
        return increaseActiveRate;
    }

    public void setIncreaseActiveRate(String increaseActiveRate) {
        this.increaseActiveRate = increaseActiveRate;
    }

    public String getPacketIncreaseRate() {
        return packetIncreaseRate;
    }

    public void setPacketIncreaseRate(String packetIncreaseRate) {
        this.packetIncreaseRate = packetIncreaseRate;
    }

    public String getSearchMonth() {
        return searchMonth;
    }

    public void setSearchMonth(String searchMonth) {
        this.searchMonth = searchMonth;
    }

    public List<Long> getSearchRegionList() {
        return searchRegionList;
    }

    public void setSearchRegionList(List<Long> searchRegionList) {
        this.searchRegionList = searchRegionList;
    }

    public String getSearchColleges() {
        return searchColleges;
    }

    public void setSearchColleges(String searchColleges) {
        this.searchColleges = searchColleges;
    }

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }
}