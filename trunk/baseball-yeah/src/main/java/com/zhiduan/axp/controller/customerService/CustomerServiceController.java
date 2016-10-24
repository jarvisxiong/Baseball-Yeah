package com.zhiduan.axp.controller.customerService;

import com.zhiduan.axp.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016-06-20.
 */
@Controller
@RequestMapping("/customerservice")
public class CustomerServiceController extends BaseController {

    @RequestMapping(value="/waybilllog")
    public ModelAndView gotoEmployeeManager(HttpServletRequest request){
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("customerService/waybillLog/waybillLog");
        }else {
            return new ModelAndView("/error/noPermission");
        }
    }
    
    @RequestMapping(value="/versionManage")
    public ModelAndView gotoVersionManage(HttpServletRequest request){
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("customerService/versionHistroy/versionHistroy");
        }else {
            return new ModelAndView("/error/noPermission");
        }
    }
}
