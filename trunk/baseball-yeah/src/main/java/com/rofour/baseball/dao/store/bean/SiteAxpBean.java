package com.rofour.baseball.dao.store.bean;

import java.io.Serializable;

import com.rofour.baseball.dao.store.mapper.StoreMapper;

/**
 * 快递站点_爱学派门店实体类
 * 
 * @author WJ
 *
 */
public class SiteAxpBean implements Serializable{
	/**
	 * @Fields serialVersionUID 
	 */
	    
	private static final long serialVersionUID = -4583679851433916008L;
	/**
	 * id
	 */
	private long earId;
	/**
	 * site_id(对应store_id)
	 */
	private long stoExpId;
	/**
	 * axp_id爱学派门店id(对应store_id)
	 */
	private long stoAxpId;
	/**
	 * 是否删除
	 */
	private int beDeleted = StoreMapper.STOREMAPPER_DELETE_STATUS_EXIST;

	public SiteAxpBean() {
	}

	public SiteAxpBean(long earId, long stoExpId, long stoAxpId, int beDeleted) {
		this.earId = earId;
		this.stoExpId = stoExpId;
		this.stoAxpId = stoAxpId;
		this.beDeleted = beDeleted;
	}

	public long getEarId() {
		return earId;
	}

	public void setEarId(long earId) {
		this.earId = earId;
	}

	public long getStoExpId() {
		return stoExpId;
	}

	public void setStoExpId(long stoExpId) {
		this.stoExpId = stoExpId;
	}

	public long getStoAxpId() {
		return stoAxpId;
	}

	public void setStoAxpId(long stoAxpId) {
		this.stoAxpId = stoAxpId;
	}

	public int getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(int beDeleted) {
		this.beDeleted = beDeleted;
	}

	@Override
	public String toString() {
		return "SiteAxpBean{" +
				"earId=" + earId +
				", stoExpId=" + stoExpId +
				", stoAxpId=" + stoAxpId +
				", beDeleted=" + beDeleted +
				'}';
	}
}
