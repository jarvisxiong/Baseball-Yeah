package com.rofour.baseball.controller.model.order;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: OrderCommentInfo
 * @Description:
 * @author ZXY
 * @date 2016/10/13 11:01
 */
public class OrderCommentInfo implements Serializable {

    private static final long serialVersionUID = 5483398169992624287L;
    /**
     * 评价ID
     */
    private Long appraiseId;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 订单类型
     */
    private Integer orderType;
    /**
     * 下单人手机号
     */
    private String appraiserMobile;
    /**
     * 小派手机号
     */
    private String packetPhone;
    /**
     * 评论时间
     */
    private Date createDate;
    /**
     * 评分
     */
    private Integer overallScore;
    /**
     * 评论内容
     */
    private String overallDesc;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAppraiseId() {
        return appraiseId;
    }

    public void setAppraiseId(Long appraiseId) {
        this.appraiseId = appraiseId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAppraiserMobile() {
        return appraiserMobile;
    }

    public void setAppraiserMobile(String appraiserMobile) {
        this.appraiserMobile = appraiserMobile;
    }

    public String getPacketPhone() {
        return packetPhone;
    }

    public void setPacketPhone(String packetPhone) {
        this.packetPhone = packetPhone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public String getOverallDesc() {
        return overallDesc;
    }

    public void setOverallDesc(String overallDesc) {
        this.overallDesc = overallDesc;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}