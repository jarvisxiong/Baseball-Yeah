package com.rofour.baseball.service.wallet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.wallet.UserDrawCheckInfo;

public interface WalletDrawService {

	/**
	 * 
	 * @Description: 查询用户提现申请信息
	 * @param thdType
	 * @param state
	 * @param userId
	 * @param checkerId
	 * @param thdFlowId
	 * @param applybgnTime
	 * @param applyendTime
	 * @param checkbgnTime
	 * @param checkendTime
	 * @param arrivebgnTime
	 * @param arriveendTime
	 * @param limit
	 * @param offset
	 * @return
	 */
	List<UserDrawCheckInfo> getUserDrawList(String thdType, String state, String userId, String checkerId,
			String thdFlowId, String applybgnTime, String applyendTime, String checkbgnTime, String checkendTime,
			String arrivebgnTime, String arriveendTime, Integer limit, Integer offset, String sort, String order);

	/**
	 * 
	 * @Description: 查询用户提现申请信息总数
	 * @param thdType
	 * @param state
	 * @param userId
	 * @param checkerId
	 * @param thdFlowId
	 * @param applybgnTime
	 * @param applyendTime
	 * @param checkbgnTime
	 * @param checkendTime
	 * @param arrivebgnTime
	 * @param arriveendTime
	 * @return
	 */
	int getUserDrawListCount(String thdType, String state, String userId, String checkerId, String thdFlowId,
			String applybgnTime, String applyendTime, String checkbgnTime, String checkendTime, String arrivebgnTime,
			String arriveendTime);

	/**
	 * 
	 * @Description: 获取交易类型
	 * @return
	 */
	List<SelectModel> getThdPaymentType();

	/**
	 * 
	 * @Description: 用户提现审核
	 * @param exchangeId
	 * @param isok
	 * @param operationId
	 * @param operationName
	 * @param request
	 * @throws IOException
	 */
	void userDrawCheck(String exchangeId, String isok, String operationId, String operationName, String flowId,
			HttpServletRequest request) throws IOException;

	/**
	 * 
	 * @Description: 用户提现批量审核
	 * @param isok
	 * @param operationId
	 * @param operationName
	 * @param request
	 * @param exchangeId
	 */
	void BatchUserDrawCheck(String isok, String operationId, String operationName, HttpServletRequest request,
			String flowId, String... exchangeId);

	/**
	 * 
	 * @Description: 保存第三方交易流水
	 * @param exchangeId
	 * @param thdId
	 * @param flowId
	 * @param request
	 * @throws IOException
	 */
	void addThdId(String exchangeId, String thdId, String flowId, HttpServletRequest request) throws IOException;
}
