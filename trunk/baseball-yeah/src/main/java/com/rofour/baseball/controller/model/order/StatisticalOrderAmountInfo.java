package com.rofour.baseball.controller.model.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 统计订单金额对象
 * @author wangyu
 * @ClassName:com.rofour.baseball.controller.model.order.StatisticalOrderAmountInfo
 * @Description: 描述：
 * @date 2016/10/12 9:44
 */
public class StatisticalOrderAmountInfo implements Serializable {
	private static final long serialVersionUID = -1916824722508279170L;

	private BigDecimal rewardAmount;

	private BigDecimal discountAmount;

	private BigDecimal finalAmount;

	public StatisticalOrderAmountInfo(BigDecimal rewardAmount, BigDecimal discountAmount, BigDecimal finalAmount) {
		this.rewardAmount = rewardAmount;
		this.discountAmount = discountAmount;
		this.finalAmount = finalAmount;
	}

	public BigDecimal getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(BigDecimal rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}
}
