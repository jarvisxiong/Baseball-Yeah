/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.manager;

import com.zhiduan.axp.common.AES;
import com.zhiduan.axp.common.JsonUtils;
import com.zhiduan.axp.common.WebUtils;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.UserManagerLogModel;
import com.zhiduan.axp.controller.model.manager.UserManagerInfo;
import com.zhiduan.axp.dao.manager.bean.SearchUserManagerBean;
import com.zhiduan.axp.dao.manager.bean.UserManagerBean;
import com.zhiduan.axp.dao.manager.bean.UserManagerLogBean;
import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;
import com.zhiduan.axp.service.manager.UserManagerLog;
import com.zhiduan.axp.service.user.UserManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 
 * @ClassName: UserManagerController
 * @Description: 用户管理 Controller。
 * @author Kevin Zou
 * @date 2016年4月20日 上午9:58:34
 */
@Controller
@RequestMapping("/user/managers")
public class UserManagerController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserManagerController.class);
	@Autowired
	@Qualifier("userManagerService")
	private UserManagerService userManagerService;
	
	 @Resource(name="webUtils")
	 private WebUtils webUtils;

	@Autowired
	@Qualifier("userManagerLog")
	UserManagerLog userManagerLog;
	/**
	 * 
	 * @Description: 更新用户信息
	 * @param data
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo update(String data,HttpServletRequest request) {
		UserManagerInfo userManager = null;
		try {
			userManager = JsonUtils.readValue(data, UserManagerInfo.class);
			userManagerService.update(userManager, request);
		} catch (Exception e) {
			return processException(e, logger);
		}
		return new ResultInfo(0, "", "更新成功");
	}

	@ResponseBody
	@RequestMapping(value = "/getLogs")
	public void getAuditUsers(HttpServletRequest request, HttpServletResponse response, UserManagerLogModel logModel) {
		if (org.apache.commons.lang3.StringUtils.isBlank(logModel.getSort())) {
			logModel.setSort("operationTime");//默认以注册时间排序
			logModel.setOrder("desc");//默认倒序
		}
		DataGrid<UserManagerLogBean> grid = new DataGrid<UserManagerLogBean>();
		try {
			List<UserManagerLogBean> menuList = userManagerLog.getLogs(logModel);
			grid.setRows(menuList);
			grid.setTotal(userManagerLog.getLogsCount(logModel));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		writeJson(grid, response);
	}

	/**
	 * 
	 * @Description: 更新用户信息
	 * @param data
	 * @return ResultInfo 操作结果bean
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateManager", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo<UserManagerBean> updateManagerUser(UserManagerInfo userManagerInfo,HttpServletRequest request) {
		try {
			return userManagerService.updateManagerUser(userManagerInfo, request);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResultInfo<UserManagerBean>(-1,"1035","更新用户出错");
		}
	}
	
	/**
	 * 
	 * @Description: 修改密码
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo<UserManagerBean> addManagerUser(HttpServletRequest request, UserManagerInfo userManagerInfo) {
		try {
			return userManagerService.addManagerUser(userManagerInfo, request);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResultInfo<UserManagerBean>(-1, "1023", "增加管理用户异常");
		}
	}

	@RequestMapping(value = "cancelManagerUser", method = RequestMethod.POST)
	public void cancelManagerUser(HttpServletRequest request,
			HttpServletResponse response, UserManagerInfo userManagerInfo) {
		int i = userManagerService.cancelManagerUser(userManagerInfo.getUserManagerId(), request);
		ResultInfo<Object> result = new ResultInfo<Object>();
		if (i > 0) {
			result.setSuccess(0);
		} else {
			result.setSuccess(-1);
		}
		writeJson(result, response);
	}

	/**
	 * 
	 * @Description: 根据id查询用户信息
	 * @param id
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/findbyid", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo<UserManagerInfo> findById(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtils.hasLength(id) || !id.matches("^\\d+$")) {
			return new ResultInfo<UserManagerInfo>(-1, "110", getMessage("110"));
		}
		UserManagerInfo info = null;
		try {
			info = userManagerService.findById(Long.valueOf(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResultInfo<UserManagerInfo>(-1,"","");
		}
		return new ResultInfo<UserManagerInfo>(0, "", "查询成功", info);
	}

	/**
	 * 
	 * @Description: 查询激活的用户
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/activeusers", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo<List<UserManagerInfo>> findByActiveUsers() {
		List<UserManagerInfo> users = null;
		try {
			users = userManagerService.findByActiveUsers();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResultInfo<List<UserManagerInfo>>(-1,"","");
		}
		return new ResultInfo<List<UserManagerInfo>>(0, "", "查询成功", users);
	}

	/**
	 * 
	 * @Description: 查询全部员工
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public void searchAll(HttpServletResponse response, UserManagerInfo userInfo) {
		List<SearchUserManagerBean> list = null;
		DataGrid<SearchUserManagerBean> grid = new DataGrid<SearchUserManagerBean>();
		if (StringUtils.isEmpty(userInfo.getSort())) {
			userInfo.setSort("a.user_manager_id");
		}else if (userInfo.getSort().equals("userManagerId")) {
			userInfo.setSort("a.user_manager_id");
		}else if (userInfo.getSort().equals("loginName")) {
			userInfo.setSort("a.login_name");
		}else if (userInfo.getSort().equals("contactTel")) {
			userInfo.setSort("a.contact_tel");
		}else if (userInfo.getSort().equals("userName")) {
			userInfo.setSort("a.user_name");
		}else if (userInfo.getSort().equals("userCode")) {
			userInfo.setSort("a.user_code");
		}else if (userInfo.getSort().equals("deptName")) {
			userInfo.setSort("b.dept_name");
		}else if (userInfo.getSort().equals("dutyName")) {
			userInfo.setSort("c.duty_name");
		}else if (userInfo.getSort().equals("deptId")) {
			userInfo.setSort("a.dept_id");
		}else if (userInfo.getSort().equals("dutyId")) {
			userInfo.setSort("a.duty_id");
		}else if (userInfo.getSort().equals("beEnabled")) {
			userInfo.setSort("a.be_enabled");
		}else if (userInfo.getSort().equals("roleId")) {
			userInfo.setSort("e.role_id");
		}else if (userInfo.getSort().equals("roleIdStr")) {
			userInfo.setSort("f.role_name");
		}else if (userInfo.getSort().equals("beDeleted")) {
			userInfo.setSort("a.be_deleted");
		}
		try {
			list = userManagerService.getAll(userInfo);
			grid.setRows(list);
			grid.setTotal(userManagerService.getManagerTotal(userInfo));
		} catch (Exception e) {
			logger.error(e.getMessage(), logger);
		}
		writeJson(grid, response);
	}

	/**
	 * 
	 * @Description: 按ID删除
	 * @param data
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo<String> deleteById(String data,HttpServletRequest request) {
		try {
			String id = JsonUtils.readValueByName(data, "id");
			if (!StringUtils.hasLength(id) || !id.matches("^\\d+$")) {
				return new ResultInfo<String>(-1, "110", getMessage("110"));
			}
			userManagerService.deleteById(Long.valueOf(id), request);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResultInfo<String>(-1,"","");
		}
		return new ResultInfo<String>(0, "", "删除成功", "");
	}

	/**
	 * 
	 * @Description: 初始化用户密码
	 * @param data
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/initPwd", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo<String> initPassword(String data,HttpServletRequest request) {
		try {
			String id = JsonUtils.readValueByName(data, "id");
			if (!StringUtils.hasLength(id) || !id.matches("^\\d+$")) {
				return new ResultInfo<String>(-1, "110", getMessage("110"));
			}
			userManagerService.initPassword(Long.valueOf(id), request);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResultInfo<String>(-1,"","");
		}
		return new ResultInfo<String>(0, "", "初始化密码成功", "");
	}

	/**
	 * 
	 * @Description: 初始化用户密码
	 * @param
	 */
	@RequestMapping(value = "/initManagerPwd", method = RequestMethod.POST)
	@ResponseBody
	public void initManagerPassword(UserManagerInfo userManagerInfo,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int i = userManagerService.initManagerPassword(userManagerInfo.getUserManagerId(), request);
			if (i > 0) {
				writeJson(new ResultInfo<UserManagerBean>(0, "", ""), response);
			} else {
				writeJson(new ResultInfo<UserManagerBean>(-1, "1028", "重置密码失败"),response);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Description: 修改密码
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo<String> changePassword(HttpServletRequest request) {
		UserManagerInfo userInfo = null;
		String timestamp = request.getParameter("timestamp");
		if (!StringUtils.hasLength(timestamp)) {
			return new ResultInfo<String>(-1, "111", getMessage("111"));
		}
		try {
			String data = request.getParameter("data");
			userInfo = JsonUtils.readValue(data, UserManagerInfo.class);
			String pwd = AES.desEncrypt(userInfo.getPassword(),
					timestamp.trim()).trim();
			userInfo.setPassword(pwd);
			userManagerService.changePassword(userInfo.getUserManagerId(),
					userInfo.getPassword(), request);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResultInfo<String>(-1,"","");
		}
		return new ResultInfo<String>(0, "", "修改密码成功", "");
	}
	
	/**
	 * @Description: 更改系统用户密码
	 * @param request
	 * @return 
	 */
	    
	@RequestMapping(value = "/changeManagerPwd", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo<Object> changeManagerPassword(HttpServletRequest request) {
		UserManagerInfo userInfo = null;
		String oldPwd = request.getParameter("oldPwd");
		String newPwd=request.getParameter("newPwd");
		if (StringUtils.isEmpty(oldPwd)) {
			return new ResultInfo<Object>(-1,"","旧密码不能为空");
		}else if (StringUtils.isEmpty(newPwd)) {
			return new ResultInfo<Object>(-1,"","新密码不能为空");
		}else if (!newPwd.matches("^[0-9a-zA-Z]{6,12}$")) {
			return new ResultInfo<Object>(-1,"","新密码6-12位数字字母组合");
		}
		try {
			return userManagerService.changeManagerPassword(((UserManagerLoginBean)request.getSession().getAttribute("user")).getUserManagerId(),newPwd, request,oldPwd);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResultInfo<Object>(-1,"","");
		}
	}

}
