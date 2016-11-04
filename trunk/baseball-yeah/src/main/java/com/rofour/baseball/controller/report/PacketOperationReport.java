package com.rofour.baseball.controller.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.rofour.baseball.common.ObjectExcelView;
import com.rofour.baseball.common.PageData;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.report.ActivityRateInfo;
import com.rofour.baseball.controller.model.report.CommentRateInfo;
import com.rofour.baseball.controller.model.report.PacketReportNavInfo;
import com.rofour.baseball.controller.model.report.RecieveOrderRateInfo;
import com.rofour.baseball.service.report.PacketOperationService;
@Controller
@RequestMapping("/report/packetoperation")
public class PacketOperationReport extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier(value = "packetOperationService")
    private PacketOperationService packetOperationService;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
    	if (request.getSession().getAttribute("user") != null) {
    		Map<String, Object> packetReportNavMap = new HashMap<>();
        	try {
        		PacketReportNavInfo packetReportNavInfo = packetOperationService.getPacketReportNav();
        		if(packetReportNavInfo != null){
        			packetReportNavMap.put("puserNum", packetReportNavInfo.getPuserNum());//小派数量
        			packetReportNavMap.put("activePuserNum", packetReportNavInfo.getActivePuserNum());//活跃小派
        			packetReportNavMap.put("activeDegree", packetReportNavInfo.getActiveDegree());//活跃度
        			packetReportNavMap.put("puserIncNum", packetReportNavInfo.getPuserIncNum());//新增小派
        			packetReportNavMap.put("puserIncRate", packetReportNavInfo.getPuserIncRate());//小派增长率
        			packetReportNavMap.put("shippingAbilityIncNum", packetReportNavInfo.getShippingAbilityIncNum());//运力新增
        			packetReportNavMap.put("shippingIncRate", packetReportNavInfo.getShippingIncRate());//运力增长率
        			packetReportNavMap.put("recOrderRate", packetReportNavInfo.getRecOrderRate());//接单率
        			packetReportNavMap.put("commentGoodRate", packetReportNavInfo.getCommentGoodRate());//小派好评率
        		}else{
        			packetReportNavMap.put("puserNum", "-");
        			packetReportNavMap.put("activePuserNum", "-");
        			packetReportNavMap.put("activeDegree", "-");
        			packetReportNavMap.put("puserIncNum", "-");
        			packetReportNavMap.put("puserIncRate", "-");
        			packetReportNavMap.put("shippingAbilityIncNum", "-");
        			packetReportNavMap.put("shippingIncRate", "-");
        			packetReportNavMap.put("recOrderRate", "-");
        			packetReportNavMap.put("commentGoodRate", "-");
        		}
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    		}
        	ModelAndView modelAndView =  new ModelAndView("report/packetOperationReport/index");
        	modelAndView.addObject("data",packetReportNavMap);
    		return modelAndView;
    	}else{
    		return new ModelAndView("/");
    	}
    }
    /**
     * 查询活跃度图表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value= "/getPuserTotalNum")
    public void  getPuserTotalNum(HttpServletRequest request,HttpServletResponse response){
    	writeJson(packetOperationService.getPuserTotalNum(1), response);
    }
    
    /**
     * 查询新增小派图表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value= "/getPuserIncrNum")
    public void  getPuserIncrNum(HttpServletRequest request,HttpServletResponse response){
    	writeJson(packetOperationService.getPuserIncrNum(1), response);
    }
    
    /**
     * 查询新增运力图表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value= "/getCapacityIncrNum")
    public void  getCapacityIncrNum(HttpServletRequest request,HttpServletResponse response){
    	writeJson(packetOperationService.getCapacityIncrNum(), response);
    }
    
    /**
     * 查询接单数图表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value= "/getReceiveOrderNum")
    public void  getReceiveOrderNum(HttpServletRequest request,HttpServletResponse response){
    	writeJson(packetOperationService.getReceiveOrder(), response);
    }
    
    /**
     * 
     * @Description: 跳转到接单率页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/gotoReceiveOrderRate")
    public ModelAndView gotoReceiveOrderRate(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("report/packetOperationReport/receiveOrderRate");
        } else {
            return new ModelAndView("/");
        }
    }
    
    /**
     * 
     * @Description: 查询接单率列表
     * @param request
     * @param recieveOrderRate
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "/queryReceiveOrderRateList")
    public DataGrid<RecieveOrderRateInfo> queryReceiveOrderRateList(HttpServletRequest request,RecieveOrderRateInfo recieveOrderRate) {
		try {
			if(StringUtils.isBlank(recieveOrderRate.getSort())){
				recieveOrderRate.setSort("day");
				recieveOrderRate.setOrder("desc");
			}
			List<RecieveOrderRateInfo> list = packetOperationService.getRecOrderRateList(recieveOrderRate);
			Integer total = packetOperationService.getRecOrderRateListCount(recieveOrderRate);
			DataGrid<RecieveOrderRateInfo> dataList = new DataGrid<RecieveOrderRateInfo>();
            dataList.setRows(list);
            dataList.setTotal(total);
            return dataList;
		} catch (Exception e) {
        	logger.error(e.getMessage(), e);
		}
		return null;
	}

    /**
     * 
     * @Description: 导出接单率excel
     * @param request
     * @param response
     * @param recieveOrderRate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exportReceiveOrderRateExcel")
    public ModelAndView exportReceiveOrderRateExcel(HttpServletRequest request, HttpServletResponse response, RecieveOrderRateInfo recieveOrderRate) {
        ModelAndView mv = new ModelAndView();
        try {
        	Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("日期");
            titles.add("校区名称");
            titles.add("有效订单数");
            titles.add("未接单数");
            titles.add("接单率");
            titles.add("环比增长");
            dataMap.put("titles", titles);
            Integer total = packetOperationService.getRecOrderRateListCount(recieveOrderRate);
            recieveOrderRate.setOffset(0);
            recieveOrderRate.setLimit(total);
            if(StringUtils.isBlank(recieveOrderRate.getSort())){
				recieveOrderRate.setSort("day");
				recieveOrderRate.setOrder("desc");
			}
            List<RecieveOrderRateInfo> reportList = packetOperationService.getRecOrderRateList(recieveOrderRate);
            List<PageData> varList = new ArrayList<PageData>();
            for (int i = 0; i < reportList.size(); i++) {
                PageData vpd = new PageData();
                vpd.put("var1", reportList.get(i).getDay() == null ? "-" : reportList.get(i).getDay() + "");
                vpd.put("var2", reportList.get(i).getCollegeName() == null ? "-" : reportList.get(i).getCollegeName() + "");
                vpd.put("var3", reportList.get(i).getTotalOrderNum() == null ? "-" : reportList.get(i).getTotalOrderNum() + "");
                vpd.put("var4", reportList.get(i).getOrderStatusWaittaking() == null ? "-" : reportList.get(i).getOrderStatusWaittaking() + "");
                vpd.put("var5", reportList.get(i).getRecOrderRate() == null ? "-" : reportList.get(i).getRecOrderRate() + "");
                vpd.put("var6", reportList.get(i).getRecOrderQoq() == null ? "-" : reportList.get(i).getRecOrderQoq() + "");
                varList.add(vpd);
            }
            dataMap.put("varList", varList);
            ObjectExcelView erv = new ObjectExcelView();
            mv = new ModelAndView(erv, dataMap);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
        return mv;
    }
    
    /**
     * 
     * @Description: 查询众包统计导航数据
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/queryPacketReportNav")
    public void queryPacketReportNav(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> packetReportNavMap = new HashMap<>();
    	try {
    		PacketReportNavInfo packetReportNavInfo = packetOperationService.getPacketReportNav();
    		if(packetReportNavInfo != null){
    			packetReportNavMap.put("puserNum", packetReportNavInfo.getPuserNum());//小派数量
    			packetReportNavMap.put("activePuserNum", packetReportNavInfo.getActivePuserNum());//活跃小派
    			packetReportNavMap.put("activeDegree", packetReportNavInfo.getActiveDegree());//活跃度
    			packetReportNavMap.put("puserIncNum", packetReportNavInfo.getPuserIncNum());//新增小派
    			packetReportNavMap.put("puserIncRate", packetReportNavInfo.getPuserIncRate());//小派增长率
    			packetReportNavMap.put("shippingAbilityIncNum", packetReportNavInfo.getShippingAbilityIncNum());//运力新增
    			packetReportNavMap.put("shippingIncRate", packetReportNavInfo.getShippingIncRate());//运力增长率
    			packetReportNavMap.put("recOrderRate", packetReportNavInfo.getRecOrderRate());//接单率
    			packetReportNavMap.put("commentGoodRate", packetReportNavInfo.getCommentGoodRate());//小派好评率
    		}else{
    			packetReportNavMap.put("puserNum", "-");
    			packetReportNavMap.put("activePuserNum", "-");
    			packetReportNavMap.put("activeDegree", "-");
    			packetReportNavMap.put("puserIncNum", "-");
    			packetReportNavMap.put("puserIncRate", "-");
    			packetReportNavMap.put("shippingAbilityIncNum", "-");
    			packetReportNavMap.put("shippingIncRate", "-");
    			packetReportNavMap.put("recOrderRate", "-");
    			packetReportNavMap.put("commentGoodRate", "-");
    		}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
    	writeJson(packetReportNavMap, response);
    }

    /**
     * 小派活跃度
     *
     * @return
     */
    @RequestMapping("/activityRateManager")
    public ModelAndView activityRateManager() {
        return new ModelAndView("report/packetOperationReport/activityRateManager");
    }

    /**
     * 查询小派活跃度列表
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryActivityRateList", method = RequestMethod.GET)
    public DataGrid<ActivityRateInfo> queryActivityRateList(HttpServletRequest request, HttpServletResponse response, ActivityRateInfo activityRateInfo)  throws IOException {
        if (StringUtils.isBlank(activityRateInfo.getSort())) {
            activityRateInfo.setSort("day");//默认以注册时间排序
            activityRateInfo.setOrder("desc");//默认倒序
        }
        DataGrid<ActivityRateInfo> grid = new DataGrid<>();
        try {
            List<ActivityRateInfo> list = packetOperationService.getActivityRateList(activityRateInfo);
            grid.setRows(list);
            grid.setTotal(packetOperationService.getActivityRateListCount(activityRateInfo));
            return grid;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 小派好评率
     *
     * @return
     */
    @RequestMapping("/commentRateManager")
    public ModelAndView commentRateManager() {
        return new ModelAndView("report/packetOperationReport/commentRateManager");
    }

    /**
     * 查询小派好评率列表
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCommentRateList", method = RequestMethod.GET)
    public DataGrid<CommentRateInfo> queryCommentRateList(HttpServletRequest request, HttpServletResponse response, CommentRateInfo commentRateInfo) throws IOException {
        if (StringUtils.isBlank(commentRateInfo.getSort())) {
            commentRateInfo.setSort("day");//默认以注册时间排序
            commentRateInfo.setOrder("desc");//默认倒序
        }
        DataGrid<CommentRateInfo> grid = new DataGrid<>();
        try {
            List<CommentRateInfo> list = packetOperationService.getCommentRateList(commentRateInfo);
            grid.setRows(list);
            grid.setTotal(packetOperationService.getCommentRateListCount(commentRateInfo));
            return grid;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
}
