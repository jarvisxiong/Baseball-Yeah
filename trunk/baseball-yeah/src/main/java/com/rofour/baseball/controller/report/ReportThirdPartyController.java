package com.rofour.baseball.controller.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.common.StringUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.report.ReportThirdPartyPhoneInfo;
import com.rofour.baseball.service.report.ReportThirdPartyService;

/**

 */
@Controller
@RequestMapping("/report/thirdparty")
public class ReportThirdPartyController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("reportThirdParty")
    private ReportThirdPartyService reportThirdParty;

    /**
     * @param request
     * @return ResultInfo 返回类型
     * @throws
     * @Method: getReportThirdPartyByPK
     * @Description: 根据条件查询第三方报表
     * @author ZXY
     * @date 2016-04-16 09:13:24
     **/
    @RequestMapping(value = "/getReport", method = RequestMethod.POST)
    public void getReportByParam(HttpServletRequest request, HttpServletResponse response) {
        try {
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String collegeIds = request.getParameter("collegeIds");
            String areaIds = request.getParameter("areaIds");
            writeJson(reportThirdParty.getReportByParam(startDate, endDate, collegeIds, areaIds), response);
        } catch (Exception e) {
        }
    }

    /**
     * @param request【collegeId，reportDate】
     * @return ResultInfo 返回类型
     * @throws
     * @Method: getPhoneByCollege
     * @Description: 截至当天，按学校查询所有号码，潜在用户
     * @author ZXY
     * @date 2016-04-16 09:13:24
     **/
    @RequestMapping(value = "/getAllNum", method = RequestMethod.POST)
    public void getPhoneByCollege(HttpServletRequest request, HttpServletResponse response) {
        try {
            String collegeId = request.getParameter("collegeId");
            String reportDate = request.getParameter("reportDate");
            writeJson(reportThirdParty.getPhoneByCollege(collegeId, reportDate), response);
        } catch (Exception e) {
        }
    }

    /**
     * @param request【collegeId，reportDate】
     * @return ResultInfo 返回类型
     * @throws
     * @Method: getRegPhoneByCollege
     * @Description: 截至当天，按学校查询所有已注册号码
     * @author ZXY
     * @date 2016-04-16 09:13:24
     **/
    @ResponseBody
    @RequestMapping(value = "/getRegNum", method = RequestMethod.POST)
    public void getRegPhoneByCollege(HttpServletRequest request, HttpServletResponse response) {
        try {
            String collegeId = request.getParameter("collegeId");
            String reportDate = request.getParameter("reportDate");
            writeJson(reportThirdParty.getRegPhoneByCollege(collegeId, reportDate), response);
        } catch (Exception e) {
        }
    }

    /**
     * @param request【collegeId，reportDate】
     * @return ResultInfo 返回类型
     * @throws
     * @Method: getUnregPhoneByCollege
     * @Description: 截至当天，按学校查询所有未注册号码
     * @author ZXY
     * @date 2016-04-16 09:13:24
     **/
    @ResponseBody
    @RequestMapping(value = "/getUnRegNum", method = RequestMethod.POST)
    public void getUnregPhoneByCollege(HttpServletRequest request, HttpServletResponse response) {
        try {
            String collegeId = request.getParameter("collegeId");
            String reportDate = request.getParameter("reportDate");
            writeJson(reportThirdParty.getUnregPhoneByCollege(collegeId, reportDate), response);
        } catch (Exception e) {
        }
    }

    /**
     * @param request【collegeId，reportDate】
     * @return ResultInfo 返回类型
     * @throws
     * @Method: getNewPhoneByCollege
     * @Description: 按学校查询当天新注册号码
     * @author ZXY
     * @date 2016-04-16 09:13:24
     **/
    @ResponseBody
    @RequestMapping(value = "/getNewNum", method = RequestMethod.POST)
    public void getNewPhoneByCollege(HttpServletRequest request, HttpServletResponse response) {
        try {
            String collegeId = request.getParameter("collegeId");
            String reportDate = request.getParameter("reportDate");
            writeJson(reportThirdParty.getNewPhoneByCollege(collegeId, reportDate), response);
        } catch (Exception e) {
        }
    }
}
