/**  
* @Title: HistoryVersionInfo.java
* @Package com.zhiduan.axp.controller.model
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月30日 下午3:10:47 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.controller.model;

import java.io.Serializable;

/**
* @ClassName: HistoryVersionInfo
* @Description: 历史版本号实体
* @author heyi
* @date 2016年6月30日 下午3:10:47 
*
*/

public class HistoryVersionInfo implements Serializable {
 
	private static final long serialVersionUID = 6747080397125820556L;
    
	/**
	 * 编号
	 */
	private Long versionId;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 序号
	 */
	private Byte sortNo;
	/**
	 * 创建时间
	 */
	private String createTime;
	
	private String startTime;
	private String endTime;
	/**
	 * @return versionId
	 */
	
	public Long getVersionId() {
		return versionId;
	}
	/**
	 * @param versionId the versionId to set
	 */
	
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}
	/**
	 * @return title
	 */
	
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return content
	 */
	
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return sortNo
	 */
	
	public Byte getSortNo() {
		return sortNo;
	}
	/**
	 * @param sortNo the sortNo to set
	 */
	
	public void setSortNo(Byte sortNo) {
		this.sortNo = sortNo;
	}
	/**
	 * @return createTime
	 */
	
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
