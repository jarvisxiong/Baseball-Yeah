package com.rofour.baseball.dao.wallet.bean;

import java.io.Serializable;
import java.util.Date;

public class AcctThdPaymentTypeBean implements Serializable {
	
	private static final long serialVersionUID = -1788392532600534283L;

    /**
	 * 编码
	 */
    private Byte typeCode;

    /**
	 * 名称
	 */
    private String typeName;

    /**
	 * 状态（0失效，1生效）
	 */
    private Byte state;

    /**
	 * 操作时间
	 */
    private Date optTime;


    public AcctThdPaymentTypeBean(Byte typeCode, String typeName, Byte state,
			Date optTime) {
		super();
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.state = state;
		this.optTime = optTime;
	}

	public AcctThdPaymentTypeBean() {
        super();
    }

    public Byte getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Byte typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }
}