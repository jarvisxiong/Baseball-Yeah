package com.rofour.baseball.controller.model.order;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;

/**
 * @ClassName: RequestOrderComment
 * @Description:
 * @author ZXY
 * @date 2016/10/13 11:01
 */
public class RequestOrderComment extends BasePage implements Serializable {

    private static final long serialVersionUID = 7927399245175702860L;

    private Long orderId;

    private String customerPhone;

    private String packetUserId;

    private String commentStartDate;

    private String commentEndDate;

    private String score;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
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

    public String getCommentStartDate() {
        return commentStartDate;
    }

    public void setCommentStartDate(String commentStartDate) {
        this.commentStartDate = commentStartDate;
    }

    public String getCommentEndDate() {
        return commentEndDate;
    }

    public void setCommentEndDate(String commentEndDate) {
        this.commentEndDate = commentEndDate;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

