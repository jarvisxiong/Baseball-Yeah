package com.rofour.baseball.dao.store.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class CustomAxpBean implements Serializable {

    private static final long serialVersionUID = 3875372445260594654L;

    /**
     * 门店id
     */
    private long storeId;
    /**
     * 商户编号
     */
    private String storeCode;
    /**
     * 商户名称
     */
    private String storeName;
    /**
     * 门店类型,1 站点 2 axp门店
     */
    private int type;
    /**
     * 状态 1:启用 ,0:禁用
     */
    private int status;
    /**
     * 负责人名字
     */
    private String supervisorName;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 地址
     */
    private String location;
    /**
     * 快递站点编码集合
     */
    private List<Long> expList;
    /**
     * 关联学校编码集合
     */
    private List<Long> colList;
    /**
     * 快递公司编码集合
     */
    private List<Long> ecList;
    /**
     * 营业执照
     */
    private String licence;

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Long> getExpList() {
        return expList;
    }

    public void setExpList(List<Long> expList) {
        this.expList = expList;
    }

    public List<Long> getColList() {
        return colList;
    }

    public void setColList(List<Long> colList) {
        this.colList = colList;
    }

    public List<Long> getEcList() {
        return ecList;
    }

    public void setEcList(List<Long> ecList) {
        this.ecList = ecList;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    @Override
    public String toString() {
        return "CustomAxpBean{" +
                "storeId=" + storeId +
                ", storeCode='" + storeCode + '\'' +
                ", storeName='" + storeName + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", supervisorName='" + supervisorName + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", expList=" + expList +
                ", colList=" + colList +
                ", ecList=" + ecList +
                ", licence='" + licence + '\'' +
                '}';
    }
}
