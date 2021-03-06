package com.rofour.baseball.dao.report.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

/**
 * 订单运营统计分析
 * 
 * @author wuzhiquan
 *
 */
@Named("orderAnalysisMapper")
public interface OrderAnalysisMapper {
    /**
     * 订单统计分析
     * 
     * @param day
     * @return
     * @author wuzhiquan
     */
    Map<String, Object> getOrder(String day);

    /**
     * 各时段订单统计分析
     * 
     * @param day
     * @return
     * @author wuzhiquan
     */
    List<Map<String, Object>> getOrderByHours(String day);

    /**
     * 前10天订单金额统计分析
     * 
     * @param day
     * @return
     * @author wuzhiquan
     */
    List<Map<String, Object>> getOrderAmountByTenDay(String day);

    /**
     * 前10天订单类型统计分析
     * 
     * @param day
     * @return
     * @author wuzhiquan
     */
    List<Map<String, Object>> getOrderTypeByTenDay(String day);

    /**
     * 各地区订单统计分析
     * 
     * @param day
     * @return
     * @author wuzhiquan
     */
    List<Map<String, Object>> getOrderByAreas(String day);

    /**
     * 前10天订单小派统计分析
     * 
     * @param day
     * @return
     * @author wuzhiquan
     */
    List<Map<String, Object>> getOrderWinnerByTenDay(String day);
}
