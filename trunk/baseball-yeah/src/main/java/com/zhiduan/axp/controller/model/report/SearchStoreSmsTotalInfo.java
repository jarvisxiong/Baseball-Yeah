/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.model.report;

import java.io.Serializable;

/**
 * @ClassName: SearchStoreSmsTotalInfo
 * @Description: 短信统计报表汇总
 * @author xl
 * @date 2016年5月17日 下午1:17:37
 */
public class SearchStoreSmsTotalInfo implements Serializable {

	private static final long serialVersionUID = -725666869855466118L;
	/**
	 * 站点Id
	 */
	private long storeId;
	/**
	 * 站点名称
	 */
	private String storeName;
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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
		return "SearchStoreSmsTotalInfo [storeId=" + storeId + ", storeName=" + storeName + ", totalCount=" + totalCount
				+ ", billCount=" + billCount + ", sucessCount=" + sucessCount + ", failCount=" + failCount + "]";
	}

}
