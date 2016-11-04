package com.rofour.baseball.dao.report.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.controller.model.report.ReportThirdPartyPhoneInfo;
import com.rofour.baseball.dao.report.bean.ReportThirdPartyBean;

/**
 * @ClassName: ReportThirdPartyMapper
 * @Description: 第三方报表数据库操作接口
 * @author ZXY
 * @date 2016-04-16 09:13:24
 *
 */
@Named("reportThirdPartyMapper")
public interface ReportThirdPartyMapper {

    /**
     * @Method: selectAll
     * @Description: 查询所有
     * @param
     * @return 返回类型 List
     * @throws
     * @date 2016-04-16 09:13:24
     * **/
    List<Integer> selectCountOfAll();

    /**
     * @Method: getReportByParam
     * @Description: 根据条件查询列表
     * @param
     * @return 返回类型 List
     * @throws
     * @date 2016-04-16 09:13:24
     **/
    List<ReportThirdPartyBean> getReportByParam(Map map);

    /**
     * @Method: getPhoneByCollege
     * @Description: 截至当天，按学校查询所有号码，潜在用户
     * @param
     * @return 返回类型 List
     * @throws
     * @date 2016-04-16 09:13:24
     **/
    List<ReportThirdPartyPhoneInfo> getPhoneByCollege(Long collegeId, String reportDate);

    /**
     * @Method: getRegPhoneByCollege
     * @Description: 截至当天，按学校查询所有注册号码，在第三方注册
     * @param
     * @return 返回类型 List
     * @throws
     * @date 2016-04-16 09:13:24
     **/
    List<ReportThirdPartyPhoneInfo> getRegPhoneByCollege(Long collegeId, String reportDate);

    /**
     * @Method: getUnregPhoneByCollege
     * @Description: 截至当天，按学校查询所有未注册号码，潜在未注册
     * @param
     * @return 返回类型 List
     * @throws
     * @date 2016-04-16 09:13:24
     **/
    List<ReportThirdPartyPhoneInfo> getUnregPhoneByCollege(Long collegeId, String reportDate);

    /**
     * @Method: getNewPhoneByCollege
     * @Description: 查询当天，按学校查询新增号码
     * @param
     * @return 返回类型 List
     * @throws
     * @date 2016-04-16 09:13:24
     **/
    List<ReportThirdPartyPhoneInfo> getNewPhoneByCollege(Long collegeId, String reportDate);
}