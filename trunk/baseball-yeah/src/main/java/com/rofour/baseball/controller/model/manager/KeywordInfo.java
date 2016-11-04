package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;

import com.rofour.baseball.controller.model.BasePage;

public class KeywordInfo extends BasePage implements Serializable {

	private static final long serialVersionUID = -9069504186269896495L;
	// 主键
	private String wordId;
	// 关键字内容
	private String wordContent;
	// 创建时间
	private String createTime;
	// 注册时间开始
	private String startDate;
	// 注册时间结束
	private String endDate; 
	private String realName;
	
	public String getStartDate() {
		if(startDate != null && !"".equals(startDate)) {
			startDate += " 00:00:00";
		}
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		if(endDate != null && !"".equals(endDate)) {
			endDate += " 23:59:59";
		}
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWordId() {
		return wordId;
	}
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}
	public String getWordContent() {
		return wordContent;
	}
	public void setWordContent(String wordContent) {
		this.wordContent = wordContent;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	} 
	
}
