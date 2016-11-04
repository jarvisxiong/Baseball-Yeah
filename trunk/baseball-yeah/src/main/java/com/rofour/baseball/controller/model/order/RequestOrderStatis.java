package com.rofour.baseball.controller.model.order;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;

/**
 * @ClassName: RequestOrderStatis
 * @Description:
 * @author ZXY
 * @date 2016/10/12 16:25
 */
public class RequestOrderStatis extends BasePage implements Serializable {

    private static final long serialVersionUID = 1097431485902947785L;

    private Long orderId;

    private String packetUserId;

    private String startDate;

    private String endDate;

    private String updStartDate;

    private String updEndDate;

    private String payMode;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPacketUserId() {
        return packetUserId;
    }

    public void setPacketUserId(String packetUserId) {
        this.packetUserId = packetUserId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUpdStartDate() {
        return updStartDate;
    }

    public void setUpdStartDate(String updStartDate) {
        this.updStartDate = updStartDate;
    }

    public String getUpdEndDate() {
        return updEndDate;
    }

    public void setUpdEndDate(String updEndDate) {
        this.updEndDate = updEndDate;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }
}
