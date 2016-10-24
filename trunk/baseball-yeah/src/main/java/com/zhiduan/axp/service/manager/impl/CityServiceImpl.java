/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiduan.axp.common.Constant;
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.service.wallet.impl.WalletDrawServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.CityInfo;
import com.zhiduan.axp.controller.model.manager.CityListInfo;
import com.zhiduan.axp.dao.manager.bean.CityBean;
import com.zhiduan.axp.dao.manager.mapper.CityMapper;
import com.zhiduan.axp.dao.manager.mapper.CountyMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.CityService;
import com.zhiduan.axp.common.HttpClientUtils;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
 * @author Administrator
 *
 */
/**
 * @ClassName: CityServiceImpl
 * @Description: 市的实现类
 * @author liujingbo
 * @date 2016年3月26日 下午8:42:42
 *
 */

@Service("cityService")
public class CityServiceImpl implements CityService {

	@Autowired
	@Qualifier("cityMapper")
	private CityMapper citymapper;
	@Autowired
	@Qualifier("countyMapper")
	private CountyMapper countyMapper;

    Logger logger = LoggerFactory.getLogger(WalletDrawServiceImpl.class);
	/**
	 * @Description: 通过城市id删除
	 * @param cityId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CityService#deleteByPrimaryKey(java.lang.Long)
	 */
	public int deleteByPrimaryKey(Long cityId) {
		return citymapper.deleteByPrimaryKey(Long.valueOf(cityId));
	}

	/**
	 * @Description: 添加城市
	 * @param record
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CityService#insert(com.zhiduan.axp.idl.managecenter.service.entity.City)
	 */
	public int insert(CityInfo record) {
		validate(record);
		CityBean citybean = new CityBean();
		BeanUtils.copyProperties(record, citybean);
		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("provinceId", citybean.getProvinceId());
		map.put("cityName", citybean.getCityName());
		// 调用dao层数据实现添加功能，并返回结果
		count = citymapper.isExistSameCityNameInsert(map);

		if (count > 0)
			throw new BusinessException("01025");
		count = citymapper.insert(citybean);
		if (count != 0) {
            removeCityCache();
			return count;
		} else {
			throw new BusinessException("01024");
		}
		

	}

	/**
	 * @Description: 通过ID查询市信息
	 * @param cityId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CityService#selectByPrimaryKey(java.lang.Long)
	 */
	public ResultInfo selectByPrimaryKey(Long cityId) {
		CityInfo cityInfo = new CityInfo();
		CityBean citybean = citymapper.selectByPrimaryKey(cityId);
		if (citybean != null) {
			BeanUtils.copyProperties(citybean, cityInfo);
			return new ResultInfo(0, "", "查询成功", cityInfo);
		} else {
			throw new BusinessException("01024");
		}

	}

	/**
	 * @Description: 根据省ID查询城市信息
	 * @param provinceId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CityService#selectCitiesByProvinceId(java.lang.Long)
	 */
	public List<CityInfo> selectCitiesByProvinceId(Long provinceId) {
		List<CityInfo> dataList = new ArrayList<CityInfo>();
		List<CityBean> citybeans = citymapper.selectCitiesByProvinceId(provinceId);
		for (int i = 0; i < citybeans.size(); i++) {

			CityInfo city = new CityInfo();
			BeanUtils.copyProperties(citybeans.get(i), city);
			dataList.add(city);
		}
		return  dataList;

	}

	public List<SelectModel> getCitiesByProvinceId(Long provinceId) {
		List<SelectModel> dataList = new ArrayList<SelectModel>();
		List<CityBean> citybeans = citymapper.selectCitiesByProvinceId(provinceId);
		for (int i = 0; i < citybeans.size(); i++) {

			SelectModel city = new SelectModel();
			city.setId(citybeans.get(i).getCityId().toString());
			city.setText(citybeans.get(i).getCityName());
			dataList.add(city);
		}
		return  dataList;

	}

	/**
	 * @Description: 根据ID更新城市信息
	 * @param record
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CityService#updateByPrimaryKey(com.zhiduan.axp.idl.managecenter.service.entity.City)
	 */
	public int updateByPrimaryKey(CityInfo record) {
		CityBean citybean = new CityBean();
		BeanUtils.copyProperties(record, citybean);
		int count = 0;

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("provinceId", citybean.getProvinceId());
		map.put("cityName", citybean.getCityName());
		map.put("cityId", citybean.getCityId());
		count = citymapper.isExistSameCityName(map);
		if (count != 0) {
			throw new BusinessException("01025");
		}
		count = citymapper.updateByPrimaryKey(citybean);
		if (count != 0) {
            removeCityCache();
			return count;
		} else {
			throw new BusinessException("01024");
		}
		
	}

	/**
	 * @Description: 查询有学校的城市
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CityService#selectAllCities()
	 */
	@Override
	public List<CityListInfo> selectAllCities() {
		List<CityBean> list = citymapper.selectAllCities();
		List<CityListInfo> datalist = new ArrayList<CityListInfo>();

		for (CityBean item : list) {
			CityListInfo city = new CityListInfo();
			BeanUtils.copyProperties(item, city);
			datalist.add(city);
		}
	
			return datalist;
	}

	/**
	 * @Description: 查询所有城市
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.CityService#selectAll()
	 */
	@Override
	public List<CityInfo> selectAll() {
		List<CityBean> list = citymapper.selectAll();
		List<CityInfo> datalist = new ArrayList<CityInfo>();

		for (CityBean item : list) {
			CityInfo city = new CityInfo();
			BeanUtils.copyProperties(item, city);
			datalist.add(city);
		}
			return datalist;
	

	}

	/**
	 * 
	 * @Description: 校验方法
	 * @param cityinfo
	 * @return ResultInfo 操作结果bean
	 */
	private void validate(CityInfo cityinfo) {
		if (cityinfo == null || cityinfo.getCityName() == null || cityinfo.getCityName().equals("")
				|| cityinfo.getProvinceId() == null) {
			throw new BusinessException("111");
		}
		if (cityinfo.getCityName().length() > 50 || (cityinfo.getPostCode()!= null && cityinfo.getPostCode().length() > 10)
				|| (cityinfo.getTelZoneCode() != null && cityinfo.getTelZoneCode().length() > 10) 
				|| (cityinfo.getSortNo() != null && cityinfo.getSortNo() > 32767)
				|| (cityinfo.getSortNo() != null && cityinfo.getSortNo() < -32768)) {
			throw new BusinessException("112");
		}
	

	}

	@Override
	public int getCityTotal(Long provinceId) {
		return citymapper.getCityTotal(provinceId);
	}
	/**
	 * 
	 * @Description: 事务性的级联删除
	 * @param cityId
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteCityCascade(Long cityId) {
		deleteByPrimaryKey(cityId);
		countyMapper.deleteByCityId(cityId);
        removeCityCache();
	}

    /**
     * 删除城市的缓存
     */
	private void removeCityCache(){
        final TypeReference<ResultInfo<?>> cityTypeRef=new TypeReference<ResultInfo<?>>(){};
        String url= Constant.axpurl.get("city_remove_serv");
        ResultInfo<?> data = null;
        try {
            data = (ResultInfo<?>) HttpClientUtils.post(url, null, cityTypeRef);
            if(data.getSuccess()!=0)
            {
                logger.error("调用AXP接口失败:" + data.getCode() + "," + data.getMessage());
                throw new BusinessException("104");
            }
            logger.info("是否调用成功:"+data.getSuccess());
        } catch (IOException e) {
            throw new BusinessException("104");
        }
	}
}
