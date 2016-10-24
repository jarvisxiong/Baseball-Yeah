/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager;

import java.util.List;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.CountyInfo;

/**
 * @ClassName: CountyService
 * @Description: 县的服务接口
 * @author liujingbo
 * @date 2016年3月26日 下午9:31:46
 *
 */

public interface CountyService {

	/**
	 * @Description: 根据主键删除县
	 * @param countyId
	 * @return
	 */

	int deleteByPrimaryKey(Long countyId);

	/**
	 * @Description: 新增一个县
	 * @param record
	 * @return
	 */

	int insert(CountyInfo record);

	/**
	 * @Description: 根据主键选择一个县
	 * @param countyId
	 * @return
	 */

	ResultInfo selectByPrimaryKey(Long countyId);

	/**
	 * @Description: 根据市的id选择县
	 * @param countyId
	 * @return
	 */

	List<CountyInfo> selectCountiesByCityId(Long cityId);

	/**
	 * @Description: 更新县信息
	 * @param record
	 * @return
	 */

	int updateByPrimaryKey(CountyInfo record);

	/**
	 * @Description: 查询全部县区
	 * @return
	 */

	List<CountyInfo> selectAll();
	int getCountyTotal(Long cityId);
	/**
	 * 
	 * @Description:根据cityId,删除区县
	 * @param cityId
	 */
	void deleteByCityId(Long cityId);
}