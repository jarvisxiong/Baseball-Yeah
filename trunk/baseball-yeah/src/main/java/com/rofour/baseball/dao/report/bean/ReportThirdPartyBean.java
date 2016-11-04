package com.rofour.baseball.dao.report.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: ReportThirdPartyBean
 * @Description: 第三方报表
 * @author ZXY
 * @date 2016-04-16 09:13:24
 */

public class ReportThirdPartyBean implements Serializable {

    private static final long serialVersionUID = 524285341798310665L;
    /**
     * 编号
     **/
    private Long reportId;
    /**
     * 报表日期
     **/
    private Date reportDate;
    /**
     * 学校编码
     **/
    private Long collegeId;
    /**
     * 学校名称
     **/
    private String collegeName;
    /**
     * 所属区域编码
     **/
    private Long areaId;
    /**
     * 所属区域名称
     **/
    private String areaName;
    /**
     * 商务负责人
     **/
    private String businessPrincipal;
    /**
     * 截至report_date当天结束的潜在用户手机号总数
     **/
    private Long totalPhoneNum;
    /**
     * 截至report_date当天结束的潜在用户已注册手机号数
     **/
    private Long totalRegNum;
    /**
     * 截至report_date当天结束的潜在用户未注册手机号数
     **/
    private Long totalUnregNum;
    /**
     * 截至report_date当天结束的注册率
     **/
    private BigDecimal regRate;
    /**
     * report_date当天注册数
     **/
    private Long newNum;

    public ReportThirdPartyBean() {
        super();
    }

    public ReportThirdPartyBean(Long reportId, Date reportDate, Long collegeId, String collegeName, Long areaId, String areaName, String businessPrincipal, Long totalPhoneNum, Long totalRegNum, Long totalUnregNum, BigDecimal regRate, Long newNum) {
        this.reportId = reportId;
        this.reportDate = reportDate;
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.areaId = areaId;
        this.areaName = areaName;
        this.businessPrincipal = businessPrincipal;
        this.totalPhoneNum = totalPhoneNum;
        this.totalRegNum = totalRegNum;
        this.totalUnregNum = totalUnregNum;
        this.regRate = regRate;
        this.newNum = newNum;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
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
        this.collegeName = collegeName == null ? null : collegeName.trim();
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getBusinessPrincipal() {
        return businessPrincipal;
    }

    public void setBusinessPrincipal(String businessPrincipal) {
        this.businessPrincipal = businessPrincipal == null ? null : businessPrincipal.trim();
    }

    public Long getTotalPhoneNum() {
        return totalPhoneNum;
    }

    public void setTotalPhoneNum(Long totalPhoneNum) {
        this.totalPhoneNum = totalPhoneNum;
    }

    public Long getTotalRegNum() {
        return totalRegNum;
    }

    public void setTotalRegNum(Long totalRegNum) {
        this.totalRegNum = totalRegNum;
    }

    public Long getTotalUnregNum() {
        return totalUnregNum;
    }

    public void setTotalUnregNum(Long totalUnregNum) {
        this.totalUnregNum = totalUnregNum;
    }

    public BigDecimal getRegRate() {
        return regRate;
    }

    public void setRegRate(BigDecimal regRate) {
        this.regRate = regRate;
    }

    public Long getNewNum() {
        return newNum;
    }

    public void setNewNum(Long newNum) {
        this.newNum = newNum;
    }

}