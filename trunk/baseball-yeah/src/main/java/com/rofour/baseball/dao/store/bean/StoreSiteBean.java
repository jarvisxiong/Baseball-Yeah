package com.rofour.baseball.dao.store.bean;

import java.io.Serializable;

/**
 * 商户_快递站点实体类
 * 
 * @author WJ
 *
 */
public class StoreSiteBean implements Serializable{
	/**
	 * @Fields serialVersionUID 
	 */
	    
	private static final long serialVersionUID = -22496120772340796L;
	/**
	 * store_id
	 */
	private Long stoExpId;
	/**
	 * phoneNumber
	 */
	private String phone;
	/**
	 * leaderName
	 */
	private String supervisorName;
	  /**
     * 众包模式  0 关闭  1 开启
     */
    private Byte packetModeMgr;

	private Byte packetModeSend;
	
	/**
     * 打烊模式  0 关闭  1 开启
     */
	private Byte closeMode;

	public Byte getPacketModeSend() {
		return packetModeSend;
	}

	public void setPacketModeSend(Byte packetModeSend) {
		this.packetModeSend = packetModeSend;
	}

	public StoreSiteBean() {
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getStoExpId() {
		return stoExpId;
	}

	public void setStoExpId(Long stoExpId) {
		this.stoExpId = stoExpId;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public StoreSiteBean(Long stoExpId, String phone, String supervisorName,Byte packetModeMgr) {
		super();
		this.stoExpId = stoExpId;
		this.phone = phone;
		this.supervisorName = supervisorName;
		this.packetModeMgr=packetModeMgr;
	}

	@Override
	public String toString() {
		return "StoreSiteBean [stoExpId=" + stoExpId + ", phone=" + phone + ", supervisorName=" + supervisorName + "]";
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
