/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.controller.model.manager.SmsVendorInfo;
import com.rofour.baseball.dao.manager.bean.SmsVendorBean;

/**
 * @ClassName: TbSmsVendorMapper
 * @Description: 短信供应商数据库查询接口
 * @author xl
 * @date 2016年3月28日 上午9:39:04
 *
 */
@Named("smsVendorMapper")
public interface SmsVendorMapper {

	/**
	 * @Description: 按主键删除
	 * @param smsVendorId
	 * @return 删除的数量
	 **/

	int deleteByPrimaryKey(String smsVendorId);

	/**
	 * @Description: 新增
	 * @param record
	 * @return int 
	 **/

	int insert(SmsVendorBean record);

	/**
	 * @Description: 按主键查询
	 * @param smsVendorId
	 * @return TbSmsVendorBean 
	 **/

	SmsVendorBean selectByPrimaryKey(String smsVendorId);

	/**
	 * @Description: 更新短信供应商信息
	 * @param record
	 * @return 更新的数量
	 **/

	int updateByPrimaryKey(SmsVendorBean record);

	/**
	 * @Description: 查询短信供应商列表
	 * @return List<TbSmsVendorBean>
	 **/
	List<SmsVendorBean> selectAll(SmsVendorInfo info);

	/**
	 * @Description: 校验供应商编码，名称，登录名唯一
	 * @param record
	 * @return 重复的数量
	 **/

	int isSmsVendorExist(Map<String, Object> map);
	/**
	 * 
	 * @Description:根据条件查询个数
	 * @param info
	 * @return
	 */
	int getTotal(SmsVendorInfo info);
}