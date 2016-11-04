package com.rofour.baseball.dao.report.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.controller.model.order.OrderStatisticsInfo;
import com.rofour.baseball.controller.model.order.RequestOrderStatisticsInfo;

@Named("orderStatisticsMapper")
public interface OrderStatisticsMapper {
	
	public List<OrderStatisticsInfo> getStatisticsOrder(RequestOrderStatisticsInfo requestOrderStatisticsInfo);
	
	public int getStatisticsOrderTotal(RequestOrderStatisticsInfo requestOrderStatisticsInfo);
	
	public String getMaxDay();
	
}
