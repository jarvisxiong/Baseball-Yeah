package com.rofour.baseball.dao.report.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.dao.report.bean.ReportOrderStatisticsSumBean;
/**
 * (全国)订单运营统计分析（所有校区按天）：订单运营统计分析,订单量监控,订单运力匹配监控,代取代寄
 * @author zhoujie
 *
 */
@Named("reportOrderStatisticsSumMapper")
public interface ReportOrderStatisticsSumMapper {

	/**
	 * 根据时间查询和区域查询
	 * @return
	 */
	List<ReportOrderStatisticsSumBean> getOrderStatisticsNumByDate(Map<String, Object> paramsMap);
	
	
}
