package com.rofour.baseball.controller.model.report;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.List;

public class CommentRateInfo extends BasePage implements Serializable {

    private static final long serialVersionUID = 9015198295215930125L;
    /**
     * 日期
     */
    private String day;
    /**
     * 校区，多逗号隔开
     */
    private String campus;
    /**
     * 有评论订单数
     */
    private Integer commentOrderNum;
    /**
     * 有评论订单分值
     */
    private Integer commentOrderTotalscore;
    /**
     * 用户评论分数
     */
    private Integer commentOrderGetscore;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public Integer getCommentOrderNum() {
        return commentOrderNum;
    }

    public void setCommentOrderNum(Integer commentOrderNum) {
        this.commentOrderNum = commentOrderNum;
    }

    public Integer getCommentOrderTotalscore() {
        return commentOrderTotalscore;
    }

    public void setCommentOrderTotalscore(Integer commentOrderTotalscore) {
        this.commentOrderTotalscore = commentOrderTotalscore;
    }

    public Integer getCommentOrderGetscore() {
        return commentOrderGetscore;
    }

    public void setCommentOrderGetscore(Integer commentOrderGetscore) {
        this.commentOrderGetscore = commentOrderGetscore;
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