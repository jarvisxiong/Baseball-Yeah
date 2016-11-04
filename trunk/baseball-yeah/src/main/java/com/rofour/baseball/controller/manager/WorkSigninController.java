package com.rofour.baseball.controller.manager;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.RequestWorkSigninInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.order.OrderInfo;
import com.rofour.baseball.controller.model.order.RequestOrderInfo;
import com.rofour.baseball.dao.manager.bean.CityBean;
import com.rofour.baseball.dao.manager.bean.CollegeBean;
import com.rofour.baseball.dao.manager.bean.ProvinceBean;
import com.rofour.baseball.dao.manager.bean.PuserBean;
import com.rofour.baseball.dao.order.bean.OrderDetailBean;
import com.rofour.baseball.dao.order.bean.OrderStateDetailBean;
import com.rofour.baseball.dao.wallet.bean.AcctThdPaymentTypeBean;
import com.rofour.baseball.service.manager.CityService;
import com.rofour.baseball.service.manager.CollegeService;
import com.rofour.baseball.service.manager.ProvinceService;
import com.rofour.baseball.service.order.OrderUserService;
import org.apache.commons.lang3.StringUtils;
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
import java.util.ArrayList;
import java.util.List;


/**
 * @author zhanglei
 * @ClassName: OrderUserController
 * @Description: 众包收件人控制层
 * @date 2016年6月6日 下午4:58:05
 */
@Controller
@RequestMapping(value = "/worksignin/")
public class WorkSigninController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //添加虚拟订单状态 待接单
    private final int STATE_WAIT = 100;

    @Autowired
    @Qualifier("provinceService")
    ProvinceService provinceService;

    @Autowired
    @Qualifier("cityService")
    CityService cityService;

    @Autowired
    @Qualifier("collegeService")
    CollegeService collegeService;

    @RequestMapping(value = "/getworksignin", method = RequestMethod.GET)
    public ModelAndView toOrderUserManager(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("worksignin/workSignin");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getprovinces")
    public DataGrid<ProvinceBean> getProvinces(HttpServletRequest request, HttpServletResponse response, RequestWorkSigninInfo requestWorkSigninInfo) {
        if (StringUtils.isBlank(requestWorkSigninInfo.getSort())) {
            requestWorkSigninInfo.setSort("work_signin");//默认以运力排序
            requestWorkSigninInfo.setOrder("desc");//默认倒序
        }
        List<ProvinceBean> list = null;
        DataGrid<ProvinceBean> grid = new DataGrid<ProvinceBean>();

        try {
            list = provinceService.selectAllProvinceAndWorkSigninPage(requestWorkSigninInfo);
            grid.setRows(list);
            grid.setTotal(provinceService.getProvinceTotal());
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        //writeJson(grid, response);
        return grid;
    }

    @ResponseBody
    @RequestMapping(value = "/getcities")
    public DataGrid<CityBean> getCities(HttpServletRequest request, HttpServletResponse response, RequestWorkSigninInfo requestWorkSigninInfo) {
        if (StringUtils.isBlank(requestWorkSigninInfo.getSort())) {
            requestWorkSigninInfo.setSort("work_signin");//默认以运力排序
            requestWorkSigninInfo.setOrder("desc");//默认倒序
        }
        List<CityBean> list = null;
        DataGrid<CityBean> grid = new DataGrid<CityBean>();

        try {
            list = provinceService.selectCityAndWorkSigninPage(requestWorkSigninInfo);
            grid.setRows(list);
            grid.setTotal(cityService.getCityTotal(requestWorkSigninInfo.getProvinceId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        return grid;
    }

    @ResponseBody
    @RequestMapping(value = "/getcolleges")
    public DataGrid<CollegeBean> getPacketOrder(HttpServletRequest request, HttpServletResponse response, RequestWorkSigninInfo requestWorkSigninInfo) {
        if (StringUtils.isBlank(requestWorkSigninInfo.getSort())) {
            requestWorkSigninInfo.setSort("work_signin");//默认以运力排序
            requestWorkSigninInfo.setOrder("desc");//默认倒序
        }
        List<CollegeBean> list = null;
        DataGrid<CollegeBean> grid = new DataGrid<CollegeBean>();

        try {
            list = collegeService.selectCollegeAndWorkSigninPage(requestWorkSigninInfo);
            grid.setRows(list);
            grid.setTotal(collegeService.getCollegeTotal(requestWorkSigninInfo.getCityId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        return grid;
    }


    @ResponseBody
    @RequestMapping(value = "/getpusers")
    public DataGrid<PuserBean> getPusers(HttpServletRequest request, HttpServletResponse response, RequestWorkSigninInfo requestWorkSigninInfo) {
        if (StringUtils.isBlank(requestWorkSigninInfo.getSort())) {
            requestWorkSigninInfo.setSort("create_date");
            requestWorkSigninInfo.setOrder("desc");//默认倒序
        }
        List<PuserBean> list = null;
        DataGrid<PuserBean> grid = new DataGrid<PuserBean>();

        try {
            list = collegeService.selectPuserAndWorkSigninPage(requestWorkSigninInfo);
            grid.setRows(list);
            grid.setTotal(collegeService.getPuserTotal(requestWorkSigninInfo.getCollegeId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        return grid;
    }


}
