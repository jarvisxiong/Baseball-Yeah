package com.zhiduan.axp.service.report;

import java.util.List;

import com.zhiduan.axp.controller.model.report.ReportStoreSmsInfo;
import com.zhiduan.axp.controller.model.report.ReportUserSmsModelInfo;
import com.zhiduan.axp.controller.model.report.SearchStoreSmsDetailInfo;
import com.zhiduan.axp.controller.model.report.SearchStoreSmsTotalInfo;

/**
 * @ClassName: ReportStoreSmsService
 * @Description: 货源短信日报表服务接口
 * @author ZXY
 * @date 2016/4/26 13:48
 */
public interface ReportStoreSmsService {

	/**
	 * 
	 * @Description: 获取货源短信报表
	 * @param storeName
	 * @param storeId
	 * @param supervisor
	 * @param expressId
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @param limit
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	List<ReportStoreSmsInfo> getStoreSmsDayReport(String storeName, String storeId, String supervisor, String expressId,
			String startDate, String endDate, String status, Integer limit, Integer offset) throws Exception;
	
	/**
	 * 
	 * @Description: 货源短信报表数据总条数
	 * @param storeName
	 * @param storeId
	 * @param supervisor
	 * @param expressId
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @return
	 */
	int getStoreSmsReportCount (String storeName, String storeId, String supervisor, String expressId,
			String startDate, String endDate, String status);

	/**
	 * 
	 * @Description: 短信统计--汇总
	 * @param storeId
	 * @param startDate
	 * @param endDate
	 * @return List<SearchStoreSmsTotalInfo>
	 * @throws Exception
	 */
	List<SearchStoreSmsTotalInfo> selectStoreSmsTotalInfo(String storeId, String startDate, String endDate)
			throws Exception;

	/**
	 * 
	 * @Description: 短信统计--明细
	 * @param storeId
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @param beVirtual
	 * @return List<SearchStoreSmsDetailInfo>
	 * @throws Exception
	 */
	List<SearchStoreSmsDetailInfo> searchStoreSmsDetails(String storeId, String startDate, String endDate,
			String status, String beVirtual) throws Exception;

	/**
	 * 查询用户自定义短信模板
	 *
	 * @param storeId
	 * @param userName
	 * @param phone
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<ReportUserSmsModelInfo> searchUserSmsModelList(String storeId, String userName, String phone,String modelContent, Integer offset, Integer limit);

	/**
	 * 查询用户自定义短信模板数量
	 *
	 * @param storeId
	 * @param userName
	 * @param phone
	 * @return
	 */
	int getUserSmsModelListCount(String storeId, String userName, String phone,String modelContent);
}
