/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.controller.model.manager;

import java.io.Serializable;

/**
 * @ClassName: TbSysParameterInfo
 * @Description: 系统参数DTO类,用作接口返回实体
 * @author xl
 * @date 2016年3月26日 下午1:44:03
 *
 */

public class SysParameterInfo implements Serializable {

	private static final long serialVersionUID = -8312019143737569209L;

	/**
	 * 编码
	 */
	private Long sysParameterId;
	/**
	 * 参数名
	 */
	private String parameterName;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 描述信息
	 */
	private String description;
	/**
	 * 是否启用
	 */
	private Byte beEnabled;

	public SysParameterInfo(Long sysParameterId, String parameterName, String value, String description,
			Byte beEnabled) {
		this.sysParameterId = sysParameterId;
		this.parameterName = parameterName;
		this.value = value;
		this.description = description;
		this.beEnabled = beEnabled;
	}

	public SysParameterInfo() {
		super();
	}

	public Long getSysParameterId() {
		return sysParameterId;
	}

	public void setSysParameterId(Long sysParameterId) {
		this.sysParameterId = sysParameterId;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName == null ? null : parameterName.trim();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value == null ? null : value.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Byte getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Byte beEnabled) {
		this.beEnabled = beEnabled;
	}
}