/**  
* @Title: ServerInfo.java
* @package com.rofour.baseball.idl.resourcecenter.service
* @Project: axp-idl
* @Description: 附件服务器信息
* @author WJ
* @date 2016年3月29日 下午12:57:13 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.controller.model.resource;

import java.io.Serializable;

/**
 * @ClassName: ServerInfo
 * @Description: 附件服务器信息
 * @author WJ
 * @date 2016年3月29日 下午12:57:13
 *
 */

public class ServerInfo implements Serializable{
	/**
	 * @Fields serialVersionUID
	 */
	    
	private static final long serialVersionUID = 5455384917303854252L;
	/**
	 * 附件服务器id 
	 */
	private String attachmentServerId;
	/**
	 * url
	 */
	private String endpoint;
	/**
	 * ack id
	 */
	private String accessKeyId;
	/**
	 * ack 秘钥
	 */
	private String accessKeySecret;
	/**
	 * 目录名
	 */
	private String bucketName;

	/**
	 * @Constructor: ServerInfo
	 *
	 **/
	public ServerInfo() {
	}

	public ServerInfo(String attachmentServerId,String endpoint, String accessKeyId, String accessKeySecret, String bucketName) {
		super();
		this.attachmentServerId = attachmentServerId;
		this.endpoint = endpoint;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.bucketName = bucketName;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	

	public String getAttachmentServerId() {
		return attachmentServerId;
	}

	public void setAttachmentServerId(String attachmentServerId) {
		this.attachmentServerId = attachmentServerId;
	}

	@Override
	public String toString() {
		return "ServerInfo [endpoint=" + endpoint + ", accessKeyId=" + accessKeyId + ", accessKeySecret="
				+ accessKeySecret + ", bucketName=" + bucketName + "]";
	}

}
