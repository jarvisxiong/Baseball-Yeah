package com.rofour.baseball.service.report;


import com.rofour.baseball.controller.model.report.ActivityRateInfo;
import com.rofour.baseball.controller.model.report.CommentRateInfo;
import com.rofour.baseball.controller.model.report.PacketReportNavInfo;
import com.rofour.baseball.controller.model.report.RecieveOrderRateInfo;
import com.rofour.baseball.dao.report.bean.ReportRegionPuserSumBean;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PacketOperationService
 * @Description: 小派报表业务层
 * @author Zhiying
 * @date 2016年10月13日 上午9:34:58
 *
 */
public interface PacketOperationService {

    /**
     * 查询活跃率列表
     *
     * @param info
     * @return
     */
    List<ActivityRateInfo> getActivityRateList(ActivityRateInfo info);

    /**
     * 查询活跃率列表数量
     * @param info
     * @return
     */
    Integer getActivityRateListCount(ActivityRateInfo info);

    /**
     * 查询好评率列表
     * @param info
     * @return
     */
    List<CommentRateInfo> getCommentRateList(CommentRateInfo info);

    /**
     * 查询好评率列表数量
     * @param info
     * @return
     */
    Integer getCommentRateListCount(CommentRateInfo info);
    /**
     * 查询最近七天全国小派增长量
     * @return
     */
    @SuppressWarnings("rawtypes")
	public Map<String,List> getPuserIncrNum(Integer regionId);
    
    /**
     * 查询最近七天小派总数量
     * @return
     */
    @SuppressWarnings("rawtypes")
	public Map<String,List> getPuserTotalNum(Integer regionId);
    
    /**
     * 查询最近七天运力增长
     * @return
     */
    @SuppressWarnings("rawtypes")
	public Map<String,List> getCapacityIncrNum();
    
    /**
     * 查询最近七天接单率
     * @return
     */
    @SuppressWarnings("rawtypes")
	public Map<String,List> getReceiveOrder();
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
	 * @Description: 众包运营导航数据查询
	 * @return
	 */
    PacketReportNavInfo getPacketReportNav();
    
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
    
}
