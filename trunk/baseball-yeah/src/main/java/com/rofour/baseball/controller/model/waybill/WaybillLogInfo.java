package com.rofour.baseball.controller.model.waybill;

import java.io.Serializable;
import java.util.Date;

/**
 * 运单日志
 * 
 * @author wuzhiquan
 *
 */
public class WaybillLogInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2652441413259906382L;
    /**
     * 运单日志编码
     */
    private Long waybillLogId;
    /**
     * 运单号
     */
    private String waybillNo;
    /**
     * 操作
     */
    private String operation;

    /**
     * 内容
     */
    private String content;

    /**
     * 日志时间
     */
    private Date logTime;

    /**
     * 商户编码
     */
    private Long storeId;

    /**
     * 用户编码
     */
    private Long userId;
    /**
     * 快递公司编码
     */
    private Long expressCompanyId;

    public Long getWaybillLogId() {
        return waybillLogId;
    }

    public void setWaybillLogId(Long waybillLogId) {
        this.waybillLogId = waybillLogId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExpressCompanyId() {
        return expressCompanyId;
    }

    public void setExpressCompanyId(Long expressCompanyId) {
        this.expressCompanyId = expressCompanyId;
    }

}
