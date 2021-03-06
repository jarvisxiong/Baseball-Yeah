/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;

/**
 * @ClassName: atmServerInfo
 * @Description: 附件服务器外部实体类
 * @author xzy
 * @date 2016年4月2日 下午5:06:40
 *
 */
public class AtmServerInfo implements Serializable {

	private static final long serialVersionUID = 2932235259090934846L;
	/**
	 * 编码
	 */
	private String attachmentServerId;
	/**
	 * 名称
	 */
	private String attachmentServerName;
	/**
	 * endpoint
	 */
	private String endpoint;
	/**
	 * accessKeyId
	 */
	private String accessKeyId;
	/**
	 * accessKeySecret
	 */
	private String accessKeySecret;
	/**
	 * accessUrl
	 */
	private String accessUrl;
	/**
	 * 是否启用
	 */
	private Byte beEnabled;

	public AtmServerInfo(String attachmentServerId, String attachmentServerName, String endpoint, String accessKeyId,
			String accessKeySecret, String accessUrl, Byte beEnabled) {
		this.attachmentServerId = attachmentServerId;
		this.attachmentServerName = attachmentServerName;
		this.endpoint = endpoint;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.accessUrl = accessUrl;
		this.beEnabled = beEnabled;
	}

	public AtmServerInfo() {
		super();
	}

	public String getAttachmentServerId() {
		return attachmentServerId;
	}

	public void setAttachmentServerId(String attachmentServerId) {
		this.attachmentServerId = attachmentServerId == null ? null : attachmentServerId.trim();
	}

	public String getAttachmentServerName() {
		return attachmentServerName;
	}

	public void setAttachmentServerName(String attachmentServerName) {
		this.attachmentServerName = attachmentServerName == null ? null : attachmentServerName.trim();
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint == null ? null : endpoint.trim();
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId == null ? null : accessKeyId.trim();
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret == null ? null : accessKeySecret.trim();
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl == null ? null : accessUrl.trim();
	}

	public Byte getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Byte beEnabled) {
		this.beEnabled = beEnabled;
	}

}
