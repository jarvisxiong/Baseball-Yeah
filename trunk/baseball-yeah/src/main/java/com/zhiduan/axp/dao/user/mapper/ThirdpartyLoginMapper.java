package com.zhiduan.axp.dao.user.mapper;

import java.util.List;

import javax.inject.Named;

import com.zhiduan.axp.dao.user.bean.ThirdpartyLoginBean;

/**
 * 第三方帐户 Mapper。
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
@Named("thirdpartyLoginMapper")
public interface ThirdpartyLoginMapper {

    /**
     * 保存用户第三方帐户信息。
     *
     * @param thirdpartyLogin 第三方帐户
     * @return 受影响行记录数
     */
    int save(ThirdpartyLoginBean thirdpartyLogin);

    /**
     * 根据ID逻辑删除第三方登录记录。
     *
     * @param id 第三方登录ID
     * @return 受影响行记录
     */
    int deleteById(long id);

    /**
     * 根据手机号码与 {@code source} 查询用户第三方信息。
     *
     * @param phone  手机号码（多个号码使用英文","逗号分隔）
     * @param source 用户来源
     * @return 用户登录信息
     */
    List<ThirdpartyLoginBean> findByPhone(String phone, String source);

    /**
     * 根据第三方ID与 {@code source} 查询用户第三方信息。
     *
     * @param openId 第三方ID
     * @param source 用户来源
     * @return 用户登录信息
     */
    ThirdpartyLoginBean findByOpenId(String openId, String source);

}
