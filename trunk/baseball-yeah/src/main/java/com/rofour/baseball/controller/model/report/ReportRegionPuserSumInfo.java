package com.rofour.baseball.controller.model.report;
/**
 * 区域下的小派数量,按天,小派数量汇总
 * @author zhoujie
 *
 */
public class ReportRegionPuserSumInfo {
	private  Long id ;//主键id
	
	private  String day;//日期
	
	private Integer regionId;//区域id
	
	private String regionName;//区域名称
	
	private Integer puserNum;//小派总数
	
	private Integer activePuserNum;//活跃小派数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getPuserNum() {
		return puserNum;
	}

	public void setPuserNum(Integer puserNum) {
		this.puserNum = puserNum;
	}

	public Integer getActivePuserNum() {
		return activePuserNum;
	}

	public void setActivePuserNum(Integer activePuserNum) {
		this.activePuserNum = activePuserNum;
	}
	
	
}
