/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.manager.SysNoticeInfo;

/**
 * @ClassName: SysNoticeService
 * @Description: 系统通知逻辑层接口
 * @author xzy
 * @date 2016年3月27日 下午12:07:50
 *
 */

public interface SysNoticeService {

	/**
	 * @Description: 增加
	 * @param notice
	 * @return
	 */

	public int addNotice(SysNoticeInfo notice, HttpServletRequest request);

	/**
	 * @Description: 逻辑删除
	 * @param notice
	 * @return
	 */

	public int deleteNotice(SysNoticeInfo notice, HttpServletRequest request);

	/**
	 * @Description: 更新
	 * @param notice
	 * @return
	 */

	public int updateNotice(SysNoticeInfo notice, HttpServletRequest request);

	/**
	 * @Description: 根据主键查找
	 * @param notice
	 * @return
	 */

	public SysNoticeInfo selectNotice(SysNoticeInfo notice);

	/**
	 * @Description: 查询所有
	 * @return
	 */

	public List<SysNoticeInfo> selectAll(SysNoticeInfo sysNoticeInfo);
	/**
	 * @Description: 查询条数
	 * @return
	 */
	public Integer getSysNoticeTotal(SysNoticeInfo notice);
	
	/**
	 * 
	 * @Description: 审核
	 * @param notice
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public void auditNotice(SysNoticeInfo notice, HttpServletRequest request) throws IOException;
}
