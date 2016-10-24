/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager;

import java.util.List;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.controller.model.manager.CityInfo;
import com.zhiduan.axp.controller.model.manager.CityListInfo;

/**
 * @ClassName: CityService
 * @Description: TODO(市的服务接口)
 * @author liujingbo
 * @date 2016年3月26日 下午9:30:22
 *
 */

public interface CityService {

	    
	/**
	 * @Description: 根据主键删除城市
	 * @param cityId
	 * @return 
	 */
	    
	int deleteByPrimaryKey(Long cityId);


	    
	/**
	 * @Description:新增一个城市
	 * @param record
	 * @return 
	 */
	    
	int insert(CityInfo record);



	    
	/**
	 * @Description: 根据主键选择一个城市
	 * @param cityId
	 * @return 
	 */
	    
	ResultInfo selectByPrimaryKey(Long cityId);

	    
	/**
	 * @Description:  根据省的id选择城市
	 * @param provinceId
	 * @return 
	 */
	    
	List<CityInfo> selectCitiesByProvinceId(Long provinceId);

	List<SelectModel> getCitiesByProvinceId(Long provinceId);
	    
	/**
	 * @Description: 更新一个市
	 * @param record
	 * @return 
	 */
	    
	int updateByPrimaryKey(CityInfo record);

	    
	/**
	 * @Description: 选择所有城市
	 * @return 
	 */
	    
	List<CityListInfo> selectAllCities();
	

	/**
	 * @Description: 查询全部城市信息
	 * @return 
	 */
	    
	List<CityInfo> selectAll();
	int getCityTotal(Long provinceId);
	/**
	 * 
	 * @Description: 事务性的级联删除
	 * @param cityId
	 */
	void deleteCityCascade(Long cityId);
}