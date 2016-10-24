package com.zhiduan.axp.dao.activity.mapper;

import com.zhiduan.axp.dao.activity.bean.AcctQuotaBean;

import javax.inject.Named;

@Named("acctQuotaMapper")
public interface AcctQuotaMapper {
    int deleteByPrimaryKey(Integer quotaId);

    int insert(AcctQuotaBean record);

    int insertSelective(AcctQuotaBean record);

    AcctQuotaBean selectByPrimaryKey(Integer quotaId);

    int updateByPrimaryKeySelective(AcctQuotaBean record);

    int updateByPrimaryKey(AcctQuotaBean record);
}