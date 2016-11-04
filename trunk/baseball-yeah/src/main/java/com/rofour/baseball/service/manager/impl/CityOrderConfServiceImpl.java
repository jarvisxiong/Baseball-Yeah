/**  
 * Copyright (c) 2016, 指端科技.
 */


    
package com.rofour.baseball.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.dao.manager.bean.CityOrderConfBean;
import com.rofour.baseball.dao.manager.mapper.CityOrderConfMapper;
import com.rofour.baseball.service.manager.CityOrderConfService;

import net.sf.json.JSONArray;

/**
 * @ClassName: CityOrderConfServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2016年10月17日 上午9:30:50 
 */
@Service("cityOrderConfService")
public class CityOrderConfServiceImpl implements CityOrderConfService {

	@Autowired
	@Qualifier("cityOrderConfMapper")
	CityOrderConfMapper cityOrderConfMapper;
	
	@Override
	public int insert(CityOrderConfBean bean) {
		return cityOrderConfMapper.insert(bean);
	}

	@Override
	public int updateCostValue(CityOrderConfBean bean) {
		return cityOrderConfMapper.updateCostValueByPrimaryKey(bean);
	}

	@Override
	public int del(CityOrderConfBean bean) {
		return cityOrderConfMapper.deleteByPrimaryKey(bean.getId());
	}
	
	@Override
	public int enable(CityOrderConfBean bean) {
		JSONArray ids = JSONArray.fromObject(bean.getIds());
		Object[] _ids = ids.toArray();
		return cityOrderConfMapper.enable(_ids);
	}
	
	@Override
	public int disable(CityOrderConfBean bean) {
		JSONArray ids = JSONArray.fromObject(bean.getIds());
		Object[] _ids = ids.toArray();
		return cityOrderConfMapper.disable(_ids);
	}
	
	@Override
	public List<CityOrderConfBean> getAll(CityOrderConfBean bean) {
		return cityOrderConfMapper.selectAll(bean);
	}

	@Override
	public int selectAllCount(CityOrderConfBean bean) {
		
		return cityOrderConfMapper.selectAllCount(bean);
	}
	
	public int selectIsExtNameCount(CityOrderConfBean bean){
		return cityOrderConfMapper.selectIsExtNameCount(bean);
	}

}
