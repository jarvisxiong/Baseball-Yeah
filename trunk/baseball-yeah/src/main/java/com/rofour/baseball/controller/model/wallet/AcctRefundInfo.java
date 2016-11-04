package com.rofour.baseball.controller.model.wallet;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rofour.baseball.common.CustomJsonDateDeserializer;
import com.rofour.baseball.controller.model.BasePage;

/**
 * Created by wny on 2016-07-20.
 */
public class AcctRefundInfo extends BasePage implements Serializable{
	    
	private static final long serialVersionUID = 7813354713812504115L;
	/**
     * 退款单号
     **/
    private Long refundId;
    /**
     * 订单号
     **/
    private String orderId;
    /**
     * 支付流水id
     **/
    private String flowId;
    /**
     * 第三方类型 1-微信 2-支付宝
     **/
    private Integer thdType;
    /**
     * 第三方退款流水id
     **/
    private String thdRefundId;
    /**
     * 支付金额
     **/
    private Integer payAmount;
    /**
     * 退款金额
     **/
    private Integer refundAmount;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 退款状态 0:提交申请 1:处理中 2:到账 3:审核不通过
     **/
    private Integer refundState;
    /**
     * 退款原因
     **/
    private String refundReason;
    /**
     * 数据状态 0-废弃 1-可用
     **/
    private Integer state;
    /**
     * ip地址
     **/
    private String ipAddress;
    /**
     * 操作人id
     **/
    private Long operationId;
    /**
     * 操作人名称
     **/
    private String operationName;
    /**
     * 更新时间
     **/
    private Date modifyTime;
    /**
     * 退款批次号
     **/
    private Long batchNo;
    /**
     * 开始时间,根据更新时间查询的参数
     */
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date startTime;
    /**
     * 结束时间,根据更新时间查询的参数
     */
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date endTime;
    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Integer getThdType() {
        return thdType;
    }

    public void setThdType(Integer thdType) {
        this.thdType = thdType;
    }

    public String getThdRefundId() {
        return thdRefundId;
    }

    public void setThdRefundId(String thdRefundId) {
        this.thdRefundId = thdRefundId;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRefundState() {
        return refundState;
    }

    public void setRefundState(Integer refundState) {
        this.refundState = refundState;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Long batchNo) {
        this.batchNo = batchNo;
    }
    
    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public AcctRefundInfo() {
	}
	public AcctRefundInfo(Long refundId, String orderId, String flowId, Integer thdType, String thdRefundId, Integer payAmount, Integer refundAmount, Date createTime, Integer refundState, String refundReason, Integer state, String ipAddress, Long operationId, String operationName, Date modifyTime, Long batchNo) {
        this.refundId = refundId;
        this.orderId = orderId;
        this.flowId = flowId;
        this.thdType = thdType;
        this.thdRefundId = thdRefundId;
        this.payAmount = payAmount;
        this.refundAmount = refundAmount;
        this.createTime = createTime;
        this.refundState = refundState;
        this.refundReason = refundReason;
        this.state = state;
        this.ipAddress = ipAddress;
        this.operationId = operationId;
        this.operationName = operationName;
        this.modifyTime = modifyTime;
        this.batchNo = batchNo;
    }
}
