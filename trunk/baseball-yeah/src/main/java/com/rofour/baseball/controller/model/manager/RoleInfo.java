/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;
import java.util.List;

import com.rofour.baseball.controller.model.BasePage;

/**
 * @ClassName: RoleInfo
 * @Description: 角色DTO类,用作接口返回实体
 * @author ww
 * @date 2016年3月27日 下午1:05:12
 *
 */
public class RoleInfo extends BasePage implements Serializable {
	
	private static final long serialVersionUID = 5292518366813976392L;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 角色
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

	/**
	 * 选中menuIdStr
	 */
	private List<String> checkNodes;
	


	public List<String> getCheckNodes() {
		return checkNodes;
	}

	public void setCheckNodes(List<String> checkNodes) {
		this.checkNodes = checkNodes;
	}

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
		this.roleName = roleName.trim();
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
		return "RoleInfo [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleType=" + roleType + ", beSysPrivilege="
				+ beSysPrivilege + ", checkNodes=" + checkNodes + "]";
	}



}
