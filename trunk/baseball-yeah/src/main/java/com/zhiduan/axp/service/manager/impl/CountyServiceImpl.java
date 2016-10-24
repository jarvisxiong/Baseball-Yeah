/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.CountyInfo;
import com.zhiduan.axp.dao.manager.bean.CountyBean;
import com.zhiduan.axp.dao.manager.mapper.CountyMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.CountyService;

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

	/**
	 * @Description: 删除县
	 * @param countyId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CountyService#deleteByPrimaryKey(java.lang.Long)
	 */
	public int deleteByPrimaryKey(Long countyId) {
		int count = 0;
		count = countyMapper.deleteByPrimaryKey(Long.valueOf(countyId));
		return count;
	}

	/**
	 * @Description: 新增县
	 * @param record
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CountyService#insert(com.zhiduan.axp.idl.managecenter.service.entity.County)
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
		return count;
	}

	/**
	 * @Description: 根据ID查询一个县
	 * @param countyId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CountyService#selectByPrimaryKey(java.lang.Long)
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
	 * @see com.zhiduan.axp.idl.managecenter.service.CountyService#selectCountiesByCityId(java.lang.Long)
	 */
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
	}

	/**
	 * @Description: 更新县信息
	 * @param record
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CountyService#updateByPrimaryKey(com.zhiduan.axp.idl.managecenter.service.entity.County)
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
		return count;
	}

	/**
	 * @Description: 查询全部区县
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CountyService#selectAll()
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
	}

}
