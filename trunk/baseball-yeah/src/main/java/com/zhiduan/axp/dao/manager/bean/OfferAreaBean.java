/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;



public class OfferAreaBean implements Serializable {

    private static final long serialVersionUID = 3550819637058290029L;
    /*
    *编码
    */
    private Long offerAreaId;

    /*
    *区域名称
    */
    private String offerAreaName;

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
}