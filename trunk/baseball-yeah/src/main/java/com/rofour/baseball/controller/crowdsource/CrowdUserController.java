package com.rofour.baseball.controller.crowdsource;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.dao.crowdsource.bean.CollegePieBean;
import com.rofour.baseball.dao.crowdsource.bean.CrowdsourceBean;
import com.rofour.baseball.dao.order.bean.PacketOrderBean;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.crowdsource.CrowdUserService;
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
 * 众包用户管理
 * Created by Administrator on 2016/10/11.
 */
@Controller
@RequestMapping("/crowdUser")
public class CrowdUserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("crowdUserService")
    private CrowdUserService crowdUserService;

    /**
     * 初始化跳转页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/gotoCrowdUserMain")
    public ModelAndView gotoMonitoringReport(HttpServletRequest request) {
        String packetUserId = request.getParameter("userId");
        String searchType = request.getParameter("searchType");
        String collegeId = request.getParameter("collegeId");
        String collegeName = request.getParameter("collegeName");
        ModelAndView mv;
        if (request.getSession().getAttribute("user") != null) {
            mv = new ModelAndView("crowdsource/crowdUserMain");
            mv.addObject("userId", packetUserId);
            mv.addObject("searchType", searchType);
            mv.addObject("collegeId", collegeId);
            mv.addObject("collegeName", collegeName);
            return mv;
        } else {
            return new ModelAndView("/");
        }
    }


    /**
     * @param request
     * @return DataGrid
     * @throws
     * @Description: 查询小派列表
     */
    @ResponseBody
    @RequestMapping(value = "/queryCrowdUserList", method = RequestMethod.GET)
    public DataGrid<CrowdsourceBean> selectCrowdUserList(HttpServletRequest request, HttpServletResponse response, CrowdsourceBean crowdsourceBean) {
        try {
            return crowdUserService.getCrowdsourceList(crowdsourceBean);//查询数据列表
            /*Integer total = crowdUserService.getCrowdsourceListCount(crowdsourceBean);//查询记录总数

            DataGrid<CrowdsourceBean> dataList = new DataGrid<CrowdsourceBean>();
            dataList.setRows(list);
            dataList.setTotal(total);
            return dataList;*/
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * @param request
     * @return ResultInfo
     * @throws
     * @Description: 根据UserId查询小派详情
     */
    @ResponseBody
    @RequestMapping(value = "/queryCrowdUserDetail", method = RequestMethod.POST)
    public CrowdsourceBean selectCrowdUserDetail(HttpServletRequest request, HttpServletResponse response, CrowdsourceBean bean) {
/*        if(bean==null){
            logger.error("传入参数为空");
            return null;
        }*/
        CrowdsourceBean crowdBean = null;
        try {
            crowdBean = crowdUserService.getCrowdUserDetail(bean);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return crowdBean;
    }

    /**
     * 根据校园ID查询校园详情
     *
     * @param request
     * @param response
     * @param collegePieBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCollegeDetail", method = RequestMethod.POST)
    public CollegePieBean selectCollegeDetail(HttpServletRequest request, HttpServletResponse response, CollegePieBean collegePieBean) {
        CollegePieBean collegePie = null;
        try {
            collegePie = crowdUserService.getCollegeDetail(collegePieBean);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return collegePie;
    }


    /**
     * 修改账户状态（支持批量）
     *
     * @param request
     * @param response
     * @param bean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchUpdateState", method = RequestMethod.POST)
    public ResultInfo batchUpdateState(HttpServletRequest request, HttpServletResponse response, CrowdsourceBean bean) {
        ResultInfo result = new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        if (bean == null) {
            result.setSuccess(-1);
            result.setMessage("操作失败，参数smsModelId不能为空!");
            return result;
        }
        try {
            UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
            bean.setUserId(sessionUser.getUserManagerId());
            int i = crowdUserService.updateState(bean, request);
        } catch (Exception e) {
            result.setSuccess(-1);
            result.setMessage("操作失败，审核失败!");
            logger.error(e.getMessage(), logger);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public ResultInfo UpdateUser(HttpServletRequest request, HttpServletResponse response, CrowdsourceBean bean) {
        ResultInfo result = new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        try {
            crowdUserService.UpdateUser(bean);
        } catch (Exception e) {
            result.setSuccess(-1);
            result.setMessage("操作失败，审核失败!");
            logger.error(e.getMessage(), logger);
        }
        return result;
    }

    /**
     * 查询小派订单详情
     *
     * @param request
     * @param response
     * @param bean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryOrderDetail", method = RequestMethod.POST)
    public PacketOrderBean selectOrderDetail(HttpServletRequest request, HttpServletResponse response, PacketOrderBean bean) {
        PacketOrderBean packetOrder = null;
        try {
            packetOrder = crowdUserService.getOrderDetail(bean);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return packetOrder;
    }
}
