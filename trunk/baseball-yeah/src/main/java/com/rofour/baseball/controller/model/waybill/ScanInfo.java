package com.rofour.baseball.controller.model.waybill;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wny on 2016-06-30.
 */
public class ScanInfo  implements Serializable {

    /**
     * 扫描编号
     */
    private Long scanId;

    /**
     * 运单号
     */
    private String waybillNumber;

    /**
     * 快递公司编码
     */
    private Long expressCompanyId;

    /**
     * 扫描方
     */
    private Long scanSide;

    /**
     * 签收类型
     */
    private String scanType;

    /**
     * 签收时间
     */
    private Date scanTime;

    /**
     * 签收人编号
     */
    private Long scanUserId;

    /**
     * 短息编码
     */
    private Long smsId;

    /**
     * 短信模板编码
     */
    private Long smsModelId;

    /**
     * 入表时间
     */
    private Date addTime;
//#region


    /**
     * 快递公司名称
     */
    private String expressCompanyText;


    //#endregion


    public Long getScanId() {
        return scanId;
    }

    public void setScanId(Long scanId) {
        this.scanId = scanId;
    }

    public String getWaybillNumber() {
        return waybillNumber;
    }

    public void setWaybillNumber(String waybillNumber) {
        this.waybillNumber = waybillNumber;
    }

    public Long getExpressCompanyId() {
        return expressCompanyId;
    }

    public void setExpressCompanyId(Long expressCompanyId) {
        this.expressCompanyId = expressCompanyId;
    }

    public Long getScanSide() {
        return scanSide;
    }

    public void setScanSide(Long scanSide) {
        this.scanSide = scanSide;
    }

    public String getScanType() {
        return scanType;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    public Date getScanTime() {
        return scanTime;
    }

    public void setScanTime(Date scanTime) {
        this.scanTime = scanTime;
    }

    public Long getScanUserId() {
        return scanUserId;
    }

    public void setScanUserId(Long scanUserId) {
        this.scanUserId = scanUserId;
    }

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public Long getSmsModelId() {
        return smsModelId;
    }

    public void setSmsModelId(Long smsModelId) {
        this.smsModelId = smsModelId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getExpressCompanyText() {
        return expressCompanyText;
    }

    public void setExpressCompanyText(String expressCompanyText) {
        this.expressCompanyText = expressCompanyText;
    }

    public ScanInfo() {
    }

    public ScanInfo(String expressCompanyText, Long scanId, String waybillNumber, Long expressCompanyId, Long scanSide, String scanType, Date scanTime, Long scanUserId, Long smsId, Long smsModelId, Date addTime) {
        this.expressCompanyText = expressCompanyText;
        this.scanId = scanId;
        this.waybillNumber = waybillNumber;
        this.expressCompanyId = expressCompanyId;
        this.scanSide = scanSide;
        this.scanType = scanType;
        this.scanTime = scanTime;
        this.scanUserId = scanUserId;
        this.smsId = smsId;
        this.smsModelId = smsModelId;
        this.addTime = addTime;
    }
}
