package com.rofour.baseball.service.report.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.rofour.baseball.common.CollectionUtils;
import com.rofour.baseball.common.DateTimeUtils;
import com.rofour.baseball.common.StringUtils;
import com.rofour.baseball.controller.model.report.ActivityRateInfo;
import com.rofour.baseball.controller.model.report.CommentRateInfo;
import com.rofour.baseball.controller.model.report.PacketReportNavInfo;
import com.rofour.baseball.controller.model.report.RecieveOrderRateInfo;
import com.rofour.baseball.dao.report.bean.ActivityRateBean;
import com.rofour.baseball.dao.report.bean.CommentRateBean;
import com.rofour.baseball.dao.report.bean.ReportOrderStatisticsSumBean;
import com.rofour.baseball.dao.report.bean.ReportRegionPuserSumBean;
import com.rofour.baseball.dao.report.mapper.PacketOperationMapper;
import com.rofour.baseball.dao.report.mapper.ReportOrderStatisticsSumMapper;
import com.rofour.baseball.dao.report.mapper.ReportRegionPuserSumMapper;
import com.rofour.baseball.service.report.PacketOperationService;

@Service("packetOperationService")
public class PacketOperationServiceImpl implements PacketOperationService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier(value = "packetOperationMapper")
    private PacketOperationMapper packetOperationMapper;

    @Resource(name = "reportRegionPuserSumMapper")
    private ReportRegionPuserSumMapper reportRegionPuserSumMapper;
    @Resource(name = "reportOrderStatisticsSumMapper")
    private ReportOrderStatisticsSumMapper reportOrderStatisticsSumMapper;
    //查询一周
    public final static int SEARCH_WEEK = -7;
    //查询昨天
    public final static int SEARCH_YESTERDAY = -1;

    /**
     * 查询活跃率列表
     *
     * @param info
     * @return
     * @see PacketOperationService#getActivityRateList(ActivityRateInfo)
     */
    @Override
    public List<ActivityRateInfo> getActivityRateList(ActivityRateInfo info) {
        List<ActivityRateInfo> resultList = new ArrayList<>();
        ActivityRateBean searchBean = null;
        if (info != null) {
            searchBean = activityRateFromInfoToBean(info);
        }
        List<ActivityRateBean> dbList = packetOperationMapper.getActivityRateList(searchBean);

        if (dbList != null && !dbList.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            try {
                if (StringUtils.isEmpty(searchBean.getSearchMonth())) {
                    // 处理按日搜索的查询结果
                    ActivityRateInfo showInfo = null;
                    // 遍历数据库检索出的记录
                    for (ActivityRateBean item : dbList) {
                        // 搜索时没有选定日期
                        showInfo = new ActivityRateInfo();
                        // 字段：【时间】
                        showInfo.setDay(item.getDay());
                        // 字段：【校区】
                        showInfo.setCollegeName(item.getCollegeName());
                        // 字段：【小派总数】
                        showInfo.setPuserNum(item.getPuserNum());
                        // 字段：【活跃小派数】
                        showInfo.setActivePuserNum(item.getActivePuserNumDay());
                        // 字段：【活跃度】
                        showInfo.setActiveRate(getRateByValue(item.getActiveRate(), 1) + "%");
                        // 字段：【平均活跃度】
                        showInfo.setAverageActiveRate(getRateByValue(item.getAverageActiveRate(), 1) + "%");
                        // 字段：【活跃度新增】
                        showInfo.setIncreaseActiveRate(getRateByValue(item.getIncreaseActiveRate(), 1) + "%");
                        // 字段：【环比增长】
                        showInfo.setPacketIncreaseRate(StringUtils.isEmpty(item.getPacketIncreaseRate()) ? null : getRateByValue(item.getPacketIncreaseRate(), 1) + "%");
                        resultList.add(showInfo);
                    }
                } else {
                    // 处理按月搜索的查询结果
                    for (ActivityRateBean item : dbList) {
                        // 搜索上月最后一天的数据
                        cal.setTime(sdf.parse(item.getDay()));
                        cal.add(Calendar.MONTH, -1);
                        searchBean.setSearchStartDate(sdf.format(cal.getTime()));
                        searchBean.setSearchEndDate(sdf.format(cal.getTime()));
                        List<ActivityRateBean> lastMonthList = packetOperationMapper.getActivityRateList(searchBean);

                        ActivityRateInfo infoItem = new ActivityRateInfo();
                        // 字段：【时间】
                        infoItem.setDay(item.getDay().split("-")[0] + "-" + item.getDay().split("-")[1]);
                        // 字段：【校区】
                        infoItem.setCollegeName(item.getCollegeName());
                        // 字段：【小派总数】
                        infoItem.setPuserNum(item.getPuserNum());
                        // 字段：【活跃小派数】
                        infoItem.setActivePuserNum(item.getActivePuserNumMonth());
                        // 字段：【活跃度】
                        infoItem.setActiveRate(getRateByValue(item.getActiveRate(), 1) + "%");
                        // 字段：【平均活跃度】
                        infoItem.setAverageActiveRate(infoItem.getActiveRate());
                        // 字段：【活跃度新增】
                        infoItem.setIncreaseActiveRate("0.00%");
                        // 字段：【环比增长】
                        if (lastMonthList != null && !lastMonthList.isEmpty()) {
                            ActivityRateBean lastMonthBean = lastMonthList.get(0);
                            infoItem.setPacketIncreaseRate((null == lastMonthBean.getActivePuserNumMonth() || lastMonthBean.getActivePuserNumMonth() <= 0) ? null : getRateByValue(infoItem.getActivePuserNum(), lastMonthBean.getActivePuserNumMonth(), lastMonthBean.getActivePuserNumMonth()) + "%");
                        }
                        resultList.add(infoItem);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 查询活跃率列表数量
     *
     * @param info
     * @return
     * @see PacketOperationService#getActivityRateListCount(ActivityRateInfo)
     */
    @Override
    public Integer getActivityRateListCount(ActivityRateInfo info) {
        ActivityRateBean searchBean = null;
        if (info != null) {
            searchBean = activityRateFromInfoToBean(info);
        }
        return packetOperationMapper.getActivityRateListCount(searchBean);
    }

    private ActivityRateBean activityRateFromInfoToBean(ActivityRateInfo info) {
        ActivityRateBean bean = new ActivityRateBean();
        if (!StringUtils.isEmpty(info.getSearchMonth()) && info.getSearchMonth().contains("-")) {
            // 按月，搜索该月最后一天的数据
            bean.setSearchMonth(info.getSearchMonth());
            int year = Integer.valueOf(bean.getSearchMonth().split("-")[0]);
            int month = Integer.valueOf(bean.getSearchMonth().split("-")[1]);
            int lastDay = getLastDayOfMonth(year, month);
            Calendar cal = Calendar.getInstance();
            cal.set(year, month - 1, lastDay);
			Date now = new Date();
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(now);
            if (cal.compareTo(cal2) >= 0) {
                // 如果是当前月的最后一天，改为【昨天】
				cal.setTime(now);
                cal.add(Calendar.DAY_OF_MONTH, -1);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 设置搜索的开始日期
            bean.setSearchStartDate(sdf.format(cal.getTime()));
            bean.setShowStartDate(sdf.format(cal.getTime()));

            // 设置搜索示的结束日期
            bean.setSearchEndDate(sdf.format(cal.getTime()));
            bean.setShowEndDate(sdf.format(cal.getTime()));

        } else if (!StringUtils.isEmpty(info.getSearchStartDate()) || !StringUtils.isEmpty(info.getSearchEndDate())) {
            // 按日
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!StringUtils.isEmpty(info.getSearchStartDate())) {
                // 设置搜索的开始日期
                bean.setSearchStartDate(info.getSearchStartDate());
            }
            if (!StringUtils.isEmpty(info.getSearchEndDate())) {
                // 设置搜索的开始日期
                bean.setSearchEndDate(info.getSearchEndDate());
            }
        }
        if (!StringUtils.isEmpty(info.getSearchColleges())) {
            bean.setSearchColleges("(" + info.getSearchColleges() + ")");
        }
        // 分页参数
        bean.setSort(info.getSort());
        bean.setOrder(info.getOrder());
        bean.setLimit(info.getLimit());
        bean.setOffset(info.getOffset());
        return bean;
    }

    /**
     * 某年某月的最后一天
     *
     * @param year  int 年份
     * @param month int 月份
     * @return int 某年某月的最后一天
     */
    private int getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        // 某年某月的最后一天
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 某年某月的第一天
     *
     * @param year  int 年份
     * @param month int 月份
     * @return int 某年某月的第一天
     */
    private int getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        // 某年某月的第一天
        return cal.getActualMinimum(Calendar.DATE);
    }

    /**
     * @param info
     * @return
     * @Description: 查询好评率列表
     * @see com.rofour.baseball.service.report.PacketOperationService#getCommentRateList(com.rofour.baseball.controller.model.report.CommentRateInfo)
     */
    @Override
    public List<CommentRateInfo> getCommentRateList(CommentRateInfo info) {
        List<CommentRateInfo> resultList = new ArrayList<>();
        CommentRateBean searchBean = null;
        if (info != null) {
            searchBean = commentRateFromInfoToBean(info);
        }

        List<CommentRateBean> dbList = packetOperationMapper.getCommentRateList(searchBean);

        if (dbList != null && !dbList.isEmpty()) {
            for (CommentRateBean beanItem : dbList) {
                CommentRateInfo infoItem = new CommentRateInfo();
                // 日期
                infoItem.setDay(beanItem.getDay());
                // 校区名
                infoItem.setCampus(beanItem.getCollegeName());
                // 有评论订单数
                infoItem.setCommentOrderNum(beanItem.getCommentOrderNum());
                // 有评论订单分值
                infoItem.setCommentOrderTotalscore(beanItem.getCommentOrderTotalscore());
                // 用户评论分数
                infoItem.setCommentOrderGetscore(beanItem.getCommentOrderGetscore());
                // 好评率
                infoItem.setFavorableRate(getRateByValue(beanItem.getFavorableRate(), 1) + "%");
                // 环比增长
                infoItem.setIncreaseRate(StringUtils.isEmpty(beanItem.getIncreaseRate()) ? null : getRateByValue(beanItem.getIncreaseRate(), 1) + "%");

                resultList.add(infoItem);
            }
        }

        return resultList;
    }

    private CommentRateBean commentRateFromInfoToBean(CommentRateInfo info) {
        CommentRateBean bean = new CommentRateBean();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(info.getSearchStartDate())) {
            // 设置搜索的开始日期
            bean.setSearchStartDate(info.getSearchStartDate());
        }
        if (!StringUtils.isEmpty(info.getSearchEndDate())) {
            // 设置搜索的开始日期
            bean.setSearchEndDate(info.getSearchEndDate());
        }
        if (!StringUtils.isEmpty(info.getSearchColleges())) {
            bean.setSearchColleges("(" + info.getSearchColleges() + ")");
        }
        // 分页参数
        bean.setSort(info.getSort());
        bean.setOrder(info.getOrder());
        bean.setLimit(info.getLimit());
        bean.setOffset(info.getOffset());
        return bean;
    }

    /**
     * 查询活跃率列表数量
     *
     * @param info
     * @return
     * @see com.rofour.baseball.service.report.PacketOperationService#getCommentRateListCount(com.rofour.baseball.controller.model.report.CommentRateInfo)
     */
    @Override
    public Integer getCommentRateListCount(CommentRateInfo info) {
        CommentRateBean searchBean = null;
        if (info != null) {
            searchBean = commentRateFromInfoToBean(info);
        }

        return packetOperationMapper.getCommentRateListCount(searchBean);
    }

    /**
     * a / b * 100% 保留两位小数
     */
    private String getRateByValue(Object a, Object b) {
        if (a == null || b == null || a.toString().equals("0") || b.toString().equals("0") || b.toString().equals("0.00")) {
            return "0.00";
        }
        BigDecimal aa = new BigDecimal(a.toString());
        BigDecimal bb = new BigDecimal(b.toString());
        return new DecimalFormat("#0.00").format(aa.divide(bb, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
    }

    /**
     * (a - b) / c * 100 保留两位小数
     */
    private String getRateByValue(Object a, Object b, Object c) {
    	String result = null;
    	try {
    		if(a == null || b == null || c == null){
        		return null;
        	}
            if (c.toString().equals("0") || c.toString().equals("0.00")) {
                return null;
            }
            BigDecimal aa = new BigDecimal(a.toString());
            BigDecimal bb = new BigDecimal(b.toString());
            BigDecimal cc = new BigDecimal(c.toString());
            result = new DecimalFormat("#0.00").format(aa.subtract(bb).divide(cc, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
		} catch (Exception e) {
			logger.error("计算异常-{}-{}",e.getMessage(),e);
		}
        return result;
    }

    /**
     * @param revieveOrderRate
     * @return
     * @Description: 查询接单率列表
     * @see com.rofour.baseball.service.report.PacketOperationService#getRecOrderRateList(com.rofour.baseball.controller.model.report.RecieveOrderRateInfo)
     */
	@Override
	public List<RecieveOrderRateInfo> getRecOrderRateList(RecieveOrderRateInfo revieveOrderRate) {
		List<RecieveOrderRateInfo> recOrderRateInfoList = packetOperationMapper.getRecOrderRateList(revieveOrderRate);
		return calculateRecOrderRate(recOrderRateInfoList);
	}
	
	private List<RecieveOrderRateInfo> calculateRecOrderRate(List<RecieveOrderRateInfo> recOrderRateInfoList){
		for (RecieveOrderRateInfo recOrderRateInfo : recOrderRateInfoList) {
			Integer totalOrderNum = recOrderRateInfo.getTotalOrderNum();
			Integer orderStatusWaittaking = recOrderRateInfo.getOrderStatusWaittaking();
			String recOrderRate = null;
			if(totalOrderNum != null && orderStatusWaittaking != null){
				recOrderRate = getRateByValue(recOrderRateInfo.getTotalOrderNum(),recOrderRateInfo.getOrderStatusWaittaking(),recOrderRateInfo.getTotalOrderNum());
			}
			Integer totalOrderNumBefore = recOrderRateInfo.getTotalOrderNumBefore();
			Integer orderStatusWaittakingBefore = recOrderRateInfo.getOrderStatusWaittakingBefore();
			String recOrderRateBefore = null;
			String recOrderQoq = null;
			if(totalOrderNumBefore != null && orderStatusWaittakingBefore != null){
				recOrderRateBefore = getRateByValue(totalOrderNumBefore,orderStatusWaittakingBefore,totalOrderNumBefore);
				recOrderQoq = getRateByValue(recOrderRate,recOrderRateBefore,recOrderRateBefore);
			}
			recOrderRateInfo.setRecOrderRate(recOrderRate == null ? null : recOrderRate + "%");
			recOrderRateInfo.setRecOrderQoq(recOrderQoq == null ? null : recOrderQoq + "%");
		}
		return recOrderRateInfoList;
	}


	/**
     * @param revieveOrderRate
     * @return
     * @Description: 查询接单率列表数量
     * @see com.rofour.baseball.service.report.PacketOperationService#getRecOrderRateListCount(com.rofour.baseball.controller.model.report.RecieveOrderRateInfo)
     */
	@Override
	public Integer getRecOrderRateListCount(RecieveOrderRateInfo revieveOrderRate) {
		return packetOperationMapper.getRecOrderRateListCount(revieveOrderRate);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String,List> getPuserIncrNum(Integer regionId) {
		Map<String, Object> paramsMap  =  new HashMap<String, Object>();
		paramsMap.put("regionId", regionId);
		paramsMap.put("count", 8);
		List<ReportRegionPuserSumBean> dataList = reportRegionPuserSumMapper.getRegionPuserByDate(paramsMap);
		Map<String,List> resultList = new HashMap<String,List>();
		
		if(!CollectionUtils.isEmpty(dataList)){
			List<String> resultDate =  new ArrayList<String>();
			List<Integer> resultNum =  new ArrayList<Integer>();
			int  differenceNum = 0;
			int  oldNum = 0;
			for (int i = 0; i < dataList.size()-1 ; i++) {
				ReportRegionPuserSumBean bean  = dataList.get(i);
				if(i+1 < dataList.size()){
					ReportRegionPuserSumBean bean1 = dataList.get(i+1);
					oldNum = bean1.getPuserNum();
				}else{
					oldNum =  bean.getPuserNum();
				}
				int newNum = bean.getPuserNum()==null?0:bean.getPuserNum();
				differenceNum = newNum - oldNum ;
				if(differenceNum < 0){
					differenceNum = 0;
				}
				resultDate.add(DateTimeUtils.getDateString(bean.getDay(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_NOT_YEAR));
				resultNum.add(differenceNum);
			}
			Collections.reverse(resultDate);
			Collections.reverse(resultNum);
			resultList.put("date", resultDate);
			resultList.put("differenceNum", resultNum);

		}

		return resultList;
	}
	 private String getDateStr(Date date)
	    {
	        String str = new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
	        return str;
	    }
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String,List> getPuserTotalNum(Integer regionId) {
		Map<String, Object> paramsMap  =  new HashMap<String, Object>();
		paramsMap.put("regionId", regionId);
		//根据当前日期计算要查询的周期
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		cal.add(Calendar.DAY_OF_MONTH, SEARCH_YESTERDAY);
//		String startDate = DateTimeUtils.getDateString(cal.getTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT);
//		cal.add(Calendar.DAY_OF_MONTH, SEARCH_WEEK);
//		String endDate = DateTimeUtils.getDateString(cal.getTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT);

		paramsMap.put("count", 7);
		List<ReportRegionPuserSumBean> dataList = reportRegionPuserSumMapper.getRegionPuserByDate(paramsMap);
		Map<String,List> resultList = new HashMap<String,List>();

		if(!CollectionUtils.isEmpty(dataList)){
			List<String> resultDate =  new ArrayList<String>();
			List<Integer> resultNum =  new ArrayList<Integer>();
			List<Integer> resultActiveNum =  new ArrayList<Integer>();
			for (int i = dataList.size()-1; i >= 0; i--) {
				ReportRegionPuserSumBean bean  = dataList.get(i);
				resultActiveNum.add(bean.getActivePuserNum());
				resultNum.add(bean.getPuserNum());
				resultDate.add(DateTimeUtils.getDateString(bean.getDay(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_NOT_YEAR));
			}
			resultList.put("resultDate", resultDate);
			resultList.put("resultNum", resultNum);
			resultList.put("resultActiveNum", resultActiveNum);
		}
		
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String,List> getCapacityIncrNum() {
		Map<String, Object> paramsMap  =  new HashMap<String, Object>();
		paramsMap.put("count", 8);
		List<ReportOrderStatisticsSumBean> dataList = reportOrderStatisticsSumMapper.getOrderStatisticsNumByDate(paramsMap);
		Map<String,List> resultList = new HashMap<String,List>();

		if(!CollectionUtils.isEmpty(dataList)){
			List<String> resultDate =  new ArrayList<String>();
			List<Integer> resultNum =  new ArrayList<Integer>();
			int  differenceNum = 0;
			int  oldNum = 0;
			for (int i = 0; i < dataList.size()-1; i++) {
				ReportOrderStatisticsSumBean bean  = dataList.get(i);
				if(i+1 < dataList.size()){
					ReportOrderStatisticsSumBean bean1 = dataList.get(i+1);
					oldNum = bean1.getShippingAbility();
				}else{
					oldNum =  bean.getShippingAbility();
				}
				differenceNum =bean.getShippingAbility() - oldNum ;
				if(differenceNum < 0){
					differenceNum = 0;
				}
				resultDate.add(DateTimeUtils.getDateString(DateTimeUtils.getDateByString(bean.getDay(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_NOT_YEAR));
				resultNum.add(differenceNum);
			}
			Collections.reverse(resultDate);
			Collections.reverse(resultNum);
			resultList.put("date", resultDate);
			resultList.put("differenceNum", resultNum);

		}

		return resultList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String,List> getReceiveOrder() {
		Map<String, Object> paramsMap  =  new HashMap<String, Object>();
		paramsMap.put("count", 7);
		List<RecieveOrderRateInfo> dataList = packetOperationMapper.getReceiveOrderByDate(paramsMap);
		Map<String,List> resultList = new HashMap<String,List>();
		if(!CollectionUtils.isEmpty(dataList)){
			List<String> resultDate =  new ArrayList<String>();
			List<Integer> resultNum =  new ArrayList<Integer>();
			for (int i =  dataList.size()-1 ; i >= 0 ; i--) {
				RecieveOrderRateInfo bean  = dataList.get(i);
				resultDate.add(DateTimeUtils.getDateString(DateTimeUtils.getDateByString(bean.getDay(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_NOT_YEAR));
				resultNum.add(bean.getOrderStatusPuser());
			}
			resultList.put("date", resultDate);
			resultList.put("differenceNum", resultNum);
		}
		return resultList;
	}

	/**
	 * @Description: 众包运营导航数据查询
	 * @return 
	 * @see com.rofour.baseball.service.report.PacketOperationService#getPacketReportNav()
	 */
	@Override
	public PacketReportNavInfo getPacketReportNav() {
		PacketReportNavInfo packetReportNavInfo = new PacketReportNavInfo();
		PacketReportNavInfo packetReportNavInfoPartOne = getPacketReportNavPartOne();
		PacketReportNavInfo packetReportNavInfoPartTwo = getPacketReportNavPartTwo();
		try {
			if(packetReportNavInfoPartTwo != null){
				BeanUtils.copyProperties(packetReportNavInfo, packetReportNavInfoPartTwo);
			}
			if(packetReportNavInfoPartOne != null){
				packetReportNavInfo.setPuserNum(packetReportNavInfoPartOne.getPuserNum());
				packetReportNavInfo.setActivePuserNum(packetReportNavInfoPartOne.getActivePuserNum());
				packetReportNavInfo.setPuserNumBefore(packetReportNavInfoPartOne.getPuserNumBefore());
				packetReportNavInfo.setPuserIncNum(packetReportNavInfoPartOne.getPuserIncNum());
				packetReportNavInfo.setActiveDegree(packetReportNavInfoPartOne.getActiveDegree());
				packetReportNavInfo.setPuserIncRate(packetReportNavInfoPartOne.getPuserIncRate());
			}
			if(packetReportNavInfoPartOne == null && packetReportNavInfoPartTwo == null){
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return packetReportNavInfo;
	}

	/**
	 * @Description: 众包运营导航数据查询Part1
	 * @return 
	 * @see com.rofour.baseball.service.report.PacketOperationService#getPacketReportNavPartOne()
	 */
	@Override
	public PacketReportNavInfo getPacketReportNavPartOne() {
		PacketReportNavInfo packetReportNavInfo = packetOperationMapper.getPacketReportNavPartOne();
		if(packetReportNavInfo != null){
			//计算数值
			int puserNum = packetReportNavInfo.getPuserNum() == null ? 0 : packetReportNavInfo.getPuserNum();
			int activePuserNum = packetReportNavInfo.getActivePuserNum() == null ? 0 : packetReportNavInfo.getActivePuserNum();
			int puserNumBefore = packetReportNavInfo.getPuserNumBefore() == null ? 0 : packetReportNavInfo.getPuserNumBefore();
			int puserIncNum = puserNum - puserNumBefore;
			String activeDegree = getRateByValue(activePuserNum,0,puserNum);
			activeDegree = (activeDegree == null ? "-"	: activeDegree + "%");
			String puserIncRate = getRateByValue(puserNum,puserNumBefore,puserNum);
			puserIncRate = (puserIncRate == null ? "-" : puserIncRate + "%");
			//封装到对象
			packetReportNavInfo.setPuserNum(puserNum);
			packetReportNavInfo.setActivePuserNum(activePuserNum);
			packetReportNavInfo.setPuserNumBefore(puserNumBefore);
			packetReportNavInfo.setPuserIncNum(puserIncNum);
			packetReportNavInfo.setActiveDegree(activeDegree);
			packetReportNavInfo.setPuserIncRate(puserIncRate);
		}
		return packetReportNavInfo;
	}

	/**
	 * @Description: 众包运营导航数据查询Part2
	 * @return 
	 * @see com.rofour.baseball.service.report.PacketOperationService#getPacketReportNavPartTwo()
	 */
	@Override
	public PacketReportNavInfo getPacketReportNavPartTwo() {
		PacketReportNavInfo packetReportNavInfo = packetOperationMapper.getPacketReportNavPartTwo();
		if(packetReportNavInfo != null){
			//计算数值
			int shippingAbility = packetReportNavInfo.getShippingAbility() == null ? 0 : packetReportNavInfo.getShippingAbility();
			int totalOrderNum = packetReportNavInfo.getTotalOrderNum() == null ? 0 : packetReportNavInfo.getTotalOrderNum();
			int takeOrderNum = packetReportNavInfo.getTakeOrderNum() == null ? 0 : packetReportNavInfo.getTakeOrderNum();
			int commentOrderNum = packetReportNavInfo.getCommentOrderNum() == null ? 0 : packetReportNavInfo.getCommentOrderNum();
			int commentOrderGoodnum = packetReportNavInfo.getCommentOrderGoodnum() == null ? 0 : packetReportNavInfo.getCommentOrderGoodnum();
			int shippingAbilityBefore = packetReportNavInfo.getShippingAbilityBefore() == null ? 0 : packetReportNavInfo.getShippingAbilityBefore();
			int commentOrderGetscore = packetReportNavInfo.getCommentOrderGetscore() == null ? 0 : packetReportNavInfo.getCommentOrderGetscore();
			int shippingAbilityIncNum = shippingAbility - shippingAbilityBefore;
			String shippingIncRate = getRateByValue(shippingAbility,shippingAbilityBefore,shippingAbility);
			shippingIncRate = (shippingIncRate == null ? "-" : shippingIncRate + "%");
			String recOrderRate = getRateByValue(takeOrderNum,0,totalOrderNum);
			recOrderRate = (recOrderRate == null ? "-" : recOrderRate + "%");
			String commentGoodRate = getRateByValue(commentOrderGoodnum,0,commentOrderNum);
			commentGoodRate = (commentGoodRate == null ? "-" : commentGoodRate + "%");
			//封装到对象
			packetReportNavInfo.setShippingAbility(shippingAbility);
			packetReportNavInfo.setTotalOrderNum(totalOrderNum);
			packetReportNavInfo.setTakeOrderNum(takeOrderNum);
			packetReportNavInfo.setCommentOrderNum(commentOrderNum);
			packetReportNavInfo.setCommentOrderGoodnum(commentOrderGoodnum);
			packetReportNavInfo.setShippingAbilityBefore(shippingAbilityBefore);
			packetReportNavInfo.setShippingAbilityIncNum(shippingAbilityIncNum);
			packetReportNavInfo.setCommentOrderGetscore(commentOrderGetscore);
			packetReportNavInfo.setShippingIncRate(shippingIncRate);
			packetReportNavInfo.setRecOrderRate(recOrderRate);
			packetReportNavInfo.setCommentGoodRate(commentGoodRate);
		}
		return packetReportNavInfo;
	}
}
