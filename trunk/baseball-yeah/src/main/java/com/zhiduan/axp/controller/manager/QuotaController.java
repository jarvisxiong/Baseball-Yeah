/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.manager;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.QuotaInfo;
import com.zhiduan.axp.service.manager.QuotaService;

/**
 * @ClassName: QuotaController
 * @Description: 指标配置控制器
 * @author xl
 * @date 2016年7月18日 下午4:08:21
 */
@Controller
@RequestMapping("/manage/quota")
public class QuotaController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("quotaService")
	private QuotaService service;

	/**
	 * 
	 * @Description: 按主键查询
	 * @param quotaId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectbyid", method = RequestMethod.POST)
	public ResultInfo<Object> selectById(HttpServletRequest request, Integer quotaId) {
		try {
			QuotaInfo quotaInfo = service.selectById(quotaId);
			return new ResultInfo<Object>(0, "", "", quotaInfo);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 查询所有在用指标
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectall", method = RequestMethod.POST)
	public void selectAll(HttpServletRequest request, HttpServletResponse response) {
		DataGrid<QuotaInfo> grid = new DataGrid<QuotaInfo>();
		try {
			List<QuotaInfo> quotaInfos = service.selectAll();
			grid.setRows(quotaInfos);
			grid.setTotal(service.getQuotaCount());
		} catch (Exception e) {
			logger.error(e.getMessage(), logger);
		}
		writeJson(grid, response);
	}

	/**
	 * 
	 * @Description: 按住建删除
	 * @param quotaId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delbyid", method = RequestMethod.POST)
	public ResultInfo<Object> deleteById(HttpServletRequest request, int quotaId) {
		try {
			service.deleteById(quotaId, request);
			return new ResultInfo<Object>(0, "", "删除成功");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 批量删除
	 * @param quotaIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delbyids", method = RequestMethod.POST)
	public ResultInfo<Object> deleteBatchByIds(@RequestParam(value = "quotaIds") List<Integer> quotaIds,
			HttpServletRequest request) {
		try {
			service.deleteBatchByIds(quotaIds, request);
			return new ResultInfo<Object>(0, "", "删除成功");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 新增指标
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultInfo<Object> insert(HttpServletRequest request, QuotaInfo info) {
		try {
			service.insert(info, request);
			return new ResultInfo<Object>(0, "", "新增成功");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 按主键更新
	 * @param info
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultInfo<Object> updateById(HttpServletRequest request, QuotaInfo info) {
		try {
			service.updateById(info, request);
			return new ResultInfo<Object>(0, "", "更新成功");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}
}
