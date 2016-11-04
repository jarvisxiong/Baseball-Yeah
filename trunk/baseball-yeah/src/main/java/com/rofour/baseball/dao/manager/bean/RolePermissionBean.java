/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;

import com.rofour.baseball.controller.model.BasePage;
/**
* @ClassName: RolePermissionBean
* @Description: 角色权限实体类
* @author WW
* @date 2016年3月26日 下午1:25:55 
*
*/
public class RolePermissionBean extends BasePage implements Serializable{
	private static final long serialVersionUID = -1037939749920810624L;
	/**
	 * 角色权限ID
	 */
	private Long rolePermissionId;          
	/**
	 * 角色ID
	 */
	private Long roleId;                    
	/**
	 * 菜单ID
	 */
	private Long menuId;                    
	/**
	 * 动作
	 */
	private String action;
	
	public RolePermissionBean(Long rolePermissionId, Long roleId, Long menuId, String actions) {
	        this.rolePermissionId = rolePermissionId;
	        this.roleId = roleId;
	        this.menuId = menuId;
	        this.action = actions;
	    }

    public RolePermissionBean(Long roleId, Long menuId, String action) {
		super();
		this.roleId = roleId;
		this.menuId = menuId;
		this.action = action;
	}

	public RolePermissionBean() {
	        super();
	    }
	public Long getRolePermissionId() {
		return rolePermissionId;
	}
	public void setRolePermissionId(Long rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	
	
	
	
}
