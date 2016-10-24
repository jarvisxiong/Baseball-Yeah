/**  
* @Title: WaybillProblemTypeService.java
* @Package com.zhiduan.axp.service.waybill
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年7月20日 上午10:20:38 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.service.waybill;

import java.util.List;

import com.zhiduan.axp.controller.model.waybill.WayBillProblemTypeInfo;

/**
* @ClassName: WaybillProblemTypeService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author heyi
* @date 2016年7月20日 上午10:20:38 
*
*/

public interface WaybillProblemTypeService {
	     /**
	      * 
	      * @Method: selectWaybillProblemTypeList
	      * @Description:查询问题类型列表
	      * @param @param info
	      * @param @return    参数
	      * @return List<WayBillProblemTypeInfo>    返回类型
	      * @throws
	      * @author heyi
	      * @date 2016年7月20日 上午10:21:46 
	      *
	      */
	    List<WayBillProblemTypeInfo> selectWaybillProblemTypeList(WayBillProblemTypeInfo info);
	    /**
	     * 
	     * @Method: insertWaybillProblemType
	     * @Description: 插入问题件类型
	     * @param @param info
	     * @param @return    参数
	     * @return int    返回类型
	     * @throws
	     * @author heyi
	     * @date 2016年7月20日 上午10:22:15 
	     *
	     */
	    int insertWaybillProblemType(WayBillProblemTypeInfo info);
	    /**
	     * 
	     * @Method: updateWaybillProblemType
	     * @Description: 更新问题件类型
	     * @param @param info
	     * @param @return    参数
	     * @return int    返回类型
	     * @throws
	     * @author heyi
	     * @date 2016年7月20日 上午10:22:29 
	     *
	     */
	    int updateWaybillProblemType(WayBillProblemTypeInfo info);
	    /**
	     * 
	     * @Method: deleteWaybillProblemType
	     * @Description: 删除问题件类型
	     * @param @param info
	     * @param @return    参数
	     * @return int    返回类型
	     * @throws
	     * @author heyi
	     * @date 2016年7月20日 上午10:22:43 
	     *
	     */
	    int deleteWaybillProblemType(String problemTypeCode);
}
