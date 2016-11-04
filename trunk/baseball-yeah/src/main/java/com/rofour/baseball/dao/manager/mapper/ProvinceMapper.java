/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.controller.model.RequestWorkSigninInfo;
import com.rofour.baseball.dao.manager.bean.ProvinceBean;

/**
 * @ClassName: ProvinceMapper
 * @Description: 省的sql语句映射类
 * @author liujingbo
 * @date 2016年3月26日 下午9:29:52
 *
 */
@Named("provinceMapper")
public interface ProvinceMapper {
	/**
	 * 
	 * @Description: 根据主键删除
	 * @param provinceId
	 * @return 删除的数量
	 */
	int deleteByPrimaryKey(Long provinceId);
	/**
	 * 
	 * @Description: 增加
	 * @param record
	 * @return int
	 */
	int insert(ProvinceBean record);
	/**
	 * 
	 * @Description: 增加
	 * @param record
	 * @return int
	 */
	int insertSelective(ProvinceBean record);
	/**
	 * 
	 * @Description: 根据主键查询
	 * @param provinceId
	 * @return ProvinceBean
	 */
	ProvinceBean selectByPrimaryKey(Long provinceId);
	/**
	 * 
	 * @Description: 查询 所有
	 * @return List<ProvinceBean>
	 */
	List<ProvinceBean> selectAllProvince();

	List<ProvinceBean> selectAllProvinceAndWorkSignin(Date date);

	/**
	 * 查询所有的省，运力，分页
	 * @param requestWorkSigninInfo
	 * @return
	 */
	List<ProvinceBean> selectAllProvinceAndWorkSigninPage(RequestWorkSigninInfo requestWorkSigninInfo);

	Long selectAllWorkSignin(Date date);
	/**
	 * 
	 * @Description: 更新
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKeySelective(ProvinceBean record);
	/**
	 * 
	 * @Description: 省份的名字是否重复
	 * @param map
	 * @return 重复的数量
	 */
	int isExistSameProvinceName(Map<Object,Object> map);
	/**
	 * 
	 * @Description: 修改自己的时是否重复
	 * @param map
	 * @return 重复的数量
	 */
	int isExistSameProvinceNameItSelf(Map<Object,Object> map);
	/**
	 * 
	 * @Description: 根据主键更新
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKey(ProvinceBean record);
	int getProvinceTotal();
	/**
	 * 
	 * @Description: 批量删除
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
}