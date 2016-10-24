/**
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.common.StringUtils;
import com.zhiduan.axp.common.WebUtils;
import com.zhiduan.axp.controller.model.manager.PropertyDictInfo;
import com.zhiduan.axp.dao.manager.bean.PropertyDictBean;
import com.zhiduan.axp.dao.manager.mapper.PropertyDictMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.PropertyDictService;

/**
 *
 * @ClassName: PropertyDictServiceImpl
 * @Description: 属性字典维护业务逻辑接口实现类
 * @author heyi
 * @date 2016年3月25日 下午4:15:00
 *
 */
@Service("propertyDictService")
public class PropertyDictServiceImpl implements PropertyDictService {

	@Autowired
	@Qualifier("propertyDictMapper")
	private PropertyDictMapper propertyDictMapper;
    @Autowired
    private WebUtils webUtils;
	/**
	 * @Description: 获取所有属性字典
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.PropertyDictService#GetPropertyDictList()
	 */
	public List<PropertyDictInfo> getPropertyDictList() {

		List<PropertyDictBean> list = propertyDictMapper.getPropertyDictList();
		List<PropertyDictInfo> datalist = new ArrayList<PropertyDictInfo>();
		for (PropertyDictBean item : list) {
			PropertyDictInfo dict = new PropertyDictInfo();
			BeanUtils.copyProperties(item, dict);
			datalist.add(dict);
		}
		return datalist;
	}

	/**
	 * @Description: 获取所有属性字典
	 * @return
	 *
	 */
	public List<PropertyDictInfo> getDictListbyCallAlias(String callAlias) {

		List<PropertyDictBean> list = propertyDictMapper.getDictListbyCallAlias(callAlias);
		List<PropertyDictInfo> datalist = new ArrayList<>();
		for (PropertyDictBean item : list) {
			PropertyDictInfo dict = new PropertyDictInfo();
			BeanUtils.copyProperties(item, dict);
			datalist.add(dict);
		}
		return datalist;
	}
	/**
	 * @Description: 新增一条属性字典数据
	 * @param model
	 */
	public void insert(PropertyDictInfo model,HttpServletRequest request) {
		checkData(model);
		PropertyDictBean Bean = new PropertyDictBean();
		BeanUtils.copyProperties(model, Bean);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("propertyId", model.getPropertyId());
		map.put("callAlias", model.getCallAlias());
		map.put("propertyValue", model.getPropertyValue());
		map.put("propertyId", model.getPropertyId());
		map.put("add", 0);
		map.put("propertyShortcode", model.getPropertyShortcode());
		if (propertyDictMapper.checkPropertyDict(map) > 0) {
			 throw new BusinessException("01005");
		}
		propertyDictMapper.insert(Bean);
		webUtils.userAddLog(request, 28, Bean);
	}

	/**
	 * @Description: 修改一条属性字典数据
	 * @param model
	 */
	public int updateByPrimaryKey(PropertyDictInfo model,HttpServletRequest request) {
		checkData(model);
		PropertyDictBean Bean = new PropertyDictBean();
		PropertyDictBean oldBean = propertyDictMapper.getPropertyDictListByPropertyId(model.getPropertyId());
		BeanUtils.copyProperties(model, Bean);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("callAlias", model.getCallAlias());
		map.put("propertyValue", model.getPropertyValue());
		map.put("propertyId", model.getPropertyId());
		map.put("update", 1);
		map.put("propertyShortcode", model.getPropertyShortcode());
		if (propertyDictMapper.checkPropertyDict(map) > 0) {
			throw new BusinessException("01005");
		}
		int result = propertyDictMapper.updateByPrimaryKey(Bean);
		webUtils.userEditLog(request, 28, oldBean, Bean);
		if(result == 0){
			throw new BusinessException("01004");
		}
		return result;
	}

	/**
	 * @Description: 删除一条属性字典数据
	 * @param id
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.PropertyDictService#deleteByPrimaryKey(java.lang.String)
	 */
	public int deleteByPrimaryKey(String id,HttpServletRequest request) {
		if(id == null || "".equals(id)){
			throw new BusinessException("111");
		}
		int result = propertyDictMapper.deleteByPrimaryKey(id);
		webUtils.userDeleteLog(request, 28, id);
		if(result == 0){
			throw new BusinessException("01004");
		}
		return result;
	}
	/**
	 *
	 * @Description: 验证所传参数是否合法
	 * @param model
	 */
	public void checkData(PropertyDictInfo model) {

		if (model.getSortNo() == null
				|| model.getDescription() == null || model.getDescription().equals("")
				|| model.getPropertyValue() == null || model.getPropertyValue().equals("")
				|| model.getPropertyShortcode() == null || model.getPropertyShortcode().equals("")
				|| model.getPropertyId() == null || model.getPropertyId().equals("")
				|| model.getCallAlias() == null || model.getCallAlias().equals("") ) {
			throw new BusinessException("111");
		}
		if (model.getCallAlias().length() > 20 ||  model.getPropertyId().length() > 20
				|| model.getPropertyShortcode().length() > 20 || model.getPropertyValue().length() > 100
				|| model.getDescription().length() > 100) {
			throw new BusinessException("112");
		}
		if (StringUtils.isChineseHave(model.getPropertyShortcode())) {
			throw new BusinessException("110");
		}
	}
}
