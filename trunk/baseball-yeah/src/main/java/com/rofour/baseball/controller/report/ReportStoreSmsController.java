package com.rofour.baseball.controller.report;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rofour.baseball.controller.model.report.ReportUserSmsModelInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.report.ReportStoreSmsInfo;
import com.rofour.baseball.controller.model.report.SearchStoreSmsDetailInfo;
import com.rofour.baseball.controller.model.report.SearchStoreSmsTotalInfo;
import com.rofour.baseball.service.report.ReportStoreSmsService;

/**
 * @author xl
 * @ClassName: ReportStoreSmsController
 * @Description: 货源日报表控制器
 * @date 2016年4月21日 下午1:56:37
 */
@Controller
@RequestMapping("/report/storesms")
public class ReportStoreSmsController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("messageSource")
    private MessageSource messageSource;

    @Autowired
    @Qualifier("reportStoreSmsService")
    private ReportStoreSmsService reportStoreSms;

    @ResponseBody
    @RequestMapping(value = "/dayReport", method = RequestMethod.GET)
    public void SelectDayReport(HttpServletRequest request, HttpServletResponse response) {
        DataGrid<ReportStoreSmsInfo> grid = new DataGrid<>();
        List<ReportStoreSmsInfo> infoList = new ArrayList<>();
        try {
            String storeName = request.getParameter("storename");
            String storeId = request.getParameter("storeid");
            String supervisor = request.getParameter("supervisor");
            String expressId = request.getParameter("expressid");
            String startDate = request.getParameter("startdate");
            String endDate = request.getParameter("enddate");
            String status = request.getParameter("status");
            Integer limit = Integer.valueOf(request.getParameter("limit"));
            Integer offset = Integer.valueOf(request.getParameter("offset"));
            infoList = reportStoreSms.getStoreSmsDayReport(storeName, storeId, supervisor, expressId, startDate,
                    endDate, status, limit, offset);
            int totalCount = reportStoreSms.getStoreSmsReportCount(storeName, storeId, supervisor, expressId, startDate,
                    endDate, status);
            grid.setRows(infoList);
            grid.setTotal(totalCount);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    /**
     * @param request
     * @param response
     * @Description: 门店短信统计汇总
     */
    @RequestMapping(value = "storesmstotal", method = RequestMethod.GET)
    public void selectSiteSmsTotalInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            String storeId = request.getParameter("storeId");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            List<SearchStoreSmsTotalInfo> storeSmsTotalInfos = reportStoreSms.selectStoreSmsTotalInfo(storeId,
                    startDate, endDate);
            writeJson(storeSmsTotalInfos, response);
        } catch (Exception e) {
            logger.error("短信统计汇总：", e);
        }
    }

    /**
     * @param request
     * @param response
     * @Description: 短信统计明细
     */
    @RequestMapping(value = "storesmsdetail", method = RequestMethod.GET)
    public void selectSiteSmsDetailInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            String storeId = request.getParameter("storeId");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String status = request.getParameter("status");
            String beVirtual = request.getParameter("beVirtual");
            List<SearchStoreSmsDetailInfo> storeSmsTotalInfos = reportStoreSms.searchStoreSmsDetails(storeId, startDate,
                    endDate, status, beVirtual);
            writeJson(storeSmsTotalInfos, response);
        } catch (Exception e) {
            logger.error("短信统计明细：", e);
        }
    }

    /**
     * 查询用户自定义模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "smsmodelreport", method = RequestMethod.GET)
    public void selectUserSmsModelInfos(HttpServletRequest request, HttpServletResponse response) {
        DataGrid<ReportUserSmsModelInfo> grid = new DataGrid<ReportUserSmsModelInfo>();
        try {
            String storeId = request.getParameter("storeId");
            String userName = request.getParameter("userName");
            String phone = request.getParameter("phone");
            String offset = request.getParameter("offset");
            String limit = request.getParameter("limit");
            String modelContent = request.getParameter("modelContent");
            List<ReportUserSmsModelInfo> userSmsModelInfos = reportStoreSms.searchUserSmsModelList(storeId, userName,
                    phone,modelContent, Integer.valueOf(offset), Integer.valueOf(limit));
            grid.setRows(userSmsModelInfos);
            grid.setTotal(reportStoreSms.getUserSmsModelListCount(storeId, userName, phone,modelContent));
            writeJson(grid, response);
        } catch (Exception e) {
            logger.error("用户自定义模板列表：", e);
        }
    }
}
