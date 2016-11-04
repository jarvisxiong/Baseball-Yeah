package com.rofour.baseball.controller.order;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.UserInfo;
import com.rofour.baseball.controller.model.user.ExpUserAuditInfo;
import com.rofour.baseball.controller.model.user.UserCheckInfo;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.dao.user.bean.UserSmsModelBean;
import com.rofour.baseball.service.order.OrderVerifyService;
import com.rofour.baseball.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
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
 * @ClassName: OrderVerifyController
 * @Description: 众包审核控制层
 * @author ZhangLei
 * @date 2016年5月26日 下午5:10:24
 *
 */

@Controller
@RequestMapping(value = "/order/verify")
public class OrderVerifyController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("orderVerifyService")
    OrderVerifyService orderVerifyService;


    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getauditusers")
    public void getAuditUsers(HttpServletRequest request, HttpServletResponse response, UserCheckInfo userInfo) {
        if (StringUtils.isBlank(userInfo.getSort())) {
            userInfo.setSort("signupTime");//默认以注册时间排序
            userInfo.setOrder("desc");//默认倒序
        }

        DataGrid<UserCheckInfo> grid = new DataGrid<UserCheckInfo>();
        try {
            List<UserCheckInfo> menuList = orderVerifyService.getAuditUsers(userInfo);
            grid.setRows(menuList);
            grid.setTotal(orderVerifyService.getAuditUsersCount(userInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    @ResponseBody
    @RequestMapping(value = "/getManageUsers")
    public void getManageUsers(HttpServletRequest request, HttpServletResponse response, UserCheckInfo userInfo) {
        if (StringUtils.isBlank(userInfo.getSort())) {
            userInfo.setSort("signupTime");//默认以注册时间排序
            userInfo.setOrder("desc");//默认倒序
        }

        DataGrid<UserCheckInfo> grid = new DataGrid<UserCheckInfo>();
        try {
            List<UserCheckInfo> menuList = orderVerifyService.getManageUsers(userInfo);
            grid.setRows(menuList);
            grid.setTotal(orderVerifyService.getManageUsersCount(userInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }


    @RequestMapping(value = "/getUserSmsModel", method = RequestMethod.POST)
    public void getUserSmsModel(HttpServletRequest request, HttpServletResponse response, @RequestBody UserSmsModelBean userInfo) {
        if (StringUtils.isBlank(userInfo.getSort())) {
            userInfo.setSort("templateName");//默认以注册时间排序
            userInfo.setOrder("desc");//默认倒序
        }
        DataGrid<UserSmsModelBean> grid = new DataGrid<UserSmsModelBean>();
        try {
            List<UserSmsModelBean> menuList = userService.getUserSmsModel(userInfo);
            grid.setRows(menuList);
            grid.setTotal(userService.getUserSmsModelCount(userInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }


    @RequestMapping(value = "/getUserSms", method = RequestMethod.POST)
    public void getUserSms(HttpServletRequest request, HttpServletResponse response, @RequestBody UserSmsModelBean userInfo) {
        if (StringUtils.isBlank(userInfo.getSort())) {
            userInfo.setSort("submitTime");
            userInfo.setOrder("desc");//默认倒序
        }
        DataGrid<UserSmsModelBean> grid = new DataGrid<UserSmsModelBean>();
        try {
            List<UserSmsModelBean> menuList = userService.getUserSms(userInfo);
            grid.setRows(menuList);
            grid.setTotal(userService.getUserSmsCount(userInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }


    /**
     * 用户审核
     * @return
     */
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo audit(HttpServletRequest request, ExpUserAuditInfo userAuditInfo) {
        try {
            ResultInfo result = checkAuditParam(userAuditInfo);
            if (null != result) {
                return result;
            }
            UserManagerLoginBean user = getCurrent(request);
            userAuditInfo.setVerifyUserId(user.getUserManagerId());
            userAuditInfo.setVerifyUserName(user.getUserName());
            return orderVerifyService.expAudit(userAuditInfo, user, request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }

    @RequestMapping(value = "/activation", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo activation(HttpServletRequest request, UserCheckInfo userInfo) {
        try {
            return orderVerifyService.activation(userInfo, request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }

    /**
     * 负责人审核信息
     * @return
     */
    @RequestMapping(value = "/getauditinfo")
    @ResponseBody
    public ResultInfo getAudit(HttpServletRequest request, UserInfo userInfo) {
        try {
            return orderVerifyService.getAudit(userInfo.getUserId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ResultInfo(-1, "", "查询负责人审核信息失败！");
    }

    /**
     * 负责人审核参数校验
     * @param userAuditInfo
     * @return
     */
    private ResultInfo checkAuditParam(ExpUserAuditInfo userAuditInfo) {
        if (null == userAuditInfo) {
            return new ResultInfo(-1, "111", "传入参数不能为空");
        } else if (null == userAuditInfo.getUserId() || null == userAuditInfo.getVerifyStatus()) {
            return new ResultInfo(-1, "111", "传入参数不能为空");
        }
        return null;
    }

    /**
     * @Description: 审核给小派打标签
     * @param
     * @return
     * @throws
     */
    @RequestMapping(value = "/setlabel")
    @ResponseBody
    public ResultInfo setLabel(HttpServletRequest request) throws Exception {
        ResultInfo resultInfo = null;
        String pUserId = request.getParameter("pUserId");
        String remark = request.getParameter("remark");
        if (StringUtils.isBlank(pUserId) || StringUtils.isBlank(remark)) {
            resultInfo = new ResultInfo(-1, "111", "传入参数不能为空");
        } else {
            orderVerifyService.setLabel(Long.parseLong(pUserId), remark);
            resultInfo = new ResultInfo(0, "", "");
        }
        return resultInfo;
    }
}
