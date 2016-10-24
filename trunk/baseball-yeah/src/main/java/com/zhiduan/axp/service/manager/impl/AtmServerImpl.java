/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.controller.model.manager.AtmServerInfo;
import com.zhiduan.axp.dao.manager.bean.AtmServerBean;
import com.zhiduan.axp.dao.manager.mapper.AtmServerMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.AtmServerService;

/**
 * @ClassName: AtmServerImpl
 * @Description: 附件服务器实现类
 * @author 周琦
 * @date 2016年4月19日 下午4:55:21
 */
@Service("atmServerService")
public class AtmServerImpl implements AtmServerService {

	@Autowired
	@Qualifier("atmServerMapper")
	private AtmServerMapper dao;

	/**
	 * @Description: 添加附件服务
	 * @param atmServer
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.AtmServerService#addAtmServer(com.zhiduan.axp.idl.managecenter.service.entity.AtmServerInfo)
	 */
	@Override
	public int addAtmServer(AtmServerInfo atmServer) {
		checkAtmServer(atmServer);
		if(atmServer.getBeEnabled() == null){
			atmServer.setBeEnabled((byte) 0);
		}
		AtmServerBean atmServerBean = new AtmServerBean();
		int result = 0;
		
		BeanUtils.copyProperties(atmServer, atmServerBean);
		result = dao.isAtmServerExists(atmServerBean);
		if (result != 0) {
			throw new BusinessException("01022");
		}
		result = dao.insert(atmServerBean);
		if (result != 0) {
			return result;
		} else {
			throw new BusinessException("01021");
		}

	}

	/**
	 * @Description: 删除附件服务
	 * @param atmServer
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.AtmServerService#deleteAtmServer(com.zhiduan.axp.idl.managecenter.service.entity.AtmServerInfo)
	 */
	@Override
	public int deleteAtmServer(AtmServerInfo atmServer) {
		
		if (StringUtils.isBlank(atmServer.getAttachmentServerId())) {
			throw new BusinessException("111");
		}
		AtmServerBean atmServerBean = new AtmServerBean();
		int result = 0;
		BeanUtils.copyProperties(atmServer, atmServerBean);
		result = dao.deleteByPrimaryKey(atmServerBean.getAttachmentServerId());
		if (result != 0) {
			return result;
		} else {
			throw new BusinessException("01021");
		}

	}

	/**
	 * @Description: 更新附件服务
	 * @param atmServer
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.AtmServerService#updateAtmServer(com.zhiduan.axp.idl.managecenter.service.entity.AtmServerInfo)
	 */
	@Override
	public int updateAtmServer(AtmServerInfo atmServer) {

		checkAtmServer(atmServer);
		if (atmServer.getBeEnabled() == null || atmServer.getBeEnabled().toString().equals("")) {
			throw new BusinessException("111");
		} else if (!atmServer.getBeEnabled().equals((byte) 0) && !atmServer.getBeEnabled().equals((byte) 1)) {
			throw new BusinessException("111");
		}
		AtmServerBean atmServerBean = new AtmServerBean();
		int result = 0;

		BeanUtils.copyProperties(atmServer, atmServerBean);
		result = dao.isAtmServerNameExists(atmServerBean);
		if (result != 0) {
			throw new BusinessException("01022");
		}
		result = dao.updateByPrimaryKey(atmServerBean);
		if (result != 0) {
			return result;
		} else {
			throw new BusinessException("01021");
		}

	}

	/**
	 * @Description: 查询附件服务
	 * @param atmServer
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.AtmServerService#selectAtmServer(com.zhiduan.axp.idl.managecenter.service.entity.AtmServerInfo)
	 */
	@Override
	public AtmServerInfo selectAtmServer(AtmServerInfo atmServer) {
		AtmServerInfo atmServerInfo = new AtmServerInfo();
		if (StringUtils.isBlank(atmServer.getAttachmentServerId())) {
			throw new BusinessException("111");
		}

		AtmServerBean back = dao.selectByPrimaryKey(atmServer.getAttachmentServerId());
		if (back != null) {
			BeanUtils.copyProperties(back, atmServerInfo);
			return atmServerInfo;
		} else {
			throw new BusinessException("01021");
		}

	}

	/**
	 * 查询所有附件服务
	 * 
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.AtmServerService#selectAll()
	 */
	@Override
	public List<AtmServerInfo> selectAll() {
		List<AtmServerInfo> result = new ArrayList<AtmServerInfo>();
		for (AtmServerBean atmServerBean : dao.selectAll()) {
			AtmServerInfo back = new AtmServerInfo();
			BeanUtils.copyProperties(atmServerBean, back);
			result.add(back);
		}
		if (result.isEmpty()) {
			throw new BusinessException("01021");
		} else {
			return result;
		}

	}

	/**
	 * @Description: 根据名称查询附件服务
	 * @param atmServer
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.AtmServerService#selectByName(com.zhiduan.axp.idl.managecenter.service.entity.AtmServerInfo)
	 */
	public AtmServerInfo selectByName(AtmServerInfo atmServer) {
		AtmServerBean atmServerBean = new AtmServerBean();
		AtmServerInfo result = new AtmServerInfo();

		BeanUtils.copyProperties(atmServer, atmServerBean);
		AtmServerBean back = dao.selectByName(atmServerBean.getAttachmentServerName());
		if (back == null) {
			BeanUtils.copyProperties(back, result);
			return result;
		} else {
			throw new BusinessException("01021");
		}

	}

	/**
	 * 
	 * @Description: 校验
	 * @param atmServer
	 * @return ResultInfo 操作结果bean
	 */	    
	private void checkAtmServer(AtmServerInfo atmServer) {
		if (StringUtils.isBlank(atmServer.getAttachmentServerId())
				|| StringUtils.isBlank(atmServer.getAttachmentServerName())
				|| StringUtils.isBlank(atmServer.getAccessKeyId()) || StringUtils.isBlank(atmServer.getEndpoint())
				|| StringUtils.isBlank(atmServer.getAccessKeySecret())
				|| StringUtils.isBlank(atmServer.getAccessUrl())) {
			throw new BusinessException("111");

		}
		if (atmServer.getAttachmentServerId().length() > 20 || atmServer.getAttachmentServerName().length() > 30
				|| atmServer.getEndpoint().length() > 50 || atmServer.getAccessKeyId().length() > 200
				|| atmServer.getAccessKeySecret().length() > 200 || atmServer.getAccessUrl().length() > 200) {
			throw new BusinessException("112");
		}

	}
}
