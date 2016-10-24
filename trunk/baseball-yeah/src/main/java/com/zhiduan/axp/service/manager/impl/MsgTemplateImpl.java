/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.common.WebUtils;
import com.zhiduan.axp.controller.model.manager.MsgTemplateInfo;
import com.zhiduan.axp.dao.manager.bean.MsgTemplateBean;
import com.zhiduan.axp.dao.manager.mapper.MsgTemplateMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.MsgTemplateService;

/**
 * @ClassName: MsgTemplateImpl
 * @Description: 消息类型接口实现类
 * @author xzy
 * @date 2016年3月31日 下午4:55:12
 *
 */
@Service("msgTemplateService")
public class MsgTemplateImpl implements MsgTemplateService {

	@Autowired
	@Qualifier("msgTemplateMapper")
	private MsgTemplateMapper dao;
    @Autowired
    private WebUtils webUtils;
	/**
	 * @Description: 添加消息类型
	 * @param msg
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.MsgTemplateService#addTemplate(com.zhiduan.axp.idl.managecenter.service.entity.MsgTemplateInfo)
	 */
	@Override
	public int addTemplate(MsgTemplateInfo msg, HttpServletRequest request) {
		checkMsgTemplate(msg);
		int existTemplateId = dao.existTemplateId(msg.getMessageTemplateId());
		if(existTemplateId > 0){
			throw new BusinessException("01001");
		}
		Map<String,String> map = new HashMap<>();
		map.put("name", msg.getMessageTemplateName());
		int existTemplateName = dao.existTemplateName(map);
		if(existTemplateName > 0){
			throw new BusinessException("01002");
		}
		MsgTemplateBean msgTemplateBean = new MsgTemplateBean();
		BeanUtils.copyProperties(msg, msgTemplateBean);
		int result = dao.insert(msgTemplateBean);
		 webUtils.userAddLog(request, 10, msgTemplateBean);
		return result;
	}

	/**
	 * @Description:删除消息类型
	 * @param msg
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.MsgTemplateService#deleteTemplate(com.zhiduan.axp.idl.managecenter.service.entity.MsgTemplateInfo)
	 */
	@Override
	public int deleteTemplate(MsgTemplateInfo msg, HttpServletRequest request) {
		int result = dao.deleteTemplate(msg);
		if(result == 0){
			throw new BusinessException("01003");
		}
		 webUtils.userDeleteLog(request, 10, msg);
		return result;
	}

	/**
	 * @Description: 更新消息类型
	 * @param msg
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.MsgTemplateService#updateTemplate(com.zhiduan.axp.idl.managecenter.service.entity.MsgTemplateInfo)
	 */
	@Override
	public int updateTemplate(MsgTemplateInfo msg, HttpServletRequest request) {
		checkMsgTemplate(msg);
		Map<String,String> map = new HashMap<>();
		map.put("name", msg.getMessageTemplateName());
		map.put("id", msg.getMessageTemplateId());
		int existTemplateName = dao.existTemplateName(map);
		if(existTemplateName > 0){
			throw new BusinessException("01002");
		}
		MsgTemplateBean msgTemplateBean = new MsgTemplateBean();
		BeanUtils.copyProperties(msg, msgTemplateBean);
		MsgTemplateBean msgTemplateBeanPast=dao.selectByPrimaryKey(msgTemplateBean.getMessageTemplateId());
		int	result = dao.updateByPrimaryKey(msgTemplateBean);
		if(result == 0){
			throw new BusinessException("01003");
		}
		webUtils.userEditLog(request, 10, msgTemplateBeanPast,msgTemplateBean);
		return result;
	}

	/**
	 * @Description: 查询消息类型
	 * @param msg
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.MsgTemplateService#selectTemplate(com.zhiduan.axp.idl.managecenter.service.entity.MsgTemplateInfo)
	 */
	@Override
	public MsgTemplateInfo selectTemplate(MsgTemplateInfo msg) {
		if(StringUtils.isBlank(msg.getMessageTemplateId())){
			throw new BusinessException("111");
		}
		MsgTemplateInfo result = null;
		MsgTemplateBean back = dao.selectByPrimaryKey(msg.getMessageTemplateId());
		if (back == null) {
			throw new BusinessException("01003");
		} else {
			result = new MsgTemplateInfo();
			BeanUtils.copyProperties(back, result);
		}
		return result;
	}

	/**
	 * @Description: 查询所有消息类型
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.MsgTemplateService#selectAll()
	 */
	@Override
	public List<MsgTemplateInfo> selectAll(MsgTemplateInfo 	msgTemplateInfo) {

				
		return dao.selectAll(msgTemplateInfo);
	}
	
	/**
	 * 
	 * @Description: 校验
	 * @param msgtemplate
	 */
	private void checkMsgTemplate(MsgTemplateInfo msgtemplate) {
		if (StringUtils.isBlank(msgtemplate.getMessageTemplateId())
				|| StringUtils.isBlank(msgtemplate.getMessageTemplateName())
				|| StringUtils.isBlank(msgtemplate.getMessageTemplate())) {
			throw new BusinessException("111");
		} 
		if (msgtemplate.getMessageTemplateId().length() > 20
				|| msgtemplate.getMessageTemplateName().length() > 50
				|| msgtemplate.getMessageTemplate().length() > 200) {
			throw new BusinessException("112");
		} 
	}

	@Override
	public Integer getMsgTemplateTotal(MsgTemplateInfo msgtemplate) {
		return dao.getMsgTemplateTotal(msgtemplate);
	}
}
