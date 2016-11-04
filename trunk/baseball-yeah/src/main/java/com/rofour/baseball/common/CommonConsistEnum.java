package com.rofour.baseball.common;

/**
 * @author zdh
 * @description 公共下拉列表常量枚举类
 */
public enum CommonConsistEnum {

	COMMON_EMPTY(" ","请选择"),
	// 订单类型
	TYPE_PACKET("packet", "派件"), 
	TYPE_AGENT("agent_packet", "代取件"), 
	TYPE_SEND("send","寄件"), 
	TYPE_AGENTSYSTEM("agent_system", "代办系统任务"), 
	TYPE_ERRANDS("errands", "跑腿"),
	
	ROLE_STORE("store", "商户"),
	ROLE_COO("coo", "校园COO"),
	ROLE_CEO("ceo", "校园CEO"),
	
	STATE_ENALBE("0","禁用"),
	STATE_DISALBE("1","启用"),
	

	STATE_SERVICE_COST("1012","导入费"),
	STATE_MANAGE_COST("1013","服务费")
	
	;

	private String code;

	private String desc;

	// 根据code获取desc
	public static CommonConsistEnum getOrderDesc(String code) {
		for (CommonConsistEnum c : CommonConsistEnum.values()) {
			if (c.getCode().equals(code)) {
				return c;
			}
		}
		return null;
	}

	CommonConsistEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	CommonConsistEnum(String code, String desc, String codeStr) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return "OrderEnum{" + "code='" + code + '\'' + ", desc='" + desc + '\'' + '}';
	}
}
