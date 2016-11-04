package com.rofour.baseball.dao.manager.bean;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016-07-06.
 */
public class OfferBean  extends BasePage implements Serializable {

    private static final long serialVersionUID = -4675266521071440648L;
    /**
     *
     */
    private Long offerId;

    private  String companyName;

    private  String companyIds;

    private  String collageIds;

    public String getCollageIds() {
        return collageIds;
    }

    public void setCollageIds(String collageIds) {
        this.collageIds = collageIds;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(String companyIds) {
        this.companyIds = companyIds;
    }

    public List<Long> getEcList() {
        return ecList;
    }

    public void setEcList(List<Long> ecList) {
        this.ecList = ecList;
    }

    /**
     * 快递公司编码集合
     */
    private List<Long> ecList;

    /**
     * 学校集合
     */
    private List<Long> collageList;

    public List<Long> getCollageList() {
        return collageList;
    }

    public void setCollageList(List<Long> collageList) {
        this.collageList = collageList;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public Integer getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(Integer initialWeight) {
        this.initialWeight = initialWeight;
    }

    public Integer getAdditionalWeight() {
        return additionalWeight;
    }

    public void setAdditionalWeight(Integer additionalWeight) {
        this.additionalWeight = additionalWeight;
    }

    public Long getSendAreaId() {
        return sendAreaId;
    }

    public void setSendAreaId(Long sendAreaId) {
        this.sendAreaId = sendAreaId;
    }

    public Long getDispAreaId() {
        return dispAreaId;
    }

    public void setDispAreaId(Long dispAreaId) {
        this.dispAreaId = dispAreaId;
    }

    public Byte getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Byte isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSendProvinceName() {
        return sendProvinceName;
    }

    public void setSendProvinceName(String sendProvinceName) {
        this.sendProvinceName = sendProvinceName;
    }

    public String getDispProvinceName() {
        return dispProvinceName;
    }

    public void setDispProvinceName(String dispProvinceName) {
        this.dispProvinceName = dispProvinceName;
    }

    public String getSendArearName() {
        return sendArearName;
    }

    public void setSendArearName(String sendArearName) {
        this.sendArearName = sendArearName;
    }

    public String getDispAreaName() {
        return dispAreaName;
    }

    public void setDispAreaName(String dispAreaName) {
        this.dispAreaName = dispAreaName;
    }

    /**
     *报价名称
     */

    private String offerName;
    /**
     *首重费用
     */
    private Integer initialWeight;
    /**
     *续重费用
     */
    private Integer additionalWeight;
    /**
     *寄件人区域
     */
    private Long sendAreaId ;
    /**
     *收件人区域
     */
    private Long dispAreaId ;

    /**
     *是否启用
     */
    private Byte isEnabled;
    /**
     *是否删除
     */
    private Byte isDeleted;
    /**
     *排序
     */
    private  Integer sortNo;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *寄件省份
     */
    private String sendProvinceName;
    /**
     *派件省份
     */
    private String dispProvinceName;
    /**
     *寄件人区域
     */
    private String sendArearName;
    /**
     *收件人区域
     */
    private String dispAreaName;
}
