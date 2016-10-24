package com.zhiduan.axp.controller.user;

import com.zhiduan.axp.common.AxpUtils;
import com.zhiduan.axp.common.CipherPwdUtils;
import com.zhiduan.axp.common.IpUtils;
import com.zhiduan.axp.common.PhoneUtils;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.StoreUserManagerInfo;
import com.zhiduan.axp.controller.model.UserInfo;
import com.zhiduan.axp.controller.model.user.AddresseeInfo;
import com.zhiduan.axp.controller.model.user.UserCheckInfo;
import com.zhiduan.axp.controller.model.user.UserNumber;
import com.zhiduan.axp.dao.order.bean.OrderStatiscs;
import com.zhiduan.axp.dao.user.bean.UserBean;
import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;
import com.zhiduan.axp.service.user.UserManagerService;
import com.zhiduan.axp.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ZhangLei
 * @ClassName: UserController
 * @Description: 用户中心控制层
 * @date 2016年4月25日 下午8:37:26
 */

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("userManagerService")
    private UserManagerService userManagerService;


    @RequestMapping(value = "storeUserList", method = RequestMethod.GET)
    public ModelAndView getAllStoreUsers(HttpServletRequest req, String startDate) {
        ModelAndView mView = new ModelAndView("storeUser/allStoreUser");
        if (!StringUtils.isBlank(startDate)) {
            mView.addObject("startDate", startDate);
        }
        return mView;
    }

    /**
     * @param @param  request
     * @param @param  userInfo
     * @param @return 参数
     * @return ModelAndView    返回类型
     * @throws
     * @Method: gotoIndex
     * @Description: 跳转到主页
     * @author ZhangLei
     * @date 2016年5月9日 上午10:18:40
     **/

    @RequestMapping(value = "gotoIndex", method = RequestMethod.GET)
    public ModelAndView gotoIndex(HttpServletRequest request, UserInfo userInfo) {
        if (request.getSession().getAttribute("user") != null) {
            int userTotal = userManagerService.getUsersTotal();
            /*Integer usersTodayTotal = userManagerService.getTodayUsersTotal();*/
            Integer todaySMS = userManagerService.getTodaySMSTotal();
            Integer todayNewPhoneTotal = userManagerService.getTodayNewPhoneTotal();
            Integer todayNewOrderTotal = userManagerService.getTodayNewOrderTotal();
            /*request.setAttribute("todayUser", usersTodayTotal);*/
            request.setAttribute("userTotal", userTotal);
            request.setAttribute("todaySMS", todaySMS);
            request.setAttribute("todayNewPhoneTotal", todayNewPhoneTotal);
            request.setAttribute("todayNewOrderTotal", todayNewOrderTotal);
            return new ModelAndView("common/indexContent");
        } else {
            return new ModelAndView("/");
        }
    }

    @ResponseBody
    @RequestMapping(value = "isLoginOut", method = RequestMethod.POST)
    public ResultInfo isLoginOut(HttpServletRequest request) {
        UserManagerLoginBean userManagerLoginBean = (UserManagerLoginBean) request.getSession().getAttribute("user");
        if (userManagerLoginBean == null) {
            return new ResultInfo<Object>(-1, "0", "用户会话过期");
        } else {
            return new ResultInfo<Object>(0, "0", "用户登录中");
        }
    }

    /*管理注册用户开始*/
    @RequestMapping(value = "GridData", method = RequestMethod.POST)
    public void GridData(HttpServletRequest req, HttpServletResponse response, @RequestBody UserInfo userInfo) {
        if (StringUtils.isBlank(userInfo.getSort())) {
            userInfo.setSort("signupTime");//默认以注册时间排序
            userInfo.setOrder("desc");//默认倒序
        }
        ModelAndView mView = userManagerService.getStoreUsers(userInfo);
        Integer total = userManagerService.getStoreUsersTotal(userInfo);
        mView.addObject("total", total);
        writeJson(mView.getModel(), response);
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public void addUser(HttpServletRequest req, UserInfo userInfo) {
        UserBean user = new UserBean();
        BeanUtils.copyProperties(userInfo, user);

    }

    @RequestMapping(value = "editUser", method = RequestMethod.POST)
    public void editUser(HttpServletRequest req, UserInfo userInfo) {
        UserBean user = new UserBean();
        BeanUtils.copyProperties(userInfo, user);

    }

    @ResponseBody
    @RequestMapping(value = "cancelUser", method = RequestMethod.POST)
    public ResultInfo<Object> cancelUser(HttpServletRequest req, HttpServletResponse response, UserInfo user) {
        int i = 0;
        for (Long userId : user.getUserIds()) {
            i = userManagerService.cancelUser(userId, req);
        }
        ResultInfo<Object> result = new ResultInfo<Object>();
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "删除用户成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "货源负责人不能删除");
        }
    }

    @ResponseBody
    @RequestMapping(value = "verify", method = RequestMethod.POST)
    public ResultInfo<Object> verifyUser(HttpServletRequest req, HttpServletResponse response, UserInfo userInfo) {
        int i = userManagerService.cancelUser(userInfo.getUserId(), req);
        ResultInfo<Object> result = new ResultInfo<Object>();
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "验证用户成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "验证用户失败");
        }
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/insertselective")
    public ResultInfo<Object> insertSelective(HttpServletRequest req, UserInfo userInfo, StoreUserManagerInfo storeUserManagerInfo) {
        try {
            // 用户手机是否为空
            if (StringUtils.isEmpty(storeUserManagerInfo.getPhone()) || StringUtils.isEmpty(storeUserManagerInfo.getAccountPwd())) {
                return new ResultInfo<Object>(-1, "111", "参数不能为空");
            }
            // 验证用户手机号码是否正确
            if (!PhoneUtils.isMobile(storeUserManagerInfo.getPhone()) || !AxpUtils.checkPwd(storeUserManagerInfo.getAccountPwd())) {
                return new ResultInfo<Object>(-1, "114", "参数格式不正确");
                // 验证用户密码是否合法，是否过短过长
            }
            storeUserManagerInfo.setAccountPwd(CipherPwdUtils.cipherPwd(storeUserManagerInfo
                    .getAccountPwd().trim()));
            // 转化补全userInfo信息
            // 设置用户名为手机号码
            storeUserManagerInfo.setUserName(storeUserManagerInfo.getPhone());
            // 设置注册IP地址
            storeUserManagerInfo.setSignupIp(IpUtils.getIpAddr(req));
            // 设置注册时间
            storeUserManagerInfo.setSignupTime(new Timestamp(System.currentTimeMillis()));
            // 设置账户状态为可用
            storeUserManagerInfo.setVerifyStatus((byte) 0);
            // 用户状态为未注销
            storeUserManagerInfo.setBeDeleted((byte) 0);
            return (ResultInfo<Object>) userManagerService.insertSortUserManager(storeUserManagerInfo, req);
        } catch (IOException e) {
            logger.error("解析用户信息失败");
            return new ResultInfo<Object>(-1, "103", "读取json参数失败");
        } catch (Exception e) {
            logger.error("更新用户失败");
            return new ResultInfo<Object>(-1, "SE001", "增加用户失败");
        }
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/updatebyprimarykeyselective")
    public ResultInfo<Object> updateByPrimaryKeySelective(HttpServletRequest req, StoreUserManagerInfo storeUserManagerInfo) {
        try {
            // 用户手机是否为空
            if (StringUtils.isEmpty(storeUserManagerInfo.getPhone())) {
                return new ResultInfo<Object>(-1, "111", "用户手机不能为空");
            }
            // 验证用户手机号码是否正确
            if (!PhoneUtils.isMobile(storeUserManagerInfo.getPhone())) {
                return new ResultInfo<Object>(-1, "114", "手机号码不正确");
                // 验证用户密码是否合法，是否过短过长
            }
            storeUserManagerInfo.setAccountPwd(null);
            return userManagerService.updateByPrimaryKeySelective(storeUserManagerInfo, req);
        } catch (Exception e) {
            logger.error("更新用户失败");
            return new ResultInfo<Object>(-1, "103", "更新用户失败");
        }
    }


    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/deletebyid")
    public ResultInfo<Object> deleteById(HttpServletRequest req, StoreUserManagerInfo storeUserManagerInfo) {
        ResultInfo<Object> result;
        try {
            result = userManagerService.deleteById(storeUserManagerInfo, getCurrent(req));
        } catch (Exception e) {
            logger.error("删除用户失败");
            return new ResultInfo<Object>(-1, "SE001", "删除用户失败");
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/initPwd")
    public ResultInfo<Object> initPwd(HttpServletRequest req, String userName) {
        try {
            return userManagerService.initPwd(userName, req);
        } catch (IOException e) {
            logger.error("解析用户信息失败", e);
            return new ResultInfo<Object>(-1, "103", "读取json参数失败");
        } catch (Exception e) {
            logger.error("初始化密码失败", e);
            return new ResultInfo<Object>(-1, "SE001", "初始化密码失败");
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getTimerInfo")
    public ResultInfo<Object> usersTotal(HttpServletRequest req, String userName) {
        Integer usersTotal = userManagerService.getUsersTotal();
        Integer usersTodayTotal = userManagerService.getTodayUsersTotal();
        Integer todaySMS = userManagerService.getTodaySMSTotal();
        Integer todayNewPhoneTotal = userManagerService.getTodayNewPhoneTotal();
        Map<String, Integer> ma = new HashMap<String, Integer>();
        ma.put("userTotal", usersTotal);
        ma.put("usersTodayTotal", usersTodayTotal);
        ma.put("todaySMS", todaySMS);
        ma.put("todayNewPhoneTotal", todayNewPhoneTotal);
        return new ResultInfo<Object>(0, "", "", ma);
    }


    @ResponseBody
    @RequestMapping(value = "/getSMSTotalByDateTime")
    public ResultInfo<Object> getSMSTotalByDateTime(HttpServletRequest req, String dateBegin, String dateEnd) {
        Integer smsTotal = userManagerService.getSMSTotalByDateTime(dateBegin, dateEnd);
        return new ResultInfo<Object>(0, "", "", smsTotal.toString());

    }

    @ResponseBody
    @RequestMapping(value = "/getOderStatiscs")
    public ResultInfo<Object> getOderStatiscs(HttpServletRequest req) {

        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar currentTime = Calendar.getInstance();
        String dateEnd ;
        String dateBegin;
        List<OrderStatiscs> list=new ArrayList<OrderStatiscs>();
        for (int i=0;i<6;i++){
            dateEnd = timeformat.format(currentTime.getTime()); //将日期时间格式化
            currentTime.add(Calendar.HOUR,-1);
            dateBegin = timeformat.format(currentTime.getTime());
            OrderStatiscs oderStatiscs = userManagerService.getOderStatiscs(dateBegin, dateEnd);
            oderStatiscs.setDateTimeString(dateEnd);
            list.add(oderStatiscs);
        }
        return new ResultInfo<Object>(0, "", "",list);
    }


	/*管理注册用户结束*/


    @ResponseBody
    @RequestMapping(value = "/getauditusers")
    public void getAuditUsers(HttpServletRequest req, HttpServletResponse response, UserCheckInfo userInfo) {
        if (StringUtils.isBlank(userInfo.getSort())) {
            userInfo.setSort("signupTime");//默认以注册时间排序
            userInfo.setOrder("desc");//默认倒序
        }
        DataGrid<UserCheckInfo> grid = new DataGrid<UserCheckInfo>();
        try {
            List<UserCheckInfo> menuList = userService.getAuditUsers(userInfo);
            grid.setRows(menuList);
            grid.setTotal(userService.getAuditUsersCount(userInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    @RequestMapping(value = "auditUserList", method = RequestMethod.GET)
    public ModelAndView auditUserList(UserInfo userInfo) {
        ModelAndView mView = new ModelAndView("auditUser/allUser");
        return mView;
    }

    @RequestMapping(value = "tenDayUsers", method = RequestMethod.GET)
    public void getTenDayUsers(HttpServletResponse response, UserInfo userInfo) {
        List<UserNumber> users = userManagerService.getTenUsers();
        writeJson(users, response);
    }

    /**
     * 查询收件人信息
     * @param request
     * @param response
     * @param info
     */
    @ResponseBody
    @RequestMapping(value = "/getaddresseeinfo")
    public void getAddresseeinfo(HttpServletRequest request, HttpServletResponse response, AddresseeInfo info) {
        if (StringUtils.isBlank(info.getSort())) {
            info.setSort("signupTime");//默认以注册时间排序
            info.setOrder("desc");//默认倒序
        }

        DataGrid<AddresseeInfo> grid = new DataGrid<>();
        try {
            List<AddresseeInfo> addresseeList =userService.getAddresseeInfo(info);
            grid.setRows(addresseeList);
            grid.setTotal(userService.selectAddresseeCount(info));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }
    @RequestMapping(value = "/gotoaddressee")
    public ModelAndView gotoAddressee(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("/addressee/addresseeManager");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }

    @RequestMapping(value = "/activation", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo activation(HttpServletRequest request){
        try {
            String userId=request.getParameter("userId");
            String beEnabled=request.getParameter("beEnabled");
            HashMap<String,String> map=new HashMap<>();
            map.put("userId",userId);
            map.put("beEnabled",beEnabled);
            return userService.updateCuserStatus(map);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }
}