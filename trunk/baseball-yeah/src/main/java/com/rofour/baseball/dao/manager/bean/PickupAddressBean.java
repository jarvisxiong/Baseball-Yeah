package com.rofour.baseball.dao.manager.bean;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: PickupAddressBean
 * @Description: 代取件取件地址实体
 * @author: xulang
 * @Date: 2016-08-09 13:34
 */
public class PickupAddressBean extends BasePage implements Serializable {
    private static final long serialVersionUID = 3340426463827603319L;
    /**
     * 主键
     */
    private Long pickupAddressId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 客户手机号
     */
    private String phone;
    /**
     * 商户名称
     */
    private String storeName;
    /**
     * 商户名称
     */
    private String storePhone;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 省份id
     */
    private Long provinceId;
    /**
     * 城市id
     */
    private Long cityId;
    /**
     * 区id
     */
    private Long countyId;
    /**
     * 是否是系统地址，0:用户地址，1:系统地址
     */
    private Byte isSystem;
    /**
     * 是否是删除，0:否，1:是
     */
    private Byte isDeleted;
    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 学校id
     */
    private String collegeIds;

    /**
     * 学校名称
     */
    private String collegeNames;

    public Long getPickupAddressId() {
        return pickupAddressId;
    }

    public void setPickupAddressId(Long pickupAddressId) {
        this.pickupAddressId = pickupAddressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    public Byte getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Byte isSystem) {
        this.isSystem = isSystem;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCollegeIds() {
        return collegeIds;
    }

    public void setCollegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
    }

    public String getCollegeNames() {
        return collegeNames;
    }

    public void setCollegeNames(String collegeNames) {
        this.collegeNames = collegeNames;
    }
}