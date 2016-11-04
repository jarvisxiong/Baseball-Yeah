package com.rofour.baseball.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.rofour.baseball.common.CipherPwdUtils;
import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.model.UserModel;
import com.rofour.baseball.dao.manager.bean.MenuBean;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.manager.MenuService;
import com.rofour.baseball.service.user.LoginService;
import com.rofour.baseball.service.user.UserManagerLoginService;
import com.rofour.baseball.service.user.UserManagerService;


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
            return new ModelAndView("login");
        } else {
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
        
        /*Integer usersTodayTotal=userManagerService.getTodayUsersTotal();
        request.setAttribute("todayUser",usersTodayTotal);*/
        
        RequestContextUtils.getWebApplicationContext(request).getServletContext().setAttribute("menuList", tbMenuService.selectAllForRole());

		/*ModelAndView modelAndView=new ModelAndView(new RedirectView("index.jsp"));
        modelAndView.addObject("command", userManager);
		return modelAndView;*/
        return new ModelAndView("index", "command", userManager);
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public ModelAndView loginOut(HttpServletRequest request, UserModel user) throws Exception {
        request.getSession().removeAttribute("user");
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/noPer", method = RequestMethod.GET)
    public ModelAndView error() {
        return new ModelAndView("error/noPermission");
    }

    /**
     * 获取二级菜单
     * @param request
     * @param response
     * @author wuzhiquan
     */
    @ResponseBody
    @RequestMapping(value = "/getChlidrenMenu", method = RequestMethod.POST)
    public void getChlidrenMenu(HttpServletRequest request, HttpServletResponse response) {
        String menuId = request.getParameter("menuId");
        UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
        List<MenuBean> list = null;
        if (null != sessionUser && StringUtils.isNotBlank(menuId)) {
            list = tbMenuService.getChlidrenMenu(sessionUser.getUserName(), menuId);
        }
        try {
            String json = JsonUtils.translateToJson(list);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取首页数据
     * @param request
     * @param response
     * @author wuzhiquan
     */
    @ResponseBody
    @RequestMapping(value = "/getIndexData", method = RequestMethod.POST)
    public void getIndexData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer usersTotal = userManagerService.getUsersTotal();
        map.put("userTotal", usersTotal);
        Integer todaySMS = userManagerService.getTodaySMSTotal();
        map.put("todaySMS", todaySMS);
        Integer todayNewPhoneTotal = userManagerService.getTodayNewPhoneTotal();
        map.put("todayNewPhoneTotal", todayNewPhoneTotal);
        Integer todayNewOrderTotal = userManagerService.getTodayNewOrderTotal();
        map.put("todayNewOrderTotal", todayNewOrderTotal);
        try {
            String json = JsonUtils.translateToJson(map);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
