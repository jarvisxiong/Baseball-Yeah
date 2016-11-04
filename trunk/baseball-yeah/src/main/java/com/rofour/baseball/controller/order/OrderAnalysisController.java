package com.rofour.baseball.controller.order;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rofour.baseball.common.DateUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.service.order.OrderAnalysisService;

/**
 * 订单运营统计分析
 * 
 * @author wuzhiquan
 *
 */
@Controller
@RequestMapping("/order/analysis")
public class OrderAnalysisController extends BaseController {

    @Autowired
    @Qualifier("orderAnalysisService")
    OrderAnalysisService orderAnalysisService;

    /**
     * 订单运营统计分析页面
     * 
     * @param request
     * @return
     * @throws Exception
     * @author wuzhiquan
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) throws Exception {
        if (null != request.getSession().getAttribute("user")) {
            return new ModelAndView("order/analysis/index");
        } else {
            return new ModelAndView("error/noPermission");
        }
    }

    /**
     * 订单统计分析
     * 
     * @param request
     * @param response
     * @author wuzhiquan
     */
    @ResponseBody
    @RequestMapping(value = "/getOrder", method = RequestMethod.POST)
    public void getOrder(HttpServletRequest request, HttpServletResponse response) {
//        String yesterday = DateUtils.format(DateUtils.getYesterday(), "yyyy-MM-dd");
        String yesterday = "2016-10-28";
        Map<String, Object> map = orderAnalysisService.getOrder(yesterday);
        writeJson(map, response);
    }

    /**
     * 各时段订单统计分析
     * 
     * @param request
     * @param response
     * @author wuzhiquan
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderByHours", method = RequestMethod.POST)
    public void getOrderByHours(HttpServletRequest request, HttpServletResponse response) {
//        String yesterday = DateUtils.format(DateUtils.getYesterday(), "yyyy-MM-dd");
        String yesterday = "2016-10-28";
        List<Map<String, Object>> list = orderAnalysisService.getOrderByHours(yesterday);
        writeJson(list, response);
    }

    /**
     * 前10天订单金额统计分析
     * 
     * @param request
     * @param response
     * @author wuzhiquan
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderAmountByTenDay", method = RequestMethod.POST)
    public void getOrderAmountByTenDay(HttpServletRequest request, HttpServletResponse response) {
//        List<Map<String, Object>> list = orderAnalysisService.getOrderAmountByTenDay(getTenDaysBefore());
        List<Map<String, Object>> list = orderAnalysisService.getOrderAmountByTenDay("2016-10-19");
        writeJson(list, response);
    }

    /**
     * 前10天订单类型统计分析
     * 
     * @param request
     * @param response
     * @author wuzhiquan
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderTypeByTenDay", method = RequestMethod.POST)
    public void getOrderTypeByTenDay(HttpServletRequest request, HttpServletResponse response) {
//        List<Map<String, Object>> list = orderAnalysisService.getOrderTypeByTenDay(getTenDaysBefore());
        List<Map<String, Object>> list = orderAnalysisService.getOrderTypeByTenDay("2016-10-19");
        writeJson(list, response);
    }

    /**
     * 各地区订单统计分析
     * 
     * @param request
     * @param response
     * @author wuzhiquan
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderByAreas", method = RequestMethod.POST)
    public void getOrderByAreas(HttpServletRequest request, HttpServletResponse response) {
//        String yesterday = DateUtils.format(DateUtils.getYesterday(), "yyyy-MM-dd");
        String yesterday = "2016-10-28";
        List<Map<String, Object>> list = orderAnalysisService.getOrderByAreas(yesterday);
        writeJson(list, response);
    }

    /**
     * 前10天订单小派统计分析
     * 
     * @param request
     * @param response
     * @author wuzhiquan
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderWinnerByTenDay", method = RequestMethod.POST)
    public void getOrderWinnerByTenDay(HttpServletRequest request, HttpServletResponse response) {
//        List<Map<String, Object>> list = orderAnalysisService.getOrderWinnerByTenDay(getTenDaysBefore());
        List<Map<String, Object>> list = orderAnalysisService.getOrderWinnerByTenDay("2016-10-19");
        writeJson(list, response);
    }

    /**
     * 获取前10天
     * 
     * @return
     * @author wuzhiquan
     */
    private String getTenDaysBefore() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -10);
        return DateUtils.format(c.getTime(), "yyyy-MM-dd");
    }
}
