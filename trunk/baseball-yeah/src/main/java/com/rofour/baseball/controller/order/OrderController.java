package com.rofour.baseball.controller.order;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.rofour.baseball.common.CipherPwdUtils;
import com.rofour.baseball.controller.model.UserInfo;
import com.rofour.baseball.controller.model.UserModel;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.manager.MenuService;
import com.rofour.baseball.service.user.LoginService;
import com.rofour.baseball.service.user.UserManagerLoginService;
import com.rofour.baseball.service.user.UserManagerService;

import java.util.HashMap;
import java.util.Map;


/**
 * @author ZhangLei
 * @ClassName: OrderController
 * @Description: 众包控制层
 * @date 2016年5月26日 下午5:10:50
 */

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @RequestMapping(value = "/gotoOrderVerify", method = RequestMethod.GET)
    public ModelAndView toWalletDrawMoney(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("order/orderVerify/orderVerifyManager");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }
    @RequestMapping(value = "/gotoAddTask", method = RequestMethod.GET)
    public ModelAndView gotoAddTask(HttpServletRequest request, UserInfo userInfo) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("order/taskOrder/add");
        }else {
            return new ModelAndView("/error/noPermission");
        }
    }
    @RequestMapping(value = "/gotoEditTask", method = RequestMethod.GET)
    public ModelAndView gotoEditTask(HttpServletRequest request, UserInfo userInfo) {

        if (request.getSession().getAttribute("user") != null) {
            ModelAndView modelAndView=new ModelAndView("order/taskOrder/edit");
            String taskId=request.getParameter("taskId");
            if (!StringUtils.isBlank(taskId)) {
                modelAndView.addObject("taskId", taskId);
            }
            return modelAndView;
        }else {
            return new ModelAndView("/error/noPermission");
        }
    }

    @RequestMapping(value = "/gotoOrderUserManager", method = RequestMethod.GET)
    public ModelAndView toOrderUserManager(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("order/orderUser/orderUserManager");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }

    @RequestMapping(value = "/gotoTaskOrderManager", method = RequestMethod.GET)
    public ModelAndView gotoTaskOrderManager(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("order/taskOrder/index");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }


    @RequestMapping(value = "/gotoPacketOrder", method = RequestMethod.GET)
    public ModelAndView gotoPacketOrder(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("order/packetOrder/index");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }

    @RequestMapping(value = "/gotoPacketStuManager", method = RequestMethod.GET)
    public ModelAndView toPacketStuManager(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView("order/packetStu/packetStuManager");
            String phone = request.getParameter("phone");
            if (!StringUtils.isBlank(phone)) {
                modelAndView.addObject("phone", phone);
            }
            return modelAndView;
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }

    @RequestMapping(value = "/gotoPickupOrderManager", method = RequestMethod.GET)
    public ModelAndView gotoPickupOrder(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("order/pickupOrder/index");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }
    @RequestMapping(value = "/gotoDoOrderTaskManager", method = RequestMethod.GET)
    public ModelAndView gotoDoOrderTaskManager(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("order/taskDoOrder/taskDoOrder");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }
    @RequestMapping(value = "/gotoDoOrderTaskAudit/{orderId}")
    public ModelAndView gotoDoOrderTaskAudit(HttpServletRequest request, @PathVariable String orderId) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            Map<String,String> map=new HashMap<>();
            map.put("orderId",orderId);
            return new ModelAndView("order/taskDoOrder/auditOrderTask",map);
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }
}
