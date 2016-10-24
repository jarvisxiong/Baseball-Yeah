/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.manager;

import java.util.ArrayList;
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
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.controller.model.manager.MsgConfigInfo;
import com.zhiduan.axp.controller.model.manager.PropertyDictInfo;
import com.zhiduan.axp.service.manager.MsgConfigService;
import com.zhiduan.axp.service.manager.PropertyDictService;

/**
 * @ClassName: MsgConfigController
 * @Description: 消息配置 对外控制器
 * @author xzy
 * @date 2016年3月28日 上午10:30:22
 *
 */
@Controller
@RequestMapping(value = "/manage/msgconfig")
public class MsgConfigController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("msgConfigService")
	private MsgConfigService msgConfigService;
	@Autowired
	@Qualifier("propertyDictService")
	private PropertyDictService propertyDictService;
	/**
	 * 消息通知类型常量
	 */
	private static String MESSAGE_TYPE_ID = "message_type_id";
	/**
	 * 数据库中消息发送类型常量
	 */
	private static String SEND_TYPE_ID = "send_type_id";
	/**
	 * 
	 * @Description: 页面跳转
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "goto", method = RequestMethod.GET)
	public String page(HttpServletRequest req) {
		return "manager/msgConfigManager/msgConfigManager";
	}
	/**
	 * @Description: 请求通知类型
	 * @param req
	 * @param response
	 */
	@RequestMapping(value = "getMsgTypeId", method = RequestMethod.GET)
	public void getMsgTypeId(HttpServletRequest req,HttpServletResponse response) {
		writeJson(getSelectList(MESSAGE_TYPE_ID), response);
	}
	/**
	 * @Description: 请求息发送类型
	 * @param req
	 * @param response
	 */
	@RequestMapping(value = "getSendTypeId", method = RequestMethod.GET)
	public void getSendTypeId(HttpServletRequest req,HttpServletResponse response) {
		writeJson(getSelectList(SEND_TYPE_ID), response);
	}
	/**
	 * 
	 * @Description: 添加消息配置
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/addconfig", method = RequestMethod.POST)
	public ResultInfo<Object> addConfig(HttpServletRequest request,MsgConfigInfo msgconfig) {
		try {
			msgConfigService.addMsgConfig(msgconfig,request);
			return new ResultInfo<Object>(0, "", "添加成功", "");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 删除消息配置
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteconfig", method = RequestMethod.POST)
	public ResultInfo<Object> deleteConfig(HttpServletRequest request) {
		try {
			String id = request.getParameter("messageConfigId");
			msgConfigService.deleteMsgConfig(Long.valueOf(id),request);
			return new ResultInfo(0, "", "删除成功", "");
		} catch (Exception e) {
			return processException(e, logger);
		}

	}

	/**
	 * 
	 * @Description: 修改消息配置
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/updateconfig", method = RequestMethod.POST)
	public ResultInfo<Object> updateConfig(HttpServletRequest request,MsgConfigInfo msgconfig) {
		try {
			msgConfigService.updateMsgConfig(msgconfig,request);
			return new ResultInfo<Object>(0, "", "修改成功", "");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 查询消息配置
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/selectconfig", method = RequestMethod.POST)
	public ResultInfo selectConfig(HttpServletRequest request) {
		String data = request.getParameter("data");
		MsgConfigInfo result = new MsgConfigInfo();
		MsgConfigInfo msgconfig = new MsgConfigInfo();
		try {
			msgconfig = JsonUtils.readValue(data, MsgConfigInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try {

			result = msgConfigService.selectMsgConfig(msgconfig);
			return new ResultInfo(0, "", "查询成功", result);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 查询所有消息配置
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/selectallconfig", method = RequestMethod.POST)
	public void selectAllConfig(HttpServletRequest request,HttpServletResponse response,@RequestBody MsgConfigInfo info) {
		if (StringUtils.isBlank(info.getSort())){
			info.setSort("messageConfigId");//默认以id排序
		}
		DataGrid<MsgConfigInfo> grid = new DataGrid<MsgConfigInfo>();
		try {
			List<MsgConfigInfo> result = msgConfigService.selectAll(info);
			grid.setRows(result);
			grid.setTotal(msgConfigService.getTotal(info));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		writeJson(grid, response);
	}
	
	private List<SelectModel> getSelectList(String alias){
		List<SelectModel> sellist= new ArrayList<>();
		SelectModel sel=new SelectModel();
		sel.setId(" ");
		sel.setText("请选择");
		sellist.add(sel);
		try{
			List<PropertyDictInfo> infoList = propertyDictService.getPropertyDictList();
			for (PropertyDictInfo info : infoList) {
				if(info.getCallAlias().equals(alias)){
					SelectModel selectModel=new SelectModel();
					selectModel.setId(info.getPropertyId());
					selectModel.setText(info.getPropertyValue()); 
					sellist.add(selectModel);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return sellist;
	}
}
