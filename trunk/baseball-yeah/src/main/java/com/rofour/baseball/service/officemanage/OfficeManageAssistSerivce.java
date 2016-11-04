package com.rofour.baseball.service.officemanage;

import java.util.List;

import com.rofour.baseball.controller.model.office.OfficeAcctQueryInfo;
import com.rofour.baseball.controller.model.office.OfficeStoreQueryInfo;
import com.rofour.baseball.dao.officemanage.bean.UserAcctBean;


public interface OfficeManageAssistSerivce {
	/**
	 * 职务详情
	 * @param userId
	 * @return
	 */
	public UserAcctBean getPartInfo(String userId);
	/**
	 * 所辖小派列表
	 * @param userId
	 * @return
	 */
	public List<UserAcctBean> getSmallPieList(String userId);
	
	/**
	 * 所辖商户列表
	 * @param userId
	 * @return
	 */
	public List<UserAcctBean> getMyStoreList(String userId);
	
	/**
	 * 插入审核记录
	 * @param acctBean
	 * @return
	 */
	public int insertOfficeManageAudit(UserAcctBean acctBean) throws Exception;
	
//	/**
//	 * 更新审核记录
//	 * @param acctBean
//	 * @return
//	 */
//	public int updateOfficeManageAudit(UserAcctBean acctBean) throws Exception;
//	
//	/**
//	 * 获取审核记录
//	 * @param acctBean
//	 * @return
//	 */
//	public int getOfficeManageAuditByUserId(UserAcctBean acctBean) throws Exception;
//	
	/**
	 * 选择账号列表
	 * @param acctQueryInfo
	 * @return
	 */
	public List<OfficeAcctQueryInfo> getAcctList(OfficeAcctQueryInfo acctQueryInfo);
	
	/**
	 * 选择账号统计
	 * @param acctQueryInfo
	 * @return
	 */
	public Integer getAcctCount(OfficeAcctQueryInfo acctQueryInfo);
	
	/**
	 * COO所辖商户列表
	 * @param storeQueryInfo
	 * @return
	 */
	public List<OfficeStoreQueryInfo> getCooStoreList(OfficeStoreQueryInfo storeQueryInfo);
	
	/**
	 * COO所辖商户统计
	 * @param storeQueryInfo
	 * @return
	 */
	public Integer getCooStoreCount(OfficeStoreQueryInfo storeQueryInfo);
	
	/**
	 * 选择商户列表
	 * @param storeQueryInfo
	 * @return
	 */
	public List<OfficeStoreQueryInfo> getStoreList(OfficeStoreQueryInfo storeQueryInfo);
	/**
	 * 选择商户统计
	 * @param storeQueryInfo
	 * @return
	 */
	public Integer getAllStoreCount(OfficeStoreQueryInfo storeQueryInfo);
	
	/**
	 * COO新增所辖商户
	 * @param acctBean
	 * @return
	 */
	public int chooseStore(UserAcctBean acctBean);
	
	/**
	 * COO删除所辖商户
	 * @param acctBean
	 * @return
	 */
	public int delStore(UserAcctBean acctBean);
	
	/**
	 * 验证学校下的CEO
	 * @param acctBean
	 * @return
	 */
	public boolean validateCollegeCeo(UserAcctBean acctBean); 
	
	/**
	 * 验证CEO/COO申请记录
	 * @param acctBean
	 * @return
	 */
	public boolean validateOfficeManageAudit(UserAcctBean acctBean);
	
	/**
	 * 验证小派身份
	 * @param acctBean
	 * @return true:CEO/COO， false:普通小派
	 */
	public boolean validateOfficeRoleType(UserAcctBean acctBean);
	
}
