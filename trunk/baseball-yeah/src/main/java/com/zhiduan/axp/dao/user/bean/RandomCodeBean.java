/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.user.bean;

import java.io.Serializable;
import java.util.Date;


/**
* @ClassName: RandomCodeForUserBean
* @Description: 用户随机码bean
* @author 周琦
* @date 2016年3月26日 下午3:23:07 
*
*/
    
public class RandomCodeBean implements Serializable{

	    
	private static final long serialVersionUID = -7499326113380978734L;

	/**
	 * 编码
	 */
	private Long randomCodeId;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 商户ID
	 */
	private Long storeId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 随机码类型
	 */
	private String codeType;
	/**
	 * 随机码
	 */
	private String code;
	/**
	 * 随机码状态
	 */
	private Byte codeStatus;
	/**
	 * 随机码使用次数
	 */
	private Integer usetimes;
	/**
	 * 随机码失效时间
	 */
	private Date expireTime;
	/**
	 * 随机码生成时间
	 */
	private Date produceTime;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public RandomCodeBean(Long randomCodeId, Long userId, String userName, String codeType, String code,
			Byte codeStatus, Integer usetimes, Date expireTime, Date produceTime) {
		this.randomCodeId = randomCodeId;
		this.userId = userId;
		this.userName = userName;
		this.codeType = codeType;
		this.code = code;
		this.codeStatus = codeStatus;
		this.usetimes = usetimes;
		this.expireTime = expireTime;
		this.produceTime = produceTime;
	}

	public RandomCodeBean() {
		super();
	}

	public Long getRandomCodeId() {
		return randomCodeId;
	}

	public void setRandomCodeId(Long randomCodeId) {
		this.randomCodeId = randomCodeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public Byte getCodeStatus() {
		return codeStatus;
	}

	public void setCodeStatus(Byte codeStatus) {
		this.codeStatus = codeStatus;
	}

	public Integer getUsetimes() {
		return usetimes;
	}

	public void setUsetimes(Integer usetimes) {
		this.usetimes = usetimes;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getProduceTime() {
		return produceTime;
	}

	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}
}