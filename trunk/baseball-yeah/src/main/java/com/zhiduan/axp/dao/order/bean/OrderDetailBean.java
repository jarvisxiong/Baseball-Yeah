package com.zhiduan.axp.dao.order.bean;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: OrderDetailBean
 * @Description: 订单详细bean
 * @author ZhangLei
 * @date 2016年6月8日 下午3:56:24 
 */
public class OrderDetailBean implements Serializable {
	
	private static final long serialVersionUID = -6300540625225026888L;
	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 收件人手机号码
	 */
	private String mobile;
	/**
	 * 运单号
	 */
	private String waybillNo;
	/**
	 * 收件人
	 */
	private String consignee;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 状态
	 */
	private Integer state;
	/**
	 * 收件地址
	 */
	private String address;
	/**
	 * 取件地址
	 */
	private String location;
	/**
	 * 商户名称
	 */
	private String storeName;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 学校名称
	 */
	private String fullName;
	/**
	 * 状态名称
	 */
	private String stateStr;
	/**
	 * 总金额
	 */
	private Long totalMoney;
	/**
	 * 优惠后总金额
	 */
	private Long rebateMoney;
	/**
	 * 最终金额
	 */
	private Long finalMoney;
	/**
	 * 支付成功金额
	 */
	private Long payMoney;
	
	
	public Long getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Long getRebateMoney() {
		return rebateMoney;
	}
	public void setRebateMoney(Long rebateMoney) {
		this.rebateMoney = rebateMoney;
	}
	public Long getFinalMoney() {
		return finalMoney;
	}
	public void setFinalMoney(Long finalMoney) {
		this.finalMoney = finalMoney;
	}
	public Long getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Long payMoney) {
		this.payMoney = payMoney;
	}
	public String getStateStr() {
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getWaybillNo() {
		return waybillNo;
	}
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public OrderDetailBean(Long orderId, String mobile, String waybillNo,
			String consignee, String sex, Integer state, String address,
			String location, String storeName, String cityName,
			String fullName, String stateStr) {
		super();
		this.orderId = orderId;
		this.mobile = mobile;
		this.waybillNo = waybillNo;
		this.consignee = consignee;
		this.sex = sex;
		this.state = state;
		this.address = address;
		this.location = location;
		this.storeName = storeName;
		this.cityName = cityName;
		this.fullName = fullName;
		this.stateStr = stateStr;
	}
	public OrderDetailBean() {
		super();
	}
	
}
