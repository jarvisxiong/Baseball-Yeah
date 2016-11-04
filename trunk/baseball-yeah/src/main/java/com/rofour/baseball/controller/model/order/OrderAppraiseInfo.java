package com.rofour.baseball.controller.model.order;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: RequestOrderComment
 * @Description:
 * @author ZXY
 * @date 2016/10/13 11:01
 */
public class OrderAppraiseInfo extends BasePage implements Serializable {

    private static final long serialVersionUID = -1870254614981163410L;

    private Long appraiseId;

    private String orderId;

    private String customerPhone;

    private String packetUserId;

    private String overallScore;

    private String overallDesc;

    private List<String> imgList;

    public Long getAppraiseId() {
        return appraiseId;
    }

    public void setAppraiseId(Long appraiseId) {
        this.appraiseId = appraiseId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getPacketUserId() {
        return packetUserId;
    }

    public void setPacketUserId(String packetUserId) {
        this.packetUserId = packetUserId;
    }

    public String getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(String overallScore) {
        this.overallScore = overallScore;
    }

    public String getOverallDesc() {
        return overallDesc;
    }

    public void setOverallDesc(String overallDesc) {
        this.overallDesc = overallDesc;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}

