/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.manager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.RoleInfo;
import com.rofour.baseball.dao.manager.bean.RoleBean;

/**
 * @ClassName: RoleService
 * @Description: 管理中心--角色管理接口
 * @author ww
 * @date 2016年3月27日 下午1:06:51
 *
 */
public interface RoleService {

	/**
	 * @Description: 添加角色接口
	 * @param role
	 * @param request TODO
	 * @return
	 */

	public ResultInfo<RoleBean> addRole(RoleInfo role, HttpServletRequest request);

	/**
	 * @Description: 按角色ID删除角色
	 * @param roleId
	 * @param request TODO
	 * @return
	 */

	public ResultInfo<RoleBean> deleteRole(Long roleId, HttpServletRequest request);

	/**
	 * @Description: 更新角色信息
	 * @param role
	 * @param request TODO
	 * @return
	 */

	public int updateRole(RoleInfo role, HttpServletRequest request);

	/**
	 * @Description: 按主键ID查询角色信息
	 * @param roleId
	 * @return
	 */

	public RoleInfo selectRole(Long roleId);

	/**
	 * @Description: 查看角色列表
	 * @param roleInfo TODO
	 * @return
	 * @throws IOException 
	 */

	public List<RoleBean> getRoleList(RoleInfo roleInfo) throws IOException;

	/**
	 * @Description: 判断是否存在role
	 * @param map
	 * @return
	 */

	boolean ifNameExist(Map<String, Object> map);

	List<RoleBean> selectAllRoleByUserName(String userName);

	Integer getTotal(RoleInfo roleInfo);

	ResultInfo<RoleBean> updateRoleAndPermission(RoleInfo roleInfo, HttpServletRequest request);

	List<RoleBean> getRoleList();

}
