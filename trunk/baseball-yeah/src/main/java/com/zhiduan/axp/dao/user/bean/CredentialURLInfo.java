package com.zhiduan.axp.dao.user.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-05-10.
 */
public class CredentialURLInfo implements Serializable {
    private static final long serialVersionUID = 5035447427501903647L;
    /**
     * 证件类型 1.手持身份证照，2.身份证照片 3.其他
     */
    private int type;
    /**
     * 文件URL
     */
    private String url;

    public CredentialURLInfo() {
    }

    public CredentialURLInfo(int type, String url) {
        super();
        this.type = type;
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CredentialURLInfo [type=" + type + ", url=" + url + "]";
    }
}

