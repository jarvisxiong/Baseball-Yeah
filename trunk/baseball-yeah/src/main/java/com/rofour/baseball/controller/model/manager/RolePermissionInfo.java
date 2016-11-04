/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;

/**
 * @ClassName: RolePermissionInfo
 * @Description: 角色权限DTO类,用作接口返回实体
 * @author ww
 * @date 2016年3月27日 下午1:01:34
 *
 */
public class RolePermissionInfo implements Serializable {
	private static final long serialVersionUID = -9117774818633262998L;
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

	public RolePermissionInfo(Long rolePermissionId, Long roleId, Long menuId, String action) {
		this.rolePermissionId = rolePermissionId;
		this.roleId = roleId;
		this.menuId = menuId;
		this.action = action;
	}

	public RolePermissionInfo() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePermissionInfo other = (RolePermissionInfo) obj;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}
	
	
}
