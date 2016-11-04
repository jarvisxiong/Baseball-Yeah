/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.rofour.baseball.controller.model.manager.AtmServerInfo;
import com.rofour.baseball.service.manager.AtmServerService;

/**
 * @ClassName: AtmServerController
 * @Description: 附件服务器对外接口
 * @author xzy
 * @date 2016年4月2日 下午5:47:31
 *
 */
@Controller
@RequestMapping(value = "/manage/atmserver")
public class AtmServerController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("atmServerService")
	private AtmServerService atmServerService;

	/**
	 * 
	 * @Description: 增加附件服务器
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/addatmserver", method = RequestMethod.POST)
	public ResultInfo addAtmServer(HttpServletRequest request) {
		String data = request.getParameter("data");
		AtmServerInfo atmServer = new AtmServerInfo();
		try {
			atmServer = JsonUtils.readValue(data, AtmServerInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try {
			atmServerService.addAtmServer(atmServer);
			return new ResultInfo(0, "", "添加成功", "");

		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 删除附件服务器
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteatmserver", method = RequestMethod.POST)
	public ResultInfo deleteAtmServer(HttpServletRequest request) {
		String data = request.getParameter("data");
		AtmServerInfo atmServer = new AtmServerInfo();
		try {
			atmServer = JsonUtils.readValue(data, AtmServerInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try {

			atmServerService.deleteAtmServer(atmServer);
			return new ResultInfo(0, "", "删除成功", "");

		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 修改附件服务器
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/updateatmserver", method = RequestMethod.POST)
	public ResultInfo updateAtmServer(HttpServletRequest request) {
		String data = request.getParameter("data");
		AtmServerInfo atmServer = new AtmServerInfo();
		try {
			atmServer = JsonUtils.readValue(data, AtmServerInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try {
			atmServerService.updateAtmServer(atmServer);
			return new ResultInfo(0, "", "修改成功", "");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 查找单个附件服务器
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/selectatmserver", method = RequestMethod.POST)
	public ResultInfo selectAtmServer(HttpServletRequest request) {
		String data = request.getParameter("data");
		AtmServerInfo atmServer = new AtmServerInfo();
		try {
			atmServer = JsonUtils.readValue(data, AtmServerInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try {

			AtmServerInfo result = atmServerService.selectAtmServer(atmServer);
			return new ResultInfo(0, "", "查询成功", result);
		} catch (Exception e) {
			return processException(e, logger);
		}

	}

	/**
	 * 
	 * @Description: 查找所有附件服务器
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/selectall", method = RequestMethod.POST)
	public ResultInfo selectAll() {
		try {
			List<AtmServerInfo> result = new ArrayList<AtmServerInfo>();
			result = atmServerService.selectAll();
				return new ResultInfo(0, "", "查询成功", result);
		} catch (Exception e) {
			return processException(e, logger);
		}

	}

}
