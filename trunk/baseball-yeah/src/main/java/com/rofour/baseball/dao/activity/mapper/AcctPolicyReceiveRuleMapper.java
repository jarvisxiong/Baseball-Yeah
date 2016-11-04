package com.rofour.baseball.dao.activity.mapper;

import com.rofour.baseball.dao.activity.bean.AcctPolicyReceiveRuleBean;

import javax.inject.Named;
import java.util.List;


@Named("acctPolicyReceiveRuleMapper")
public interface AcctPolicyReceiveRuleMapper {
    int deleteByPrimaryKey(Integer policyId);

    int insert(AcctPolicyReceiveRuleBean record);

    int insertSelective(AcctPolicyReceiveRuleBean record);

    AcctPolicyReceiveRuleBean selectByPrimaryKey(Integer ruleId);
    List<AcctPolicyReceiveRuleBean> selectByPolicyId(Integer PolicyId);
    int updateByPrimaryKeySelective(AcctPolicyReceiveRuleBean record);

    int updateByPrimaryKey(AcctPolicyReceiveRuleBean record);
}