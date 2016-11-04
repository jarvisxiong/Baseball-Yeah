package com.rofour.baseball.dao.report.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.controller.model.report.ReportOfflinePartyInfo;
import com.rofour.baseball.controller.model.report.ReportWaybillInfo;

@Named("reportOfflineStoreMapper")
public interface ReportOfflineStoreMapper {

	public List<ReportOfflinePartyInfo> getOfflineStoreList(
			ReportOfflinePartyInfo info);
	
	public Integer getOfflineStoreCount(ReportOfflinePartyInfo info);

	public List<ReportWaybillInfo> getDetailList(ReportWaybillInfo waybillInfo);

	public Integer getDetailCount(ReportWaybillInfo waybillInfo);
}
