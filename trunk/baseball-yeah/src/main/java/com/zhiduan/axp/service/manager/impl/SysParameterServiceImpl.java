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
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.SysParameterInfo;
import com.zhiduan.axp.dao.manager.bean.SysParameterBean;
import com.zhiduan.axp.dao.manager.mapper.SysParameterMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.SysParameterService;

/**
 * @ClassName: TbSysParameterServiceImpl
 * @Description: 管理中心--系统参数操作实现类
 * @author xl
 * @date 2016年3月26日 下午1:21:10
 *
 */
@Service("tbSysParameterService")
public class SysParameterServiceImpl implements SysParameterService {

	@Autowired
	@Qualifier("tbSysParameterMapper")
	private SysParameterMapper tbSysParameterMapper;
    @Autowired
    private WebUtils webUtils;
	/**
	 * @Description:删除一个系统参数
	 * @param sysParameterId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SysParameterService#deleteByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int deleteByPrimaryKey(Long sysParameterId,HttpServletRequest request) {
		if(sysParameterId == null){
			throw new BusinessException("111");
		}
		int result = tbSysParameterMapper.deleteByPrimaryKey(sysParameterId);
		webUtils.userDeleteLog(request, 29, sysParameterId);
		if(result == 0){
			throw new BusinessException("01016");
		}
		return result;
	}

	/**
	 * @Description: 插入一个系统参数
	 * @param record
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SysParameterService#insert(com.zhiduan.axp.idl.managecenter.service.entity.SysParameterInfo)
	 */
	@Override
	public ResultInfo insert(SysParameterInfo record,HttpServletRequest request) {
		validateSysPara(record);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parameterName", record.getParameterName());
		int rtn = tbSysParameterMapper.isSysParaNameExists(map);
		if (rtn > 0) {
			throw new BusinessException("01017");
		}
		SysParameterBean parameterBean = new SysParameterBean();
		BeanUtils.copyProperties(record, parameterBean);
		tbSysParameterMapper.insert(parameterBean);
		webUtils.userAddLog(request, 29, parameterBean);
		return new ResultInfo(0, "", "添加成功");
	}

	/**
	 * @Description: 查询系统参数
	 * @param sysParameterId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SysParameterService#selectByPrimaryKey(java.lang.Long)
	 */
	@Override
	public SysParameterInfo selectByPrimaryKey(Long sysParameterId) {
		if (sysParameterId == null) {
			throw new BusinessException("111");
		}
		SysParameterBean parameterBean = tbSysParameterMapper.selectByPrimaryKey(sysParameterId);
		if (parameterBean == null) {
			throw new BusinessException("01016");
		} else {
			SysParameterInfo parameter = new SysParameterInfo();
			BeanUtils.copyProperties(parameterBean, parameter);
			return parameter;
		}
	}

	/**
	 * @Description: 更新一个系统参数
	 * @param record
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SysParameterService#updateByPrimaryKey(com.zhiduan.axp.idl.managecenter.service.entity.SysParameterInfo)
	 */
	@Override
	public ResultInfo updateByPrimaryKey(SysParameterInfo record,HttpServletRequest request) {
		validateSysPara(record);
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parameterName", record.getParameterName());
		map.put("sysParameterId", record.getSysParameterId());
		int rtn = tbSysParameterMapper.isSysParaNameExists(map);
		if(rtn > 0){
			throw new BusinessException("01017");
		}
		SysParameterBean parameterBean = new SysParameterBean();
		SysParameterBean oldBean =tbSysParameterMapper.selectByPrimaryKey(record.getSysParameterId());
		BeanUtils.copyProperties(record, parameterBean);
		rtn = tbSysParameterMapper.updateByPrimaryKey(parameterBean);
		webUtils.userEditLog(request, 29, oldBean, parameterBean);
		if(rtn == 0){
			throw new BusinessException("01016");
		}
		return new ResultInfo(0, "", "更新成功");
	}

	/**
	 * @Description: 查询所有系统参数
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SysParameterService#selectAll()
	 */
	@Override
	public List<SysParameterInfo> selectAll() {
		List<SysParameterBean> list = tbSysParameterMapper.selectAll();
		if(list == null || list .isEmpty()){
			throw new BusinessException("01016");
		}
		List<SysParameterInfo> datalist = new ArrayList<SysParameterInfo>();
		for (SysParameterBean item : list) {
			SysParameterInfo parameter = new SysParameterInfo();
			BeanUtils.copyProperties(item, parameter);
			datalist.add(parameter);
		}
		return datalist;
	}

	/**
	 * @Description:根据名称查询系统参数
	 * @param paraName
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SysParameterService#selectByParaName(java.lang.String)
	 */
	@Override
	public SysParameterInfo selectByParaName(String paraName) {
		if(StringUtils.isBlank(paraName)){
			throw new BusinessException("111");
		}
		SysParameterBean paraBean = tbSysParameterMapper.selectByParaName(paraName);
		if(paraBean == null){
			throw new BusinessException("01016");
		}else{
			SysParameterInfo paraInfo = new SysParameterInfo();
			BeanUtils.copyProperties(paraBean, paraInfo);
			return paraInfo;
		}
	}

	/**
	 * 
	 * @Description: 参数校验方法
	 * @param parameter
	 */
	private void validateSysPara(SysParameterInfo parameter) {
		if (StringUtils.isBlank(parameter.getParameterName())
				|| StringUtils.isBlank(parameter.getValue())) {
			throw new BusinessException("111");
		}
		if(parameter.getParameterName().length() > 30
				|| parameter.getValue().length() > 1000
				|| (parameter.getDescription() != null && parameter.getDescription().length() > 500)
				|| (parameter.getBeEnabled() != null && parameter.getBeEnabled().toString().length() > 1)){
			throw new BusinessException("112");
		}
	}
}
