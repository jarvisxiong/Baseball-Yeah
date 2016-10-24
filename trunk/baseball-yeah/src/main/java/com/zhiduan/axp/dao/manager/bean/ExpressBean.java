/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: ExpressBean
* @Description: 快递公司dao层实体
* @author heyi
* @date 2016年4月5日 上午11:33:36 
*
 */
public class ExpressBean implements Serializable {

	    
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
	 * 是否启用 1 启用 0 未启用
	 */
	private Byte beenabled;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	private String ecGcode;

	public ExpressBean(Long expresscompanyid, String fullname, String simplename, String code, Long sortno,
			Byte importance, String logo, Byte level, String hotline, Byte beenabled,Date modifyTime,String ecGcode) {
		this.expresscompanyid = expresscompanyid;
		this.fullname = fullname;
		this.simplename = simplename;
		this.code = code;
		this.sortno = sortno;
		this.importance = importance;
		this.logo = logo;
		this.level = level;
		this.hotline = hotline;
		this.beenabled = beenabled;
		this.modifyTime = modifyTime;
		this.ecGcode=ecGcode;
	}

	public ExpressBean() {
		super();
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

	public Byte getBeenabled() {
		return beenabled;
	}

	public void setBeenabled(Byte beenabled) {
		this.beenabled = beenabled;
	}

	public String getEcGcode() {
		return ecGcode;
	}

	public void setEcGcode(String ecGcode) {
		this.ecGcode = ecGcode;
	}
}
