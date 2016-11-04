/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;

/**
 * @ClassName: UserManagerRoleRelBean
 * @Description: 管理用户_角色关联实体类
 * @author 王伟
 * @date 2016年4月13日 下午16:19:30
 *
 */
public class UserManagerRoleRelBean implements Serializable {
	private static final long serialVersionUID = -2398274239217901469L;

	/**
	 * 管理用户_角色关联唯一ID
	 */
	private Long userManagerRoleRelId;
	/**
	 * 管理用户编码
	 */
	private Long userManagerId;
	/**
	 * 角色编码
	 */
	private Long roleId;

	public UserManagerRoleRelBean(Long userManagerRoleRelId, Long userManagerId, Long roleId) {
		this.userManagerRoleRelId = userManagerRoleRelId;
		this.userManagerId = userManagerId;
		this.roleId = roleId;
	}

	public UserManagerRoleRelBean(Long userManagerId, Long roleId) {
		super();
		this.userManagerId = userManagerId;
		this.roleId = roleId;
	}

	public UserManagerRoleRelBean() {
		super();
	}

	public Long getUserManagerRoleRelId() {
		return userManagerRoleRelId;
	}

	public void setUserManagerRoleRelId(Long userManagerRoleRelId) {
		this.userManagerRoleRelId = userManagerRoleRelId;
	}

	public Long getUserManagerId() {
		return userManagerId;
	}

	public void setUserManagerId(Long userManagerId) {
		this.userManagerId = userManagerId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
