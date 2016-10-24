/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.zhiduan.axp.controller.model.manager.MsgConfigInfo;
import com.zhiduan.axp.dao.manager.bean.MsgConfigBean;

/**
 * @ClassName: MsgConfigMapper
 * @Description:消息配置操作接口
 * @author xzy
 * @date 2016年4月3日 下午2:17:18
 *
 */

@Named("msgConfigMapper")
public interface MsgConfigMapper {
	/**
	 * 
	 * @Description: 根据主键删除
	 * @param messageConfigId
	 * @return 删除的数量
	 */
	int deleteByPrimaryKey(Long messageConfigId);

	/**
	 * 
	 * @Description: 增加
	 * @param record
	 * @return int
	 */
	int insert(MsgConfigBean record);

	/**
	 * 
	 * @Description: 根据主键查找
	 * @param messageConfigId
	 * @return MsgConfigBean
	 */
	MsgConfigBean selectByPrimaryKey(Long messageConfigId);

	/**
	 * 
	 * @Description: 根据主键更新
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKey(MsgConfigBean record);

	/**
	 * 
	 * @Description: 查询所有
	 * @return List<MsgConfigBean>
	 */
	List<MsgConfigBean> selectAll(MsgConfigInfo info);
	/**
	 * 
	 * @Description:根据条件查询数量
	 * @param info
	 * @return
	 */
	int getTotal(MsgConfigInfo info);

}