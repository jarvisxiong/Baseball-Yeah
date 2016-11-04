package com.rofour.baseball.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.dao.manager.bean.CommisionOrderConfBean;

@Named("commisionOrderConfMapper")
public interface CommisionOrderConfMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(CommisionOrderConfBean record);
    
    /**
     * 增加的时候判断，是否已经存在相同的记录
     * */
    int isExtNormalCostType(CommisionOrderConfBean record);
    
    CommisionOrderConfBean selectByPrimaryKey(Long id);

    int updateByPrimaryKey(CommisionOrderConfBean record);
    
    int selectAllCount(CommisionOrderConfBean record);
    
    List<CommisionOrderConfBean> getCommisionOrderConfList(CommisionOrderConfBean record);
    
    /**
     * 拿到COO对应的姓名和学校名称
     * */
    CommisionOrderConfBean getUserInfoByPhone(CommisionOrderConfBean bean);
    
    /**
     * 拿到商户负责人对应的姓名
     * */
    CommisionOrderConfBean getStoreUserInfoByPhone(String phone);
    
    List<CommisionOrderConfBean> getSupervisorListByCooId(Long userId);
    
    int updateCostValueByPrimaryKey(CommisionOrderConfBean record);
    
    int disable(Object[] ids);
    
    int enable(Object[] ids);
}