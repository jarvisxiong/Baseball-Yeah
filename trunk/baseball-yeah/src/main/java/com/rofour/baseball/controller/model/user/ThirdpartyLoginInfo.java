package com.rofour.baseball.controller.model.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方登录 Info。
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class ThirdpartyLoginInfo implements Serializable {

    private static final long serialVersionUID = 9216643504737766821L;
    /**
     * ID。
     */
    private long id;
    /**
     * 手机。
     */
    private String phone;
    /**
     * 第三方平台标识。
     */
    private String openId;
    /**
     * 用户来源。
     */
    private String source;
    /**
     * 注册时间。
     */
    private Date regtime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    @Override
    public String toString() {
        return "ThirdpartyLoginInfo{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", openId='" + openId + '\'' +
                ", source='" + source + '\'' +
                ", regtime=" + regtime +
                '}';
    }
}
