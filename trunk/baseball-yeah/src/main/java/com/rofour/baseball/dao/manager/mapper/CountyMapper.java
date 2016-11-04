/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.mapper;


import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.dao.manager.bean.CountyBean;

@Named("countyMapper")
public interface CountyMapper {
	/**
	 * 
	 * @Description: 根据主键删除
	 * @param countyId 
	 * @return 删除的数量
	 */
	int deleteByPrimaryKey(Long countyId);
	/**
	 * 
	 * @Description: 增加
	 * @param record
	 * @return int
	 */
	int insert(CountyBean record);
	/**
	 * 
	 * @Description: 增加
	 * @param record
	 * @return int
	 */
	int insertSelective(CountyBean record);
	/**
	 * 
	 * @Description: 根据主键查询
	 * @param countyId
	 * @return CountyBean
	 */
	CountyBean selectByPrimaryKey(Long countyId);
	/**
	 * 
	 * @Description: 根据城市id查询
	 * @param countyId
	 * @return List<CountyBean>
	 */
	List<CountyBean> selectCountiesByCityId(Long countyId);
	/**
	 * 
	 * @Description: 根据主键更新
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKeySelective(CountyBean record);
	/**
	 * 
	 * @Description: 更新时判断重名
	 * @param map
	 * @return 已存在的数量
	 */
	int isExistSameCountyName(Map<Object,Object> map);
	/**
	 * 
	 * @Description: 修改自身
	 * @param map
	 * @return 已存在的数量
	 */
	int isExistSameCountyNameItSelf(Map<Object,Object> map);
	/**
	 * 
	 * @Description: 增加时判断重名
	 * @param map
	 * @return 已存在的数量
	 */
	int isExistSameCountyNameInsert(Map<Object,Object> map);
	/**
	 * 
	 * @Description: 根据主键更新
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKey(CountyBean record);
	
	/**
	 * 
	 * @Description: 查询全部县区
	 * @return List<CountyBean>    
	 */
	List<CountyBean> selectAll();
	int getCountyTotal(Long cityId);
	/**
	 * @Description:根据cityId,删除区县
	 * @param cityId
	 */
	void deleteByCityId(Long cityId);
	/**
	 * 
	 * @Description: 批量删除
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
}