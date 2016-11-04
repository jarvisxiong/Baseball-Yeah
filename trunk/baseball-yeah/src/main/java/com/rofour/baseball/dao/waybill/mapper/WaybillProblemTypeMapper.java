/**  
* @Title: WaybillProblemTypeMapper.java
* @package com.rofour.baseball.dao.waybill.mapper
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年7月20日 上午9:22:25 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.dao.waybill.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.controller.model.waybill.WayBillProblemTypeInfo;

/**
* @ClassName: WaybillProblemTypeMapper
* @Description: 
* @author heyi
* @date 2016年7月20日 上午9:22:25 
*
*/
@Named("waybillProblemTypeMapper")
public interface WaybillProblemTypeMapper {
    List<WayBillProblemTypeInfo> selectWaybillProblemTypeList(WayBillProblemTypeInfo info);
    int insertWaybillProblem(WayBillProblemTypeInfo info);
    int updateWaybillProblem(WayBillProblemTypeInfo info);
    int deleteWaybillProblem(String problemTypeCode);
}
