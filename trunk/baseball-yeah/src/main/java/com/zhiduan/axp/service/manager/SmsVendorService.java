/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.SmsVendorInfo;

/**
 * @ClassName: TbSmsVendorService
 * @Description: 管理中心--短信供应商服务接口
 * @author xl
 * @date 2016年3月28日 上午9:45:18
 *
 */

public interface SmsVendorService {

	/**
	 * @Description:删除短信供应商
	 * @param smsVendorId
	 * @return
	 */

	int deleteByPrimaryKey(String smsVendorId,HttpServletRequest request);

	/**
	 * @Description: 新增短信供应商
	 * @param record
	 * @return
	 */

	ResultInfo insert(SmsVendorInfo record,HttpServletRequest request);

	/**
	 * @Description:按主键查询短信供应商信息
	 * @param smsVendorId
	 * @return
	 */

	SmsVendorInfo selectByPrimaryKey(String smsVendorId);

	/**
	 * @Description: 更新短信供应商信息
	 * @param record
	 * @return
	 */

	ResultInfo updateByPrimaryKey(SmsVendorInfo record,HttpServletRequest request);

	/**
	 * @param info 
	 * @Description: 获取短信供应商列表
	 * @return
	 */

	List<SmsVendorInfo> selectAll(SmsVendorInfo info);
	/**
	 * 
	 * @Description: 根剧条件查找总数
	 * @param info
	 * @return
	 */
	int getTotal(SmsVendorInfo info);
}