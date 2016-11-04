/**
 * Copyright (c) 2016, 指端科技.
 */
package com.rofour.baseball.controller.message;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.message.SmsModelForUserInfo;
import com.rofour.baseball.controller.model.order.OrderInfo;
import com.rofour.baseball.controller.model.order.RequestOrderInfo;
import com.rofour.baseball.controller.model.user.User;
import com.rofour.baseball.dao.message.bean.SmsModelForUserBean;
import com.rofour.baseball.dao.order.bean.OrderDetailBean;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.message.SmsModelForUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;


/**
 * @ClassName: SmsModelForUserController
 * @Description: 用户自定义模板服务控制器
 * @author: xulang
 * @date: 2016年09月20日 13:30
 */
@Controller
@RequestMapping("/message/usersmsmodel")
public class SmsModelForUserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("smsModelForUserService")
    private SmsModelForUserService smsModelForUserService;

    /**
     * 短信模板审核页面初始化
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gotoSmsParasManage", method = RequestMethod.GET)
    public ModelAndView toMsgTypeParas(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("message/sms/smsAudit");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }


    /**
     * 获取短信列表
     * @param request
     * @param response
     * @param smsModelForUserBean
     */
    @RequestMapping(value = "/querySmsAuditList", method = RequestMethod.GET)
    @ResponseBody
    public void querySmsAuditList(HttpServletRequest request, HttpServletResponse response,SmsModelForUserBean smsModelForUserBean){
        DataGrid<SmsModelForUserBean> grid = new DataGrid<SmsModelForUserBean>();
        try {
            if(smsModelForUserBean == null){
                smsModelForUserBean = new SmsModelForUserBean();
            }
            List<SmsModelForUserBean> list = smsModelForUserService.getSmsAuditList(smsModelForUserBean);
            grid.setRows(list);
//            grid.setTotal(smsModelForUserService.getSmsAuditListTotal(smsModelForUserBean));
            grid.setTotal(smsModelForUserService.getSmsAuditListTotal(smsModelForUserBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeJson(grid, response);
    }

    /**
     * 根据短信模板ID查询短信模板内容
     * @param response
     * @param record
     * @return
     */
    @RequestMapping(value = "/smsAuditView", method = RequestMethod.POST)
    @ResponseBody
    public SmsModelForUserBean smsAuditView(HttpServletResponse response, SmsModelForUserBean record){
        SmsModelForUserBean smsModelForUserBean = null;
        try {
            smsModelForUserBean = smsModelForUserService.getSmsAuditView(record);
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        return smsModelForUserBean;
    }

    /**
     * 短信模板审核保存
     * @param request
     * @param response
     * @param record
     * @return
     */
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo auditSms(HttpServletRequest request, HttpServletResponse response,SmsModelForUserBean record){
        ResultInfo result = new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        if (record == null) {
            result.setSuccess(-1);
            result.setMessage("操作失败，参数smsModelId不能为空!");
            return result;
        }
        try {
            UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
            record.setAuditUserManagerId(sessionUser.getUserManagerId());
            smsModelForUserService.auditSms(record);
        } catch (Exception e) {
            result.setSuccess(-1);
            result.setMessage("操作失败，审核失败!");
            logger.error(e.getMessage(), logger);
        }
        return result;
    }

    @RequestMapping(value = "/batchAudit", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo batchAudit(HttpServletRequest request, HttpServletResponse response, SmsModelForUserBean record){
        ResultInfo result = new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        if (record == null) {
            result.setSuccess(-1);
            result.setMessage("操作失败，参数smsModelId不能为空!");
            return result;
        }
        try {
            UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
            record.setAuditUserManagerId(sessionUser.getUserManagerId());
            smsModelForUserService.batchAudit(record);
        } catch (Exception e) {
            result.setSuccess(-1);
            result.setMessage("操作失败，审核失败!");
            logger.error(e.getMessage(), logger);
        }
        return result;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delMsgTmplSms(HttpServletRequest request, HttpServletResponse response, SmsModelForUserBean record) {
        ResultInfo result = new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        if (record == null) {
            result.setSuccess(-1);
            result.setMessage("操作失败，参数smsModelId不能为空!");
            return result;
        }
        try {
            UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
            record.setUserId(sessionUser.getUserManagerId());
            smsModelForUserService.delUpdate(record);
        } catch (Exception e) {
            result.setSuccess(-1);
            result.setMessage("操作失败，删除数据失败!");
            logger.error(e.getMessage(), logger);
        }
        return result;
    }
}
