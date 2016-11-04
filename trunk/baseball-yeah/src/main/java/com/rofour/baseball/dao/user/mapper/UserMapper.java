package com.rofour.baseball.dao.user.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.controller.model.StoreUserManagerInfo;
import com.rofour.baseball.controller.model.UserInfo;
import com.rofour.baseball.dao.store.bean.StoreUserManagerBean;
import com.rofour.baseball.dao.user.bean.AddresseeBean;
import com.rofour.baseball.dao.user.bean.UserBean;
import com.rofour.baseball.dao.user.bean.UserCheckBean;

@Named("userMapper")
public interface UserMapper {

	 /**
	  * 根据用户手机删除用户信息
	  */
	int deleteByPhone(String phone);

	 /**
	  * 动态插入用户信息
	  */
	Integer insertSelective(UserBean userBean);

	 /**
	  * 查询所有用户信息
	  */
	List<StoreUserManagerBean> selectAll();
	 /**
	  * 获取站点员工列表
	  */
	List<UserCheckBean> userRegisterCheck(UserCheckBean userCheckBean);

	 /**
	  * 根据已有信息，查询用户信息
	  */
	List<UserBean> selectSelective(UserBean userBean);

	 /**
	  * 登录查询
	  */
	List<UserBean> loginSelect(UserBean userBean);

	 /**
	  * 根据用户名查询用户信息
	  */
	List<UserBean> selectByUserName(String userName);

	 /**
	  * 根据用户收集查询用户信息
	  */
	List<UserBean> selectByPhone(String phone);

	 /**
	  * 更改用户密码
	  */
	int changePwd(UserInfo userInfo);

	 /**
	  *重置用户密码 
	  */
	int resetPwd(UserInfo userInfo);

	 /**
	  * 注销用户
	  */
	int cancleUser(UserInfo userInfo);

	 /**
	  * 通过旧手机更换用户手机
	  */
	int changePnByPn(UserInfo userInfo);

	
	 /**
	  * 通过用户名密码，跟换用户手机
	  */
	int changePnByPwd(UserInfo userInfo);

	 /**
	  * 根据用户名和密码查询用户
	  */
	List<UserBean> selectByNameAndPwd(UserInfo userInfo);

	 /**
	  * 查询用户信息
	  */
	UserInfo selectUserInfo(UserInfo user);

	/**
	 * 查询用户名密码是否正确
	 */
	int selectByNameAndPwd1(UserInfo user);

	/**
	 * 查询手机号是否已存在
	 */
	int validatePhone(String phone);

	 /**
	  * 更新用户
	  */
	int updateUser(UserInfo userInfo);

	 /**
	  * 获取自增键值
	  */
	Long getSeq();
	/**
	  * 根据用户ID删除用户
	  */
	int deleteById(Long userId);
	/**
	  * 更新合作公司user信息
	  */
	int updateByPrimaryKeySelective(StoreUserManagerBean storeUserManagerBean);
	/**
	 * 查询手机号是否注册（包括对自己的判断）
	 */
	int validateUserPhone(StoreUserManagerBean storeUserManagerBean);
	/**
	 * 审核实名制
	 */
    int verifyUser(List<StoreUserManagerInfo> list);
    /**
     * 查看审核状态
     */
    Integer queryVerifyStatus(Long userId);
	/**
	 * 判断用户身份证是否注册过
	 * @param userInfo 用户信息
	 * @return
	 */
	int selectUserInfoByIdCard(UserInfo userInfo);

	/**
	 * 判断当前用户是否已经填过身份证帐号
	 * @param userInfo 用户信息
	 * @return
	 */
	int selectUserInfoByIdCardAndPhone(UserInfo userInfo);

	int userRegisterCheckCount(UserCheckBean userCheckBean);

	/**
	 * 收件人信息查询
	 * @param bean
	 * @return
     */
	List<AddresseeBean> selectAddresseeInfo(AddresseeBean bean);

	/**
	 * 查询收件人统计数
	 * @param bean
	 * @return
     */
	int selectAddresseeCount(AddresseeBean bean);

	/**
	 * 批量删除商户下的用户
	 * @param ids
	 * @return
     */
	int batchLogicDelUser(List<Integer> ids);
}