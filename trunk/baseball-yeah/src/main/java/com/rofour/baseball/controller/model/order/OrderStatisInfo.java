package com.rofour.baseball.controller.model.order;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: OrderStatisInfo
 * @Description:
 * @author ZXY
 * @date 2016/10/13 11:01
 */
public class OrderStatisInfo implements Serializable{

    private static final long serialVersionUID = 636390721643849153L;
    /**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 订单类型
	 */
	private String orderType;
	/**
	 * 总金额
	 */
	private Long totalMoney;
    /**
     * 打赏金额
     */
    private Long rewardMoney;
	/**
	 * 优惠后总金额
	 */
	private Long rebateMoney;
    /**
     * 优惠金额
     */
    private Long discountMoney;
	/**
	 * 最终金额
	 */
	private Long finalMoney;
    /**
     * 支付类型
     */
    private Integer payMode;
	/**
	 * 支付成功金额
	 */
	private Long payMoney;
	/**
	 * 支付方式 根据userAgent区分微信还是支付宝...
	 */
	private Integer payType;
    /**
     * 备注
     */
    private String remark;
	/**
	 * 创建人
	 */
	private Long createUser;
    /**
     * 创建人手机号
     */
    private String createUserMobile;
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
	 * 学校ID
	 */
	private Long collegeId;
    /**
     * 学校名称 fullName
     */
    private String collegeName;
	
	/**
	 *中标人 winner
	 */
	private Long packetUserId;
	
	/**
	 * 众包人手机号码 phone
	 */
	private String packetPhone;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(Long rewardMoney) {
        this.rewardMoney = rewardMoney;
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

    public Integer getPayMode() {
        return payMode;
    }

    public void setPayMode(Integer payMode) {
        this.payMode = payMode;
    }

    public Long getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Long payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserMobile() {
        return createUserMobile;
    }

    public void setCreateUserMobile(String createUserMobile) {
        this.createUserMobile = createUserMobile;
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

    public Long getPacketUserId() {
        return packetUserId;
    }

    public void setPacketUserId(Long packetUserId) {
        this.packetUserId = packetUserId;
    }

    public String getPacketPhone() {
        return packetPhone;
    }

    public void setPacketPhone(String packetPhone) {
        this.packetPhone = packetPhone;
    }

    public Long getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Long discountMoney) {
        this.discountMoney = discountMoney;
    }
}