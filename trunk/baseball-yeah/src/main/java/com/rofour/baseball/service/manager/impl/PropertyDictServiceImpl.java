/**
 * Copyright (c) 2016, 指端科技.
 */


package com.rofour.baseball.service.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.common.StringUtils;
import com.rofour.baseball.common.WebUtils;
import com.rofour.baseball.controller.model.manager.PropertyDictInfo;
import com.rofour.baseball.dao.manager.bean.PropertyDictBean;
import com.rofour.baseball.dao.manager.mapper.PropertyDictMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.PropertyDictService;

/**
 * @author heyi
 * @ClassName: PropertyDictServiceImpl
 * @Description: 属性字典维护业务逻辑接口实现类
 * @date 2016年3月25日 下午4:15:00
 */
@Service("propertyDictService")
public class PropertyDictServiceImpl implements PropertyDictService {

    @Autowired
    @Qualifier("propertyDictMapper")
    private PropertyDictMapper propertyDictMapper;
    @Autowired
    private WebUtils webUtils;

    /**
     * @return
     * @Description: 获取所有属性字典
     * @see com.rofour.baseball.service.manager.PropertyDictService#getPropertyDictList()
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
     * @return
     * @Description: 获取所有属性字典
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
     * @param model
     * @Description: 新增一条属性字典数据
     */
    public void insert(PropertyDictInfo model, HttpServletRequest request) {
        checkData(model);
        PropertyDictBean Bean = new PropertyDictBean();
        BeanUtils.copyProperties(model, Bean);
        //Map<String, Object> map = new HashMap<String, Object>();
        //map.put("propertyId", model.getPropertyId());
        //map.put("callAlias", model.getCallAlias());
        //map.put("propertyValue", model.getPropertyValue());
        //map.put("propertyId", model.getPropertyId());
        //map.put("add", 0);
        //map.put("propertyShortcode", model.getPropertyShortcode());
        //if (propertyDictMapper.checkPropertyDict(map) > 0) {
        //    throw new BusinessException("01005");
        //}
        checkPropertyDict(model,"add");
        propertyDictMapper.insert(Bean);
        webUtils.userAddLog(request, 28, Bean);
    }

    /**
     * @param model
     * @Description: 修改一条属性字典数据
     */
    public int updateByPrimaryKey(PropertyDictInfo model, HttpServletRequest request) {
        checkData(model);
        PropertyDictBean Bean = new PropertyDictBean();
        PropertyDictBean oldBean = propertyDictMapper.getPropertyDictListByPropertyId(model.getPropertyId());
        BeanUtils.copyProperties(model, Bean);
        //Map<String, Object> map = new HashMap<String, Object>();
        //map.put("callAlias", model.getCallAlias());
        //map.put("propertyValue", model.getPropertyValue());
        //map.put("propertyId", model.getPropertyId());
        //map.put("update", 1);
        //map.put("propertyShortcode", model.getPropertyShortcode());
        //if (propertyDictMapper.checkPropertyDict(map) > 0) {
        //    throw new BusinessException("01005");
        //}
        checkPropertyDict(model,"update");
        int result = propertyDictMapper.updateByPrimaryKey(Bean);
        webUtils.userEditLog(request, 28, oldBean, Bean);
        if (result == 0) {
            throw new BusinessException("01004");
        }
        return result;
    }

    /**
     * @param id
     * @return
     * @Description: 删除一条属性字典数据
     * @see com.rofour.baseball.service.manager.PropertyDictService#deleteByPrimaryKey(java.lang.String, HttpServletRequest)
     */
    public int deleteByPrimaryKey(String id, HttpServletRequest request) {
        if (id == null || "".equals(id)) {
            throw new BusinessException("111");
        }
        int result = propertyDictMapper.deleteByPrimaryKey(id);
        webUtils.userDeleteLog(request, 28, id);
        if (result == 0) {
            throw new BusinessException("01004");
        }
        return result;
    }

    /**
     * @param model
     * @Description: 验证所传参数是否合法
     */
    public void checkData(PropertyDictInfo model) {

        if (model.getSortNo() == null
                //|| model.getDescription() == null || model.getDescription().equals("")
                || model.getPropertyValue() == null || model.getPropertyValue().equals("")
                || model.getPropertyShortcode() == null || model.getPropertyShortcode().equals("")
                || model.getPropertyId() == null || model.getPropertyId().equals("")
                || model.getCallAlias() == null || model.getCallAlias().equals("")) {
            throw new BusinessException("111");
        }
        if (model.getCallAlias().length() > 20 || model.getPropertyId().length() > 20
                || model.getPropertyShortcode().length() > 20 || model.getPropertyValue().length() > 100
                || model.getDescription().length() > 100) {
            throw new BusinessException("112");
        }
        if (StringUtils.isChineseHave(model.getPropertyShortcode())) {
            throw new BusinessException("110");
        }
    }

    /**
     * 检验属性字典调用别名与值的组合是否重复，编码是否重复
     */
    /*private void checkPropertyDict(PropertyDictInfo model) throws BusinessException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("propertyId", model.getPropertyId());
        map.put("propertyShortcode", model.getPropertyShortcode());

        if (propertyDictMapper.checkPropertyDict(map) > 0) {
            throw new BusinessException("01043");
        }
        map.remove("propertyShortcode");
        map.put("callAlias", model.getCallAlias());
        map.put("propertyValue", model.getPropertyValue());

        if (propertyDictMapper.checkPropertyDict(map) > 0) {
            throw new BusinessException("01044");
        }
    }*/
    private void checkPropertyDict(PropertyDictInfo model,String oper) throws BusinessException {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("propertyId", model.getPropertyId());
    	map.put("callAlias", model.getCallAlias());
    	map.put("propertyValue", model.getPropertyValue());
    	map.put("propertyShortcode", model.getPropertyShortcode());
    	if("add".equals(oper)){
    		if (propertyDictMapper.checkPropertyDictPropertyId(map) > 0) {
    			//属性字典编码已存在
    			throw new BusinessException("10001");
    		}
    	}
    	if("update".equals(oper)){
    		map.put("update", 1);
    		if (propertyDictMapper.checkPropertyDictPropertyValue(map) > 0) {
    			//属性值已存在
    			throw new BusinessException("10002");
    		}
    		if (propertyDictMapper.checkPropertyDictPropertyShortcode(map) > 0) {
    			//属性简码已存在
    			throw new BusinessException("10003");
    		}
    	}
    }
}
