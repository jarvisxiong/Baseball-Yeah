/**
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.controller.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiduan.axp.common.StringUtils;
import com.zhiduan.axp.controller.model.SelectModel;
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
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.PropertyDictInfo;
import com.zhiduan.axp.service.manager.PropertyDictService;

/**
 *
 * @ClassName: PropertyDictController
 * @Description: 属性字典增删改查对外接口
 * @author heyi
 * @date 2016年3月25日 下午4:09:24
 *
 */
@Controller
@RequestMapping("/manage/propertydict")
public class PropertyDictController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("propertyDictService")
	private PropertyDictService propertyDictService;

	/**
	 *
	 * @Description: 获取所有属性字典
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getPropertyList(HttpServletRequest request) {
		try {
			List<PropertyDictInfo> list = propertyDictService.getPropertyDictList();
			if (list != null && !list.isEmpty()) {
				return new ResultInfo(0, "", "查询成功", list);
			}
			return new ResultInfo(-1, "01004", getMessage("01004"), "");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 *
	 * @Description: 新增属性字典
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo insert(HttpServletRequest request) {
		String data = request.getParameter("data");
		PropertyDictInfo model = null;
		try {
			model = JsonUtils.readValue(data, PropertyDictInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try {
			propertyDictService.insert(model,request);
		} catch (Exception e) {
			return processException(e, logger);
		}
		return new ResultInfo(0, "", "添加成功", "");

	}

	/**
	 *
	 * @Description: 修改属性字典
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo update(HttpServletRequest request) {
		String data = request.getParameter("data");
		PropertyDictInfo model = null;
		try {
			model = JsonUtils.readValue(data, PropertyDictInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try{
			propertyDictService.updateByPrimaryKey(model,request);
		} catch (Exception e) {
			return processException(e, logger);
		}
		return new ResultInfo(0, "", "修改成功", "");
	}

	/**
	 *
	 * @Description: 根据主键删除属性字典
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo Delete(HttpServletRequest request) {
		String data = request.getParameter("data");
		String id = null;
		try {
			id = JsonUtils.readValueByName(data, "propertyId");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try {
			propertyDictService.deleteByPrimaryKey(id,request);
		} catch (Exception e) {
			return processException(e, logger);
		}
		return new ResultInfo(0, "", "删除成功", "");
	}

	/**
	 *
	 * @Description: 通过组名获取属性字典列表
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/queryByAlias", method = RequestMethod.POST)
	@ResponseBody
	public  void getListbyCallAlias(HttpServletRequest request, HttpServletResponse response, String alias)
	{
		List<SelectModel> sellist= new ArrayList<>();
		SelectModel sel = new SelectModel();
		sel.setId(" ");
		sel.setText("请选择");
		sellist.add(sel);
		alias=request.getParameter("alias");
		if(alias!=null)
		{
		List<PropertyDictInfo> listProperty=propertyDictService.getDictListbyCallAlias(alias);
			for (PropertyDictInfo item :listProperty
				 ) {

SelectModel temp =new SelectModel();
				temp.setId(item.getPropertyId());
				temp.setText(item.getPropertyValue());
				sellist.add(temp);
			}
		}

		writeJson(sellist, response);
	}
}
