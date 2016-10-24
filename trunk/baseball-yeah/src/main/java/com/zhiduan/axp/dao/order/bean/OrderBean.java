package com.zhiduan.axp.dao.order.bean;

import java.io.*;
import java.util.Date;

/**
 * @ClassName: OrderBean
 * @Description: 订单bean
 * @author ZhangLei
 * @date 2016年6月8日 下午3:56:13 
 */
public class OrderBean implements Serializable {
	
	private static final long serialVersionUID = -6300540625225026888L;
	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 订单来源
	 */
	private String source;
	/**
	 * 渠道
	 */
	private String channel;
	/**
	 * 订单类型
	 */
	private String type;
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
	 * 订单状态  1:创建2:已接单,3:配送中,4: 处理中 ,5:完成,6:取消,7:异常
	 */
	private Integer state;
	/**
	 * 支付成功金额
	 */
	private Long payMoney;
	/**
	 * 支付状态:1 未支付 2 :部分支付 3:已支付
	 */
	private String payState;
	/**
	 * 支付方式 根据userAgent区分微信还是支付宝...
	 * 1：微信，2：支付宝
	 */
	private Integer payType;
	/**
	 * 支付流水号
	 */
	private String payId;
	/**
	 * 取消类型: 1 接单人取消,2 下单人取消,3 系统取消
	 */
	private Integer cancelType;
	/**
	 * 收件人
	 */
	private String consignee;
	/**
	 * 收货地址
	 */
	private String address;
	/**
	 * 送达开始日期
	 */
	private Date deliveryStartDate;
	/**
	 * 送达结束日期
	 */
	private Date deliveryEndDate;
	/**
	 * 创建人
	 */
	private Long createUser;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 异常描述
	 */
	private String unnormalDesc;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 修改人
	 */
	private Long updateUser;
	/**
	 * 修改日期
	 */
	private Date updateDate;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 运单号
	 */
	private String waybillNo;
	/**
	 * 学校Id
	 */
	private Long collegeId;

	/**
	 * 运单id
	 */
	private Long waybillId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Long payMoney) {
		this.payMoney = payMoney;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public Integer getCancelType() {
		return cancelType;
	}

	public void setCancelType(Integer cancelType) {
		this.cancelType = cancelType;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDeliveryStartDate() {
		return deliveryStartDate;
	}

	public void setDeliveryStartDate(Date deliveryStartDate) {
		this.deliveryStartDate = deliveryStartDate;
	}

	public Date getDeliveryEndDate() {
		return deliveryEndDate;
	}

	public void setDeliveryEndDate(Date deliveryEndDate) {
		this.deliveryEndDate = deliveryEndDate;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUnnormalDesc() {
		return unnormalDesc;
	}

	public void setUnnormalDesc(String unnormalDesc) {
		this.unnormalDesc = unnormalDesc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public Long getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(Long waybillId) {
		this.waybillId = waybillId;
	}

	@Override
	public String toString() {
		return "OrderBean{" +
				"orderId=" + orderId +
				", source='" + source + '\'' +
				", channel='" + channel + '\'' +
				", type='" + type + '\'' +
				", totalMoney=" + totalMoney +
				", rebateMoney=" + rebateMoney +
				", finalMoney=" + finalMoney +
				", state=" + state +
				", payMoney=" + payMoney +
				", payState='" + payState + '\'' +
				", payType=" + payType +
				", payId='" + payId + '\'' +
				", cancelType=" + cancelType +
				", consignee='" + consignee + '\'' +
				", address='" + address + '\'' +
				", deliveryStartDate=" + deliveryStartDate +
				", deliveryEndDate=" + deliveryEndDate +
				", createUser=" + createUser +
				", mobile='" + mobile + '\'' +
				", remark='" + remark + '\'' +
				", unnormalDesc='" + unnormalDesc + '\'' +
				", createDate=" + createDate +
				", updateUser=" + updateUser +
				", updateDate=" + updateDate +
				", sex='" + sex + '\'' +
				", waybillNo='" + waybillNo + '\'' +
				", collegeId=" + collegeId +
				", waybillId=" + waybillId +
				'}';
	}
}
