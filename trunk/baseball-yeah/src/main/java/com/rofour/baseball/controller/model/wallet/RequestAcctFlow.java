package com.rofour.baseball.controller.model.wallet;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;

/**
 * @ClassName: RequestAcctFlow
 * @Description:
 * @author ZXY
 * @date 2016/10/13 18:03
 */
public class RequestAcctFlow extends BasePage implements Serializable {

    private static final long serialVersionUID = -6307241553731190147L;

    private String packetUserId;

    private String startDate;

    private String endDate;

    private String tradeType;

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

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
