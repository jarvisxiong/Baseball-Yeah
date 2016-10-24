package com.zhiduan.axp.dao.report.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.dao.report.bean.ReportStoreSmsBean;
import com.zhiduan.axp.dao.report.bean.ReportUserSmsModelBean;
import com.zhiduan.axp.dao.report.bean.SearchStoreSmsDetailBean;
import com.zhiduan.axp.dao.report.bean.SearchStoreSmsTotalBean;

/**
 * @author ZXY
 * @ClassName: ReportStoreSmsMapper
 * @Description: 短信报表
 * @date 2016/4/26 13:38
 */
@Named("reportStoreSmsMapper")
public interface ReportStoreSmsMapper {
    /**
     * 货源短信报表
     */
    List<ReportStoreSmsBean> getStoreSmsDayReport(Map<String, Object> map);

    /**
     * @param map
     * @return
     * @Description: 货源短信报表总条数
     */
    int getStoreSmsReportCount(Map<String, String> map);

    /**
     * @param map
     * @return List<SearchStoreSmsTotalBean> 返回类型
     * @Description: 短信统计--汇总
     */
    List<SearchStoreSmsTotalBean> selectStoreSmsTotalInfo(Map<String, String> map);

    /**
     * @param map
     * @return List<SearchStoreSmsDetailBean> 返回类型
     * @Description: 短信统计--明细
     */
    List<SearchStoreSmsDetailBean> selectStoreSmsDetailInfo(Map<String, String> map);

    /**
     * 用户短信模板查询
     *
     * @param map
     * @return
     */
    List<ReportUserSmsModelBean> getuserSmsModelList(Map<String, Object> map);

    /**
     * 用户短信模板数量查询
     *
     * @param map
     * @return
     */
    int getuserSmsModelListCount(Map<String, Object> map);
}
