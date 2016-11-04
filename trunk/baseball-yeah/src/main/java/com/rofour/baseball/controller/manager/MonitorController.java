/**
 * Copyright (c) 2016, 指端科技.
 */
package com.rofour.baseball.controller.manager;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.MonitorInfo;
import com.rofour.baseball.service.manager.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: MonitorController
 * @Description: 监控项维护控制器
 * @author: xulang
 * @date: 2016年08月22日 16:51
 */
@Controller("monitorController")
@RequestMapping("/manage/monitor/")
public class MonitorController extends BaseController {
    @Autowired
    @Qualifier("monitorService")
    private MonitorService monitorService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 查询全部
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    private void selectList(HttpServletRequest request, HttpServletResponse response) {
        List<MonitorInfo> list = null;
        // DataGrid<DeptInfo> grid = new DataGrid<DeptInfo>();
        try {
            //Integer limit = Integer.valueOf(request.getParameter("limit"));
            //Integer offset = Integer.valueOf(request.getParameter("offset"));
            String monitorName = request.getParameter("monitorName");
            list = monitorService.selectAll(monitorName);
            // grid.setRows(list);
            // grid.setTotal(deptService.selectTotalCount());
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        logger.info("查询监控项成功");
        writeJson(list, response);
    }

    /**
     * 新增
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public ResultInfo addMonitor(HttpServletRequest request, HttpServletResponse response,@RequestBody MonitorInfo info) {
        logger.info("开始添加监控项信息[data:" + info + "]");
        try {
            monitorService.addMonitor(info);
            return new ResultInfo(0, "", "添加成功");
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     * 删除
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteMonitor(HttpServletRequest request, HttpServletResponse response) {
        logger.info("开始删除监控项信息");
        String monitorId = request.getParameter("monitorId");
        if (monitorId == null || !monitorId.matches("^\\d+$")) {
            logger.error("传入参数错误");
            return new ResultInfo(-1, "111", getMessage("111"));
        }
        try {
            monitorService.deleteById(Long.valueOf(monitorId));
            return new ResultInfo(0, "", "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }

    /**
     * 更新
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public ResultInfo updateMonitor(HttpServletRequest request, HttpServletResponse response, @RequestBody MonitorInfo info) {
        logger.info("开始更新监控项信息[data:" + info + "]");
        try {
            monitorService.updateMonitor(info);
            return new ResultInfo(0, "", "修改操作成功");
        } catch (Exception e) {
            return processException(e, logger);
        }
    }
}
