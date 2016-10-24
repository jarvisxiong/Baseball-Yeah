/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.dao.manager.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.dao.manager.bean.ExpressBean;

/**
 * 
 * @ClassName ExpressMapper
 * @Description 快递公司映射接口
 * @author heyi
 * @date 2016年4月5日 上午11:27:46
 *
 */
@Named("expressMapper")
public interface ExpressMapper {
	/**
	 * 
	 * @Description: 获取所有快递公司(分页)
	 * @param limit
	 * @param offset
	 * @return
	 */
	List<ExpressBean> GetExpressList(Integer limit, Integer offset);

	/**
	 * 
	 * @Description: 获取所有快递公司
	 * @param limit
	 * @param offset
	 * @return
	 */
	List<ExpressBean> GetExpressList();
	
	/**
	 * 
	 * @Description: 根据ID查询快递公司
	 * @return
	 */
	ExpressBean getExpressById(Long id);

	/**
	 * 
	 * @Description: 查询快递公司总数
	 * @return
	 */
	int selectTotalCount();

	/**
	 * 
	 * @Description: 根据修改时间获取快递集合
	 * @param modifyTime
	 * @return List<ExpressBean>
	 */
	List<ExpressBean> GetExpressListByModifyTime(String modifyTime);

	/**
	 * 
	 * @Description: 新增快递公司
	 * @param bean
	 * @return int
	 */
	int Insert(ExpressBean bean);

	/**
	 * 
	 * @Description: 修改快递公司
	 * @param bean
	 * @return 更新的数量
	 */
	int updateByPrimaryKey(ExpressBean bean);

	/**
	 * 
	 * @Description: 删除快递公司
	 * @param id
	 * @return 删除的数量
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 
	 * @Description: 判断快递公司的全称，简称，编码是否重复
	 * @param map
	 * @return 重复的数量
	 */
	int isExpressExist(Map<String, Object> map);
	
	List<ExpressBean> GetAllEnabledExpressList();
}
