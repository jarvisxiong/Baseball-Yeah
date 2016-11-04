package com.rofour.baseball.service.report.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.controller.model.report.ReportOfflinePartyInfo;
import com.rofour.baseball.controller.model.report.ReportWaybillInfo;
import com.rofour.baseball.dao.report.mapper.ReportOfflineStoreMapper;
import com.rofour.baseball.service.report.ReportOfflineStoreService;

@Service("reportOfflineStoreService")
public class ReportOfflineStoreServiceImpl implements ReportOfflineStoreService {

	
	@Autowired
	@Qualifier(value="reportOfflineStoreMapper")
	private ReportOfflineStoreMapper reportOfflineStoreMapper;

	
	public List<ReportOfflinePartyInfo> getOfflineStoreList(
			ReportOfflinePartyInfo info) {
		return reportOfflineStoreMapper.getOfflineStoreList(info);
	}


	public Integer getOfflineStoreCount(ReportOfflinePartyInfo info) {
		return reportOfflineStoreMapper.getOfflineStoreCount(info);
	}


	public List<ReportWaybillInfo> getDetailList(ReportWaybillInfo waybillInfo) {
		return reportOfflineStoreMapper.getDetailList(waybillInfo);
	}


	public Integer getDetailCount(ReportWaybillInfo waybillInfo) {
		return reportOfflineStoreMapper.getDetailCount(waybillInfo);
	}
	
	

}
