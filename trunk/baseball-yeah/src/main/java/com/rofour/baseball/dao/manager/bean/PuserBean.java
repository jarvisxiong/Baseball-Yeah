package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zcq
 * @ClassName:
 * @Description: 众包用户
 * @date 2016/10/20 20:53
 */
public class PuserBean implements Serializable {

    private static final long serialVersionUID = -8089560587027933359L;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 签到数
     */
    private Long WorkSignin;

    /**
     * 创建时间
     */
    private Date createDate;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getWorkSignin() {
        return WorkSignin;
    }

    public void setWorkSignin(Long workSignin) {
        WorkSignin = workSignin;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
