/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhiduan.axp.controller.model.manager.MsgConfigInfo;

/**
 * @ClassName: MsgConfigService
 * @Description: 消息配置操作接口
 * @author xzy
 * @date 2016年3月28日 上午10:05:29
 *
 */

public interface MsgConfigService {

	/**
	 * @Description: 增加
	 * @param msgconfig
	 * @return
	 */

	public int addMsgConfig(MsgConfigInfo msgconfig,HttpServletRequest request);

	/**
	 * @Description: 删除
	 * @param msgconfig
	 * @return
	 */

	public int deleteMsgConfig(Long id,HttpServletRequest request);

	/**
	 * @Description: 更新
	 * @param msgconfig
	 * @return
	 */

	public int updateMsgConfig(MsgConfigInfo msgconfig,HttpServletRequest request);

	/**
	 * @Description: 根据主键查找单条
	 * @param msgconfig
	 * @return
	 */

	public MsgConfigInfo selectMsgConfig(MsgConfigInfo msgconfig);

	/**
	 * @Description: 查询所有
	 * @return
	 */

	public List<MsgConfigInfo> selectAll(MsgConfigInfo info);
	/**
	 * 
	 * @Description:根据条件查询数量
	 * @param info
	 * @return
	 */
	public int getTotal(MsgConfigInfo info);

}
