/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.service.manager.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rofour.baseball.common.RedisCommons;
import com.rofour.baseball.common.RedisKeyConstants;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.manager.CityInfo;
import com.rofour.baseball.controller.model.manager.CityListInfo;
import com.rofour.baseball.dao.manager.bean.CityBean;
import com.rofour.baseball.dao.manager.mapper.CityMapper;
import com.rofour.baseball.dao.manager.mapper.CountyMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.CityService;
import com.rofour.baseball.service.wallet.impl.WalletDrawServiceImpl;

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
	
	@Resource(name="redisCommons")
	private RedisCommons redisCommons;

    Logger logger = LoggerFactory.getLogger(WalletDrawServiceImpl.class);
	/**
	 * @Description: 通过城市id删除
	 * @param cityId
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CityService#deleteByPrimaryKey(java.lang.Long)
	 */
	public int deleteByPrimaryKey(Long cityId) {
		/*return citymapper.deleteByPrimaryKey(Long.valueOf(cityId));*/
		int count = 0;
		CityBean cityBean = citymapper.selectByPrimaryKey(cityId);
		if(cityBean != null && cityBean.getProvinceId() != null){
			count = citymapper.deleteByPrimaryKey(cityId);
			if(count != 0){
				delCache();
			}
		}
		return count;
	}
	
	/**
	 * @Description: 添加城市
	 * @param record
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CityService#insert(com.rofour.baseball.idl.managecenter.service.entity.City)
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
//            removeCityCache();
			delCache();
			return count;
		} else {
			throw new BusinessException("01024");
		}
		

	}

	/**
	 * @Description: 通过ID查询市信息
	 * @param cityId
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CityService#selectByPrimaryKey(java.lang.Long)
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
	 * @see com.rofour.baseball.idl.managecenter.service.CityService#selectCitiesByProvinceId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<CityInfo> selectCitiesByProvinceId(Long provinceId) {
		List<CityInfo> dataList = new ArrayList<CityInfo>();
		List<CityBean> citybeans = citymapper.selectCitiesByProvinceId(provinceId);
		for (int i = 0; i < citybeans.size(); i++) {

			CityInfo city = new CityInfo();
			BeanUtils.copyProperties(citybeans.get(i), city);
			dataList.add(city);
		}
		return  dataList;
		/*String redisKey = RedisKeyConstants.CITY_MAP;
		List<CityInfo> dataList = redisCommons.hGet(redisKey, String.valueOf(provinceId), List.class);
		if(CollectionUtils.isEmpty(dataList)){
			dataList = new ArrayList<CityInfo>();
			List<CityBean> citybeans = citymapper.selectCitiesByProvinceId(provinceId);
			if (citybeans != null && citybeans.size() != 0) {
				for (int i = 0; i < citybeans.size(); i++) {
					CityInfo city = new CityInfo();
					BeanUtils.copyProperties(citybeans.get(i), city);
					dataList.add(city);
				}
				redisCommons.hSet(redisKey, String.valueOf(provinceId), dataList);
			}
		}
		return dataList;*/

	}

	@SuppressWarnings("unchecked")
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
		/*String redisKey = RedisKeyConstants.CITY_MAP;
		List<CityInfo> dataList = redisCommons.hGet(redisKey, String.valueOf(provinceId), List.class);
		List<SelectModel> dataModelList = new ArrayList<SelectModel>();
		if(CollectionUtils.isEmpty(dataList)){
			dataList = new ArrayList<CityInfo>();
			List<CityBean> citybeans = citymapper.selectCitiesByProvinceId(provinceId);
			if (citybeans != null && citybeans.size() != 0) {
				for (int i = 0; i < citybeans.size(); i++) {
					CityInfo city = new CityInfo();
					BeanUtils.copyProperties(citybeans.get(i), city);
					dataList.add(city);
				}
				for (int i = 0; i < citybeans.size(); i++) {
					SelectModel city = new SelectModel();
					city.setId(citybeans.get(i).getCityId().toString());
					city.setText(citybeans.get(i).getCityName());
					dataModelList.add(city);
				}
				redisCommons.hSet(redisKey, String.valueOf(provinceId), dataList);
			}
		}
		return dataModelList;*/
	}

	/**
	 * @Description: 根据ID更新城市信息
	 * @param record
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CityService#updateByPrimaryKey(com.rofour.baseball.idl.managecenter.service.entity.City)
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
//            removeCityCache();
			delCache();
			return count;
		} else {
			throw new BusinessException("01024");
		}
		
	}

	/**
	 * @Description: 查询有学校的城市
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CityService#selectAllCities()
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
		/*String redisKey = RedisKeyConstants.CITY_ALL_HASCOLLEGE_MAP;
		List<CityListInfo> datalist = redisCommons.getList(redisKey, CityListInfo.class);
		if(CollectionUtils.isEmpty(datalist)){
			datalist = new ArrayList<CityListInfo>();
			List<CityBean> list = citymapper.selectAllCities();
			if (list != null && !list.isEmpty()) {
				for (CityBean item : list) {
					CityListInfo city = new CityListInfo();
					BeanUtils.copyProperties(item, city);
					datalist.add(city);
				}
				redisCommons.set(redisKey, datalist);
			}
		}
		return datalist;*/
	}

	/**
	 * @Description: 查询所有城市
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.CityService#selectAll()
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
		/*String redisKey = RedisKeyConstants.CITY_ALL_MAP;
		List<CityInfo> datalist = redisCommons.getList(redisKey, CityInfo.class);
		if(CollectionUtils.isEmpty(datalist)){
			datalist = new ArrayList<CityInfo>();
			List<CityBean> list = citymapper.selectAll();
			if (list != null && !list.isEmpty()) {
				for (CityBean item : list) {
					CityInfo city = new CityInfo();
					BeanUtils.copyProperties(item, city);
					datalist.add(city);
				}
				redisCommons.set(redisKey, datalist);
			}
		}
		return datalist;*/
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
//        removeCityCache();
		
	}

    /**
     * 删除城市的缓存
     */
	/*private void removeCityCache(){
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
	}*/
	
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
     * @param cityId
     * @return
     */
    @Override
    public CityBean selectById(Long cityId)
    {
        return citymapper.selectByPrimaryKey(cityId);
    }
	
}
