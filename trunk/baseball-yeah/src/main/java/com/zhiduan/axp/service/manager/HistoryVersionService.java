/**  
* @Title: HistoryVersionService.java
* @Package com.zhiduan.axp.service.manager
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月30日 下午3:31:36 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.service.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhiduan.axp.controller.model.HistoryVersionInfo;
import com.zhiduan.axp.controller.model.ResultInfo;

/**
* @ClassName: HistoryVersionService
* @Description: 
* @author heyi
* @date 2016年6月30日 下午3:31:36 
*
*/

public interface HistoryVersionService {
     
	/**
	 * 
	 * @Method: getList
	 * @Description: 获取版本号
	 * @param @param version
	 * @param @return    参数
	 * @return List<HistoryVersionInfo>    返回类型
	 * @throws
	 * @author heyi
	 * @date 2016年6月30日 下午3:41:29 
	 *
	 */
    List<HistoryVersionInfo> getList(HistoryVersionInfo version); 
    
    /**
     * 
     * @Method: addVersion
     * @Description: 添加版本号 
     * @param @param version
     * @param @return    参数
     * @return ResultInfo<String>    返回类型
     * @throws
     * @author heyi
     * @date 2016年6月30日 下午3:42:13 
     *
     */
    ResultInfo<String> addVersion(HttpServletRequest request);
    
    /**
     * 
     * @Method: updateVersion
     * @Description: 修改版本号
     * @param @param version
     * @param @return    参数
     * @return ResultInfo<String>    返回类型
     * @throws
     * @author heyi
     * @date 2016年6月30日 下午3:42:41 
     *
     */
    ResultInfo<String> updateVersion(HttpServletRequest request);
    
    /**
     * 
     * @Method: removeVersion
     * @Description: 删除版本号 
     * @param @param versionId
     * @param @return    参数
     * @return ResultInfo<String>    返回类型
     * @throws
     * @author heyi
     * @date 2016年6月30日 下午3:43:14 
     *
     */
    ResultInfo<String> removeVersion(HttpServletRequest request);
}
