package com.zhiduan.axp.dao.store.bean;

import java.io.Serializable;
/**
 * 查询门店实体
 * @author xl
 *
 */
public class SearchSiteBean implements Serializable {

	private static final long serialVersionUID = -8545132693639935168L;

	/**
	 * 门店编号
	 */
	private String siteId;
	/**
	 * 门店名称
	 */
	private String siteName;
	
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
}
