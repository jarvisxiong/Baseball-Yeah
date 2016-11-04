package com.rofour.baseball.controller.model.report;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-05-23.
 */
public class SearchMonitoring implements Serializable {

    private static final long serialVersionUID = 7404087241738206152L;
    private String startDate;
    private String endDate;
    private String collegeIds;
    private String areaIds;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getCollegeIds() {
        return collegeIds;
    }

    public void setCollegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }


}
