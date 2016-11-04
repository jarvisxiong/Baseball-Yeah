/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.manager.MsgTemplateInfo;

/**
 * @ClassName: MsgTemplateService
 * @Description: 消息类型操作接口
 * @author 周琦
 * @date 2016年4月20日 上午11:19:14
 */
public interface MsgTemplateService {

	/**
	 * @Description: 增加
	 * @param msg
	 * @return
	 */

	public int addTemplate(MsgTemplateInfo msg, HttpServletRequest request);

	/**
	 * @Description: 删除
	 * @param msg
	 * @return
	 */

	public int deleteTemplate(MsgTemplateInfo msg, HttpServletRequest request);

	/**
	 * @Description: 更新
	 * @param msg
	 * @return
	 */

	public int updateTemplate(MsgTemplateInfo msg, HttpServletRequest request);

	/**
	 * @Description: 根据主键查找
	 * @param msg
	 * @return
	 */

	public MsgTemplateInfo selectTemplate(MsgTemplateInfo msg);

	/**
	 * @Description: 查找所有
	 * @return
	 */

	List<MsgTemplateInfo> selectAll(MsgTemplateInfo msgTemplateInfo);
	/**
	 * @Description: 查找消息模板总数
	 * @return
	 */
	Integer getMsgTemplateTotal(MsgTemplateInfo msgTemplateInfo);
}
