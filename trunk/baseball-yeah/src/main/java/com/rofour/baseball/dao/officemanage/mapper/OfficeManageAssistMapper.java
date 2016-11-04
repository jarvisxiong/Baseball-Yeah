package com.rofour.baseball.dao.officemanage.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.controller.model.office.OfficeAcctQueryInfo;
import com.rofour.baseball.controller.model.office.OfficeStoreQueryInfo;
import com.rofour.baseball.dao.officemanage.bean.UserAcctBean;


@Named("officeManageAssistMapper")
public interface OfficeManageAssistMapper {

	/**
	 * 职务详情
	 * @param acctBean
	 * @return
	 */
	public UserAcctBean getPartInfo(UserAcctBean acctBean);
	
	/**
	 * 所辖小派列表
	 * @param acctBean
	 * @return
	 */
	public List<UserAcctBean> getSmallPieList(UserAcctBean acctBean);
	
	/**
	 * 所辖商户列表
	 * @param acctBean
	 * @return
	 */
	public List<UserAcctBean> getStoreList(UserAcctBean acctBean);
	
	/**
	 * 插入审核记录
	 * @param acctBean
	 * @return
	 */
	public int insertOfficeManageAudit(UserAcctBean acctBean);
	
	/**
	 * 更新审核记录
	 * @param acctBean
	 * @return
	 */
	public int updateOfficeManageAudit(UserAcctBean acctBean) throws Exception;
	
	/**
	 * 获取审核记录
	 * @param acctBean
	 * @return
	 */
	public UserAcctBean getOfficeManageAuditByUserId(UserAcctBean acctBean) throws Exception;
	
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
	public List<OfficeStoreQueryInfo> getAllStoreList(OfficeStoreQueryInfo storeQueryInfo);
	 
	/**
	 * 选择商户统计
	 * @param acctQueryInfo
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
	public List<UserAcctBean> validateCollegeCeo(UserAcctBean acctBean); 
	
	/**
	 * 验证CEO/COO申请记录
	 * @param acctBean
	 * @return
	 */
	public List<UserAcctBean> validateOfficeManageAudit (UserAcctBean acctBean);
	
	/**
	 * 取小派身份
	 * @param acctBean
	 * @return
	 */
	public List<UserAcctBean> getOfficeRoleType (UserAcctBean acctBean);
	
	/**
	 * CEO所辖小派
	 * @param acctBean
	 * @return
	 */
	public List<UserAcctBean> getCeoSmallPieList (UserAcctBean acctBean);
}