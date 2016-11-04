package com.rofour.baseball.dao.order.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * @ClassName: PacketOrderBean
 * @Description: 小派订单详情bean
 * @author lijun
 * @date 2016年10月13日 下午2:56:13
 */
public class PacketOrderBean implements Serializable {
	

	private String userPhone;//收件人手机号
	private String userName;//收件人姓名

	private Long collegeId;//学校Id
	private String collegeName;//学校名称

	private String deliAddress;//送货地址

	private String sex;//性别

	private String fetchAddress;//取件地址

	private String cityName;//城市

	private String expressAddress;//快递站点

	private String orderId;//订单号

	private Long payMoney;//实际支付金额


	private String waybillNo;//运单号

	private String rebateType;//优惠方式
	/**
	 * 订单状态  1:创建2:已接单,3:配送中,4: 处理中 ,5:完成,6:取消,7:异常
	 */
	private String state;
	/**
	 * 支付方式 根据userAgent区分微信还是支付宝...
	 * 1：微信，2：支付宝
	 */
	private String payMethod;

	private Integer feeMoney;//打赏金额

	private String payType;//支付类型

	private Integer rebateMoney;//优惠金额

	private String distribionRemark;//配送要求

	private String payTime;

	private String signTime;

	private String pickupTime;

	private String grabTime;

	private String updateFeeTime;

	private String createTime;

	private String pickupCode;//取货码

	private String bookTime;//预约时间

	private Integer goodsAmount;//商品金额




	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getDeliAddress() {
		return deliAddress;
	}

	public void setDeliAddress(String deliAddress) {
		this.deliAddress = deliAddress;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFetchAddress() {
		return fetchAddress;
	}

	public void setFetchAddress(String fetchAddress) {
		this.fetchAddress = fetchAddress;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getExpressAddress() {
		return expressAddress;
	}

	public void setExpressAddress(String expressAddress) {
		this.expressAddress = expressAddress;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Long payMoney) {
		this.payMoney = payMoney;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getRebateType() {
		return rebateType;
	}

	public void setRebateType(String rebateType) {
		this.rebateType = rebateType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Integer getFeeMoney() {
		return feeMoney;
	}

	public void setFeeMoney(Integer feeMoney) {
		this.feeMoney = feeMoney;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public Integer getRebateMoney() {
		return rebateMoney;
	}

	public void setRebateMoney(Integer rebateMoney) {
		this.rebateMoney = rebateMoney;
	}

	public String getDistribionRemark() {
		return distribionRemark;
	}

	public void setDistribionRemark(String distribionRemark) {
		this.distribionRemark = distribionRemark;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getGrabTime() {
		return grabTime;
	}

	public void setGrabTime(String grabTime) {
		this.grabTime = grabTime;
	}

	public String getUpdateFeeTime() {
		return updateFeeTime;
	}

	public void setUpdateFeeTime(String updateFeeTime) {
		this.updateFeeTime = updateFeeTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPickupCode() {
		return pickupCode;
	}

	public void setPickupCode(String pickupCode) {
		this.pickupCode = pickupCode;
	}

	public String getBookTime() {
		return bookTime;
	}

	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}

}
