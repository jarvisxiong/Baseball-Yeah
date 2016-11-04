package com.rofour.baseball.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.dao.manager.bean.CityOrderConfBean;

@Named("cityOrderConfMapper")
public interface CityOrderConfMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(CityOrderConfBean record);

    int insertSelective(CityOrderConfBean record);

    CityOrderConfBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CityOrderConfBean record);

    int updateByPrimaryKey(CityOrderConfBean record);
    
    List<CityOrderConfBean> selectAll(CityOrderConfBean record);
    
    int selectAllCount(CityOrderConfBean record);
    
    int selectIsExtNameCount(CityOrderConfBean record);
    
    int updateCostValueByPrimaryKey(CityOrderConfBean record);
    
    int disable(Object[] ids);
    
    int enable(Object[] ids);
    
}