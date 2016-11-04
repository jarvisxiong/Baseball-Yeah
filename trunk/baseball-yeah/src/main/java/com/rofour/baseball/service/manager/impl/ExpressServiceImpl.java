/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.service.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.common.WebUtils;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.ExpressCompanyInfo;
import com.rofour.baseball.dao.manager.bean.ExpressBean;
import com.rofour.baseball.dao.manager.mapper.ExpressMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.ExpressService;
import com.rofour.baseball.common.RedisCommons;
import com.rofour.baseball.common.RedisKeyConstants;

/**
 * 
 * @ClassName: ExpressServiceImpl
 * @Description: 快递公司维护业务逻辑层
 * @author heyi
 * @date 2016年3月25日 下午4:11:55
 *
 */
@Service("expressService")
public class ExpressServiceImpl implements ExpressService {
	@Autowired
	@Qualifier("expressMapper")
	private ExpressMapper expressMapper;

	@Autowired
	private WebUtils webUtils;
	
	 @Resource(name = "redisCommons")
	 private RedisCommons redisCommons;

	/**
	 * 
	 * @Description: 获取所有快递公司(分页)
	 * @param limit
	 * @param offset
	 * @return
	 * @see com.rofour.baseball.service.manager.ExpressService#GetExpressList(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	public List<ExpressCompanyInfo> GetExpressList(Integer limit, Integer offset) {
		List<ExpressBean> list = expressMapper.GetExpressList(limit, offset);
		List<ExpressCompanyInfo> datalist = new ArrayList<ExpressCompanyInfo>();
		for (ExpressBean item : list) {
			ExpressCompanyInfo express = new ExpressCompanyInfo();
			BeanUtils.copyProperties(item, express);
			datalist.add(express);
		}
		return datalist;
	}

	/**
	 * 
	 * @Description: 获取所有快递公司
	 * @return
	 * @see com.rofour.baseball.service.manager.ExpressService#GetExpressList()
	 */
	public List<ExpressCompanyInfo> GetExpressList() {
		List<ExpressBean> list = expressMapper.GetAllEnabledExpressList();
		List<ExpressCompanyInfo> datalist = new ArrayList<ExpressCompanyInfo>();
		for (ExpressBean item : list) {
			ExpressCompanyInfo express = new ExpressCompanyInfo();
			BeanUtils.copyProperties(item, express);
			datalist.add(express);
		}
		return datalist;
	}

	/**
	 * 
	 * @Description: 查询快递公司总数
	 * @return
	 * @see com.rofour.baseball.service.manager.ExpressService#selectTotalCount()
	 */
	public int selectTotalCount() {
		return expressMapper.selectTotalCount();
	}

	/**
	 * @Description: 新增一条数据
	 * @param model
	 * @param request
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.ExpressService#Insert(com.rofour.baseball.idl.managecenter.service.entity.ExpressCompanyInfo)
	 */
	public ResultInfo Insert(ExpressCompanyInfo expressCompany, HttpServletRequest request) {
		checkExpressCompany(expressCompany);
		ExpressBean Bean = new ExpressBean();
		BeanUtils.copyProperties(expressCompany, Bean);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", expressCompany.getCode());
		map.put("fullname", expressCompany.getFullname());
		map.put("simplename", expressCompany.getSimplename());
		int existExpress = expressMapper.isExpressExist(map);
		if (existExpress > 0) {
			throw new BusinessException("01034");
		}
		int status = expressMapper.Insert(Bean);
		webUtils.userAddLog(request, 21, Bean);
		if (status != 0) {
		    delCache();
			return new ResultInfo(0, "", "添加成功", "");

		} else {
			throw new BusinessException("01035");
		}

	}

	/**
	 * @Description: 根据主键修改一条数据
	 * @param model
	 * @param request
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.ExpressService#updateByPrimaryKey(com.rofour.baseball.idl.managecenter.service.entity.ExpressCompanyInfo)
	 */
	public ResultInfo updateByPrimaryKey(ExpressCompanyInfo expressCompany, HttpServletRequest request) {
		checkExpressCompany(expressCompany);
		if (expressCompany.getExpresscompanyid() == null) {
			throw new BusinessException("");
		}
		ExpressBean Bean = new ExpressBean();
		BeanUtils.copyProperties(expressCompany, Bean);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", expressCompany.getCode());
		map.put("fullname", expressCompany.getFullname());
		map.put("simplename", expressCompany.getSimplename());
		map.put("expresscompanyid", expressCompany.getExpresscompanyid());
		int existExpress = expressMapper.isExpressExist(map);
		if (existExpress > 0) {
			throw new BusinessException("01034");
		}
		ExpressBean oldExpressBean = expressMapper.getExpressById(Bean.getExpresscompanyid());
		int num = expressMapper.updateByPrimaryKey(Bean);
		webUtils.userEditLog(request, 21, oldExpressBean, Bean);
		if (num == 0) {
			throw new BusinessException("01035");
		}
		delCache();
		return new ResultInfo(0, "", "更新成功", "");
	}

	/**
	 * @Description: 根据主键删除一条数据
	 * @param id
	 * @param request
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.ExpressService#deleteByPrimaryKey(java.lang.Long)
	 */
	public int deleteByPrimaryKey(Long id, HttpServletRequest request) {
		if (id == null) {
			throw new BusinessException("111");
		}
		int result = expressMapper.deleteByPrimaryKey(id);
		webUtils.userDeleteLog(request, 21, id);
		if (result == 0) {
			throw new BusinessException("01035");
		}
		delCache();
		return result;
	}

	/**
	 * @Description: 根据修改时间查询快递公司列表
	 * @param modifyTime
	 * @return
	 * @see com.rofour.baseball.idl.managecenter.service.ExpressService#GetExpressListByModifyTime(java.lang.String)
	 */
	@Override
	public List<ExpressCompanyInfo> GetExpressListByModifyTime(String modifyTime) {
		List<ExpressBean> list = expressMapper.GetExpressListByModifyTime(modifyTime);
		List<ExpressCompanyInfo> datalist = new ArrayList<ExpressCompanyInfo>();
		for (ExpressBean item : list) {
			ExpressCompanyInfo express = new ExpressCompanyInfo();
			BeanUtils.copyProperties(item, express);
			datalist.add(express);
		}
		return datalist;
	}

	/**
	 * @Description: 获取公司信息，公司ID为key，公司简称为value
	 * @param
	 * @return Map<Long,String> @throws
	 */
	@Override
	public Map<Long, String> getCompanyMapInfo() {
		List<ExpressBean> list = expressMapper.GetExpressList();
		Map<Long, String> retMap = new HashMap<>();
		for (ExpressBean bean : list) {
			retMap.put(bean.getExpresscompanyid(), bean.getSimplename());
		}
		return retMap;
	}

	/**
	 * 
	 * @Description: 验证传入的参数是否合法
	 * @param model
	 * @return ResultInfo 操作结果bean
	 */
	private void checkExpressCompany(ExpressCompanyInfo expressCompany) {
		if (expressCompany == null || StringUtils.isBlank(expressCompany.getFullname())
				|| StringUtils.isBlank(expressCompany.getSimplename())
				|| StringUtils.isBlank(expressCompany.getCode())) {
			throw new BusinessException("111");
		}
		if ((StringUtils.isNotBlank(expressCompany.getLogo()) && expressCompany.getLogo().length() > 255)
				|| (StringUtils.isNotBlank(expressCompany.getHotline()) && expressCompany.getHotline().length() > 100)
				|| expressCompany.getFullname().length() > 100 || expressCompany.getSimplename().length() > 50
				|| expressCompany.getCode().length() > 30) {
			throw new BusinessException("112");
		}
	}
	 public void delCache()
	    {
	        String redisKey = RedisKeyConstants.EXPRESS_LIST;
	        String key = RedisKeyConstants.EXPRESS_MAP;
	        redisCommons.delete(redisKey);
	        redisCommons.delete(key);
	    }
}
