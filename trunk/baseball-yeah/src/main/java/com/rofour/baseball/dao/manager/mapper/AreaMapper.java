/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.dao.manager.bean.AreaBean;

/**
* @ClassName: AreaMapper
* @Description: 区域数据库操作接口
* @author cy
* @date 2016-04-18 10:24:31
*
*/
@Named("areaMapper")
public interface AreaMapper {

	/**
	 * 
	 * @Description: 新增
	 * @param bean
	 * @return int
	 */
    int insertSelective(AreaBean bean);

    /**
    * @Description: 按主键ID删除菜单
    * @param  areaId
    * @return int 删除数量
    **/
    int deleteByPrimaryKey(Long areaId);

    /**
    * @Description: 更新
    * @param bean
    * @return int 更新的数量
    **/
    int updateByPrimaryKeySelective(AreaBean bean);

    /**
    * @Description: 根据主键查询
    * @param areaId
    * @return AreaBean
    **/
    AreaBean selectByPrimaryKey(Long areaId);

    /**
    * @Description: 查询所有
    * @return List<AreaBean>
    **/
    List<AreaBean> selectAll();
    /**
     * 
     * @Description: 判断重名
     * @param map
     * @return 已存在的数量
     */
    int isAreaExists(AreaBean bean);
}