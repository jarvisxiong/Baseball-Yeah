package com.zhiduan.axp.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.zhiduan.axp.common.CipherPwdUtils;
import com.zhiduan.axp.controller.model.UserModel;
import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;
import com.zhiduan.axp.service.manager.MenuService;
import com.zhiduan.axp.service.user.LoginService;
import com.zhiduan.axp.service.user.UserManagerLoginService;
import com.zhiduan.axp.service.user.UserManagerService;


@Controller
public class LoginController {

    @Autowired
    @Qualifier("loginService")
    private LoginService loginService;

    @Autowired
    @Qualifier("tbMenuService")
    private MenuService tbMenuService;

    @Autowired
    @Qualifier("userManagerLoginService")
    private UserManagerLoginService userManagerLoginService;

    @Autowired
    @Qualifier("userManagerService")
    private UserManagerService userManagerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcome(HttpServletRequest request) {

        //判断session是否存在
        UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
        if (sessionUser == null) {
            return new ModelAndView("/login");
        } else {
            Integer usersTotal = userManagerService.getUsersTotal();
            request.setAttribute("userTotal", usersTotal);
            Integer todaySMS = userManagerService.getTodaySMSTotal();
            request.setAttribute("todaySMS", todaySMS);
            Integer todayNewPhoneTotal = userManagerService.getTodayNewPhoneTotal();
            request.setAttribute("todayNewPhoneTotal", todayNewPhoneTotal);
            Integer todayNewOrderTotal = userManagerService.getTodayNewOrderTotal();
            request.setAttribute("todayNewOrderTotal", todayNewOrderTotal);
            RequestContextUtils.getWebApplicationContext(request).getServletContext().setAttribute("menuList", tbMenuService.selectAllForRole());
            return new ModelAndView("index", "command", sessionUser);
        }
    }

    /**
     * @param @return 参数
     * @return ModelAndView 返回类型
     * @throws Exception
     * @throws @author
     * @Method: login
     * @Description: 登录页面跳转
     * @date 2016年4月8日 上午9:18:46
     **/

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, UserModel user) throws Exception {
        //判断session是否存在
        UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
        if (sessionUser == null) {
            if (StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getPassword())) {
                return new ModelAndView("redirect:/");
            }
        } else {
            Integer usersTotal = userManagerService.getUsersTotal();
            request.setAttribute("userTotal", usersTotal);
            Integer todaySMS = userManagerService.getTodaySMSTotal();
            request.setAttribute("todaySMS", todaySMS);
            Integer todayNewPhoneTotal = userManagerService.getTodayNewPhoneTotal();
            request.setAttribute("todayNewPhoneTotal", todayNewPhoneTotal);
            Integer todayNewOrderTotal = userManagerService.getTodayNewOrderTotal();
            request.setAttribute("todayNewOrderTotal", todayNewOrderTotal);
            RequestContextUtils.getWebApplicationContext(request).getServletContext().setAttribute("menuList", tbMenuService.selectAllForRole());

            return new ModelAndView("index", "command", sessionUser);
        }

        UserManagerLoginBean userManager = userManagerLoginService.login(user);
        if (userManager != null) {
            userManager.setUserName(user.getName());
        }
        ModelAndView mod = new ModelAndView("login");
        mod.addObject("userName", user.getName());
        if (userManager == null) {
            mod.addObject("msg", "用户不存在");
            return mod;
        } else if (userManager.getBeDeleted() != 0) {
            mod.addObject("msg", "用户已注销");
            return mod;
        } else if (!StringUtils.equals(userManager.getPassword(), CipherPwdUtils.cipherPwd(user.getPassword()))) {
            mod.addObject("msg", "用户密码不正确");
            return mod;
        }
        String errInfo = "";
        request.setAttribute("user", userManager);

        //shiro加入身份验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            errInfo = "身份验证失败！";
        }
        request.getSession().setAttribute("user", userManager);
        Integer usersTotal = userManagerService.getUsersTotal();
        request.setAttribute("userTotal", usersTotal);
        /*Integer usersTodayTotal=userManagerService.getTodayUsersTotal();
        request.setAttribute("todayUser",usersTodayTotal);*/
        Integer todaySMS = userManagerService.getTodaySMSTotal();
        request.setAttribute("todaySMS", todaySMS);
        Integer todayNewPhoneTotal = userManagerService.getTodayNewPhoneTotal();
        request.setAttribute("todayNewPhoneTotal", todayNewPhoneTotal);
        Integer todayNewOrderTotal = userManagerService.getTodayNewOrderTotal();
        request.setAttribute("todayNewOrderTotal", todayNewOrderTotal);
        RequestContextUtils.getWebApplicationContext(request).getServletContext().setAttribute("menuList", tbMenuService.selectAllForRole());

		/*ModelAndView modelAndView=new ModelAndView(new RedirectView("index.jsp"));
        modelAndView.addObject("command", userManager);
		return modelAndView;*/
        return new ModelAndView("index", "command", userManager);
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public ModelAndView loginOut(HttpServletRequest request, UserModel user) throws Exception {
        request.getSession().removeAttribute("user");
        return new ModelAndView("/login");
    }

    @RequestMapping(value = "/noPer", method = RequestMethod.GET)
    public ModelAndView error() {
        return new ModelAndView("error/noPermission");
    }

}
