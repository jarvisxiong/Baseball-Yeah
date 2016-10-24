/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhiduan.axp.controller.model.manager.DutyInfo;

/**
 * @ClassName: DutyService
 * @Description: 管理中心 职务
 * @author 高振
 * @date 2016年3月27日 下午4:53:15
 *
 */

public interface DutyService {

	/**
	 * @Description: 删除一个职务
	 * @param dutyId
	 * @param request
	 * @return
	 */

	int deleteByPrimaryKey(Long dutyId, HttpServletRequest request);

	/**
	 * @Description: 添加一个职务
	 * @param record
	 * @param request
	 * @return
	 */

	int insert(DutyInfo record, HttpServletRequest request);

	/**
	 * @Description: 查询一个职务
	 * @param dutyId
	 * @return
	 */

	DutyInfo selectByPrimaryKey(Long dutyId);

	/**
	 * @Description: 更新一个职务
	 * @param record
	 * @param request
	 * @return
	 */

	int updateByPrimaryKey(DutyInfo record, HttpServletRequest request);

	/**
	 * 
	 * @Description: 查询所有职务
	 * @param limit
	 * @param offset
	 * @return
	 */

	List<DutyInfo> selectAllDuty(Integer limit, Integer offset);

	/**
	 * 
	 * @Description: 查询职务记录数
	 * @return
	 */
	int selectTotalCount();

	List<DutyInfo> getAllDuty();

}
