/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.manager;

import java.util.List;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.manager.CityInfo;
import com.rofour.baseball.controller.model.manager.CityListInfo;
import com.rofour.baseball.dao.manager.bean.CityBean;

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
	
	CityBean selectById(Long cityId);

	    
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