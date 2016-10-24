/**  
* @Title: SmsAccountBean.java
* @Package com.zhiduan.axp.dao.bean
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年5月30日 上午10:59:24 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.dao.bean;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: SmsAccountBean
* @Description: 短信账户实体
* @author heyi
* @date 2016年5月30日 上午10:59:24 
*
*/

public class SmsAccountBean implements Serializable {
	  
	private static final long serialVersionUID = -4898993016754284479L;
	/**
     * 主键
     **/
    private Long id;
    /**
     * 商户ID
     **/
    private Long storeId;
    /**
     * 余量
     **/
    private Integer balance;
    /**
     * 是否启用
     **/
    private Boolean beEnabled;
    /**
     * 锁定次数
     **/
    private Long lockNum;
    /**
     * 新增时间
     **/
    private Date addTime;
    /**
     * 备注
     **/
    private String memo;

    public SmsAccountBean() {
        super();
    }

    public SmsAccountBean(Long id, Long storeId, Integer balance, Boolean beEnabled, Long lockNum, Date addTime, String memo) {
        this.id = id;
        this.storeId = storeId;
        this.balance = balance;
        this.beEnabled = beEnabled;
        this.lockNum = lockNum;
        this.addTime = addTime;
        this.memo = memo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Boolean getBeEnabled() {
        return beEnabled;
    }

    public void setBeEnabled(Boolean beEnabled) {
        this.beEnabled = beEnabled;
    }

    public Long getLockNum() {
        return lockNum;
    }

    public void setLockNum(Long lockNum) {
        this.lockNum = lockNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}
