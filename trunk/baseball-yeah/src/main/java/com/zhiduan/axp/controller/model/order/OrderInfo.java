package com.zhiduan.axp.controller.model.order;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: OrderInfo
 * @Description: 运单
 * @author ZhangLei
 * @date 2016年6月7日 上午11:07:41 
 */
public class OrderInfo implements Serializable{
	
	private static final long serialVersionUID = -3431743372155513497L;
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

	private String token;
	
	/**
	 * 学校ID
	 */
	private Long collegeId;
	
	/**
	 *运单ID
	 */
	private Long waybillId;
	/**
	 *运单号
	 */
	private String waybillNo;
	/**
	 *中标人
	 */
	private Long winner;
	
	/**
	 *众包人手机号码
	 */
	private String phone;
	
	/**
	 *学校名称
	 */
	private String fullName;

	/**
	 * 代金券id
	 */
	private String commodityCode;

	/**
	 * 代金券金额
	 */
	private Long rebatePrice;

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public Long getRebatePrice() {
		return rebatePrice;
	}

	public void setRebatePrice(Long rebatePrice) {
		this.rebatePrice = rebatePrice;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getOrderId() {
		return orderId;
	}

	/**
	  * 身份证号
	  **/
	private String idNo;
	/**
	  * 真实姓名
	  **/
	private String realName;
	/**
   * 用户ID
   **/
   private Long userId;
   /**
   * 学校地址
   **/
   private String collegeAddress;
   /**
   * 宿舍地址
   **/
   private String dormitoryAddress;
   /**
   * 审核信息
   **/
   private String verifyMsg;
   
   /**
   * 抢单模式, 0:开启 1:关闭
   **/
   private Integer grabOrderMode;
   
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public Long getWinner() {
		return winner;
	}

	public void setWinner(Long winner) {
		this.winner = winner;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCollegeAddress() {
		return collegeAddress;
	}

	public void setCollegeAddress(String collegeAddress) {
		this.collegeAddress = collegeAddress;
	}

	public String getDormitoryAddress() {
		return dormitoryAddress;
	}

	public void setDormitoryAddress(String dormitoryAddress) {
		this.dormitoryAddress = dormitoryAddress;
	}

	public String getVerifyMsg() {
		return verifyMsg;
	}

	public void setVerifyMsg(String verifyMsg) {
		this.verifyMsg = verifyMsg;
	}

	public Integer getGrabOrderMode() {
		return grabOrderMode;
	}

	public void setGrabOrderMode(Integer grabOrderMode) {
		this.grabOrderMode = grabOrderMode;
	}
	
	
}
