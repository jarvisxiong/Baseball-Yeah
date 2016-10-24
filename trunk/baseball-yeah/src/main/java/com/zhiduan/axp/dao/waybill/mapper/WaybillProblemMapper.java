/**  
* @Title: WaybillProblemMapper.java
* @Package com.zhiduan.axp.dao.waybill.mapper
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年7月20日 上午9:22:25 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.dao.waybill.mapper;

import java.util.List;

import javax.inject.Named;

import com.zhiduan.axp.controller.model.waybill.WayBillProblemTypeInfo;

/**
* @ClassName: WaybillProblemMapper
* @Description: 
* @author heyi
* @date 2016年7月20日 上午9:22:25 
*
*/
@Named("waybillProblemTypeMapper")
public interface WaybillProblemMapper {
    List<WayBillProblemTypeInfo> selectWaybillProblemTypeList(WayBillProblemTypeInfo info);
    int insertWaybillProblem(WayBillProblemTypeInfo info);
    int updateWaybillProblem(WayBillProblemTypeInfo info);
    int deleteWaybillProblem(String problemTypeCode);
}
