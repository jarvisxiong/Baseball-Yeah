/**  
* @Title: WayBillLogInfo.java
* @Package com.zhiduan.axp.controller.model
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月20日 下午4:11:03 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.zhiduan.axp.controller.model;

import java.io.Serializable;

/**
 * @ClassName: WayBillLogInfo
 * @Description: 运单日志实体
 * @author heyi
 * @date 2016年6月20日 下午4:11:03
 *
 */

public class WayBillLogInfo implements Serializable {

	private static final long serialVersionUID = -8609580894370874818L;
	/**
	 * 运单号
	 */
	private String wayBillNo;
	/**
	 * 记录时间
	 */
	private String logTime;
	/**
	 * 记录内容
	 */
	private String content;
	/**
	 * 商户Id
	 */
	private Long storeId;
	/**
	 * 用户Id
	 */
	private Long userId;
	/**
	 * 商户名称
	 */
	private String storeName;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 运单操作类型
	 */
	private String operation;
	private Integer limit;
	private Integer offset;
	private String startTime;
	private String endTime;
	private String expressCompanyName;
	private Long expressCompanyId;

	/**
	 * @return wayBillNo
	 */

	public String getWayBillNo() {
		return wayBillNo;
	}

	/**
	 * @param wayBillNo
	 *            the wayBillNo to set
	 */

	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}

	/**
	 * @return logTime
	 */

	public String getLogTime() {
		return logTime;
	}

	/**
	 * @param logTime
	 *            the logTime to set
	 */

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	/**
	 * @return content
	 */

	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return storeId
	 */

	public Long getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId
	 *            the storeId to set
	 */

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return userId
	 */

	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return storeName
	 */

	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName
	 *            the storeName to set
	 */

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return userName
	 */

	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return operation
	 */

	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	public Long getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(Long expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

}
