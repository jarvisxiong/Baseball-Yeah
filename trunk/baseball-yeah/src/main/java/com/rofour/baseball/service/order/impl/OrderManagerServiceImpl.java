package com.rofour.baseball.service.order.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.Constant;
import com.rofour.baseball.common.HttpClientUtils;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.order.OrderManagerQueryParamInfo;
import com.rofour.baseball.controller.model.order.PacketUserQueryParamInfo;
import com.rofour.baseball.dao.order.bean.OrderFlowBean;
import com.rofour.baseball.dao.order.bean.OrderManagerBean;
import com.rofour.baseball.dao.order.mapper.OrderManagerMapper;
import com.rofour.baseball.dao.user.bean.PacketUserBean;
import com.rofour.baseball.service.order.OrderManagerService;
import com.rofour.baseball.service.waybill.WaybillProblemService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单管理相关接口
 * @author wangyu
 * @ClassName:com.rofour.baseball.service.order.impl.OrderManagerServiceImpl
 * @Description: 描述：
 * @date 2016/10/10 16:22
 */
@Service("orderManagerService")
public class OrderManagerServiceImpl implements OrderManagerService {
	private static final Logger logger = LoggerFactory.getLogger(OrderManagerServiceImpl.class);
	@Autowired
	@Qualifier("orderManagerMapper")
	private OrderManagerMapper orderManagerMapper;
	@Autowired
	@Qualifier("waybillProblemService")
	private WaybillProblemService waybillProblemService;

	/**
	 * 获取订单详情
	 * @param orderId 订单ID
	 * @return 订单信息
	 * @throws Exception
	 */
	@Override
	public ResultInfo getOrderDetail(Long orderId) throws Exception {
		if(null == orderId){
			logger.error("getOrderDetail orderId is null");
			return  new ResultInfo(-1,"","订单编号不能为空");
		}
		OrderManagerBean orderManagerBean = orderManagerMapper.selectOrderDetail(orderId);
		if(null == orderManagerBean){
			logger.error("orderManagerMapper.selectOrderDetail is null");
			return new ResultInfo(-1,"","未查询到订单信息");
		}
		List<OrderFlowBean> orderFlowList = orderManagerMapper.selectOrderFlowByOrderId(orderId);
		if(CollectionUtils.isNotEmpty(orderFlowList)){
			orderManagerBean.setOrderFlowList(orderFlowList);
		}
		return new ResultInfo(0,"","查询成功",orderManagerBean);
	}

	/**
	 * 查询订单列表
	 * @param queryParamInfo 查询参数对象
	 * @return 订单列表信息
	 * @throws Exception
	 */
	@Override
	public List<OrderManagerBean> selectOrderList(OrderManagerQueryParamInfo queryParamInfo) throws Exception {
		List<OrderManagerBean> list = orderManagerMapper.selectOrderList(queryParamInfo);
		if(CollectionUtils.isNotEmpty(list)){
			for(OrderManagerBean orderManagerBean:list){
				if((orderManagerBean.getState().intValue() == 1 && orderManagerBean.getPayState().intValue() == 1 && orderManagerBean.getPayMode().intValue() ==1)){
					orderManagerBean.setState(100);
				}
			}
		}
		return list;
	}

	/**
	 * 查询订单列表数量
	 * @param queryParamInfo 查询参数对象
	 * @return 订单数量
	 * @throws Exception
	 */
	@Override
	public int selectOrderListCount(OrderManagerQueryParamInfo queryParamInfo) throws Exception {
		return orderManagerMapper.selectOrderListCount(queryParamInfo);
	}

	/**
	 * 查询小派列表
	 * @param queryParamInfo 查询小派参数对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PacketUserBean> selectPacketUserList(PacketUserQueryParamInfo queryParamInfo) throws Exception {
		return orderManagerMapper.selectPacketUserList(queryParamInfo);
	}

	/**
	 * 查询小派数量
	 * @param queryParamInfo 查询小派参数对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectPacketUserListCount(PacketUserQueryParamInfo queryParamInfo) throws Exception {
		return orderManagerMapper.selectPacketUserListCount(queryParamInfo);
	}

	/**
	 * 取消订单
	 * @param orderId 订单编号
	 * @param cancelReason 取消原因
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultInfo cancelOrder(Long orderId,String cancelReason,String type) throws Exception {
		if(null == orderId){
			return new ResultInfo(-1, "0", "订单编号不能为空", "");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("orderId",orderId);
		map.put("cancelReason",cancelReason);
		String url = Constant.axpurl.get("orderMgrCancelOrder");
		// 定义反序列化 数据格式
		final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
		};
		ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
		// 返回参数赋值
		if (data.getSuccess() < 0) {
			return new ResultInfo(-1, "0", "取消订单失败", "");
		}
		waybillProblemService.cancelWaybillProblem(orderId,type);
		return new ResultInfo(0, "0", "取消订单成功", "");
	}

	/**
	 * 派单
	 * @param orderIds 订单id列表
	 * @param userIds 用户id列表
	 * @param assignUserId 转单人Id
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultInfo assignOrder(List<Long> orderIds,List<Long> userIds,Long assignUserId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("assignUserId",assignUserId);
		map.put("orderIds",orderIds);
		map.put("userIds",userIds);
		String url = Constant.axpurl.get("orderMgrAssignOrder");
		// 定义反序列化 数据格式
		final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
		};
		ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
		// 返回参数赋值
		if (data.getSuccess() < 0) {
			return new ResultInfo(-1, "0", "派单失败", "");
		}
		return new ResultInfo(0, "0", "派单成功", "");
	}
}
