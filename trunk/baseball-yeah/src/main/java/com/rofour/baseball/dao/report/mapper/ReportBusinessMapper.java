package com.rofour.baseball.dao.report.mapper;

import java.util.List;

import javax.inject.Named;
/**
 * @author gechao
 * @ClassName: ReportBusinessMapper
 * @Description: 运营报表
 * @date 2016/11/01 13:38
 */

import com.rofour.baseball.controller.model.report.ReportBusinessInfo;
@Named("reportBusinessMapper")
public interface ReportBusinessMapper {

	List<ReportBusinessInfo> queryDayStatistics(ReportBusinessInfo info);
	
	int queryDayStatisticsCount(ReportBusinessInfo info);
}
