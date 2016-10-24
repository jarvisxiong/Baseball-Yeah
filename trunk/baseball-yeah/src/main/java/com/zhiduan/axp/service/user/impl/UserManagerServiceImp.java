/**
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.user.impl;

import com.zhiduan.axp.common.*;
import com.zhiduan.axp.controller.model.Permission;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.StoreUserManagerInfo;
import com.zhiduan.axp.controller.model.UserInfo;
import com.zhiduan.axp.controller.model.manager.SearchUserManagerInfo;
import com.zhiduan.axp.controller.model.manager.UserManagerInfo;
import com.zhiduan.axp.controller.model.resource.CredentialURLInfo;
import com.zhiduan.axp.controller.model.store.AxpStoreInfo;
import com.zhiduan.axp.controller.model.store.CustomStoreInfo;
import com.zhiduan.axp.controller.model.store.SearchStoreInfo;
import com.zhiduan.axp.controller.model.user.ExpUserAuditResultInfo;
import com.zhiduan.axp.controller.model.user.ExpUserPerfectInfo;
import com.zhiduan.axp.controller.model.user.UserNumber;
import com.zhiduan.axp.dao.manager.bean.*;
import com.zhiduan.axp.dao.manager.mapper.*;
import com.zhiduan.axp.dao.order.bean.OrderStatiscs;
import com.zhiduan.axp.dao.order.mapper.OrderUserMapper;
import com.zhiduan.axp.dao.store.bean.StoreUserManagerBean;
import com.zhiduan.axp.dao.user.bean.UserBean;
import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;
import com.zhiduan.axp.dao.user.bean.UserStoreExpBean;
import com.zhiduan.axp.dao.user.bean.UserTypeBean;
import com.zhiduan.axp.dao.user.mapper.UserStoreExpMapper;
import com.zhiduan.axp.dao.user.mapper.UserTypeMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.resource.ResourceService;
import com.zhiduan.axp.service.store.StoreService;
import com.zhiduan.axp.service.user.UserManagerService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.util.PublicSuffixList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wny
 * @ClassName: UserManagerServiceImp
 * @Description: 管理员用户管理服务
 * @date 2016年4月11日 下午4:34:43
 */
@Service("userManagerService")
public class UserManagerServiceImp implements UserManagerService {

    /**
     * 管理用户操作数据
     */
    @Autowired
    @Qualifier("userManagerMapper")
    private UserManagerMapper userManagerMapper;

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
    @Qualifier("storeService")
    private StoreService storeService;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Resource(name = "orderUserMapper")
    OrderUserMapper orderUserMapper;

    private enum userType {
        /**
         * 没有类型
         */
        notype,
        /**
         * 货源版
         */
        commodity;
    }

    /**
     * @Fields sysParameterMapper : 注入系统参数操作服务
     */
    @Autowired
    @Qualifier("tbSysParameterMapper")
    private SysParameterMapper sysParameterMapper;

    @Autowired
    private WebUtils webUtils;

    @Autowired
    private ResourceService resourceService;

    @Qualifier("propertyDictMapper")
    private PropertyDictMapper propertyDictMapper;
    @Autowired
    @Qualifier("deptMapper")
    private DeptMapper deptMapper;
    @Autowired
    @Qualifier("dutyBeanMapper")
    private DutyBeanMapper dutyMapper;
    @Autowired
    @Qualifier("userManagerRoleRelMapper")
    private UserManagerRoleRelMapper relMapper;

    @Autowired
    @Qualifier("userManagerRoleRelMapper")
    private UserManagerRoleRelMapper userManagerRoleRelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserManagerServiceImp.class);

    /**
     * @param
     * @return ModelAndView
     * @throws Exception
     * @Description: 管理员用户登录
     * @see
     */
    @Override
    public List<UserBean> getUsers(UserInfo userInfo) {
        List<UserBean> users = userManagerMapper.getUsers(userInfo);
        return users;
    }

    @Override
    public List<UserBean> getAllUsers() {
        List<UserBean> users = userManagerMapper.getAllUsers();

        return users;
    }

    @Override
    public ModelAndView getStoreUsers(UserInfo userInfo) {
        List<StoreUserManagerBean> users = userManagerMapper
                .getStoreUsers(userInfo);
        return new ModelAndView("storeUser/allStoreUser", "rows", users);
    }

    @Override
    public Integer getUsersTotal() {
        return userManagerMapper.getUsersTotal();
    }

    @Override
    public Integer getTodayUsersTotal() {
        return userManagerMapper.getTodayUsersTotal();
    }

    @Override
    public Integer getStoreUsersTotal(UserInfo userInfo) {
        return userManagerMapper.getStoreUsersTotal(userInfo);
    }

    @Override
    public ModelAndView addUser(UserInfo userInfo) {
        int i = userManagerMapper.addUser(userInfo);
        if (i > 0) {
            return new ModelAndView("rediuser/allUser", "msg", "增加成功");
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public ResultInfo<UserManagerBean> addManagerUser(UserManagerInfo userManagerInfo, HttpServletRequest request) throws Exception {
        ResultInfo<UserManagerBean> result = validaManagerUser(userManagerInfo);
        if (result.getSuccess() != 0) {
            return result;
        } else if (userManagerMapper.getUserByLoginName(userManagerInfo.getLoginName()) > 0) {
            return new ResultInfo<UserManagerBean>(-1, "1023", "用户已经存在");
        } else if (userManagerMapper.getUserByUserCode(userManagerInfo.getUserCode()) > 0) {
            return new ResultInfo<UserManagerBean>(-1, "1027", "用户工号已经存在");
        } else {
            userManagerInfo.setBeDeleted((byte) 0);
            userManagerInfo.setBeEnabled((byte) 1);
            userManagerInfo.setPassword(CipherPwdUtils.cipherPwd(userManagerInfo.getPassword()));
            if (userManagerMapper.addManagerUser(userManagerInfo) > 0) {
                webUtils.userAddLog(request, 16, userManagerInfo);
                List<UserManagerRoleRelBean> userRoles = new ArrayList<UserManagerRoleRelBean>();
                if (userManagerInfo.getRoleIds() != null && userManagerInfo.getRoleIds().size() > 0) {
                    for (Long roleId : userManagerInfo.getRoleIds()) {
                        userRoles.add(new UserManagerRoleRelBean(userManagerInfo.getUserManagerId(), roleId));
                    }
                }
                userManagerRoleRelMapper.batchInsert(userRoles);
                redisTemplate.opsForHash().delete("roleAndPermission", userManagerInfo.getLoginName());
                return new ResultInfo<UserManagerBean>(0, "", "");
            } else {
                return new ResultInfo<UserManagerBean>(-1, "1024", "增加用户失败");
            }
        }
    }

    private ResultInfo<UserManagerBean> validaManagerUser(UserManagerInfo userManagerInfo) {
        if (StringUtils.isEmpty(userManagerInfo.getLoginName())) {
            return new ResultInfo<UserManagerBean>(-1, "1008", "登录名不能为空");
        } else if (StringUtils.isEmpty(userManagerInfo.getPassword())) {
            return new ResultInfo<UserManagerBean>(-1, "1011", "密码不能为空");
        } else if (!userManagerInfo.getPassword()
                .matches("^[0-9a-zA-Z]{6,30}$")) {
            return new ResultInfo<UserManagerBean>(-1, "1012",
                    "用户密码6-30位数字和字母组成");
        } else if (StringUtils.isEmpty(userManagerInfo.getUserName())) {
            return new ResultInfo<UserManagerBean>(-1, "1009", "用户名不能为空");
        } else if (StringUtils.isEmpty(userManagerInfo.getUserCode())) {
            return new ResultInfo<UserManagerBean>(-1, "1010", "工号不能为空");
        } else if (!StringUtils.isEmpty(userManagerInfo.getContactTel())) {
            if (!PhoneUtils.isMobile(userManagerInfo.getContactTel())) {
                return new ResultInfo<UserManagerBean>(-1, "1009", "手机号格式不正确");
            } else {
                return new ResultInfo<UserManagerBean>(0, "", "");
            }
        } else {
            return new ResultInfo<UserManagerBean>(0, "", "");
        }

    }

    @Override
    public ModelAndView editUser(UserInfo userInfo) {
        int i = userManagerMapper.editUser(userInfo);
        return null;
    }

    @Override
    public int cancelUser(Long userManagerId, HttpServletRequest request) {
        StoreUserManagerBean delModel = userManagerMapper.getStoreUserByUserId(userManagerId);
        CustomStoreInfo model = storeService.querySiteById(delModel.getStoreId());
        //如果站点被删除 那么可以删除负责人
        if (model == null || model.getBeDeleted() == 1) {
            int i = userManagerMapper.cancelUser(userManagerId);
            webUtils.userDeleteLog(request, 6, delModel);
            return i;
        }
        //否则不能删除负责人
        if (delModel.getBeSupervisor() == 1) {
            return -1;
        }
        int i = userManagerMapper.cancelUser(userManagerId);
        webUtils.userDeleteLog(request, 6, delModel);
        return i;
    }

    @Override
    public int cancelManagerUser(Long userManagerId, HttpServletRequest request) {
        UserManagerBean userManagerBean = userManagerMapper.selectByUserId(userManagerId);
        int i = userManagerMapper.cancelManagerUser(userManagerId);
        UserManagerBean userManagerBean2 = userManagerMapper.selectByUserId(userManagerId);
        webUtils.userEditLog(request, 16, userManagerBean, userManagerBean2);
        return i;
    }

    @Override
    public UserBean getUserById(Long userId) {
        UserBean user = userManagerMapper.getUserById(userId);
        return null;

    }

    /**
     * 动态插入用户信息
     */
    @Transactional
    public ResultInfo<Object> insertSortUserManager(StoreUserManagerInfo storeUserManagerInfo, HttpServletRequest request) {
        long status;
        if (!storeUserManagerInfo.getPhone().equals(storeUserManagerInfo.getUserName())) {
            return new ResultInfo<Object>(-1, "1020040030", "该手机号与用户名不相符");
        }
        try {
            status = userManagerMapper.validatePhone(storeUserManagerInfo
                    .getPhone());
            if (status != 0) {
                return new ResultInfo<Object>(-1, "1020040030", "该手机号已存在");
            }
            StoreUserManagerBean storeUserManagerBean = new StoreUserManagerBean();
            BeanUtils.copyProperties(storeUserManagerInfo, storeUserManagerBean);
            UserBean userBean = new UserBean();
            BeanUtils.copyProperties(storeUserManagerBean, userBean);
            status = userManagerMapper.insertSelective(userBean); // 插入tb_user表
            UserStoreExpBean userStoreExpBean = new UserStoreExpBean();
            userStoreExpBean.setBeSupervisor(storeUserManagerBean.getBeSupervisor());
            userStoreExpBean.setStoreId(storeUserManagerBean.getStoreId());
            userStoreExpBean.setUserId(userBean.getUserId());
            userStoreExpBean.setIdentityNumber(storeUserManagerBean.getIdentityNumber());
            userStoreExpMapper.insert(userStoreExpBean); // //
            // 插入tb_user_store_exp表
            if (userStoreExpBean.getBeSupervisor() == 1) {
                userStoreExpMapper.updateBeSupervisor(userStoreExpBean);
            }
            UserTypeBean userTypeBean = new UserTypeBean();
            userTypeBean.setUserId(userBean.getUserId());
            userTypeBean.setUserType(userType.commodity.ordinal());//货源用户
            userTypeBean.setBeEnabled((byte) 1);
            int userTypeResult = userTypeMapper.insertSelective(userTypeBean); // 插入tb_user_type

            if (status != 0) {
                webUtils.userAddLog(request, 6, storeUserManagerInfo);
                return new ResultInfo<Object>(0, "0", "添加用户成功");
            } else {
                return new ResultInfo<Object>(-1, "1020040030", "添加用户失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResultInfo<Object>(-1, "1020040030", "添加用户失败");
        }

    }

    public ResultInfo<Object> updateByPrimaryKeySelective(StoreUserManagerInfo storeUserManagerInfo, HttpServletRequest request) {
        int status;
        StoreUserManagerBean editModel = userManagerMapper.getStoreUserByUserId(storeUserManagerInfo.getUserId());
        StoreUserManagerBean storeUserManagerBean = new StoreUserManagerBean();
        BeanUtils.copyProperties(storeUserManagerInfo, storeUserManagerBean);
        if (!storeUserManagerBean.getPhone().equals(storeUserManagerBean.getUserName())) {
            return new ResultInfo<Object>(-1, "1020040030", "该手机号与用户名不相符");
        }
        try {
            status = userManagerMapper.validateUserPhone(storeUserManagerBean);
            if (status != 0) {
                return new ResultInfo<Object>(-1, "1020040030", "该手机号已存在");
            }
            status = userManagerMapper.updateByPrimaryKeySelective(storeUserManagerBean); // 更新tb_user表
            UserStoreExpBean userStoreExpBean = new UserStoreExpBean();
            userStoreExpBean.setBeSupervisor(storeUserManagerInfo.getBeSupervisor());
            userStoreExpBean.setStoreId(storeUserManagerBean.getStoreId());
            userStoreExpBean.setUserId(storeUserManagerBean.getUserId());

            //当用户是负责人时候不能修改为非负责人
            if (editModel.getBeSupervisor() == 1 && userStoreExpBean.getBeSupervisor() == 0) {
                return new ResultInfo<Object>(-1, "1020040030", "负责人不能改为非负责人");
            }
            if (userStoreExpBean.getBeSupervisor() == 1) {
                userStoreExpMapper.updateBeSupervisor(userStoreExpBean);
                //当用户修改为负责人时候 其站点下的用户负责人状态要修改为否
                userStoreExpMapper.updateStoreUser(userStoreExpBean);
            }
            userStoreExpMapper.updateByPrimaryKey(userStoreExpBean); // 更新tb_user_store_exp表
            if (status != 0) {
                webUtils.userEditLog(request, 6, editModel, storeUserManagerInfo);
                return new ResultInfo<Object>(0, "0", "更新用户成功");
            } else {
                return new ResultInfo<Object>(-1, "1020040030", "更新用户失败");

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResultInfo<Object>(-1, "1020040030", "更新用户失败");
        }

    }

    public ResultInfo<Object> deleteById(StoreUserManagerInfo storeUserManagerInfo, UserManagerLoginBean loginBean) {
        int status;
        try {
            StoreUserManagerBean deleteModel = userManagerMapper.getStoreUserByUserId(storeUserManagerInfo.getUserId());
            if (deleteModel.getBeSupervisor() == 1) {
                return new ResultInfo<Object>(-1, "", "负责人不能删除");
            }
            status = userManagerMapper.deleteById(storeUserManagerInfo.getUserId()); // 删除tb_user表
            if (status != 0) {
                return new ResultInfo<Object>(0, "0", "删除用户成功");
            } else {
                return new ResultInfo<Object>(-1, "1020040030", "删除用户失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResultInfo<Object>(-1, "1020040030", "删除用户失败");
        }
    }

    public ResultInfo<Object> initPwd(String userName, HttpServletRequest request) {
        int status;
        SysParameterBean sysParameterBean = sysParameterMapper.selectByParaName("init_pwd");
        if (sysParameterBean == null) {
            return new ResultInfo<Object>(-1, "0", "初始化密码失败", "");
        } else {
            UserInfo userInfo = new UserInfo();
            try {
                userInfo.setNewPwd(CipherPwdUtils.cipherPwd(sysParameterBean.getValue()));
                userInfo.setUserName(userName);
                status = userManagerMapper.changePwd(userInfo);
                if (status != 0) {
                    webUtils.userLog(request, String.format("用户名：%s 初始化密码：", userName),
                            6, "", "", Permission.INITPWD.name());
                    return new ResultInfo<Object>(0, "0", "初始化密码成功", "");
                } else {
                    return new ResultInfo<Object>(-1, "-1", "初始化密码失败", "");
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return new ResultInfo<Object>(-1, "-1", "初始化密码失败", "");
            }
        }
    }

    @Override
    public Integer getTodaySMSTotal() {
        return userManagerMapper.getTodaySMSTotal();
    }

    @Override
    public Integer getSMSTotalByDateTime(String dateBegin, String dateEnd) {
        return userManagerMapper.getSMSTotalByDateTime(dateBegin, dateEnd);
    }

    @Override
    public OrderStatiscs getOderStatiscs(String dateBegin, String dateEnd) {
        return userManagerMapper.getOderStatiscs(dateBegin, dateEnd);
    }

    @Override
    public Integer getTodayNewPhoneTotal() {
        return userManagerMapper.getTodayNewPhoneTotal();
    }

    /**
     * @param userManager
     * @Description: 更新用户管理数据
     * @see
     */
    @Override
    public void update(UserManagerInfo userManager, HttpServletRequest request) {
        logger.debug("修改用户 -> {}", userManager);

        checkUserManagerInfo(userManager);

        UserManagerBean userManagerBean = new UserManagerBean();
        BeanUtils.copyProperties(userManager, userManagerBean);

        int r = userManagerMapper.update(userManagerBean);
        webUtils.userAddLog(request, 16, userManagerBean);
        if (r != 1) {
            logger.error("修改用户失败，受影响行记录数为[{}] -> {}", r, userManager);
            throw new BusinessException("119");
        }
    }

    /**
     * @param
     * @throws Exception
     * @Description: 更新用户管理数据
     * @see
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = false)
    public ResultInfo<UserManagerBean> updateManagerUser(UserManagerInfo userManagerInfo, HttpServletRequest request) throws Exception {
        ResultInfo<UserManagerBean> result = checkUserManagerInfo(userManagerInfo);
        if (result.getSuccess() == 0) {
            if (userManagerInfo.getDeptId() == null) {
                userManagerInfo.setDeptId(0L);
            }
            if (userManagerInfo.getDutyId() == null) {
                userManagerInfo.setDutyId(0L);
            }
            UserManagerBean userManager = userManagerMapper.selectByUserId(userManagerInfo.getUserManagerId());
            if (!StringUtils.isBlank(userManagerInfo.getPassword())) {
                userManagerInfo.setPassword(CipherPwdUtils.cipherPwd(userManagerInfo.getPassword()));
            }
            if (userManagerMapper.editManagerUser(userManagerInfo) > 0) {
                List<UserManagerRoleRelBean> userRoles = new ArrayList<UserManagerRoleRelBean>();
                if (userManagerInfo.getRoleIds() != null && userManagerInfo.getRoleIds().size() > 0) {
                    for (Long roleId : userManagerInfo.getRoleIds()) {
                        if (roleId != null) {
                            userRoles.add(new UserManagerRoleRelBean(userManagerInfo.getUserManagerId(), roleId));
                        }
                    }
                }
                webUtils.userEditLog(request, 16, userManager, userManagerInfo);
                List<UserManagerRoleRelBean> userDeleteRoles = userManagerRoleRelMapper.selectByUserId(userManagerInfo.getUserManagerId());
                userManagerRoleRelMapper.deleteByManagerId(userManagerInfo.getUserManagerId());
                webUtils.userDeleteLog(request, 16, userDeleteRoles);
                if (userRoles != null && userRoles.size() > 0) {
                    userManagerRoleRelMapper.batchInsert(userRoles);
                }
                webUtils.userAddLog(request, 8, userRoles);
                redisTemplate.opsForHash().delete("roleAndPermission", userManager.getLoginName());
                return new ResultInfo<UserManagerBean>(0, "", "");
            } else {
                redisTemplate.opsForHash().delete("roleAndPermission", userManager.getLoginName());
                return new ResultInfo<UserManagerBean>(-1, "1034", "更新用户失败");
            }
        } else {
            return result;
        }
    }

    /**
     * @param id
     * @return
     * @Description: 查询用户管理信息
     * @see com.zhiduan.axp.service.user.idl.managecenter.service.UserManagerService#findById(long)
     */
    @Override
    public UserManagerInfo findById(long id) {
        UserManagerBean userManagerBean = userManagerMapper.findById(id);
        if (userManagerBean == null) {
            throw new BusinessException("01019");
        }
        UserManagerInfo userManagerInfo = new UserManagerInfo();
        BeanUtils.copyProperties(userManagerBean, userManagerInfo);
        return userManagerInfo;
    }

    /**
     * @return
     * @Description: 根据当前用户查询用户管理信息
     * @see com.zhiduan.axp.service.user.idl.managecenter.service.UserManagerService#findByActiveUsers()
     */
    @Override
    public List<UserManagerInfo> findByActiveUsers() {
        List<UserManagerBean> users = userManagerMapper.findByActiveUsers();
        if (users == null || users.isEmpty()) {
            throw new BusinessException("01019");
        }
        List<UserManagerInfo> userManagerInfos = new ArrayList<>();
        for (UserManagerBean userManagerBean : users) {
            UserManagerInfo userManagerInfo = new UserManagerInfo();
            BeanUtils.copyProperties(userManagerBean, userManagerInfo);
            userManagerInfos.add(userManagerInfo);
        }
        return userManagerInfos;
    }

    /**
     * @return
     * @Description:查询所有用户管理查询信息
     * @see com.zhiduan.axp.service.user.idl.managecenter.service.UserManagerService#searchAll()
     */
    @Override
    public List<SearchUserManagerInfo> searchAll() {
        List<SearchUserManagerBean> list = userManagerMapper.searchAll();
        if (list == null || list.isEmpty()) {
            throw new BusinessException("01019");
        }
        List<SearchUserManagerInfo> userInfos = new ArrayList<SearchUserManagerInfo>();
        for (SearchUserManagerBean userBean : list) {
            SearchUserManagerInfo userInfo = new SearchUserManagerInfo();
            BeanUtils.copyProperties(userBean, userInfo);
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    /**
     * @return
     * @Description:查询所有用户管理查询信息
     * @see com.zhiduan.axp.service.user.idl.managecenter.service.UserManagerService#searchAll()
     */
    @Override
    public List<SearchUserManagerBean> getAll(UserManagerInfo userManagerInfo) {
        List<SearchUserManagerBean> list = null;
        try {
            list = userManagerMapper.getAll(userManagerInfo);
            if (list != null && list.size() > 0) {
                for (SearchUserManagerBean searchUserManagerBean : list) {
                    if (!StringUtils.isBlank(searchUserManagerBean.getRoleIdStr())) {
                        String[] strs = searchUserManagerBean.getRoleIdStr().split(",");
                        for (String str : strs) {
                            searchUserManagerBean.getRoleIds().add(Long.valueOf(str));
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (list == null || list.isEmpty()) {
            throw new BusinessException("01019");
        }
        return list;
    }

    @Override
    public Integer getManagerTotal(UserManagerInfo userInfo) {
        return userManagerMapper.getManagerTotal(userInfo);
    }

    /**
     * @param id
     * @Description: 删除用户管理信息
     * @see com.zhiduan.axp.service.user.idl.managecenter.service.UserManagerService#deleteById(long, HttpServletRequest)
     */
    @Override
    public void deleteById(long id, HttpServletRequest request) {
        UserManagerBean userManagerBean = userManagerMapper.selectByUserId(id);
        int r = userManagerMapper.deleteById(id);
        UserManagerBean userManagerBean2 = userManagerMapper.selectByUserId(id);
        webUtils.userEditLog(request, 16, userManagerBean, userManagerBean2);
        if (r != 1) {
            throw new BusinessException("01019");
        }
    }

    /**
     * @param id
     * @Description: 初始化密码
     */
    @Override
    public void initPassword(long id, HttpServletRequest request) {
        UserManagerBean userManagerBean = userManagerMapper.selectByUserId(id);
        changePwd(id, "111111", request);
        UserManagerBean userManagerBean2 = userManagerMapper.selectByUserId(id);
        webUtils.userEditLog(request, 16, userManagerBean, userManagerBean2);
        webUtils.userEditLog(request, 6, id, "111111");
    }

    /**
     * @param id
     * @Description: 初始化密码
     */
    @Override
    public Integer initManagerPassword(long userManagerId, HttpServletRequest request) {
        UserManagerBean userManagerBean = userManagerMapper.selectByUserId(userManagerId);
        int i = changeManagerPwd(userManagerId, "111111");
        UserManagerBean userManagerBean2 = userManagerMapper.selectByUserId(userManagerId);
        webUtils.userEditLog(request, 16, userManagerBean, userManagerBean2);
        return i;
    }

    /**
     * @param id
     * @param pwd
     * @Description: 更改密码
     * java.lang.String)
     */
    @Override
    public void changePassword(long id, String pwd, HttpServletRequest request) {
        changePwd(id, pwd, request);
    }

    /**
     * @param id
     * @param pwd
     * @param request
     * @throws Exception
     * @Description: 更改系统用户密码
     */

    @Override
    public ResultInfo<Object> changeManagerPassword(long id, String pwd, HttpServletRequest request, String oldPwd) throws Exception {
        UserManagerBean userManagerBean = userManagerMapper.selectByUserId(id);
        if (!StringUtils.equals(CipherPwdUtils.cipherPwd(oldPwd.trim()), userManagerBean.getPassword())) {
            return new ResultInfo<Object>(-1, "", "旧密码不正确");
        } else {
            int i = changeManagerPwd(id, pwd);
            UserManagerBean userManagerBean2 = userManagerMapper.selectByUserId(id);
            webUtils.userEditLog(request, 16, userManagerBean, userManagerBean2);
            if (i > 0) {
                return new ResultInfo<Object>(0, "", "修改密码成功");
            } else {
                return new ResultInfo<Object>(0, "", "修改密码失败");
            }
        }
    }

    /*
     * @Description: 获取最近10天注册的用户数量
     * @return
     * @see com.zhiduan.axp.service.user.UserManagerService#getTenUsers()
     */
    @Override
    public List<UserNumber> getTenUsers() {
        List<UserNumber> users = userManagerMapper.getTenDayUser();
        List<UserNumber> pageUsers = new ArrayList<UserNumber>();
        pageUsers.addAll(users);
        Set<String> sets = new HashSet<String>();
        for (int i = 0; i < 10; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            sets.add(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        }
        for (UserNumber user : users) {
            if (sets.contains(user.getDayTime())) {
                sets.remove(user.getDayTime());
            }
        }
        for (String str : sets) {
            pageUsers.add(new UserNumber(str, 0));
        }
        return pageUsers;
    }

    /**
     * @param id
     * @param pwd
     * @param request TODO
     * @Description: 更改密码
     */

    private void changePwd(long id, String pwd, HttpServletRequest request) {
        // 密码初始化为 111111
        try {
            pwd = CipherPwdUtils.cipherPwd(pwd);
        } catch (Exception e) {
            throw new BusinessException("102");
        }
        UserManagerBean userManagerBean = userManagerMapper.selectByUserId(id);
        int r = userManagerMapper.changePassword(id, pwd);
        UserManagerBean userManagerBean2 = userManagerMapper.selectByUserId(id);
        webUtils.userEditLog(request, 16, userManagerBean, userManagerBean2);
        if (r != 1) {
            throw new BusinessException("01019");
        }
    }

    private Integer changeManagerPwd(long id, String pwd) {
        try {
            pwd = CipherPwdUtils.cipherPwd(pwd);
        } catch (Exception e) {
            throw new BusinessException("102");
        }
        int r = userManagerMapper.changeManagerPassword(id, pwd);
        if (r != 1) {
            throw new BusinessException("01019");
        }
        return r;
    }

    /**
     * @param userManagerInfo
     * @return TODO
     * @Description: 校验用户管理信息
     */

    private ResultInfo<UserManagerBean> checkUserManagerInfo(UserManagerInfo userManagerInfo) {
        /*if (deptMapper.selectByPrimaryKey(userManagerInfo.getDeptId()) == null) {
            return new ResultInfo<UserManagerBean>(-1,"1030","部门不存在");
		}else if (dutyMapper.selectByPrimaryKey(userManagerInfo.getDutyId()) == null) {
			return new ResultInfo<UserManagerBean>(-1,"1031","职务不存在");
		}else */
        if (StringUtils.isEmpty(userManagerInfo.getUserName())) {
            return new ResultInfo<UserManagerBean>(-1, "1032", "用户名不能为空");
        } else if (StringUtils.isEmpty(userManagerInfo.getUserCode())) {
            return new ResultInfo<UserManagerBean>(-1, "1033", "工号不能为空");
        } else {
            return new ResultInfo<UserManagerBean>(0, "", "");
        }
    }

    /**
     * 用户完善审核信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public ResultInfo getAudit(long userId) throws Exception {
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

    private UserInfo setUserInfo(long userId, ExpUserPerfectInfo expUserPerfectInfo) throws IOException {
        UserInfo uinfo = new UserInfo();
        uinfo.setUserId(userId);
        uinfo.setUserType(1);
        //查询用户信息
        UserInfo userInfo = userManagerMapper.selectUserInfo(uinfo);
        if (null != userInfo) {
            AxpUtils.copyProperties(userInfo, expUserPerfectInfo);
            if (StringUtils.isNotEmpty(userInfo.getVerifyInfo())) {
                expUserPerfectInfo.setAuditResultInfo(JsonUtils.readValue(userInfo.getVerifyInfo(), ExpUserAuditResultInfo.class));
            }
        }
        return userInfo;
    }

    /**
     * 设置商户信息
     *
     * @param expUserPerfectInfo
     * @param userInfo
     * @return
     * @throws Exception
     */
    private SearchStoreInfo setStoreInfo(ExpUserPerfectInfo expUserPerfectInfo, UserInfo userInfo) throws Exception {
        SearchStoreInfo searchStoreInfo = storeService.selectStoreExpressByPhone(userInfo.getPhone());
        if (null != searchStoreInfo) {
            AxpUtils.copyProperties(searchStoreInfo, expUserPerfectInfo);
            expUserPerfectInfo.setExpressCompanyName(searchStoreInfo.getExpressName());
        }
        return searchStoreInfo;
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

    /**
     * 设置证件信息
     *
     * @param userId
     * @param expUserPerfectInfo
     */
    private void setCredentialUrlInfo(long userId, ExpUserPerfectInfo expUserPerfectInfo) {
        ResultInfo credentialUrls = resourceService.getCredentialUrls(userId);
        if (null != credentialUrls && credentialUrls.getSuccess() == 0) {
            List<CredentialURLInfo> credentialURLInfo = (List<CredentialURLInfo>) credentialUrls.getData();
            if (CollectionUtils.isNotEmpty(credentialURLInfo)) {
                expUserPerfectInfo.setPhotoList(credentialURLInfo);
            }
        }
    }

    /**
     * @return
     * @Description:
     * @see com.zhiduan.axp.service.user.UserManagerService#getTodayNewOrderTotal()
     */
    @Override
    public Integer getTodayNewOrderTotal() {
        return orderUserMapper.getTodayNewOrderTotal();
    }

}
