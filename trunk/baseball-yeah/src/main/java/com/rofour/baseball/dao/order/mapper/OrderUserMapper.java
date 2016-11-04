package com.rofour.baseball.dao.order.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.controller.model.order.OrderInfo;
import com.rofour.baseball.controller.model.order.RequestOrderInfo;
import com.rofour.baseball.dao.order.bean.Order;
import com.rofour.baseball.dao.order.bean.OrderDetailBean;
import com.rofour.baseball.dao.order.bean.OrderStateDetailBean;

@Named("orderUserMapper")
public interface OrderUserMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> queryOrderInfoByPhone(String phone);

    List<Order> queryCollgeListByWatBillNo(String wayBillNo);

    int queryOrderCountByWayBillNo(String wayBillNo);

    /**
     * @return
     * @Description: 获取所有订单信息
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
     * @param orderId
     * @return
     * @Description: 查询订单明细
     */

    OrderDetailBean getOrderDetail(RequestOrderInfo requestOrderInfo);

    /**
     * @param orderId
     * @return
     * @Description: 订单状态变化集合
     */

    List<OrderStateDetailBean> getOrderStateDetail(Long orderId);

    /**
     * @return
     * @Description: 获取今日订单数量
     */

    int getTodayNewOrderTotal();
}