package com.zhiduan.axp.dao.user.mapper;

import java.util.List;

import javax.inject.Named;

import com.zhiduan.axp.dao.user.bean.UserOfPotentialBean;


@Named("userOfPotentialMapper")
public interface UserOfPotentialMapper {
    int deleteByPrimaryKey(Long potentialUserId);

    int insert(UserOfPotentialBean record);

    int insertSelective(UserOfPotentialBean record);

    UserOfPotentialBean selectByPrimaryKey(Long potentialUserId);

    /**
     * 根据手机号码查询。
     *
     * @param phone 手机号码
     * @return
     */
    UserOfPotentialBean findByPhone(String phone);

    int updateByPrimaryKeySelective(UserOfPotentialBean record);

    int updateByPrimaryKey(UserOfPotentialBean record);

    int addUserOfPotential(List<UserOfPotentialBean> list);
}