package com.rofour.baseball.dao.user.bean;

import java.io.Serializable;


/**
* @ClassName: TbUserTypeBean
* @Description: 操作用户类型DTO
* @author ZhangLei
* @date 2016年3月25日 下午6:35:30 
*
*/
    
public class UserTypeBean implements Serializable{
    /**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	    
	private static final long serialVersionUID = 4356791132617077894L;

	/**
	 * @Fields userTypeId :用户类型编码
	 */
	    
	private Long userTypeId;

	/**
	 * @Fields userId :用户编码
	 */
    private Long userId;

    /**
	 * @Fields userType :类型
	 */
    private Integer userType;

    /**
	 * @Fields beEnabled :是否启用
	 */
    private Byte beEnabled;

    public UserTypeBean(Long userTypeId, Long userId, Integer userType, Byte beEnabled) {
        this.userTypeId = userTypeId;
        this.userId = userId;
        this.userType = userType;
        this.beEnabled = beEnabled;
    }

    public UserTypeBean() {
        super();
    }


    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Byte getBeEnabled() {
        return beEnabled;
    }

    public void setBeEnabled(Byte beEnabled) {
        this.beEnabled = beEnabled;
    }

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
    
}