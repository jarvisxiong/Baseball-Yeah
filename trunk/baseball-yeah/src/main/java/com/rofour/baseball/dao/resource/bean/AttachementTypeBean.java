/**  
* @Title: AttachementTypeBean.java
* @package com.rofour.baseball.idl.resourcecenter.dao
* @Project: axp-idl
* @Description: 附件类型实体类
* @author WJ
* @date 2016年4月4日 上午11:02:54 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.dao.resource.bean;

import java.io.Serializable;

/**
 * @ClassName: AttachementTypeBean
 * @Description: 附件类型实体类
 * @author WJ
 * @date 2016年4月4日 上午11:02:54
 *
 */

public class AttachementTypeBean implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -5777416033571197450L;
	/**
	 * 附件类型id
	 */
	private String attachmentTypeId;
	/**
	 * 附件类型名
	 */
	private String attachmentTypeName;
	/**
	 * 可上传的文件类型
	 */
	private String uploadFileType;
	/**
	 * 允许上传的数量
	 */
	private Integer allowUploadCount;
	/**
	 * 大小上限
	 */
	private Integer uploadFileSize;
	/**
	 * 文件存放在oss的目录名
	 */
	private String bucketName;

	public AttachementTypeBean() {
	}

	public AttachementTypeBean(String attachmentTypeId, String attachmentTypeName, String uploadFileType,
			Integer allowUploadCount, Integer uploadFileSize, String bucketName) {
		super();
		this.attachmentTypeId = attachmentTypeId;
		this.attachmentTypeName = attachmentTypeName;
		this.uploadFileType = uploadFileType;
		this.allowUploadCount = allowUploadCount;
		this.uploadFileSize = uploadFileSize;
		this.bucketName = bucketName;
	}

	public String getAttachmentTypeId() {
		return attachmentTypeId;
	}

	public void setAttachmentTypeId(String attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId;
	}

	public String getAttachmentTypeName() {
		return attachmentTypeName;
	}

	public void setAttachmentTypeName(String attachmentTypeName) {
		this.attachmentTypeName = attachmentTypeName;
	}

	public String getUploadFileType() {
		return uploadFileType;
	}

	public void setUploadFileType(String uploadFileType) {
		this.uploadFileType = uploadFileType;
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
		this.bucketName = bucketName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AttachementTypeBean [attachmentTypeId=" + attachmentTypeId + ", attachmentTypeName="
				+ attachmentTypeName + ", uploadFileType=" + uploadFileType + ", allowUploadCount=" + allowUploadCount
				+ ", uploadFileSize=" + uploadFileSize + ", bucketName=" + bucketName + "]";
	}

}
