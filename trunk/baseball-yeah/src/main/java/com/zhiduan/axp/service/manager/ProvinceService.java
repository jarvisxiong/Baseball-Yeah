/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager;

import java.util.List;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.ProvinceInfo;

/**
 * @ClassName: ProvinceService
 * @Description: 省的服务接口
 * @author liujingbo
 * @date 2016年3月26日 下午9:32:27
 *
 */

public interface ProvinceService {
	/**
	 * @Description: 删除一个省
	 * @param provinceId
	 * @return 
	 */
	    
	int deleteByPrimaryKey(Long provinceId);

	/**
	 * @Description: 插入一个省
	 * @param record
	 * @return 
	 */
	    
	int insert(ProvinceInfo record);

	/**
	 * @Description: 查询一个省
	 * @param provinceId
	 * @return 
	 */
	    
	ResultInfo selectByPrimaryKey(Long provinceId);

	/**
	 * @Description: 查询所有的省
	 * @return 
	 */
	    
	List<ProvinceInfo> selectAllProvince();

	/**
	 * @Description: 更新一个省
	 * @param record
	 * @return 
	 */
	    
	int updateByPrimaryKey(ProvinceInfo record);
	
	public Integer getProvinceTotal();
	/**
	 * 
	 * @Description: 级联删除省份和省份下的城市,和区县
	 * @param provinceId
	 */
	void deleteProvinceCascade(Long provinceId);
}