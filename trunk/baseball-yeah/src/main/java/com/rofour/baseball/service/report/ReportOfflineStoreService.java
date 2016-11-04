package com.rofour.baseball.service.report;

import java.util.List;

import com.rofour.baseball.controller.model.report.ReportOfflinePartyInfo;
import com.rofour.baseball.controller.model.report.ReportWaybillInfo;

public interface ReportOfflineStoreService {

	public List<ReportOfflinePartyInfo> getOfflineStoreList(ReportOfflinePartyInfo info);
	
	public Integer getOfflineStoreCount(ReportOfflinePartyInfo info);

	public List<ReportWaybillInfo> getDetailList(ReportWaybillInfo waybillInfo);

	public Integer getDetailCount(ReportWaybillInfo waybillInfo);
}
