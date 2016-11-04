package com.rofour.baseball.dao.order.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.controller.model.order.OrderAppraiseIn;
import com.rofour.baseball.controller.model.order.RequestOrderAppraiseInfo;
import com.rofour.baseball.dao.order.bean.CollegeCEOBean;

@Named("orderAppraiserMapper")
public interface OrderAppraiserMapper {
	
	public List<OrderAppraiseIn>  getOrderAppraise(RequestOrderAppraiseInfo requestOrderAppraiseInfo);
	
	public int getOrderAppraiseTotal(RequestOrderAppraiseInfo requestOrderAppraiseInfo); 
	
	public OrderAppraiseIn getOrderAppraiseDetail(int appraiseId);
	
	public List<Long> getCollegeByCEO(String realName);
	
	public List<CollegeCEOBean> getCollegeCEO();
}
