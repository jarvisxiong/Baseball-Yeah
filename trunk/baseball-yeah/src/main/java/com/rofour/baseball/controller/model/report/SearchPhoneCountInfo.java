/**
 * @Title: SearchPhoneCountInfo.java
 * @package com.rofour.baseball.idl.waybillcenter.service
 * @Project: axp-idl
 * @Description: (用一句话描述该文件做什么)
 * @author heyi
 * @date 2016年4月15日 下午4:21:10
 * @version V1.0
 * ──────────────────────────────────
 * Copyright (c) 2016, 指端科技.
 */


package com.rofour.baseball.controller.model.report;

import java.io.Serializable;

/**
 * @ClassName: SearchPhoneCountInfo
 * @Description: TODO(学校运单号上的手机统计)
 * @author heyi
 * @date 2016年4月15日 下午4:21:10
 *
 */

public class SearchPhoneCountInfo implements Serializable {

    private static final long serialVersionUID = 7404087241738306152L;
    private Long collegeId;
    private String collegeName;
    private int phoneCount;
    private String phone;
    private int collegeCount;
    private Integer limit;
    private Integer offset;

    public SearchPhoneCountInfo() {

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

    public Integer getPhoneCount() {
        return phoneCount;
    }

    public void setPhoneCount(Integer phoneCount) {
        this.phoneCount = phoneCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCollegeCount() {
        return collegeCount;
    }

    public void setCollegeCount(Integer collegeCount) {
        this.collegeCount = collegeCount;
    }

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
}
