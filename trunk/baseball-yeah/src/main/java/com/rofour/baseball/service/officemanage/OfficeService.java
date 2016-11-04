/**  
* @Title: OfficeService.java
* @package com.rofour.baseball.service.officemanage
* @Project: baseball-yeah
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.service.officemanage;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.office.OfficeQueryInfo;
import com.rofour.baseball.dao.officemanage.bean.OfficeBean;
import com.rofour.baseball.dao.officemanage.bean.OfficeStoreBean;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @ClassName: OfficeService
* @Description 职务管理接口
* @author WJ
* @date 2016年10月12日 下午1:50:44 
*
*/

public interface OfficeService {

	/**
	 * @Description 查询
	 * @param  info
	 * @return List<AcctRefundBean>    返回类型
	 * @author WJ
	 * @date 2016年10月12日 下午1:56:02 
	 **/
	    
	List<OfficeBean> list(OfficeQueryInfo info);

	/**
	 * @Description 统计
	 * @param  info
	 * @return int    返回类型
	 * @author WJ
	 * @date 2016年10月12日 下午1:56:05 
	 **/
	    
	int getTotal(OfficeQueryInfo info);

	/**
	 * @Description 解除职务
	 * @return ResultInfo<Object>    返回类型
	 **/
	    
	ResultInfo<Object> dismiss(Long valueOf,int office);

	/**
	 * @Description 更新审核状态
	 **/

    void audit(Long auditId, Boolean isPass, Long managerId) throws Exception;

	/**
	 * @Description 职务编辑细节
	 * @param  userId
	 * @throws IOException 
	 **/
	    
	ResultInfo<Object> officeDetail(Long userId) throws IOException;
	/**
	 * 
	 * @Description 获取头像等图片信息
	 * @param  bizId
	 * @param  attachType
	 *
	 */
	ResultInfo<Object> getUrl(Long bizId,String attachType) throws IOException;
	/**
	 * 
	 * @Description 查询属下小派和coo
	 * @param  info
	 *
	 */
	public List<OfficeBean> queryAttached(OfficeQueryInfo info);
	/**
	 * 
	 * @Description 统计属下小派或者coo人数
	 * @param  info
	 *
	 */
	public int userTotal(OfficeQueryInfo info);
	/**
	 * 
	 * @Description 统计属下商户数
	 * @param  info
	 *
	 */
	public int storeTotal(OfficeQueryInfo info);

	/**
	 * @Description 查询属下的商户
	 * @param  info
	 **/
	    
	List<OfficeStoreBean> queryAttachedStores(OfficeQueryInfo info);

	/**
	 * @Description 小派删除,支持批量
	 * @param  map
	 **/
	    
	ResultInfo<Object> pDel(Map<String,Object> map);

	/**
	 * @Description  商户删除,支持批量
	 * @param  map
	 **/
	    
	ResultInfo<Object> sDel(Map<String, Object> map);

	/**
	 * @Description 查询普通小派
	 * @param  info
	 **/
	    
	List<OfficeBean> listPuser(OfficeQueryInfo info);

	/**
	 * @Description 统计小派人数
	 * @param  info
	 **/
	    
	int getPuserTotal(OfficeQueryInfo info);

	/**
	 * @Description 小派增加,支持批量
	 * @param  map
	 **/
	    
	ResultInfo<Object> pAdd(Map<String, Object> map);

    /**
     * @Description: 获取审核记录
     * @param
     * @return
     * @throws
     * @author ZXY
     */
    Map getOfficeAudit(OfficeQueryInfo queryInfo) throws Exception;
}
