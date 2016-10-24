/**
 * @Title: ExpStoreBean.java
 * @Package com.zhiduan.axp.idl.storecenter.dao
 * @Project: axp-idl
 * @Description: (用一句话描述该文件做什么)
 * @author heyi
 * @date 2016年4月8日 下午3:40:02
 * @version V1.0
 * ──────────────────────────────────
 * Copyright (c) 2016, 指端科技.
 */


package com.zhiduan.axp.dao.store.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ExpStoreBean
 * @Description: TODO(快递站点信息, 做查询用)
 * @author heyi
 * @date 2016年4月8日 下午3:40:02
 *
 */

public class ExpStoreBean implements Serializable {


    private static final long serialVersionUID = 5232643228923231989L;
    /**
     * 快递站点编号
     */
    private Long storeId;
    /**
     * 快递站点编码
     */
    private String storeCode;
    /**
     * 快递站点名称
     */
    private String storeName;
    /**
     * 联系地址
     */
    private String location;
    /**
     * 快递站点状态 0 禁用 1 启用
     */
    private Integer status;
    /**
     * 快递站点类型 2 门店 1快递站点
     */
    private Byte type;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 负责人
     */
    private String supervisorName;
    /**
     * 物流商名称
     */
    private String ecName;
    /**
     * 物流商编号
     */
    private String ecGcode;
    private String colCode;
    private String colName;
    private List<Long> ecList;
    /**
     * 默认物流商编码
     */
    private Long defaultECId;
    /**
     * 默认物流商名称
     */
    private String defaultECName;
    
    private Byte packetModeMgr;
    private Byte packetModeSend;

	/**
	 * 打烊模式  0 关闭  1 开启  
	 * wuhongxue 2016.9.18增加打烊状态字段
	 */
	private Byte closeMode;
    
    public Byte getPacketModeSend() {
        return packetModeSend;
    }

    public void setPacketModeSend(Byte packetModeSend) {
        this.packetModeSend = packetModeSend;
    }

    public ExpStoreBean() {

    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getEcName() {
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    public String getEcGcode() {
        return ecGcode;
    }

    public void setEcGcode(String ecGcode) {
        this.ecGcode = ecGcode;
    }

    public List<Long> getEcList() {
        return ecList;
    }

    public void setEcList(List<Long> ecList) {
        this.ecList = ecList;
    }

    @Override
    public String toString() {
        return "ExpStoreBean{" +
                "storeId=" + storeId +
                ", storeCode='" + storeCode + '\'' +
                ", storeName='" + storeName + '\'' +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", phone='" + phone + '\'' +
                ", supervisorName='" + supervisorName + '\'' +
                ", ecName='" + ecName + '\'' +
                ", ecGcode='" + ecGcode + '\'' +
                ", ecList=" + ecList +
                '}';
    }

	public String getColCode() {
		return colCode;
	}

	public void setColCode(String colCode) {
		this.colCode = colCode;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public Long getDefaultECId() {
		return defaultECId;
	}

	public void setDefaultECId(Long defaultECId) {
		this.defaultECId = defaultECId;
	}

	public String getDefaultECName() {
		return defaultECName;
	}

	public void setDefaultECName(String defaultECName) {
		this.defaultECName = defaultECName;
	}

	public Byte getPacketModeMgr() {
		return packetModeMgr;
	}

	public void setPacketModeMgr(Byte packetModeMgr) {
		this.packetModeMgr = packetModeMgr;
	}

	public Byte getCloseMode() {
		return closeMode;
	}

	public void setCloseMode(Byte closeMode) {
		this.closeMode = closeMode;
	}
}
