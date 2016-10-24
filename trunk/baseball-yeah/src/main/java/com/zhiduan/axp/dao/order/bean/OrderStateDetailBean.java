package com.zhiduan.axp.dao.order.bean;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: OrderDetailBean
 * @Description: 订单详细bean
 * @author ZhangLei
 * @date 2016年6月8日 下午3:56:24 
 */
public class OrderStateDetailBean implements Serializable {

	private static final long serialVersionUID = 7197823124648189901L;
	
	/**
	 * 创建时间
	 */
	    
	private Date createDate;
	
	/**
	 * 创建时间字符串
	 */
	private String createDateStr;
	
	/**
	 * 内容
	 */
	
	private String content;
	
	/**
	 * 状态
	 */
	
	private Integer state;
	
	/**
	 * 订单创建人
	 */
	private String createUser;
	
	/**
	 * 众包抢单人
	 */
	private String winnerName;

	
	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getWinnerName() {
		return winnerName;
	}

	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
		setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createDate));
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
