package com.rofour.baseball.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.order.OrderAppraiseIn;
import com.rofour.baseball.controller.model.order.OrderStatisticsInfo;
import com.rofour.baseball.controller.model.order.RequestOrderAppraiseInfo;
import com.rofour.baseball.controller.model.order.RequestOrderStatisticsInfo;
import com.rofour.baseball.dao.order.bean.CollegeCEOBean;
import com.rofour.baseball.service.order.OrderStatisticsService;

/**
 * 
 * @ClassName: OrderStatistics
 * @Description: 订单统计
 * @author 徐海波
 * @date 2016年10月11日 上午10:36:04
 */

@Controller
@RequestMapping(value = "/order/statistics")
public class OrderStatisticsController extends BaseController{
	
	private final Logger logger = LoggerFactory.getLogger(OrderStatisticsController.class);
	
	@Autowired
	@Qualifier("orderStatisticsService")
	OrderStatisticsService orderStatisticsService;
	
	/**
	 * 
	 * @Description:获取订单统计显示的数据
	 * @param request
	 * @param response
	 * @param requestOrderStatisticsInfo
	 */
 	@ResponseBody
    @RequestMapping(value = "/getOrderStatistics")
    public void getOrderStatistics(HttpServletRequest request, HttpServletResponse response, RequestOrderStatisticsInfo requestOrderStatisticsInfo) {
        if (StringUtils.isBlank(requestOrderStatisticsInfo.getSort())) {
        	requestOrderStatisticsInfo.setSort("totalOrderNum");//默认以注册时间排序
        	requestOrderStatisticsInfo.setOrder("desc");//默认倒序
        }
        List<OrderStatisticsInfo> list = null;
        DataGrid<OrderStatisticsInfo> grid = new DataGrid<OrderStatisticsInfo>();
        try {
            list = orderStatisticsService.getStatisticsOrder(requestOrderStatisticsInfo);
            grid.setRows(list); 
            grid.setTotal(orderStatisticsService.getStatisticsOrderTotal(requestOrderStatisticsInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        writeJson(grid, response);
    }
 	
 	/**
 	 * 
 	 * @Description: 获取订单评价管理数据
 	 * @param request
 	 * @param response
 	 * @param requestOrderAppraiseInfo
 	 */
 	@ResponseBody
    @RequestMapping(value = "/getOrderAppraise")
    public void getOrderAppraise(HttpServletRequest request, HttpServletResponse response, RequestOrderAppraiseInfo requestOrderAppraiseInfo) {
        if (StringUtils.isBlank(requestOrderAppraiseInfo.getSort())) {
        	requestOrderAppraiseInfo.setSort("createDate");//默认以注册时间排序
        	requestOrderAppraiseInfo.setOrder("desc");//默认倒序
        }
        List<OrderAppraiseIn> list = null;
        DataGrid<OrderAppraiseIn> grid = new DataGrid<OrderAppraiseIn>();
        try {
            list = orderStatisticsService.getOrderAppraise(requestOrderAppraiseInfo);
            grid.setRows(list);
            grid.setTotal(orderStatisticsService.getOrderAppraiseTotal(requestOrderAppraiseInfo));
            logger.info("list"+list.size());
            logger.info("total"+orderStatisticsService.getOrderAppraiseTotal(requestOrderAppraiseInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        writeJson(grid, response);
    }
 	
 	 @RequestMapping(value = "/selectCollegeCEO", method = RequestMethod.POST)
     @ResponseBody
     public void selectCollegeCEO(HttpServletRequest request, HttpServletResponse response) {
         List<CollegeCEOBean> collegeCEOBeans = new ArrayList<CollegeCEOBean>();
         try {
        	 collegeCEOBeans = orderStatisticsService.getCollegeCEO();
             List<SelectModel> sellist = new ArrayList<SelectModel>();
             SelectModel sel = new SelectModel();
             sel.setId(" ");
             sel.setText("请选择");
             sellist.add(sel);
             for (CollegeCEOBean collegeCEOBean : collegeCEOBeans) {
                 SelectModel selectModel = new SelectModel();
                 selectModel.setId(collegeCEOBean.getCollegeId());
                 selectModel.setText(collegeCEOBean.getRealName());
                 sellist.add(selectModel);
             }

             writeJson(sellist, response);
         } catch (Exception e) {
             logger.error(e.getMessage(), e);
         }
     }
 	
 	/**
 	 * 
 	 * @Description: 跳转到订单统计页面
 	 * @param request
 	 * @return
 	 * @throws Exception
 	 */
 	 @RequestMapping(value = "/gotoOrderStatistics", method = RequestMethod.GET)
     public ModelAndView gotoOrderStatistics(HttpServletRequest request) throws Exception {
         if (request.getSession().getAttribute("user") != null) {
             return new ModelAndView("order/orderStatistics/index");
         } else {
             return new ModelAndView("/error/noPermission");
         }
     }
     
 	 /**
 	  * 
 	  * @Description:跳转到订单评价页面
 	  * @param request
 	  * @return
 	  * @throws Exception
 	  */
     @RequestMapping(value = "/gotoOrderAppraise", method = RequestMethod.GET)
     public ModelAndView gotoOrderAppraise(HttpServletRequest request) throws Exception {
         if (request.getSession().getAttribute("user") != null) {
             return new ModelAndView("order/orderStatistics/orderAppraiseManage");
         } else {
             return new ModelAndView("/error/noPermission");
         }
     }
}
