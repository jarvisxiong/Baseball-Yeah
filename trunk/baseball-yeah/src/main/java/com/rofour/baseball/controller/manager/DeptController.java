/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.manager;

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

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.manager.DeptInfo;
import com.rofour.baseball.service.manager.DeptService;

/**
 * @ClassName: DeptController
 * @Description: 部门信息维护控制器
 * @author 高振
 * @date 2016年3月27日 下午4:40:21
 *
 */

@Controller
@RequestMapping(value = "/manage/dept")
public class DeptController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("deptService")
	private DeptService deptService;

	/**
	 * 
	 * @Description: 查询所有部门
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void selectAllDept(HttpServletRequest request, HttpServletResponse response) {
		List<DeptInfo> list = null;
		// DataGrid<DeptInfo> grid = new DataGrid<DeptInfo>();
		try {
			Integer limit = Integer.valueOf(request.getParameter("limit"));
			Integer offset = Integer.valueOf(request.getParameter("offset"));
			list = deptService.selectAllDept(limit, offset);
			// grid.setRows(list);
			// grid.setTotal(deptService.selectTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage(), logger);
		}
		logger.info("查询部门成功");

		writeJson(list, response);
	}

	/**
	 * 
	 * @Description: 增加部门
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addDept(HttpServletRequest request, HttpServletResponse response, DeptInfo dept) {
		logger.info("开始添加部门信息[data:" + dept + "]");
		try {
			deptService.addDept(dept, request);
			return new ResultInfo(0, "", "添加成功");
		} catch (Exception e) {
			return processException(e, logger);
		}

	}

	/**
	 * 
	 * @Description: 删除部门
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo deleteDept(HttpServletRequest request, HttpServletResponse response) {
		logger.info("开始删除部门信息");
		String deptId = request.getParameter("deptId");
		if (deptId == null || !deptId.matches("^\\d+$")) {
			logger.error("传入参数错误");
			return new ResultInfo(-1, "111", getMessage("111"));
		}
		try {
			deptService.deleteByPrimaryKey(Long.valueOf(deptId), request);
			return new ResultInfo(0, "", "删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 更新部门
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateDept(HttpServletRequest request, HttpServletResponse response, DeptInfo dept) {
		logger.info("开始更新部门信息[data:" + dept + "]");
		try {
			deptService.updateByPrimaryKey(dept, request);
			return new ResultInfo(0, "", "修改操作成功");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 查询单个部门
	 * @param request
	 * @param respons
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/selectdept", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo selectDept(HttpServletRequest request, HttpServletResponse respons) {

		String data = request.getParameter("data");
		String deptId;
		try {
			deptId = JsonUtils.readValueByName(data, "deptId");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		if (deptId == null || !deptId.matches("^\\d+$")) {
			logger.error("传入参数错误");
			return new ResultInfo(-1, "111", getMessage("111"));
		}
		DeptInfo deptIdInfo = null;
		try {
			deptIdInfo = deptService.selectByPrimaryKey(Long.valueOf(deptId));
			return new ResultInfo(0, "", "查询成功", deptIdInfo);
		} catch (Exception e) {
			return processException(e, logger);
		}

	}

	@RequestMapping(value = "/selectalldept", method = RequestMethod.POST)
	@ResponseBody
	public void getAllDept(HttpServletRequest request, HttpServletResponse response) {
		List<DeptInfo> list = null;
		try {
			list = deptService.getAllDept();
			List<SelectModel> sellist = new ArrayList<SelectModel>();
			SelectModel sel = new SelectModel();
			sel.setId(" ");
			sel.setText("请选择");
			sellist.add(sel);
			for (DeptInfo deptInfo : list) {
				SelectModel selectModel = new SelectModel();
				selectModel.setId(deptInfo.getDeptId().toString());
				selectModel.setText(deptInfo.getDeptName());
				sellist.add(selectModel);
			}

			writeJson(sellist, response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
