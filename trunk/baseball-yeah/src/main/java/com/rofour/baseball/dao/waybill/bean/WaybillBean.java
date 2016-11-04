package com.rofour.baseball.dao.waybill.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 运单
 * 
 * @author wuzhiquan
 *
 */
public class WaybillBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1273327539038401672L;

    /**
     * 运单编码
     */
    private Long waybillId;

    /**
     * 运单号
     */
    private String waybillNo;

    /**
     * 快递公司编码
     */
    private Long expressCompanyId;

    /**
     * 收款金额
     */
    private BigDecimal freightCollect;

    /**
     * 运单金额
     */
    private BigDecimal paymentOfGoods;

    /**
     * 运单手机号码
     */
    private String phone;

    /**
     * 货位号
     */
    private String position;

    /**
     * 是否签收
     */
    private Byte beSigned;

    /**
     * 签收人
     */
    private String signer;

    /**
     * 签收类型
     */
    private String signType;

    /**
     * 签收时间
     */
    private Date signTime;

    /**
     * 是否虚拟运单
     */
    private Integer beVirtual;

    /**
     * 扫描时间
     */
    private Date addTime;

    /**
     * 寄件货源
     */
    private Long sendExpId;

    /**
     * 寄件门店
     */
    private Integer sendAxpId;

    /**
     * 派件货源
     */
    private Integer dispExpId;

    /**
     * 派件门店
     */
    private Integer dispAxpId;

    /**
     * 状态
     */
    private String state;

    /**
     * 问题状态
     */
    private Integer problemState;

    public Long getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Long waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Long getExpressCompanyId() {
        return expressCompanyId;
    }

    public void setExpressCompanyId(Long expressCompanyId) {
        this.expressCompanyId = expressCompanyId;
    }

    public BigDecimal getFreightCollect() {
        return freightCollect;
    }

    public void setFreightCollect(BigDecimal freightCollect) {
        this.freightCollect = freightCollect;
    }

    public BigDecimal getPaymentOfGoods() {
        return paymentOfGoods;
    }

    public void setPaymentOfGoods(BigDecimal paymentOfGoods) {
        this.paymentOfGoods = paymentOfGoods;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Byte getBeSigned() {
        return beSigned;
    }

    public void setBeSigned(Byte beSigned) {
        this.beSigned = beSigned;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Integer getBeVirtual() {
        return beVirtual;
    }

    public void setBeVirtual(Integer beVirtual) {
        this.beVirtual = beVirtual;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Long getSendExpId() {
        return sendExpId;
    }

    public void setSendExpId(Long sendExpId) {
        this.sendExpId = sendExpId;
    }

    public Integer getSendAxpId() {
        return sendAxpId;
    }

    public void setSendAxpId(Integer sendAxpId) {
        this.sendAxpId = sendAxpId;
    }

    public Integer getDispExpId() {
        return dispExpId;
    }

    public void setDispExpId(Integer dispExpId) {
        this.dispExpId = dispExpId;
    }

    public Integer getDispAxpId() {
        return dispAxpId;
    }

    public void setDispAxpId(Integer dispAxpId) {
        this.dispAxpId = dispAxpId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getProblemState() {
        return problemState;
    }

    public void setProblemState(Integer problemState) {
        this.problemState = problemState;
    }

}
