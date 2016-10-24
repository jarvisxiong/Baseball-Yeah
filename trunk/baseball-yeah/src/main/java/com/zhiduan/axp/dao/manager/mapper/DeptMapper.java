/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.zhiduan.axp.dao.manager.bean.DeptBean;

/**
 * @ClassName: DeptMapper
 * @Description: 这里用一句话描述这个类的作用
 * @author 高振
 * @date 2016年3月27日 下午4:51:40
 *
 */

@Named("deptMapper")
public interface DeptMapper {
	/**
	 * 
	 * @Description: 通过id删除部门
	 * @param deptId
	 * @return 删除的数量
	 */
	int deleteByPrimaryKey(Long deptId);

    List<DeptBean> getAllDept();
	/**
	 * 
	 * @Description: 新增部门
	 * @param record
	 * @return int
	 */
	int insert(DeptBean record);

	/**
	 * 
	 * @Description: 查询部门
	 * @param deptId
	 * @return DeptIdUserBean
	 */
	DeptBean selectByPrimaryKey(Long deptId);

	/**
	 * 
	 * @Description: 修改部门
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKeySelective(DeptBean record);

	/**
	 * 
	 * @Description: 修改部门
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKey(DeptBean record);

	/**
	 * 
	 * @Description: 查询全部部门
	 * @param limit
	 * @param offset
	 * @return
	 */
	List<DeptBean> selectAllDept(Integer limit, Integer offset);
	
	/**
	 * 
	 * 查询部门总数
	 * @return
	 */
	int selectTotalCount();

	/**
	 * 
	 * @Description: 判断重名
	 * @param map
	 *            条件
	 * @return 已存在的数量
	 */
	int isDeptExist(DeptBean record);

}
