package com.rofour.baseball.controller.base;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rofour.baseball.dao.manager.bean.UserManagerLogBean;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.manager.UserManagerLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.exception.BusinessException;

public class BaseController{


	@PostConstruct
	public void init() {
		USER_LOGINOUT = new ResultInfo<Object>(-1, "100", getMessage("100"));
		CENTIFICATE_ERROR = new ResultInfo<Object>(-1, "101", getMessage("101"));
		UNKNOWN_ERROR = new ResultInfo<Object>(-1, "102", getMessage("102"));
		JSON_ERROR = new ResultInfo<Object>(-1, "103", getMessage("103"));
		NO_DATA = new ResultInfo<Object>(-1, "105", getMessage("105"));
	}

	@Autowired
	@Qualifier("messageSource")
	private MessageSource messageSource;

	@Autowired
	@Qualifier("userManagerLog")
	UserManagerLog userManagerLog;

	/**
	 * 用户未登录
	 */
	protected ResultInfo<Object> USER_LOGINOUT;
	/**
	 * 签名认证失败
	 */
	protected ResultInfo<Object> CENTIFICATE_ERROR;
	/**
	 * 服务器异常(超时,未找到服务等)
	 */
	protected ResultInfo<Object> UNKNOWN_ERROR;
	/**
	 * json解析失败
	 */
	protected ResultInfo<Object> JSON_ERROR;
	/**
	 * 没有此条记录
	 */
	protected ResultInfo<Object> NO_DATA;

	public String getMessage(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}

	/**
	 * 异常处理
	 * 
	 * @param e
	 * @param logger
	 * @return
	 */
	public ResultInfo<Object> processException(Exception e, Logger logger) {
		if (e instanceof BusinessException) {
            BusinessException ex = (BusinessException) e;
            String detail = getMessage(ex.getMessageCode());
            logger.error(ex.getMessageCode() + " - {}", detail,ex);
            return new ResultInfo<Object>(-1, ex.getMessageCode(), detail);
        } else if (e instanceof JsonProcessingException) {
            logger.error(e.getMessage(), e);
            return JSON_ERROR;
        } else {
            logger.error(e.getMessage(), e);
            return UNKNOWN_ERROR;
        }
	}

	/**
	 * 获取当前系统用户
	 * @param request
	 * @return
     */
	public UserManagerLoginBean getCurrent(HttpServletRequest request){
		return  (UserManagerLoginBean) request.getSession().getAttribute("user");
	}

	/**
	 * 添加系统日志 放弃（日志统一在实现类中处理）
	 * @param request
	 * @param remark
	 * @param tableName
	 * @param operationType
     */
	public void userLog(HttpServletRequest request, String remark, String tableName, String operationType) {
		UserManagerLoginBean userManagerLoginBean = getCurrent(request);
		UserManagerLogBean model = new UserManagerLogBean();
		model.setRemark(remark);
		model.setTableName(tableName);
		model.setOperationTime(new Date());
		model.setOperationType(operationType);
		model.setUserManagerId(userManagerLoginBean.getUserManagerId());
		model.setUserName(userManagerLoginBean.getUserName());
		userManagerLog.insert(model);
	}
	 /**
     * 
     * @Description: 用于service层直接返回resultInfo时,直接返回code,这样可以国际化
     * @param result
     * @return
     */
    public ResultInfo processResultInfo(ResultInfo result){
    	if(!StringUtils.isBlank(result.getCode())
    				&& StringUtils.isBlank(result.getMessage())){
    		result.setMessage(getMessage(result.getCode()));
    	}
    	return result;
    }

	/**
	 * json 序列化输出
	 * @param grid
	 * @param response
     */
	public void writeJson(Object grid,HttpServletResponse response) {
		try {
//			JsonUtils.LongToStringSerializer
			String json = JsonUtils.translateToJson(grid);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

