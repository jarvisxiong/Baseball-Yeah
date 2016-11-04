package com.rofour.baseball.common;




public class ConstantFunction {
	/**
	 * @Fields RANDOM_CODE_TIME_OUT : 验证码超时时间
	 */

	private static final String RANDOM_CODE_TIMEOUT = "sms_timeout";

	/**
	 * @Fields MSG_TYPE_REGISTER : 注册消息类型
	 */

	private static final String MSG_TYPE_REGISTER = "register";

	public static String getRandomCodeTimeout() {
		return RANDOM_CODE_TIMEOUT;
	}

	/**
	 * @Fields MSG_TYPE_RESETPWD : 重置密码消息类型
	 */

	private static final String MSG_TYPE_RESETPWD = "resetpwd";

	public static String getMsgTypeBindingWeixin() {
		return MSG_TYPE_BINDING_WEIXIN;
	}

	/**
	 * @Fields MSG_TYPE_CHANGEPHONE : 修改手机号码消息类型
	 */
	private static final String MSG_TYPE_CHANGEPHONE = "changephone";

	/**
	 * 微信与手机号码绑定验证码类型。
	 */
	private static final String MSG_TYPE_BINDING_WEIXIN = "bindingweixin";

	/**
	 * @Fields SEND_ROLE_SYSTEM : 消息发送角色:系统
	 */

	private static final String SEND_ROLE_SYSTEM = "p_sendrole_sys";

	/**
	 * @Fields SEND_ROLE_USER :消息发送角色:用户
	 */

	private static final String SEND_ROLE_USER = "p_sendrole_user";

	/**
	 * @Fields SEND_TYPE_AXP : 消息发送类型：AXP推送
	 */

	private static final String SEND_TYPE_AXP = "p_sendtype_axp";

	/**
	 * @Fields SEND_TYPE_SMS : 消息发送类型：短信
	 */

	private static final String SEND_TYPE_SMS = "p_sendtype_sms";

	/**
	 * @Fields SEND_TYPE_WX : 消息发送类型：微信推送
	 */

	private static final String SEND_TYPE_WX = "p_sendtype_wx";

	/**
	 * @Fields MSG_TYPE_CHANGEPHONE : 消息通知类型:校验通知
	 */

	private static final String MESSAGE_TYPE_CHECK = "p_noticetype_check";

	/**
	 * @Fields MSG_TYPE_CHANGEPHONE : 消息通知类型:到件通知
	 */

	private static final String MESSAGE_TYPE_COMSCAN = "p_noticetype_come";

	/**
	 * @Fields SCAN_TYPE_COME : 扫描类型:到件扫描
	 */

	private static final String SCAN_TYPE_COME = "p_scan_come";

	/**
	 * @Fields SCAN_TYPE_DISP : 扫描类型:派件扫描
	 */

	private static final String SCAN_TYPE_DISP = "p_scan_disp";

	/**
	 * @Fields SCAN_TYPE_SIGN : 扫描类型:签收扫描
	 */

	private static final String SCAN_TYPE_SIGN = "p_scan_sign";

	/**
	 * @Fields SCAN_TYPE_SIGN : 签收类型:拍照签收
	 */

	private static final String SIGN_TYPE_IMG = "p_signtype_img";
	/**
	 * @Fields SCAN_TYPE_SIGN : 签收类型:他人代收
	 */

	private static final String SIGN_TYPE_OTHER = "p_signtype_other";
	/**
	 * @Fields SCAN_TYPE_SIGN : 签收类型:本人签收
	 */

	private static final String SIGN_TYPE_SELF = "p_signtype_self";
	/**
	 * 证件类型 身份证照
	 */
	private static final String CREDENTIAL_TYPE_ID = "idcard";
	/**
	 * 证件类型  手持 身份证照
	 */
	private static final String CREDENTIAL_TYPE_ID_HOLD = "handheld";
	/**
	 * 证件类型  其他的一些,如营业合照,店面照片等
	 */
	private static final String CREDENTIAL_TYPE_OTHER = "other";
	/**
	 * 跟站点相关,所使用的前缀,如站点认证的图片
	 */
	private static final String EXP = "exp";
	
	private static final String PLACE_HOLDER_WAYBILL = "<运单号>";
	private static final String PLACE_HOLDER_POSITION = "<货位号>";
	private static final String PLACE_HOLDER_COMPNAY = "<快递品牌>";
	
	/**
	 * @Fields OSS_SERVER_SIGNIMG : 附件服务器-服务器1
	 */
	    
	private static final String OSS_SERVER_PIC = "oss_server_pic";
	
	/**
	 * @Fields ATM_TYPE_SIGNIMG : 附件类型-签收图片
	 */
	    
	private static final String ATM_TYPE_SIGNIMG="atm_type_sign_img";
	/**
	 * @Fields ATM_TYPE_USERAUTH : 附件类型-用户认证需要的照片,身份证等
	 */
	    
	private static final String ATM_TYPE_USERAUTH="atm_type_user_auth";
	private static final String ATM_TYPE_AD="atm_type_ad";
	/**
	 * @Fields ATM_TYPE_SIGNIMG : 数据来源-PDA
	 */
	    
	private static final String SOUCE_PDA_PDA="p_souce_pda";

	/**
	 * @Fields ATM_TYPE_SIGNIMG : 数据来源-Android
	 */
	    
	private static final String SOURCE_ANDROID="p_source_android";

	/**
	 * @Fields ATM_TYPE_SIGNIMG : 数据来源-IOS
	 */
	    
	private static final String SOURCE_IOS="p_source_ios";

	/**
	 * @Fields ATM_TYPE_SIGNIMG : 数据来源-Other
	 */
	    
	private static final String SOURCE_OTHER="p_source_other";

	/**
	 * @Fields GENDER_PCODE : 数据来源-Web页面上传
	 */
	    
	private static final String SOURCE_WEB="p_source_web";
	
	
	/**
	 * @Fields GENDER_PCODE : 性别-男
	 */
	    
	private static final String GENDER_MALE="p_gender_male";
	
	
	
	/**
	 * @Fields ATM_TYPE_SIGNIMG : 性别-女
	 */
	    
	private static final String GENDER_FEMALE="p_gender_female";
	
	/**
	 * @Fields USER_SOURCE_PCODE : 用户来源-短信通知
	 */
	    
	private static final String USER_SOURCE_SMS="p_user_sms";
	
	/**
	 * @Fields USER_SOURCE_PCODE : 用户来源-微信关注
	 */
	    
	private static final String USER_SOURCE_WEIXIN="p_user_weixin";

    /**
     * @Fields USER_SOURCE_PCODE : 用户来源-微信关注
     */
    private static final String SEND_TYPE_PREFECT="prefectinfo";

	/**
	 * @Fields SMS_SIGN :短信签名
	 */
	    
	private static final String SMS_SIGN="【爱学派】";
	
	/**
	 * 短信签名
	 * @return
	 */
	public static String getSmsSign() {
		return SMS_SIGN;
	}


	/**
	 * @return 消息发送角色:系统
	 */

	public static String getSendRoleSystem() {
		return SEND_ROLE_SYSTEM;
	}


	/**
	 * @return 消息发送类型：AXP推送
	 */

	public static String getSendRoleUser() {
		return SEND_ROLE_USER;
	}

	/**
	 * @return 消息发送类型：AXP推送
	 */

	public static String getSendTypeAxp() {
		return SEND_TYPE_AXP;
	}

	/**
	 * @return 消息发送类型：短信
	 */

	public static String getSendTypeSms() {
		return SEND_TYPE_SMS;
	}

	/**
	 * @return 消息发送类型：微信推送
	 */

	public static String getSendTypeWx() {
		return SEND_TYPE_WX;
	}

	/**
	 * @return 消息通知类型:校验通知
	 */

	public static String getMessageTypeCheck() {
		return MESSAGE_TYPE_CHECK;
	}

	/**
	 * @return 消息通知类型:到件通知
	 */

	public static String getMessageTypeComscan() {
		return MESSAGE_TYPE_COMSCAN;
	}

	/**
	 * @Method: getMsgTypeRegister
	 * @Description: 注册消息类型
	 * @param @return
	 *            参数
	 * @return String 返回类型
	 * @throws @author
	 *             wjj
	 * @date 2016年3月31日 下午6:31:19
	 **/

	public static String getMsgTypeRegister() {
		return MSG_TYPE_REGISTER;
	}

	/**
	 * @Method: getMsgTypeResetpwd
	 * @Description: 重置密码消息类型
	 * @param @return
	 *            参数
	 * @return String 返回类型
	 * @throws @author
	 *             wjj
	 * @date 2016年3月31日 下午6:31:15
	 **/

	public static String getMsgTypeResetpwd() {
		return MSG_TYPE_RESETPWD;
	}

	/**
	 * @Method: getMsgTypeChangephone
	 * @Description: 修改手机号码消息类型
	 * @param @return
	 *            参数
	 * @return String 返回类型
	 * @throws @author
	 *             wjj
	 * @date 2016年3月31日 下午6:31:23
	 **/

	public static String getMsgTypeChangephone() {
		return MSG_TYPE_CHANGEPHONE;
	}

	/**
	 * @return 扫描类型:到件扫描
	 */

	public static String getScanTypeCome() {
		return SCAN_TYPE_COME;
	}

	/**
	 * @return 扫描类型:派件扫描
	 */

	public static String getScanTypeDisp() {
		return SCAN_TYPE_DISP;
	}

	/**
	 * @return 扫描类型:签收扫描
	 */

	public static String getScanTypeSign() {
		return SCAN_TYPE_SIGN;
	}

	/**
	 * @return 模板占位符:运单号
	 */
	public static String getPlaceHolderWaybill() {
		return PLACE_HOLDER_WAYBILL;
	}

	/**
	 * @return 模板占位符:货位号
	 */
	public static String getPlaceHolderPosition() {
		return PLACE_HOLDER_POSITION;
	}

	/**
	 * @return 模板占位符:品牌
	 */
	public static String getPlaceHolderCompnay() {
		return PLACE_HOLDER_COMPNAY;
	}

	/**
	 * @return 签收类型:拍照签收
	 */
	public static String getSignTypeImg() {
		return SIGN_TYPE_IMG;
	}

	/**
	 * @return 签收类型:他人代收
	 */
	public static String getSignTypeOther() {
		return SIGN_TYPE_OTHER;
	}

	/**
	 * @return 签收类型:本人签收
	 */
	public static String getSignTypeSelf() {
		return SIGN_TYPE_SELF;
	}

	/**
	 * @Method: getOssServerPic
	 * @Description: 阿里云图片服务器
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 * @author wjj
	 * @date 2016年4月4日 上午11:02:01 
	 **/
	    
	public static String getOssServerPic() {
		return OSS_SERVER_PIC;
	}

	/**
	 * @Method: getAtmTypeSignimg
	 * @Description: 附件类型-签收图片
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 * @author wjj
	 * @date 2016年4月4日 上午11:02:15 
	 **/
	    
	/**
	 * @return 消息发送角色:系统
	 */
	public static String getAtmTypeSignimg() {
		return ATM_TYPE_SIGNIMG;
	}

	/**
	 * @return 数据来源-PDA
	 */
	public static String getSoucePdaPda() {
		return SOUCE_PDA_PDA;
	}

	/**
	 * @return 数据来源-ANDROID
	 */
	public static String getSourceAndroid() {
		return SOURCE_ANDROID;
	}

	/**
	 * @return 数据来源-IOS
	 */
	public static String getSourceIos() {
		return SOURCE_IOS;
	}

	/**
	 * @return 数据来源-OTHER
	 */
	public static String getSourceOther() {
		return SOURCE_OTHER;
	}

	/**
	 * @return 数据来源-WEB上传
	 */
	public static String getSourceWeb() {
		return SOURCE_WEB;
	}


	/**
	 * @return 性别-男
	 */
	public static String getGenderMale() {
		return GENDER_MALE;
	}

	/**
	 * @return 性别-女
	 */
	public static String getGenderFemale() {
		return GENDER_FEMALE;
	}

	/**
	 * @Fields USER_SOURCE_PCODE : 用户来源-短信通知
	 */
	public static String getUserSourceSms() {
		return USER_SOURCE_SMS;
	}

	/**
	 * @Fields USER_SOURCE_PCODE : 用户来源-微信关注
	 */
	public static String getUserSourceWeixin() {
		return USER_SOURCE_WEIXIN;
	}
	/**
	 * @Description: 证件类型 身份证照
	 * @return
	 */
	public static String getCredentialTypeId() {
		return CREDENTIAL_TYPE_ID;
	}
	/**
	 * @Description: 证件类型  手持 身份证照
	 * @return
	 */
	public static String getCredentialTypeIdHold() {
		return CREDENTIAL_TYPE_ID_HOLD;
	}
	/**
	 * @Description: 证件类型  其他的一些,如营业合照,店面照片等
	 * @return
	 */
	public static String getCredentialTypeOther() {
		return CREDENTIAL_TYPE_OTHER;
	}
	/**
	 * @Description:附件类型-用户认证需要的照片,身份证等
	 * @return
	 */
	public static String getAtmTypeUserauth() {
		return ATM_TYPE_USERAUTH;
	}

	public static String getATM_TYPE_AD() {
		return ATM_TYPE_AD;
	}
	private static final String FOCUSPIC = "focuspic";
	public static String getFOCUSPIC() {
		return FOCUSPIC;
	}
	/**
	 * 跟站点相关,所使用的前缀,如站点认证的图片
	 */
	public static String getExp() {
		return EXP;
	}

    public static String getSendTypePrefect() {
        return SEND_TYPE_PREFECT;
    }
}