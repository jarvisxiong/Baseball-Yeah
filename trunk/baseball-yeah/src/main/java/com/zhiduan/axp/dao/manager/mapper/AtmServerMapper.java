/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.zhiduan.axp.dao.manager.bean.AtmServerBean;

/**
 * @ClassName: AtmServerMapper
 * @Description: 附件服务器操作接口
 * @author xzy
 * @date 2016年4月3日 下午2:15:44
 *
 */

@Named("atmServerMapper")
public interface AtmServerMapper {
	/**
	 * 
	 * @Description: 根据主键删除
	 * @param attachmentServerId
	 * @return int 删除的数量
	 */
	int deleteByPrimaryKey(String attachmentServerId);

	/**
	 * 
	 * @Description: 增加
	 * @param record
	 * @return int
	 */
	int insert(AtmServerBean record);

	/**
	 * 
	 * @Description: 根据主键查找
	 * @param attachmentServerId
	 * @return AtmServerBean
	 */
	AtmServerBean selectByPrimaryKey(String attachmentServerId);

	/**
	 * 
	 * @Description: 根据主键更新
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKey(AtmServerBean record);

	/**
	 * 
	 * @Description: 查询所有
	 * @return List<AtmServerBean>
	 */
	List<AtmServerBean> selectAll();

	/**
	 * 
	 * @Description: 根据名字查找
	 * @param atmServerName
	 * @return AtmServerBean
	 */
	AtmServerBean selectByName(String atmServerName);
	
	/**
	 * @Description: 附件服务器ID和名称是否存在
	 * @param record
	 * @return 
	 */
	    
	int isAtmServerExists(AtmServerBean record);
	/**
	 * @Description: 附件服务器名称是否存在
	 * @param record
	 * @return 
	 */
	    
	int isAtmServerNameExists(AtmServerBean record);
}