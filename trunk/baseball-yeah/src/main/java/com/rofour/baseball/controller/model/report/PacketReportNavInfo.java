/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.model.report;

import java.io.Serializable;

/**
 * @ClassName: PacketReportNavInfo
 * @Description: 众包运营统计导航
 * @author lpp
 * @date 2016年10月14日 下午2:49:14 
 */
public class PacketReportNavInfo implements Serializable {

	private static final long serialVersionUID = 2139304060444532200L;

	/**小派总数*/
	private Integer puserNum;
	
	/**活跃小派数*/
	private Integer activePuserNum;
	
	/**前天小派总数*/
	private Integer puserNumBefore;
	
	/**新增小派数*/
	private Integer puserIncNum;
	
	/**当天运力*/
	private Integer shippingAbility;
	
	/**当天总订单数*/
	private Integer totalOrderNum;
	
	/**当天总接单数*/
	private Integer takeOrderNum;
	
	/**有效评论订单数*/
	private Integer commentOrderNum;
	
	/**有效评论好评单数*/
	private Integer commentOrderGoodnum;
	
	/**前天运力*/
	private Integer shippingAbilityBefore;
	
	/**运力新增数*/
	private Integer shippingAbilityIncNum;
	
	/**有效评论用户打分总数*/
	private Integer commentOrderGetscore;
	
	/**活跃度*/
	private String activeDegree;
	
	/**小派增长率*/
	private String puserIncRate;
	
	/**运力增长率*/
	private String shippingIncRate;
	
	/**接单率*/
	private String recOrderRate;
	
	/**好评率*/
	private String commentGoodRate;

	public Integer getShippingAbility() {
		return shippingAbility;
	}

	public void setShippingAbility(Integer shippingAbility) {
		this.shippingAbility = shippingAbility;
	}

	public Integer getTotalOrderNum() {
		return totalOrderNum;
	}

	public void setTotalOrderNum(Integer totalOrderNum) {
		this.totalOrderNum = totalOrderNum;
	}

	public Integer getTakeOrderNum() {
		return takeOrderNum;
	}

	public void setTakeOrderNum(Integer takeOrderNum) {
		this.takeOrderNum = takeOrderNum;
	}

	public Integer getCommentOrderNum() {
		return commentOrderNum;
	}

	public void setCommentOrderNum(Integer commentOrderNum) {
		this.commentOrderNum = commentOrderNum;
	}

	public Integer getCommentOrderGoodnum() {
		return commentOrderGoodnum;
	}

	public void setCommentOrderGoodnum(Integer commentOrderGoodnum) {
		this.commentOrderGoodnum = commentOrderGoodnum;
	}

	public Integer getShippingAbilityBefore() {
		return shippingAbilityBefore;
	}

	public void setShippingAbilityBefore(Integer shippingAbilityBefore) {
		this.shippingAbilityBefore = shippingAbilityBefore;
	}

	public Integer getPuserNum() {
		return puserNum;
	}

	public void setPuserNum(Integer puserNum) {
		this.puserNum = puserNum;
	}

	public Integer getActivePuserNum() {
		return activePuserNum;
	}

	public void setActivePuserNum(Integer activePuserNum) {
		this.activePuserNum = activePuserNum;
	}

	public Integer getPuserNumBefore() {
		return puserNumBefore;
	}

	public void setPuserNumBefore(Integer puserNumBefore) {
		this.puserNumBefore = puserNumBefore;
	}

	public Integer getPuserIncNum() {
		return puserIncNum;
	}

	public void setPuserIncNum(Integer puserIncNum) {
		this.puserIncNum = puserIncNum;
	}

	public Integer getShippingAbilityIncNum() {
		return shippingAbilityIncNum;
	}

	public void setShippingAbilityIncNum(Integer shippingAbilityIncNum) {
		this.shippingAbilityIncNum = shippingAbilityIncNum;
	}

	public Integer getCommentOrderGetscore() {
		return commentOrderGetscore;
	}

	public void setCommentOrderGetscore(Integer commentOrderGetscore) {
		this.commentOrderGetscore = commentOrderGetscore;
	}

	public String getActiveDegree() {
		return activeDegree;
	}

	public void setActiveDegree(String activeDegree) {
		this.activeDegree = activeDegree;
	}

	public String getPuserIncRate() {
		return puserIncRate;
	}

	public void setPuserIncRate(String puserIncRate) {
		this.puserIncRate = puserIncRate;
	}

	public String getShippingIncRate() {
		return shippingIncRate;
	}

	public void setShippingIncRate(String shippingIncRate) {
		this.shippingIncRate = shippingIncRate;
	}

	public String getRecOrderRate() {
		return recOrderRate;
	}

	public void setRecOrderRate(String recOrderRate) {
		this.recOrderRate = recOrderRate;
	}

	public String getCommentGoodRate() {
		return commentGoodRate;
	}

	public void setCommentGoodRate(String commentGoodRate) {
		this.commentGoodRate = commentGoodRate;
	}
}
