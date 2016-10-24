package com.zhiduan.axp.controller.model;

import java.io.Serializable;

    
/**
* @ClassName: ChartsRegister
* @Description: 图标统计model
* @author ZhangLei
* @date 2016年5月13日 下午3:58:24 
*
*/
    
public class ChartsRegister implements Serializable {

	    
	private static final long serialVersionUID = 9032421827955293192L;
	    
	String date;
	Long userNumber;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(Long userNumber) {
		this.userNumber = userNumber;
	}
	public ChartsRegister(String date, Long userNumber) {
		super();
		this.date = date;
		this.userNumber = userNumber;
	}
	public ChartsRegister() {
		super();
	}
	
	
	
}
