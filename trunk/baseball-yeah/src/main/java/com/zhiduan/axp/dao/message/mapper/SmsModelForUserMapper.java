package com.zhiduan.axp.dao.message.mapper;


import com.zhiduan.axp.dao.message.bean.SmsModelForUserBean;

import javax.inject.Named;

/**
 * @ClassName: SmsModelForUserMapper
 * @Description: 用户自定义模板数据库操作接口
 * @author: xulang
 * @Date: 2016-09-20 13:21
 */
@Named("smsModelForUserMapper")
public interface SmsModelForUserMapper {
    int deleteByPrimaryKey(Long smsModelId);

    int insertSelective(SmsModelForUserBean record);

    SmsModelForUserBean selectByPrimaryKey(Long smsModelId);

    int updateByPrimaryKey(SmsModelForUserBean record);
}