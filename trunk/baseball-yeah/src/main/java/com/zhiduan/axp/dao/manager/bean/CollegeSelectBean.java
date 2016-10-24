/**  
* @Title: CollegeSelectBean.java
* @Package com.zhiduan.axp.dao.manager.bean
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年5月13日 下午1:55:25 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;

/**
* @ClassName: CollegeSelectBean
* @Description: TODO(这里用一句话描述这个类的作用)
* @author heyi
* @date 2016年5月13日 下午1:55:25 
*
*/

public class CollegeSelectBean implements Serializable {
	
	private static final long serialVersionUID = 8933401891637838172L;
	/**
	 * 编码
	 */
	private Long collegeId;
	/**
	 * 简称
	 */
	

	private String simpleName;
	private String fullName;
	public CollegeSelectBean()
	{
		super();
	}
	public CollegeSelectBean(Long collegeId,String simpleName,String fullName)
	{
		this.collegeId=collegeId;
		this.simpleName=simpleName;
		this.fullName=fullName;
	}
	public Long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}
	public String getSimpleName() {
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
