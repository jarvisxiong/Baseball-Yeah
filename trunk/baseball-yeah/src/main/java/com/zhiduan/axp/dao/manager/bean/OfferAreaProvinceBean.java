/**
 * Copyright (c) 2016, 指端科技.
 */


package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;


public class OfferAreaProvinceBean implements Serializable {


    private static final long serialVersionUID = 4831929760788995071L;
    /*
      *报价区域编码
     */
    private Long offerAreaId;

    /*
    *  报价编码
    */
    private Long oapId;

    /*
    *省份编码
    */
    private Long provinceId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOfferAreaId() {
        return offerAreaId;
    }

    public void setOfferAreaId(Long offerAreaId) {
        this.offerAreaId = offerAreaId;
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
}