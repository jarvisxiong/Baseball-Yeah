/**  
* @Title: SignInfo.java
* @Package com.zhiduan.axp.idl.resourcecenter.service
* @Project: axp-idl
* @Description: 快递签收上传信息
* @author WJ
* @date 2016年3月29日 下午1:30:42 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.zhiduan.axp.controller.model.resource;

import java.io.Serializable;

/**
 * @ClassName: SignInfo
 * @Description: 快递签收上传信息
 * @author WJ
 * @date 2016年3月29日 下午1:30:42
 *
 */

public class SignInfo implements Serializable {
	/**
	 * @Fields serialVersionUID
	 */

	private static final long serialVersionUID = -4039918373480394414L;
	/**
	 * 附件类型
	 */
	private String attachmentTypeId;
	/**
	 * 来源id
	 */
	private String sourceId;
	/**
	 * 上传的文件名
	 */
	private String fileName;
	/**
	 * 上传的文件base64编码后的字符串,如果文件不是base64编码的,忽略为null
	 */
	private String file;
	/**
	 * 存在bucket中的key,必须唯一,否则会覆盖之前的文件
	 */
	private String fileKey;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 用户名
	 */
	private String userName;
	
	public SignInfo() {
	}

	public String getAttachmentTypeId() {
		return attachmentTypeId;
	}

	public void setAttachmentTypeId(String attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId;
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

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public SignInfo(String attachmentTypeId, String sourceId, String fileName, String file, String fileKey,
			Long userId, String userName) {
		super();
		this.attachmentTypeId = attachmentTypeId;
		this.sourceId = sourceId;
		this.fileName = fileName;
		this.file = file;
		this.fileKey = fileKey;
		this.userId = userId;
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "SignInfo [attachmentTypeId=" + attachmentTypeId + ", sourceId=" + sourceId + ", fileName=" + fileName
				+ ", file=" + file + ", fileKey=" + fileKey + ", userId=" + userId + ", userName=" + userName + "]";
	}


}
