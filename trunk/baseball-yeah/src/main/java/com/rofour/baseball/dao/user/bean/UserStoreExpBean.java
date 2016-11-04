package com.rofour.baseball.dao.user.bean;

import java.io.Serializable;
import java.util.Date;


/**
* @ClassName: TbUserStoreExpBean
* @Description: 操作用户商户信息DTO
* @author ZhangLei
* @date 2016年3月25日 下午6:36:54 
*
*/
    
public class UserStoreExpBean implements Serializable{

    private static final long serialVersionUID = -8409090449689946171L;

    /**
     * @Fields userId : 用户编码
     */

    private Long userId;

    /**
     * @Fields beSupervisor : 是否负责人
     */
    private Byte beSupervisor;

    /**
     * @Fields storeId : 商户编码
     */
    private Long storeId;

    /**
     * @Fields identityNumber : 身份证号码
     */
    private String identityNumber;
    /**
     *验证状态
     */
    private Byte verifyStatus;
    /**
     *验证说明
     */
    private String verifyInfo;
    /**
     *验证人
     */
    private Long verifyUserId;
    /**
     *验证人姓名
     */
    private String verifyUserName;
    /**
     *验证时间
     */
    private Date verifyTime;
    /**
     * 验证备注
     */
    private String verifyRemark;
    public UserStoreExpBean(Long userId, Byte beSupervisor, Long storeId, String identityNumber) {
        this.userId = userId;
        this.beSupervisor = beSupervisor;
        this.storeId = storeId;
        this.identityNumber = identityNumber;
    }

    public UserStoreExpBean() {
        super();
    }

    public Byte getBeSupervisor() {
        return beSupervisor;
    }

    public void setBeSupervisor(Byte beSupervisor) {
        this.beSupervisor = beSupervisor;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber == null ? null : identityNumber.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Byte getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Byte verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

    public Long getVerifyUserId() {
        return verifyUserId;
    }

    public void setVerifyUserId(Long verifyUserId) {
        this.verifyUserId = verifyUserId;
    }

    public String getVerifyUserName() {
        return verifyUserName;
    }

    public void setVerifyUserName(String verifyUserName) {
        this.verifyUserName = verifyUserName;
    }

    public String getVerifyRemark() {
        return verifyRemark;
    }

    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark;
    }
}