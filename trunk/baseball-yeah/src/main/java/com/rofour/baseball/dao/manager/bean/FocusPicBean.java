package com.rofour.baseball.dao.manager.bean;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016-06-30.
 */
public class FocusPicBean extends BasePage implements Serializable {

    private static final long serialVersionUID = -4675266529071440648L;
    /**
     *
     */
    private Long adId;
    /**
     *标题
     */
    private String adTitle;
    /**
     *图片
     */
    private String adImg;
    /**
     *'广告类型
     */
    private Integer adType;
    /**
     *用户类型
     */
    private Integer userType;
    /**
     *跳转url
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdImg() {
        return adImg;
    }

    public void setAdImg(String adImg) {
        this.adImg = adImg;
    }

    public Integer getAdType() {
        return adType;
    }

    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
