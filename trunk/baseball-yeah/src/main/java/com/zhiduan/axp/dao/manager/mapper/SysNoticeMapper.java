/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.controller.model.manager.SysNoticeInfo;
import com.zhiduan.axp.dao.manager.bean.SysNoticeBean;

/**
 * @ClassName: SysNoticeMapper
 * @Description: 系统通知操作接口
 * @author xzy
 * @date 2016年3月27日 上午11:58:20
 *
 */

@Named("sysNoticeMapper")
public interface SysNoticeMapper {
	/**
	 * 
	 * @Description:批量删除
	 * @param id
	 * @return 删除的数量
	 */
	int deleteBatch(SysNoticeInfo sysNotice);

	/**
	 * 
	 * @Description: 增加
	 * @param record
	 * @return int
	 */
	int insert(SysNoticeBean record);
	
	/**
	 * 
	 * @Description: 批量增加
	 * @param list
	 * @return
	 */
	int insertBatch(List<SysNoticeBean> list);

	/**
	 * 
	 * @Description: 根据主键查找
	 * @param sysNoticeId
	 * @return SysNoticeBean
	 */
	SysNoticeBean selectByPrimaryKey(Long sysNoticeId);


	/**
	 * 
	 * @Description: 根据主键更新
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKeyWithBLOBs(SysNoticeBean record);

	/**
	 * 
	 * @Description:根据主键更新
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKey(SysNoticeInfo record);

	/**
	 * 
	 * @Description: 查询所有
	 * @return List<SysNoticeBean>
	 */
	List<SysNoticeInfo> selectAll(SysNoticeInfo sysNoticeInfo);
	/**
	 * 
	 * @Description: 查询条数
	 * @return List<SysNoticeBean>
	 */
	Integer getSysNoticeTotal(SysNoticeInfo record);
	
	/**
	 * 
	 * @Description: 审核
	 * @param map
	 */
	void auditUpdate(Map<String, Object> map);
}