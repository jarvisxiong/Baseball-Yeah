package com.rofour.baseball.dao.report.bean;

import java.io.Serializable;
/**
 * (全国)订单运营统计分析（所有校区按天）：订单运营统计分析,订单量监控,订单运力匹配监控,代取代寄
 * @author zhoujie
 *
 */
public class ReportOrderStatisticsSumBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1322712220178684245L;

	// 
	private  Long id ;
	
	private  String  day;// '日期'
	
	private  int totalOrderNum;//当天总订单数
	
	private int activePpuserNum;//当天活跃小派总数
	
	private int shippingAbility;//当天运力
	
	private int bonusIncome;//支付金额
	
	private float takeOrderMins;//当天接单时效（单位：分钟）
	
	private float takeOrderMinsAvg;//全国历史接单时效（单位：分钟）
	
	private int orderTypeTake;//订单类型：代取
	
	private int orderTypeSend;//订单类型：代寄
	
	private float orderNumYoy;//订单增长同比12.25%->0.1225
	
	private float orderNumQoq;//订单增长环比
	
	private int orderSourceService;//当天订单来源数：服务号
	
	private int orderSourceSms;//当天订单来源数：商户短信
	
	private int commentOrderNum;//有效评论订单数
	
	private int commentOrderGoodnum;//有效评论好评单数
	
	private int commentOrderGetscore;//有效评论用户打分总数
	
	private int orderStatusDoneFinishtime;//完成时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getActivePpuserNum() {
		return activePpuserNum;
	}

	public void setActivePpuserNum(int activePpuserNum) {
		this.activePpuserNum = activePpuserNum;
	}

	public int getShippingAbility() {
		return shippingAbility;
	}

	public void setShippingAbility(int shippingAbility) {
		this.shippingAbility = shippingAbility;
	}

	public int getBonusIncome() {
		return bonusIncome;
	}

	public void setBonusIncome(int bonusIncome) {
		this.bonusIncome = bonusIncome;
	}

	public float getTakeOrderMins() {
		return takeOrderMins;
	}

	public void setTakeOrderMins(float takeOrderMins) {
		this.takeOrderMins = takeOrderMins;
	}

	public float getTakeOrderMinsAvg() {
		return takeOrderMinsAvg;
	}

	public void setTakeOrderMinsAvg(float takeOrderMinsAvg) {
		this.takeOrderMinsAvg = takeOrderMinsAvg;
	}

	public int getOrderTypeTake() {
		return orderTypeTake;
	}

	public void setOrderTypeTake(int orderTypeTake) {
		this.orderTypeTake = orderTypeTake;
	}

	public int getOrderTypeSend() {
		return orderTypeSend;
	}

	public void setOrderTypeSend(int orderTypeSend) {
		this.orderTypeSend = orderTypeSend;
	}

	public float getOrderNumYoy() {
		return orderNumYoy;
	}

	public void setOrderNumYoy(float orderNumYoy) {
		this.orderNumYoy = orderNumYoy;
	}

	public float getOrderNumQoq() {
		return orderNumQoq;
	}

	public void setOrderNumQoq(float orderNumQoq) {
		this.orderNumQoq = orderNumQoq;
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

    public int getOrderStatusDoneFinishtime()
    {
        return orderStatusDoneFinishtime;
    }

    public void setOrderStatusDoneFinishtime(int orderStatusDoneFinishtime)
    {
        this.orderStatusDoneFinishtime = orderStatusDoneFinishtime;
    }

   
	
	
}
