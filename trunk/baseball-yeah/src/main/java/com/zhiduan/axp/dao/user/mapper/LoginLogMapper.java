package com.zhiduan.axp.dao.user.mapper;

import java.util.List;

import javax.inject.Named;

import com.zhiduan.axp.dao.user.bean.LoginLogBean;




/**
* @ClassName: LoginLogMapper
* @Description: 登录日志操作接口
* @author 史丹丹
* @date 2016年3月28日 下午12:44:42 
*
*/
    
@Named("loginLogMapper")
public interface LoginLogMapper {
    int deleteByPrimaryKey(Long loginLogId);

    int insert(LoginLogBean record);

    int insertSelective(LoginLogBean record);

    LoginLogBean selectByPrimaryKey(Long loginLogId);

    int updateByPrimaryKeySelective(LoginLogBean record);

    int updateByPrimaryKey(LoginLogBean record);
    
    
    /**
	 * @Method: selectByUserId
	 * @Description: T根据用户id查询用户登录日志
	 * @param @param userId
	 * @param @param lli
	 * @param @return    参数
	 * @return Object    返回类型
	 * @throws
	 * @author 史丹丹
	 * @date 2016年3月28日 下午2:09:57 
	 **/
	    
	    
    List <LoginLogBean> selectByUserId(Long userId);

	/**
	 * @Method: selectAll
	 * @Description: 查询所有登录日志
	 * @param @return    参数
	 * @return List<LoginLogBean>    返回类型
	 * @throws
	 * @author 史丹丹
	 * @date 2016年3月31日 下午3:40:21 
	 **/
	    
	List<LoginLogBean> selectAll();

	/**
	 * @Description: 查询用户是否首次登录
	 * @param userId
	 * @return 
	 */
	    
	int isFirstLogin(Long userId); 
    
    
}