package com.zhiduan.axp.dao.activity.mapper;

import com.zhiduan.axp.dao.activity.bean.AcctPolicyPutRuleBean;

import javax.inject.Named;
import java.util.List;


@Named("acctPolicyPutRuleMapper")
public interface AcctPolicyPutRuleMapper {
    int deleteByPrimaryKey(Integer policyId);

    int insert(AcctPolicyPutRuleBean record);

    int insertSelective(AcctPolicyPutRuleBean record);

    AcctPolicyPutRuleBean selectByPrimaryKey(Integer ruleId);

    List<AcctPolicyPutRuleBean> selectByPolicyId(Integer PolicyId);

    int updateByPrimaryKeySelective(AcctPolicyPutRuleBean record);

    int updateByPrimaryKey(AcctPolicyPutRuleBean record);
}