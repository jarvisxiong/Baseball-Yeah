package com.zhiduan.axp.dao.report.bean;

import java.io.Serializable;

/**
 *
 * @ClassName: ReportStoreSmsBean
 * @Description: 货源短信统日报表返回实体
 * @author xl
 * @date 2016年4月21日 上午11:30:03
 *
 */
public class ReportStoreSmsBean implements Serializable {

	private static final long serialVersionUID = 7032573373820585624L;
	/**
	 * 门店Id
	 */
	private long siteId;
	/**
	 * 门店编号
	 */
	private String siteCode;
	/**
	 * 门店名称
	 */
	private String siteName;
	/**
	 * 归属校区
	 */
	private String collegeName;
	/**
	 * 站点编号
	 */
	private String storeCode;
	/**
	 * 站点名称
	 */
	private String storeName;
	/**
	 * 站点地址
	 */
	private String location;
	/**
	 * 负责人
	 */
	private String supervisor;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 快递公司
	 */
	private String expressCompanyName;
	/**
	 * 站点状态
	 */
	private String status;
	/**
	 * 统计日期
	 */
	private String submitTime;
	/**
	 * 短信总数
	 */
	private long totalCount;
	/**
	 * 带单号短信数
	 */
	private long billCount;
	/**
	 * 成功数
	 */
	private long sucessCount;
	/**
	 * 失败数
	 */
	private long failCount;

	public long getSiteId() {
		return siteId;
	}

	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
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

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getBillCount() {
		return billCount;
	}

	public void setBillCount(long billCount) {
		this.billCount = billCount;
	}

	public long getSucessCount() {
		return sucessCount;
	}

	public void setSucessCount(long sucessCount) {
		this.sucessCount = sucessCount;
	}

	public long getFailCount() {
		return failCount;
	}

	public void setFailCount(long failCount) {
		this.failCount = failCount;
	}

	@Override
	public String toString() {
		return "ReportStoreSmsBean [siteId=" + siteId + ", siteCode=" + siteCode + ", siteName=" + siteName
				+ ", collegeName=" + collegeName + ", storeCode=" + storeCode + ", storeName=" + storeName
				+ ", location=" + location + ", supervisor=" + supervisor + ", phone=" + phone + ", expressCompanyName="
				+ expressCompanyName + ", status=" + status + ", submitTime=" + submitTime + ", totalCount="
				+ totalCount + ", billCount=" + billCount + ", sucessCount=" + sucessCount + ", failCount=" + failCount
				+ "]";
	}

}
