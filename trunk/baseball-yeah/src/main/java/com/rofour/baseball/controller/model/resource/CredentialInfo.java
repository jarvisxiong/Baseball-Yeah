/** 
 *  
 * Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.controller.model.resource;

import java.io.Serializable;

/**
 * @ClassName: CredentialInfo
 * @Description: 用户身份验证信息
 * @author WJ
 * @date 2016年5月05日 下午12:57:13
 *
 */

public class CredentialInfo implements Serializable {
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 3067183709995392437L;
	/**
	 * 证件类型 1.手持身份证照，2.身份证照片 3.其他
	 */
	private String type;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件字节 base编码过的
	 */
	private String file;
	/**
	 * sourceId
	 */
	private String sourceId;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 用户名
	 */
	private String userName;

	public CredentialInfo() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public CredentialInfo(String type, String fileName, String file, String sourceId) {
		super();
		this.type = type;
		this.fileName = fileName;
		this.file = file;
		this.sourceId = sourceId;
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

	public void setUserName(String username) {
		this.userName = username;
	}

	@Override
	public String toString() {
		return "CredentialInfo [type=" + type + ", fileName=" + fileName + ", file=" + file + ", sourceId=" + sourceId
				+ "]";
	}

}
