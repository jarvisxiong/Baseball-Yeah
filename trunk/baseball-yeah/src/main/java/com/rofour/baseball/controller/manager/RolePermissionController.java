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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.RolePermissionInfo;
import com.rofour.baseball.dao.manager.bean.RolePermissionBean;
import com.rofour.baseball.service.manager.RolePermissionService;

/**
 * @ClassName: RolePermissionController
 * @Description: 管理中心--角色权限维护
 * @author ww
 * @date 2016年3月26日 下午10:30:16
 *
 */
@Controller
@RequestMapping(value = "/manage/role")

public class RolePermissionController extends BaseController{
	/* 日志 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("rolePermissionService")
	private RolePermissionService rolePermissionService;

	/**
	 * 
	 * @Description: 根据角色ID查询所有角色权限
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/selectallpermission", method = RequestMethod.GET)
	@ResponseBody
	public void selectAllPermission(HttpServletRequest request, HttpServletResponse response) {
		List<RolePermissionBean> list = null;
		DataGrid<RolePermissionBean> grid=new DataGrid<RolePermissionBean>();
		try {
			list = rolePermissionService.selectAll();
			grid.setRows(list);
			grid.setTotal(rolePermissionService.getTotal());
		} catch (Exception e) {
			logger.error("查询所有角色权限信息失败");
//			return processException(e, logger);
		}
		writeJson(grid, response);
	}

	/**
	 * 
	 * @Description: 添加角色权限接口
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/addrolepermission", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addRolePermission(HttpServletRequest request, HttpServletResponse response) {
		String data = request.getParameter("data");
		logger.info("开始添加角色权限信息[data:" + data + "]");
		RolePermissionInfo rolePermission;
		try {
			rolePermission = JsonUtils.readValue(data, RolePermissionInfo.class);
		} catch (Exception e) {
			logger.error("解析角色权限信息失败");
			return JSON_ERROR;
		}
		try {
			rolePermissionService.addRolePermission(rolePermission);
		} catch (Exception e) {
			logger.error("添加角色权限信息失败");
			return processException(e, logger);
		}
		logger.info("添加角色权限信息成功");
		return new ResultInfo(0, "", "添加角色权限信息成功");

	}

	/**
	 * 
	 * @Description: 删除角色权限接口
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/deleterolepermission", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo deleteRolePermission(HttpServletRequest request, HttpServletResponse response) {
		String data = request.getParameter("data");
		logger.info("开始删除角色权限信息[data:" + data + "]");
		String rolePermissionId;
		try {
			rolePermissionId = JsonUtils.readValueByName(data, "rolePermissionId");
		} catch (Exception e) {
			logger.error("解析角色权限信息失败");
			return JSON_ERROR;
		}
		if (rolePermissionId == null || !rolePermissionId.matches("^\\d+$")) {
			logger.error("传入参数错误");
			return new ResultInfo(-1, "110", getMessage("110"));
		}
		try {
			rolePermissionService.deleteRolePermission(Long.valueOf(rolePermissionId));
		} catch (Exception e) {
			logger.error("删除角色权限信息失败");
			return processException(e, logger);
		}
		logger.info("删除角色权限信息成功");
		return new ResultInfo(0, "", "删除角色权限信息成功");

	}

	/**
	 * 
	 * @Description: 修改角色权限接口
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/updaterolepermission", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateRolePermission(HttpServletRequest request) {
		String data = request.getParameter("data");
		logger.info("开始更新角色权限信息[data:" + data + "]");
		RolePermissionInfo rolePermission;
		try {
			rolePermission = JsonUtils.readValue(data, RolePermissionInfo.class);
		} catch (Exception e) {
			logger.error("解析角色权限信息失败");
			return JSON_ERROR;
		}
		try {
			rolePermissionService.updateRolePermission(rolePermission);
		} catch (Exception e) {
			logger.error("更新角色权限信息失败");
			return processException(e, logger);
		}
		logger.info("更新角色权限信息成功");
		return new ResultInfo(0, "", "更新角色权限信息成功");
	}

	/**
	 * 
	 * @Description: 查看角色权限接口
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/selectrolepermission", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo selectPermission(HttpServletRequest request, HttpServletResponse response) {
		String data = request.getParameter("data");
		String rolePermissionId;
		try {
			rolePermissionId = JsonUtils.readValueByName(data, "rolePermissionId");
		} catch (Exception e) {
			logger.error("解析角色权限信息失败");
			return JSON_ERROR;
		}
		if (rolePermissionId == null || !rolePermissionId.matches("^\\d+$")) {
			logger.error("传入参数错误");
			return new ResultInfo(-1, "110", getMessage("110"));
		}
		RolePermissionInfo rolePermissionInfo = null;
		try {
			rolePermissionInfo = rolePermissionService.selectRolePermission(Long.valueOf(rolePermissionId));
		} catch (Exception e) {
			logger.error("查询此角色权限的信息失败");
			return processException(e, logger);
		}
		logger.info("查询角色权限信息成功");
		return new ResultInfo(0, "", "查询角色权限信息成功", rolePermissionInfo);

	}
	
	/**
	 * 
	 * @Description: 批量添加角色权限信息
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/batchadd", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo batchAdd(HttpServletRequest request, HttpServletResponse response) {
		List<RolePermissionInfo> list=null;
		String data=request.getParameter("data");
		try{
			list=JsonUtils.readValueByType(data, new TypeReference<List<RolePermissionInfo>>() {});
		}catch(Exception e){
			logger.error(e + "解析用户信息失败");
			return JSON_ERROR;
		}
		ResultInfo result=null;
		try{
			result=rolePermissionService.batchAdd(list);
		} catch(Exception e){
			logger.error("批量更新角色权限信息失败");
			return processException(e, logger);
		}
		return result;
		
	}
}
