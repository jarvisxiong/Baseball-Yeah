/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.controller.model.manager;

import java.io.Serializable;

/**
 * @ClassName: DutyInfo
 * @Description: 管理中心 职务
 * @author 高振
 * @date 2016年3月27日 下午4:52:52
 *
 */
public class DutyInfo implements Serializable {
	    
	private static final long serialVersionUID = 2615488053231958057L;
	/**
	 * 编码
	 */
	private Long dutyId;
	/**
	 * 名称
	 */
	private String dutyName;
	/**
	 * 级别
	 */
	private Byte rankNo;
	/**
	 * 是否删除
	 */
	private Byte beDeleted;

	public Long getDutyId() {
		return dutyId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public Byte getRankNo() {
		return rankNo;
	}

	public void setRankNo(Byte rankNo) {
		this.rankNo = rankNo;
	}

	public Byte getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Byte beDeleted) {
		this.beDeleted = beDeleted;
	}

	@Override
	public String toString() {
		return "DutyInfo [dutyId=" + dutyId + ", dutyName=" + dutyName + ", rankNo=" + rankNo + ", beDeleted="
				+ beDeleted + "]";
	}

}
