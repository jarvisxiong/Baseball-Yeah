/**  
* @Title: OfficeMapper.java
* @package com.rofour.baseball.dao.officemanage.mapper
* @Project: baseball-yeah
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.dao.officemanage.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.controller.model.office.OfficeQueryInfo;
import com.rofour.baseball.dao.officemanage.bean.*;

/**
 * @ClassName: OfficeMapper
 * @Description 职务管理mapper
 * @author WJ
 * @date 2016年10月12日 下午2:12:05
 *
 */
@Named("officeMapper")
public interface OfficeMapper {
	/**
	 * 
	 * @Description: 查询全部记录
	 * @return List<OfficeBean>
	 *
	 */
	List<OfficeBean> queryAll(OfficeQueryInfo info);
	/**
	 * 
	 * @Description: 查询全部记录
	 * @return List<OfficeBean>
	 *
	 */
	List<OfficeBean> queryAllPuser(OfficeQueryInfo info);
	/**
	 * 查询CEO,COO总数
	 */
	int getTotal(OfficeQueryInfo info);
	/**
	 * 查询小派总数
	 */
	int queryAllPuserTotal(OfficeQueryInfo info);

	/**
	 * @Description 统计属下的小派
	 * @param  userId
	 * @return Integer    返回类型
	 **/
	    
	Integer queryUserTotal(Long userId);
	
	/**
	 * @Description 统计属下的小派和COO
	 * @param  userId
	 * @return Integer    返回类型
	 **/
	    
	Integer queryUserAndCOOTotal(Long userId);

	/**
	 * @Description 统计属下的商户和COO
	 * @param @param userId
	 **/
	    
	Integer queryStoreAndCOOTotal(Long userId);

	/**
	 * @Description 统计属下的商户
	 * @param @param userId
	 **/
	    
	Integer queryStoreTotal(Long userId);

	/**
	 * @Description 更新p_user中角色类型
	 * @param  userId
	 **/
	    
	int updateRoleType(Long userId);
	
	/**
	 * @Description 更新p_user中CEO
	 * @param  userId
	 **/
	    
	int updatePuserCEO(Long userId);
	
	/**
	 * @Description 更新p_user中COO
	 * @param  userId
	 **/
	    
	int updatePuserCOO(Long userId);
	
	/**
	 * @Description 更新tb_store_exp中COO
	 * @param  userId
	 **/
	    
	int updateStoreCOO(Long userId);
	/**
	 * @Description 更新tb_store_exp中CEO
	 * @param  userId
	 **/
	    
	int updateStoreCEO(Long userId);

	/**
	 * @Description 更新审核状态
	 * @param  map
	 **/
	    
	int updateAuditState(HashMap<String, Object> map);

	/**
	 * @Description 根据uid查询
	 * @param  userId
	 **/
	    
	OfficeDetailBean queryByUserId(Long userId);
	/**
	 * @Description 根据uid查询属下的小派
	 * @param  OfficeQueryInfo
	 **/
	    
	List<OfficeBean> queryAttached(OfficeQueryInfo info);
	/**
	 * @Description 根据uid查询属下的商户
	 * @param  OfficeQueryInfo
	 **/
	    
	List<OfficeStoreBean> queryAttachedStores(OfficeQueryInfo info);
	/**
	 * 
	 * @Description 删除属下的小派
	 * @param  map
	 *
	 */
	int deletePuserBoss(Map<String,Object> map);

	/**
	 * @Description 删除属下的商户
	 * @param  map
	 **/
	    
	int deleteStoreBoss(Map<String, Object> map);
	/**
	 * @Description 增加小派,支持批量
	 * @param  map
	 **/
	    
	int addPuserBoss(Map<String, Object> map);

    /**
     * @Description 更新小派角色信息
     * @param  map
     **/
    int updatePacketRole(HashMap<String, Object> map);

    /**
     * @Description 获取职务审核记录
     **/
    List<OfficeAuditInfo> getOfficeAudit(OfficeQueryInfo queryInfo);

    /**
     * @Description 获取职务审核记录条数
     **/
    int getOfficeAuditCount(OfficeQueryInfo queryInfo);

    /**
     * @Description 获取职务审核记录
     **/
    OfficeAuditBean getOfficeAuditById(Long auditId);
	
    List<OfficeBean> selectSameCollegeCEO(Map<String, String> map); 
}
