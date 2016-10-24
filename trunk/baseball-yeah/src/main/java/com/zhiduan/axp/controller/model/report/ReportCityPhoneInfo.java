/**  
* @Title: ReportCityPhoneInfo.java
* @Package com.zhiduan.axp.controller.model.report
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月7日 上午10:54:47 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.controller.model.report;

import java.io.Serializable;

/**
* @ClassName: ReportCityPhoneInfo
* @Description: 手机号码数据统计
* @author heyi
* @date 2016年6月7日 上午10:54:47 
*
*/

public class ReportCityPhoneInfo implements Serializable {

	private static final long serialVersionUID = -6554776670712110899L;
	/**
	 * 城市编号
	 */
    private Integer cityId;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 手机号总数
     */
    private Long totalPhoneNum;
    /**
     * 注册总数
     */
    private Long totalRegNum;
    /**
     * 新增手机号
     */
    private Long newNum;
    /**
     * 注册率
     */
    private Double regRate;
    /**
     * 手机增加率
     */
    private Double growRate;
    /**
     * 学校名称
     */
    private String colName;
    /**
     * 用户账户名
     */
    private String userName;
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Long getTotalPhoneNum() {
		return totalPhoneNum;
	}
	public void setTotalPhoneNum(Long totalPhoneNum) {
		this.totalPhoneNum = totalPhoneNum;
	}
	public Long getTotalRegNum() {
		return totalRegNum;
	}
	public void setTotalRegNum(Long totalRegNum) {
		this.totalRegNum = totalRegNum;
	}
	public Long getNewNum() {
		return newNum;
	}
	public void setNewNum(Long newNum) {
		this.newNum = newNum;
	}
	public Double getRegRate() {
		return regRate;
	}
	public void setRegRate(Double regRate) {
		this.regRate = regRate;
	}
	public Double getGrowRate() {
		return growRate;
	}
	public void setGrowRate(Double growRate) {
		this.growRate = growRate;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
