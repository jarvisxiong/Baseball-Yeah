/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.AreaInfo;

/**
 * @ClassName: Area
 * @Description: 区域接口
 * @author cy
 * @date 2016-04-18 10:24:31
 */

public interface Area {

	/**
	 * @Description: 增加
	 * @param info
	 * @return
	 */
	ResultInfo addArea(AreaInfo info);

	/**
	 * @Description:删除
	 * @param id
	 * @return
	 */

	ResultInfo delArea(Long id);

	/**
	 * @Description: 更改
	 * @param info
	 * @return
	 */

	ResultInfo updArea(AreaInfo info);

	/**
	 * @Description: 根据主键查找
	 * @param info
	 * @return
	 */

	ResultInfo getAreaByPK(AreaInfo info);

	/**
	 * @Description: 查询所有
	 * @return
	 */

	ResultInfo getAll();
	/**
	 * 
	 * @Description: 事务性的级联删除该区域下的所有省份,城市,区县
	 * @param areaId
	 */
	void deleteAreaCascade(Long areaId);
}