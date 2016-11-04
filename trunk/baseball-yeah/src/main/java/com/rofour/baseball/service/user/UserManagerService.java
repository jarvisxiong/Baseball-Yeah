/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.user;

import java.util.List;

import com.rofour.baseball.dao.order.bean.OrderStatiscs;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;

import org.springframework.web.servlet.ModelAndView;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.StoreUserManagerInfo;
import com.rofour.baseball.controller.model.UserInfo;
import com.rofour.baseball.controller.model.manager.SearchUserManagerInfo;
import com.rofour.baseball.controller.model.manager.UserManagerInfo;
import com.rofour.baseball.controller.model.user.UserNumber;
import com.rofour.baseball.dao.manager.bean.SearchUserManagerBean;
import com.rofour.baseball.dao.manager.bean.UserManagerBean;
import com.rofour.baseball.dao.user.bean.UserBean;

import javax.servlet.http.HttpServletRequest;

public interface UserManagerService {

	/**
	 * @Description: 管理员登录
	 * @param userInfo TODO
	 * @param user
	 * @return 	
	 * @throws Exception 
	 */
	    
	public List<UserBean> getUsers(UserInfo userInfo);
	
	public ModelAndView getStoreUsers(UserInfo userInfo);
	/**
	 * @Method: getUsersTotal
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    参数
	 * @return Integer    返回类型
	 * @throws
	 * @author ZhangLei
	 * @date 2016年4月23日 下午5:49:39 
	 **/
	    
	Integer getUsersTotal();
	
	Integer getStoreUsersTotal(UserInfo userInfo);

	ModelAndView addUser(UserInfo userInfo);

	int cancelUser(Long userId, HttpServletRequest request);

	ModelAndView editUser(UserInfo userInfo);

	UserBean getUserById(Long userId);

	 /**
	  * 动态插入合作公司用户信息
	  */
	ResultInfo<?> insertSortUserManager(StoreUserManagerInfo storeUserManagerInfo, HttpServletRequest request);
	
	/**
	  * 更新合作公司user信息
	  */
	public ResultInfo updateByPrimaryKeySelective(StoreUserManagerInfo storeUserManagerInfo, HttpServletRequest request);
	
	/**
	  * 根据合作公司用户ID删除
	  */
	public ResultInfo deleteById(StoreUserManagerInfo storeUserManagerInfo, UserManagerLoginBean loginBean);

	
	/**
	 * 初始化用户密码
	 * @throws Exception 
	 */
	public ResultInfo initPwd(String userName, HttpServletRequest request) throws Exception;

	Integer getTodayUsersTotal();

	List<UserBean> getAllUsers();
	
	Integer getTodaySMSTotal();

	Integer getSMSTotalByDateTime(String dateBegin,String dateEnd);
	OrderStatiscs getOderStatiscs(String dateBegin, String dateEnd);

	Integer getTodayNewPhoneTotal();

	void update(UserManagerInfo userManager, HttpServletRequest request);

	UserManagerInfo findById(long id);

	List<UserManagerInfo> findByActiveUsers();

	List<SearchUserManagerInfo> searchAll();

	void deleteById(long id, HttpServletRequest request);

	void initPassword(long id, HttpServletRequest request);

	void changePassword(long id, String pwd, HttpServletRequest request);

	List<SearchUserManagerBean> getAll(UserManagerInfo userManagerInfo);
	
	Integer getManagerTotal(UserManagerInfo userInfo);

	ResultInfo<UserManagerBean> addManagerUser(UserManagerInfo userManagerInfo, HttpServletRequest request) throws Exception;

	int cancelManagerUser(Long userId, HttpServletRequest request);

	Integer initManagerPassword(long userManagerId, HttpServletRequest request);

	ResultInfo updateManagerUser(UserManagerInfo userManager, HttpServletRequest request) throws Exception;

	public ResultInfo getAudit(long userId) throws Exception;

	List<UserNumber> getTenUsers();

	/**
	 * @Description: 更改系统用户密码
	 * @param id
	 * @param pwd
	 * @param request 
	 * @param oldPwd TODO
	 * @return TODO
	 * @throws Exception 
	 */
	    
	ResultInfo changeManagerPassword(long id, String pwd, HttpServletRequest request, String oldPwd) throws Exception;

	/**
	 * @Description: 获取今日订单总数量
	 * @return 
	 */
	    
	Integer getTodayNewOrderTotal();
}
