/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.controller.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang3.StringUtils;
import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.SysNoticeInfo;
import com.rofour.baseball.dao.manager.bean.SearchUserManagerBean;
import com.rofour.baseball.service.manager.SysNoticeService;

/**
 * @ClassName: TbSysNoticeController
 * @Description: 系统通知 对外控制器
 * @author 薛子夜
 * @date 2016年3月26日 下午4:04:56
 *
 */
@Controller
@RequestMapping(value = "/manage/sysnotice")
public class SysNoticeController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("sysNoticeService")
	private SysNoticeService sysNoticeService;

	/**
	 * 
	 * @Description: 增加系统通知
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/addsysnotice", method = RequestMethod.POST)
	public ResultInfo addNotice(HttpServletRequest request,SysNoticeInfo notice) {
		try {
			sysNoticeService.addNotice(notice,request);
		} catch (Exception e) {
			return processException(e, logger);
		}
		return new ResultInfo(0, "", "添加成功", "");

	}

	/**
	 * 
	 * @Description: 系统通知逻辑删除
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/deletesysnotice", method = RequestMethod.POST)
	public ResultInfo<Object> deleteNotice(HttpServletRequest request,SysNoticeInfo notice) {

		try {
			sysNoticeService.deleteNotice(notice,request);
		} catch (Exception e) {
			return processException(e, logger);
		}
		return new ResultInfo(0, "", "删除成功");
	}

	/**
	 * 
	 * @Description: 更新系统通知
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/updatesysnotice", method = RequestMethod.POST)
	public ResultInfo<Object> updateNotice(HttpServletRequest request,SysNoticeInfo notice) {
		try {
			sysNoticeService.updateNotice(notice,request);
		} catch (Exception e) {
			return processException(e, logger);
		}
		return new ResultInfo(0, "", "更新成功");
	}

	/**
	 * 
	 * @Description: 查找单条系统通知
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/selectsysnotice", method = RequestMethod.GET)
	public ResultInfo<Object> selectNotice(HttpServletRequest request) {

		String data = request.getParameter("data");
		SysNoticeInfo notice = null;
		try {
			notice = JsonUtils.readValue(data, SysNoticeInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JSON_ERROR;
		}
		try {
			SysNoticeInfo result = sysNoticeService.selectNotice(notice);
			return new ResultInfo(0, "", "查询成功", result);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 查看所有系统通知
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "selectall", method = RequestMethod.POST)
	public void selectAllNotice(HttpServletResponse response,@RequestBody SysNoticeInfo sysNoticeInfo) {
		if (StringUtils.isBlank(sysNoticeInfo.getSort())){
			sysNoticeInfo.setSort("sysNoticeId");//默认以id排序
		}
		List<SysNoticeInfo> list = null;
		DataGrid<SysNoticeInfo> grid=new DataGrid<SysNoticeInfo>();
		try {
			list = sysNoticeService.selectAll(sysNoticeInfo);
			grid.setRows(list);
			grid.setTotal(sysNoticeService.getSysNoticeTotal(sysNoticeInfo));
		} catch (Exception e) {
			logger.error(e.getMessage(), logger);
		} 
		writeJson(grid, response);
	}

	/**
	 * 
	 * @Description: 系统通知审核
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public ResultInfo auditNotice(HttpServletRequest request,SysNoticeInfo notice) {
		try {
			sysNoticeService.auditNotice(notice,request);
		} catch (Exception e) {
			return processException(e, logger);
		}
		return new ResultInfo(0, "", "审核成功", "");
	}
}
