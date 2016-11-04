/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.dao.manager.bean.DutyBean;

/**
 * @ClassName: DutyBeanMapper
 * @Description: 这里用一句话描述这个类的作用
 * @author 高振
 * @date 2016年3月27日 下午4:51:13
 *
 */

@Named("dutyBeanMapper")
public interface DutyBeanMapper {

	/**
	 * 
	 * @Description: 按主键ID删除职务
	 * @param dutyId
	 * @return 删除的数量
	 */
	int deleteByPrimaryKey(Long dutyId);

	/**
	 * 
	 * @Description: 新增职务
	 * @param record
	 * @return int
	 */
	int insert(DutyBean record);

	/**
	 * 
	 * @Description: 查询职务
	 * @param dutyId
	 * @return DutyBean
	 */
	DutyBean selectByPrimaryKey(Long dutyId);

	/**
	 * 
	 * @Description: 修改职务
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKey(DutyBean record);

	
	List<DutyBean>  getAllDupt();
	/**
	 * 
	 * @Description: 查询全部
	 * @param limit
	 * @param offset
	 * @return
	 */
	List<DutyBean> selectAllDupt(Integer limit, Integer offset);

	/**
	 * 
	 * @Description: 查询职务记录数
	 * @return
	 */
	int selectTotalCount();

	/**
	 * 
	 * @Description: 判断重名
	 * @param map
	 * @return 已存在的数量
	 */
	int isDutyExist(DutyBean record);
}
