package com.rofour.baseball.controller.model;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.Date;

public class RequestWorkSigninInfo extends BasePage implements Serializable {

    private static final long serialVersionUID = -8563926153005515603L;
    /**
	 * 省id
	 */
	private Long provinceId;

	/**
	 * 市id
	 */
	private Long cityId;

	/**
	 * 学校id
	 */
	private Long collegeId;

	/**
	 * 工作日期
	 */
	private String workDate;


    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }
}
