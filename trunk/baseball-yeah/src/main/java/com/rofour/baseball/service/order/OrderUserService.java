package com.rofour.baseball.service.order;

import java.util.List;

import com.rofour.baseball.controller.model.order.OrderInfo;
import com.rofour.baseball.controller.model.order.RequestOrderInfo;
import com.rofour.baseball.dao.order.bean.OrderDetailBean;
import com.rofour.baseball.dao.order.bean.OrderStateDetailBean;
import com.rofour.baseball.dao.wallet.bean.AcctThdPaymentTypeBean;


public interface OrderUserService {

    /**
     * @param requestOrderInfo
     * @return
     * @Description: 获取订单
     */

    List<OrderInfo> getAllOrder(RequestOrderInfo requestOrderInfo);

    List<OrderInfo> getPacketOrder(RequestOrderInfo requestOrderInfo);

    List<OrderInfo> getPickupOrder(RequestOrderInfo requestOrderInfo);

    /**
     * @param requestOrderInfo
     * @return
     * @Description: 获取订单总数
     */

    Integer getOrderTotal(RequestOrderInfo requestOrderInfo);

    Integer getPacketOrderTotal(RequestOrderInfo requestOrderInfo);

    Integer getPickupOrderTotal(RequestOrderInfo requestOrderInfo);

    /**
     * @return
     * @Description: 获取所有三方支付类型
     */

    List<AcctThdPaymentTypeBean> getAllPayType();

    /**
     * @param orderId
     * @return
     * @Description: 获取订单详情
     */

    OrderDetailBean getOrderView(RequestOrderInfo requestOrderInfo);

    /**
     * @param orderId
     * @return
     * @Description: 查询订单明细
     */
    List<OrderStateDetailBean> getOrderDetail(Long orderId);


}
