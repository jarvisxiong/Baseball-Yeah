package com.rofour.baseball.controller.order;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.order.OrderManagerQueryParamInfo;
import com.rofour.baseball.controller.model.order.PacketUserQueryParamInfo;
import com.rofour.baseball.controller.model.order.StatisticalOrderAmountInfo;
import com.rofour.baseball.dao.order.bean.OrderManagerBean;
import com.rofour.baseball.dao.user.bean.PacketUserBean;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.order.OrderManagerService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * 订单管理
 * @author wangyu
 * @ClassName:com.rofour.baseball.controller.order.OrderManagerController
 * @Description: 描述：
 * @date 2016/10/10 16:08
 */
@Controller
@RequestMapping("/orderMgr")
public class OrderManagerController extends BaseController {
	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(OrderManagerController.class);
	@Autowired
	@Qualifier("orderManagerService")
	private OrderManagerService orderManagerService;

	/**
	 * 跳转到订单管理页面
	 * @return
	 */
	@RequestMapping("/list")
	public String orderManagerList(){

		return "order/manager/list";
	}

	/**
	 * 订单管理列表ajax查询
	 * @param response
	 * @param queryParamInfo
	 */
	@RequestMapping(value = "/ajaxlist",method = RequestMethod.GET)
	@ResponseBody
	public void orderManagerList(HttpServletResponse response, OrderManagerQueryParamInfo queryParamInfo){
		DataGrid<OrderManagerBean> grid=new DataGrid<>();
		try {
			logger.debug("orderManagerList queryParamInfo:{}",JsonUtils.translateToJson(queryParamInfo));
			List collegeIdList = JsonUtils.readValue(queryParamInfo.getColleges(),List.class);
			if(CollectionUtils.isNotEmpty(collegeIdList)){
				queryParamInfo.setCollegeIdList(collegeIdList);
			}
			if(null != queryParamInfo.getOrderState()){
				if( queryParamInfo.getOrderState().intValue() == 1){
					queryParamInfo.setPayState(3);
				}else if(queryParamInfo.getOrderState().intValue() == 100){
					queryParamInfo.setOrderState(1);
					queryParamInfo.setPayState(1);
					queryParamInfo.setPayModel(1);
				}
			}
			List<OrderManagerBean> list = orderManagerService.selectOrderList(queryParamInfo);
			if(CollectionUtils.isNotEmpty(list)){
				for(OrderManagerBean orderManagerBean:list){
					if(orderManagerBean.getPayMode().intValue() == 1 && orderManagerBean.getPayState().intValue() != 3){
						orderManagerBean.setFinalMoney(0l);
					}else if(orderManagerBean.getPayMode().intValue() == 2 && orderManagerBean.getState().intValue() != 5){
						orderManagerBean.setFinalMoney(0l);
					}
				}
			}
			grid.setRows(list);
			grid.setTotal(orderManagerService.selectOrderListCount(queryParamInfo));
		} catch (Exception e) {
			logger.error("orderManagerList is error...",e);
		}
		writeJson(grid, response);
	}

	/**
	 * 跳转到小派用户列表
	 * @param modelMap
	 * @param orderIds
	 * @return
	 */
	@RequestMapping("/topusers")
	public String packetUserList(ModelMap modelMap,@RequestParam(value = "orderIds",required = false) String orderIds,String collegeId){
		modelMap.addAttribute("orderIds",orderIds);
		modelMap.addAttribute("collegeId",collegeId);
		return "order/manager/users";
	}

	/**
	 * 小派用户列表AJAX
	 * @param response
	 * @param queryParamInfo
	 * @param modelMap
	 */
	@RequestMapping("/ajaxpusers")
	@ResponseBody
	public void packetUserList(HttpServletResponse response, PacketUserQueryParamInfo queryParamInfo, ModelMap modelMap){
		DataGrid<PacketUserBean> grid=new DataGrid<>();
		try {
			logger.debug(JsonUtils.translateToJson(queryParamInfo));
			List<PacketUserBean> list = orderManagerService.selectPacketUserList(queryParamInfo);
			grid.setRows(list);
			grid.setTotal(orderManagerService.selectPacketUserListCount(queryParamInfo));
		} catch (Exception e) {
			logger.error("packetUserList is error...",e);
		}
		writeJson(grid, response);
	}

	/**
	 * 根据订单ID 获取订单详情
	 * @param orderId 订单号
	 * @return 订单信息
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public ResultInfo<OrderManagerBean> orderDetail(Long orderId){
		try {
			return orderManagerService.getOrderDetail(orderId);
		} catch (Exception e) {
			logger.error("getOrderDetail is error...",e);
			return new ResultInfo(-1,"","获取订单详情失败");
		}
	}

	/**
	 * 统计订单金额信息
	 * @return 统计信息
	 */
	@RequestMapping(value = "/statistic")
	@ResponseBody
	public ResultInfo<OrderManagerBean> statisticalOrderAmount(OrderManagerQueryParamInfo queryParamInfo){
		try {
			if(null == queryParamInfo){
				queryParamInfo = new OrderManagerQueryParamInfo();
			}
			if(null != queryParamInfo.getOrderState()){
				if( queryParamInfo.getOrderState().intValue() == 1){
					queryParamInfo.setPayState(3);
				}else if(queryParamInfo.getOrderState().intValue() == 100){
					queryParamInfo.setOrderState(1);
					queryParamInfo.setPayState(1);
					queryParamInfo.setPayModel(1);
				}
			}
			//queryParamInfo.setOffset(0);
			//queryParamInfo.setLimit(orderManagerService.selectOrderListCount(queryParamInfo));
			List collegeIdList = JsonUtils.readValue(queryParamInfo.getColleges(),List.class);
			if(CollectionUtils.isNotEmpty(collegeIdList)){
				queryParamInfo.setCollegeIdList(collegeIdList);
			}
			List<OrderManagerBean> stasticList = orderManagerService.selectOrderList(queryParamInfo);
			BigDecimal   b1   =   BigDecimal.ZERO;
			BigDecimal   b2   =   BigDecimal.ZERO;
			BigDecimal   b3   =   BigDecimal.ZERO;
			if(CollectionUtils.isNotEmpty(stasticList)){
				for(OrderManagerBean orderManagerBean:stasticList){
					if(orderManagerBean.getPayState().intValue() != 3 && orderManagerBean.getPayMode().intValue() == 1){
						continue;
					}
					if(orderManagerBean.getPayMode().intValue() == 2 && orderManagerBean.getState().intValue() != 5){
						continue;
					}
					DecimalFormat formater = new DecimalFormat();
					formater.setMaximumFractionDigits(2);
					formater.setGroupingSize(0);
					formater.setRoundingMode(RoundingMode.FLOOR);
					if(null != orderManagerBean.getRewardMoney()){
						BigDecimal bigDecimal = new BigDecimal(orderManagerBean.getRewardMoney()).divide(new BigDecimal("100"));
						b1 = b1.add(new BigDecimal(formater.format(bigDecimal)));
					}
					if(null != orderManagerBean.getFinalMoney()){
						BigDecimal bigDecimal = new BigDecimal(orderManagerBean.getFinalMoney()).divide(new BigDecimal("100"));
						b2 = b2.add(new BigDecimal(formater.format(bigDecimal)));
					}
					if(null != orderManagerBean.getRebateMoney()){
						BigDecimal bigDecimal = new BigDecimal(orderManagerBean.getRebateMoney()).divide(new BigDecimal("100"));
						b3 = b3.add(new BigDecimal(formater.format(bigDecimal)));//String.valueOf(orderManagerBean.getRebateMoney()/100)
					}
				}
			}
			StatisticalOrderAmountInfo statisticalOrderAmountInfo = new StatisticalOrderAmountInfo(b1,b3,b2);
			return new ResultInfo(0,"","获取订单金额统计成功",statisticalOrderAmountInfo);
		} catch (Exception e) {
			logger.error("getOrderDetail is error...",e);
			return new ResultInfo(-1,"","获取订单金额统计失败");
		}
	}
	/**
	 * 取消订单
	 * @return
	 */
	@RequestMapping("/cancelOrder")
	@ResponseBody
	public ResultInfo<OrderManagerBean> cancelOrder(@RequestParam("orderId") Long orderId,@RequestParam("cancelReason")String cancelReason,@RequestParam("type")String type){
		try {
			return orderManagerService.cancelOrder(orderId,cancelReason,type);
		} catch (Exception e) {
			logger.error("getOrderDetail is error...",e);
			return new ResultInfo(-1,"","取消订单失败");
		}
	}
	/**
	 * 派单
	 * @return
	 */
	@RequestMapping("/assignOrder")
	@ResponseBody
	public ResultInfo<OrderManagerBean> assignOrder(@RequestParam("orderIds") String orderIds,@RequestParam("userIds") String userIds, HttpServletRequest request){
		try {
			List orderIdsList = JsonUtils.readValue(orderIds,List.class);
			List userIdsList = JsonUtils.readValue(userIds,List.class);
			UserManagerLoginBean userInfo=(UserManagerLoginBean)request.getSession().getAttribute("user");
			return orderManagerService.assignOrder(orderIdsList,userIdsList,userInfo.getUserManagerId());
		} catch (Exception e) {
			logger.error("getOrderDetail is error...",e);
			return new ResultInfo(-1,"","转单失败");
		}
	}
}
