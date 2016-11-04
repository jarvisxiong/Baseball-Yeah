package com.rofour.baseball.service.manager;

import java.util.List;

import com.rofour.baseball.dao.manager.bean.CommisionOrderConfBean;

public interface CommisionOrderConfService {

	int insert(CommisionOrderConfBean bean);

	int updateCostValue(CommisionOrderConfBean bean);

	int del(Long id);

	List<CommisionOrderConfBean> getCommisionOrderConfList(CommisionOrderConfBean bean);

	int selectAllCount(CommisionOrderConfBean bean);
	
	int isExistSameRecord(CommisionOrderConfBean bean);
	
	int enable(CommisionOrderConfBean bean);
	
	int disable(CommisionOrderConfBean bean);
	
	CommisionOrderConfBean getNormalUserInfoByPhone(String phone,String roleType);
    
	CommisionOrderConfBean getStoreUserInfoByPhone(String phone);

	List<CommisionOrderConfBean> getSupervisorListByCooId(Long userId);
	
}
