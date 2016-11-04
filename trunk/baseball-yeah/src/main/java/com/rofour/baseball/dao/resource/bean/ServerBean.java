/**  
* @Title: ServerBean.java
* @package com.rofour.baseball.idl.resourcecenter.dao
* @Project: axp-idl
* @Description: 服务器信息实体类
* @author WJ
* @date 2016年3月29日 上午11:45:10 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.dao.resource.bean;

import java.io.Serializable;

import com.rofour.baseball.dao.resource.mapper.ResourceMapper;

/**
 * @ClassName: ServerBean
 * @Description: 服务器信息实体类
 * @author WJ
 * @date 2016年3月29日 上午11:45:10
 *
 */

public class ServerBean implements Serializable{
	/**
	 * @Fields serialVersionUID
	 */
	    
	private static final long serialVersionUID = 7771651885781247293L;
	/**
	 * 服务器id
	 */
	private String serverId;
	/**
	 * 服务器名
	 */
	private String serverName;
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
	 * access url
	 */
	private String accessUrl;
	/**
	 * 默认启用
	 */
	private Short beEnabled = ResourceMapper.RESOURCE_SERVER_ENABLED;

	/**
	 * @Constructor: ServerBean
	 *
	 **/
	public ServerBean() {
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
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

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public Short getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Short beEnabled) {
		this.beEnabled = beEnabled;
	}

	@Override
	public String toString() {
		return "ServerBean [serverId=" + serverId + ", serverName=" + serverName + ", endpoint=" + endpoint
				+ ", accessKeyId=" + accessKeyId + ", accessKeySecret=" + accessKeySecret
				+ ", accessUrl=" + accessUrl + ", beEnabled=" + beEnabled + "]";
	}

	public ServerBean(String serverId, String serverName, String endpoint, String accessKeyId, String accessKeySecret,
			String accessUrl, Short beEnabled) {
		super();
		this.serverId = serverId;
		this.serverName = serverName;
		this.endpoint = endpoint;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.accessUrl = accessUrl;
		this.beEnabled = beEnabled;
	}

}
