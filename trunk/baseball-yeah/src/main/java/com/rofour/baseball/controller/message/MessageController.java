/**
 * Copyright (c) 2016, 指端科技.
 */
package com.rofour.baseball.controller.message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: MessageController
 * @Description: 消息控制层
 * @author: xulang
 * @date: 2016年09月05日 13:48
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    @RequestMapping(value = "/gotoMsgTypeParasManage", method = RequestMethod.GET)
    public ModelAndView toMsgTypeParas(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("message/msgTypeParas/msgTypeParaManager");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }

    @RequestMapping(value = "/gotoMsgTmplSmsManager", method = RequestMethod.GET)
    public ModelAndView toMsgTmplSms(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("message/MsgTmplSms/msgTmplSmsManager");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }

    @RequestMapping(value = "/gotoMsgTmplWxManager", method = RequestMethod.GET)
    public ModelAndView toMsgTmplWx(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("message/MsgTmplWx/msgTmplWxManager");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }

    @RequestMapping(value = "/gotoMsgTmplMymsgManager", method = RequestMethod.GET)
    public ModelAndView toMsgTmplMymsg(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("message/MsgTmplMymsg/msgTmplMymsgManager");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }
}
