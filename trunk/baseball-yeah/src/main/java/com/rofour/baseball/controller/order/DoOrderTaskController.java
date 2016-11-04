package com.rofour.baseball.controller.order;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.Constant;
import com.rofour.baseball.common.HttpClientUtils;
import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.common.StringUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.order.DoOrderTaskDetailInfo;
import com.rofour.baseball.controller.model.order.DoOrderTaskResultInfo;
import com.rofour.baseball.controller.model.order.DoOrderTaskSearchInfo;
import com.rofour.baseball.controller.model.order.TbSysTaskOrderInfo;
import com.rofour.baseball.dao.order.bean.TbSysTaskOrder;
import com.rofour.baseball.dao.order.mapper.TbSysTaskOrderMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.order.TaskOrderService;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.SystemColor.info;

/**
 * Created by Administrator on 2016-08-24.
 */
@Controller
@RequestMapping("/order/doordertask")
public class DoOrderTaskController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource(name = "taskOrderService")
    TaskOrderService taskOrderService;


    @Resource(name = "tbSysTaskOrderMapper")
    private TbSysTaskOrderMapper tbSysTaskOrderMapper;

    /**
     * 查询做单任务列表
     *
     * @param request
     * @param response
     * @param info
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public void getDoOrderTaskList(HttpServletRequest request, HttpServletResponse response, DoOrderTaskSearchInfo info) {
        if (StringUtils.isEmpty(info.getSort())) {
            info.setSort("auditTime");
            info.setOrder("desc");
        }
        DataGrid<DoOrderTaskResultInfo> grid = new DataGrid<>();
        try {

            List<DoOrderTaskResultInfo> list = taskOrderService.getDoOrderTaskList(info);
            grid.setRows(list);
            grid.setTotal(taskOrderService.selectDoOrderTaskCount(info));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    /**
     * 详情
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public DoOrderTaskDetailInfo detail(@PathVariable String orderId, HttpServletResponse response) {
        try {
            if (orderId != null) {
                writeJson(taskOrderService.getDoOrderTaskDetail(orderId), response);
//                return taskOrderService.getDoOrderTaskDetail(orderId);
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 保存审核结果
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo save(HttpServletRequest request) {
        try {
            String data = request.getParameter("data");
            TbSysTaskOrderInfo info = JsonUtils.readValue(data, TbSysTaskOrderInfo.class);
            info.setAcceptDate(info.getReceiveTime());
            return taskOrderService.saveAuditResult(info);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResultInfo(-1, "", "服务器繁忙，请重试");
        }
    }

    /**
     * 提交审核结果并评分,更改订单状态为审核
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo submit(HttpServletRequest request) {
        try {
            String data = request.getParameter("data");
            TbSysTaskOrderInfo info = JsonUtils.readValue(data, TbSysTaskOrderInfo.class);
            ResultInfo resultInfo = taskOrderService.submitAuditResult(info, request);
            if (resultInfo.getSuccess() == 0) {
                //调用任务结算接口
                final TypeReference<ResultInfo<?>> typeRef = new TypeReference<ResultInfo<?>>() {
                };
                String url = Constant.axpurl.get("do_task_settle");
                ResultInfo<?> postData;
                try {

                    Map<String, Object> parameter = new HashMap<>();
                    parameter.put("bizId", info.getOrderId());
                    parameter.put("totalAmount", info.getTotalMoney());
                    parameter.put("payAmount", resultInfo.getData());
                    parameter.put("packetUserId", info.getWinnerId());
                    postData = (ResultInfo<?>) HttpClientUtils.post(url, parameter, typeRef);
                    if (postData == null) {
                        logger.error("调用AXP接口失败");
                        return new ResultInfo(-1, "", "调用接口失败");
                    }
                    if (postData.getSuccess() != 0) {
                        logger.error("调用AXP接口失败:" + postData.getCode() + "," + postData.getMessage());
                        return new ResultInfo(-1, "", "调用接口失败:" + postData.getMessage());
                    }
                    logger.info("是否调用成功:" + postData.getSuccess());

                } catch (IOException e) {
                    throw new BusinessException("104");
                }
            }
            return resultInfo;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResultInfo(-1, "", "服务器繁忙，请重试");
        }
    }
}
