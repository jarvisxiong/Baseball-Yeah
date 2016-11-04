package com.rofour.baseball.dao.report.mapper;

import com.rofour.baseball.controller.model.report.PacketReportNavInfo;
import com.rofour.baseball.controller.model.report.RecieveOrderRateInfo;
import com.rofour.baseball.dao.report.bean.ActivityRateBean;
import com.rofour.baseball.dao.report.bean.CommentRateBean;
import com.rofour.baseball.dao.report.bean.ReportOrderStatisticsSumBean;

import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named("packetOperationMapper")
public interface PacketOperationMapper {

	/**
	 * 查询小派活跃度列表
	 *
	 * @param @return 参数
	 * @return List<ActivityRateBean>    返回类型
	 */
	List<ActivityRateBean> getActivityRateList(ActivityRateBean bean);

	/**
	 * 查询小派活跃度列表数量
	 *
	 * @param bean
	 * @return
	 */
	Integer getActivityRateListCount(ActivityRateBean bean);

	/**
	 * 查询好评率列表
	 *
	 * @param @return 参数
	 * @return List<ActivityRateBean>    返回类型
	 */
	List<CommentRateBean> getCommentRateList(CommentRateBean bean);

	/**
	 * 查询好评率列表数量
	 *
	 * @param bean
	 * @return
	 */
	Integer getCommentRateListCount(CommentRateBean bean);

    /**
	 *
	 * @Description: 查询接单率列表
	 * @param revieveOrderRate
	 * @return
	 */
	List<RecieveOrderRateInfo> getRecOrderRateList(RecieveOrderRateInfo revieveOrderRate);

	/**
	 *
	 * @Description: 查询接单率列表数量
	 * @param revieveOrderRate
	 * @return
	 */
	Integer getRecOrderRateListCount(RecieveOrderRateInfo revieveOrderRate);
	
	/**
	 * 
	 * @Description: 众包运营导航数据查询Part1
	 * @return
	 */
	PacketReportNavInfo getPacketReportNavPartOne();
	
	/**
	 * 
	 * @Description: 众包运营导航数据查询Part2
	 * @return
	 */
	PacketReportNavInfo getPacketReportNavPartTwo();
	
	/**
	 * 查询最新接单数几条记录
	 * @param paramsMap
	 * @return
	 */
	List<RecieveOrderRateInfo> getReceiveOrderByDate(Map<String, Object> paramsMap);
}
