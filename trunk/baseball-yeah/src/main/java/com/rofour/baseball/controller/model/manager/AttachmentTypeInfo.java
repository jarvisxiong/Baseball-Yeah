/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;

/**
 * @ClassName: AttachmentTypeInfo
 * @Description: 附件类型表对外实体类
 * @author 史丹丹
 * @date 2016年4月2日 下午5:08:05
 *
 */

public class AttachmentTypeInfo implements Serializable {

	private static final long serialVersionUID = 7207866883535143312L;

	/**
	 * 附件类型编号（主键）
	 */
	private String attachmentTypeId;

	/**
	 * 附件类型名称
	 */
	private String attachmentTypeName;

	/**
	 * 上传文件类型
	 */
	private String uploadFileType;

	/**
	 * 允许上传文件数量
	 */
	private Integer allowUploadCount;

	/**
	 * 上传文件大小
	 */
	private Integer uploadFileSize;

	/**
	 * OSS的节点名
	 */
	private String bucketName;

	public AttachmentTypeInfo(String attachmentTypeId, String attachmentTypeName, String uploadFileType,
			Integer allowUploadCount, Integer uploadFileSize, String bucketName) {
		this.attachmentTypeId = attachmentTypeId;
		this.attachmentTypeName = attachmentTypeName;
		this.uploadFileType = uploadFileType;
		this.allowUploadCount = allowUploadCount;
		this.uploadFileSize = uploadFileSize;
		this.bucketName = bucketName;
	}

	public AttachmentTypeInfo() {
		super();
	}

	public String getAttachmentTypeId() {
		return attachmentTypeId;
	}

	public void setAttachmentTypeId(String attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId == null ? null : attachmentTypeId.trim();
	}

	public String getAttachmentTypeName() {
		return attachmentTypeName;
	}

	public void setAttachmentTypeName(String attachmentTypeName) {
		this.attachmentTypeName = attachmentTypeName == null ? null : attachmentTypeName.trim();
	}

	public String getUploadFileType() {
		return uploadFileType;
	}

	public void setUploadFileType(String uploadFileType) {
		this.uploadFileType = uploadFileType == null ? null : uploadFileType.trim();
	}

	public Integer getAllowUploadCount() {
		return allowUploadCount;
	}

	public void setAllowUploadCount(Integer allowUploadCount) {
		this.allowUploadCount = allowUploadCount;
	}

	public Integer getUploadFileSize() {
		return uploadFileSize;
	}

	public void setUploadFileSize(Integer uploadFileSize) {
		this.uploadFileSize = uploadFileSize;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName == null ? null : bucketName.trim();
	}
}