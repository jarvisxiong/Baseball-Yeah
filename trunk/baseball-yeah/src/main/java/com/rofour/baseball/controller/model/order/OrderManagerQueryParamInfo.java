package com.rofour.baseball.controller.model.order;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单管理查询条件对象
 * @author wangyu
 * @ClassName:com.rofour.baseball.controller.model.order.OrderManagerQueryParamInfo
 * @Description: 描述：
 * @date 2016/10/11 9:26
 */
public class OrderManagerQueryParamInfo extends BasePage implements Serializable {
	private static final long serialVersionUID = 8254042614989880676L;
	/**
	 * 支付方式
	 */
	private Integer payType;
	/**
	 * 学校ID
	 */
	private Long collegeId;
	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 订单状态
	 */
	private Integer orderState;
	/**
	 * 创建开始时间
	 */
	private String createStartDate;
	/**
	 *
	 */
	private String colleges;
	/**
	 * 创建结束时间
	 */
	private String createEndDate;
	/**
	 * 支付类型1：预付，2：当面付
	 */
	private Integer payModel;
	/**
	 * 支付状态:1 未支付 2 :部分支付 3:已支付
	 */
	private Integer payState;
	/**
	 * 状态更新开始时间
	 */
	private String updateStartDate;
	/**
	 * 状态更新结束时间
	 */
	private String updateEndDate;
	/**
	 * 小派手机号
	 */
	private String winnerMobile;
	/**
	 * 下单人手机号
	 */
	private String createUserMobile;
	/**
	 * 订单类型 1：代取，2：代寄
	 */
	private Integer orderType;
	/**
	 * 根据省查询订单列表
	 */
	private List<Long> provinceIdList;
	/**
	 * 根据市查询订单列表
	 */
	private List<Long> cityIdList;
	/**
	 * 根据校区查询订单列表
	 */
	private List<Long> collegeIdList;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}



	public Integer getPayModel() {
		return payModel;
	}

	public void setPayModel(Integer payModel) {
		this.payModel = payModel;
	}

	public String getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(String createStartDate) {
		this.createStartDate = createStartDate;
	}

	public String getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(String createEndDate) {
		this.createEndDate = createEndDate;
	}

	public String getUpdateStartDate() {
		return updateStartDate;
	}

	public void setUpdateStartDate(String updateStartDate) {
		this.updateStartDate = updateStartDate;
	}

	public String getUpdateEndDate() {
		return updateEndDate;
	}

	public void setUpdateEndDate(String updateEndDate) {
		this.updateEndDate = updateEndDate;
	}

	public String getWinnerMobile() {
		return winnerMobile;
	}

	public void setWinnerMobile(String winnerMobile) {
		this.winnerMobile = winnerMobile;
	}

	public String getCreateUserMobile() {
		return createUserMobile;
	}

	public void setCreateUserMobile(String createUserMobile) {
		this.createUserMobile = createUserMobile;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public List<Long> getProvinceIdList() {
		return provinceIdList;
	}

	public void setProvinceIdList(List<Long> provinceIdList) {
		this.provinceIdList = provinceIdList;
	}

	public List<Long> getCityIdList() {
		return cityIdList;
	}

	public void setCityIdList(List<Long> cityIdList) {
		this.cityIdList = cityIdList;
	}

	public List<Long> getCollegeIdList() {
		return collegeIdList;
	}

	public void setCollegeIdList(List<Long> collegeIdList) {
		this.collegeIdList = collegeIdList;
	}

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public String getColleges() {
		return colleges;
	}

	public void setColleges(String colleges) {
		this.colleges = colleges;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@Override
	public String toString() {
		return "OrderManagerQueryParamInfo{" +
				"orderId=" + orderId +
				", orderState=" + orderState +
				", createStartDate=" + createStartDate +
				", createEndDate=" + createEndDate +
				", payModel=" + payModel +
				", payState=" + payState +
				", updateStartDate=" + updateStartDate +
				", updateEndDate=" + updateEndDate +
				", winnerMobile='" + winnerMobile + '\'' +
				", createUserMobile='" + createUserMobile + '\'' +
				", orderType=" + orderType +
				", provinceIdList=" + provinceIdList +
				", cityIdList=" + cityIdList +
				", collegeId=" + collegeId +
				", payType=" + payType +
				", collegeIdList=" + collegeIdList +
				'}';
	}
}
