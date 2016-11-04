/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.controller.model.manager.RoleInfo;
import com.rofour.baseball.dao.manager.bean.RoleBean;


/**
* @ClassName: RoleMapper
* @Description: 角色管理操作接口
* @author WW
* @date 2016年3月26日 下午1:34:48 
*/
@Named("roleMapper")
public interface RoleMapper {
	/**
     * @Description: 新增角色
     * @param roleInfo
     * @return int
     **/
	int insert(RoleInfo roleInfo);
	
	/**
     * @Description: 按主键ID删除角色
     * @param  roleId
     * @return 删除的数量
     **/
        
	int deleteByPrimaryKey(Long roleId);
	
	/**
     * @Description: 修改角色
     * @param  record
     * @return 更新的数量
     **/
	int updateByPrimaryKey(RoleInfo record);
	
	/**
     * @Description: 按主键ID查找角色
     * @param roleId
     * @return RoleBean
     **/
	RoleBean selectByPrimaryKey(Long roleId);
	/**
     * @Description: 查看所有角色列表
	 * @param roleInfo TODO
     * @return List<RoleBean>
     **/
	List<RoleBean> selectAllRole(RoleInfo roleInfo);
	    
	/**
     * @Description: 查看所有角色列表
	 * @param roleInfo TODO
     * @return List<RoleBean>
     **/
	List<RoleBean> selectAllRoleSelect();
	/**
	 * @Description: 返回已存在的roleName条数,理论上是唯一
	 * @param map 条件
	 * @return 重复的数量
	 **/
	int ifNameExist(Map<String, Object> map);
	
	List<RoleBean> selectAllRoleByUserName(String userName);
	
	Integer getTotal(RoleInfo roleInfo);
}
