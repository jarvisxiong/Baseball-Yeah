package com.rofour.baseball.controller.model.order;

import java.io.Serializable;
import java.util.Date;

import com.rofour.baseball.controller.model.BasePage;

public class OrderStatisticsInfo extends BasePage implements Serializable {
	
	
	/**
	 * 序列号
	 */
	    
	private static final long serialVersionUID = -2343850393592833634L;
	
	
	private Long collegeId;//校区ID
	
	private String collegeName;//校区全称
	
	private  Long regionId;//区域ID
	
	private String regionName;//区域描述
	
	private String day;//日期
	
	private int totalOrderNum;//订单总数
	
	private double totalOrderMoney;//订单金额
	
	private double avgMoney;//订单均价
	
	//处于各个状态中的订单个数
	
	private int orderStatusCreating;//创建中个数
	
	private int orderStatusWaittaking;//待接单个数
	
	private int orderStatusToken;//已接单个数
	
	private int orderStatusShipping;//配送中个数
	
	private int orderStatusDone;//已完成个数
	
	private int orderStatusCancel;//已取消个数
	
	private int orderStatusAbnormal;//异常单个数
	
	//状态分析
	private String tokenPercent;//接单率
	
	private String donePercent;//完成率
	
	private String cancelPercent;//取消率
	
	private String abnormalPercent;//异常率
	
	
	private float takeOrderMins;//当天接单时效(分钟)
	
	private float takeOrderMnsAvg;//历史平均接单时效（单位：分钟）
	
	//运力匹配分析
	
	private int activePuserNumDay ;//小派个数
	
	private double shippingIndex;//运力指数
	
	private int shippingAbility;//运力
	
	
	//订单类型分析
	
	private String orderTypeCoTake;//合作商户代取
	
	private String orderTypeNoncoTake;//非合作商户代取
	
	private String orderTypeSend;//代寄
	
	//订单评价分析
	
	private int commentOrderNum;//有效评论订单数
	
	private int commentOrderGoodnum;//有效评论好评单数
	
	private int commentOrderGetscore;//有效评论用户打分总数
	
	private double avgScore;//平均评分
	
	private String avgGoogAppraise;//好评率
	
	//代金券使用情况
	private int voucherOrderNum;//使用代金券订单数

	private Long voucherOrderMoney;//抵消金额
	
	private Long voucherOrderActualpay;//实付金额
	
	//订单来源分析
	
	private int orderSourceService;//当天订单来源数：服务号
	
	private int orderSourceSms;//当天订单来源数：商户短信
	
	private int orderSourceColink;//当天订单来源数：合作链接

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

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getTotalOrderNum() {
		return totalOrderNum;
	}

	public void setTotalOrderNum(int totalOrderNum) {
		this.totalOrderNum = totalOrderNum;
	}

	public double getTotalOrderMoney() {
		return totalOrderMoney;
	}

	public void setTotalOrderMoney(double totalOrderMoney) {
		this.totalOrderMoney = totalOrderMoney;
	}

	public double getAvgMoney() {
		return avgMoney;
	}

	public void setAvgMoney(double avgMoney) {
		this.avgMoney = avgMoney;
	}

	public int getOrderStatusCreating() {
		return orderStatusCreating;
	}

	public void setOrderStatusCreating(int orderStatusCreating) {
		this.orderStatusCreating = orderStatusCreating;
	}

	public int getOrderStatusWaittaking() {
		return orderStatusWaittaking;
	}

	public void setOrderStatusWaittaking(int orderStatusWaittaking) {
		this.orderStatusWaittaking = orderStatusWaittaking;
	}

	public int getOrderStatusToken() {
		return orderStatusToken;
	}

	public void setOrderStatusToken(int orderStatusToken) {
		this.orderStatusToken = orderStatusToken;
	}

	public int getOrderStatusShipping() {
		return orderStatusShipping;
	}

	public void setOrderStatusShipping(int orderStatusShipping) {
		this.orderStatusShipping = orderStatusShipping;
	}

	public int getOrderStatusDone() {
		return orderStatusDone;
	}

	public void setOrderStatusDone(int orderStatusDone) {
		this.orderStatusDone = orderStatusDone;
	}

	public int getOrderStatusCancel() {
		return orderStatusCancel;
	}

	public void setOrderStatusCancel(int orderStatusCancel) {
		this.orderStatusCancel = orderStatusCancel;
	}

	public int getOrderStatusAbnormal() {
		return orderStatusAbnormal;
	}

	public void setOrderStatusAbnormal(int orderStatusAbnormal) {
		this.orderStatusAbnormal = orderStatusAbnormal;
	}

	
	public String getTokenPercent() {
		return tokenPercent;
	}

	public void setTokenPercent(String tokenPercent) {
		this.tokenPercent = tokenPercent;
	}

	public String getDonePercent() {
		return donePercent;
	}

	public void setDonePercent(String donePercent) {
		this.donePercent = donePercent;
	}

	public String getCancelPercent() {
		return cancelPercent;
	}

	public void setCancelPercent(String cancelPercent) {
		this.cancelPercent = cancelPercent;
	}

	public String getAbnormalPercent() {
		return abnormalPercent;
	}

	public void setAbnormalPercent(String abnormalPercent) {
		this.abnormalPercent = abnormalPercent;
	}

	public float getTakeOrderMins() {
		return takeOrderMins;
	}

	public void setTakeOrderMins(float takeOrderMins) {
		this.takeOrderMins = takeOrderMins;
	}

	public float getTakeOrderMnsAvg() {
		return takeOrderMnsAvg;
	}

	public void setTakeOrderMnsAvg(float takeOrderMnsAvg) {
		this.takeOrderMnsAvg = takeOrderMnsAvg;
	}

	public int getActivePuserNumDay() {
		return activePuserNumDay;
	}

	public void setActivePuserNumDay(int activePuserNumDay) {
		this.activePuserNumDay = activePuserNumDay;
	}

	public double getShippingIndex() {
		return shippingIndex;
	}

	public void setShippingIndex(double shippingIndex) {
		this.shippingIndex = shippingIndex;
	}

	public int getShippingAbility() {
		return shippingAbility;
	}

	public void setShippingAbility(int shippingAbility) {
		this.shippingAbility = shippingAbility;
	}

	public String getOrderTypeCoTake() {
		return orderTypeCoTake;
	}

	public void setOrderTypeCoTake(String orderTypeCoTake) {
		this.orderTypeCoTake = orderTypeCoTake;
	}

	public String getOrderTypeNoncoTake() {
		return orderTypeNoncoTake;
	}

	public void setOrderTypeNoncoTake(String orderTypeNoncoTake) {
		this.orderTypeNoncoTake = orderTypeNoncoTake;
	}

	public String getOrderTypeSend() {
		return orderTypeSend;
	}

	public void setOrderTypeSend(String orderTypeSend) {
		this.orderTypeSend = orderTypeSend;
	}

	public int getCommentOrderNum() {
		return commentOrderNum;
	}

	public void setCommentOrderNum(int commentOrderNum) {
		this.commentOrderNum = commentOrderNum;
	}

	public int getCommentOrderGoodnum() {
		return commentOrderGoodnum;
	}

	public void setCommentOrderGoodnum(int commentOrderGoodnum) {
		this.commentOrderGoodnum = commentOrderGoodnum;
	}

	public int getCommentOrderGetscore() {
		return commentOrderGetscore;
	}

	public void setCommentOrderGetscore(int commentOrderGetscore) {
		this.commentOrderGetscore = commentOrderGetscore;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public String getAvgGoogAppraise() {
		return avgGoogAppraise;
	}

	public void setAvgGoogAppraise(String avgGoogAppraise) {
		this.avgGoogAppraise = avgGoogAppraise;
	}

	public int getVoucherOrderNum() {
		return voucherOrderNum;
	}

	public void setVoucherOrderNum(int voucherOrderNum) {
		this.voucherOrderNum = voucherOrderNum;
	}

	public Long getVoucherOrderMoney() {
		return voucherOrderMoney;
	}

	public void setVoucherOrderMoney(Long voucherOrderMoney) {
		this.voucherOrderMoney = voucherOrderMoney;
	}

	public Long getVoucherOrderActualpay() {
		return voucherOrderActualpay;
	}

	public void setVoucherOrderActualpay(Long voucherOrderActualpay) {
		this.voucherOrderActualpay = voucherOrderActualpay;
	}

	public int getOrderSourceService() {
		return orderSourceService;
	}

	public void setOrderSourceService(int orderSourceService) {
		this.orderSourceService = orderSourceService;
	}

	public int getOrderSourceSms() {
		return orderSourceSms;
	}

	public void setOrderSourceSms(int orderSourceSms) {
		this.orderSourceSms = orderSourceSms;
	}

	public int getOrderSourceColink() {
		return orderSourceColink;
	}

	public void setOrderSourceColink(int orderSourceColink) {
		this.orderSourceColink = orderSourceColink;
	}


	

}
