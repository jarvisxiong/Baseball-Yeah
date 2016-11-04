package com.rofour.baseball.controller.report;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.common.ObjectExcelView;
import com.rofour.baseball.common.PageData;
import com.rofour.baseball.dao.user.bean.UserBean;
import com.rofour.baseball.service.user.UserManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.report.ReportOfflinePartyInfo;
import com.rofour.baseball.controller.model.report.ReportWaybillInfo;
import com.rofour.baseball.service.report.ReportOfflineStoreService;
import org.springframework.web.servlet.ModelAndView;

/**
 * 线下直营门店运营报表
 * 
 * @author 张伟
 * @date 2016年6月7日 下午4:42:51
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/report/offline")
public class OfflineStoreReportController extends BaseController {

	@Autowired
	@Qualifier(value = "reportOfflineStoreService")
	private ReportOfflineStoreService reportOfflineStoreService;

	@RequestMapping(value = "/query", method= RequestMethod.GET)
	public @ResponseBody DataGrid<ReportOfflinePartyInfo> getOfflineReport(
			HttpServletRequest request, ReportOfflinePartyInfo partyInfo) {

		if(null == partyInfo.getOffset()){
			partyInfo.setOffset(0);
		}
		if(null == partyInfo.getLimit()){
			partyInfo.setLimit(10);
		}
		
		if(StringUtils.isBlank(partyInfo.getSort())) {
			partyInfo.setOrder("desc");
			partyInfo.setSort("date");
		}
		
		if(!StringUtils.isBlank(partyInfo.getStoreName())){
			String temp = partyInfo.getStoreName();
			try {
				partyInfo.setStoreName(new String(temp.getBytes("ISO-8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				
			}
		}

		List<ReportOfflinePartyInfo> list = reportOfflineStoreService
				.getOfflineStoreList(partyInfo);
		Integer total = reportOfflineStoreService.getOfflineStoreCount(partyInfo);
		
		DataGrid<ReportOfflinePartyInfo> dataList = new DataGrid<ReportOfflinePartyInfo>();
		dataList.setRows(list);
		dataList.setTotal(total);

		return dataList;
	}

	@Autowired
	@Qualifier("userManagerService")
	private UserManagerService userManagerService;


	/*报表导出demon*/
	@RequestMapping("/exportExcel")
	public ModelAndView exportExcel() {
		ModelAndView mv = new ModelAndView();
		try {

			Map<String, Object> dataMap = new HashMap<String, Object>();
			List<String> titles = new ArrayList<String>();

			titles.add("序号"); // 1
			titles.add("编号"); // 2
			titles.add("姓名"); // 3
			titles.add("年龄"); // 4
			titles.add("手机"); // 5
			titles.add("性别"); // 6
			titles.add("注册IP"); // 7
			dataMap.put("titles", titles);

			List<UserBean> userList = userManagerService.getAllUsers();
			List<PageData> varList = new ArrayList<PageData>();
			for (int i = 0; i < userList.size(); i++) {
				PageData vpd = new PageData();
				vpd.put("var1", i + ""); // 1
				vpd.put("var2", userList.get(i).getUserId() + ""); // 2
				vpd.put("var3", userList.get(i).getUserName() + ""); // 3
				vpd.put("var4", userList.get(i).getAge() + ""); // 4
				vpd.put("var5", userList.get(i).getPhone() + ""); // 5
				vpd.put("var6", userList.get(i).getGender() + ""); // 6
				vpd.put("var7", userList.get(i).getSignupIp() + ""); // 7
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView(); // 执行excel操作
			mv = new ModelAndView(erv, dataMap);
		} catch (Exception e) {
		}
		return mv;
	}
	
	/**
	 * 运单流水明细
	 * @param request
	 * @param waybillInfo
	 * @return
	 *
	 */
	@RequestMapping(value = "/detail", method=RequestMethod.POST)
	public @ResponseBody DataGrid<ReportWaybillInfo> detail(HttpServletRequest request, 
			@RequestBody ReportWaybillInfo waybillInfo) {
		
		if(null == waybillInfo.getOffset()){
			waybillInfo.setOffset(0);
		}
		if(null == waybillInfo.getLimit()){
			waybillInfo.setLimit(10);
		}
		
		if(StringUtils.isBlank(waybillInfo.getSort())) {
			waybillInfo.setOrder("desc");
			waybillInfo.setSort("arriveDate");
		}
		
		List<ReportWaybillInfo> list = reportOfflineStoreService.getDetailList(waybillInfo);
		
		Integer total = reportOfflineStoreService.getDetailCount(waybillInfo);
		
		DataGrid<ReportWaybillInfo> dataList = new DataGrid<ReportWaybillInfo>();
		dataList.setRows(list);
		dataList.setTotal(total);

		return dataList;
	}
}
