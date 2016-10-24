/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager;

import java.util.List;

import com.zhiduan.axp.controller.model.manager.AtmServerInfo;

/**
 * @ClassName: AtmServerService
 * @Description: 附件服务器操作接口
 * @author xzy
 * @date 2016年4月2日 下午5:11:06
 *
 */

public interface AtmServerService {

	/**
	 * @Description: 增加
	 * @param atmServer
	 * @return
	 */

	public int addAtmServer(AtmServerInfo atmServer);

	/**
	 * @Description: 删除
	 * @param atmServer
	 * @return
	 */

	public int deleteAtmServer(AtmServerInfo atmServer);

	/**
	 * @Description: 更改
	 * @param atmServer
	 * @return
	 */

	public int updateAtmServer(AtmServerInfo atmServer);

	/**
	 * @Description: 根据主键查找
	 * @param atmServer
	 * @return
	 */

	public AtmServerInfo selectAtmServer(AtmServerInfo atmServer);

	/**
	 * @Description: 查询所有
	 * @return
	 */

	public List<AtmServerInfo> selectAll();

	/**
	 * @Description: 根据名字查找
	 * @param atmServer
	 * @return
	 */

	public AtmServerInfo selectByName(AtmServerInfo atmServer);

}
