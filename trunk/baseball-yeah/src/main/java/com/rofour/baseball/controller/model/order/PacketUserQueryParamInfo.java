package com.rofour.baseball.controller.model.order;

import com.rofour.baseball.controller.model.BasePage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 派单小派条件对象
 * @author wangyu
 * @ClassName:com.rofour.baseball.controller.model.order.OrderManagerQueryParamInfo
 * @Description: 描述：
 * @date 2016/10/11 9:26
 */
public class PacketUserQueryParamInfo extends BasePage implements Serializable {
	private static final long serialVersionUID = 8254042614989880676L;
	private Long userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 注册开始时间
	 */
	private String signupStartTime;
	/**
	 * 注册结束时间
	 */
	private String signupEndTime;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 1-普通小派 2-coo 3-ceo
	 */
	private Integer officeRoleType;
	/**
	 * 审核状态
	 */
	private Integer beEnabled;
	/**
	 * 校区ID
	 */
	private Long collegeId;
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
		this.userName = userName;
	}

	public String getSignupStartTime() {
		return signupStartTime;
	}

	public void setSignupStartTime(String signupStartTime) {
		this.signupStartTime = signupStartTime;
	}

	public String getSignupEndTime() {
		return signupEndTime;
	}

	public void setSignupEndTime(String signupEndTime) {
		this.signupEndTime = signupEndTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getOfficeRoleType() {
		return officeRoleType;
	}

	public void setOfficeRoleType(Integer officeRoleType) {
		this.officeRoleType = officeRoleType;
	}

	public Integer getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Integer beEnabled) {
		this.beEnabled = beEnabled;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}
}
