package com.rofour.baseball.service.statistic;

import com.rofour.baseball.controller.model.order.OrderAppraiseInfo;
import com.rofour.baseball.controller.model.wallet.RequestAcctFlow;
import com.rofour.baseball.controller.model.order.RequestOrderComment;
import com.rofour.baseball.controller.model.order.RequestOrderStatis;

import java.util.Map;

/**
 * @ClassName: StatisticsService
 * @Description:
 * @author ZXY
 * @date 2016/10/12 14:47
 */
public interface StatisticsService {

    /**
     * @Description: 获取订单量统计明细
     * @param
     * @return
     * @throws
     */
    Map getOrderStatis(RequestOrderStatis requestOrderStatis) throws Exception;

    /**
     * @Description: 获取订单量统计明细
     * @param
     * @return
     * @throws
     */
    Map getOrderComment(RequestOrderComment requestOrderComment) throws Exception;

    /**
     * @Description: 获取订单评价明细
     * @param
     * @return
     * @throws
     */
    OrderAppraiseInfo getOrderCommentDetail(Long appraiseId) throws Exception;

    /**
     * @Description: 获取订单量统计明细
     * @param
     * @return
     * @throws
     */
    Map getAcctFlow(RequestAcctFlow requestAcctFlow) throws Exception;

}
