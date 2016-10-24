package com.zhiduan.axp.service.message;

import com.zhiduan.axp.controller.model.message.SmsModelForUserInfo;

/**
 * @ClassName: SmsModelForUserService
 * @Description: 用户自定义模板服务
 * @author: xulang
 * @date: 2016年09月20日 13:22
 */

public interface SmsModelForUserService {
    int delete(Long smsModelId);

    int insert(SmsModelForUserInfo record);

    SmsModelForUserInfo selectById(Long smsModelId);

    int update(SmsModelForUserInfo record);
}
