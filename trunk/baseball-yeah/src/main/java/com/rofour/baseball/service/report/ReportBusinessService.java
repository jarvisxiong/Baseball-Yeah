package com.rofour.baseball.service.report;

import java.util.List;

import com.rofour.baseball.controller.model.report.ReportBusinessInfo;

/**
 * @ClassName: ReportBusinessService
 * @Description: 运营报表业务层
 * @author gechao
 * @date 2016年10月13日 上午9:34:58
 *
 */
public interface ReportBusinessService {

	List<ReportBusinessInfo> queryDayStatistics(ReportBusinessInfo info);
}
