package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;
import java.util.Date;

import com.rofour.baseball.controller.model.BasePage;

public class CommisionOrderConfBean extends BasePage implements Serializable{
	    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userId;

    private Long storeId;

    private Long supervisorId;

    private Long collegeId;

    private String roleType;

    private String orderType;

    private String costType;

    private Long costValue;

    private Integer costMode;

    private Integer state;

    private String remark;

    private Date createDate;

    private Long createUser;

    private Date updateDate;

    private Long updateUser;
    
    //以下是不与表字段对应的
    private String createUserName;
    
    private String createStartDate;
    
    private String createEndDate;
    
    private String stateDesc;
    
    private String costTypeDesc;
    
    private String orderTypeDesc;
    
    private String roleTypeDesc;
    
    private String collegeName;
    
    private String supervisorName;
    
    private String userName;
    
    private String phone;
    
    private String supervisorPhone;
    
    private String  ids;
    
    private float costValueYuan;
    
    private boolean isModifyFlag;
    
	public boolean isModifyFlag() {
		return isModifyFlag;
	}

	public void setModifyFlag(boolean isModifyFlag) {
		this.isModifyFlag = isModifyFlag;
	}

	public float getCostValueYuan() {
		return costValueYuan;
	}

	public void setCostValueYuan(float costValueYuan) {
		this.costValueYuan = costValueYuan;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
    
    public String getRoleTypeDesc() {
		return roleTypeDesc;
	}

	public void setRoleTypeDesc(String roleTypeDesc) {
		this.roleTypeDesc = roleTypeDesc;
	}

	public String getSupervisorPhone() {
		return supervisorPhone;
	}

	public void setSupervisorPhone(String supervisorPhone) {
		this.supervisorPhone = supervisorPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(String createStartDate) {
		this.createStartDate = createStartDate;
	}

	public String getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(String createEndDate) {
		this.createEndDate = createEndDate;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public String getCostTypeDesc() {
		return costTypeDesc;
	}

	public void setCostTypeDesc(String costTypeDesc) {
		this.costTypeDesc = costTypeDesc;
	}

	public String getOrderTypeDesc() {
		return orderTypeDesc;
	}

	public void setOrderTypeDesc(String orderTypeDesc) {
		this.orderTypeDesc = orderTypeDesc;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType == null ? null : costType.trim();
    }

    public Long getCostValue() {
        return costValue;
    }

    public void setCostValue(Long costValue) {
        this.costValue = costValue;
    }

    public Integer getCostMode() {
        return costMode;
    }

    public void setCostMode(Integer costMode) {
        this.costMode = costMode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}