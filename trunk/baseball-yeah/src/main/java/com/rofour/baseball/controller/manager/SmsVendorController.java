/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.controller.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.SmsVendorInfo;
import com.rofour.baseball.service.manager.SmsVendorService;

/**
 * @ClassName: SmsVendorController
 * @Description: 管理中心--短信供应商操作对外控制器
 * @author xl
 * @date 2016年3月28日 上午10:24:59
 *
 */
@Controller
@RequestMapping(value = "/manage/smsvendor")
public class SmsVendorController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("smsVendorService")
	private SmsVendorService smsVendorService;
	
	/**
	 * 
	 * @Description: 页面跳转
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "goto", method = RequestMethod.GET)
	public String page(HttpServletRequest req) {
		return "manager/smsVendorManager/smsVendorManager";
	}
	
	/**
	 * 
	 * @Description: 查询短信供应商列表
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/getlist", method = RequestMethod.POST)
	public void getSmsVendorInfoList(HttpServletRequest request,HttpServletResponse response,@RequestBody SmsVendorInfo info) {
		if (StringUtils.isBlank(info.getSort())){
			info.setSort("smsVendorId");//默认以id排序
		}
		DataGrid<SmsVendorInfo> grid = new DataGrid<SmsVendorInfo>();
		try {
			List<SmsVendorInfo> menuList = smsVendorService.selectAll(info);
			grid.setRows(menuList);
			grid.setTotal(smsVendorService.getTotal(info));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		writeJson(grid, response);
	}

	/**
	 * 
	 * @Description: 增加短信供应商接口
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultInfo<Object> addSmsVendor(HttpServletRequest request,SmsVendorInfo info) {
		try {
			return smsVendorService.insert(info,request);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 删除供应商接口
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResultInfo<Object>deleteSmsVendor(HttpServletRequest request) {
		String smsVendorId = request.getParameter("smsVendorId");
		try{
			smsVendorService.deleteByPrimaryKey(smsVendorId,request);
		} catch (Exception e) {
			return new ResultInfo<Object>(-1, "", "删除失败");
		}
		return new ResultInfo<Object>(0, "", "删除成功");
	}

	/**
	 * 
	 * @Description: 更新供应商接口
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultInfo<Object> updateSmsVendor(HttpServletRequest request,SmsVendorInfo info) {
		try{
			ResultInfo<Object> val = smsVendorService.updateByPrimaryKey(info,request);
			return val;
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

}
