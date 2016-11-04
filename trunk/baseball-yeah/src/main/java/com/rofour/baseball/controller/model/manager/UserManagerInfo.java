/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.rofour.baseball.controller.model.BasePage;

/**
 * 用户管理业务对象。
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class UserManagerInfo extends BasePage implements Serializable {

    private static final long serialVersionUID = -4360011037999994334L;
    /**
	 * 管理用户ID
	 */
	private Long userManagerId;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 工号
	 */
	private String userCode;
	/**
	 * 所属部门唯一标识
	 */
	private Long deptId;
	/**
	 * 所属职务ID
	 */
	private Long dutyId;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 联系电话
	 */
	private String contactTel;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 是否启用 1启用 0禁用
	 */
	private Byte beEnabled;
	/**
	 * 是否已删除 0未删除 1已删除
	 */
	private Byte beDeleted;
	// #region 扩展字段
	/**
	 * 部门编码
	 */
	private String deptCode;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 职务名称
	 */
	private String dutyName;
	/**
	 * 用户角色Id集合
	 */
	private List<Long> roleIds;
	/**
	 * 用户角色名称集合
	 */
	private List<String> roleNames;
	
	private Long roleId;
	

	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}

	public String getLoginName() {
        return loginName.trim();
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName.trim();
    }

    public String getPassword() {
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }


    public String getGender() {
        return gender.trim();
    }

    public Long getDeptId() {
		return deptId;
	}

	public Long getDutyId() {
		return dutyId;
	}

	public void setGender(String gender) {
        this.gender = gender.trim();
    }

    public String getContactTel() {
        return contactTel.trim();
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel.trim();
    }

    public String getAddress() {
        return address.trim();
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

	public Long getUserManagerId() {
		return userManagerId;
	}

	public void setUserManagerId(Long userManagerId) {
		this.userManagerId = userManagerId;
	}

	public String getUserName() {
		return userName.trim();
	}

	public void setUserName(String userName) {
		this.userName = userName.trim();
	}

	public String getUserCode() {
		return userCode.trim();
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode.trim();
	}

	public Byte getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Byte beEnabled) {
		this.beEnabled = beEnabled;
	}

	public Byte getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Byte beDeleted) {
		this.beDeleted = beDeleted;
	}

	public String getDeptCode() {
		return deptCode.trim();
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode.trim();
	}

	public String getDeptName() {
		return deptName.trim();
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName.trim();
	}

	public String getDutyName() {
		return dutyName.trim();
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName.trim();
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}


	public UserManagerInfo() {
		super();
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public UserManagerInfo(Long userManagerId, String loginName,
			String password, String userName, String userCode, Long deptId,
			Long dutyId, String gender, String contactTel, String address,
			Byte beEnabled, Byte beDeleted, String deptCode, String deptName,
			String dutyName, List<Long> roleIds, List<String> roleNames,
			Long roleId) {
		super();
		this.userManagerId = userManagerId;
		this.loginName = loginName;
		this.password = password;
		this.userName = userName;
		this.userCode = userCode;
		this.deptId = deptId;
		this.dutyId = dutyId;
		this.gender = gender;
		this.contactTel = contactTel;
		this.address = address;
		this.beEnabled = beEnabled;
		this.beDeleted = beDeleted;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.dutyName = dutyName;
		this.roleIds = roleIds;
		this.roleNames = roleNames;
		this.roleId = roleId;
	}



	
}
