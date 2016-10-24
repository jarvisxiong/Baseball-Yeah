/**
 * Copyright (c) 2016, 指端科技.
 */


package com.zhiduan.axp.controller.model.manager;

import com.zhiduan.axp.controller.model.BasePage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class OfferPriceInfo extends BasePage implements Serializable {


    private static final long serialVersionUID = -1098512006343824635L;

    /*
   *编码
   */
    private Long offerAreaId;

    /*
    *区域名称
    */
    private String offerAreaName;

    /*
    *  报价编码
    */
    private Long oapId;

    /*
    *省份编码
    */
    private Long provinceId;

    /*
    *省份编码集合
    */
    private List<Long> provinceIds=new ArrayList<>();

    /*
    *省份名称
    */
    private String provinceNames;

    /*
    *省份id字符串集合
    */
    private String provinceIdsStr;

    public String getProvinceIdsStr() {
        return provinceIdsStr;
    }

    public void setProvinceIdsStr(String provinceIdsStr) {
        this.provinceIdsStr = provinceIdsStr;
    }

    public String getProvinceNames() {
        return provinceNames;
    }

    public void setProvinceNames(String provinceNames) {
        this.provinceNames = provinceNames;
    }

    public List<Long> getProvinceIds() {
        return provinceIds;
    }

    public void setProvinceIds(List<Long> provinceIds) {
        this.provinceIds = provinceIds;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOfferAreaId() {
        return offerAreaId;
    }

    public void setOfferAreaId(Long offerAreaId) {
        this.offerAreaId = offerAreaId;
    }

    public String getOfferAreaName() {
        return offerAreaName;
    }

    public void setOfferAreaName(String offerAreaName) {
        this.offerAreaName = offerAreaName;
    }

    public Long getOapId() {
        return oapId;
    }

    public void setOapId(Long oapId) {
        this.oapId = oapId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public OfferPriceInfo(Long offerAreaId, Long provinceId) {
        this.offerAreaId = offerAreaId;
        this.provinceId = provinceId;
    }

    public OfferPriceInfo() {
    }
}