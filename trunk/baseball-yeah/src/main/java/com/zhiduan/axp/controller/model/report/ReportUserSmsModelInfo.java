/**
 * Copyright (c) 2016, 指端科技.
 */
package com.zhiduan.axp.controller.model.report;

import java.io.Serializable;

/**
 * @ClassName: ReportUserSmsModelInfo
 * @Description: 用户短信模板实体
 * @author: xulang
 * @date: 2016年09月20日 11:36
 */

public class ReportUserSmsModelInfo implements Serializable {

    private static final long serialVersionUID = -1709971093577991211L;
    private Long smsModelId;
    private String storeName;
    private String userName;
    private String phone;
    private String modelName;
    private String modelContent;

    public Long getSmsModelId() {
        return smsModelId;
    }

    public void setSmsModelId(Long smsModelId) {
        this.smsModelId = smsModelId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelContent() {
        return modelContent;
    }

    public void setModelContent(String modelContent) {
        this.modelContent = modelContent;
    }
}
