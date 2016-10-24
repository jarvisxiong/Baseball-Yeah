/**
 * Copyright (c) 2016, 指端科技.
 */
package com.zhiduan.axp.controller.message;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.service.message.SmsModelForUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: SmsModelForUserController
 * @Description: 用户自定义模板服务控制器
 * @author: xulang
 * @date: 2016年09月20日 13:30
 */
@Controller
@RequestMapping("/message/usersmsmodel")
public class SmsModelForUserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("smsModelForUserService")
    private SmsModelForUserService smsModelForUserService;

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delMsgTmplSms(HttpServletRequest request, HttpServletResponse response, Long smsModelId) {
        ResultInfo result = new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        if (smsModelId == null) {
            result.setSuccess(-1);
            result.setMessage("操作失败，参数smsModelId不能为空!");
        }
        try {
            smsModelForUserService.delete(smsModelId);
        } catch (Exception e) {
            result.setSuccess(-1);
            result.setMessage("操作失败，删除数据失败!");
            logger.error(e.getMessage(), logger);
        }
        return result;
    }
}
