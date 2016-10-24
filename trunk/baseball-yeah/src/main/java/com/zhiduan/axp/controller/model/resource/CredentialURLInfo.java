/** 
 *  
 * Copyright (c) 2016, 指端科技.
*/

package com.zhiduan.axp.controller.model.resource;

import java.io.Serializable;

/**
 * @ClassName: CredentialURLInfo
 * @Description: 用户身份验证信息图片url封装类
 * @author WJ
 * @date 2016年5月05日 下午12:57:13
 *
 */

public class CredentialURLInfo implements Serializable {
	private static final long serialVersionUID = 5035447427501903647L;
	/**
	 * 证件类型 1.手持身份证照，2.身份证照片 3.其他
	 */
	private int type;
	/**
	 * 文件URL
	 */
	private String url;

	public CredentialURLInfo() {
	}

	public CredentialURLInfo(int type, String url) {
		super();
		this.type = type;
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "CredentialURLInfo [type=" + type + ", url=" + url + "]";
	}

}
