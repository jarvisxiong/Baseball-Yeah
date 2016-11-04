/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.manager;

import java.util.List;

import com.rofour.baseball.controller.model.RequestWorkSigninInfo;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.ProvinceInfo;
import com.rofour.baseball.dao.manager.bean.CityBean;
import com.rofour.baseball.dao.manager.bean.ProvinceBean;

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
     * @Description: 查询一个省
     * @param provinceId
     * @return 
     */
        
    ProvinceBean selectById(Long provinceId);

	/**
	 * @Description: 查询所有的省
	 * @return 
	 */
	    
	List<ProvinceInfo> selectAllProvince();

	/**
	 * 查询全国的明日运力
	 * @return
	 */
	Long selectAllWorkSignin();

	/**
	 * @Description: 查询所有的省及运力
	 * @return
	 */
	List<ProvinceBean> selectAllProvinceAndWorkSignin();

	/**
	 * @Description: 查询所有的省及运力 分页
	 * @return
	 */
	List<ProvinceBean> selectAllProvinceAndWorkSigninPage(RequestWorkSigninInfo requestWorkSigninInfo);

	/**
	 * 查询城市和运力
	 * @param provinceId
	 * @return
	 */
	List<CityBean> selectCityAndWorkSignin(Long provinceId);

	/**
	 * 查询所有城市和运力
	 * @return CityBean list
	 */
	List<CityBean> selectAllCitiesAndSignin();


	/**
	 * 查询城市和运力 分页
	 * @param requestWorkSigninInfo
	 * @return
	 */
	List<CityBean> selectCityAndWorkSigninPage(RequestWorkSigninInfo requestWorkSigninInfo);



	/**
     * @Description: 查询所有的省
     * @return 
     */
        
    List<ProvinceBean> selectProvincesFromAreaId(int areaId);
    List<CityBean> selectCitiesByProvinceId(Long provinceId);
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