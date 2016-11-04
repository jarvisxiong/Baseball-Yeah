package com.rofour.baseball.dao.order.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangyu
 * @ClassName:com.rofour.baseball.idl.ordercenter.dao.bean.OrderFlowBean
 * @Description: 描述：订单流转记录bean
 * @date 2016/5/23 13:28
 */
public class OrderFlowBean implements Serializable {

	private static final long serialVersionUID = -3659655599909708918L;
	/**
	 * 订单项ID
	 */
	private Long id;
	/**
	 * 订单号
	 */
	private Long orderId;
	/**
	 * 订单状态  1:创建2:已接单,3:配送中,4: 处理中 ,5:完成,6:取消,7:异常
	 */
	private Integer state;
	/**
	 * 订单类型
	 */
	private String type;
	/**
	 *内容
	 */
	private String content;
	/**
	 * 创建人
	 */
	private Long createUser;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 备注
	 */
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "OrderFlowBean{" +
				"id=" + id +
				", orderId=" + orderId +
				", state=" + state +
				", type='" + type + '\'' +
				", content='" + content + '\'' +
				", createUser=" + createUser +
				", createDate=" + createDate +
				", remark='" + remark + '\'' +
				'}';
	}
}
