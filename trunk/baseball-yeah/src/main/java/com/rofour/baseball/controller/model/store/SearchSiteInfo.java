package com.rofour.baseball.controller.model.store;

import java.io.Serializable;
/**
 * 查询门店DTO
 * @author xl
 *
 */
public class SearchSiteInfo implements Serializable {

	private static final long serialVersionUID = 5195047040967743931L;

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
