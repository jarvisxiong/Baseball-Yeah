/**
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.model.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: VoucherInfo
 * @Description: 代金券info类
 * @author chenyu
 * @date 2016年7月19日 上午10:12:35
 */
public class VoucherInfo implements Serializable {

    private static final long serialVersionUID = -7577051122711539717L;

    private Long voucherId;

    private Integer policyId;

    private Long userId;

    private Integer faceValue;

    private Date effectTime;

    private Date expireTime;

    private Date receiveTime;

    private Date useTime;

    private Byte state;

    private String stateStr;

    private String token;

    private Long collegeId;

    private Date endTime;

    private Integer payType;

    private Integer reward;

    private Date sendTime;

    private String phone;

    private String useStateStr;

    private Byte useState;


    public Byte getUseState() {
        return useState;
    }

    public void setUseState(Byte useState) {
        this.useState = useState;
    }

    public String getUseStateStr() {
        return useStateStr;
    }

    public void setUseStateStr(String useStateStr) {
        this.useStateStr = useStateStr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String policyName;

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Integer faceValue) {
        this.faceValue = faceValue;
    }

    /**
     * Gets effect time.
     *
     * @return the effect time
     */
    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }


}
