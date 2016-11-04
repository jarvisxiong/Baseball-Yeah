package com.rofour.baseball.controller.model.report;

import java.io.Serializable;

import com.rofour.baseball.controller.model.BasePage;

/**
 * @ClassName: ReportBusinessInfo
 * @Description: 运营日报
 * @author gechao
 * @date 2016年11月01日 下午4:46:54
 */
public class ReportBusinessInfo extends BasePage implements Serializable {

	private static final long serialVersionUID = -6908896998742302183L;
	
	// 开始时间
	private String startDate;
	// 结束时间 
	private String endDate;
	// 日期
	private String recordDate;
	// 主键
	private String recordId;
	// 订单累计总量真实值
	private int realOrderNum;
	// 真实日环比
	private String realDayPpercent;
	// 真实周同比
	private String realDayWeePercent;
	// 真实短信量
	private int realSmsNum;
	// 真实短信占比
	private String realSmSpercent;
	// 真实赏金金额
	private int realRewardSum;
	// 真实平台提成金额
	private int realBonusSum;
	// 真实学生手机号总量
	private int realPhoneSum;
	// 真实较昨天手机号增量
	private int realPhoneAddSum;
	// 真实注册总量
	private int realRegisterSum;
	// 真实较昨天注册增量
	private int realRegisterAddSum;
	// 真实累计小派总量
	private int realPacketSum;
	// 真实较昨天小派增量
	private int realPacketAddSum;
	// 订单累计总量显示值
	private int dispOrderNum;
	// 显示日环比
	private String dispDayPercent;
	// 显示周同比
	private String dispDayWeekPpercent;
	// 显示短信量
	private int dispSmsNum;
	// 显示短信占比
	private String dispSmsPercent;
	// 显示赏金金额总计
	private int dispRewardSum;
	// 显示平台提成金额
	private int dispBonusSum;
	// 显示学生手机号总量
	private int dispPhoneSum;
	// 显示较昨天手机号增量
	private int dispPhoneAddSum;
	// 显示注册总量
	private int dispRegisterSum;
	// 显示较昨天注册增量
	private int dispRegisterAddSum;
	// 显示累计小派总量
	private int dispPacketSum;
	// 显示较昨天小派增量
	private int dispPacketAddSum;
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public int getRealOrderNum() {
		return realOrderNum;
	}
	public void setRealOrderNum(int realOrderNum) {
		this.realOrderNum = realOrderNum;
	}
	public String getRealDayPpercent() {
		return realDayPpercent;
	}
	public void setRealDayPpercent(String realDayPpercent) {
		this.realDayPpercent = realDayPpercent;
	}
	public String getRealDayWeePercent() {
		return realDayWeePercent;
	}
	public void setRealDayWeePercent(String realDayWeePercent) {
		this.realDayWeePercent = realDayWeePercent;
	}
	public int getRealSmsNum() {
		return realSmsNum;
	}
	public void setRealSmsNum(int realSmsNum) {
		this.realSmsNum = realSmsNum;
	}
	public String getRealSmSpercent() {
		return realSmSpercent;
	}
	public void setRealSmSpercent(String realSmSpercent) {
		this.realSmSpercent = realSmSpercent;
	}
	public int getRealRewardSum() {
		return realRewardSum;
	}
	public void setRealRewardSum(int realRewardSum) {
		this.realRewardSum = realRewardSum;
	}
	public int getRealBonusSum() {
		return realBonusSum;
	}
	public void setRealBonusSum(int realBonusSum) {
		this.realBonusSum = realBonusSum;
	}
	public int getRealPhoneSum() {
		return realPhoneSum;
	}
	public void setRealPhoneSum(int realPhoneSum) {
		this.realPhoneSum = realPhoneSum;
	}
	public int getRealPhoneAddSum() {
		return realPhoneAddSum;
	}
	public void setRealPhoneAddSum(int realPhoneAddSum) {
		this.realPhoneAddSum = realPhoneAddSum;
	}
	public int getRealRegisterSum() {
		return realRegisterSum;
	}
	public void setRealRegisterSum(int realRegisterSum) {
		this.realRegisterSum = realRegisterSum;
	}
	public int getRealRegisterAddSum() {
		return realRegisterAddSum;
	}
	public void setRealRegisterAddSum(int realRegisterAddSum) {
		this.realRegisterAddSum = realRegisterAddSum;
	}
	public int getRealPacketSum() {
		return realPacketSum;
	}
	public void setRealPacketSum(int realPacketSum) {
		this.realPacketSum = realPacketSum;
	}
	public int getRealPacketAddSum() {
		return realPacketAddSum;
	}
	public void setRealPacketAddSum(int realPacketAddSum) {
		this.realPacketAddSum = realPacketAddSum;
	}
	public int getDispOrderNum() {
		return dispOrderNum;
	}
	public void setDispOrderNum(int dispOrderNum) {
		this.dispOrderNum = dispOrderNum;
	}
	public String getDispDayPercent() {
		return dispDayPercent;
	}
	public void setDispDayPercent(String dispDayPercent) {
		this.dispDayPercent = dispDayPercent;
	}
	public String getDispDayWeekPpercent() {
		return dispDayWeekPpercent;
	}
	public void setDispDayWeekPpercent(String dispDayWeekPpercent) {
		this.dispDayWeekPpercent = dispDayWeekPpercent;
	}
	public int getDispSmsNum() {
		return dispSmsNum;
	}
	public void setDispSmsNum(int dispSmsNum) {
		this.dispSmsNum = dispSmsNum;
	}
	public String getDispSmsPercent() {
		return dispSmsPercent;
	}
	public void setDispSmsPercent(String dispSmsPercent) {
		this.dispSmsPercent = dispSmsPercent;
	}
	public int getDispRewardSum() {
		return dispRewardSum;
	}
	public void setDispRewardSum(int dispRewardSum) {
		this.dispRewardSum = dispRewardSum;
	}
	public int getDispBonusSum() {
		return dispBonusSum;
	}
	public void setDispBonusSum(int dispBonusSum) {
		this.dispBonusSum = dispBonusSum;
	}
	public int getDispPhoneSum() {
		return dispPhoneSum;
	}
	public void setDispPhoneSum(int dispPhoneSum) {
		this.dispPhoneSum = dispPhoneSum;
	}
	public int getDispPhoneAddSum() {
		return dispPhoneAddSum;
	}
	public void setDispPhoneAddSum(int dispPhoneAddSum) {
		this.dispPhoneAddSum = dispPhoneAddSum;
	}
	public int getDispRegisterSum() {
		return dispRegisterSum;
	}
	public void setDispRegisterSum(int dispRegisterSum) {
		this.dispRegisterSum = dispRegisterSum;
	}
	public int getDispRegisterAddSum() {
		return dispRegisterAddSum;
	}
	public void setDispRegisterAddSum(int dispRegisterAddSum) {
		this.dispRegisterAddSum = dispRegisterAddSum;
	}
	public int getDispPacketSum() {
		return dispPacketSum;
	}
	public void setDispPacketSum(int dispPacketSum) {
		this.dispPacketSum = dispPacketSum;
	}
	public int getDispPacketAddSum() {
		return dispPacketAddSum;
	}
	public void setDispPacketAddSum(int dispPacketAddSum) {
		this.dispPacketAddSum = dispPacketAddSum;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	} 
}
