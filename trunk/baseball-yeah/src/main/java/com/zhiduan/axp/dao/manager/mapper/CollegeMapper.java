/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.dao.manager.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.dao.manager.bean.CityCollegeBean;
import com.zhiduan.axp.dao.manager.bean.CollegeBean;
import com.zhiduan.axp.dao.manager.bean.CollegeManageBean;
import com.zhiduan.axp.dao.manager.bean.CollegeSelectBean;

/**
 * @ClassName: TbCollegeMapper
 * @Description: 学校数据库操作接口
 * @author xl
 * @date 2016年3月26日 下午1:27:49
 *
 */

@Named("collegeMapper")
public interface CollegeMapper {

	/**
	 * 
	 * @Description: 新增学校记录
	 * @param collegeBean
	 * @return int
	 */
	int insert(CollegeBean collegeBean);

	/**
	 * 
	 * @Description: 查询所有学校记录
	 * @return List<TbCollegeBean>
	 */
	List<CollegeBean> selectAll();

	/**
	 * 
	 * @Method: querySelect
	 * @Description: 查询学校下拉列表
	 * @param @return
	 *            参数
	 * @return List<CollegeBean> 返回类型
	 * @throws @author
	 *             heyi
	 * @date 2016年5月13日 下午1:41:16
	 *
	 */
	List<CollegeSelectBean> querySelect();

	/**
	 * 
	 * @Description: 删除学校记录
	 * @param collegeId
	 * @return 删除的数量
	 */
	int delete(Long collegeId);

	/**
	 * 
	 * @Description: 更新学校信息
	 * @param collegeBean
	 * @return int
	 */
	int update(CollegeBean collegeBean);

	/**
	 * 
	 * @Description: 判断学校全称，编号是否存在
	 * @param map
	 * @return 存在的数量
	 */
	int isCollegeExist(Map<String, Object> map);

	/**
	 * 
	 * @Description: 按城市id查询学校
	 * @param cityId
	 * @return List<CityCollegeBean>
	 */
	List<CityCollegeBean> selectByCityId(Long cityId);

	/**
	 * 
	 * @Description: 学校管理查询
	 * @return
	 */
	List<CollegeManageBean> selectManageCollegeInfo();

	List<CollegeManageBean> selectCollegeAJAX( CollegeBean collegeBean);
	List<CollegeManageBean> selectCollegeForEdit( CollegeBean collegeBean);
	/**
	 * 
	 * @Description: 按主键查询学校
	 * @param collegeId
	 * @return
	 */
	CollegeBean selectById(Long collegeId);

	/**
	 * 
	 * @Description: 修改学校众包模式
	 * @param map
	 * @return
	 */
	int changePacketModel(Map<String, Object> map);

	/**
	 * 
	 * @Description: 按Id查询学校众包模式开启状态
	 * @param map
	 * @return
	 */
	String getPacketMode(Map<String, Object> map);
}
