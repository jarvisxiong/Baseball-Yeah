/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.service.manager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectExpressModel;
import com.rofour.baseball.controller.model.manager.ExpressCompanyInfo;

/**
 * 
 * @ClassName: ExpressService
 * @Description: 快递公司逻辑层接口定义
 * @author heyi
 * @date 2016年4月3日 下午2:18:46
 *
 */
public interface ExpressService {

	/**
	 * 
	 * @Description: 获取所有快递公司(分页)
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<ExpressCompanyInfo> GetExpressList(Integer limit, Integer offset);

	/**
	 * 
	 * @Description: 获取所有快递公司
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<ExpressCompanyInfo> GetExpressList();

	/**
	 * 
	 * @Description: 查询快递公司总数
	 * @return
	 */
	int selectTotalCount();

	/**
	 * @Description: 根据修改时间查询快递公司列表
	 * @param modifyTime
	 * @return
	 */

	public List<ExpressCompanyInfo> GetExpressListByModifyTime(String modifyTime);

	/**
	 * @Description: 新增快递公司
	 * @param model
	 * @param request
	 * @return
	 */

	ResultInfo Insert(ExpressCompanyInfo model, HttpServletRequest request);

	/**
	 * @Description: 修改快递公司
	 * @param model
	 * @param request
	 * @return
	 */

	ResultInfo updateByPrimaryKey(ExpressCompanyInfo model, HttpServletRequest request);

	/**
	 * @Description: 删除快递公司
	 * @param id
	 * @param request
	 * @return
	 */

	int deleteByPrimaryKey(Long id, HttpServletRequest request);

	/**
	 * @Description: 获取公司信息 @param @return Map<Long, String> @throws
	 */
	Map<Long, String> getCompanyMapInfo() throws Exception;

}
