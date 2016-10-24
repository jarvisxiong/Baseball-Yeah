package com.zhiduan.axp.controller.model.user;

import java.io.Serializable;
import java.util.List;

import com.zhiduan.axp.controller.model.resource.CredentialURLInfo;
import com.zhiduan.axp.dao.manager.bean.CollegeBean;

/**
 * @author wangyu
 * @ClassName:com.zhiduan.axp.idl.usercenter.service.entity.ExpUserPerfectInfo
 * @Description: 描述：
 * @date 2016/5/6 13:39
 */
public class ExpUserPerfectInfo implements Serializable{
	private static final long serialVersionUID = -6222988143848481398L;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 门店编号
	 */
	private String storeCode;
	/**
	 * 门店名称
	 */
	private String storeName;
	/**
	 * 站点地址
	 */
	private String location;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 帐号
	 */
	private String userName;
	/**
	 * 快递公司名称
	 */
	private String expressCompanyName;
	/**
	 * 身份证号码
	 */
	private String identityNumber;
	/**
	 * 用户认证状态
	 */
	private Byte verifyStatus;
	/**
	 * 认证信息
	 */
	private String verifyInfo;
	/**
	 * 认证备注
	 */
	private String verifyRemark;
	/**
	 * 学校列表
	 */
	private List<CollegeBean> collegeList;
	/**
	 * 证件信息
	 */
	private List<CredentialURLInfo> photoList;
	
	/**
	 * 校区名称
	 */
	private String collegeFullName;
	
	/**
	 * 学校地址
	 */
	private String collegeAddress;
	
	/**
	 * 宿舍地址
	 */
	private String dormitoryAddress;
	
	public void setCollegeAddress(String collegeAddress) {
		this.collegeAddress = collegeAddress;
	}
	public String getCollegeAddress() {
		return collegeAddress;
	}
	
	public void setDormitoryAddress(String dormitoryAddress) {
		this.dormitoryAddress = dormitoryAddress;
	}
	
	public String getDormitoryAddress() {
		return dormitoryAddress;
	}
	
	public void setCollegeFullName(String collegeFullName) {
		this.collegeFullName = collegeFullName;
	}
	public String getCollegeFullName() {
		return collegeFullName;
	}

	private ExpUserAuditResultInfo auditResultInfo;
	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public Byte getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Byte verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public List<CollegeBean> getCollegeList() {
		return collegeList;
	}

	public void setCollegeList(List<CollegeBean> collegeList) {
		this.collegeList = collegeList;
	}

	public List<CredentialURLInfo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<CredentialURLInfo> photoList) {
		this.photoList = photoList;
	}

	public String getVerifyInfo() {
		return verifyInfo;
	}

	public void setVerifyInfo(String verifyInfo) {
		this.verifyInfo = verifyInfo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ExpUserAuditResultInfo getAuditResultInfo() {
		return auditResultInfo;
	}

	public void setAuditResultInfo(ExpUserAuditResultInfo auditResultInfo) {
		this.auditResultInfo = auditResultInfo;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}
}

