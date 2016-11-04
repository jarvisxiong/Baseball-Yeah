package com.rofour.baseball.dao.user.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.controller.model.UserInfo;
import com.rofour.baseball.dao.user.bean.UserTypeBean;

/**
* @ClassName: UserTypeMapper
* @Description: 操作用户基本信息接口
* @author ZhangLei
* @date 2016年3月26日 下午5:59:15 
*
*/
    
@Named("userTypeMapper")
public interface UserTypeMapper {
	/**
	 * 根据用户类型编码删除用户类型信息
	 */
    Integer deleteByPrimaryKey(Long userTypeId);

    /**
	 * 插入用户类型数据
	 */
    Integer insert(UserTypeBean record);

    /**
	 * 动态插入用户类型信息
	 */
    Integer insertSelective(UserTypeBean record);

    /**
	 * 根据用户类型编码查询用户类型信息
	 */
    UserTypeBean selectByPrimaryKey(Long userTypeId);

    /**
	 * 根据用户类型编码动态更新用户数据
	 */
    Integer updateByPrimaryKeySelective(UserTypeBean record);

    /**
	 * 根据用户类型编码更新用户信息
	 */
    Integer updateByPrimaryKey(UserTypeBean record);
    
    /**
	 * 根据用户名和用户类型查询用户
	 */
    List<UserTypeBean> selectByUserAndType(UserInfo userInfo);

	/**
	 * 收件人帐号激活
	 * @param map
	 * @return
     */
	int updateCuserStatus(Map<String,String> map);
	
	/**
	 * 众包人帐号激活
	 * @param map
	 * @return
     */
	int updatePuserStatus(Map<String,String> map);
}