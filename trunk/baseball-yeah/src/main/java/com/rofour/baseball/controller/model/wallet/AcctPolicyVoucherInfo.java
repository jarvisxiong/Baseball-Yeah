/**  
* @Title: AcctPolicyVoucherInfo.java
* @package com.rofour.baseball.controller.model.wallet
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年7月18日 下午3:13:08 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.controller.model.wallet;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: AcctPolicyVoucherInfo
* @Description: 代金券实体
* @author heyi
* @date 2016年7月18日 下午3:13:08 
*
*/

public class AcctPolicyVoucherInfo extends BasePage implements Serializable {

    
	private static final long serialVersionUID = 5049696000558106266L;
    
	/**
	 * 策略ID
	 */
	private Integer policyId;
	/**
	 * 生效时间
	 */
	private Date effectTime;
	/**
	 * 失效时间
	 */
	private Date expireTime;
	/**
	 * 面值
	 */
	private Integer faceValue;	
	/**
	 * 领取时间
	 */
	private Date receiveTime;
	/**
	 * 状态名称
	 */
	private String stateName;
	/**
	 * 状态编码
	 */
	private Byte state;
	/**
	 * 使用时间
	 */
	private String useTime;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 代金券ID
	 */
	private Long voucherId;
	/**
	 * 策略名称
	 */
	private String policyName;
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 用户登录名
	 */
	private String userName;
	/**
	 * 活动ID
	 */
	private Integer activityId;
	
	private String useStartTime;
	private String useEndTime;
	private String receiveStartTime;
	private String receiveEndTime;

	/**
	 * @return policyId
	 */
	
	public Integer getPolicyId() {
		return policyId;
	}
	/**
	 * @param policyId the policyId to set
	 */
	
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}
	/**
	 * @return effectTime
	 */
	
	public Date getEffectTime() {
		return effectTime;
	}
	/**
	 * @param effectTime the effectTime to set
	 */
	
	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}
	/**
	 * @return expireTime
	 */
	
	public Date getExpireTime() {
		return expireTime;
	}
	/**
	 * @param expireTime the expireTime to set
	 */
	
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * @return faceValue
	 */
	
	public Integer getFaceValue() {
		return faceValue;
	}
	/**
	 * @param faceValue the faceValue to set
	 */
	
	public void setFaceValue(Integer faceValue) {
		this.faceValue = faceValue;
	}
	/**
	 * @return receiveTime
	 */
	
	public Date getReceiveTime() {
		return receiveTime;
	}
	/**
	 * @param receiveTime the receiveTime to set
	 */
	
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	/**
	 * @return stateName
	 */
	
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/**
	 * @return state
	 */
	
	public Byte getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	
	public void setState(Byte state) {
		this.state = state;
	}
	/**
	 * @return useTime
	 */
	
	public String getUseTime() {
		return useTime;
	}
	/**
	 * @param useTime the useTime to set
	 */
	
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	/**
	 * @return userId
	 */
	
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return voucherId
	 */
	
	public Long getVoucherId() {
		return voucherId;
	}
	/**
	 * @param voucherId the voucherId to set
	 */
	
	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}
	/**
	 * @return policyName
	 */
	
	public String getPolicyName() {
		return policyName;
	}
	/**
	 * @param policyName the policyName to set
	 */
	
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	/**
	 * @return activityName
	 */
	
	public String getActivityName() {
		return activityName;
	}
	/**
	 * @param activityName the activityName to set
	 */
	
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * @return nickName
	 */
	
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	/**
	 * @return activityId
	 */
	
	public Integer getActivityId() {
		return activityId;
	}
	/**
	 * @param activityId the activityId to set
	 */
	
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getUseStartTime() {
		return useStartTime;
	}
	public void setUseStartTime(String useStartTime) {
		this.useStartTime = useStartTime;
	}
	public String getUseEndTime() {
		return useEndTime;
	}
	public void setUseEndTime(String useEndTime) {
		this.useEndTime = useEndTime;
	}
	public String getReceiveStartTime() {
		return receiveStartTime;
	}
	public void setReceiveStartTime(String receiveStartTime) {
		this.receiveStartTime = receiveStartTime;
	}
	public String getReceiveEndTime() {
		return receiveEndTime;
	}
	public void setReceiveEndTime(String receiveEndTime) {
		this.receiveEndTime = receiveEndTime;
	}
	
	
}
