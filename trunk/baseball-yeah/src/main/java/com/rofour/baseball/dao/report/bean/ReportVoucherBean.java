package com.rofour.baseball.dao.report.bean;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;

/**
 *
 * @ClassName: ReportStoreSmsBean
 * @Description: 代金券报表返回实体
 * @author lj
 * @date 2016年9月29日 上午09:35:03
 *
 */
public class ReportVoucherBean extends BasePage implements Serializable {

	private static final long serialVersionUID = 7032573373820585624L;
	/**
	 * 序号
	 */
	private long rowNo;

	private String voucherId;
	/**
	 * 校园名称
	 */
	private String collegeName;
	private String collegeId;
	/**
	 * 城市
	 */
	private String cityName;
	private String cityId;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 代金券金额
	 */
	private String money;
	/**
	 * 订单状态
	 */
	private String state;
	/**
	 * 订单入口
	 */
	private String channelName;
	/**
	 * 渠道来源
	 */
	private String itemName;
	/**
	 * 领用时间
	 */
	private String receiveTime;
	/**
	 * 使用时间
	 */
	private String useTime;
	/**
	 * 失效时间
	 */
	private String expireTime;


	private String receiveStartTime;
	private String receiveEndTime;

	private String useStartTime;
	private String useEndTime;

	private String expireStartTime;
	private String expireEndTime;


	public long getRowNo() {
		return rowNo;
	}
	public void setRowNo(long rowNo) {
		this.rowNo = rowNo;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getReceiveStartTime() {
		return receiveStartTime;
	}
	public void setReceiveStartTime(String receiveStartTime) {
		this.receiveStartTime = receiveStartTime;
	}
	public String getReceiveEndTime() {
		return receiveEndTime;
	}
	public void setReceiveEndTime(String receiveEndTime) {
		this.receiveEndTime = receiveEndTime;
	}
	public String getUseStartTime() {
		return useStartTime;
	}
	public void setUseStartTime(String useStartTime) {
		this.useStartTime = useStartTime;
	}
	public String getUseEndTime() {
		return useEndTime;
	}
	public void setUseEndTime(String useEndTime) {
		this.useEndTime = useEndTime;
	}
	public String getExpireStartTime() {
		return expireStartTime;
	}
	public void setExpireStartTime(String expireStartTime) {
		this.expireStartTime = expireStartTime;
	}
	public String getExpireEndTime() {
		return expireEndTime;
	}
	public void setExpireEndTime(String expireEndTime) {
		this.expireEndTime = expireEndTime;
	}
	public String getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}

	@Override
	public String toString() {
		return "SampleServiceImpl [rowNo=" + rowNo + ", voucherId="+voucherId+",collegeName=" + collegeName + ", cityName=" + cityName
				+ ", orderId=" + orderId + ", money=" + money + ", state=" + state + ", channelName=" + channelName
				+ ", itemName=" + itemName + ", receiveTime=" + receiveTime + ", useTime=" + useTime + ", expireTime="
				+ expireTime + ", receiveStartTime=" + receiveStartTime + ", receiveEndTime=" + receiveEndTime
				+ ", useStartTime=" + useStartTime + ", useEndTime=" + useEndTime + ", expireStartTime="
				+ expireStartTime + ", expireEndTime=" + expireEndTime + "]";
	}

	public String getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
}
