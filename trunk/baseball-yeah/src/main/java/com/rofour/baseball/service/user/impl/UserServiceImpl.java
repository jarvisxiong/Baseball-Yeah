package com.rofour.baseball.service.user.impl;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.*;
import com.rofour.baseball.controller.model.*;
import com.rofour.baseball.controller.model.user.*;
import com.rofour.baseball.dao.user.bean.*;
import com.rofour.baseball.dao.user.mapper.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.rofour.baseball.controller.model.resource.CredentialURLInfo;
import com.rofour.baseball.controller.model.store.AxpStoreInfo;
import com.rofour.baseball.controller.model.store.SearchStoreInfo;
import com.rofour.baseball.controller.model.store.StoreInfo;
import com.rofour.baseball.dao.manager.bean.CollegeBean;
import com.rofour.baseball.dao.manager.mapper.SysParameterMapper;
import com.rofour.baseball.dao.officemanage.bean.OfficeBean;
import com.rofour.baseball.dao.officemanage.mapper.OfficeMapper;
import com.rofour.baseball.dao.store.bean.StoreUserManagerBean;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.resource.ResourceService;
import com.rofour.baseball.service.store.StoreService;
import com.rofour.baseball.service.user.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangLei
 * @ClassName: TbUserServiceImpl
 * @Description: 用户中心服务层
 * @date 2016年3月22日 下午3:09:47
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    // token有效时间
    private static final Long tokenTime = 12L;
    // token有效时间单位，取值:TimeUnit.HOURS,TimeUnit.SECONDS,TimeUnit.MINUTES,TimeUnit.DAYS
    private static final TimeUnit timeUnit = TimeUnit.HOURS;
    //注册密码正则表达式6-30位数字和字母组成
    private static final String  REGISTE_REGEX_TEMP = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,30}$";
    //注册密码正则表达式6-30位数字或字母组成
    private static final String  REGISTE_REGEX = "^[0-9a-zA-Z]{6,30}$";
    /**
     * @Fields tbUserMapper : 注入用户数据操作服务
     */
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

	@Autowired
	@Qualifier("userSmsModelMapper")
	private UserSmsModelMapper userSmsModelMapper;

	@Autowired
	private WebUtils webUtils;
    /**
     * @Fields sysParameterMapper : 注入系统参数操作服务
     */
    @Autowired
    @Qualifier("tbSysParameterMapper")
    private SysParameterMapper sysParameterMapper;
    /**
     * @Fields tbUserTypeMapper : 注入用户类型数据操作服务
     */
    @Autowired
    @Qualifier("userTypeMapper")
    private UserTypeMapper userTypeMapper;
    /**
     * @Fields tbUserStoreExpMapper : 注入用户商户关系服务
     */
    @Autowired
    @Qualifier("userStoreExpMapper")
    private UserStoreExpMapper userStoreExpMapper;

    @Autowired
    @Qualifier("userOfPotentialMapper")
    private UserOfPotentialMapper userOfPotentialMapper;
    /**
     * @Fields storeService : 注入商户中心服务
     */
    @Autowired
    @Qualifier("storeService")
    private StoreService storeService;
    @Autowired
    private ResourceService resourceService;

    /**
     * @Fields storeService : 注入用户日志服务
     */
    @Autowired
    @Qualifier("loginLogMapper")
    private LoginLogMapper loginLogMapper;

    @Autowired
    private OfficeMapper officeMapper;
   /* @Autowired
    @Qualifier("taskExecutor")
    private TaskExecutor taskExecutor;*/
	/**
	 * @return 审核列表用户信息
	 * @Description 获取审核列表用户信息
	 */
	public List<UserCheckInfo> getAuditUsers(UserCheckInfo user) {
		List<UserCheckInfo> dataList = new ArrayList<UserCheckInfo>();
		UserCheckBean userCheckBean=new UserCheckBean();
		AxpUtils.copyProperties(user, userCheckBean);
		List<UserCheckBean> storeUserManagerBeanList = userMapper.userRegisterCheck(userCheckBean);
		if (CollectionUtils.isNotEmpty(storeUserManagerBeanList)) {
			for (UserCheckBean item : storeUserManagerBeanList) {
				UserCheckInfo userCheckInfo = new UserCheckInfo();
				AxpUtils.copyProperties(item, userCheckInfo);
				dataList.add(userCheckInfo);
			}
		}
		return dataList;
	}

	@Override
	public int getAuditUsersCount(UserCheckInfo user) throws Exception {
		List<UserCheckInfo> dataList = new ArrayList<UserCheckInfo>();
		UserCheckBean userCheckBean=new UserCheckBean();
		AxpUtils.copyProperties(user, userCheckBean);
		return  userMapper.userRegisterCheckCount(userCheckBean);
	}

	/**
     * @return 站点员工信息
     * @Description 获取站点员工信息
     */
    public List<StoreUserManagerInfo> getStoreUsers() {
        List<StoreUserManagerInfo> dataList = new ArrayList<StoreUserManagerInfo>();
            List<StoreUserManagerBean> storeUserManagerBeanList = userMapper.selectAll();
            if (CollectionUtils.isNotEmpty(storeUserManagerBeanList)) {
                for (StoreUserManagerBean item : storeUserManagerBeanList) {
                    StoreUserManagerInfo storeUserManagerInfo = new StoreUserManagerInfo();
                    AxpUtils.copyProperties(item, storeUserManagerInfo);
                    dataList.add(storeUserManagerInfo);
                }
            }
  
        return dataList;
    }


    /**
     * @param userInfo
     *            用户信息
     * @return
     * @Description:验证用户是否已经注册
     */
    public boolean ifUserExist(UserInfo userInfo) {
        try {
            return !(CollectionUtils.isNotEmpty(userMapper.selectByPhone(userInfo.getPhone())));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new BusinessException("02015");
        }
    }
	@Override
	public ResultInfo registeBeSupervisor(UserInfo userInfo, StoreInfo storeInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UserInfo loginIn(UserInfo userInf) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo changePwd(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int resetPwd(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int cancleUser(UserInfo useriInfo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ResultInfo registeEmployed(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo registeServerStu(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo registeStu(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int changePnByPwd(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int changePnByPn(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean checkUserAndPwd(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public UserInfo selectUserInfo(UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UserInfo selectUserInfoRetain(UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo changeBindPhone(UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo deleteById(StoreUserManagerInfo storeUserManagerInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo updateByPrimaryKeySelective(StoreUserManagerInfo storeUserManagerInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo insertSortUserManager(StoreUserManagerInfo storeUserManagerInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo initPwd(String userName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo verifyUser(List<StoreUserManagerInfo> list) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo expRegiste(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo headPrefectInfo(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo empPrefectInfo(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultInfo expAudit(ExpUserAuditInfo userAuditInfo, HttpServletRequest request) throws Exception {
		try {
			//查询当前用户的审核状态
			UserStoreExpBean userStoreExp = userStoreExpMapper.selectByPrimaryKey(Long.parseLong(userAuditInfo.getUserId()));
			long vstatus = (long)userStoreExp.getVerifyStatus();
			if(vstatus == 0){
				throw new BusinessException("02024");
			}else if(vstatus == 2){
				throw new BusinessException("02023");
			}

			UserStoreExpBean userStoreExpBean = AxpUtils.copyProperties(userAuditInfo, UserStoreExpBean.class);
			userStoreExpBean.setStoreId(userStoreExp.getStoreId());
			userStoreExpBean.setVerifyStatus(Byte.parseByte(userAuditInfo.getVerifyStatus()));
			userStoreExpBean.setVerifyUserId(userAuditInfo.getVerifyUserId());
			Date date=new Date();
			userStoreExpBean.setVerifyTime(date);
			userStoreExpBean.setUserId(Long.parseLong(userAuditInfo.getUserId()));
			userStoreExpBean.setVerifyInfo(userAuditInfo.getVerifyInfoString());
			userStoreExpBean.setVerifyRemark(userAuditInfo.getVerifyRemark());
			logger.info("审核用户userStoreExpBean：{}",userStoreExpBean);
			int result = userStoreExpMapper.updateByPrimaryKeySelective(userStoreExpBean);
			if (result != 0) {
				webUtils.userLog(request, String.format("用户ID：%s 审核状态由%s 改为：%s",userAuditInfo.getUserId(), vstatus,userAuditInfo.getVerifyStatus()),
						7,"","",Permission.AUDIT.name());
				Map<String, String> map = new HashMap<String, String>();
				map.put("userId", userAuditInfo.getUserId());
				map.put("storeId", userStoreExp.getStoreId().toString());
				map.put("userType", "1");
				String url = Constant.axpurl.get("suppy_audit_serv");
				// 定义反序列化 数据格式
				final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
				};
				ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
				// 返回参数赋值
				if (data.getSuccess() < 0) {
					return new ResultInfo(-1, "0", "调用AXP接口失败", "");
				}
				return new ResultInfo(0, "0", "用户审核信息成功", "");
			} else {
				return new ResultInfo(-1, "-1", "用户审核信息失败", "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	@Override
	public ResultInfo getAudit(long userId) throws Exception {
		// TODO Auto-generated method stub

		ExpUserPerfectInfo expUserPerfectInfo = new ExpUserPerfectInfo();
		//设置用户信息
		UserInfo userInfo = setUserInfo(userId, expUserPerfectInfo);
		//查询商户信息
		SearchStoreInfo searchStoreInfo = setStoreInfo(expUserPerfectInfo, userInfo);
		//设置学校信息
		setCollegeInfo(expUserPerfectInfo, searchStoreInfo);
		//设置证件信息
		setCredentialUrlInfo(userId, expUserPerfectInfo);
		return new ResultInfo(0, "", "查询用户审核信息成功", expUserPerfectInfo);

	}

	private SearchStoreInfo setStoreInfo(ExpUserPerfectInfo expUserPerfectInfo, UserInfo userInfo) throws Exception {
		SearchStoreInfo searchStoreInfo = storeService.selectStoreExpressByPhone(userInfo.getPhone());
		if (null != searchStoreInfo) {
			AxpUtils.copyProperties(searchStoreInfo, expUserPerfectInfo);
			expUserPerfectInfo.setExpressCompanyName(searchStoreInfo.getExpressName());
		}
		return searchStoreInfo;
	}

	@Override
	public boolean addUserOfPotentialAsync(List<UserOfPotentialBean> beanList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	private void setCollegeInfo(ExpUserPerfectInfo expUserPerfectInfo, SearchStoreInfo searchStoreInfo) throws Exception {
		List<CollegeBean> collegeBeanList = new ArrayList<>();
		//查询学校列表
		List<AxpStoreInfo> axpStoreInfoList = storeService.selectCollegesByStoreId(Long.parseLong(searchStoreInfo.getStoreId()));
		if (CollectionUtils.isNotEmpty(axpStoreInfoList)) {
			for (AxpStoreInfo axpStoreInfo : axpStoreInfoList) {
				CollegeBean collegeBean = new CollegeBean();
				collegeBean.setCollegeId(Long.parseLong(axpStoreInfo.getCollegeId()));
				collegeBean.setFullName(axpStoreInfo.getCollegeName());
				collegeBeanList.add(collegeBean);
			}
			expUserPerfectInfo.setCollegeList(collegeBeanList);
		}
	}

	private UserInfo setUserInfo(long userId, ExpUserPerfectInfo expUserPerfectInfo) throws IOException {
		UserInfo uinfo = new UserInfo();
		uinfo.setUserId(userId);
		uinfo.setUserType(1);
		//查询用户信息
		UserInfo userInfo = userMapper.selectUserInfo(uinfo);
		if (null != userInfo) {
			AxpUtils.copyProperties(userInfo, expUserPerfectInfo);
			if(StringUtils.isNotEmpty(userInfo.getVerifyInfo())){
				expUserPerfectInfo.setAuditResultInfo(JsonUtils.readValue(userInfo.getVerifyInfo(),ExpUserAuditResultInfo.class));
			}
		}
		return userInfo;
	}

	/**
	 * 设置证件信息
	 * @param userId
	 * @param expUserPerfectInfo
	 */
	private void setCredentialUrlInfo(long userId, ExpUserPerfectInfo expUserPerfectInfo) {
		ResultInfo credentialUrls = resourceService.getCredentialUrls(userId);
		if (null != credentialUrls && credentialUrls.getSuccess() == 0) {
			try {
				List<CredentialURLInfo> credentialURLInfo = (List<CredentialURLInfo>) credentialUrls.getData();
				if (CollectionUtils.isNotEmpty(credentialURLInfo)) {
					expUserPerfectInfo.setPhotoList(credentialURLInfo);
				}
			} catch (Exception e ){
				//没查到图片返回空
			}
		}
	}

	public List<UserSmsModelBean> getUserSmsModel(UserSmsModelBean modelBean){
		return userSmsModelMapper.getUserSmsModel(modelBean);
	}

	public int getUserSmsModelCount(UserSmsModelBean modelBean){
		return userSmsModelMapper.getUserSmsModelCount(modelBean);
	}

	public List<UserSmsModelBean> getUserSms(UserSmsModelBean modelBean){
		return userSmsModelMapper.getUserSms(modelBean);
	}

	public int getUserSmsCount(UserSmsModelBean modelBean){
		return userSmsModelMapper.getUserSmsCount(modelBean);
	}

	/**
	 * 查询收件人信息
	 * @param info
	 * @return
     */
	@Override
	public List<AddresseeInfo> getAddresseeInfo(AddresseeInfo info) {
		AddresseeBean bean=new AddresseeBean();
		BeanUtils.copyProperties(info,bean);
		List<AddresseeBean> beanList=userMapper.selectAddresseeInfo(bean);
		List<AddresseeInfo> newList=new ArrayList<>();
		for (AddresseeBean addresseeBean : beanList) {
			AddresseeInfo newInfo=new AddresseeInfo();
			BeanUtils.copyProperties(addresseeBean,newInfo);
			newList.add(newInfo);
		}
        return newList;
	}

	/**
	 * 获取收件人数目
	 * @param info
	 * @return
     */
	@Override
	public int selectAddresseeCount(AddresseeInfo info) {
		AddresseeBean bean=new AddresseeBean();
		BeanUtils.copyProperties(info,bean);
		return userMapper.selectAddresseeCount(bean);
	}

	/**
	 * 更新收件人用户状态
	 * @param map
	 * @return
     */
	@Override
	public ResultInfo updateCuserStatus(Map<String, String> map) {
		int result=userTypeMapper.updateCuserStatus(map);
		return result>0?new ResultInfo(0,"操作成功",""):new ResultInfo(-1,"操作失败","");
	}

	/**
	 * 更新众包人用户状态
	 * @param map
	 * @return
     */    
	@Override
	public ResultInfo<Object> updatePuserStatus(Map<String, String> map) {
		String beEnabled = map.get("beEnabled");
		String office = map.get("office");
		if(beEnabled.equals("1") && office.equals("3")){ //启用 CEO 1激活,0未激活  office,1:普通小派 ,2:校园COO,3: 校园CEO
			if(checkCollegeCeo(map)){ //已经存在
				return new ResultInfo<Object>(-1,"","该校区已启用CEO");
			}
		}
		int result=userTypeMapper.updatePuserStatus(map);
		return result>0?new ResultInfo<Object>(0,"操作成功",""):new ResultInfo<Object>(-1,"操作失败","");
	}

	/**
	 * @Description 检查该校区是否存在CEO
	 * @param  map
	 * @date 2016年10月18日 上午9:52:52 
	 **/
	    
	private boolean checkCollegeCeo(Map<String, String> map) {
		List<OfficeBean> list = officeMapper.selectSameCollegeCEO(map);
		return list != null && !list.isEmpty();
	}

}
