/**  
* @Title: WaybillProblemTypeImpl.java
* @package com.rofour.baseball.service.waybill.impl
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年7月20日 上午10:21:10 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.service.waybill.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rofour.baseball.controller.model.waybill.WayBillProblemTypeInfo;
import com.rofour.baseball.dao.waybill.mapper.WaybillProblemTypeMapper;
import com.rofour.baseball.service.waybill.WaybillProblemTypeService;

/**
* @ClassName: WaybillProblemTypeImpl
* @Description: 问题件类型接口实现类
* @author heyi
* @date 2016年7月20日 上午10:21:10 
*
*/

@Service("waybillProblemTypeService")
public class WaybillProblemTypeImpl implements WaybillProblemTypeService {
    /**
     * 注入问题件类型
     */
	@Resource(name="waybillProblemTypeMapper")
	private WaybillProblemTypeMapper waybillProblemTypeMapper;
	
	/**
	 * 查询问题件类型
	 */
	@Override
	public List<WayBillProblemTypeInfo> selectWaybillProblemTypeList(WayBillProblemTypeInfo info) {
		try {
			return waybillProblemTypeMapper.selectWaybillProblemTypeList(info);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	 /**
	  * 插入问题件类型
	  */
	@Override
	public int insertWaybillProblemType(WayBillProblemTypeInfo info) {
	try {
		return waybillProblemTypeMapper.insertWaybillProblem(info);
	} catch (Exception e) {
		e.printStackTrace();
		return 0;
	}
	}

	/**
	 * 更新问题件类型
	 */
	@Override
	public int updateWaybillProblemType(WayBillProblemTypeInfo info) {
		try {
			return waybillProblemTypeMapper.updateWaybillProblem(info);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	 /**
	  * 删除问题件类型
	  */
	@Override
	public int deleteWaybillProblemType(String problemTypeCode) {
		try {
			return waybillProblemTypeMapper.deleteWaybillProblem(problemTypeCode);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
