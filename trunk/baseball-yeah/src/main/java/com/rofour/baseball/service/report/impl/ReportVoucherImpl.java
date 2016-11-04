package com.rofour.baseball.service.report.impl;

import com.rofour.baseball.controller.model.WayBillLogInfo;
import com.rofour.baseball.controller.model.report.ReportCityPhoneInfo;
import com.rofour.baseball.controller.model.report.SearchPhoneCountInfo;
import com.rofour.baseball.dao.report.bean.ReportVoucherBean;
import com.rofour.baseball.dao.report.bean.SearchPhoneCountBean;
import com.rofour.baseball.dao.report.mapper.ReportVoucherMapper;
import com.rofour.baseball.dao.report.mapper.ReportWaybillMapper;
import com.rofour.baseball.service.report.ReportVoucherService;
import com.rofour.baseball.service.report.ReportWaybillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.awt.SystemColor.info;


/**
 * @ClassName: reportVoucherService
 * @Description: 代金券报表
 * @author lijun
 * @date 2016/9/29 09:31
 */
@Service("reportVoucherService")
public class ReportVoucherImpl implements ReportVoucherService {

    private Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	@Qualifier("reportVoucherMapper")
    private ReportVoucherMapper reportVoucherMapper;
    
	@Override
	public List<ReportVoucherBean> getVoucherList(ReportVoucherBean bean) throws Exception {
		return reportVoucherMapper.getVoucherList(bean);
	}

	@Override
	public int getVoucherListCount(ReportVoucherBean bean) throws Exception {
		return reportVoucherMapper.getVoucherListCount(bean);
	}
	@Override
	public List<ReportVoucherBean> getAllUsers(ReportVoucherBean bean){
		return reportVoucherMapper.getAllUsers(bean);
	}
}
