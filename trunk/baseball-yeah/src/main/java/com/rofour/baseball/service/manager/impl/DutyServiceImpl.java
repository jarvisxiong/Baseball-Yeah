/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.service.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.common.StringUtils;
import com.rofour.baseball.common.WebUtils;
import com.rofour.baseball.controller.model.manager.DutyInfo;
import com.rofour.baseball.controller.model.manager.RoleInfo;
import com.rofour.baseball.dao.manager.bean.DutyBean;
import com.rofour.baseball.dao.manager.mapper.DutyBeanMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.DutyService;

/**
 * @ClassName: DutyServiceImpl
 * @Description:
 * @author 高振
 * @date 2016年3月27日 下午4:48:38
 *
 */

@Service("dutyService")
public class DutyServiceImpl implements DutyService {

	@Autowired
	@Qualifier("dutyBeanMapper")
	private DutyBeanMapper dutyBeanMapper;

	@Autowired
	private WebUtils webUtils;

	/**
	 * @Description: 删除职位
	 * @param dutyId
	 * @param request
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.DutyService#deleteByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int deleteByPrimaryKey(Long dutyId, HttpServletRequest request) {
		int a = 0;
		a = dutyBeanMapper.deleteByPrimaryKey(dutyId);
		if (a != 0) {
			webUtils.userDeleteLog(request, 27, dutyId);
			return a;
		} else {
			throw new BusinessException("01033");
		}

	}

	/**
	 * @Description: 插入职位
	 * @param duty
	 * @param request
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.DutyService#insert(com.rofour.baseball.idl.managecenter.service.entity.DutyInfo)
	 */
	@Override
	public int insert(DutyInfo duty, HttpServletRequest request) {
		valiDate(duty);
		int a = 0;
		DutyBean record = new DutyBean();
		BeanUtils.copyProperties(duty, record);
		a = dutyBeanMapper.isDutyExist(record);
		if (a != 0) {
			throw new BusinessException("01032");
		}
		a = dutyBeanMapper.insert(record);
		if (a != 0) {
			webUtils.userAddLog(request, 27, record);
			return a;
		} else {
			throw new BusinessException("01033");
		}

	}

	/**
	 * @Description:查询职位信息
	 * @param dutyId
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.DutyService#selectByPrimaryKey(java.lang.Long)
	 */
	@Override
	public DutyInfo selectByPrimaryKey(Long dutyId) {
		DutyInfo tb = new DutyInfo();

		DutyBean tbDu = dutyBeanMapper.selectByPrimaryKey(dutyId);
		if (tbDu != null) {
			BeanUtils.copyProperties(tbDu, tb);
			return tb;
		} else {
			throw new BusinessException("01033");
		}

	}

	@Override
	public List<DutyInfo> getAllDuty() {
		List<DutyInfo> list = new ArrayList<DutyInfo>();

		List<DutyBean> dataList = dutyBeanMapper.getAllDupt();
		for (DutyBean record : dataList) {
			DutyInfo dutyInfo = new DutyInfo();
			BeanUtils.copyProperties(record, dutyInfo);
			list.add(dutyInfo);
		}

		return list;

	}

	/**
	 * @Description: 更新职位信息
	 * @param duty
	 * @param request
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.DutyService#updateByPrimaryKey(RoleInfo)
	 */
	@Override
	public int updateByPrimaryKey(DutyInfo duty, HttpServletRequest request) {
		valiDate(duty);
		int a = 0;
		DutyBean record = new DutyBean();
		BeanUtils.copyProperties(duty, record);
		a = dutyBeanMapper.isDutyExist(record);
		if (a != 0) {
			throw new BusinessException("01032");
		}
		DutyBean editModel = dutyBeanMapper.selectByPrimaryKey(duty.getDutyId());
		a = dutyBeanMapper.updateByPrimaryKey(record);
		if (a != 0) {
			webUtils.userEditLog(request, 27, editModel, duty);
			return a;
		} else {
			throw new BusinessException("01033");
		}

	}

	/**
	 * @Description: 查询所有职位
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.DutyService#selectAllDupt()
	 */
	@Override
	public List<DutyInfo> selectAllDuty(Integer limit, Integer offset) {
		List<DutyInfo> list = new ArrayList<DutyInfo>();

		List<DutyBean> dataList = dutyBeanMapper.selectAllDupt(limit, offset);
		for (DutyBean record : dataList) {
			DutyInfo dutyInfo = new DutyInfo();
			BeanUtils.copyProperties(record, dutyInfo);
			list.add(dutyInfo);
		}
		return list;
	}

	/**
	 * 
	 * @Description: 查询职务记录数
	 * @return
	 * @see com.rofour.baseball.service.manager.DutyService#selectTotalCount()
	 */
	public int selectTotalCount() {
		return dutyBeanMapper.selectTotalCount();
	}

	/**
	 * 
	 * @Description: 校验
	 * @param model
	 * @return ResultInfo 操作结果bean
	 */
	public void valiDate(DutyInfo model) {
		if (StringUtils.isEmpty(model.getDutyName()) || model.getRankNo() == null) {
			throw new BusinessException("111");
		}
		if (model.getDutyName().length() > 30) {
			throw new BusinessException("112");
		}
		if ((model.getDutyId() != null && !StringUtils.isNumber(model.getDutyId().toString()))
				|| !StringUtils.isNumber(model.getRankNo().toString())) {
			throw new BusinessException("114");
		}
	}
}
