package com.rofour.baseball.dao.order.mapper;

import com.rofour.baseball.controller.model.order.*;
import com.rofour.baseball.controller.model.wallet.AcctFlowInfo;
import com.rofour.baseball.controller.model.wallet.RequestAcctFlow;

import javax.inject.Named;
import java.util.List;

/**
* @ClassName: ResourceMapper
* @Description: 
* @author ZXY
* @date 2016年3月29日 上午10:47:56 
*/
@Named("statisticsMapper")
public interface StatisticsMapper {

	/**
	 * @Description: 查询订单统计列表
	 * @param orderStatis
	 * @return
	 */
	List<OrderStatisInfo> getOrderStatis(RequestOrderStatis orderStatis);

    /**
     * @Description: 查询订单统计列表数量
     * @param orderStatis
     * @return
     */
    int getOrderStatisCount(RequestOrderStatis orderStatis);

    /**
     * @Description: 查询订单评论列表
     * @param orderComment
     * @return
     */
    List<OrderCommentInfo> getOrderComment(RequestOrderComment orderComment);

    /**
     * @Description: 查询订单评论列表数量
     * @param orderComment
     * @return
     */
    int getOrderCommentCount(RequestOrderComment orderComment);

    /**
     * @Description: 查询订单评论详情
     * @param appraiseId
     * @return
     */
    OrderAppraiseInfo getOrderCommentDetail(Long appraiseId);

    /**
     * @Description: 查询账户流水列表
     * @param requestAcctFlow
     * @return
     */
    List<AcctFlowInfo> getAcctFlow(RequestAcctFlow requestAcctFlow);

    /**
     * @Description: 查询账户流水数量
     * @param requestAcctFlow
     * @return
     */
    int getAcctFlowCount(RequestAcctFlow requestAcctFlow);
}
