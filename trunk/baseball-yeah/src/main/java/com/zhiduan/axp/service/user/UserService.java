package com.zhiduan.axp.service.user;

import java.util.List;
import java.util.Map;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.StoreUserManagerInfo;
import com.zhiduan.axp.controller.model.UserInfo;
import com.zhiduan.axp.controller.model.store.StoreInfo;
import com.zhiduan.axp.controller.model.user.AddresseeInfo;
import com.zhiduan.axp.controller.model.user.ExpUserAuditInfo;
import com.zhiduan.axp.controller.model.user.UserCheckInfo;
import com.zhiduan.axp.dao.user.bean.AddresseeBean;
import com.zhiduan.axp.dao.user.bean.UserOfPotentialBean;
import com.zhiduan.axp.dao.user.bean.UserSmsModelBean;

import javax.servlet.http.HttpServletRequest;

/*
 * @author 张雷
 * 
 * @date 2016年3月22日
 * 
 * @version V1.0
 */

public interface UserService {
	/**
	 *注册负责人 
	 */
	public ResultInfo registeBeSupervisor(UserInfo userInfo,StoreInfo storeInfo)throws Exception;
	
	/**
	 * 登录
	 */
	public UserInfo loginIn(UserInfo userInf)throws Exception;
	
	/**
	 * 修改密码
	 */
	public ResultInfo changePwd(UserInfo userInfo)throws Exception;
	
	/**
	 * 重置密码
	 */
	public int resetPwd(UserInfo userInfo)throws Exception;
	
	/**
	 * 注销用户
	 */
	public int cancleUser(UserInfo useriInfo)throws Exception;
	
	/**
	 * 注册站点员工
	 */
	public ResultInfo registeEmployed(UserInfo userInfo)throws Exception;
	
	/**
	 * 注册众包学生
	 */
	public ResultInfo registeServerStu(UserInfo userInfo)throws Exception;
	
	/**
	 * 注册收件学生
	 */
	public ResultInfo registeStu(UserInfo userInfo)throws Exception;
	
	/**
	 * 用户是否存在
	 */
	public boolean ifUserExist(UserInfo userInfo)throws Exception;

	/**
	 * 通过旧密码更换手机号码
	 */
	public int changePnByPwd(UserInfo userInfo)throws Exception;

	/**
	 * 通过验证码更换手机号码
	 */
	public int changePnByPn(UserInfo userInfo)throws Exception;

	/**
	 * 检查用户名和密码是否正确
	 */
	public boolean checkUserAndPwd(UserInfo userInfo)throws Exception;

	/**
	 * 查询用户信息
	 */
	public UserInfo selectUserInfo(UserInfo user)throws Exception;
	
	/**
	 * 查询用户信息
	 */
	public UserInfo selectUserInfoRetain(UserInfo user)throws Exception;
	/**
	 * 更改绑定手机号
	 */
	public ResultInfo changeBindPhone(UserInfo user)throws Exception;
	/**
	  * 根据合作公司用户ID删除
	  */
	public ResultInfo deleteById(StoreUserManagerInfo storeUserManagerInfo)throws Exception;
	/**
	  * 更新合作公司user信息
	  */
	public ResultInfo updateByPrimaryKeySelective(StoreUserManagerInfo storeUserManagerInfo)throws Exception;
	 /**
	  * 动态插入合作公司用户信息
	  */
	public ResultInfo insertSortUserManager(StoreUserManagerInfo storeUserManagerInfo)throws Exception;
	 /**
	  * 查询所有合作公司用户信息
	  */
	public List<UserCheckInfo> getAuditUsers(UserCheckInfo user)throws Exception;

	public int getAuditUsersCount(UserCheckInfo user)throws Exception;

	 /**
	  * 查询所有合作公司用户信息
	  */
	public List<StoreUserManagerInfo> getStoreUsers()throws Exception;
	/**
	 * 初始化用户密码
	 * @throws Exception 
	 */
	public ResultInfo initPwd(String userName) throws Exception;
	/**
	 * 审核实名制
	 */
	public ResultInfo verifyUser(List<StoreUserManagerInfo> list)throws Exception;

	/**
	 *注册
	 */
	public ResultInfo expRegiste(UserInfo userInfo)throws Exception;
	/**
	 *完善负责人信息
	 */
	public ResultInfo headPrefectInfo(UserInfo userInfo)throws Exception;
	/**
	 *完善员工信息
	 */
	public ResultInfo empPrefectInfo(UserInfo userInfo)throws Exception;
	/**
	 * 负责人信息审核
	 * @param userAuditInfo 用户审核信息
	 * @return
	 * @throws Exception
	 */
	public ResultInfo expAudit(ExpUserAuditInfo userAuditInfo, HttpServletRequest request) throws Exception;

	/**
	 * 获取完善审核信息
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ResultInfo getAudit(long userId) throws Exception;

    /**
     * @Description: 异步批量更新潜在用户表
     * @param beanList
     * @return boolean
     * @throws
     */
    public boolean addUserOfPotentialAsync(List<UserOfPotentialBean> beanList) throws Exception;

	/**
	 *获取符合条件的短信模板数量
	 * @param modelBean
	 * @return
	 */
	int getUserSmsModelCount(UserSmsModelBean modelBean);


	/**
	 *
	 * @param modelBean
	 * @return
	 */
	List<UserSmsModelBean> getUserSmsModel(UserSmsModelBean modelBean);

	List<UserSmsModelBean> getUserSms(UserSmsModelBean modelBean);
	int getUserSmsCount(UserSmsModelBean modelBean);

	/**
	 * 获取收件人信息
	 * @param info
	 * @return
     */
    List<AddresseeInfo> getAddresseeInfo(AddresseeInfo info);

	/**
	 * 收件人统计数
	 * @param info
	 * @return
     */
	int selectAddresseeCount(AddresseeInfo info);

	/**
	 * 更新收件人用户状态
	 * @param map
	 * @return
     */
	ResultInfo updateCuserStatus(Map<String,String> map);
}
