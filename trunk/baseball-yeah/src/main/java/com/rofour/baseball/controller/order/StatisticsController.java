package com.rofour.baseball.controller.order;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.order.*;
import com.rofour.baseball.controller.model.wallet.RequestAcctFlow;
import com.rofour.baseball.service.statistic.StatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: StatisticsController
 * @Description: 统计信息控制器
 * @author ZXY
 * @date 2016/10/12 13:27
 */
@Controller
@RequestMapping(value = "/statistic")
public class StatisticsController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "statisticsService")
    private StatisticsService statisticsService;

    //跳转订单量页面
    @RequestMapping(value = "/gotoOrderCount", method = RequestMethod.GET)
    public ModelAndView gotoOrderCount(HttpServletRequest request) throws Exception {
        String packetUserId = request.getParameter("packetUserId");
        ModelAndView mav;
        if (request.getSession().getAttribute("user") != null) {
            mav = new ModelAndView("order/packetOrder/packetOrderCount");
            mav.addObject("packetUserId", packetUserId);
        } else {
            mav = new ModelAndView("/error/noPermission");
        }
        return mav;
    }

    //跳转评价页面
    @RequestMapping(value = "/gotoComment", method = RequestMethod.GET)
    public ModelAndView gotoComment(HttpServletRequest request) throws Exception {
        String packetUserId = request.getParameter("packetUserId");
        ModelAndView mav;
        if (request.getSession().getAttribute("user") != null) {
            mav = new ModelAndView("order/packetOrder/packetOrderComment");
            mav.addObject("packetUserId", packetUserId);
        } else {
            mav = new ModelAndView("/error/noPermission");
        }
        return mav;
    }

    //跳转账户余额页面
    @RequestMapping(value = "/gotoAcctFlow", method = RequestMethod.GET)
    public ModelAndView gotoAcctFlow(HttpServletRequest request) throws Exception {
        String packetUserId = request.getParameter("packetUserId");
        ModelAndView mav;
        if (request.getSession().getAttribute("user") != null) {
            mav = new ModelAndView("/wallet/acctFlow");
            mav.addObject("packetUserId", packetUserId);
        } else {
            mav = new ModelAndView("/error/noPermission");
        }
        return mav;
    }

    //获取小派订单统计列表
    @RequestMapping(value = "/getOrderStatis", method = RequestMethod.GET)
    public void getOrderStatis(HttpServletRequest request, HttpServletResponse response, RequestOrderStatis requestOrderStatis) throws Exception {
        logger.debug("获取小派订单统计列表");
        if (requestOrderStatis == null) {
            logger.error("传入参数为空");
        } else {
            DataGrid<OrderStatisInfo> grid = new DataGrid<>();
            try {
                Map retMap = statisticsService.getOrderStatis(requestOrderStatis);

                grid.setRows((List<OrderStatisInfo>) retMap.get("list"));
                grid.setTotal(Integer.parseInt(retMap.get("count").toString()));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            writeJson(grid, response);
        }
    }

    //获取订单评价列表
    @RequestMapping(value = "/getOrderComment", method = RequestMethod.GET)
    public void getOrderComment(HttpServletRequest request, HttpServletResponse response, RequestOrderComment requestOrderComment) throws Exception {
        logger.debug("获取订单评价列表");
        if (requestOrderComment == null) {
            logger.error("传入参数为空");
        } else {
            DataGrid<OrderCommentInfo> grid = new DataGrid<>();
            try {
                Map retMap = statisticsService.getOrderComment(requestOrderComment);

                grid.setRows((List<OrderCommentInfo>) retMap.get("list"));
                grid.setTotal(Integer.parseInt(retMap.get("count").toString()));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            writeJson(grid, response);
        }
    }

    //获取订单评价详情
    @RequestMapping(value = "/getCommentDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getCommentDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("获取订单评价详情");
        ResultInfo resultInfo = null;
        String appraiseId = request.getParameter("appraiseId");
        Object obj = null;
        if (StringUtils.isBlank(appraiseId)) {
            resultInfo = new ResultInfo(-1, "111", "传入参数不能为空");
        } else {
            try {
                OrderAppraiseInfo orderAppraiseInfo = statisticsService.getOrderCommentDetail(Long.parseLong(appraiseId));
                resultInfo = new ResultInfo(0, "", "查询成功", orderAppraiseInfo);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return resultInfo;
    }

    //获取账户明细列表
    @RequestMapping(value = "/getAcctFlow", method = RequestMethod.GET)
    public void getAcctFlow(HttpServletRequest request, HttpServletResponse response, RequestAcctFlow requestAcctFlow) throws Exception {
        logger.debug("获取账户明细列表");
        if (requestAcctFlow == null) {
            logger.error("传入参数为空");
        } else {
            DataGrid<OrderStatisInfo> grid = new DataGrid<>();
            try {
                Map retMap = statisticsService.getAcctFlow(requestAcctFlow);

                grid.setRows((List<OrderStatisInfo>) retMap.get("list"));
                grid.setTotal(Integer.parseInt(retMap.get("count").toString()));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            writeJson(grid, response);
        }
    }

    /**
     * json 序列化输出
     * @param grid
     * @param response
     */
    public void writeJson(Object grid, HttpServletResponse response) {
        try {
            String json = JsonUtils.translateToJson(grid);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
