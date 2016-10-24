/**
 * Copyright (c) 2016, 指端科技.
 */


package com.zhiduan.axp.dao.manager.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.dao.manager.bean.PropertyDictBean;
import org.apache.ibatis.annotations.Param;

/**
 * @author heyi
 * @ClassName: PropertyDictMapper
 * @Description: 属性字典接口定义
 * @date 2016年4月5日 上午11:30:11
 */
@Named("propertyDictMapper")
public interface PropertyDictMapper {
	/**
	 *
	 * @Description: 获取所有属性字典
	 * @return List<PropertyDictBean>
	 */
    List<PropertyDictBean> getPropertyDictList();

    /**
     *
     * @Description: 新增属性字典
     * @param bean
     * @return int
     */
    int insert(PropertyDictBean bean);

    /**
     *
     * @Description: 更新属性字典
     * @param bean
     * @return 更新的数量
     */
    int updateByPrimaryKey(PropertyDictBean bean);

    /**
     *
     * @Description: 删除数据
     * @param id
     * @return 删除的数量
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @Description: 检验属性字典调用别名与值的组合是否重复，编码是否重复，编号是否重复
     * @param map 条件
     * @return 重复的数量
     */
    int checkPropertyDict(Map<String, Object> map);

    /**
     *
     * @Description: 根据字典ID和调用别名查询字典对象
     * @param id
     * @param callAlias
     * @return PropertyDictBean
     */
    PropertyDictBean findByIdAndCallAlias(String id, String callAlias);

    PropertyDictBean getPropertyDictListByPropertyId(String PropertyId);

    /**
     * @Description: 根据组别获取所有属性字典
     * @return
     *
     */
     List<PropertyDictBean> getDictListbyCallAlias(@Param("callAlias") String callAlias);
}
