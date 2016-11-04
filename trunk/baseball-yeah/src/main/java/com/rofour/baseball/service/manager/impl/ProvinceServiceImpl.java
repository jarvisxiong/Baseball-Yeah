/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.manager.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.rofour.baseball.controller.model.RequestWorkSigninInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rofour.baseball.common.RedisCommons;
import com.rofour.baseball.common.RedisKeyConstants;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.ProvinceInfo;
import com.rofour.baseball.dao.manager.bean.CityBean;
import com.rofour.baseball.dao.manager.bean.ProvinceBean;
import com.rofour.baseball.dao.manager.mapper.CityMapper;
import com.rofour.baseball.dao.manager.mapper.CountyMapper;
import com.rofour.baseball.dao.manager.mapper.ProvinceMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.ProvinceService;

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
	
	@Resource(name="redisCommons")
	private RedisCommons redisCommons;


	/**
	 * 获取明天的日期
	 * @return
	 */
	private Date getTomorrow(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	/**
	 * @Description: 删除一个省
	 * @param provinceId
	 * @return
	 */
	public int deleteByPrimaryKey(Long provinceId) {
		/*if(provinceId == null){
			throw new BusinessException("111");
		}
		int	count = provincemapper.deleteByPrimaryKey(provinceId);
		if(count == 0){
			throw new BusinessException("01007");
		}
		return count;*/
		if(provinceId == null){
			throw new BusinessException("111");
		}
		int count = 0;
		ProvinceBean provinceBean = provincemapper.selectByPrimaryKey(provinceId);
		if(provinceBean != null){
			count = provincemapper.deleteByPrimaryKey(provinceId);
		}
		if(count == 0){
			throw new BusinessException("01007");
		}
		delCache();
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
		if(count > 0){
			delCache();
		}
		return count;
	}

	/*
	 * @param provinceId
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.ProvinceService#selectByPrimaryKey(java.lang.Long)
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
	@SuppressWarnings("unchecked")
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
		/*String redisKey = RedisKeyConstants.PROVINCE_ALL_MAP;
		List<ProvinceInfo> dataList = redisCommons.get(redisKey, List.class);
		if(CollectionUtils.isEmpty(dataList)){
			dataList = new ArrayList<ProvinceInfo>();
			List<ProvinceBean> provincebeans = provincemapper.selectAllProvince();
			if (provincebeans != null && provincebeans.size() != 0) {
				for (int i = 0; i < provincebeans.size(); i++) {
					ProvinceInfo province = new ProvinceInfo();
					BeanUtils.copyProperties(provincebeans.get(i), province);
					dataList.add(province);
				}
				redisCommons.set(redisKey, dataList);
			}else{
				throw new BusinessException("01007");
			}
		}
		return dataList;*/
	}

	/**
	 * 查询全国的明日运力
	 * @return
	 */
	public Long selectAllWorkSignin(){
		return provincemapper.selectAllWorkSignin(this.getTomorrow());
	}

	/**
	 * @Description: 查询所有的省及运力
	 * @return
	 */
	public List<ProvinceBean> selectAllProvinceAndWorkSignin() {
		List<ProvinceBean> provincebeans = provincemapper.selectAllProvinceAndWorkSignin(this.getTomorrow());
		if (provincebeans != null && provincebeans.size() != 0) {
			return  provincebeans;
		} else {
			throw new BusinessException("01007");
		}
	}

	/**
	 * @Description: 查询所有的省及运力
	 * @return
	 */
	public List<ProvinceBean> selectAllProvinceAndWorkSigninPage(RequestWorkSigninInfo requestWorkSigninInfo) {
		List<ProvinceBean> provincebeans = provincemapper.selectAllProvinceAndWorkSigninPage(requestWorkSigninInfo);
		if (provincebeans != null && provincebeans.size() != 0) {
			return  provincebeans;
		} else {
			throw new BusinessException("01007");
		}
	}

	@Override
	public List<CityBean> selectCityAndWorkSignin(Long provinceId) {
		HashMap<String, String> map = new HashMap<>();
		map.put("provinceId",provinceId+"");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		map.put("date",sdf.format(this.getTomorrow()));
		List<CityBean> cities=new ArrayList<CityBean>();
		cities = citymapper.selectCityAndWorkSignin(map);
		return cities;
	}

	@Override
	public List<CityBean> selectAllCitiesAndSignin() {
		HashMap<String, String> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		map.put("date",sdf.format(this.getTomorrow()));
		List<CityBean> cities=new ArrayList<CityBean>();
		cities = citymapper.selectCityAndWorkSignin(map);
		return cities;
	}

	@Override
	public List<CityBean> selectCityAndWorkSigninPage(RequestWorkSigninInfo requestWorkSigninInfo) {
		List<CityBean> cities=new ArrayList<CityBean>();
		cities = citymapper.selectCityAndWorkSigninPage(requestWorkSigninInfo);
		return cities;
	}

	/**
	 * @Description: 更新一个省
	 * @param record
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.ProvinceService#updateByPrimaryKey(com.rofour.baseball.idl.managecenter.service.entity.Province)
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
		delCache();
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

    /**
     * 重载方法
     * @param provinceId
     * @return
     */
    @Override
    public ProvinceBean selectById(Long provinceId)
    {
        return  provincemapper.selectByPrimaryKey(provinceId);
    }

    /**
     * 根据areaID 获取所在大区的省份
     * @param areaId
     * @return
     */
    @Override
    public List<ProvinceBean> selectProvincesFromAreaId(int areaId)
    {
        List<ProvinceBean> listByAreaId=new ArrayList<ProvinceBean>();
        List<ProvinceBean> list=provincemapper.selectAllProvince();
        for(ProvinceBean bean:list)
        {
            if(bean.getAreaId()==areaId)
            {
                listByAreaId.add(bean);
            }
        }
        
        return listByAreaId;
    }
	
    /**
     * 根据provinceID 获取省下所有的城市
     * @param areaId
     * @return
     */
    @Override
    public List<CityBean> selectCitiesByProvinceId(Long provinceId)
    {
        List<CityBean> cities=new ArrayList<CityBean>();
        cities = citymapper.selectCitiesByProvinceId(provinceId);       
        return cities;
    }
}
