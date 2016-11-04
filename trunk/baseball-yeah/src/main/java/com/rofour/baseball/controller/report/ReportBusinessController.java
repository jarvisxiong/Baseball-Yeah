/**  
* @Title: ReportPhoneController.java
* @package com.rofour.baseball.controller.report
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月7日 上午11:18:08 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.controller.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.service.report.ReportBusinessService;

/**
* @ClassName: ReportBusinessController
* @Description: 运营报表控制器
* @author gechao
* @date 2016年11月01日 下午17:18:08 
*
*/
@Controller
@RequestMapping("/report/business")
public class ReportBusinessController extends BaseController  {
	   private Logger logger = LoggerFactory.getLogger(getClass());
	    @Autowired
	    @Qualifier("reportBusinessService")
	    private ReportBusinessService businessService;
	    
//	    /**
//	     * @Description: 城市手机号统计
//	     * @param  request
//	     * @return ResultInfo
//	     * @throws
//	     */
//	    @ResponseBody
//	    @RequestMapping(value = "/cityPhoneCount", method = RequestMethod.POST)
//	    public  List<ReportCityPhoneInfo> SelectPhoneCountInfoFromBill(HttpServletRequest request,HttpServletResponse response) {
//	   	 List<ReportCityPhoneInfo> dataList=new ArrayList<ReportCityPhoneInfo>();
//	        try {
//	            String selectDate = request.getParameter("selectDate"); 
//	            String cityId=request.getParameter("cityId");
//	            HashMap<String,Object> map=new HashMap<String,Object>();
//	            map.put("selectDate", selectDate);
//	            map.put("cityId", cityId);
//	            dataList = reportWaybill.selectCityPhoneCount(map);
//	            return dataList;
//	        } catch (Exception e) {
//	        	logger.error(e.getMessage(), e);
//	        	 return dataList;
//	        }
//	    }
}
