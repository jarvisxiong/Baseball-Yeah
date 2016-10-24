package com.zhiduan.axp.dao.user.bean;

import com.zhiduan.axp.controller.model.BasePage;

import java.io.Serializable;
import java.util.Date;

/**
 * 收件人Bean
 * Created by Administrator on 2016-08-10.
 */
public class AddresseeBean extends BasePage implements Serializable {
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 登录名
     */
    private String userName;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 注册时间
     */
    private Date signupTime;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 性别
     */
    private String gender;
    /**
     * 备注
     */
    private String remark;

    /**
     * 查询开始时间
     */
    private String startDate;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 查询结束时间
     */
    private String endDate;

    /**
     * 帐号状态
     */
    private Byte grabOrderMode;

    public Byte getGrabOrderMode() {
        return grabOrderMode;
    }

    public void setGrabOrderMode(Byte grabOrderMode) {
        this.grabOrderMode = grabOrderMode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Date signupTime) {
        this.signupTime = signupTime;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
