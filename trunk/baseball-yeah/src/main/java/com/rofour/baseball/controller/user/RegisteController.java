package com.rofour.baseball.controller.user;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.common.AES;
import com.rofour.baseball.common.AxpUtils;
import com.rofour.baseball.common.ConstantFunction;
import com.rofour.baseball.common.IdcardValidator;
import com.rofour.baseball.common.IpUtils;
import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectUserInfo;
import com.rofour.baseball.controller.model.UserInfo;
import com.rofour.baseball.controller.model.user.ExpUserAuditInfo;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.user.UserService;

/**
 * 注册
 * @author wangyu
 * @ClassName:com.rofour.baseball.acl.usercenter.RegisteController
 * @Description: 描述：
 * @date 2016/5/4 15:25
 */
@Controller
@RequestMapping(value = "/user/exp", method = RequestMethod.POST)
public class RegisteController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisteController.class);
	/**
	 * 执行结果标识
	 */
	private static final int EXECUTE_RESULT_FLAG = 0;
	/**
	 * @Fields userService : 注入用户中心服务
	 */
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("messageSource")
	private MessageSource messageSource;
//	/**
//	 * @Fields redisTemplate : 注入redis
//	 */
//	@Autowired
//	@Qualifier("redisTemplate")
//	private RedisTemplate<String, SelectUserInfo> redisTemplate;
	/**
	 * 注册
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/register")
	@ResponseBody
	public ResultInfo expRegiste(HttpServletRequest request) throws IOException {
		String data = StringUtils.trim(request.getParameter("data"));
		String timestamp = StringUtils.trim(request.getParameter("timestamp"));
		LOGGER.debug("expRegiste data:{}，timestamp：{}",data,timestamp);
		if (StringUtils.isEmpty(data)) {
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}
		try {
			UserInfo userInfo = JsonUtils.readValue(data, UserInfo.class);
			if (null == userInfo || StringUtils.isBlank(userInfo.getAccountPwd()) || StringUtils.isBlank(userInfo.getCode())) {
				return new ResultInfo(-1, "111", "参数不能为空");
			}
			// AES解密用户名和密码
			userInfo.setPhone(AES.desEncrypt(userInfo.getPhone(), timestamp)
					.trim());
			userInfo.setAccountPwd(AES.desEncrypt(userInfo.getAccountPwd(),
					timestamp).trim());
			// 密码加密
			dataHand(request,userInfo);
			userService.expRegiste(userInfo);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return processException(e, LOGGER);
		}
		return new ResultInfo(0, "", "注册成功！");
	}

	/**
	 * 完善负责人信息
	 * @return
	 */
	@RequestMapping(value = "/headprefectinfo")
	@ResponseBody
	public ResultInfo headprefectinfo(HttpServletRequest request){
		String data = StringUtils.trim(request.getParameter("data"));
		String token = StringUtils.trim(request.getParameter("token"));
		LOGGER.debug("registe data:{}",data);
		if (StringUtils.isEmpty(data) || StringUtils.isBlank(token)) {
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}
		try {
			UserInfo userInfo = JsonUtils.readValue(data, UserInfo.class);
			ResultInfo result = checkOfficerParam(userInfo);
			if(null != result){
				return result;
			}
			userInfo.setToken(token);
			userService.headPrefectInfo(userInfo);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return processException(e, LOGGER);
		}
		return new ResultInfo(0, "", "完善负责人信息成功！");
	}
	/**
	 * 完善员工信息
	 * @return
	 */
	@RequestMapping(value = "/empprefectinfo")
	@ResponseBody
	public ResultInfo empprefectinfo(HttpServletRequest request){
		String data = StringUtils.trim(request.getParameter("data"));
		String token = StringUtils.trim(request.getParameter("token"));
		LOGGER.debug("registe data:{}",data);
		if (StringUtils.isEmpty(data)|| StringUtils.isBlank(token)) {
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}
		try {
			UserInfo userInfo = JsonUtils.readValue(data, UserInfo.class);
			ResultInfo result = checkEmployeeParam(userInfo);
			if(null != result){
				return result;
			}
			userInfo.setToken(token);
			userService.empPrefectInfo(userInfo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return processException(e, LOGGER);
		}
		return new ResultInfo(0, "", "完善员工信息成功！");
	}
	/**
	 * 用户审核
	 * @return
	 */
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo audit(HttpServletRequest request,ExpUserAuditInfo userAuditInfo){
		try {
			ResultInfo result = checkAuditParam(userAuditInfo);
			if(null != result){
				return result;
			}
			UserManagerLoginBean user=	(UserManagerLoginBean) request.getSession().getAttribute("user");
			userAuditInfo.setVerifyUserId(user.getUserManagerId());
			return userService.expAudit(userAuditInfo,request);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return processException(e, LOGGER);
		}
	}
	/**
	 * 负责人审核信息
	 * @return
	 */
	@RequestMapping(value = "/getauditinfo")
	@ResponseBody
	public ResultInfo getAudit(HttpServletRequest request, UserInfo userInfo){
		try {
			return userService.getAudit(userInfo.getUserId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		return new ResultInfo(-1, "", "查询负责人审核信息失败！");
	}
	/**
	 * @param request
	 * @param userInfo 参数
	 * @author ZhangLei
	 * @Description: 把额外参数封装到userInfo中
	 * @date 2016年3月22日 下午1:31:05
	 **/
	private void dataHand(HttpServletRequest request, UserInfo userInfo) {
		// 设置用户名为手机号码
		userInfo.setUserName(userInfo.getPhone());
		// 设置注册IP地址
		userInfo.setSignupIp(IpUtils.getIpAddr(request));
		// 设置注册时间
		userInfo.setSignupTime(new Timestamp(System.currentTimeMillis()));
		// 设置账户状态为可用
		userInfo.setVerifyStatus((byte) 0);
		// 性别为空，默认为男
		if (StringUtils.isBlank(userInfo.getGender())) {
			userInfo.setGender(ConstantFunction.getGenderMale());
			// 1为男
		} else if (userInfo.getGender().equals("1")) {
			userInfo.setGender(ConstantFunction.getGenderMale());
			// 2为女
		} else if (userInfo.getGender().equals("2")) {
			userInfo.setGender(ConstantFunction.getGenderFemale());
			// 其他情况为男
		} else {
			userInfo.setGender(ConstantFunction.getGenderMale());
		}
		// 用户状态为未注销
		userInfo.setBeDeleted((byte) 0);
		// 用户类型中字段为可用
		userInfo.setBeEnabled((byte) 0);
	}

	/**
	 * 负责人完善资料参数判断
	 * @param userEntity
	 * @return
	 */
	private ResultInfo checkOfficerParam(UserInfo userEntity){
		if(null == userEntity){
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}else if(StringUtils.isBlank(userEntity.getStoreCode()) || StringUtils.isBlank(userEntity.getStoreName()) || userEntity.getExpressCompanyId() == null ||userEntity.getExpressCompanyId() <= 0){
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}
		else if(StringUtils.isBlank(userEntity.getRealName()))
		{
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}
		else if(!userEntity.getRealName().matches("^[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*$"))
		{
			return new ResultInfo(-1, "111", "真实姓名不合法");
		}
		else if(!IdcardValidator.isValid(userEntity.getIdentityNumber())){
			return new ResultInfo(-1, "111", "身份证号码不合法");
		}

		return null;
	}
	/**
	 * 员工完善资料参数判断
	 * @param userEntity
	 * @return
	 */
	private ResultInfo checkEmployeeParam(UserInfo userEntity){
		if(null == userEntity){
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}else if(StringUtils.isBlank(userEntity.getRealName()) || StringUtils.isBlank(userEntity.getHeadPhone()) || StringUtils.isBlank(userEntity.getCode())){
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}
		return null;
	}

	/**
	 * 负责人审核参数校验
	 * @param userAuditInfo
	 * @return
	 */
	private ResultInfo checkAuditParam(ExpUserAuditInfo userAuditInfo){
		if(null == userAuditInfo){
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}else if(null == userAuditInfo.getUserId() || null == userAuditInfo.getVerifyStatus()){
			return new ResultInfo(-1, "111", "传入参数不能为空");
		}
		return null;
	}



}
