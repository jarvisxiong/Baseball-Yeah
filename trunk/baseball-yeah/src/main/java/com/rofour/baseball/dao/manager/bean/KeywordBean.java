package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;

/**
 * Created by gechao on 2016-10-24.
 */
public class KeywordBean implements Serializable{
	 
	private static final long serialVersionUID = 8037347059843540208L;
	// 主键
	private String wordId;
	// 关键字内容
	private String addWordContent;
	// 创建时间
	private String createTime;
	// 用户ID
	private Long userId;
	
	public String getWordId() {
		return wordId;
	}
	public void setWordId(String wordId) {
		this.wordId = wordId;
	} 
	
	public String getAddWordContent() {
		return addWordContent;
	}
	public void setAddWordContent(String addWordContent) {
		this.addWordContent = addWordContent;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
