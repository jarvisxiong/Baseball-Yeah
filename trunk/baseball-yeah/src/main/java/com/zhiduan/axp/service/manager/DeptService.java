/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhiduan.axp.controller.model.manager.DeptInfo;

/**
 * @ClassName: DeptService
 * @Description: 管理中心 部门
 * @author 高振
 * @date 2016年3月27日 下午4:52:38
 *
 */

public interface DeptService {

	/**
	 * @Description: 删除一个部门
	 * @param deptId
	 * @param request
	 * @return
	 */

	int deleteByPrimaryKey(Long deptId, HttpServletRequest request);

	/**
	 * @Description: 添加一个部门
	 * @param dept
	 * @param request
	 * @return
	 */

	int addDept(DeptInfo dept, HttpServletRequest request);

	/**
	 * @Description: 查询一个部门
	 * @param deptId
	 * @return
	 */

	DeptInfo selectByPrimaryKey(Long deptId);

	/**
	 * @Description: 更新一个部门
	 * @param dept
	 * @param request
	 * @return
	 */

	int updateByPrimaryKey(DeptInfo dept, HttpServletRequest request);

	/**
	 * 
	 * @Description: 查询所有部门
	 * @param limit
	 * @param offset
	 * @return
	 */
	List<DeptInfo> selectAllDept(Integer limit, Integer offset);

	/**
	 * 
	 * 查询部门总数
	 * 
	 * @return
	 */
	int selectTotalCount();

	List<DeptInfo> getAllDept();

}
