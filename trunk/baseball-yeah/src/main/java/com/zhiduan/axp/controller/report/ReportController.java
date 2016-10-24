package com.zhiduan.axp.controller.report;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhiduan.axp.controller.base.BaseController;

/**
 * @author ZhangLei
 * @ClassName: ReportController
 * @Description: 报表页面跳转控制层
 * @date 2016年5月5日 上午10:24:04
 */

@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {

    @RequestMapping(value = "/gotoMonitoringReport")
    public ModelAndView gotoMonitoringReport(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("report/monitoringReport/monitoringReport");
        } else {
            return new ModelAndView("/");
        }
    }

    @RequestMapping(value = "/gotoSchoolPhoneReport")
    public ModelAndView gotoSchoolPhoneReport(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("report/schoolPhoneReport/schoolPhoneReport");
        } else {
            return new ModelAndView("/");
        }
    }

    @RequestMapping(value = "/gotoSMSDayReport")
    public ModelAndView gotoSMSDayReport(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("report/SMSDayReport/SMSDayReport");
        } else {
            return new ModelAndView("/");
        }
    }

    @RequestMapping(value = "/gotoSMSReport")
    public ModelAndView gotoSMSReport(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("report/SMSReport/SMSReport");
        } else {
            return new ModelAndView("/");
        }
    }

    @RequestMapping(value = "/gotoPhoneDataReport")
    public ModelAndView gotoPhoneDataReport(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("report/phoneCityReport/phoneCityReport");
        } else {
            return new ModelAndView("/");
        }
    }

    @RequestMapping(value = "/gotoOfflineStoreReport")
    public ModelAndView gotoOfflineStoreReport(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("report/offlineStoreReport/offlineStoreReport");
        } else {
            return new ModelAndView("/");
        }
    }

    @RequestMapping(value = "/gotoUserSmsModelReport")
    public ModelAndView gotoUserSmsModelReport(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("report/userSmsModelReport/UserSmsModelReport");
        } else {
            return new ModelAndView("/");
        }
    }
}
