/**
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.service.manager.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.common.WebUtils;
import com.rofour.baseball.controller.model.Permission;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.MenuInfo;
import com.rofour.baseball.controller.model.manager.RoleInfo;
import com.rofour.baseball.dao.manager.bean.RoleBean;
import com.rofour.baseball.dao.manager.bean.RolePermissionBean;
import com.rofour.baseball.dao.manager.mapper.RoleMapper;
import com.rofour.baseball.dao.manager.mapper.RolePermissionMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.interceptor.ShiroRealm;
import com.rofour.baseball.service.manager.RoleService;

/**
 * @author WW
 * @ClassName: RoleServiceImpl
 * @Description: 管理中心角色业务实现类
 * @date 2016年4月20日 上午9:38:24
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    /* 日志 */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("roleMapper")
    private RoleMapper roleMapper;

    @Autowired
    @Qualifier("rolePermissionMapper")
    RolePermissionMapper rolePermissionMapper;

    @Resource(name = "webUtils")
    WebUtils webUtils;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    /**
     * @return
     * @throws IOException
     * @Description: 查询所有角色信息
     * @see com.rofour.baseball.idl.managecenter.service.RoleService#getRoleList(RoleInfo)
     */
    @Override
    public List<RoleBean> getRoleList(RoleInfo roleInfo) throws IOException {
        logger.error(JsonUtils.translateToJson(roleInfo));
        List<RoleBean> list;
        try {
            list = roleMapper.selectAllRole(roleInfo);
            logger.error(JsonUtils.translateToJson(list));
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @return
     * @Description: 查询所有角色信息
     * @see com.rofour.baseball.idl.managecenter.service.RoleService#getRoleList(RoleInfo)
     */
    @Override
    public List<RoleBean> getRoleList() {
        List<RoleBean> list = roleMapper.selectAllRoleSelect();
        return list;
    }

    /**
     * @param roleInfo
     * @return
     * @Description: 添加角色业务
     * @see com.rofour.baseball.idl.managecenter.service.RoleService#addRole(com.rofour.baseball.idl.managecenter.service.entity.RoleInfo, HttpServletRequest)
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResultInfo<RoleBean> addRole(RoleInfo roleInfo, HttpServletRequest request) {
        ResultInfo<RoleBean> result = validate(roleInfo);
        if (result.getSuccess() == 0) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("roleName", roleInfo.getRoleName());
            if (!ifNameExist(map)) {
                return new ResultInfo<RoleBean>(-1, "1050", "角色已存在");
            } else if (roleMapper.insert(roleInfo) > 0) {
                webUtils.userAddLog(request, 8, roleInfo);
                List<String> menuIds = roleInfo.getCheckNodes();
                List<RolePermissionBean> menus = new ArrayList<RolePermissionBean>();
                Map<Long, String> ma = new HashMap<Long, String>();
                //组合menuId
                for (String idString : menuIds) {
                    List<String> idsArray = new ArrayList<String>();
                    if (idString.contains("_")) {
                        String[] m = idString.split("_");
                        idsArray.add(m[0]);
                        idsArray.add(m[1]);
                    } else {
                        idsArray.add(idString);
                    }
                    if (ma.containsKey(Long.valueOf(idsArray.get(0)))) {
                        String action = ma.get(Long.valueOf(idsArray.get(0)));
                        if (idsArray.size() > 1) {
                            String newAction = Permission.getDbNameById(Long.valueOf(idsArray.get(1)));
                            if (StringUtils.isBlank(action)) {
                                ma.put(Long.valueOf(idsArray.get(0)), newAction);
                            } else {
                                ma.put(Long.valueOf(idsArray.get(0)), action + "," + newAction);
                            }
                        }
                    } else {
                        if (idsArray.size() > 1) {
                            String newAction = Permission.getDbNameById(Long.valueOf(idsArray.get(1)));
                            ma.put(Long.valueOf(idsArray.get(0)), newAction);
                        } else {
                            ma.put(Long.valueOf(idsArray.get(0)), "");
                        }
                    }
                }

                for (Map.Entry<Long, String> entry : ma.entrySet()) {
                    menus.add(new RolePermissionBean(roleInfo.getRoleId(), entry.getKey(), entry.getValue()));
                }
                List<RolePermissionBean> rolePermissionBeans = rolePermissionMapper.selectByRoleId(roleInfo.getRoleId());
                rolePermissionMapper.deleteByRoleId(roleInfo.getRoleId());
                webUtils.userDeleteLog(request, 8, rolePermissionBeans);
                if (menus != null && menus.size() > 0) {
                    rolePermissionMapper.addMenus(menus);
//                    rolePermissionMapper.batchInsert(menus);
                    webUtils.userAddLog(request, 8, menus);
                }

                return new ResultInfo<RoleBean>(0, "", "");
            } else {
                return new ResultInfo<RoleBean>(-1, "1051", "增加角色失败");
            }
        } else {
            return result;
        }
    }

    /**
     * @param role
     * @return
     * @Description修改角色业务
     * @see com.rofour.baseball.idl.managecenter.service.RoleService#updateRole(com.rofour.baseball.idl.managecenter.service.entity.RoleInfo, HttpServletRequest)
     */
    public int updateRole(RoleInfo role, HttpServletRequest request) {
        validate(role);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleName", role.getRoleName());
        map.put("roleId", role.getRoleId());

        boolean exist = ifNameExist(map);
        if (exist) {
            int result = roleMapper.updateByPrimaryKey(role);
//			webUtils.userEditLog(request, "tb_role", "", role);
            if (result == 0) {
                throw new BusinessException("01009");
            }
            return result;
        } else {
            throw new BusinessException("01008");
        }
    }

    /**
     * @param roleId
     * @return
     * @Description:查询角色业务
     * @see com.rofour.baseball.idl.managecenter.service.RoleService#selectRole(java.lang.Long)
     */
    public RoleInfo selectRole(Long roleId) {

        RoleBean record = roleMapper.selectByPrimaryKey(roleId);
        if (record != null) {
            RoleInfo role = new RoleInfo();
            BeanUtils.copyProperties(record, role);
            return role;
        } else {
            throw new BusinessException("01009");
        }
    }

    /**
     * @param map
     * @return
     * @Description:判断是否存在重复的角色名称
     * @see com.rofour.baseball.idl.managecenter.service.RoleService#ifNameExist(java.util.Map)
     */
    public boolean ifNameExist(Map<String, Object> map) {

        int exist = roleMapper.ifNameExist(map);
        if (exist > 0) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * @param role
     * @return ResultInfo 操作结果bean
     * @Description: 参数校验
     */
    private ResultInfo<RoleBean> validate(RoleInfo role) {
        Pattern pattern = Pattern.compile("^[0|1]{1}$");
        if (role.getBeSysPrivilege() == null
                || StringUtils.isBlank(role.getRoleName())
                || role.getRoleType() == null) {
            return new ResultInfo<RoleBean>(-1, "1052", "参数不全");
        } else if (role.getRoleType().toString().length() > 1
                || role.getRoleName().trim().length() > 30) {
            return new ResultInfo<RoleBean>(-1, "1053", "参数长度不和法");
        } else if (!pattern.matcher(role.getBeSysPrivilege().toString()).find()) {
            return new ResultInfo<RoleBean>(-1, "1054", "是否系统管理员参数不正确");
        } else {
            return new ResultInfo<RoleBean>(0, "", "");
        }
    }


    /**
     * @param role
     * @return ResultInfo 操作结果bean
     * @Description: 通过用户名获取角色
     */
    @Override
    public List<RoleBean> selectAllRoleByUserName(String userName) {
        List<RoleBean> roles = roleMapper.selectAllRoleByUserName(userName);
        if (roles == null) {
            throw new BusinessException("01010");
        }
        return roles;
    }


    /**
     * @param role
     * @return ResultInfo 操作结果bean
     * @Description: 通过角色id删除角色
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResultInfo<RoleBean> deleteRole(Long roleId, HttpServletRequest request) {
        RoleBean role = roleMapper.selectByPrimaryKey(roleId);
        int num = roleMapper.deleteByPrimaryKey(roleId);
        webUtils.userDeleteLog(request, 8, role);
        if (num == 0) {
            return new ResultInfo<RoleBean>(-1, "1044", "删除角色失败");
        } else {
            rolePermissionMapper.deleteByRoleId(roleId);
            return new ResultInfo<RoleBean>(0, "", "");
        }
    }


    /**
     * @param role
     * @return ResultInfo 操作结果bean
     * @Description: 获取角色总数
     */
    @Override
    public Integer getTotal(RoleInfo roleInfo) {
        Integer i = roleMapper.getTotal(roleInfo);
        if (i == null) {
            throw new BusinessException("1008");
        }
        return i;
    }


    /**
     * @param role
     * @return ResultInfo 操作结果bean
     * @Description: 更新角色权限
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResultInfo<RoleBean> updateRoleAndPermission(RoleInfo roleInfo, HttpServletRequest request) {
        RoleBean role = roleMapper.selectByPrimaryKey(roleInfo.getRoleId());
        roleMapper.updateByPrimaryKey(roleInfo);
        webUtils.userEditLog(request, 8, role, roleInfo);
        List<String> menuIds = roleInfo.getCheckNodes();
        List<RolePermissionBean> menus = new ArrayList<RolePermissionBean>();
        Map<Long, String> ma = new HashMap<Long, String>();
        //组合menuId
        for (String idString : menuIds) {
            List<String> idsArray = new ArrayList<String>();
            if (idString.contains("_")) {
                String[] m = idString.split("_");
                idsArray.add(m[0]);
                idsArray.add(m[1]);
            } else {
                idsArray.add(idString);
            }
            if (ma.containsKey(Long.valueOf(idsArray.get(0)))) {
                String action = ma.get(Long.valueOf(idsArray.get(0)));
                if (idsArray.size() > 1) {
                    String newAction = Permission.getDbNameById(Long.valueOf(idsArray.get(1)));
                    if (StringUtils.isBlank(action)) {
                        ma.put(Long.valueOf(idsArray.get(0)), newAction);
                    } else {
                        ma.put(Long.valueOf(idsArray.get(0)), action + "," + newAction);
                    }
                }
            } else {
                if (idsArray.size() > 1) {
                    String newAction = Permission.getDbNameById(Long.valueOf(idsArray.get(1)));
                    ma.put(Long.valueOf(idsArray.get(0)), newAction);
                } else {
                    ma.put(Long.valueOf(idsArray.get(0)), "");
                }
            }
        }

        for (Map.Entry<Long, String> entry : ma.entrySet()) {
            menus.add(new RolePermissionBean(roleInfo.getRoleId(), entry.getKey(), entry.getValue()));
        }
        List<RolePermissionBean> permissionBeans = rolePermissionMapper.selectByRoleId(roleInfo.getRoleId());
        rolePermissionMapper.deleteByRoleId(roleInfo.getRoleId());
        webUtils.userDeleteLog(request, 8, permissionBeans);
        if (menus != null && menus.size() > 0) {
            rolePermissionMapper.addMenus(menus);
//            rolePermissionMapper.batchInsert(menus);
            webUtils.userAddLog(request, 8, menus);
        }
        redisTemplate.delete("roleAndPermission");
        return new ResultInfo<RoleBean>(0, "", "");
    }
}
