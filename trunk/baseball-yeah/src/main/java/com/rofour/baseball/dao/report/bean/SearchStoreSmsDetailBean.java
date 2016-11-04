package com.rofour.baseball.dao.report.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SearchStoreSmsDetailBean
 * @Description: 短信统计明细返回实体
 * @author xl
 * @date 2016年4月15日 下午3:13:07
 *
 */

public class SearchStoreSmsDetailBean implements Serializable {
	private static final long serialVersionUID = 8851903180323540280L;
	/**
	 * 门店名称
	 */
	private String storeName;
	/**
	 * 单号
	 */
	private String billNo;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 发送人
	 */
	private String sendName;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	@Override
	public String toString() {
		return "SearchStoreSmsDetailBean [storeName=" + storeName + ", billNo=" + billNo + ", phone=" + phone
				+ ", status=" + status + ", sendTime=" + sendTime + ", sendName=" + sendName + "]";
	}

}
