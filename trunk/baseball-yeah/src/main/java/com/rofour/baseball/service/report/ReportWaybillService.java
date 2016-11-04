package com.rofour.baseball.service.report;

import java.util.List;
import java.util.Map;

import com.rofour.baseball.controller.model.WayBillLogInfo;
import com.rofour.baseball.controller.model.report.ReportCityPhoneInfo;
import com.rofour.baseball.controller.model.report.SearchPhoneCountInfo;


/**
 * @ClassName: ReportWaybillService
 * @Description: 运单报表
 * @author ZXY
 * @date 2016/4/26 13:21
 */
public interface ReportWaybillService {
    /**
     * @Description: 统计学校运单手机号
     * @param info
     * @return List<SearchPhoneCountInfo>
     * @throws
     */
    List<SearchPhoneCountInfo> getPhoneCount(SearchPhoneCountInfo info) throws Exception;

    /**
     * @Description: 学校手机号统计明细
     * @param info
     * @return List<SearchPhoneCountInfo>
     * @throws
     */
    List<SearchPhoneCountInfo> getPhoneDetailCount(SearchPhoneCountInfo info) throws Exception;

    /**
     * @Description: 学校手机号统计总数
     * @param
     * @return List<SearchPhoneCountInfo>
     * @throws
     */
    List<SearchPhoneCountInfo> getPhoneAllCount() throws Exception;

    /**
     * @Description: 执行传入的sql语句
     * @param
     * @return List<Map<String, Object>>
     * @throws
     */
    List<Map<String, Object>> getSqlDataList(Map<String, Object> map) throws Exception;
    
    List<ReportCityPhoneInfo> selectCityPhoneCount(Map<String,Object> map);
    
    List<WayBillLogInfo> selectWayBillLogInfo(WayBillLogInfo info);
    int selectWayBillLogTotalCount(WayBillLogInfo info);
}
