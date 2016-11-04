/**
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.manager.PropertyDictInfo;

/**
 *
 * @ClassName: PropertyDictService
 * @Description: 属性字典逻辑层接口定义
 * @author heyi
 * @date 2016年4月3日 下午2:22:48
 *
 */
public interface PropertyDictService {

	/**
	 * @Description: 获取所有属性字典
	 * @return
	 */

	List<PropertyDictInfo> getPropertyDictList();

	/**
	 * @Description: 新增属性字典
	 * @param bean
	 */

	void insert(PropertyDictInfo bean,HttpServletRequest request);

	/**
	 * @Description: 修改属性字典
	 * @param model
	 * @return
	 */

	int updateByPrimaryKey(PropertyDictInfo model,HttpServletRequest request);

	/**
	 * @Description: 删除属性字典
	 * @param id
	 * @return
	 */

	int deleteByPrimaryKey(String id,HttpServletRequest request);

	/**
	 * @Description: 获取所有属性字典
	 * @return
	 *
	 */
	 List<PropertyDictInfo> getDictListbyCallAlias(String callAlias);
}
