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
import com.rofour.baseball.controller.model.manager.DeptInfo;
import com.rofour.baseball.dao.manager.bean.DeptBean;
import com.rofour.baseball.dao.manager.mapper.DeptMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.DeptService;

/**
 * @ClassName: DeptServiceImpl
 * @Description:
 * @author 高振
 * @date 2016年3月27日 下午4:46:42
 *
 */

@Service("deptService")
public class DeptServiceImpl implements DeptService {

	@Autowired
	@Qualifier("deptMapper")
	private DeptMapper deptMapper;

	@Autowired
	private WebUtils webUtils;

	/**
	 * @Description: 删除部门
	 * @param deptId
	 * @param request
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.DeptService#deleteByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int deleteByPrimaryKey(Long deptId, HttpServletRequest request) {
		int a = 0;
		a = deptMapper.deleteByPrimaryKey(deptId);
		if (a != 0) {
			webUtils.userDeleteLog(request, 20, deptId);
			return a;
		} else {
			throw new BusinessException("01031");
		}
	}

	/**
	 * @Description: 添加部门
	 * @param dept
	 * @param request
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.DeptService#addDept(com.rofour.baseball.idl.managecenter.service.entity.DeptInfo)
	 */
	@Override
	public int addDept(DeptInfo dept, HttpServletRequest request) {
		valiDate(dept);
		int a = 0;
		DeptBean record = new DeptBean();

		BeanUtils.copyProperties(dept, record);
		a = deptMapper.isDeptExist(record);
		if (a != 0) {
			throw new BusinessException("01030");
		}
		a = deptMapper.insert(record);
		if (a != 0) {
			webUtils.userAddLog(request, 20, record);
			return a;
		} else {
			throw new BusinessException("01031");
		}
	}

	/**
	 * @Description: 查询部门信息
	 * @param deptId
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.DeptService#selectByPrimaryKey(java.lang.Long)
	 */

	@Override
	public DeptInfo selectByPrimaryKey(Long deptId) {

		DeptInfo dept = new DeptInfo();

		DeptBean record = deptMapper.selectByPrimaryKey(deptId);

		if (record != null) {
			BeanUtils.copyProperties(record, dept);
			return dept;
		} else {
			throw new BusinessException("01031");
		}

	}

	/**
	 * 
	 * @Description: 更新部门
	 * @param dept
	 * @param request
	 * @return
	 * @see com.rofour.baseball.service.manager.DeptService#updateByPrimaryKey(com.rofour.baseball.controller.model.manager.DeptInfo,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public int updateByPrimaryKey(DeptInfo dept, HttpServletRequest request) {
		valiDate(dept);
		int a = 0;
		DeptBean deptIdBean = new DeptBean();
		BeanUtils.copyProperties(dept, deptIdBean);
		a = deptMapper.isDeptExist(deptIdBean);
		if (a != 0) {
			throw new BusinessException("01030");
		}
		DeptBean oldDept = deptMapper.selectByPrimaryKey(dept.getDeptId());
		a = deptMapper.updateByPrimaryKey(deptIdBean);
		if (a != 0) {
			webUtils.userEditLog(request, 20, oldDept, deptIdBean);
			return a;
		} else {
			throw new BusinessException("01031");
		}

	}

	/**
	 * @Description: 查询所有部门
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.DeptService#selectAllDept()
	 */
	@Override
	public List<DeptInfo> selectAllDept(Integer limit, Integer offset) {
		List<DeptInfo> list = new ArrayList<DeptInfo>();
		List<DeptBean> dataList = deptMapper.selectAllDept(limit, offset);
		for (DeptBean record : dataList) {
			DeptInfo deptIdInfo = new DeptInfo();
			BeanUtils.copyProperties(record, deptIdInfo);
			list.add(deptIdInfo);
		}

		return list;
	}

	/**
	 * 
	 * 查询部门总数
	 * 
	 * @return
	 */
	public int selectTotalCount() {
		return deptMapper.selectTotalCount();
	}

	@Override
	public List<DeptInfo> getAllDept() {
		List<DeptInfo> list = new ArrayList<DeptInfo>();

		List<DeptBean> dataList = deptMapper.getAllDept();
		for (DeptBean record : dataList) {
			DeptInfo deptIdInfo = new DeptInfo();
			BeanUtils.copyProperties(record, deptIdInfo);
			list.add(deptIdInfo);
		}

		return list;
	}

	/**
	 * 
	 * @Description: 校验
	 * @param modeld
	 * @return ResultInfo 操作结果bean
	 */
	private void valiDate(DeptInfo modeld) {
		if (modeld == null || StringUtils.isEmpty(modeld.getDeptName()) || StringUtils.isEmpty(modeld.getDeptCode())) {
			throw new BusinessException("111");
		}
		if (modeld.getDeptName().length() > 30 || modeld.getDeptCode().trim().length() > 10) {
			throw new BusinessException("112");
		}
		if (modeld.getDeptId() != null && !StringUtils.isNumber(modeld.getDeptId().toString())) {
			throw new BusinessException("114");
		}

	}
}
