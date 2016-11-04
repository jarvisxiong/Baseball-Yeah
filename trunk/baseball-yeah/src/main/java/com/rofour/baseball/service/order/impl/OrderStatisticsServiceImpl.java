package com.rofour.baseball.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.controller.model.order.OrderAppraiseIn;
import com.rofour.baseball.controller.model.order.OrderStatisticsInfo;
import com.rofour.baseball.controller.model.order.RequestOrderAppraiseInfo;
import com.rofour.baseball.controller.model.order.RequestOrderStatisticsInfo;
import com.rofour.baseball.dao.order.bean.CollegeCEOBean;
import com.rofour.baseball.dao.order.mapper.OrderAppraiserMapper;
import com.rofour.baseball.dao.report.mapper.OrderStatisticsMapper;
import com.rofour.baseball.service.order.OrderStatisticsService;

@Service("orderStatisticsService")
public class OrderStatisticsServiceImpl implements OrderStatisticsService{

	@Autowired
	@Qualifier("orderStatisticsMapper")
	OrderStatisticsMapper orderStatisticsMapper;
	
	@Autowired
	@Qualifier("orderAppraiserMapper")
	OrderAppraiserMapper orderAppraiserMapper;
	
	
	/**
	 * 
	 * @Description: 查询评价订单
	 * @param requestOrderAppraiseInfo
	 * @return 
	 * @see com.rofour.baseball.service.order.OrderStatisticsService#getOrderAppraise(com.rofour.baseball.controller.model.order.RequestOrderAppraiseInfo)
	 */
	@Override
	public List<OrderAppraiseIn> getOrderAppraise(RequestOrderAppraiseInfo requestOrderAppraiseInfo) {
		return orderAppraiserMapper.getOrderAppraise(requestOrderAppraiseInfo);
	}
	/**
	 * 
	 * @Description: 统计评价订单个数
	 * @param requestOrderAppraiseInfo
	 * @return 
	 * @see com.rofour.baseball.service.order.OrderStatisticsService#getOrderAppraiseTotal(com.rofour.baseball.controller.model.order.RequestOrderAppraiseInfo)
	 */
	@Override
	public int getOrderAppraiseTotal(RequestOrderAppraiseInfo requestOrderAppraiseInfo) {
		return orderAppraiserMapper.getOrderAppraiseTotal(requestOrderAppraiseInfo);
	}
	
	/**
	 * 
	 * @Description: 查询统计订单
	 * @param requestOrderStatisticsInfo
	 * @return 
	 * @see com.rofour.baseball.service.order.OrderStatisticsService#getStatisticsOrder(com.rofour.baseball.controller.model.order.RequestOrderStatisticsInfo)
	 */
	@Override
	public List<OrderStatisticsInfo> getStatisticsOrder(RequestOrderStatisticsInfo requestOrderStatisticsInfo) {
		//默认只显示昨天的数据
		/*if((requestOrderStatisticsInfo.getCreateStartDate() == null || "".equals(requestOrderStatisticsInfo.getCreateStartDate())) && (requestOrderStatisticsInfo.getCreateEndDate() == null ||  "".equals(requestOrderStatisticsInfo.getCreateEndDate()))){
			Date as = new Date(new Date().getTime()-24*60*60*1000);
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
			String createDate = matter.format(as);
			requestOrderStatisticsInfo.setCreateDate(createDate);	
		}*/
		//默认显示最新数据
		if((requestOrderStatisticsInfo.getCreateStartDate() == null || "".equals(requestOrderStatisticsInfo.getCreateStartDate())) && (requestOrderStatisticsInfo.getCreateEndDate() == null ||  "".equals(requestOrderStatisticsInfo.getCreateEndDate()))){
			String maxDay = orderStatisticsMapper.getMaxDay();
			requestOrderStatisticsInfo.setCreateDate(maxDay);
		}
		List<String> collegess = new ArrayList<String>();
		if(requestOrderStatisticsInfo.getCollegeList() !=null && !"".equals(requestOrderStatisticsInfo.getCollegeList())){
			collegess.addAll(Arrays.asList(requestOrderStatisticsInfo.getCollegeList().split(",")));
		}
		//校园CEO
		//List<Long> collegeIdList = requestOrderStatisticsInfo.getCollegeId();
		List<Long> collegeIdList = new ArrayList<Long>();
		/*if(requestOrderStatisticsInfo.getCollegeCEO() != null&& !" ".equals(requestOrderStatisticsInfo.getCollegeCEO()) && !"".equals(requestOrderStatisticsInfo.getCollegeCEO())){
			collegeID =Long.parseLong(requestOrderStatisticsInfo.getCollegeCEO());
		}*/
		if(" ".equals(requestOrderStatisticsInfo.getCollegeCEO()))
		{
			requestOrderStatisticsInfo.setCollegeCEO("");
		}
		if(collegess.size()>0){
			//如果校园ceo不为空，判断该校园ceo所属学校是否在所选的学校内
			
			for(int i=0;i<collegess.size();i++){
				String collegeId = collegess.get(i);
				collegeIdList.add(Long.parseLong(collegeId));
			}
		}
		if(collegeIdList.size()>0){
			StringBuffer colleges = new StringBuffer();
			colleges.append("(");
			for(int i=0;i<collegeIdList.size();i++){
				if(i !=collegeIdList.size()-1 ){
					colleges.append(collegeIdList.get(i)).append(",");
				}
				else{
					colleges.append(collegeIdList.get(i));
				}
			}
			colleges.append(")");
			requestOrderStatisticsInfo.setColleges(colleges.toString());
		}
		return orderStatisticsMapper.getStatisticsOrder(requestOrderStatisticsInfo);
	}
	
	/**
	 * 
	 * @Description: 订单总数
	 * @param requestOrderStatisticsInfo
	 * @return 
	 * @see com.rofour.baseball.service.order.OrderStatisticsService#getStatisticsOrderTotal(com.rofour.baseball.controller.model.order.RequestOrderStatisticsInfo)
	 */
	@Override
	public int getStatisticsOrderTotal(RequestOrderStatisticsInfo requestOrderStatisticsInfo) {
		//默认只显示昨天的数据
		/*if((requestOrderStatisticsInfo.getCreateStartDate() == null || "".equals(requestOrderStatisticsInfo.getCreateStartDate())) && (requestOrderStatisticsInfo.getCreateEndDate() == null ||  "".equals(requestOrderStatisticsInfo.getCreateEndDate()))){
			Date as = new Date(new Date().getTime()-24*60*60*1000);
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
			String createDate = matter.format(as);
			requestOrderStatisticsInfo.setCreateDate(createDate);	
		}*/
		//默认显示最新数据
		if((requestOrderStatisticsInfo.getCreateStartDate() == null || "".equals(requestOrderStatisticsInfo.getCreateStartDate())) && (requestOrderStatisticsInfo.getCreateEndDate() == null ||  "".equals(requestOrderStatisticsInfo.getCreateEndDate()))){
			String maxDay = orderStatisticsMapper.getMaxDay();
			requestOrderStatisticsInfo.setCreateDate(maxDay);
		}
		List<String> collegess = new ArrayList<String>();
		if(requestOrderStatisticsInfo.getCollegeList() !=null && !"".equals(requestOrderStatisticsInfo.getCollegeList())){
			collegess.addAll(Arrays.asList(requestOrderStatisticsInfo.getCollegeList().split(",")));
		}
		//校园CEO
		//List<Long> collegeIdList = requestOrderStatisticsInfo.getCollegeId();
		List<Long> collegeIdList = new ArrayList<Long>();
		/*if(requestOrderStatisticsInfo.getCollegeCEO() != null&& !" ".equals(requestOrderStatisticsInfo.getCollegeCEO()) && !"".equals(requestOrderStatisticsInfo.getCollegeCEO())){
			collegeID =Long.parseLong(requestOrderStatisticsInfo.getCollegeCEO());
		}*/
		if(" ".equals(requestOrderStatisticsInfo.getCollegeCEO()))
		{
			requestOrderStatisticsInfo.setCollegeCEO("");
		}
		if(collegess.size()>0){
			//如果校园ceo不为空，判断该校园ceo所属学校是否在所选的学校内
			
			for(int i=0;i<collegess.size();i++){
				String collegeId = collegess.get(i);
				collegeIdList.add(Long.parseLong(collegeId));
			}
		}
		if(collegeIdList.size()>0){
			StringBuffer colleges = new StringBuffer();
			colleges.append("(");
			for(int i=0;i<collegeIdList.size();i++){
				if(i !=collegeIdList.size()-1 ){
					colleges.append(collegeIdList.get(i)).append(",");
				}
				else{
					colleges.append(collegeIdList.get(i));
				}
			}
			colleges.append(")");
			requestOrderStatisticsInfo.setColleges(colleges.toString());
		}
		return orderStatisticsMapper.getStatisticsOrderTotal(requestOrderStatisticsInfo);
	}
	
	/**
	 * 
	 * @Description: 评价详情
	 * @param orderAppraiseInfo
	 * @return 
	 * @see com.rofour.baseball.service.order.OrderStatisticsService#getOrderAppraiseDetail(com.rofour.baseball.controller.model.order.OrderAppraiseIn)
	 */
	@Override
	public OrderAppraiseIn getOrderAppraiseDetail(OrderAppraiseIn orderAppraiseInfo) {
		return orderAppraiserMapper.getOrderAppraiseDetail(orderAppraiseInfo.getAppraiseId());
	}
	@Override
	public List<CollegeCEOBean> getCollegeCEO() {
		return orderAppraiserMapper.getCollegeCEO();
	}

}
