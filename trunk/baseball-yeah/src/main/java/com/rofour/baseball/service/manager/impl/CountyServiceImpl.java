/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.service.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.common.RedisCommons;
import com.rofour.baseball.common.RedisKeyConstants;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.CountyInfo;
import com.rofour.baseball.dao.manager.bean.CountyBean;
import com.rofour.baseball.dao.manager.mapper.CountyMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.CountyService;

/**
 * @ClassName: CountyServiceImpl
 * @Description: 县的实现类
 * @author liujingbo
 * @date 2016年4月6日 下午5:02:47
 *
 */

@Service("countyService")
public class CountyServiceImpl implements CountyService {


	@Autowired
	@Qualifier("countyMapper")
	private CountyMapper countyMapper;
	
	@Resource(name="redisCommons")
	private RedisCommons redisCommons;

	/**
	 * @Description: 删除县
	 * @param countyId
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CountyService#deleteByPrimaryKey(java.lang.Long)
	 */
	public int deleteByPrimaryKey(Long countyId) {
		/*int count = 0;
		count = countyMapper.deleteByPrimaryKey(Long.valueOf(countyId));
		return count;*/
		int count = 0;
		CountyBean countyBean = countyMapper.selectByPrimaryKey(countyId);
		if(countyBean != null && countyBean.getCityId() != null){
			count = countyMapper.deleteByPrimaryKey(Long.valueOf(countyId));
			if(count != 0){
				delCache();
			}
		}
		return count;
	}
	
	/**
	 * @Description: 新增县
	 * @param record
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CountyService#insert(com.rofour.baseball.idl.managecenter.service.entity.County)
	 */
	public int insert(CountyInfo record) {
		validate(record);
		CountyBean countyBean = new CountyBean();
		BeanUtils.copyProperties(record, countyBean);
		int count = 0;

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("cityId", countyBean.getCityId());
		map.put("countyName", countyBean.getCountyName());
		count = countyMapper.isExistSameCountyNameInsert(map);
		if (count > 0)
			throw new BusinessException("01028");
		count = countyMapper.insert(countyBean);
        if(count==0)
        	throw new BusinessException("01029");
        delCache();
		return count;
	}

	/**
	 * @Description: 根据ID查询一个县
	 * @param countyId
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CountyService#selectByPrimaryKey(java.lang.Long)
	 */
	public ResultInfo selectByPrimaryKey(Long countyId) {
		if (countyId == null)
			throw new BusinessException("");
		CountyInfo county = new CountyInfo();
		CountyBean countyBean = new CountyBean();
		countyBean = countyMapper.selectByPrimaryKey(countyId);
		if (countyBean != null) {
			BeanUtils.copyProperties(countyBean, county);
			return new ResultInfo(0, "", "查询成功", county);
		} else {
        	throw new BusinessException("01029");
		}

	}

	/**
	 * @Description: 根据城市ID查询所有县
	 * @param cityId
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CountyService#selectCountiesByCityId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<CountyInfo> selectCountiesByCityId(Long cityId) {
		if (cityId == null)
			throw new BusinessException("");
		List<CountyInfo> dataList = new ArrayList<CountyInfo>();

		List<CountyBean> countybeans = countyMapper.selectCountiesByCityId(cityId);
		if (countybeans != null && countybeans.size() != 0) {
			for (int i = 0; i < countybeans.size(); i++) {
				CountyInfo countyInfo = new CountyInfo();
				BeanUtils.copyProperties(countybeans.get(i), countyInfo);
				dataList.add(countyInfo);
			}
		}
		return  dataList;
		/*String redisKey = RedisKeyConstants.COUNTY_MAP;
		if (cityId == null){
			throw new BusinessException("");
		}
		List<CountyInfo> dataList = redisCommons.hGet(redisKey, String.valueOf(cityId), List.class);
		if(CollectionUtils.isEmpty(dataList)){
			dataList = new ArrayList<CountyInfo>();
			List<CountyBean> countybeans = countyMapper.selectCountiesByCityId(cityId);
			if (countybeans != null && countybeans.size() != 0) {
				for (int i = 0; i < countybeans.size(); i++) {
					CountyInfo countyInfo = new CountyInfo();
					BeanUtils.copyProperties(countybeans.get(i), countyInfo);
					dataList.add(countyInfo);
				}
				redisCommons.hSet(redisKey, String.valueOf(cityId), dataList);
			}
		}
		return  dataList;*/
	}

	/**
	 * @Description: 更新县信息
	 * @param record
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CountyService#updateByPrimaryKey(com.rofour.baseball.idl.managecenter.service.entity.County)
	 */
	public int updateByPrimaryKey(CountyInfo record) {
		validate(record);
		CountyBean countyBean = new CountyBean();
		int count = 0;
		BeanUtils.copyProperties(record, countyBean);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("cityId", countyBean.getCityId());
		map.put("countyId", countyBean.getCountyId());
		map.put("countyName", countyBean.getCountyName());
		count = countyMapper.isExistSameCountyName(map);

		if (count > 0)
			throw new BusinessException("01028");
		count = countyMapper.updateByPrimaryKey(countyBean);
       if(count==0){
    	   throw new BusinessException("01029");
       }
       delCache();
       return count;
	}

	/**
	 * @Description: 查询全部区县
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CountyService#selectAll()
	 */
	@Override
	public List<CountyInfo> selectAll() {
		List<CountyInfo> countyInfos = new ArrayList<CountyInfo>();
		List<CountyBean> countyBeans = countyMapper.selectAll();
		if (countyBeans != null && !countyBeans.isEmpty()) {
			for (CountyBean item : countyBeans) {
				CountyInfo county = new CountyInfo();
				BeanUtils.copyProperties(item, county);
				countyInfos.add(county);
			}
		}

		return countyInfos;
		/*String redisKey = RedisKeyConstants.COUNTY_ALL_MAP;
		List<CountyInfo> countyInfos = redisCommons.getList(redisKey, CountyInfo.class);
		if(CollectionUtils.isEmpty(countyInfos)){
			countyInfos = new ArrayList<CountyInfo>();
			List<CountyBean> countyBeans = countyMapper.selectAll();
			if (countyBeans != null && !countyBeans.isEmpty()) {
				for (CountyBean item : countyBeans) {
					CountyInfo county = new CountyInfo();
					BeanUtils.copyProperties(item, county);
					countyInfos.add(county);
				}
				redisCommons.set(redisKey, countyInfos);
			}
		}
		return countyInfos;*/
	}

	/**
	 * 
	 * @Description: 校验
	 * @param countyinfo
	 * @return ResultInfo 操作结果bean
	 */
	private void validate(CountyInfo countyInfo) {
		if(countyInfo == null||StringUtils.isBlank(countyInfo.getCountyName())||countyInfo.getCityId() == null){
			throw new BusinessException("111");
		}
		if(countyInfo.getCountyName().length() > 50 
				||(countyInfo.getPostCode() != null && countyInfo.getPostCode().length() > 10)
				||(countyInfo.getSortNo() != null && countyInfo.getSortNo() > 32767)
				||(countyInfo.getSortNo() != null && countyInfo.getSortNo() < -32768)){
			throw new BusinessException("112");
		}
		

	}


	@Override
	public int getCountyTotal(Long cityId) {
		return countyMapper.getCountyTotal(cityId);
	}
	/**
	 * 
	 * @Description:根据cityId,删除区县
	 * @param cityId
	 */
	@Override
	public void deleteByCityId(Long cityId) {
		countyMapper.deleteByCityId(cityId);
		delCache();
	}
	
	public void delAllKeysCache(){
		redisCommons.delete(RedisKeyConstants.COUNTY_MAP);
		redisCommons.delete(RedisKeyConstants.CITY_MAP);
		redisCommons.delete(RedisKeyConstants.PROVINCE_MAP);
	}
	
	public void delAllMapCache(){
		redisCommons.delete(RedisKeyConstants.COUNTY_ALL_MAP);
		redisCommons.delete(RedisKeyConstants.CITY_ALL_MAP);
		redisCommons.delete(RedisKeyConstants.CITY_ALL_HASCOLLEGE_MAP);
		redisCommons.delete(RedisKeyConstants.PROVINCE_ALL_MAP);
	}
	
	public void delCache(){
		delAllKeysCache();
		delAllMapCache();
	}

}
