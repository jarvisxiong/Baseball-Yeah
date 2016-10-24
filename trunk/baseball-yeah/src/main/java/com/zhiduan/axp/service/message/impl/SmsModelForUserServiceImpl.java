/**
 * Copyright (c) 2016, 指端科技.
 */
package com.zhiduan.axp.service.message.impl;

import com.zhiduan.axp.controller.model.message.SmsModelForUserInfo;
import com.zhiduan.axp.dao.message.bean.SmsModelForUserBean;
import com.zhiduan.axp.dao.message.mapper.SmsModelForUserMapper;
import com.zhiduan.axp.service.message.SmsModelForUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SmsModelForUserServiceImpl
 * @Description: 用户自定义模板服务实现
 * @author: xulang
 * @date: 2016年09月20日 13:24
 */
@Service("smsModelForUserService")
public class SmsModelForUserServiceImpl implements SmsModelForUserService {

    @Autowired
    @Qualifier("smsModelForUserMapper")
    private SmsModelForUserMapper smsModelForUserMapper;

    @Override
    public int delete(Long smsModelId) {
        return smsModelForUserMapper.deleteByPrimaryKey(smsModelId);
    }

    @Override
    public int insert(SmsModelForUserInfo record) {
        SmsModelForUserBean smsModelForUserBean = new SmsModelForUserBean();
        BeanUtils.copyProperties(record, smsModelForUserBean);
        return smsModelForUserMapper.insertSelective(smsModelForUserBean);
    }

    @Override
    public SmsModelForUserInfo selectById(Long smsModelId) {
        SmsModelForUserBean smsModelForUserBean = smsModelForUserMapper.selectByPrimaryKey(smsModelId);
        SmsModelForUserInfo smsModelForUserInfo = new SmsModelForUserInfo();
        BeanUtils.copyProperties(smsModelForUserBean, smsModelForUserInfo);
        return smsModelForUserInfo;
    }

    @Override
    public int update(SmsModelForUserInfo record) {
        SmsModelForUserBean smsModelForUserBean = new SmsModelForUserBean();
        BeanUtils.copyProperties(record, smsModelForUserBean);
        return smsModelForUserMapper.updateByPrimaryKey(smsModelForUserBean);
    }
}
