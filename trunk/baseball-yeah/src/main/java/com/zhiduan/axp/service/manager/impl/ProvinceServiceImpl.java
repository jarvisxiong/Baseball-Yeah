/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.ProvinceInfo;
import com.zhiduan.axp.dao.manager.bean.CityBean;
import com.zhiduan.axp.dao.manager.bean.ProvinceBean;
import com.zhiduan.axp.dao.manager.mapper.CityMapper;
import com.zhiduan.axp.dao.manager.mapper.CountyMapper;
import com.zhiduan.axp.dao.manager.mapper.ProvinceMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.ProvinceService;

/**
 * @ClassName: ProvinceServiceImpl
 * @Description: 省的实现类
 * @author liujingbo
 * @date 2016年3月26日 下午8:46:36
 *
 */

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {
	@Autowired
	@Qualifier("provinceMapper")
	private ProvinceMapper provincemapper;
	@Autowired
	@Qualifier("cityMapper")
	private CityMapper citymapper;
	@Autowired
	@Qualifier("countyMapper")
	private CountyMapper countyMapper;

	/**
	 * @Description: 删除一个省
	 * @param provinceId
	 * @return
	 */
	public int deleteByPrimaryKey(Long provinceId) {
		if(provinceId == null){
			throw new BusinessException("111");
		}
		int	count = provincemapper.deleteByPrimaryKey(provinceId);
		if(count == 0){
			throw new BusinessException("01007");
		}
		return count;
	}

	/**
	 * @Description: 插入一个省
	 * @param record
	 * @return
	 */
	public int insert(ProvinceInfo record) {
		validate(record);
		ProvinceBean provincebean = new ProvinceBean();
		BeanUtils.copyProperties(record, provincebean);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("provinceName", provincebean.getProvinceName());
		int count = provincemapper.isExistSameProvinceName(map);
		if (count > 0) {
			throw new BusinessException("01006");
		}
		count = provincemapper.insert(provincebean);
		return count;
	}

	/*
	 * @param provinceId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.ProvinceService#selectByPrimaryKey(java.lang.Long)
	 */
	public ResultInfo selectByPrimaryKey(Long provinceId) {
		if(provinceId == null){
			throw new BusinessException("111");
		}
		ProvinceBean provincebean = provincemapper.selectByPrimaryKey(provinceId);
		if (provincebean != null) {
			ProvinceInfo province = new ProvinceInfo();
			BeanUtils.copyProperties(provincebean, province);
			return new ResultInfo(0, "", "查询成功", province);
		} else {
			throw new BusinessException("01007");
		}

	}

	/**
	 * @Description: 查询所有的省
	 * @return 
	 */
	public List<ProvinceInfo> selectAllProvince() {
		List<ProvinceBean> provincebeans = provincemapper.selectAllProvince();
		if (provincebeans != null && provincebeans.size() != 0) {
			List<ProvinceInfo> dataList = new ArrayList<ProvinceInfo>();
			for (int i = 0; i < provincebeans.size(); i++) {
				ProvinceInfo province = new ProvinceInfo();
				BeanUtils.copyProperties(provincebeans.get(i), province);
				dataList.add(province);
			}
			return  dataList;
		} else {
			throw new BusinessException("01007");
		}
	}

	/**
	 * @Description: 更新一个省
	 * @param record
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.ProvinceService#updateByPrimaryKey(com.zhiduan.axp.idl.managecenter.service.entity.Province)
	 */
	public int updateByPrimaryKey(ProvinceInfo record) {
		validate(record);
		if(record.getProvinceId() == null){
			throw new BusinessException("111");
		}
		ProvinceBean provincebean = new ProvinceBean();
		BeanUtils.copyProperties(record, provincebean);
		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("provinceId", provincebean.getProvinceId());
		map.put("provinceName", provincebean.getProvinceName());
		count = provincemapper.isExistSameProvinceName(map);
		int number = provincemapper.isExistSameProvinceNameItSelf(map);
		boolean selfexsit = false;
		if (number > 0) {
			selfexsit = true;
		}
		if (count > 0 && !selfexsit) {
			throw new BusinessException("01006");
		}
		count = provincemapper.updateByPrimaryKey(provincebean);
		if(count == 0){
			throw new BusinessException("01007");
		}
		return count;
	}
	/**
	 * 
	 * @Description: 校验
	 * @param provinceinfo
	 */
	private void validate(ProvinceInfo provinceinfo) {
		if (provinceinfo == null || provinceinfo.getProvinceName() == null 
				|| provinceinfo.getProvinceName().equals("")) {
			throw new BusinessException("111");
		} 
		if (provinceinfo.getProvinceName().length() > 20) {
			throw new BusinessException("112");
		}
	}

	@Override
	public Integer getProvinceTotal() {
		return provincemapper.getProvinceTotal();
	}
	/**
	 * 
	 * @Description: 级联删除省份和省份下的城市,和区县
	 * @param provinceId
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void deleteProvinceCascade(Long provinceId) {
		deleteByPrimaryKey(provinceId);
		List<CityBean> cities = citymapper.selectCitiesByProvinceId(provinceId);
		List<Long> ids = new ArrayList<>();
		for(CityBean city:cities){
			ids.add(city.getCityId());
		}
		if(!ids.isEmpty()){
			//删除所有城市
			citymapper.batchDelete(ids);
			//删除所有城市下的区县
			countyMapper.batchDelete(ids);
		}
	}
}
