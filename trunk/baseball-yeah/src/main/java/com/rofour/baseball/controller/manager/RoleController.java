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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.manager.CollegeManageInfo;
import com.rofour.baseball.controller.model.manager.RoleInfo;
import com.rofour.baseball.dao.manager.bean.RoleBean;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.RoleService;
/**
 * @ClassName: RoleController
 * @Description: 管理中心--角色管理
 * @author ww
 * @date 2016年3月26日 下午10:35:34
 *
 */

@Controller
@RequestMapping(value="/manage/role")
public class RoleController extends BaseController{
	/* 日志 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;

	/**
	 * 
	 * @Description: 查看所有角色信息
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/selectAllRole",method=RequestMethod.GET)
	@ResponseBody
	public void selectAllRole(HttpServletRequest request, HttpServletResponse response,RoleInfo roleInfo) {
		DataGrid<RoleBean> grid=new DataGrid<RoleBean>();
		try {
			if (StringUtils.isEmpty(roleInfo.getSort())) {
				roleInfo.setSort("roleName");
			}
			logger.error(JsonUtils.translateToJson(roleInfo));
			List<RoleBean> list = roleService.getRoleList(roleInfo);
			grid.setRows(list);
			grid.setTotal(roleService.getTotal(roleInfo));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		writeJson(grid, response);
	}

	/**
	 * 
	 * @Description: 新增角色内容
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/addRole",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo<RoleBean> addRole(RoleInfo roleInfo,HttpServletRequest request) {
		try {
			return roleService.addRole(roleInfo, request);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResultInfo<RoleBean>(-1,"1060","增加角色出错");
		}
	}

	/**
	 * 
	 * @Description: 删除角色接口
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/deleteRole",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo deleteRole(RoleInfo roleInfo,HttpServletRequest request) {
		if (roleInfo.getRoleId() == null || !roleInfo.getRoleId().toString().matches("^\\d+$")) {
			logger.error("传入参数错误");
			return new ResultInfo(-1, "114", getMessage("114"));
		}
		ResultInfo result = null;
		try {
			result = roleService.deleteRole(roleInfo.getRoleId(), request);
		} catch (BusinessException e) {
			return processException(e, logger);
		}
		return result;
		

	}

	/**
	 * 
	 * @Description: 修改角色接口
	 * @param request
	 * @param response
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/updaterole",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateRole(HttpServletRequest request, HttpServletResponse response) {
		String data = request.getParameter("data");
		logger.info("开始更新角色信息[data:" + data + "]");
		RoleInfo role;
		try {
			role = JsonUtils.readValue(data, RoleInfo.class);
		} catch (Exception e) {
			logger.error("解析角色信息失败");
			return JSON_ERROR;
		}
		
		if (role.getRoleId() == null) {
			return new ResultInfo(-1, "111", getMessage("111"));
		}
		try{
			roleService.updateRole(role, request);
		}catch(Exception e){
			return processException(e, logger);
		}

		logger.info("更新角色信息成功");
		return new ResultInfo(0, "", "更新角色信息成功");

	}

	/**
	 * 
	 * @Description: 查看角色接口
	 * @param request
	 * @param respons
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/selectrole",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo selectRole(HttpServletRequest request, HttpServletResponse respons) {
		String data = request.getParameter("data");
		String roleId;
		try {
			roleId = JsonUtils.readValueByName(data, "roleId");
		} catch (Exception e) {
			logger.error("解析角色信息失败");
			return JSON_ERROR;
		}
		if (roleId == null || !roleId.matches("^\\d+$")) {
			logger.error("传入参数错误");
			return new ResultInfo(-1, "111", getMessage("111"));
		}
		RoleInfo roleInfo = null;
		try {
			roleInfo = roleService.selectRole(Long.valueOf(roleId));
		} catch (Exception e) {
			return processException(e, logger);
		}
		logger.info("查询角色信息成功");
		return new ResultInfo(0, "", "查询角色信息成功", roleInfo);
	}
	
	/**
	 * @Method: updateRoleAndPermission
	 * @Description: 更新角色和角色权限
	 * @param @param request
	 * @param @param respons
	 * @param @return    参数
	 * @return ResultInfo    返回类型
	 * @throws
	 * @author ZhangLei
	 * @date 2016年5月12日 上午10:26:18 
	 **/
	    
	@RequestMapping(value = "/updateRoleAndPermission",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo<RoleBean> updateRoleAndPermission(RoleInfo roleInfo,HttpServletRequest request) {
		return roleService.updateRoleAndPermission(roleInfo, request);
	}
	
	@RequestMapping(value="/getRoleSelect",method=RequestMethod.GET)
	public void getRoleSelect(HttpServletResponse response) {
		try {
			List<RoleBean> roleList = roleService.getRoleList();
			List<SelectModel> sellist= new ArrayList<SelectModel>();
			for (RoleBean roleInfo : roleList) {
				SelectModel selectModel=new SelectModel();
				selectModel.setId(roleInfo.getRoleId().toString());
				selectModel.setText(roleInfo.getRoleName());
				sellist.add(selectModel);
			}
			writeJson(sellist, response);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
