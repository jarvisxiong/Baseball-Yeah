/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhiduan.axp.common.Assert;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.UserInfo;
import com.zhiduan.axp.controller.model.manager.AreaInfo;
import com.zhiduan.axp.dao.manager.bean.AreaBean;
import com.zhiduan.axp.dao.manager.bean.ProvinceBean;
import com.zhiduan.axp.dao.manager.mapper.AreaMapper;
import com.zhiduan.axp.dao.manager.mapper.CityMapper;
import com.zhiduan.axp.dao.manager.mapper.CountyMapper;
import com.zhiduan.axp.dao.manager.mapper.ProvinceMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.Area;

/**
 * @ClassName: AreaImpl
 * @Description: 区域服务实现
 * @author cy
 * @date 2016年4月19日 下午4:54:39
 */
@Service("area")
public class AreaImpl implements Area {

	@Autowired
	@Qualifier("areaMapper")
	private AreaMapper dao;
	@Autowired
	@Qualifier("provinceMapper")
	private ProvinceMapper provincemapper;
	@Autowired
	@Qualifier("cityMapper")
	private CityMapper citymapper;
	@Autowired
	@Qualifier("countyMapper")
	private CountyMapper countyMapper;
	/**
	 * @Description: 增加
	 * @param info
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.Area#addArea(com.zhiduan.axp.idl.managecenter.service.entity.AreaInfo)
	 */
	@Override
	public ResultInfo addArea(AreaInfo info) {
		ResultInfo result = null;
		AreaBean bean = new AreaBean();
		validate(info);
		BeanUtils.copyProperties(info, bean);
		int status = dao.isAreaExists(bean);
		if (status != 0) {
			throw new BusinessException("01040");
		}
		status = dao.insertSelective(bean);
		if (status == 0) {
			throw new BusinessException("01020");
		}
		result = new ResultInfo(0, "", "新增成功", "");
		return result;
	}

	/**
	 * @Description: 删除
	 * @param id
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.Area#delArea(com.zhiduan.axp.idl.managecenter.service.entity.AreaInfo)
	 */
	public ResultInfo delArea(Long id) {
		int status = dao.deleteByPrimaryKey(id);
		if (status == 0) {
			// 未查询相关信息，无法删除
			throw new BusinessException("01020");
		}
		return new ResultInfo(0, "", "删除成功", "");
	}

	/**
	 * @Description: 更改
	 * @param info
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.Area#updArea(com.zhiduan.axp.idl.managecenter.service.entity.AreaInfo)
	 */
	public ResultInfo updArea(AreaInfo info) {
		ResultInfo result = null;
		AreaBean bean = new AreaBean();
		validate(info);
		BeanUtils.copyProperties(info, bean);
		int status = dao.isAreaExists(bean);
		if (status != 0) {
			throw new BusinessException("01040");
		}
		status = dao.updateByPrimaryKeySelective(bean);
		result = new ResultInfo(0, "", "更新成功", "");
		if (status == 0) {
			// 未查询相关信息，无法更新
			throw new BusinessException("01020");
		}
		return result;
	}

	/**
	 * @Description: 根据主键查询
	 * @param info
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.Area#getAreaByPK(com.zhiduan.axp.idl.managecenter.service.entity.AreaInfo)
	 */
	public ResultInfo<Object> getAreaByPK(AreaInfo info) {
		Long areaId = info.getAreaId();
		AreaBean bean = dao.selectByPrimaryKey(areaId);
		if (bean != null) {
			return new ResultInfo<Object>(0, "", "根据主键查询成功", bean);
		} else {
			// 未查询到相关信息
			throw new BusinessException("01020");
		}
	}

	/**
	 * @Description: 查询所有
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.Area#getAll(UserInfo)
	 */
	public ResultInfo getAll() {

		List<AreaInfo> infoList = new ArrayList<>();
		List<AreaBean> beanList = dao.selectAll();
		for (AreaBean bean : beanList) {
			AreaInfo info = new AreaInfo();
			BeanUtils.copyProperties(bean, info);
			infoList.add(info);
		}
		return new ResultInfo(0, "", "查询所有成功", infoList);
	}
	/**
	 * 
	 * @Description: 事务性的级联删除该区域下的所有省份,城市,区县
	 * @param areaId
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void deleteAreaCascade(Long areaId) {
		delArea(areaId);
		List<ProvinceBean> provinces = provincemapper.selectAllProvince();
		List<Long> ids = new ArrayList<>();
		for(ProvinceBean province : provinces){
			if(province.getAreaId().equals(areaId)){
				ids.add(province.getProvinceId());
			}
		}
		if(!ids.isEmpty()){
			//删除省份信息
			provincemapper.batchDelete(ids);
			//根据省份id,删除城市
			List<Long> cids = citymapper.selectCidsByPids(ids);
			if(cids != null && !cids.isEmpty()){
				citymapper.batchDeleteByProvinceId(ids);
				//删除区县
				countyMapper.batchDelete(cids);
			}
			
		}
		
	}
	private void validate(AreaInfo info) {
		if (info == null || StringUtils.isBlank(info.getAreaName()) || info.getSortNo() == null
				|| StringUtils.isBlank(info.getBusinessPrincipal()) || StringUtils.isBlank(info.getContactPhone())) {
			// 新增错误，参数不能为空
			throw new BusinessException("111");
		}
	}
}