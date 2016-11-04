package com.rofour.baseball.service.report;

import java.util.List;

import com.rofour.baseball.controller.model.report.ReportStoreSmsInfo;
import com.rofour.baseball.controller.model.report.ReportUserSmsModelInfo;
import com.rofour.baseball.controller.model.report.SearchStoreSmsDetailInfo;
import com.rofour.baseball.controller.model.report.SearchStoreSmsTotalInfo;

/**
 * @author ZXY
 * @ClassName: ReportStoreSmsService
 * @Description: 货源短信日报表服务接口
 * @date 2016/4/26 13:48
 */
public interface ReportStoreSmsService {

    /**
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
     * @Description: 获取货源短信报表
     */
    List<ReportStoreSmsInfo> getStoreSmsDayReport(String storeName, String storeId, String supervisor, String expressId,
                                                  String startDate, String endDate, String status, Integer limit, Integer offset) throws Exception;

    /**
     * @param storeName
     * @param storeId
     * @param supervisor
     * @param expressId
     * @param startDate
     * @param endDate
     * @param status
     * @return
     * @Description: 货源短信报表数据总条数
     */
    int getStoreSmsReportCount(String storeName, String storeId, String supervisor, String expressId,
                               String startDate, String endDate, String status);

    /**
     * @param storeId
     * @param startDate
     * @param endDate
     * @return List<SearchStoreSmsTotalInfo>
     * @throws Exception
     * @Description: 短信统计--汇总
     */
    List<SearchStoreSmsTotalInfo> selectStoreSmsTotalInfo(String storeId, String startDate, String endDate)
            throws Exception;

    /**
     * @param storeId
     * @param startDate
     * @param endDate
     * @param status
     * @param beVirtual
     * @return List<SearchStoreSmsDetailInfo>
     * @throws Exception
     * @Description: 短信统计--明细
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
