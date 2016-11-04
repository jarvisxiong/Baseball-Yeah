package com.rofour.baseball.dao.crowdsource.bean;

import com.rofour.baseball.controller.model.BasePage;
import com.rofour.baseball.controller.model.resource.CredentialURLInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 校园详情
 * Created by lijun on 2016/10/12.
 */
public class CollegePieBean extends BasePage implements Serializable {

    private String collegeId;//校区ID

    private String collegeCode;//校园编码

    private String fullName;//校区全称

    private String simpleName;//校区简称

    private String cityId;//城市ID

    private String cityName;//城市名称

    private String nature;//办学性质

    private String collegeType;//所属类型

    private String provinceName;//省份

    private String countyName;//县区

    private String ceoName;//校园ceo

    private String collegeRemark;//校园备注

    private String status;//状态


    public CollegePieBean() {
        super();
    }


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getCollegeType() {
        return collegeType;
    }

    public void setCollegeType(String collegeType) {
        this.collegeType = collegeType;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getCeoName() {
        return ceoName;
    }

    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }

    public String getCollegeRemark() {
        return collegeRemark;
    }

    public void setCollegeRemark(String collegeRemark) {
        this.collegeRemark = collegeRemark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
