package com.rofour.baseball.controller.wallet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.wallet.UserDrawCheckInfo;
import com.rofour.baseball.controller.user.FeedBackController;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.wallet.WalletDrawService;

@Controller
@RequestMapping(value = "/wallet/draw")
public class WalletDrawController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(FeedBackController.class);

    @Autowired
    @Qualifier(value = "walletDrawService")
    WalletDrawService walletDrawService;

    /**
     * @param request
     * @param response
     * @Description: 查询用户提现申请信息
     */
    @RequestMapping(value = "/getdrawlist", method = RequestMethod.GET)
    public void getUserDrawList(HttpServletRequest request, HttpServletResponse response) {
        try {
            String thdType = request.getParameter("thdType");
            String state = request.getParameter("state");
            String userId = request.getParameter("userId");
            String checkerId = request.getParameter("checkerId");
            String thdFlowId = request.getParameter("thdFlowId");
            String applybgnTime = request.getParameter("applybgnTime");
            String applyendTime = request.getParameter("applyendTime");
            String checkbgnTime = request.getParameter("checkbgnTime");
            String checkendTime = request.getParameter("checkendTime");
            String arrivebgnTime = request.getParameter("arrivebgnTime");
            String arriveendTime = request.getParameter("arriveendTime");
            Integer limit = Integer.valueOf(request.getParameter("limit"));
            Integer offset = Integer.valueOf(request.getParameter("offset"));
            String sort = request.getParameter("sort");
            String order = request.getParameter("order");
            if (StringUtils.isBlank(sort)) {
                sort = "createTime";
            }
            if (StringUtils.isBlank(order)) {
                order = "asc";
            }
            List<UserDrawCheckInfo> list = walletDrawService.getUserDrawList(thdType, state, userId, checkerId,
                    thdFlowId, applybgnTime, applyendTime, checkbgnTime, checkendTime, arrivebgnTime, arriveendTime,
                    limit, offset, sort, order);
            int totalCount = walletDrawService.getUserDrawListCount(thdType, state, userId, checkerId, thdFlowId,
                    applybgnTime, applyendTime, checkbgnTime, checkendTime, arrivebgnTime, arriveendTime);
            DataGrid<UserDrawCheckInfo> grid = new DataGrid<UserDrawCheckInfo>();
            grid.setRows(list);
            grid.setTotal(totalCount);
            writeJson(grid, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
    }

    /**
     * @return
     * @Description: 获取交易类型
     */
    @RequestMapping(value = "/getthdpayment", method = RequestMethod.POST)
    public void getThdPaymentType(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<SelectModel> mapList = walletDrawService.getThdPaymentType();
            writeJson(mapList, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
    }

    /**
     * @param request
     * @param response
     * @Description: 提现审核
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public void checkUserDraw(HttpServletRequest request, HttpServletResponse response) {
        try {

            String exchangeId = request.getParameter("exchangeId");
            String isok = request.getParameter("isok");
            String flowId = request.getParameter("flowId");
            UserManagerLoginBean user = getCurrent(request);
            String oprationId = String.valueOf(user.getUserManagerId());
            String oprationName = user.getUserName();

            String[] flowIds = flowId.split(",");
            String[] exchangeIds = exchangeId.split(",");
            for (int i = 0; i < flowIds.length; i++) {
//                walletDrawService.BatchUserDrawCheck(isok, oprationId, oprationName, request, flowIds[i], exchangeIds[i]);
                walletDrawService.userDrawCheck(exchangeIds[i], isok, oprationId, oprationName, flowIds[i], request);
            }


////
//			if (!exchangeId.contains("]")) {
//				walletDrawService.userDrawCheck(exchangeId, isok, oprationId, oprationName, flowId, request);
//			} else {
//				String[] exchangeIds = JsonUtils.readValue(exchangeId, String[].class);
//				walletDrawService.BatchUserDrawCheck(isok, oprationId, oprationName, request, flowId, exchangeIds);
//			}
            writeJson(new ResultInfo<Object>(0, "", ""), response);
        } catch (BusinessException be) {
            logger.error(be.getMessage(), logger);
            writeJson(new ResultInfo<Object>(-1, be.getMessageCode(), be.getMessage()), response);
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
            writeJson(new ResultInfo<Object>(-1, "", "操作失败"), response);
        }
    }

    /**
     * @param request
     * @param response
     * @Description: 保存第三方交易流水
     */
    @RequestMapping(value = "/addthdid", method = RequestMethod.POST)
    public void addThdId(HttpServletRequest request, HttpServletResponse response) {
        try {
            String exchangeId = request.getParameter("exchangeId");
            String thdId = request.getParameter("thdId");
            String flowId = request.getParameter("flowId");
            walletDrawService.addThdId(exchangeId, thdId, flowId, request);
            writeJson(new ResultInfo<Object>(0, "", ""), response);
        } catch (BusinessException be) {
            writeJson(processException(be, logger), response);
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
            writeJson(new ResultInfo<Object>(-1, "", "操作失败"), response);
        }
    }

}
