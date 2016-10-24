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
import com.zhiduan.axp.controller.model.manager.SmsVendorInfo;
import com.zhiduan.axp.dao.manager.bean.SmsVendorBean;
import com.zhiduan.axp.dao.manager.mapper.SmsVendorMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.SmsVendorService;

/**
 * @ClassName: TbSmsVendorServiceImpl
 * @Description: 管理中心--短信供应商操作实现类
 * @author xl
 * @date 2016年3月28日 上午9:55:01
 *
 */
@Service("smsVendorService")
public class SmsVendorServiceImpl implements SmsVendorService {

	@Autowired
	@Qualifier("smsVendorMapper")
	private SmsVendorMapper smsVendorMapper;
	@Autowired
	private WebUtils webUtils;

	/**
	 * @Description: 删除一个短信供应商
	 * @param smsVendorId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SmsVendorService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String smsVendorId,HttpServletRequest request) {
		if(StringUtils.isBlank(smsVendorId)){
			throw new BusinessException("111");
		}
		SmsVendorBean origin = smsVendorMapper.selectByPrimaryKey(smsVendorId);
		int result = smsVendorMapper.deleteByPrimaryKey(smsVendorId);
		if(result == 0){
			throw new BusinessException("01013");
		}
		webUtils.userDeleteLog(request, 18, origin);
		return result;
	}

	/**
	 * @Description: 添加一个短信供应商
	 * @param record
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SmsVendorService#insert(com.zhiduan.axp.idl.managecenter.service.entity.SmsVendorInfo)
	 */
	@Override
	public ResultInfo insert(SmsVendorInfo record,HttpServletRequest request) {

		validateInfo(record);
		SmsVendorBean bean = smsVendorMapper.selectByPrimaryKey(record.getSmsVendorId());
		if(bean != null){
			throw new BusinessException("01014");
		}
		SmsVendorBean smsVendorBean = new SmsVendorBean();
		BeanUtils.copyProperties(record, smsVendorBean);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smsVendorCode", smsVendorBean.getSmsVendorCode());
		map.put("smsVendorName", smsVendorBean.getSmsVendorName());
		map.put("loginName", smsVendorBean.getLoginName());
		if (smsVendorMapper.isSmsVendorExist(map) > 0) {
			throw new BusinessException("01014");
		}
		smsVendorMapper.insert(smsVendorBean);
		webUtils.userAddLog(request, 18, smsVendorBean);
		return new ResultInfo(0, "", "添加供应商信息成功");
	}

	/**
	 * @Description: 查询短信供应商
	 * @param smsVendorId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SmsVendorService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public SmsVendorInfo selectByPrimaryKey(String smsVendorId) {
		SmsVendorBean smsVendorBean = smsVendorMapper.selectByPrimaryKey(smsVendorId);
		SmsVendorInfo smsVendorInfo = new SmsVendorInfo();
		if (smsVendorBean != null) {
			BeanUtils.copyProperties(smsVendorBean, smsVendorInfo);
		}
		return smsVendorInfo;
	}

	/**
	 * @Description: 更新一个短信供应商
	 * @param record
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SmsVendorService#updateByPrimaryKey(com.zhiduan.axp.idl.managecenter.service.entity.SmsVendorInfo)
	 */
	@Override
	public ResultInfo updateByPrimaryKey(SmsVendorInfo record,HttpServletRequest request) {
		
		validateInfo(record);
		SmsVendorBean smsVendorBean = new SmsVendorBean();
		BeanUtils.copyProperties(record, smsVendorBean);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smsVendorCode", smsVendorBean.getSmsVendorCode());
		map.put("smsVendorName", smsVendorBean.getSmsVendorName());
		map.put("loginName", smsVendorBean.getLoginName());
		map.put("smsVendorId", smsVendorBean.getSmsVendorId());

		int rtn = smsVendorMapper.isSmsVendorExist(map);
		if (rtn > 0) {
			throw new BusinessException("01014");
		}
		SmsVendorBean origin = smsVendorMapper.selectByPrimaryKey(record.getSmsVendorId());
		rtn = smsVendorMapper.updateByPrimaryKey(smsVendorBean);
		if(rtn == 0){
			throw new BusinessException("01013");
		}
		webUtils.userEditLog(request, 18, origin,smsVendorBean);
		return new ResultInfo(0, "", "更新成功");
	}

	/**
	 * @Description: 查询所有短信供应商
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SmsVendorService#selectAll()
	 */
	@Override
	public List<SmsVendorInfo> selectAll(SmsVendorInfo info) {
		List<SmsVendorBean> list = smsVendorMapper.selectAll(info);
		if(list == null || list.isEmpty()){
			throw new BusinessException("01013");
		}
		List<SmsVendorInfo> dataList = new ArrayList<SmsVendorInfo>();
		for (SmsVendorBean item : list) {
			SmsVendorInfo smsVendorInfo = new SmsVendorInfo();
			BeanUtils.copyProperties(item, smsVendorInfo);
			dataList.add(smsVendorInfo);
		}
		return dataList;
	}
	/**
	 * 
	 * @Description: 根据条件查询总数
	 * @param info
	 * @return 
	 * @see com.zhiduan.axp.service.manager.SmsVendorService#getTotal(com.zhiduan.axp.controller.model.manager.SmsVendorInfo)
	 */
	@Override
	public int getTotal(SmsVendorInfo info) {
		return smsVendorMapper.getTotal(info);
	}
	/**
	 * @Description: 字段校验
	 * @param smsVendor
	 * @return
	 */

	private void validateInfo(SmsVendorInfo smsVendor) {
		
		if(StringUtils.isBlank(smsVendor.getSmsVendorId()) || StringUtils.isBlank(smsVendor.getSmsVendorCode())
				|| StringUtils.isBlank(smsVendor.getSmsVendorName()) || StringUtils.isBlank(smsVendor.getLoginName())
				|| StringUtils.isBlank(smsVendor.getPassword()) || smsVendor.getLevel() == null
				|| smsVendor.getWeight() == null || smsVendor.getThreshold() == null
				|| smsVendor.getStatus() == null || StringUtils.isBlank(smsVendor.getInterfaceAddress())){
			throw new BusinessException("111");
		}
		if (smsVendor.getSmsVendorCode().length() > 20 || smsVendor.getSmsVendorName().length() > 100
				|| smsVendor.getLoginName().length() > 30 ||smsVendor.getPassword().length() > 30
				|| smsVendor.getLevel().toString().length() > 1 || smsVendor.getStatus().toString().length() > 1
				|| (smsVendor.getBeDeleted()!=null && smsVendor.getBeDeleted().toString().length() > 1) 
				|| (smsVendor.getChannelCode()!=null && smsVendor.getChannelCode().length() > 30)
				|| smsVendor.getInterfaceAddress().length() > 50) {
			throw new BusinessException("112");
		}
		
	}

}
