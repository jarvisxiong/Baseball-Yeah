package com.zhiduan.axp.service.wallet.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.zhiduan.axp.dao.wallet.mapper.WalletVerifyMapper;
import com.zhiduan.axp.service.resource.ResourceService;
import com.zhiduan.axp.service.wallet.WalletVerifyService;
import com.zhiduan.axp.common.AxpUtils;
import com.zhiduan.axp.common.JsonUtils;
import com.zhiduan.axp.common.WebUtils;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.resource.CredentialURLInfo;
import com.zhiduan.axp.controller.model.user.ExpUserAuditResultInfo;
import com.zhiduan.axp.controller.model.wallet.WalletUserPerfectInfo;
import com.zhiduan.axp.controller.model.wallet.WalletVerifyInfo;;

@Service("walletVerifyService")
public class WalletVerifyServiceImpl implements WalletVerifyService {

	@Autowired
	@Qualifier(value="walletVerifyMapper")
	WalletVerifyMapper walletVerifyMapper;
    
	  @Autowired
	    private ResourceService resourceService;
	  

	    @Autowired
	    private WebUtils webUtils;
	  private static final Logger logger = LoggerFactory.getLogger(WalletVerifyServiceImpl.class);
	/* 获取钱包验证的用户
	 * @param map
	 * @return
	 * @see com.zhiduan.axp.service.wallet.WalletVerifyService#selectWalletVerify(java.util.Map)
	 */   
	@Override
	public List<WalletVerifyInfo> selectWalletVerify(WalletVerifyInfo searchInfo) {
		 List<WalletVerifyInfo> infoList=walletVerifyMapper.selectWalletVerify(searchInfo);
		 return infoList;
	}
	/**
	 * 获取钱包账户审核信息
	 */
	@Override
	public ResultInfo getWalletAudit(long userId) throws Exception {
		WalletUserPerfectInfo  walletUserPerfectInfo = new WalletUserPerfectInfo();
		//设置用户信息
		WalletVerifyInfo userInfo = setUserInfo(userId,  walletUserPerfectInfo);
		//设置证件信息
		setCredentialUrlInfo(userId,  walletUserPerfectInfo);
		return new ResultInfo(0, "", "查询钱包用户审核信息成功",  walletUserPerfectInfo);
	}
	private WalletVerifyInfo setUserInfo(long userId, WalletUserPerfectInfo walletUserPerfectInfo) throws IOException {
		WalletVerifyInfo uinfo = new WalletVerifyInfo();
		uinfo.setUserId(userId);
		//查询用户信息
		WalletVerifyInfo walletUserInfo = walletVerifyMapper.selectWalletUserInfo(uinfo);
		if (null != walletUserInfo) {
			AxpUtils.copyProperties(walletUserInfo, walletUserPerfectInfo);
			if(StringUtils.isNotEmpty(walletUserInfo.getVerifyInfo())){
				walletUserPerfectInfo.setAuditResultInfo(JsonUtils.readValue(walletUserInfo.getVerifyInfo(),ExpUserAuditResultInfo.class));
			}
		}
		return walletUserInfo;
	}

	/**
	 * 设置证件信息
	 * @param userId
	 * @param expUserPerfectInfo
	 */
	private void setCredentialUrlInfo(long userId, WalletUserPerfectInfo walletUserPerfectInfo) {
		ResultInfo credentialUrls = resourceService.getCredentialUrls(userId);
		if (null != credentialUrls && credentialUrls.getSuccess() == 0) {
			List<CredentialURLInfo> credentialURLInfo = (List<CredentialURLInfo>) credentialUrls.getData();
			if (CollectionUtils.isNotEmpty(credentialURLInfo)) {
				walletUserPerfectInfo.setPhotoList(credentialURLInfo);
			}
		}
	}
	@Override
	public ResultInfo walletAudit(WalletVerifyInfo searchInfo,HttpServletRequest request) throws Exception {
		try {
			//查询当前用户的审核状态
			/*WalletVerifyInfo presentWalletInfo = walletVerifyMapper.selectWalletUserInfo(searchInfo);
			long vstatus = (long)presentWalletInfo.getVerifyStatus();
			if(vstatus == 0){
				throw new BusinessException("02024");
			}else if(vstatus == 2){
				throw new BusinessException("02023");
			}*/
			int result=-1;
			WalletVerifyInfo presentWalletInfo = walletVerifyMapper.selectWalletUserInfo(searchInfo);//旧的edit数据源
            if(searchInfo.getUserType()==1)//货源
            {
              result=walletVerifyMapper.updateExpUserVerify(searchInfo);
              webUtils.userEditLog(request, 137, presentWalletInfo, searchInfo);
            }
            else if(searchInfo.getUserType()==3)//众包
            {
            	result=walletVerifyMapper.updatePuserVerify(searchInfo);
            	 webUtils.userEditLog(request, 137, presentWalletInfo, searchInfo);
            }
            else
            {
            	return new ResultInfo(-1, "-1", "钱包 用户审核信息失败", "");
            }
			logger.info("审核用户WalletVerifyInfo：{}",searchInfo);
			if (result > 0) {
			
				long vstatus = (long)presentWalletInfo.getVerifyStatus();
				if(vstatus == 0){
					return new ResultInfo(-1, "-1", "该账户未完善信息", "");
				}else if(vstatus == 2){
					result=walletVerifyMapper.updateAcctUserVerifyStatus(searchInfo.getUserId());
					 webUtils.userEditLog(request, 137, presentWalletInfo, searchInfo);
					if(result>0)
					{
						return new ResultInfo(-1, "-1", "钱包用户审核通过", "");
					}
					else
					{
						return new ResultInfo(-1, "-1", "钱包用户审核信息失败", "");
					}
				}
				else
				{
					return new ResultInfo(-1, "-1", "钱包用户审核信息失败", "");
				}
			} else {
				return new ResultInfo(-1, "-1", "钱包用户审核信息失败", "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}
