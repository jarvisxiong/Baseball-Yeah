package com.rofour.baseball.dao.report.bean;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.List;

public class CommentRateBean extends BasePage implements Serializable {

    private static final long serialVersionUID = 1711598997797397696L;

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
     * 当天订单总数
     */
    private Integer totalOrderNum;
    /**
     * 有效评论订单数
     */
    private Integer commentOrderNum;
    /**
     * 有效评论好评单数
     */
    private Integer commentOrderGoodnum;
    /**
     * 有效评论用户打分总数
     */
    private Integer commentOrderGetscore;

    // info 字段
    /**
     * 校区，多逗号隔开
     */
    private String campus;
    /**
     * 有评论订单分值
     */
    private Integer commentOrderTotalscore;
    /**
     * 好评率
     */
    private String favorableRate;
    /**
     * 环比增长
     */
    private String increaseRate;

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

    public Integer getTotalOrderNum() {
        return totalOrderNum;
    }

    public void setTotalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    public Integer getCommentOrderNum() {
        return commentOrderNum;
    }

    public void setCommentOrderNum(Integer commentOrderNum) {
        this.commentOrderNum = commentOrderNum;
    }

    public Integer getCommentOrderGoodnum() {
        return commentOrderGoodnum;
    }

    public void setCommentOrderGoodnum(Integer commentOrderGoodnum) {
        this.commentOrderGoodnum = commentOrderGoodnum;
    }

    public Integer getCommentOrderGetscore() {
        return commentOrderGetscore;
    }

    public void setCommentOrderGetscore(Integer commentOrderGetscore) {
        this.commentOrderGetscore = commentOrderGetscore;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public Integer getCommentOrderTotalscore() {
        return commentOrderTotalscore;
    }

    public void setCommentOrderTotalscore(Integer commentOrderTotalscore) {
        this.commentOrderTotalscore = commentOrderTotalscore;
    }

    public String getFavorableRate() {
        return favorableRate;
    }

    public void setFavorableRate(String favorableRate) {
        this.favorableRate = favorableRate;
    }

    public String getIncreaseRate() {
        return increaseRate;
    }

    public void setIncreaseRate(String increaseRate) {
        this.increaseRate = increaseRate;
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