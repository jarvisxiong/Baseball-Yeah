package com.rofour.baseball.service.order;

import java.util.List;

import com.rofour.baseball.controller.model.order.OrderAppraiseIn;
import com.rofour.baseball.controller.model.order.OrderStatisticsInfo;
import com.rofour.baseball.controller.model.order.RequestOrderAppraiseInfo;
import com.rofour.baseball.controller.model.order.RequestOrderStatisticsInfo;
import com.rofour.baseball.dao.order.bean.CollegeCEOBean;

public interface OrderStatisticsService {
	
	public List<OrderStatisticsInfo> getStatisticsOrder(RequestOrderStatisticsInfo requestOrderStatisticsInfo);
	
	public int getStatisticsOrderTotal(RequestOrderStatisticsInfo requestOrderStatisticsInfo);
	
	public List<OrderAppraiseIn>  getOrderAppraise(RequestOrderAppraiseInfo requestOrderAppraiseInfo);
	
	public int getOrderAppraiseTotal(RequestOrderAppraiseInfo requestOrderAppraiseInfo);
	
	public OrderAppraiseIn getOrderAppraiseDetail(OrderAppraiseIn orderAppraiseInfo);
	
	public List<CollegeCEOBean> getCollegeCEO();

}
