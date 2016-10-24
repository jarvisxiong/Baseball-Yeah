/**  
* @Title: AttachmentBean.java
* @Package com.zhiduan.axp.idl.resourcecenter.dao
* @Project: axp-idl
* @Description:  附件信息实体类
* @author WJ
* @date 2016年3月31日 下午12:48:44 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.zhiduan.axp.dao.resource.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: AttachmentBean
 * @Description: 附件信息实体类
 * @author WJ
 * @date 2016年3月31日 下午12:48:44
 *
 */

public class AttachmentBean implements Serializable{
	/**
	 * @Fields serialVersionUID 
	 */
	    
	private static final long serialVersionUID = -7749491153262645781L;
	/**
	 * 附件信息id
	 */
	private Long attachmentId;
	/**
	 * 来源id
	 */
	private String sourceId;
	/**
	 * 附件服务器id
	 */
	private String attachmentServerId;
	/**
	 * 附件类型id
	 */
	private String attachmentTypeId;
	/**
	 * 文件URL
	 */
	private String fileUrl;
	/**
	 * 文件名字
	 */
	private String file;
	/**
	 * 扩展名
	 */
	private String extension;
	/**
	 * 文件大小
	 */
	private Long size;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 上传时间
	 */
	private Date uploadTime;

	/**
	 * @Constructor: AttachmentBean
	 *
	 **/
	public AttachmentBean() {
	}

	public AttachmentBean(String sourceId, String attachmentServerId,
			String attachmentTypeId, String fileUrl, String file, String extension, Long size, Long userId,
			String userName, Date uploadTime) {
		super();
		this.sourceId = sourceId;
		this.attachmentServerId = attachmentServerId;
		this.attachmentTypeId = attachmentTypeId;
		this.fileUrl = fileUrl;
		this.file = file;
		this.extension = extension;
		this.size = size;
		this.userId = userId;
		this.userName = userName;
		this.uploadTime = uploadTime;
	}

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getAttachmentServerId() {
		return attachmentServerId;
	}

	public void setAttachmentServerId(String attachmentServerId) {
		this.attachmentServerId = attachmentServerId;
	}

	public String getAttachmentTypeId() {
		return attachmentTypeId;
	}

	public void setAttachmentTypeId(String attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() {
		return "AttachmentBean [attachmentId=" + attachmentId + ", sourceId="
				+ sourceId + ", attachmentServerId=" + attachmentServerId + ", attachmentTypeId=" + attachmentTypeId
				+ ", fileUrl=" + fileUrl + ", file=" + file + ", extension=" + extension + ", size=" + size
				+ ", userId=" + userId + ", userName=" + userName + ", uploadTime=" + uploadTime + "]";
	}

}
