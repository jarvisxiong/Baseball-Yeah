/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.controller.model.manager.MenuInfo;
import com.zhiduan.axp.dao.manager.bean.RolePermissionBean;

/**
 * @ClassName: RolePermissionMapper
 * @Description: 角色权限管理操作接口
 * @author WW
 * @date 2016年3月26日 下午1:42:48
 */
@Named("rolePermissionMapper")
public interface RolePermissionMapper {
	/**
	 * @Description: 新增角色权限
	 * @param  record
	 * @return int
	 **/
	int insert(RolePermissionBean record);

	/**
	 * @Description: 按主键ID删除角色权限
	 * @param rolePermissionId
	 * @return 删除的数量
	 **/
	int deleteByPrimaryKey(Long rolePermissionId);

	/**
	 * @Description: 修改角色权限
	 * @param record
	 * @return 更新的数量
	 **/
	int updateByPrimaryKey(RolePermissionBean record);

	/**
	 * @Description: 按主键ID查找角色权限
	 * @param rolePermissionId
	 * @return RolePermissionBean 
	 **/
	RolePermissionBean selectByPrimaryKey(Long rolePermissionId);

	/**
	 * @Description: 根据角色ID查询角色权限
	 * @param roleId
	 * @return List<RolePermissionBean>
	 **/

	List<RolePermissionBean> selectAllPermission(Long roleId);

	/**
	 * @Description: 验证重复
	 * @param map
	 * @return 重复的数量
	 **/
	int isRolePermissionExists(Map<String, Long> map);

	/**
	 * @Description: 批量添加角色权限信息
	 * @param list
	 * @return 增加的数量
	 **/
	int batchInsert(List<RolePermissionBean> list);
	/**
	 * 
	 * @Description: 查询全部
	 * @return ist<RolePermissionBean>
	 */ 
	List<RolePermissionBean> selectAll();

	/**
	 * @Description: 按角色ID删除角色权限
	 * @param roleId
	 * @return 删除的数量
	 **/
	int deleteByRoleId(Long id);
	
	/**
	 * @Method: selectAllPermissionByUserName
	 * @Description: 通过用户名获取所有的权限Id
	 * @param @param userName
	 * @param @return    参数
	 * @return List<RolePermissionBean>    返回类型
	 * @throws
	 * @author ZhangLei
	 * @date 2016年5月12日 下午2:40:14 
	 **/
	    
	List<RolePermissionBean> selectAllPermissionByUserName(String userName);
	
	/**
	 * @Method: getTotal
	 * @Description: 获取所有权限的总数
	 * @param @return    参数
	 * @return Integer    返回类型
	 * @throws
	 * @author ZhangLei
	 * @date 2016年5月12日 下午2:39:57 
	 **/
	    
	Integer getTotal();
	
	/**
	 * @Method: addOrUpdate
	 * @Description: 增加或者更新权限
	 * @param menus TODO
	 * @param @return    参数
	 * @return Integer    返回类型
	 * @throws
	 * @author ZhangLei
	 * @date 2016年5月12日 下午2:39:41 
	 **/
	    
	Integer addOrUpdate(List<MenuInfo> menus);
	
	    
	/**
	 * @Method: addMenus
	 * @Description: 增加menu
	 * @param @param menus
	 * @param @return    参数
	 * @return Integer    返回类型
	 * @throws
	 * @author ZhangLei
	 * @date 2016年5月12日 下午3:38:32 
	 **/
	    
	Integer addMenus(List<RolePermissionBean> menus);
	
	
	/**
	 * @Description: 根据角色Id查询角色权限
	 * @param roleId
	 * @return 
	 */
	    
	List<RolePermissionBean> selectByRoleId(Long roleId);
}
