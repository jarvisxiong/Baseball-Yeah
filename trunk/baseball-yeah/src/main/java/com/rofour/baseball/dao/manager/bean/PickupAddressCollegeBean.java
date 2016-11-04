package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;

/**
 * @ClassName: PickupAddressCollegeBean
 * @Description: 取件地址学校关联实体
 * @author: xulang
 * @Date: 2016-08-10 15:25
 */
public class PickupAddressCollegeBean implements Serializable {
    private static final long serialVersionUID = 2280421162765047349L;
    /**
     * 主键
     */
    private Long pacId;
    /**
     * 取件地址id
     */
    private Long pickupAddressId;
    /**
     * 学校id
     */
    private Long collegeId;

    public PickupAddressCollegeBean(Long pacId, Long pickupAddressId, Long collegeId) {
        this.pacId = pacId;
        this.pickupAddressId = pickupAddressId;
        this.collegeId = collegeId;
    }

    public PickupAddressCollegeBean() {
        super();
    }

    public Long getPacId() {
        return pacId;
    }

    public void setPacId(Long pacId) {
        this.pacId = pacId;
    }

    public Long getPickupAddressId() {
        return pickupAddressId;
    }

    public void setPickupAddressId(Long pickupAddressId) {
        this.pickupAddressId = pickupAddressId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }
}