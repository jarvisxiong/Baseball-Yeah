package com.rofour.baseball.controller.model.user;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: LoginLogInfo
* @Description: 登录日志外部实体类
* @author 史丹丹
* @date 2016年3月28日 下午12:46:48 
*
*/
    

public class LoginLogInfo implements Serializable{

	private static final long serialVersionUID = -6223988143848481397L;

	/**
	 * 用户登录日志编号（主键）
	 */
	private Long loginLogId;

    /**
     * 用户id
     */
        
    private Long userId;

    /**
     * 登录用户名
     */
        
    private String userName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登陆地点
     */
    private String loginLocation;

    /**
     * 登录ip
     */
    private String loginIp;

    public LoginLogInfo(Long loginLogId, Long userId, String userName, String remark, Date loginTime, String loginLocation, String loginIp) {
        this.loginLogId = loginLogId;
        this.userId = userId;
        this.userName = userName;
        this.remark = remark;
        this.loginTime = loginTime;
        this.loginLocation = loginLocation;
        this.loginIp = loginIp;
    }

    public LoginLogInfo() {
        super();
    }

    public Long getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(Long loginLogId) {
        this.loginLogId = loginLogId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation == null ? null : loginLocation.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }
}