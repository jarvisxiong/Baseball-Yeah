/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;

/**
 * @ClassName: QuotaBean
 * @Description: 指标实体
 * @author xl
 * @date 2016年7月18日 下午3:22:10
 */
public class QuotaBean implements Serializable {

	private static final long serialVersionUID = -4531402587034965841L;
	/**
	 * 主键
	 */
	private int quotaId;
	/**
	 * 指标名称
	 */
	private String quotaName;
	/**
	 * 对应字段
	 */
	private String fieldName;
	/**
	 * 状态 0-废弃 1-在用
	 */
	private Integer state;

	private int oldQuotaId;

	public QuotaBean() {
	}

	/**
	 * @Constructor: QuotaBean
	 * @param quotaId
	 * @param quotaName
	 * @param fieldName
	 * @param state
	 */

	public QuotaBean(int quotaId, String quotaName, String fieldName, Integer state) {
		super();
		this.quotaId = quotaId;
		this.quotaName = quotaName;
		this.fieldName = fieldName;
		this.state = state;
	}

	public int getOldQuotaId() {
		return oldQuotaId;
	}

	public void setOldQuotaId(int oldQuotaId) {
		this.oldQuotaId = oldQuotaId;
	}

	public int getQuotaId() {
		return quotaId;
	}

	public void setQuotaId(int quotaId) {
		this.quotaId = quotaId;
	}

	public String getQuotaName() {
		return quotaName;
	}

	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
