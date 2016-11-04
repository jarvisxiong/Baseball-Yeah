/**  
* @Title: WalletCashShowInfo.java
* @package com.rofour.baseball.controller.model.wallet
* @Project: baseball-yeah
* @Description: 用户钱包提现帐号实体
* @author heyi
* @date 2016年6月13日 下午5:42:50 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.controller.model.wallet;

import java.io.Serializable;

/**
 * @ClassName: WalletCashShowInfo
 * @Description: 用户钱包提现帐号实体
 * @author heyi
 * @date 2016年6月13日 下午5:42:50
 *
 */

public class WalletCashShowInfo implements Serializable {

	private static final long serialVersionUID = -6615110377939357119L;
	private Integer acctId;
	private String acctNo;
	private Byte thdType;
	private String typeName;

	/**
	 * @return acctId
	 */

	public Integer getAcctId() {
		return acctId;
	}

	/**
	 * @param acctId
	 *            the acctId to set
	 */

	public void setAcctId(Integer acctId) {
		this.acctId = acctId;
	}

	/**
	 * @return acctNo
	 */

	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * @param acctNo
	 *            the acctNo to set
	 */

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	/**
	 * @return thdType
	 */

	public Byte getThdType() {
		return thdType;
	}

	/**
	 * @param thdType
	 *            the thdType to set
	 */

	public void setThdType(Byte thdType) {
		this.thdType = thdType;
	}

	/**
	 * @return typeName
	 */

	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 *            the typeName to set
	 */

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
