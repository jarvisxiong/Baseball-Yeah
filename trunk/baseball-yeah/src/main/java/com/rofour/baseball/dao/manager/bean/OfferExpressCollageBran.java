package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-08-03.
 */
public class OfferExpressCollageBran  implements Serializable {
    private static final long serialVersionUID = -2375266121071440648L;

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

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    private Long oecId;
    private Long offerId;
    private Long collegeId;
}
