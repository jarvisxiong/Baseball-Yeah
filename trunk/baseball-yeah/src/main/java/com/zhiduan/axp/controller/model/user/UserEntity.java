package com.zhiduan.axp.controller.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: UserEntity
 * @Description: 用户信息
 * @author wangyu
 * @date 2016年3月22日 下午10:13:36
 *
 */

public class UserEntity implements Serializable {

	private static final long serialVersionUID = -3438877352147602903L;

	/*
	 * TbUser 参数
	 */
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String accountPwd;
	/**
	 * 用户认证状态
	 */
	private Byte verifyStatus;
	/**
	 * 认证说明
	 */
	private String verifyMark;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 用户性别
	 */
	private Byte age;
	/**
	 * 所属地区
	 */
	private Long countyId;
	/**
	 * 注册时间
	 */
	private Date signupTime;
	/**
	 * 注册IP
	 */
	private String signupIp;
	/**
	 * 头像
	 */
	private String icon;
	/**
	 * 是否删除
	 */
	private Byte beDeleted;

	/*
	 * TbUserType 参数
	 */
	/**
	 * 用户类型ID
	 */
	private Long userTypeId;
	/**
	 * 用户类型
	 */
	private Integer userType;
	/**
	 * 是否启用
	 */
	private Byte beEnabled;

	/*
	 * TbUserStoreExp 字段
	 */
	/**
	 * 是否负责人
	 */
	private Byte beSupervisor;
	/**
	 * 商户ID
	 */
	private Long storeId;
	/**
	 * 身份证号码
	 */
	private String identityNumber;
	/**
	 * 手持身份证照
	 */
	private String idCardHandPic;
	/**
	 *身份证照
	 */
	private String idCardPic;
	/**
	 * 其他证件照
	 */
	private String credentialsPic;
	/*
	 * 前台参数
	 */

	/**
	 * 新手机号
	 */
	private String newPhone;

	/**
	 * 新密码
	 */
	private String newPwd;

	/**
	 * 更换手机的方式
	 */
	private String changePnMethod;

	/**
	 * 验证码
	 */
	private String verifycode;

	/**
	 * 手机imei
	 */
	private String imei;

	/**
	 * 验证码
	 */
	private String code;

	/**
	 * 验证码类型
	 */
	private String codeType;

	/**
	 * token
	 */
	private String token;

	/**
	 * 手机服务appid
	 */
	private String appid;

	/**
	 * 格式化
	 */
	private String format;

	/**
	 * 版本
	 */
	private String version;

	/**
	 * 时间戳
	 */
	private String timestamp;

	/**
	 * 签名
	 */
	private String sign;
	/*
	 * tb_store字段
	 */
	/**
	 * 位置
	 */
	private String location;

	/**
	 * 商户编码
	 */
	private String storeCode;

	/**
	 * 商户名称
	 */
	private String storeName;

	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 门店编码列表
	 */
	private List<String> axpList;
	/**
	 *学校编码列表
	 */
	private List<String> collegeList;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 快递公司编码（必填）
	 */
	private String expressCompanyId;
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	private String verifyCode;

	public UserEntity() {
	}

	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public Byte getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Byte verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public Byte getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Byte beDeleted) {
		this.beDeleted = beDeleted;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Byte getAge() {
		return age;
	}

	public void setAge(Byte age) {
		this.age = age;
	}


	public Date getSignupTime() {
		return signupTime;
	}

	public void setSignupTime(Date signupTime) {
		this.signupTime = signupTime;
	}

	public String getSignupIp() {
		return signupIp;
	}

	public void setSignupIp(String signupIp) {
		this.signupIp = signupIp;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Byte getBeEnabled() {
		return beEnabled;
	}

	public void setBeEnabled(Byte beEnabled) {
		this.beEnabled = beEnabled;
	}

	public Byte getBeSupervisor() {
		return beSupervisor;
	}

	public void setBeSupervisor(Byte beSupervisor) {
		this.beSupervisor = beSupervisor;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getNewPhone() {
		return newPhone;
	}

	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}

	public String getChangePnMethod() {
		return changePnMethod;
	}

	public void setChangePnMethod(String changePnMethod) {
		this.changePnMethod = changePnMethod;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}


	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}


	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}


	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getIdCardHandPic() {
		return idCardHandPic;
	}

	public void setIdCardHandPic(String idCardHandPic) {
		this.idCardHandPic = idCardHandPic;
	}

	public String getIdCardPic() {
		return idCardPic;
	}

	public void setIdCardPic(String idCardPic) {
		this.idCardPic = idCardPic;
	}

	public String getCredentialsPic() {
		return credentialsPic;
	}

	public void setCredentialsPic(String credentialsPic) {
		this.credentialsPic = credentialsPic;
	}

	public List<String> getAxpList() {
		return axpList;
	}

	public void setAxpList(List<String> axpList) {
		this.axpList = axpList;
	}

	public List<String> getCollegeList() {
		return collegeList;
	}

	public void setCollegeList(List<String> collegeList) {
		this.collegeList = collegeList;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(String expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

	public String getVerifyMark() {
		return verifyMark;
	}

	public void setVerifyMark(String verifyMark) {
		this.verifyMark = verifyMark;
	}

	@Override
	public String toString() {
		return "UserEntity{" +
				"userId=" + userId +
				", nickname='" + nickname + '\'' +
				", userName='" + userName + '\'' +
				", accountPwd='" + accountPwd + '\'' +
				", verifyStatus=" + verifyStatus +
				", phone='" + phone + '\'' +
				", gender='" + gender + '\'' +
				", age=" + age +
				", countyId=" + countyId +
				", signupTime=" + signupTime +
				", signupIp='" + signupIp + '\'' +
				", icon='" + icon + '\'' +
				", beDeleted=" + beDeleted +
				", userTypeId=" + userTypeId +
				", userType=" + userType +
				", beEnabled=" + beEnabled +
				", beSupervisor=" + beSupervisor +
				", storeId=" + storeId +
				", identityNumber='" + identityNumber + '\'' +
				", idCardHandPic='" + idCardHandPic + '\'' +
				", idCardPic='" + idCardPic + '\'' +
				", credentialsPic='" + credentialsPic + '\'' +
				", newPhone='" + newPhone + '\'' +
				", newPwd='" + newPwd + '\'' +
				", changePnMethod='" + changePnMethod + '\'' +
				", verifycode='" + verifycode + '\'' +
				", imei='" + imei + '\'' +
				", code='" + code + '\'' +
				", codeType='" + codeType + '\'' +
				", token='" + token + '\'' +
				", appid='" + appid + '\'' +
				", format='" + format + '\'' +
				", version='" + version + '\'' +
				", timestamp='" + timestamp + '\'' +
				", sign='" + sign + '\'' +
				", location='" + location + '\'' +
				", storeCode='" + storeCode + '\'' +
				", storeName='" + storeName + '\'' +
				", type=" + type +
				", axpList=" + axpList +
				", collegeList=" + collegeList +
				", realName='" + realName + '\'' +
				", expressCompanyId='" + expressCompanyId + '\'' +
				", verifyMark='" + verifyMark + '\'' +
				", verifyCode='" + verifyCode + '\'' +
				'}';
	}
}
