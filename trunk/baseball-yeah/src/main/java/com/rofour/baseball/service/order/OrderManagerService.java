package com.rofour.baseball.service.order;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.order.OrderManagerQueryParamInfo;
import com.rofour.baseball.controller.model.order.PacketUserQueryParamInfo;
import com.rofour.baseball.dao.order.bean.OrderManagerBean;
import com.rofour.baseball.dao.user.bean.PacketUserBean;

import java.util.List;

/**
 * @author wangyu
 * @ClassName:com.rofour.baseball.service.order.OrderManagerService
 * @Description: 描述：
 * @date 2016/10/10 16:20
 */
public interface OrderManagerService {
	/**
	 * 获取订单详情
	 * @param orderId 订单ID
	 * @return 订单信息
	 * @throws Exception
	 */
	ResultInfo getOrderDetail(Long orderId) throws Exception;
	/**
	 * 查询订单列表
	 * @param queryParamInfo 查询参数对象
	 * @return 订单列表
	 */
	List<OrderManagerBean> selectOrderList(OrderManagerQueryParamInfo queryParamInfo) throws Exception;

	/**
	 * 查询订单列表数量
	 * @param queryParamInfo
	 * @return 订单数量
	 * @throws Exception
	 */
	int selectOrderListCount(OrderManagerQueryParamInfo queryParamInfo)throws Exception;
	/**
	 * 查询小派列表
	 * @param queryParamInfo 查询小派参数对象
	 * @return 小派列表
	 */
	List<PacketUserBean> selectPacketUserList(PacketUserQueryParamInfo queryParamInfo)throws Exception;
	/**
	 * 查询小派数量
	 * @param queryParamInfo 查询小派参数对象
	 * @return 小派数量
	 */
	int selectPacketUserListCount(PacketUserQueryParamInfo queryParamInfo)throws Exception;

	/**
	 * 取消订单
	 * @param orderId 订单编号
	 * @param cancelReason 取消原因
	 * @param type 订单类型
	 * @return
	 * @throws Exception
	 */
	ResultInfo cancelOrder(Long orderId,String cancelReason,String type) throws Exception;

	/**
	 * 派单
	 * @param orderIds 订单id列表
	 * @param userIds 用户id列表
	 * @param assignUserId 转单人Id
	 * @return
	 * @throws Exception
	 */
	ResultInfo assignOrder(List<Long> orderIds,List<Long> userIds,Long assignUserId)throws Exception;
}
