package com.zhiduan.axp.dao.user.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016-05-30.
 */
public class UserPExpBean implements Serializable {

    private static final long serialVersionUID = -8409090449689946111L;

    /**
     * 用户ID
     */

    private Long userId;
    /**
     * 学校
     */
    private Long collegeId;


    /**
     *  学校
     */
    private String collegeAddress;

    /**
     *  宿舍地址
     */
    private String dormitoryAddress;

    /**
     *验证状态状态 1:未提交 2:已提交 3:审核通过,4:审核失败
     */
    private Byte state;


    /**
     *审核信息
     */
    private String verifyMsg;

    /**
     *修改人
     */
    private Long createUser;

    /**
     *修改日期
     */
    private Date createDate;
    /**
    /**
     *修改人
     */
    private Long updateUser;
    /**
     *修改日期
     */
    private Date updateDate;
    /**
     *备注
     */
    private String remark;
    /**
     *抢单模式,0:关闭 1:开启
     */
    private Integer grabOrderMode;
    /**
     * 学生证
     */
    private String stuNo;

    public String getCollegeAddress() {
        return collegeAddress;
    }

    public void setCollegeAddress(String collegeAddress) {
        this.collegeAddress = collegeAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getDormitoryAddress() {
        return dormitoryAddress;
    }

    public void setDormitoryAddress(String dormitoryAddress) {
        this.dormitoryAddress = dormitoryAddress;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getVerifyMsg() {
        return verifyMsg;
    }

    public void setVerifyMsg(String verifyMsg) {
        this.verifyMsg = verifyMsg;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGrabOrderMode() {
        return grabOrderMode;
    }

    public void setGrabOrderMode(Integer grabOrderMode) {
        this.grabOrderMode = grabOrderMode;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }


}
