package com.zhiduan.axp.controller.model.manager;

import java.io.Serializable;

/**
 * @ClassName: PickupAddressCollegeInfo
 * @Description: 取件地址学校关联实体
 * @author: xulang
 * @Date: 2016-08-10 15:23
 */
public class PickupAddressCollegeInfo implements Serializable {
    private static final long serialVersionUID = -7280745031660686653L;
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

    public PickupAddressCollegeInfo(Long pacId, Long pickupAddressId, Long collegeId) {
        this.pacId = pacId;
        this.pickupAddressId = pickupAddressId;
        this.collegeId = collegeId;
    }

    public PickupAddressCollegeInfo() {
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