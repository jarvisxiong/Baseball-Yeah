/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.common.WebUtils;
import com.zhiduan.axp.controller.model.manager.QuotaInfo;
import com.zhiduan.axp.dao.manager.bean.QuotaBean;
import com.zhiduan.axp.dao.manager.mapper.QuotaMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.QuotaService;

/**
 * @ClassName: QuotaImpl
 * @Description: 指标配置接口实现
 * @author xl
 * @date 2016年7月18日 下午3:46:02
 */
@Service("quotaService")
public class QuotaImpl implements QuotaService {

	@Autowired
	@Qualifier("quotaMapper")
	private QuotaMapper dao;
	@Autowired
	private WebUtils webUtils;

	/**
	 * @Description: 按主键查询指标
	 * @param quotaId
	 * @return
	 * @see com.zhiduan.axp.service.manager.QuotaService#selectById(int)
	 */
	@Override
	public QuotaInfo selectById(int quotaId) {
		QuotaBean quotaBean = dao.selectById(quotaId);
		if (quotaBean == null) {
			throw new BusinessException("105");
		}
		QuotaInfo quotaInfo = new QuotaInfo();
		BeanUtils.copyProperties(quotaBean, quotaInfo);
		return quotaInfo;
	}

	/**
	 * @Description: 查询所有指标
	 * @return
	 * @see com.zhiduan.axp.service.manager.QuotaService#selectAll()
	 */
	@Override
	public List<QuotaInfo> selectAll() {
		List<QuotaBean> quotaBeans = dao.selectAll();
		if (quotaBeans == null || quotaBeans.isEmpty()) {
			throw new BusinessException("105");
		}
		List<QuotaInfo> quotaInfos = new ArrayList<QuotaInfo>();
		for (QuotaBean item : quotaBeans) {
			QuotaInfo quotaInfo = new QuotaInfo();
			BeanUtils.copyProperties(item, quotaInfo);
			quotaInfos.add(quotaInfo);
		}
		return quotaInfos;
	}

	/**
	 * @Description: 查询在用指标总数
	 * @return
	 * @see com.zhiduan.axp.service.manager.QuotaService#getQuotaCount()
	 */
	@Override
	public int getQuotaCount() {
		return dao.getQuotaCount();
	}

	/**
	 * @Description: 删除指标
	 * @param quotaId
	 * @return
	 * @see com.zhiduan.axp.service.manager.QuotaService#deleteById(int)
	 */
	@Override
	public int deleteById(int quotaId, HttpServletRequest request) {
		int rtn = dao.deleteById(quotaId);
		if (rtn == 0) {
			throw new BusinessException("105");
		}
		QuotaInfo info = new QuotaInfo();
		info.setQuotaId(quotaId);
		webUtils.userDeleteLog(request, 9, info);
		return rtn;
	}

	/**
	 * @Description: 批量删除
	 * @param quotaIds
	 * @return
	 * @see com.zhiduan.axp.service.manager.QuotaService#deleteBatchByIds(java.util.List)
	 */
	@Override
	public int deleteBatchByIds(List<Integer> quotaIds, HttpServletRequest request) {
		int rtn = dao.deleteBatchByIds(quotaIds);
		if (rtn == 0) {
			throw new BusinessException("105");
		}
		QuotaInfo info = new QuotaInfo();
		for (Integer item : quotaIds) {
			info.setQuotaId(item);
			webUtils.userDeleteLog(request, 9, info);
		}
		return rtn;
	}

	/**
	 * @Description: 新增指标
	 * @param info
	 * @return
	 * @see com.zhiduan.axp.service.manager.QuotaService#insert(com.zhiduan.axp.controller.model.manager.QuotaInfo)
	 */
	@Override
	public int insert(QuotaInfo info, HttpServletRequest request) {
		QuotaBean quotaBean = new QuotaBean();
		if (info == null) {
			throw new BusinessException("110");
		}
		BeanUtils.copyProperties(info, quotaBean);
		int rtn = dao.isQuotaIdExist(quotaBean);
		if (rtn > 0) {
			throw new BusinessException("01042");
		}
		rtn = dao.isQuotaExist(quotaBean);
		if (rtn > 0) {
			throw new BusinessException("01041");
		}
		rtn = dao.insert(quotaBean);
		webUtils.userDeleteLog(request, 9, info);
		return rtn;
	}

	/**
	 * @Description: 更新指标
	 * @param quotaId
	 * @return
	 * @see com.zhiduan.axp.service.manager.QuotaService#updateById(int)
	 */
	@Override
	public int updateById(QuotaInfo info, HttpServletRequest request) {
		if (info == null) {
			throw new BusinessException("110");
		}
		QuotaBean quotaBean = new QuotaBean();
		BeanUtils.copyProperties(info, quotaBean);
		int rtn = dao.isQuotaExist(quotaBean);
		if (rtn > 0) {
			throw new BusinessException("01041");
		}
		rtn = dao.updateById(quotaBean);
		webUtils.userDeleteLog(request, 9, info);
		return rtn;
	}

}
