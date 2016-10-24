/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.controller.model.manager.MsgTemplateInfo;
import com.zhiduan.axp.dao.manager.bean.MsgTemplateBean;

/**
 * @ClassName: MsgTemplateMapper
 * @Description: 消息类型操作接口
 * @author xzy
 * @date 2016年4月3日 下午2:18:25
 *
 */

@Named("msgTemplateMapper")
public interface MsgTemplateMapper {
	/**
	 * 
	 * @Description: 根据主键删除
	 * @param messageTemplateId
	 * @return 删除的数量
	 */
	int deleteTemplate(MsgTemplateInfo msg);

	/**
	 * 
	 * @Description: 增加
	 * @param record
	 * @return int
	 */
	int insert(MsgTemplateBean record);

	/**
	 * 
	 * @Description: 根据主键查找
	 * @param messageTemplateId
	 * @return MsgTemplateBean
	 */
	MsgTemplateBean selectByPrimaryKey(String messageTemplateId);

	/**
	 * 
	 * @Description: 根据主键更新
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKey(MsgTemplateBean record);

	/**
	 * 
	 * @Description: 查询所有
	 * @return List<MsgTemplateBean>
	 */
	List<MsgTemplateInfo> selectAll(MsgTemplateInfo msgTemplateInfo);
	/**
	 * @Description: 查找已存在的id
	 * @param id
	 * @return 存在的数量
	 */
	int existTemplateId(String id);
	/**
	 * @Description: 查找已存在的name
	 * @param id
	 * @return 存在的数量
	 */
	int existTemplateName(Map<String,String> map);
	/**
	 * @Description: 查找消息模板总数
	 * @return 
	 */
	    
	int getMsgTemplateTotal(MsgTemplateInfo msgTemplateInfo);
}