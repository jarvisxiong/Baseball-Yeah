package com.rofour.baseball.service.officemanage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.controller.model.office.OfficeAcctQueryInfo;
import com.rofour.baseball.controller.model.office.OfficeStoreQueryInfo;
import com.rofour.baseball.dao.officemanage.bean.UserAcctBean;
import com.rofour.baseball.dao.officemanage.mapper.OfficeManageAssistMapper;
import com.rofour.baseball.service.officemanage.OfficeManageAssistSerivce;
/**
 * @ClassName: OfficeManageToolSerivceImpl
 * @Description: 职务管理辅助功能
 * @author: gechao
 * @date: 2016年10月11日 18:23
 */
@Service("officeManageAssistSerivce")
public class OfficeManageAssistSerivceImpl implements OfficeManageAssistSerivce {

//    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("officeManageAssistMapper")
    private OfficeManageAssistMapper officeManageAssistMappe;
	
	@Override
	public UserAcctBean getPartInfo(String userId) {
		UserAcctBean acctBean = new UserAcctBean(); 
		acctBean.setUserId(userId);
		acctBean = officeManageAssistMappe.getPartInfo(acctBean); 
		return acctBean;
	}

	@Override
	public List<UserAcctBean> getSmallPieList(String userId) {
		UserAcctBean acctBean = new UserAcctBean(); 
		acctBean.setUserId(userId);
		List<UserAcctBean> list = officeManageAssistMappe.getOfficeRoleType(acctBean);
		if(list != null) {
			if(list.size() > 0) {
				UserAcctBean userAcctBean = list.get(0);
				if(userAcctBean.getOfficeRoleType() == 3) {
					return officeManageAssistMappe.getCeoSmallPieList(acctBean);
				}
			}
		}
		return officeManageAssistMappe.getSmallPieList(acctBean);
	}

	@Override
	public List<UserAcctBean> getMyStoreList(String userId) {
		UserAcctBean acctBean = new UserAcctBean(); 
		acctBean.setUserId(userId);
		List<UserAcctBean> list = officeManageAssistMappe.getStoreList(acctBean);
		return list;
	}

	@Override
	public int insertOfficeManageAudit(UserAcctBean acctBean) throws Exception {
		int result = 0;
//		return officeManageAssistMappe.insertOfficeManageAudit(acctBean);
		UserAcctBean userAcctBean = officeManageAssistMappe.getOfficeManageAuditByUserId(acctBean);
		if(userAcctBean != null) {
			acctBean.setAuditId(userAcctBean.getAuditId());
			result = officeManageAssistMappe.updateOfficeManageAudit(acctBean);
		} else {
			result = officeManageAssistMappe.insertOfficeManageAudit(acctBean);
		}
		return result;
	}

	@Override
	public List<OfficeAcctQueryInfo> getAcctList(OfficeAcctQueryInfo acctQueryInfo) {
		return officeManageAssistMappe.getAcctList(acctQueryInfo);
	}

	@Override
	public Integer getAcctCount(OfficeAcctQueryInfo acctQueryInfo) {
		return officeManageAssistMappe.getAcctCount(acctQueryInfo);
	}

	@Override
	public List<OfficeStoreQueryInfo> getCooStoreList(OfficeStoreQueryInfo storeQueryInfo) {
		return officeManageAssistMappe.getCooStoreList(storeQueryInfo);
	}

	@Override
	public Integer getCooStoreCount(OfficeStoreQueryInfo storeQueryInfo) { 
		return officeManageAssistMappe.getCooStoreCount(storeQueryInfo);
	}

	@Override
	public List<OfficeStoreQueryInfo> getStoreList(OfficeStoreQueryInfo storeQueryInfo) {
		return officeManageAssistMappe.getAllStoreList(storeQueryInfo);
	}

	@Override
	public Integer getAllStoreCount(OfficeStoreQueryInfo storeQueryInfo) { 
		return officeManageAssistMappe.getAllStoreCount(storeQueryInfo);
	}

	@Override
	public int chooseStore(UserAcctBean acctBean) { 
		return officeManageAssistMappe.chooseStore(acctBean);
	}

	@Override
	public int delStore(UserAcctBean acctBean) {
		return officeManageAssistMappe.delStore(acctBean);
	}

	@Override
	public boolean validateCollegeCeo(UserAcctBean acctBean) {
		List<UserAcctBean> list = officeManageAssistMappe.validateCollegeCeo(acctBean);
		if(list != null) {
			if(list.size() > 0) {
				return true;
			}
		}
		return false; 
	}

	@Override
	public boolean validateOfficeManageAudit(UserAcctBean acctBean) {
		List<UserAcctBean> list = officeManageAssistMappe.validateOfficeManageAudit(acctBean);
		if(list != null) {
			if(list.size() > 0) {
				return true;
			}
		}
		return false; 
	}

	@Override
	public boolean validateOfficeRoleType(UserAcctBean acctBean) {
		List<UserAcctBean> list = officeManageAssistMappe.getOfficeRoleType(acctBean);
		if(list != null) {
			if(list.size() > 0) {
				UserAcctBean userAcctBean = list.get(0);
				// 1是小派
				if(userAcctBean.getOfficeRoleType() > 1) {
					return true;
				}
			}
		}
		return false;
	} 

}
