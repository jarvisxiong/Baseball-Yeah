/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.controller.model.manager;

import java.io.Serializable;
import java.util.Date;

/**
 * Express company info.
 *
 * @author will
 * @Description
 * @date 2016 -08-08 15:17:44
 */
public class ExpressCompanyInfo implements Serializable {

	    
	private static final long serialVersionUID = 1L;
	/**
	 * 编号
	 */
	private Long expresscompanyid;
	/**
	 * 全称
	 */
	private String fullname;
	/**
	 * 简称
	 */
	private String simplename;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 排序号
	 */
	private Long sortno;
	/**
	 * 重要程度
	 */
	private Byte importance;
	/**
	 * LOGO
	 */
	private String logo;
	/**
	 * 级别
	 */
	private Byte level;
	/**
	 * 客服电话
	 */
	private String hotline;
	
	/**
	 * 修改时间
	 */
	private Date modifyTime;
    /**
     * 快递公司编码(old)
     */
	private String ecGcode;
	
	/**
	 * 是否启用 1 启用 0 未启用
	 */
	private Byte beenabled;
	
	public Byte getBeenabled() {
		return beenabled;
	}

	public void setBeenabled(Byte beenabled) {
		this.beenabled = beenabled;
	}

	public ExpressCompanyInfo() {

	}
	
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getExpresscompanyid() {
		return expresscompanyid;
	}

	public void setExpresscompanyid(Long expresscompanyid) {
		this.expresscompanyid = expresscompanyid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getSimplename() {
		return simplename;
	}

	public void setSimplename(String simplename) {
		this.simplename = simplename;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getSortno() {
		return sortno;
	}

	public void setSortno(Long sortno) {
		this.sortno = sortno;
	}

	public Byte getImportance() {
		return importance;
	}

	public void setImportance(Byte importance) {
		this.importance = importance;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getEcGcode() {
		return ecGcode;
	}

	public void setEcGcode(String ecGcode) {
		this.ecGcode = ecGcode;
	}

	
}
