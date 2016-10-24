package com.zhiduan.axp.service.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.controller.model.order.OrderInfo;
import com.zhiduan.axp.controller.model.order.RequestOrderInfo;
import com.zhiduan.axp.dao.order.bean.OrderDetailBean;
import com.zhiduan.axp.dao.order.bean.OrderStateDetailBean;
import com.zhiduan.axp.dao.order.mapper.OrderUserMapper;
import com.zhiduan.axp.dao.wallet.bean.AcctThdPaymentTypeBean;
import com.zhiduan.axp.dao.wallet.mapper.AcctThdPaymentTypeMapper;
import com.zhiduan.axp.service.order.OrderUserService;

/**
 * @author ZhangLei
 * @ClassName: OrderVerifyServiceImpl
 * @Description: 众包审核服务实现
 * @date 2016年5月26日 下午5:19:39
 */

@Service("orderUserService")
public class OrderUserServiceImpl implements OrderUserService {

    /**
     * 注入订单数据连接
     */
        
    @Autowired
    @Qualifier("orderUserMapper")
    OrderUserMapper orderUserMapper;
    
    /**
     * 注入三方账户类型
     */
    @Resource(name="acctThdPaymentTypeMapper")
    AcctThdPaymentTypeMapper acctThdPaymentTypeMapper;

    /**
     * @Description: 获取订单信息
     * @param requestOrderInfo
     * @return 
     * @see com.zhiduan.axp.service.order.OrderUserService#getAllOrder(com.zhiduan.axp.controller.model.order.RequestOrderInfo) 
     */
    @Override
    public List<OrderInfo> getAllOrder(RequestOrderInfo requestOrderInfo){
    	List<OrderInfo> orderInfos=orderUserMapper.getAllOrder(requestOrderInfo);
		return orderInfos;
    }
    public List<OrderInfo> getPacketOrder(RequestOrderInfo requestOrderInfo){
        List<OrderInfo> orderInfos=orderUserMapper.getPacketOrder(requestOrderInfo);
        return orderInfos;
    }

    @Override
    public List<OrderInfo> getPickupOrder(RequestOrderInfo requestOrderInfo) {
        List<OrderInfo> orderInfos=orderUserMapper.getPickupOrder(requestOrderInfo);
        return orderInfos;
    }

    /**
     * @Description: 获取订单总数
     * @param requestOrderInfo
     * @return 
     * @see com.zhiduan.axp.service.order.OrderUserService#getOrderTotal(com.zhiduan.axp.controller.model.order.RequestOrderInfo) 
     */
    @Override
    public  Integer getOrderTotal(RequestOrderInfo requestOrderInfo) {
        return orderUserMapper.getOrderTotal(requestOrderInfo);
    }

    public  Integer getPacketOrderTotal(RequestOrderInfo requestOrderInfo) {
        return orderUserMapper.getPacketOrderTotal(requestOrderInfo);
    }

    @Override
    public Integer getPickupOrderTotal(RequestOrderInfo requestOrderInfo) {
        return orderUserMapper.getPickupOrderTotal(requestOrderInfo);
    }

    /**
     * @Description: 获取所有三方支付类型
     * @return 
     * @see com.zhiduan.axp.service.order.OrderUserService#getAllPayType() 
     */
    @Override
    public List<AcctThdPaymentTypeBean> getAllPayType(){
    	List<AcctThdPaymentTypeBean> acctThdPaymentTypeBeans=acctThdPaymentTypeMapper.getAllPayType();
		return acctThdPaymentTypeBeans;
    }
    
    /**
     * @Description: 获取订单详情
     * @param orderId
     * @return 
     * @see com.zhiduan.axp.service.order.OrderUserService#getOrderDetail(java.lang.Long) 
     */
    @Override
    public OrderDetailBean getOrderView(Long orderId){
    	return orderUserMapper.getOrderDetail(orderId);
    }
    
    
    /**
     * @Description: 查询订单明细
     * @param orderId
     * @return 
     * @see com.zhiduan.axp.service.order.OrderUserService#getOrderView(java.lang.Long) 
     */
    @Override
    public List<OrderStateDetailBean>  getOrderDetail(Long orderId){
    	return orderUserMapper.getOrderStateDetail(orderId);
    }
}
