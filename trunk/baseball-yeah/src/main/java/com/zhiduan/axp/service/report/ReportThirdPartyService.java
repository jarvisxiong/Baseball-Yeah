package com.zhiduan.axp.service.report;

import java.util.List;
import java.util.Map;

import com.zhiduan.axp.controller.model.report.ReportThirdPartyInfo;
import com.zhiduan.axp.controller.model.report.ReportThirdPartyPhoneInfo;

/**
 * @author ZXY
 * @ClassName: ReportThirdParty
 * @Description: 第三方报表服务接口
 * @date 2016-04-16 09:13:24
 */
public interface ReportThirdPartyService {
    /**
     * 根据条件查询报表
     */
    Map getReportByParam(String startDate, String endDate, String collegeIds, String areaIds) throws Exception;

    /**
     * 截至当天，按学校查询所有号码，潜在用户
     */
    List<ReportThirdPartyPhoneInfo> getPhoneByCollege(String collegeId, String reportDate) throws Exception;

    /**
     * 截至当天，按学校查询所有注册号码，在第三方注册
     */
    List<ReportThirdPartyPhoneInfo> getRegPhoneByCollege(String collegeId, String reportDate) throws Exception;

    /**
     * 截至当天，按学校查询所有未注册号码，潜在未注册
     */
    List<ReportThirdPartyPhoneInfo> getUnregPhoneByCollege(String collegeId, String reportDate) throws Exception;

    /**
     * 当天，按学校查询新增号码
     */
    List<ReportThirdPartyPhoneInfo> getNewPhoneByCollege(String collegeId, String reportDate) throws Exception;
}