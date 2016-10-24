package com.zhiduan.axp.dao.activity.mapper;

import com.zhiduan.axp.dao.activity.bean.AcctPolicyUseRuleBean;

import java.util.List;

import javax.inject.Named;


@Named("acctPolicyUseRuleMapper")
public interface AcctPolicyUseRuleMapper {
    int deleteByPrimaryKey(Integer policyId);

    int insert(AcctPolicyUseRuleBean record);

    int insertSelective(AcctPolicyUseRuleBean record);

    AcctPolicyUseRuleBean selectByPrimaryKey(Integer ruleId);

    List<AcctPolicyUseRuleBean> selectByPolicyId(Integer ruleId);

    int updateByPrimaryKeySelective(AcctPolicyUseRuleBean record);

    int updateByPrimaryKey(AcctPolicyUseRuleBean record);


    /**
     * @param voucherId
     * @return
     * @Description: 根据代金券Id获取代金券使用规则
     */

    List<AcctPolicyUseRuleBean> getUseRulesByVoucheId(Long voucherId);
}