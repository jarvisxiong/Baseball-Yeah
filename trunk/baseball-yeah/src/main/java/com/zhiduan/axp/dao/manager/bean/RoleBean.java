/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;

/**
* @ClassName: RoleBean
* @Description: 角色实体类
* @author WW
* @date 2016年3月26日 下午1:26:12
*
*/
public class RoleBean implements Serializable{
	private static final long serialVersionUID = -5876337659614589407L;
	/**
	 * 角色ID
	 */
	private Long roleId;               
	/**
	 * 角色名称 
	 */
	private String roleName;           
	/**
	 * 角色类型
	 */
	private Byte roleType;          
	/**
	 * 是否系统权限
	 */
	private Byte beSysPrivilege;             
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Byte getRoleType() {
		return roleType;
	}
	public void setRoleType(Byte roleType) {
		this.roleType = roleType;
	}
	public Byte getBeSysPrivilege() {
		return beSysPrivilege;
	}
	public void setBeSysPrivilege(Byte beSysPrivilege) {
		this.beSysPrivilege = beSysPrivilege;
	}
	@Override
	public String toString() {
		return "TbRoleBean [roleId=" + roleId + ", roleName=" + roleName + ", roleType=" + roleType + ", beSysPrivilege=" + beSysPrivilege
				+ "]";
	}
	
	
}	
