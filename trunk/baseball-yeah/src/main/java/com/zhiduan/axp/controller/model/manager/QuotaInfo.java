/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.model.manager;

import java.io.Serializable;

/**
 * @ClassName: QuotaInfo
 * @Description: 指标传输实体
 * @author xl
 * @date 2016年7月18日 下午3:40:34
 */
public class QuotaInfo implements Serializable {

	private static final long serialVersionUID = -8747123116840994336L;
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

	public QuotaInfo() {

	}

	/**
	 * @Constructor: QuotaInfo
	 * @param quotaId
	 * @param quotaName
	 * @param fieldName
	 * @param state
	 */

	public QuotaInfo(int quotaId, String quotaName, String fieldName, Integer state) {
		super();
		this.quotaId = quotaId;
		this.quotaName = quotaName;
		this.fieldName = fieldName;
		this.state = state;
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
