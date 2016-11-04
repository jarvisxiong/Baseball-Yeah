/**
 * Copyright (c) 2016, 指端科技.
 */


package com.rofour.baseball.dao.manager.mapper;


import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.controller.model.UserInfo;
import com.rofour.baseball.controller.model.manager.UserManagerInfo;
import com.rofour.baseball.controller.model.store.SearchStoreInfo;
import com.rofour.baseball.controller.model.user.UserNumber;
import com.rofour.baseball.dao.manager.bean.SearchUserManagerBean;
import com.rofour.baseball.dao.manager.bean.UserManagerBean;
import com.rofour.baseball.dao.manager.bean.UserManagerLoginBean;
import com.rofour.baseball.dao.order.bean.OrderStatiscs;
import com.rofour.baseball.dao.store.bean.StoreUserManagerBean;
import com.rofour.baseball.dao.user.bean.UserBean;


/**
 * 管理用户数据操作
 * @ClassName: UserManagerMapper
 * @Description:管理用户数据操作
 * @author zhanglei
 * @date 2016年4月11日 下午4:06:18
 *
 */
@Named("userManagerMapper")
public interface UserManagerMapper {
    /**
     * @Method: getAllUsers
     * @Description: 获取所有用户信息
     * @param userInfo TODO
     * @param @return    参数
     * @return List<UserBean>    返回类型
     * @throws
     * @author ZhangLei
     * @date 2016年4月23日 上午11:49:57
     **/

    List<UserBean> getUsers(UserInfo userInfo);

    List<StoreUserManagerBean> getStoreUsers(UserInfo userInfo);

    StoreUserManagerBean getStoreUserByUserId(long userId);

    /**
     * @Method: getUsersTotal
     * @Description: 获取用户的总数
     * @param @return    参数
     * @return Integer    返回类型
     * @throws
     * @author ZhangLei
     * @date 2016年4月23日 下午5:31:14
     **/

    Integer getUsersTotal();

    Integer getStoreUsersCount(Long storeId);

    Integer getStoreUsersTotal(UserInfo userInfo);

    int editUser(UserInfo userInfo);

    int addUser(UserInfo userInfo);

    Integer addManagerUser(UserManagerInfo userManagerInfo);

    int cancelUser(Long userManagerId);

    int cancelManagerUser(Long userManagerId);

    UserBean getUserById(Long userId);

    /**
     * 查询手机号是否已存在
     */
    int validatePhone(String phone);

    /**
     * 获取自增键值
     */
    Long getSeq();

    /**
     * 动态插入用户信息
     */
    Integer insertSelective(UserBean userBean);

    /**
     * 查询手机号是否注册（包括对自己的判断）
     */
    int validateUserPhone(StoreUserManagerBean storeUserManagerBean);

    /**
     * 更新合作公司user信息
     */
    int updateByPrimaryKeySelective(StoreUserManagerBean storeUserManagerBean);

    /**
     * 根据用户ID删除用户
     */
    int deleteById(Long userId);

    /**
     * 更改用户密码
     */
    int changePwd(UserInfo userInfo);

    /**
     * @Method: getUsersTotal
     * @Description: 获取用户的总数
     * @param @return    参数
     * @return Integer    返回类型
     * @throws
     * @author ZhangLei
     * @date 2016年4月23日 下午5:31:14
     **/

    Integer getTodayUsersTotal();

    List<UserBean> getAllUsers();

    Integer getTodaySMSTotal();

    Integer getSMSTotalByDateTime(String dateBegin, String dateEnd);

    OrderStatiscs getOderStatiscs(String dateBegin, String dateEnd);

    Integer getTodayNewPhoneTotal();

    /**
     *
     * @Description: 保存用户信息。
     * @param userManager 用户
     * @return int 受影响行记录数
     */
    int save(UserManagerBean userManager);

    /**
     *
     * @Description 根据ID更新用户信息
     * @param userManager 用户
     * @return int 受影响行记录数
     */
    int update(UserManagerBean userManager);

    /**
     *
     * @Description: 根据ID查询用户信息。
     * @param id 主键ID
     * @return UserManagerBean
     */
    UserManagerBean findById(long id);

    /**
     *
     * @Description: 根据登录名查询用户信息
     * @param loginName 登录名
     * @return UserManagerBean
     */
    UserManagerBean findByLoginName(String loginName);

    /**
     *
     * @Description: 加载活动的用户列表
     * @return List<UserManagerBean>
     */
    List<UserManagerBean> findByActiveUsers();

    /**
     * @Description: 查询所有用户
     * @return List<SearchUserManagerBean>
     **/
    List<SearchUserManagerBean> searchAll();


    /**
     * @Description: 查询所有用户
     * @param userManagerInfo TODO
     * @return List<SearchUserManagerBean>
     **/
    List<SearchUserManagerBean> getAll(UserManagerInfo userManagerInfo);

    /**
     * @Description: 按Id删除
     * @param id
     * @return 删除的数量
     **/

    int deleteManagerById(long id);

    /**
     *
     * @Description: 初始化密码
     * @param id
     * @return 更新的数量
     *
     */
    int changePassword(long id, String pwd);

    /**
     *
     * @Description: 初始化密码
     * @param id
     * @return 更新的数量
     *
     */
    int changeManagerPassword(long id, String pwd);

    /**
     * @Description: 验证用户是否已存在
     * @param map 条件
     * @return 重复的数量
     **/
    int isUserExists(Map<String, Object> map);

    /**
     *
     * @Description: 根据登录名获取用户信息
     * @param loginName 登录名
     * @return UserManagerLoginBean
     */
    UserManagerLoginBean getInfoByLoginName(String loginName);

    Integer getManagerTotal(UserManagerInfo userInfo);

    Integer getUserByLoginName(String loginName);

    Integer getUserByUserCode(String userCode);

    int editManagerUser(UserManagerInfo userManager);

    /**
     * 查询用户信息
     */
    UserInfo selectUserInfo(UserInfo user);

    /**
     * 根据手机号查询商户和快递信息
     * @param phone
     * @return
     * @throws Exception
     */
    SearchStoreInfo selectStoreExpressByPhone(String phone) throws Exception;

    /**
     * 获取10天注册的用户数量
     */
    List<UserNumber> getTenDayUser();

    /**
     * 根据用户id获取用户信息
     */
    UserManagerBean selectByUserId(Long userManagerId);


}