/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.manager;

import java.util.ArrayList;
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

import com.zhiduan.axp.common.JsonUtils;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.controller.model.SimpleZtree;
import com.zhiduan.axp.controller.model.manager.CityInfo;
import com.zhiduan.axp.controller.model.manager.CityListInfo;
import com.zhiduan.axp.service.manager.CityService;

/**
 * @ClassName: CityController
 * @Description: 市的增删改查接口
 * @author liujingbo
 * @date 2016年3月26日 下午8:35:42
 *
 */
@Controller
@RequestMapping(value = "/manage/city")
public class CityController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("cityService")
	private CityService cityService;
	/**
	 * 
	 * @Description: 新增一个市
	 * @param request
	 * @param response
	 * @param cityinfo
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/insertcity", method = RequestMethod.POST)
	public ResultInfo<Object> insertCity(HttpServletRequest request, HttpServletResponse response, CityInfo cityinfo) {
		try { 
			cityService.insert(cityinfo);
		} catch (Exception e) {
			return processException(e, logger);
		}
		return new ResultInfo<Object>(0, "", "添加成功", "");
	}

	/**
	 * 
	 * @Description: 删除市
	 * @param request
	 * @param response
	 * @param cityinfo
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/deletecity", method = RequestMethod.POST)
	public ResultInfo<Object> deleteCity(HttpServletRequest request, HttpServletResponse response, CityInfo cityInfo) {
		try {
			cityService.deleteCityCascade(cityInfo.getCityId());
			return new ResultInfo<Object>(0, "", "删除成功", "");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 根据id选择市
	 * @param request
	 * @param response
	 * @param cityinfo
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/selectcity", method = RequestMethod.POST)
	public ResultInfo<Object> selectCity(HttpServletRequest request, HttpServletResponse response, String cityId) {
		try { 
			if (cityId == null || !cityId.matches("^\\d+$")) {
				return new ResultInfo<Object>(-1, "111", "请输入正确的城市id");
			}
			return cityService.selectByPrimaryKey(Long.valueOf(cityId));
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 根据省份id查询所有此省份的城市
	 * @param request
	 * @param response
	 * @param cityinfo
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/selectcitiesbyprovinceid", method = RequestMethod.POST)
	public List<SimpleZtree> selectCitiesByProvinceId(HttpServletRequest request, HttpServletResponse response,
			CityInfo cityinfo) {
		List<SimpleZtree> result = new ArrayList<>();
		try {
			List<CityInfo> cities = cityService.selectCitiesByProvinceId(cityinfo.getProvinceId());
			for(CityInfo city:cities){
				SimpleZtree ztree=new SimpleZtree("c_"+city.getCityId(),city.getCityName());
				ztree.setpId(request.getParameter("id"));
				ztree.setIsParent(true);
				result.add(ztree);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), logger);
		} 
		return result;
	}
	
	/**
	 * 
	 * @Description: 根据省份id查询所有此省份的城市
	 * @param request
	 * @param response
	 * @param cityinfo
	 */
	@ResponseBody
	@RequestMapping(value = "/selectprovincecities", method = RequestMethod.POST)
	public void selectProvinceCities(HttpServletRequest request, HttpServletResponse response,
			CityInfo cityinfo) {
		List<CityInfo> list = null;
		try {
			list = cityService.selectCitiesByProvinceId(cityinfo.getProvinceId());
		} catch (Exception e) {
			logger.error(e.getMessage(), logger);
		} 
		writeJson(list, response);
	}

	/**
	 * 
	 * @Description: 查询所有城市信息
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/queryall", method = RequestMethod.POST)
	public ResultInfo selectAll(HttpServletRequest request) {
		try {
			List<CityInfo> cityInfos = cityService.selectAll();
			return new ResultInfo(0, "", "", cityInfos);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 更新一个市
	 * @param request
	 * @param response
	 * @param cityinfo
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/updatecity", method = RequestMethod.POST)
	public ResultInfo updateCity(HttpServletRequest request, HttpServletResponse response, CityInfo cityInfo) {
		try {
			cityService.updateByPrimaryKey(cityInfo);
			return new ResultInfo(0, "101001021", "修改成功", "");
		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * @Description: 查询有学校的城市
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CityService#selectAllCities()
	 */
	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public ResultInfo selectAllCity() {
		try {
			List<CityListInfo> cityAll = cityService.selectAllCities();
			return new ResultInfo(0, "0", "查询城市列表成功", cityAll);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}
	/**
	 * 
	 * @Description: 用于下拉列表
	 * @return List<SelectModel>
	 */
	@ResponseBody
	@RequestMapping(value = "/getcity",method=RequestMethod.GET)
	public List<SelectModel> getSelectList(String provinceId){
		List<SelectModel> sellist= new ArrayList<>();
		SelectModel sel=new SelectModel();
		sel.setId(" ");
		sel.setText("请选择");
		sellist.add(sel);
		if(provinceId == null || provinceId.trim().equals("")){
			return sellist;
		}
		try{
			List<CityInfo> infoList = cityService.selectCitiesByProvinceId(Long.valueOf(provinceId));
			for (CityInfo info : infoList) {
				SelectModel selectModel=new SelectModel();
				selectModel.setId(info.getCityId().toString());
				selectModel.setText(info.getCityName()); 
				sellist.add(selectModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return sellist;
	}
}
