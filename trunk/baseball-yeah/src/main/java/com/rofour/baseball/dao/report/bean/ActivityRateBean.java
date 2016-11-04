package com.rofour.baseball.dao.report.bean;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.List;

public class ActivityRateBean extends BasePage implements Serializable {

    private static final long serialVersionUID = 2909265954392073893L;

    // datebase 字段
    /**
     * id
     */
    private Long id;
    /**
     * 时间
     */
    private String day;
    /**
     * 校区Id
     */
    private Long collegeId;
    /**
     * 校区名称
     */
    private String collegeName;
    /**
     * 区域Id
     */
    private Long regionId;
    /**
     * 区域描述（市）
     */
    private String regionName;
    /**
     * 当前小派总数
     */
    private Integer puserNum;
    /**
     * 当天活跃小派总数
     */
    private Integer activePuserNumDay;
    /**
     * 当月活跃小派总数
     */
    private Integer activePuserNumMonth;
    /**
     * 当天打赏总金额
     */
    private Integer bonusIncome;
    /**
     * 当天运力指数（1/10）
     */
    private Integer shippingIndex;
    /**
     * 当天运力
     */
    private Integer shippingAbility;

    // info 字段
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
     * 搜索月份
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
    /**
     * 需展示的开始时间
     */
    private String showStartDate;
    /**
     * 需展示的结束时间
     */
    private String showEndDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getPuserNum() {
        return puserNum;
    }

    public void setPuserNum(Integer puserNum) {
        this.puserNum = puserNum;
    }

    public Integer getActivePuserNumDay() {
        return activePuserNumDay;
    }

    public void setActivePuserNumDay(Integer activePuserNumDay) {
        this.activePuserNumDay = activePuserNumDay;
    }

    public Integer getActivePuserNumMonth() {
        return activePuserNumMonth;
    }

    public void setActivePuserNumMonth(Integer activePuserNumMonth) {
        this.activePuserNumMonth = activePuserNumMonth;
    }

    public Integer getBonusIncome() {
        return bonusIncome;
    }

    public void setBonusIncome(Integer bonusIncome) {
        this.bonusIncome = bonusIncome;
    }

    public Integer getShippingIndex() {
        return shippingIndex;
    }

    public void setShippingIndex(Integer shippingIndex) {
        this.shippingIndex = shippingIndex;
    }

    public Integer getShippingAbility() {
        return shippingAbility;
    }

    public void setShippingAbility(Integer shippingAbility) {
        this.shippingAbility = shippingAbility;
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

    public String getShowStartDate() {
        return showStartDate;
    }

    public void setShowStartDate(String showStartDate) {
        this.showStartDate = showStartDate;
    }

    public String getShowEndDate() {
        return showEndDate;
    }

    public void setShowEndDate(String showEndDate) {
        this.showEndDate = showEndDate;
    }
}