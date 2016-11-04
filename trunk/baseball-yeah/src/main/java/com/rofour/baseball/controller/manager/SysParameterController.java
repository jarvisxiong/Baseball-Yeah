/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.controller.manager;

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
import com.rofour.baseball.controller.model.manager.SysParameterInfo;
import com.rofour.baseball.service.manager.SysParameterService;

/**
 * @ClassName: SysParameterController
 * @Description: 管理中心--系统参数操作对外控制器
 * @author xl
 * @date 2016年3月26日 下午1:03:16
 *
 */

@Controller
@RequestMapping(value = "/manage/syspara")
public class SysParameterController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("tbSysParameterService")
	private SysParameterService paraService;

	/**
	 * 
	 * @Description: 查询所有系统参数接口
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value="/getlist", method = RequestMethod.POST)
	public ResultInfo getSysParaList(HttpServletRequest request) {
		try {
			List<SysParameterInfo> paraList = paraService.selectAll();
			return new ResultInfo(0, "", "查询成功", paraList);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 按名称查询系统参数接口
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value="/getbyname", method = RequestMethod.POST)
	public ResultInfo getSysParaListByName(HttpServletRequest request) {
		String data = request.getParameter("data");
		SysParameterInfo reqPara = null;
		try {
			reqPara = JsonUtils.readValue(data, SysParameterInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JSON_ERROR;
		}
		try{
			SysParameterInfo paraInfo = paraService.selectByParaName(reqPara.getParameterName());
			return new ResultInfo(0, "", "查询成功", paraInfo);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 按主键ID查询系统参数接口
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value="/getbyid", method = RequestMethod.POST)
	public ResultInfo getSysParaById(HttpServletRequest request) {
		String data = request.getParameter("data");
		SysParameterInfo reqPara = null;
		try {
			reqPara = JsonUtils.readValue(data, SysParameterInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JSON_ERROR;
		}
		try{
			SysParameterInfo rtnParaInfo = paraService.selectByPrimaryKey(reqPara.getSysParameterId());
			return new ResultInfo(0, "", "查询成功", rtnParaInfo);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 新增系统参数接口
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResultInfo addSysPara(HttpServletRequest request) {
		String data = request.getParameter("data");
		SysParameterInfo parameter = null;
		try {
			parameter = JsonUtils.readValue(data, SysParameterInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JSON_ERROR;
		}
		try{
			return paraService.insert(parameter,request);
		}
		catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 按主键ID删除系统参数接口
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public ResultInfo deleteSysPara(HttpServletRequest request) {
		String data = request.getParameter("data");
		SysParameterInfo parameter = null;
		try {
			parameter = JsonUtils.readValue(data, SysParameterInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JSON_ERROR;
		}
		try{
			paraService.deleteByPrimaryKey(parameter.getSysParameterId(),request);
		}
		catch (Exception e) {
			return processException(e, logger);
		}
		return new ResultInfo(0, "", "删除成功");
	}

	/**
	 * 
	 * @Description: 更新系统参数接口
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ResultInfo updateSysPara(HttpServletRequest request) {
		String data = request.getParameter("data");
		SysParameterInfo parameter = null;
		try {
			parameter = JsonUtils.readValue(data, SysParameterInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JSON_ERROR;
		}
		try{
			return paraService.updateByPrimaryKey(parameter,request);
		}  catch (Exception e) {
			return processException(e, logger);
		}
	}
}
