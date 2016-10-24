package com.zhiduan.axp.dao.order.bean;

/**
 * Created by Administrator on 2016-07-04.
 */
public class OrderStatiscs {
    private Integer OrderCout;
    private Integer FinalMoney;
    private String DateTimeString;

    public String getDateTimeString() {
        return DateTimeString;
    }

    public void setDateTimeString(String dateTimeString) {
        DateTimeString = dateTimeString;
    }

    public Integer getOrderCout() {
        return OrderCout;
    }

    public void setOrderCout(Integer orderCout) {
        OrderCout = orderCout;
    }

    public Integer getFinalMoney() {
        return FinalMoney;
    }

    public void setFinalMoney(Integer finalMoney) {
        FinalMoney = finalMoney;
    }
}
