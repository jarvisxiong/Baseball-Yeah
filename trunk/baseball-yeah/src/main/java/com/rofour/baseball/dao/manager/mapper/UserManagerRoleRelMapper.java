/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.dao.manager.bean.UserManagerRoleRelBean;

/**
* @ClassName: UserManagerRoleRelMapper
* @Description: 管理用户_角色操作接口
* @author 王伟
* @date 2016年4月13日 下午16:23:55
*/
@Named("userManagerRoleRelMapper")
public interface UserManagerRoleRelMapper {
	/**
     * @Description: 新增管理用户ID,角色ID
     * @param  record
     * @return int
     **/
	int insert(UserManagerRoleRelBean record); 
	
	/**
     * @Description: 根据管理用户ID删除所有角色信息
     * @param  record
     * @return 删除的数量
     **/
	int deleteByManagerId(Long userManagerId);
	
	/**
	 * @Description: 批量新增数据
	 * @param relBeans
	 * @return 插入的数量
	 **/
	    
	int batchInsert(List<UserManagerRoleRelBean> relBeans);
	
	
	/**
	 * @Description: 根据用户id查找角色用户对应关系
	 * @param userManagerId
	 * @return 
	 */
	    
	List<UserManagerRoleRelBean> selectByUserId(Long userManagerId);
}
