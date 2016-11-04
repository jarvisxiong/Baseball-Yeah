package com.rofour.baseball.dao.user.bean;

import java.io.Serializable;
import java.util.Date;


/**
* @ClassName: LoginLogBean
* @Description: 登录日志实体类
* @author 史丹丹
* @date 2016年3月28日 下午12:45:34 
*
*/

public class LoginLogBean implements Serializable{
	
	private static final long serialVersionUID = -3553960925257814369L;

	/**
	 * 用户登录日志编号（主键）
	 */
	private Long loginLogId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户姓名
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
     * 登录IP地址
     */
    private String loginIp;

    public LoginLogBean(Long loginLogId, Long userId, String userName, String remark, Date loginTime, String loginLocation, String loginIp) {
        this.loginLogId = loginLogId;
        this.userId = userId;
        this.userName = userName;
        this.remark = remark;
        this.loginTime = loginTime;
        this.loginLocation = loginLocation;
        this.loginIp = loginIp;
    }

    public LoginLogBean() {
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