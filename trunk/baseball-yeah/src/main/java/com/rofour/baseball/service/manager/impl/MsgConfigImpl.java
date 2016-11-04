/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.service.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.common.Assert;
import com.rofour.baseball.common.WebUtils;
import com.rofour.baseball.controller.model.manager.MsgConfigInfo;
import com.rofour.baseball.dao.manager.bean.MsgConfigBean;
import com.rofour.baseball.dao.manager.mapper.MsgConfigMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.MsgConfigService;

/**
 * @ClassName: MsgConfigImpl
 * @Description: 消息配置方法实现类
 * @author xzy
 * @date 2016年3月28日 上午10:11:17
 *
 */
@Service("msgConfigService")
public class MsgConfigImpl implements MsgConfigService {
	@Autowired
	@Qualifier("msgConfigMapper")
	private MsgConfigMapper dao;
	@Autowired
    private WebUtils webUtils;
	/**
	 * @Description: 添加消息配置
	 * @param msgconfig
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.MsgConfigService#addMsgConfig(com.rofour.baseball.idl.managecenter.service.entity.MsgConfigInfo)
	 */
	public int addMsgConfig(MsgConfigInfo msgconfig,HttpServletRequest request) {
		checkMsgConfig(msgconfig);
		MsgConfigBean msgConfigBean = new MsgConfigBean();
		BeanUtils.copyProperties(msgconfig, msgConfigBean);
		int result = dao.insert(msgConfigBean);
		if(result==0){
			throw new BusinessException("01039");
		}
		webUtils.userAddLog(request, 19, msgConfigBean);
		return result;
	}

	/**
	 * @Description: 删除消息配置
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.MsgConfigService#deleteMsgConfig(com.rofour.baseball.idl.managecenter.service.entity.MsgConfigInfo)
	 */
	public int deleteMsgConfig(Long id,HttpServletRequest request) {
		Assert.notNull(id,"111");
		MsgConfigBean origin = dao.selectByPrimaryKey(id);
		int result = dao.deleteByPrimaryKey(id);
		if (result == 0) {
			throw new BusinessException("01039");
		}
		webUtils.userDeleteLog(request, 19, origin);
		return result;
	}

	/**
	 * @Description: 更新消息配置
	 * @param msgconfig
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.MsgConfigService#updateMsgConfig(com.rofour.baseball.idl.managecenter.service.entity.MsgConfigInfo)
	 */
	public int updateMsgConfig(MsgConfigInfo msgconfig,HttpServletRequest request) {
		checkMsgConfig(msgconfig);
		MsgConfigBean msgConfigBean = new MsgConfigBean();
		BeanUtils.copyProperties(msgconfig, msgConfigBean);
		MsgConfigBean origin = dao.selectByPrimaryKey(msgconfig.getMessageConfigId());
		int result = dao.updateByPrimaryKey(msgConfigBean);
		if (result == 0) {
			throw new BusinessException("01039");
		}
		webUtils.userEditLog(request, 19, origin,msgConfigBean);
		return result;
	}

	/**
	 * @Description: 查询消息配置
	 * @param msgconfig
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.MsgConfigService#selectMsgConfig(com.rofour.baseball.idl.managecenter.service.entity.MsgConfigInfo)
	 */
	public MsgConfigInfo selectMsgConfig(MsgConfigInfo msgconfig) {
		Assert.isNull(msgconfig.getMessageConfigId(),"111");
		MsgConfigBean msgConfigBean = new MsgConfigBean();
		MsgConfigInfo result = null;
		BeanUtils.copyProperties(msgconfig, msgConfigBean);
		MsgConfigBean back = dao.selectByPrimaryKey(msgConfigBean.getMessageConfigId());
		if (back == null) {
			throw new BusinessException("01039");
		} else {
			result = new MsgConfigInfo();
			BeanUtils.copyProperties(back, result);
		}
		return result;
	}

	/**
	 * @Description: 查询所有消息配置
	 * @see com.rofour.baseball.idl.managecenter.service.MsgConfigService#selectAll()
	 */
	public List<MsgConfigInfo> selectAll(MsgConfigInfo info) {
		List<MsgConfigInfo> result = new ArrayList<MsgConfigInfo>();
		for (MsgConfigBean msgConfigBean : dao.selectAll(info)) {
			MsgConfigInfo back = new MsgConfigInfo();
			BeanUtils.copyProperties(msgConfigBean, back);
			result.add(back);
		}
		return result;
	}

	/**
	 * 
	 * @Description: 校验
	 * @param msgconfig
	 * @return ResultInfo 操作结果bean
	 */
	private void checkMsgConfig(MsgConfigInfo msgconfig) {
		Pattern pattern = Pattern.compile("^[0|1]{1}$");
		if (StringUtils.isBlank(msgconfig.getMessageTypeId()) || msgconfig.getLevel() == null
				|| StringUtils.isBlank(msgconfig.getSendTypeId()) || msgconfig.getBeImmediateSend() == null
				|| msgconfig.getMaxLength() == null || msgconfig.getSendRoleId() == null
				|| StringUtils.isBlank(msgconfig.getExtendCode())) {
			throw new BusinessException("111");
		}
		if (msgconfig.getMessageTypeId().length() > 20 || msgconfig.getSendTypeId().length() > 20
				|| msgconfig.getExtendCode().length() > 10) {
			throw new BusinessException("112");
		}
		if (!pattern.matcher(msgconfig.getBeImmediateSend().toString()).find()) {
			throw new BusinessException("114");
		}
	}
	/**
	 * 
	 * @Description: 根据条件查询数量
	 * @param info
	 * @return 
	 * @see com.rofour.baseball.service.manager.MsgConfigService#getTotal(com.rofour.baseball.controller.model.manager.MsgConfigInfo)
	 */
	@Override
	public int getTotal(MsgConfigInfo info) {
		return dao.getTotal(info);
	}
}
