package com.rofour.baseball.dao.activity.mapper;

import com.rofour.baseball.dao.activity.bean.AcctQuotaBean;

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