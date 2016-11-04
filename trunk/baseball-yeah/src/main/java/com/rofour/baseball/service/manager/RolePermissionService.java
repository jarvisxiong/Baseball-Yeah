/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.manager;

import java.util.List;
import java.util.Map;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.RolePermissionInfo;
import com.rofour.baseball.dao.manager.bean.RolePermissionBean;

/**
 * @ClassName: RolePermissionService
 * @Description: 管理中心--角色权限管理接口
 * @author ww
 * @date 2016年3月27日 下午2:01:51
 *
 */
public interface RolePermissionService {

	/**
	 * @Description: 添加角色权限接口
	 * @param permission
	 * @return
	 */

	public int addRolePermission(RolePermissionInfo permission);

	/**
	 * @Description: 按角色权限ID删除角色权限
	 * @param rolePermissionId
	 * @return
	 */

	public int deleteRolePermission(Long rolePermissionId);

	/**
	 * @Description:更新角色权限信息
	 * @param rolePermission
	 * @return
	 */

	public int updateRolePermission(RolePermissionInfo rolePermission);

	/**
	 * @Description: 按主键ID查询角色权限信息
	 * @param rolePermissionId
	 * @return
	 */

	public RolePermissionInfo selectRolePermission(Long rolePermissionId);

	/**
	 * @Description: 按角色ID查询角色所有权限
	 * @return
	 */

	public List<RolePermissionBean> selectAll();

	/**
	 * @Description: 验证重复
	 * @param map
	 * @return
	 */

	boolean isRolePermissionExists(Map<String, Long> map);

	/**
	 * @Description: 批量添加角色权限信息
	 * @param list
	 * @return
	 */

	public ResultInfo batchAdd(List<RolePermissionInfo> list);

	List<RolePermissionBean> selectRolePermissionByUserName(String userName);

	Integer getTotal();

}
