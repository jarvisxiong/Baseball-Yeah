/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.service.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.manager.QuotaInfo;

/**
 * @ClassName: QuotaService
 * @Description: 指标配置接口
 * @author xl
 * @date 2016年7月18日 下午3:38:19
 */
public interface QuotaService {
	/**
	 * 
	 * @Description: 按主键查询
	 * @param quotaId
	 * @return
	 */
	public QuotaInfo selectById(int quotaId);

	/**
	 * 
	 * @Description: 查询所有在用指标
	 * @return
	 */
	public List<QuotaInfo> selectAll(QuotaInfo quotaIfo);

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
	public int deleteById(int quotaId, HttpServletRequest request);

	/**
	 * 
	 * @Description: 批量删除
	 * @param quotaIds
	 * @return
	 */
	public int deleteBatchByIds(List<Integer> quotaIds, HttpServletRequest request);

	/**
	 *
	 * @Description: 批量启用
	 * @param quotaIds
	 * @return
	 */
	public int activeBatchByIds(List<Integer> quotaIds, HttpServletRequest request);

	/**
	 * 
	 * @Description: 新增指标
	 * @return
	 */
	public int insert(QuotaInfo info, HttpServletRequest request);

	/**
	 * 
	 * @Description: 按主键更新
	 * @param info
	 * @return
	 */
	public int updateById(QuotaInfo info, HttpServletRequest request);
}
