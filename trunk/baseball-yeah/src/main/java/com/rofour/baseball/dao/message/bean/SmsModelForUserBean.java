package com.rofour.baseball.dao.message.bean;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SmsModelForUserBean extends BasePage implements Serializable {
    private static final long serialVersionUID = -8474153956658617041L;
    private Long smsModelId;//主键

    private Long userId;//创建人

    private String createUserName;

    private String createUserPhone;

    private String templateName;//模板名称

    private String modelContent;//模板内容

    private byte state;//审核状态

    private String createTime;

    private String auditTime;//审核时间

    private long auditUserManagerId;//审核人

    private String auditUserManagerName;//审核人

    private String headUserId;//负责人Id

    private String headUserName;//负责人名称

    private String headUserPhone;//负责人手机号

    private String optReason;//原因

    private String stateNum;//审核状态，字符串类型

    private String collegeId;//校园ID

    private String beSupervisor;

    private String storeId;

    private Map<String,String> storeIdMap;

    private String createStartTime;
    private String createEndTime;
    private String auditStartTime;
    private String auditEndTime;

    private List<String> smsModelIdArr;

    private List<String> userIdPhoneArr;


    public SmsModelForUserBean(Long smsModelId, Long userId, String templateName, String modelContent, Byte state,String auditTime,long auditUserManagerId,String optReason,String createUserName
            ,String createUserPhone,String headUserPhone,String createTime,String auditUserManagerName) {
        this.smsModelId = smsModelId;
        this.userId = userId;
        this.templateName = templateName;
        this.modelContent = modelContent;
        this.state = state;
        this.auditTime = auditTime;
        this.auditUserManagerId = auditUserManagerId;
        this.optReason = optReason;
        this.createUserName = createUserName;
        this.createUserPhone = createUserPhone;
        this.headUserPhone = headUserPhone;
        this.createTime = createTime;
        this.auditUserManagerName = auditUserManagerName;
    }

    public SmsModelForUserBean() {
        super();
    }

    public Map<String,String> getStoreIdMap() {
        return storeIdMap;
    }

    public void setStoreIdMap(Map<String,String> storeIdMap) {
        this.storeIdMap = storeIdMap;
    }

    public Long getSmsModelId() {
        return smsModelId;
    }

    public void setSmsModelId(Long smsModelId) {
        this.smsModelId = smsModelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getModelContent() {
        return modelContent;
    }

    public void setModelContent(String modelContent) {
        this.modelContent = modelContent == null ? null : modelContent.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public Long getAuditUserManagerId() {
        return auditUserManagerId;
    }

    public void setAuditUserManagerId(Long auditUserManagerId) {
        this.auditUserManagerId = auditUserManagerId;
    }

    public String getOptReason() {
        return optReason;
    }

    public void setOptReason(String optReason) {
        this.optReason = optReason;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserPhone() {
        return createUserPhone;
    }

    public void setCreateUserPhone(String createUserPhone) {
        this.createUserPhone = createUserPhone;
    }

    public String getHeadUserPhone() {
        return headUserPhone;
    }

    public void setHeadUserPhone(String headUserPhone) {
        this.headUserPhone = headUserPhone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStateNum() {
        return stateNum;
    }

    public void setStateNum(String stateNum) {
        this.stateNum = stateNum;
    }

    public String getAuditUserManagerName() {
        return auditUserManagerName;
    }

    public void setAuditUserManagerName(String auditUserManagerName) {
        this.auditUserManagerName = auditUserManagerName;
    }


    public String getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(String createStartTime) {
        this.createStartTime = createStartTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getAuditStartTime() {
        return auditStartTime;
    }

    public void setAuditStartTime(String auditStartTime) {
        this.auditStartTime = auditStartTime;
    }

    public String getAuditEndTime() {
        return auditEndTime;
    }

    public void setAuditEndTime(String auditEndTime) {
        this.auditEndTime = auditEndTime;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getBeSupervisor() {
        return beSupervisor;
    }

    public void setBeSupervisor(String beSupervisor) {
        this.beSupervisor = beSupervisor;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }


    public String getHeadUserId() {
        return headUserId;
    }

    public void setHeadUserId(String headUserId) {
        this.headUserId = headUserId;
    }

    public String getHeadUserName() {
        return headUserName;
    }

    public void setHeadUserName(String headUserName) {
        this.headUserName = headUserName;
    }


    public List<String> getSmsModelIdArr() {
        return smsModelIdArr;
    }

    public void setSmsModelIdArr(List<String> smsModelIdArr) {
        this.smsModelIdArr = smsModelIdArr;
    }

    public List<String> getUserIdPhoneArr() {
        return userIdPhoneArr;
    }

    public void setUserIdPhoneArr(List<String> userIdPhoneArr) {
        this.userIdPhoneArr = userIdPhoneArr;
    }

}