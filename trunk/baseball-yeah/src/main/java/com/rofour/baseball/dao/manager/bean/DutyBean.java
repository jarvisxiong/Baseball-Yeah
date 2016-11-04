/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;

/**
 * @ClassName: DutyBean
 * @Description: 管理中心 职务
 * @author 高振
 * @date 2016年3月27日 下午4:50:50
 *
 */

public class DutyBean implements Serializable {

	private static final long serialVersionUID = -4675266529072440648L;
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
	private String rankNo;
	/**
	 * 是否删除
	 */
	private Byte beDeleted;

	public DutyBean(Long dutyId, String dutyName, String rankNo, Byte beDeleted) {
		this.dutyId = dutyId;
		this.dutyName = dutyName;
		this.rankNo = rankNo;
		this.beDeleted = beDeleted;
	}

	public DutyBean() {
		super();
	}

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
		this.dutyName = dutyName == null ? null : dutyName.trim();
	}

	public String getRankNo() {
		return rankNo;
	}

	public void setRankNo(String rankNo) {
		this.rankNo = rankNo;
	}

	public Byte getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Byte beDeleted) {
		this.beDeleted = beDeleted;
	}

}
