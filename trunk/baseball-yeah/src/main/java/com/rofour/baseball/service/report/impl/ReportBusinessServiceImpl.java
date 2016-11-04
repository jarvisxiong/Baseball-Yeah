package com.rofour.baseball.service.report.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.controller.model.report.ReportBusinessInfo;
import com.rofour.baseball.dao.report.mapper.ReportBusinessMapper;
import com.rofour.baseball.service.report.ReportBusinessService;
@Service("reportBusinessService")
public class ReportBusinessServiceImpl implements ReportBusinessService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier(value = "reportBusinessMapper")
    private ReportBusinessMapper businessMapper;

	@Override
	public List<ReportBusinessInfo> queryDayStatistics(ReportBusinessInfo info) { 
		logger.info("info" + info);
		return businessMapper.queryDayStatistics(info);
	}
    
}
