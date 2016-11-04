package com.rofour.baseball.dao.crowdsource.bean;

import com.rofour.baseball.controller.model.BasePage;
import com.rofour.baseball.controller.model.resource.CredentialURLInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 众包管理
 * Created by lijun on 2016/10/11.
 */
public class CrowdsourceBean extends BasePage implements Serializable {

    private static final long serialVersionUID = -5388542435272969619L;

    private Long userId;

    private String loginAccount;//登录账号

    private String registerTime;//注册时间

    private String userName;//姓名（小派）

    private String sex;//性别

    private String cityId;//城市ID
    private String cityName;//城市名称

    private String collegeName;//校区

    private String dorm;//宿舍

    private int orderNum;//订单量

    private String favourable;//好评率

    private String walletBalance;//钱包余额

    private String userState;//用户状态

    private String collegeId;//校区ID

    private String registerStartTime;
    private String registerEndTime;


    private String phone;//手机号

    private String source;//来源

    private String cardNo;//身份证

    private String auditTime;//审核时间

    private String auditState;//审核状态

    private String auditRemark;//审核备注

    private String detailPath;//详细地址

    private String accountImg;//账号头像图片

    private String handCardPhoto;//手持身份证正面照

    private String cardPhoto;//身份证正面照


    /**
     * 证件信息
     */
    private List<CredentialURLInfo> photoList;

    private List<String> userIdArr;

    private String colleges;//校园多选

    private String searchType;//区分查询类型


    public CrowdsourceBean() {
        super();
    }

    public String getColleges() {
        return colleges;
    }

    public void setColleges(String colleges) {
        this.colleges = colleges;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public List<String> getUserIdArr() {
        return userIdArr;
    }

    public void setUserIdArr(List<String> userIdArr) {
        this.userIdArr = userIdArr;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRegisterStartTime() {
        return registerStartTime;
    }

    public void setRegisterStartTime(String registerStartTime) {
        this.registerStartTime = registerStartTime;
    }

    public String getRegisterEndTime() {
        return registerEndTime;
    }

    public void setRegisterEndTime(String registerEndTime) {
        this.registerEndTime = registerEndTime;
    }


    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getFavourable() {
        return favourable;
    }

    public void setFavourable(String favourable) {
        this.favourable = favourable;
    }

    public String getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(String walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getDetailPath() {
        return detailPath;
    }

    public void setDetailPath(String detailPath) {
        this.detailPath = detailPath;
    }

    public String getAccountImg() {
        return accountImg;
    }

    public void setAccountImg(String accountImg) {
        this.accountImg = accountImg;
    }

    public String getHandCardPhoto() {
        return handCardPhoto;
    }

    public void setHandCardPhoto(String handCardPhoto) {
        this.handCardPhoto = handCardPhoto;
    }

    public String getCardPhoto() {
        return cardPhoto;
    }

    public void setCardPhoto(String cardPhoto) {
        this.cardPhoto = cardPhoto;
    }

    public List<CredentialURLInfo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<CredentialURLInfo> photoList) {
        this.photoList = photoList;
    }

}
