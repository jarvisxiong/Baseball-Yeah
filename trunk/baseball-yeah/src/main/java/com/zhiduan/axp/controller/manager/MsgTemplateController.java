/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.controller.manager;

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

import com.zhiduan.axp.common.JsonUtils;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.MsgTemplateInfo;
import com.zhiduan.axp.service.manager.MsgTemplateService;

/**
 * @ClassName: MsgTemplateController
 * @Description: 消息通知类型 对外控制器
 * @author xzy
 * @date 2016年3月31日 下午5:14:58
 *
 */
@Controller
@RequestMapping(value = "/manage/msgtemplate")
public class MsgTemplateController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("msgTemplateService")
	private MsgTemplateService msgTemplateService;

	/**
	 * 
	 * @Description: 增加消息类型
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/addtemplate",method = RequestMethod.POST)
	public ResultInfo addTemplate(HttpServletRequest request,MsgTemplateInfo msgtemplate) {
		try {
			msgTemplateService.addTemplate(msgtemplate,request);
			return new ResultInfo(0, "", "添加成功", "");
		} catch (Exception e) {
			return processException(e,logger);
		}
	}

	/**
	 * 
	 * @Description: 删除消息类型
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/deletetemplate",method = RequestMethod.POST)
	public ResultInfo deleteTemplate(HttpServletRequest request,MsgTemplateInfo msgtemplate) {
		try {
			msgTemplateService.deleteTemplate(msgtemplate,request);
			return new ResultInfo(0, "", "删除成功", "");
		} catch (Exception e) {
			return processException(e,logger);
		}
	}

	/**
	 * 
	 * @Description: 更新消息类型
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/updatetemplate",method = RequestMethod.POST)
	public ResultInfo updateTemplate(HttpServletRequest request,MsgTemplateInfo msgtemplate) {

		try{
			msgTemplateService.updateTemplate(msgtemplate,request);
			return new ResultInfo(0, "", "修改成功", "");
		}catch(Exception e){
			return processException(e,logger);
		}

	}

	/**
	 * 
	 * @Description: 查询单条消息类型
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/selecttemplate",method = RequestMethod.POST)
	public ResultInfo selectTemplate(HttpServletRequest request) {
		String data = request.getParameter("data");
		MsgTemplateInfo msgtemplate = new MsgTemplateInfo();
		try {
			msgtemplate = JsonUtils.readValue(data, MsgTemplateInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JSON_ERROR;
		}
		try {
			MsgTemplateInfo result = msgTemplateService.selectTemplate(msgtemplate);
			return new ResultInfo(0, "", "查找成功", result);
		} catch (Exception e) {
			return processException(e,logger);
		}
	}

	/**
	 * 
	 * @Description: 查找所有消息类型
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/selectall",method = RequestMethod.POST)
	public void selectAllTemplate(HttpServletResponse response,@RequestBody MsgTemplateInfo msgTemplateInfo) {
		if (StringUtils.isBlank(msgTemplateInfo.getSort())){
			msgTemplateInfo.setSort("messageTemplateId");//默认以id排序
		}
		DataGrid<MsgTemplateInfo> grid=new DataGrid<MsgTemplateInfo>();

		try {
			List<MsgTemplateInfo> list = msgTemplateService.selectAll(msgTemplateInfo);
			grid.setRows(list);
			grid.setTotal(msgTemplateService.getMsgTemplateTotal(msgTemplateInfo));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), logger);

		}
		writeJson(grid, response);
	}

}
