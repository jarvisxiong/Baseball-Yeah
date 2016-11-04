package com.rofour.baseball.dao.order.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Order
 * @Description: 订单
 * @author ZhangLei
 * @date 2016年6月8日 下午3:55:55 
 */
public class Order implements  Serializable{
    
	    
	private static final long serialVersionUID = 7941105231136814597L;


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
    private Integer payState;
    /**
	 * 支付方式 根据userAgent区分微信还是支付宝...
	 */
    private Integer payType;
    /**
	 * 支付流水号
	 */
    private Long payId;
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

    private String sex;

    /**
     * 众包模式,0:关闭 1:开启
     */
    private Integer packetMode;
    private Long collgeId;
    public Long getCollgeId() {
		return collgeId;
	}

	public void setCollgeId(Long collgeId) {
		this.collgeId = collgeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	private String collegeName;
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
        this.source = source == null ? null : source.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
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
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUnnormalDesc() {
        return unnormalDesc;
    }

    public void setUnnormalDesc(String unnormalDesc) {
        this.unnormalDesc = unnormalDesc == null ? null : unnormalDesc.trim();
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
        this.sex = sex == null ? null : sex.trim();
    }

	public Integer getPacketMode() {
		return packetMode;
	}

	public void setPacketMode(Integer packetMode) {
		this.packetMode = packetMode;
	}
}