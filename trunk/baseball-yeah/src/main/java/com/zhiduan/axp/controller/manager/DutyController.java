/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.manager;

import java.util.ArrayList;
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
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.controller.model.manager.DutyInfo;
import com.zhiduan.axp.service.manager.DutyService;

/**
 * @ClassName: duty
 * @Description: 职务管理控制器
 * @author 高振
 * @date 2016年3月27日 下午4:41:27
 *
 */
@Controller
@RequestMapping(value = "/manage/duty")
public class DutyController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("dutyService")
	private DutyService dutyService;

	/**
	 * 
	 * @Description: 增加职务
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addDuty(HttpServletRequest request, HttpServletResponse response, DutyInfo dupt) {
		logger.info("开始添加职务信息[data:" + dupt + "]");
		try {
			dutyService.insert(dupt, request);
			return new ResultInfo(0, "", "添加OK");

		} catch (Exception e) {
			return processException(e, logger);
		}

	}

	/**
	 * 
	 * @Description: 删除职务
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo deleteDuty(HttpServletRequest request, HttpServletResponse response) {
		logger.info("开始删除职务信息");
		String dutyId = request.getParameter("dutyId");
		if (dutyId == null || !dutyId.matches("^\\d+$")) {
			logger.error("传入参数错误");
			return new ResultInfo(-1, "111", getMessage("111"));
		}
		try {
			dutyService.deleteByPrimaryKey(Long.valueOf(dutyId), request);
		} catch (Exception e) {
			return processException(e, logger);
		}
		logger.info("删除职务信息成功");
		return new ResultInfo(0, "", "删除OK");
	}

	/**
	 * 
	 * @Description: 更新职务
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateDuty(HttpServletRequest request, HttpServletResponse response, DutyInfo dupt) {
		logger.info("开始更新职务信息[data:" + dupt + "]");
		try {
			dutyService.updateByPrimaryKey(dupt, request);
			return new ResultInfo(0, "", "修改操作成功");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 查询单个职务信息
	 * @param request
	 * @param respons
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo selectDuty(HttpServletRequest request, HttpServletResponse respons) {
		String data = request.getParameter("data");
		String dutyId;
		try {
			dutyId = JsonUtils.readValueByName(data, "dutyId");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		if (dutyId == null || !dutyId.matches("^\\d+$")) {
			logger.error("传入参数错误");
			return new ResultInfo(-1, "111", getMessage("111"));
		}
		DutyInfo dutyInfo = null;
		try {
			dutyInfo = dutyService.selectByPrimaryKey(Long.valueOf(dutyId));
		} catch (Exception e) {
			return processException(e, logger);
		}
		logger.info("查询职务信息成功");
		return new ResultInfo(0, "", "查询OK", dutyInfo);
	}

	/**
	 * @Description: 查询全部
	 * @param request
	 * @param respons
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public void selectAllDuty(HttpServletRequest request, HttpServletResponse response) {
		List<DutyInfo> list = null;
		// DataGrid<DutyInfo> grid = new DataGrid<DutyInfo>();
		try {
			Integer limit = Integer.valueOf(request.getParameter("limit"));
			Integer offset = Integer.valueOf(request.getParameter("offset"));
			list = dutyService.selectAllDuty(limit, offset);
			// grid.setRows(list);
			// grid.setTotal(dutyService.selectTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage(), logger);
		}
		logger.info("查询职务成功");
		writeJson(list, response);
	}

	@RequestMapping(value = "/selectallduty", method = RequestMethod.POST)
	@ResponseBody
	public void getAllDuty(HttpServletRequest request, HttpServletResponse response) {
		List<DutyInfo> list = null;
		try {
			list = dutyService.getAllDuty();
			List<SelectModel> sellist = new ArrayList<SelectModel>();
			SelectModel sel = new SelectModel();
			sel.setId(" ");
			sel.setText("请选择");
			sellist.add(sel);
			for (DutyInfo deptInfo : list) {
				SelectModel selectModel = new SelectModel();
				selectModel.setId(deptInfo.getDutyId().toString());
				selectModel.setText(deptInfo.getDutyName());
				sellist.add(selectModel);
			}

			writeJson(sellist, response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}