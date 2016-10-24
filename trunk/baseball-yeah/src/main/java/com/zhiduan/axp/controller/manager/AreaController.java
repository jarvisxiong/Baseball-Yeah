/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.manager;


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

import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.controller.model.SimpleZtree;
import com.zhiduan.axp.controller.model.manager.AreaInfo;
import com.zhiduan.axp.service.manager.Area;

/**
 * @ClassName: AreaController
 * @Description: 区域控制器
 * @author cy
 * @date 2016-04-18 10:24:31
 *
 */
@Controller
@RequestMapping("/manage/area")
public class AreaController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("area")
	private Area area;

	/**
	 * 
	 * @Description: 新增区域
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultInfo<Object> addArea(HttpServletRequest request,AreaInfo info) {
		try {
			return area.addArea(info);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 删除区域
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public ResultInfo<Object> delArea(HttpServletRequest request,AreaInfo info) {
		try {
			area.deleteAreaCascade(info.getAreaId());
			return new ResultInfo<Object>(0,"","删除成功");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 更新区域
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/upd", method = RequestMethod.POST)
	public ResultInfo updArea(HttpServletRequest request,AreaInfo info) {
		try {
			return area.updArea(info);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 根据主键查询区域
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/getbyid", method = RequestMethod.POST)
	public ResultInfo<Object> getAreaById(HttpServletRequest request,AreaInfo info) {
		try {
			return area.getAreaByPK(info);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 获取所有区域
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/getall")
	public List<SimpleZtree> getAllArea(HttpServletRequest request) {
		List<SimpleZtree> list2 = new ArrayList<>();
		try {
			List<AreaInfo> areas = (List<AreaInfo>) area.getAll().getData();
			for (AreaInfo item : areas) {
				//区域id由a_开头
				SimpleZtree ztree = new SimpleZtree("a_"+item.getAreaId(),item.getAreaName());
				ztree.setIsParent(true);
				list2.add(ztree);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), logger);
		} 
		return list2;
	}
	/**
	 * 
	 * @Description: 用于下拉列表
	 * @return List<SelectModel>
	 */
	@ResponseBody
	@RequestMapping(value = "/getarea",method=RequestMethod.GET)
	public List<SelectModel> getSelectList(){
		List<SelectModel> sellist= new ArrayList<>();
		SelectModel sel=new SelectModel();
		sel.setId(" ");
		sel.setText("请选择");
		sellist.add(sel);
		try{
			List<AreaInfo> areas = (List<AreaInfo>) area.getAll().getData();
			for (AreaInfo info : areas) {
				SelectModel selectModel=new SelectModel();
				selectModel.setId(info.getAreaId().toString());
				selectModel.setText(info.getAreaName()); 
				sellist.add(selectModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return sellist;
	}
}
