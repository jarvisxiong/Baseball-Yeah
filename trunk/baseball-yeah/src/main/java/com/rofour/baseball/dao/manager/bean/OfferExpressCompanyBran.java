package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-07-07.
 */
public class OfferExpressCompanyBran implements Serializable {

    private static final long serialVersionUID = -2375266521071440648L;

    public Long getOecId() {
        return oecId;
    }

    public void setOecId(Long oecId) {
        this.oecId = oecId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getExpressCompanyId() {
        return expressCompanyId;
    }

    public void setExpressCompanyId(Long expressCompanyId) {
        this.expressCompanyId = expressCompanyId;
    }

    private Long oecId;
    private Long offerId;
    private Long expressCompanyId;
}
