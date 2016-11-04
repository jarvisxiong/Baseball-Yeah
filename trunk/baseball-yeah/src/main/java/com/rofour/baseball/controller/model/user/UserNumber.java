package com.rofour.baseball.controller.model.user;

import java.io.Serializable;

public class UserNumber implements Serializable {

	private static final long serialVersionUID = -537481135826142533L;
	
	String dayTime;
	Integer number;
	public String getDayTime() {
		return dayTime;
	}
	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public UserNumber(String dayTime, Integer number) {
		super();
		this.dayTime = dayTime;
		this.number = number;
	}
	public UserNumber() {
		super();
	}
	
	
}
