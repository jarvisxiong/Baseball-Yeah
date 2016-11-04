/**  
* @Title: SearchPhoneCount.java
* @package com.rofour.baseball.idl.waybillcenter.dao
* @Project: axp-idl
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年4月15日 下午4:14:41 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.dao.report.bean;

import java.io.Serializable;

/**
* @ClassName: SearchPhoneCount
* @Description: TODO(学校运单上的手机号统计信息)
* @author heyi
* @date 2016年4月15日 下午4:14:41 
*
*/

public class SearchPhoneCountBean implements Serializable {
    
	private static final long serialVersionUID = -8533784374167881976L;
	/**
	 * 学校编号
	 */
    private  Long collegeId;
    /**
     * 学校名称
     */
    private String collegeName;
    /**
     * 手机号总数
     */
    private int phoneCount;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 学校总数
     */
    private int collegeCount;
    private Integer limit;
    private Integer offset;
    public SearchPhoneCountBean()
    {
    	
    }
	public Long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public Integer getPhoneCount() {
		return phoneCount;
	}
	public void setPhoneCount(Integer phoneCount) {
		this.phoneCount = phoneCount;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getCollegeCount() {
		return collegeCount;
	}
	public void setCollegeCount(Integer collegeCount) {
		this.collegeCount = collegeCount;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
}
