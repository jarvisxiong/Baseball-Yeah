package com.rofour.baseball.dao.store.bean;

import java.io.Serializable;

/**
 * @ClassName: SearchSchoolBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZXY
 * @date 2016/5/9 19:31
 */
public class SearchCollegeBean implements Serializable {

    private static final long serialVersionUID = -2407566490746749216L;
    /**
     * 学校号
     */
    private Long collegeId;
    /**
     * 学校编码
     */
    private String collegeCode;
    /**
     * 学校名称
     */
    private String collegeName;

    public SearchCollegeBean(){};

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
