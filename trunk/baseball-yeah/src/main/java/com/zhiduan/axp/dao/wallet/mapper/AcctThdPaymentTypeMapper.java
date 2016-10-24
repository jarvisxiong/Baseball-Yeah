package com.zhiduan.axp.dao.wallet.mapper;

import java.util.List;

import javax.inject.Named;

import com.zhiduan.axp.dao.wallet.bean.AcctThdPaymentTypeBean;

@Named("acctThdPaymentTypeMapper")
public interface AcctThdPaymentTypeMapper {
    int deleteByPrimaryKey(Byte typeCode);

    int insert(AcctThdPaymentTypeBean record);

    int insertSelective(AcctThdPaymentTypeBean record);

    AcctThdPaymentTypeBean selectByPrimaryKey(Byte typeCode);

    int updateByPrimaryKeySelective(AcctThdPaymentTypeBean record);

    int updateByPrimaryKey(AcctThdPaymentTypeBean record);
    
    /**
     * @Description: 获取所有三方支付方式
     * @return 
     */
        
    List<AcctThdPaymentTypeBean> getAllPayType();
}