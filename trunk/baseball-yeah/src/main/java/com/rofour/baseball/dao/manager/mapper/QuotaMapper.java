/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.controller.model.manager.QuotaInfo;
import com.rofour.baseball.dao.manager.bean.QuotaBean;

/**
 * @ClassName: QuotaMapper
 * @Description: 指标数据库操作接口
 * @author xl
 * @date 2016年7月18日 下午3:19:11
 */
@Named("quotaMapper")
public interface QuotaMapper {
	/**
	 * 
	 * @Description: 按主键查询
	 * @param quotaId
	 * @return
	 */
	public QuotaBean selectById(int quotaId);

	/**
	 * 
	 * @Description: 查询所有在用指标
	 * @return
	 */
	public List<QuotaBean> selectAll(QuotaInfo quotaIfo);

	/**
	 * 
	 * @Description: 查询在用指标总数
	 * @return
	 */
	public int getQuotaCount();

	/**
	 * 
	 * @Description: 按住建删除
	 * @param quotaId
	 * @return
	 */
	public int deleteById(int quotaId);

	/**
	 * 
	 * @Description: 批量删除
	 * @param list
	 * @return
	 */
	public int deleteBatchByIds(List<Integer> quotaIds);
	/**
	 *
	 * @Description: 批量启用
	 * @param quotaIds
	 * @return
	 */
	public int activeBatchByIds(List<Integer> quotaIds);

	/**
	 * 
	 * @Description: 新增指标
	 * @return
	 */
	public int insert(QuotaBean quotaBean);
	/**
	 *
	 * @Description: 按主键删除
	 * @param quotaBean
	 * @return
	 */
	public int updateDelById(QuotaBean quotaBean);

	/**
	 *
	 * @Description: 按主键更新
	 * @param quotaBean
	 * @return
	 */
	public int updateById(QuotaBean quotaBean);

	/**
	 * 
	 * @Description: 判断指标是否存在
	 * @return
	 */
	public int isQuotaExist(QuotaBean quotaBean);

	/**
	 * 
	 * @Description: 判断指标ID是否存在
	 * @param quotaBean
	 * @return
	 */
	public int isQuotaIdExist(QuotaBean quotaBean);
}
