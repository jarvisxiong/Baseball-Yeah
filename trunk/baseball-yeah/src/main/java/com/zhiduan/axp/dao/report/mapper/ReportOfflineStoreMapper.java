package com.zhiduan.axp.dao.report.mapper;

import java.util.List;

import javax.inject.Named;

import com.zhiduan.axp.controller.model.report.ReportOfflinePartyInfo;
import com.zhiduan.axp.controller.model.report.ReportWaybillInfo;

@Named("reportOfflineStoreMapper")
public interface ReportOfflineStoreMapper {

	public List<ReportOfflinePartyInfo> getOfflineStoreList(
			ReportOfflinePartyInfo info);
	
	public Integer getOfflineStoreCount(ReportOfflinePartyInfo info);

	public List<ReportWaybillInfo> getDetailList(ReportWaybillInfo waybillInfo);

	public Integer getDetailCount(ReportWaybillInfo waybillInfo);
}
