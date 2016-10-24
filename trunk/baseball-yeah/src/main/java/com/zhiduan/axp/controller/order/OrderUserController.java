package com.zhiduan.axp.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiduan.axp.controller.model.ResultInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.controller.model.order.OrderInfo;
import com.zhiduan.axp.controller.model.order.RequestOrderInfo;
import com.zhiduan.axp.dao.order.bean.OrderDetailBean;
import com.zhiduan.axp.dao.order.bean.OrderStateDetailBean;
import com.zhiduan.axp.dao.wallet.bean.AcctThdPaymentTypeBean;
import com.zhiduan.axp.service.order.OrderUserService;


/**
 * @author zhanglei
 * @ClassName: OrderUserController
 * @Description: 众包收件人控制层
 * @date 2016年6月6日 下午4:58:05
 */
@Controller
@RequestMapping(value = "/order/user")
public class OrderUserController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("orderUserService")
    OrderUserService orderUserService;

    @ResponseBody
    @RequestMapping(value = "/getAll")
    public void getAllOrder(HttpServletRequest request, HttpServletResponse response, RequestOrderInfo requestOrderInfo) {
        requestOrderInfo.setType("send");//寄件
        if (StringUtils.isBlank(requestOrderInfo.getSort())) {
            requestOrderInfo.setSort("createDate");//默认以注册时间排序
            requestOrderInfo.setOrder("desc");//默认倒序
        }
        List<OrderInfo> list = null;
        DataGrid<OrderInfo> grid = new DataGrid<OrderInfo>();
        try {
            list = orderUserService.getAllOrder(requestOrderInfo);
            grid.setRows(list);
            grid.setTotal(orderUserService.getOrderTotal(requestOrderInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        writeJson(grid, response);
    }


    @ResponseBody
    @RequestMapping(value = "/getPacketOrder")
    public void getPacketOrder(HttpServletRequest request, HttpServletResponse response, RequestOrderInfo requestOrderInfo) {
        requestOrderInfo.setType("packet");//派件
        if (StringUtils.isBlank(requestOrderInfo.getSort())) {
            requestOrderInfo.setSort("createDate");//默认以注册时间排序
            requestOrderInfo.setOrder("desc");//默认倒序
        }
        List<OrderInfo> list = null;
        DataGrid<OrderInfo> grid = new DataGrid<OrderInfo>();
        try {
            list = orderUserService.getPacketOrder(requestOrderInfo);
            grid.setRows(list);
            grid.setTotal(orderUserService.getPacketOrderTotal(requestOrderInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        writeJson(grid, response);
    }

    @ResponseBody
    @RequestMapping(value = "/getPickupOrder")
    public void getPickupOrder(HttpServletRequest request, HttpServletResponse response, RequestOrderInfo requestOrderInfo) {
        requestOrderInfo.setType("agent_packet");//代取件
        if (StringUtils.isBlank(requestOrderInfo.getSort())) {
            requestOrderInfo.setSort("createDate");//默认以注册时间排序
            requestOrderInfo.setOrder("desc");//默认倒序
        }
        List<OrderInfo> list = null;
        DataGrid<OrderInfo> grid = new DataGrid<OrderInfo>();
        try {
            list = orderUserService.getPickupOrder(requestOrderInfo);
            grid.setRows(list);
            grid.setTotal(orderUserService.getPickupOrderTotal(requestOrderInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        writeJson(grid, response);
    }

    @RequestMapping(value = "/selectAllPayType", method = RequestMethod.POST)
    @ResponseBody
    public void getAllPayType(HttpServletRequest request, HttpServletResponse response) {
        List<AcctThdPaymentTypeBean> acctThdPaymentTypeBeans = new ArrayList<AcctThdPaymentTypeBean>();
        try {
            acctThdPaymentTypeBeans = orderUserService.getAllPayType();
            List<SelectModel> sellist = new ArrayList<SelectModel>();
            SelectModel sel = new SelectModel();
            sel.setId(" ");
            sel.setText("请选择");
            sellist.add(sel);
            for (AcctThdPaymentTypeBean acctThdPaymentTypeBean : acctThdPaymentTypeBeans) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(acctThdPaymentTypeBean.getTypeCode().toString());
                selectModel.setText(acctThdPaymentTypeBean.getTypeName());
                sellist.add(selectModel);
            }

            writeJson(sellist, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/orderView")
    public OrderDetailBean getOrderView(HttpServletResponse response, RequestOrderInfo requestOrderInfo) {
        OrderDetailBean orderDetailBean = null;
        try {
            orderDetailBean = orderUserService.getOrderView(requestOrderInfo.getOrderId());
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        return orderDetailBean;
    }

    @RequestMapping(value = "/orderView1")
    public void getOrderView1(HttpServletResponse response, RequestOrderInfo requestOrderInfo) {
        OrderDetailBean orderDetailBean = null;
        try {
            orderDetailBean = orderUserService.getOrderView(requestOrderInfo.getOrderId());
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        writeJson(orderDetailBean, response);
    }

    @ResponseBody
    @RequestMapping(value = "/orderDetail")
    public List<OrderStateDetailBean> getOrderDetail(HttpServletResponse response, RequestOrderInfo requestOrderInfo) {
        List<OrderStateDetailBean> orderStateDetails = null;
        try {
            orderStateDetails = orderUserService.getOrderDetail(requestOrderInfo.getOrderId());
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        return orderStateDetails;
    }

}
