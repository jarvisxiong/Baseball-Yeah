/**  
* @Title: WalletAccountInfo.java
* @package com.rofour.baseball.controller.model.wallet
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月13日 下午3:58:46 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.controller.model.wallet;

import java.io.Serializable;

/**
* @ClassName: WalletAccountInfo
* @Description:钱包账户实体
* @author heyi
* @date 2016年6月13日 下午3:58:46 
*
*/

public class WalletAccountInfo implements Serializable {

	private static final long serialVersionUID = 3254983769234100394L;
    private Integer acctId;
    private String activeStateName;
    private Byte activeState;
    private Integer userId;
    private Double balance;
    private Double frozenBalance;
    private String activeTime;
    private String userName;
    private String activeStartTime;
    private String activeEndTime;
    private Byte state;
    private String stateName;
    private String nickName;
	/**
	 * @return acctId
	 */
	
	public Integer getAcctId() {
		return acctId;
	}
	/**
	 * @param acctId the acctId to set
	 */
	
	public void setAcctId(Integer acctId) {
		this.acctId = acctId;
	}
	/**
	 * @return activeStateName
	 */
	
	public String getActiveStateName() {
		return activeStateName;
	}
	/**
	 * @param activeStateName the activeStateName to set
	 */
	
	public void setActiveStateName(String activeStateName) {
		this.activeStateName = activeStateName;
	}
	/**
	 * @return activeState
	 */
	
	public Byte getActiveState() {
		return activeState;
	}
	/**
	 * @param activeState the activeState to set
	 */
	
	public void setActiveState(Byte activeState) {
		this.activeState = activeState;
	}
	/**
	 * @return userId
	 */
	
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return balance
	 */
	
	public Double getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	/**
	 * @return frozenBalance
	 */
	
	public Double getFrozenBalance() {
		return frozenBalance;
	}
	/**
	 * @param frozenBalance the frozenBalance to set
	 */
	
	public void setFrozenBalance(Double frozenBalance) {
		this.frozenBalance = frozenBalance;
	}
	/**
	 * @return activeTime
	 */
	
	public String getActiveTime() {
		return activeTime;
	}
	/**
	 * @param activeTime the activeTime to set
	 */
	
	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}
	/**
	 * @return userName
	 */
	
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getActiveStartTime() {
		return activeStartTime;
	}
	public void setActiveStartTime(String activeStartTime) {
		this.activeStartTime = activeStartTime;
	}
	public String getActiveEndTime() {
		return activeEndTime;
	}
	public void setActiveEndTime(String activeEndTime) {
		this.activeEndTime = activeEndTime;
	}
	public Byte getState() {
		return state;
	}
	public void setState(Byte state) {
		this.state = state;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
