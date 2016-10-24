/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.manager;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.support.RequestContextUtils;

import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.SelectExpressModel;
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.controller.model.manager.ExpressCompanyInfo;
import com.zhiduan.axp.service.manager.ExpressService;

/**
 * 
 * @ClassName: ExpressController
 * @Description: 快递公司增删改查对外接口
 * @author heyi
 * @date 2016年3月25日 下午4:04:39
 *
 */
@Controller
@RequestMapping("/manage/express")
public class ExpressController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("expressService")
	private ExpressService expressService;

	/**
	 * 
	 * @Description: 查询所有快递公司
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public void getExpressList(HttpServletRequest request, HttpServletResponse response) {
		// DataGrid<ExpressCompanyInfo> grid = new
		// DataGrid<ExpressCompanyInfo>();
		try {
			Integer limit = Integer.valueOf(request.getParameter("limit"));
			Integer offset = Integer.valueOf(request.getParameter("offset"));
			List<ExpressCompanyInfo> expressList = expressService.GetExpressList(limit, offset);
			// grid.setRows(expressList);
			// grid.setTotal(expressService.selectTotalCount());
			writeJson(expressList, response);
		} catch (Exception e) {
			logger.error(e.getMessage(), logger);
		}
		// writeJson(grid, response);
	}

	/**
	 * 
	 * @Description: 获取启用的快递公司列表
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/queryenabled", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getEnabledExpressList(HttpServletRequest request) {
		try {
			List<ExpressCompanyInfo> expressList = expressService.GetExpressList();
			return new ResultInfo(0, "", "查询成功", expressList);
		} catch (Exception e) {
			return processException(e, logger);
		}

	}

	@RequestMapping(value = "/queryenabledforsel", method = RequestMethod.POST)
	@ResponseBody
	public void getEnabledExpressListforsel(HttpServletRequest request, HttpServletResponse response) {
			List<ExpressCompanyInfo> expressList = expressService.GetExpressList();
			List<SelectModel> sellist= new ArrayList<>();
			for (ExpressCompanyInfo expStoreInfo : expressList) {
				SelectModel selectModel=new SelectModel();
				selectModel.setId(expStoreInfo.getExpresscompanyid().toString());
				selectModel.setText(expStoreInfo.getFullname());
				sellist.add(selectModel);
			}
			writeJson(sellist, response);
	}
	/**
	 * 
	 * @Description: 获取启用的快递公司列表
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/getenable", method = RequestMethod.POST)
	@ResponseBody
	public void getEnabledExpressInfos(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<SelectModel> sellist = new ArrayList<>();
			SelectModel sel = new SelectModel();
			sel.setId(" ");
			sel.setText("请选择");
			sellist.add(sel);
			List<ExpressCompanyInfo> expressList = expressService.GetExpressList();
			for (ExpressCompanyInfo item : expressList) {
				SelectModel selectModel = new SelectModel();
				selectModel.setId(item.getExpresscompanyid().toString());
				selectModel.setText(item.getSimplename());
				sellist.add(selectModel);
			}
			writeJson(sellist, response);
		} catch (Exception e) {
			writeJson(processException(e, logger), response);
		}

	}

	/**
	 * 
	 * @Description: 新增快递公司
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo Insert(HttpServletRequest request, ExpressCompanyInfo model) {
		try {
			model.setModifyTime(new Date());
			return expressService.Insert(model, request);

		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 修改快递公司
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo Update(HttpServletRequest request, ExpressCompanyInfo model) {
		try {
			model.setModifyTime(new Date());
			return expressService.updateByPrimaryKey(model, request);

		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 删除快递公司
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo Delete(HttpServletRequest request) {
		try {
			String expressCompanyId = request.getParameter("expressCompanyId");
			if (expressCompanyId == null || !expressCompanyId.matches("^\\d+$")) {
				logger.error("传入参数错误");
				return new ResultInfo(-1, "111", getMessage("111"));
			}
			expressService.deleteByPrimaryKey(Long.valueOf(expressCompanyId), request);
			return new ResultInfo(0, "", "删除成功", "");

		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 获取快递公司名称
	 * @param request
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = "getexpress", method = RequestMethod.POST)
	public List<SelectExpressModel> getExpressCompanyList(HttpServletRequest request) {
		
		List<SelectExpressModel> expressList = (List<SelectExpressModel>) RequestContextUtils.getWebApplicationContext(request).getServletContext().getAttribute("expressList");
		
		if(null == expressList) {
			List<ExpressCompanyInfo> list = expressService.GetExpressList();
			expressList = new ArrayList<SelectExpressModel>();
			for(ExpressCompanyInfo item : list) {
				SelectExpressModel model = new SelectExpressModel();
				model.setId(item.getExpresscompanyid());
				model.setText(item.getFullname());
				expressList.add(model);
			}
			RequestContextUtils.getWebApplicationContext(request).getServletContext().setAttribute("expressList", expressList);
		}
		return expressList;
	}
}
