package com.rofour.baseball.service.manager;

import com.rofour.baseball.dao.manager.bean.CityOrderConfBean;

import java.util.List;

public interface CityOrderConfService {

	int insert(CityOrderConfBean bean);

	int updateCostValue(CityOrderConfBean bean);

	int del(CityOrderConfBean bean);

	List<CityOrderConfBean> getAll(CityOrderConfBean bean);

	int selectAllCount(CityOrderConfBean bean);
	
	int selectIsExtNameCount(CityOrderConfBean bean);
	
	int enable(CityOrderConfBean bean);
	
	int disable(CityOrderConfBean bean);

}
