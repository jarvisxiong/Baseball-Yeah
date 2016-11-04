package com.rofour.baseball.dao.report.bean;

import java.io.Serializable;

/**
 * @ClassName: ReportBusinessBean
 * @Description: 运营日报 
 * @author gechao
 * @date 2016年11月01日 下午4:46:54 
 */
public class ReportBusinessBean implements Serializable{
 
	private static final long serialVersionUID = 2130127400974429208L;
	
	// 主键
	private String recordId;
	// 订单累计总量显示值
	private int dispOrderNum;
	// 显示日环比
	private String dispDayPercent;
	// 显示周同比
	private String dispDayWeekPpercent; 
	// 显示众包商户短信量
	private int dispSmsNum;
	// 显示短信总量
	private int dispSmsAllNum;
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
	// 提交人
	private String editOpId;
	// 审核人
	private String auditOpId;
	
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
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
	public int getDispSmsAllNum() {
		return dispSmsAllNum;
	}
	public void setDispSmsAllNum(int dispSmsAllNum) {
		this.dispSmsAllNum = dispSmsAllNum;
	}
	public String getEditOpId() {
		return editOpId;
	}
	public void setEditOpId(String editOpId) {
		this.editOpId = editOpId;
	}
	public String getAuditOpId() {
		return auditOpId;
	}
	public void setAuditOpId(String auditOpId) {
		this.auditOpId = auditOpId;
	}

}
