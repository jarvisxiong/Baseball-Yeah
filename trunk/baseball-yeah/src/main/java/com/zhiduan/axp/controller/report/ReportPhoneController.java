/**  
* @Title: ReportPhoneController.java
* @Package com.zhiduan.axp.controller.report
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月7日 上午11:18:08 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.controller.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiduan.axp.common.JsonUtils;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.report.ReportCityPhoneInfo;
import com.zhiduan.axp.controller.model.report.SearchPhoneCountInfo;
import com.zhiduan.axp.service.report.ReportWaybillService;

/**
* @ClassName: ReportPhoneController
* @Description: 手机号统计报表控制器
* @author heyi
* @date 2016年6月7日 上午11:18:08 
*
*/
@Controller
@RequestMapping("/report/phonecount")
public class ReportPhoneController extends BaseController  {
	   private Logger logger = LoggerFactory.getLogger(getClass());
	    @Autowired
	    @Qualifier("reportWaybillService")
	    private ReportWaybillService reportWaybill;
	    /**
	     * @Description: 城市手机号统计
	     * @param  request
	     * @return ResultInfo
	     * @throws
	     */
	    @ResponseBody
	    @RequestMapping(value = "/cityPhoneCount", method = RequestMethod.POST)
	    public  List<ReportCityPhoneInfo> SelectPhoneCountInfoFromBill(HttpServletRequest request,HttpServletResponse response) {
	   	 List<ReportCityPhoneInfo> dataList=new ArrayList<ReportCityPhoneInfo>();
	        try {
	            String selectDate = request.getParameter("selectDate"); 
	            String cityId=request.getParameter("cityId");
	            HashMap<String,Object> map=new HashMap<String,Object>();
	            map.put("selectDate", selectDate);
	            map.put("cityId", cityId);
	            dataList = reportWaybill.selectCityPhoneCount(map);
	            return dataList;
	        } catch (Exception e) {
	        	logger.error(e.getMessage(), e);
	        	 return dataList;
	        }
	    }
}
