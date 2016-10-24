/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.dao.manager.bean.CityBean;

/**
 * @ClassName: CityMapper
 * @Description: 市的跟sql语句映射类
 * @author liujingbo
 * @date 2016年3月26日 下午9:28:01
 *
 */
@Named("cityMapper")
public interface CityMapper {
	/**
	 * 
	 * @Description: 根据主键删除
	 * @param cityId
	 * @return 删除的数量
	 */
	int deleteByPrimaryKey(Long cityId);
	/**
	 * 
	 * @Description: 增加
	 * @param record
	 * @return int
	 */
	int insert(CityBean record);
	/**
	 * 
	 * @Description: 增加
	 * @param record
	 * @return int
	 */
	int insertSelective(CityBean record);
	/**
	 * 
	 * @Description: 根据主键查询
	 * @param cityId
	 * @return CityBean
	 */
	CityBean selectByPrimaryKey(Long cityId);
	/**
	 * 
	 * @Description: 根据省份id查询城市
	 * @param provinceId
	 * @return List<CityBean>
	 */
	List<CityBean> selectCitiesByProvinceId(Long provinceId);
	/**
	 * 
	 * @Description: 根据主键更新
	 * @param record
	 * @return 
	 */
	int updateByPrimaryKeySelective(CityBean record);
	/**
	 * 
	 * @Description: 更新时判断是否重名
	 * @param map
	 * @return 已存在的数量
	 */
	int isExistSameCityName(Map<Object,Object> map);
	/**
	 * 
	 * @Description: 修改自身
	 * @param map
	 * @return 已存在的数量
	 */
	int isExistSameCityNameItSelf(Map<Object,Object> map);
	/**
	 * 
	 * @Description: 是否存在相同的城市名
	 * @param map
	 * @return 已存在的数量
	 */
	int isExistSameCityNameInsert(Map<Object,Object> map);
	/**
	 * 
	 * @Description: 根据主键更新
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKey(CityBean record);
	/**
	 * 
	 * @Description: 查询所有城市
	 * @return List<CityBean>
	 */
	List<CityBean> selectAllCities();
	
	/**
	 * 
	 * @Description: 查询所有城市
	 * @return List<CityBean>
	 */
	List<CityBean> selectAll();
	int getCityTotal(Long provinceId);
	/**
	 * 
	 * @Description:批量删除
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
	/**
	 * 
	 * @Description: 批量删除,根据省份id
	 * @param ids
	 */
	void batchDeleteByProvinceId(List<Long> ids);
	/**
	 * 
	 * @Description: 根据省份id,查出城市id集合
	 * @param ids
	 */
	List<Long> selectCidsByPids(List<Long> ids);
}