/**
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.common.WebUtils;
import com.zhiduan.axp.controller.model.Permission;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.Ztree;
import com.zhiduan.axp.controller.model.manager.MenuInfo;
import com.zhiduan.axp.dao.manager.bean.MenuBean;
import com.zhiduan.axp.dao.manager.mapper.MenuMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.MenuService;

/**
 * @author 周琦
 * @ClassName: MenuServiceImpl
 * @Description: 菜单接口实现类
 * @date 2016年4月20日 上午9:20:10
 */
@Service("tbMenuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    @Qualifier("tbMenuMapper")
    private MenuMapper menuMapper;
    @Autowired
    private WebUtils webUtils;
    /**
     * @param menuId
     * @return
     * @Description: 删除菜单
     * @see com.zhiduan.axp.idl.managecenter.service.MenuService#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long menuId,HttpServletRequest request) {
        if (menuId == null) {
            throw new BusinessException("111");
        }
        MenuBean origin = menuMapper.selectByPrimaryKey(menuId);
        int result = menuMapper.deleteByPrimaryKey(menuId);
        if (result == 0) {
            throw new BusinessException("01037");
        }
        webUtils.userDeleteLog(request, 17, origin);
        return result;
    }

    /**
     * @param record
     * @return
     * @Description: 插入菜单
     * @see com.zhiduan.axp.idl.managecenter.service.MenuService#insertSelective(com.zhiduan.axp.idl.managecenter.service.entity.MenuInfo)
     */
    @Override
    public ResultInfo insertSelective(MenuInfo menu,HttpServletRequest request) {
        checkMenu(menu);
        MenuBean menuBean = new MenuBean();
        BeanUtils.copyProperties(menu, menuBean);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("caption", menuBean.getCaption());
        map.put("parentId", menuBean.getParentId());
        int rtn = menuMapper.isMenuExistUnderSameParent(map);
        if (rtn > 0) {
            throw new BusinessException("01036");
        }
        int status = menuMapper.insertSelective(menuBean);
        if (status == 0) {
            throw new BusinessException("01037");
        }
        webUtils.userAddLog(request, 17, menuBean);
        return new ResultInfo(0, "", "添加成功", "");
    }

    /**
     * @param menuId
     * @return
     * @Description: 查询菜单
     * @see com.zhiduan.axp.idl.managecenter.service.MenuService#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public MenuInfo selectByPrimaryKey(Long menuId) {
        if (menuId == null) {
            throw new BusinessException("111");
        }
        MenuBean menuBean = menuMapper.selectByPrimaryKey(menuId);
        MenuInfo menu = null;
        if (menuBean == null) {
            throw new BusinessException("01037");
        } else {
            menu = new MenuInfo();
            BeanUtils.copyProperties(menuBean, menu);
        }
        return menu;
    }

    /**
     * @param
     * @return
     * @Description: 更新菜单
     * @see
     */
    @Override
    public ResultInfo updateByPrimaryKeySelective(MenuInfo menu,HttpServletRequest request) {
         checkMenu(menu);
        if (menu.getMenuId() == null) {
            throw new BusinessException("");
        }
        MenuBean menuBean = new MenuBean();
        BeanUtils.copyProperties(menu, menuBean);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("caption", menuBean.getCaption());
        map.put("parentId", menuBean.getParentId());
        map.put("menuId", menuBean.getMenuId());
        int rtn = menuMapper.isMenuExistUnderSameParent(map);
        if (rtn > 0) {
            throw new BusinessException("01036");
        }
        MenuBean origin = menuMapper.selectByPrimaryKey(menu.getMenuId());
        rtn = menuMapper.updateByPrimaryKeySelective(menuBean);
        if (rtn == 0) {
            throw new BusinessException("01037");
        }
        webUtils.userEditLog(request, 17, origin,menuBean);
        return new ResultInfo(0, "", "更新成功", "");
    }

    /**
     * @return
     * @Description: 查询所有菜单
     * @see
     */
    @Override
    public List<MenuBean> selectAll(MenuBean bean) {
        List<MenuBean> list = menuMapper.selectAll(bean);
        return list;
    }

    /**
     * @return
     * @Description: 获取和权限关联的菜单
     * @see
     */
    @Override
    public List<MenuBean> selectAllForRole() {
        List<MenuBean> list = menuMapper.selectAllForRole();

        //获取一级目录
        List<MenuBean> retunList = new ArrayList<MenuBean>();
        for (MenuBean mune : list) {
            if (mune.getParentId() == null || mune.getParentId() == 0) {
                retunList.add(mune);
            }
        }
        //二级
        for (MenuBean parent : retunList) {
            List<MenuBean> twolist = new ArrayList<MenuBean>();
            for (MenuBean mune : list) {
                if (parent.getMenuId().equals(mune.getParentId())) {
                    List<MenuBean> threelist = new ArrayList<MenuBean>();
                    //三级
                    for (MenuBean three : list) {
                        if (mune.getMenuId().equals(three.getParentId())) {
                            threelist.add(three);
                        }
                    }
                    mune.setChlidrenMenu(threelist);
                    twolist.add(mune);
                }
            }
            parent.setChlidrenMenu(twolist);
        }
        return retunList;
    }

    /**
     * @param menuInfo
     * @return
     * @Description: 字段校验
     */
    private void checkMenu(MenuInfo menu) {
        Pattern pattern = Pattern.compile("^[0|1]{1}$");
        if (StringUtils.isBlank(menu.getMenuStatusCode())
                || StringUtils.isBlank(menu.getMenuDevStatusCode())
                || menu.getBeLeaf() == null
                || StringUtils.isBlank(menu.getCaption())
                || menu.getBeMandatory() == null
                ||(menu.getBeLeaf() == 1 && StringUtils.isBlank(menu.getMenuCtrlPath()))) {
            throw new BusinessException("111");
        }
        if ((menu.getBeLeaf() == 1 && menu.getMenuCtrlPath().length() > 100)
        		|| menu.getCaption().length() > 50
                || (StringUtils.isNotBlank(menu.getHint()) && menu.getHint()
                .length() > 100)
                || (StringUtils.isNotBlank(menu.getDllFile()) && menu.getDllFile().length() > 200)
                || (StringUtils.isNotBlank(menu.getPicFile()) && menu.getPicFile().length() > 200)
                || (StringUtils.isNotBlank(menu.getHotKey()) && menu
                .getHotKey().toString().length() > 20)
                || (StringUtils.isNotBlank(menu.getAction()) && menu
                .getAction().length() > 100)
                || (StringUtils.isNotBlank(menu.getMenuTypeId()) && menu
                .getMenuTypeId().length() > 20)) {
            throw new BusinessException("112");
        }
        if (!pattern.matcher(menu.getBeLeaf().toString()).find()
                || menu.getBeMandatory() != null
                && !pattern.matcher(menu.getBeMandatory().toString()).find())
            throw new BusinessException("114");
        if (menu.getParentId() != null) {
            int count = menuMapper.isMenuExist(menu.getParentId());
            if (count < 1) {
                throw new BusinessException("01037");
            }
        }
    }

    /**
     * @param bean
     * @return
     * @Description: 根据条件查询总数
     * @see com.zhiduan.axp.service.manager.MenuService#getTotal(com.zhiduan.axp.dao.manager.bean.MenuBean)
     */
    @Override
    public Integer getTotal(MenuBean bean) {
        Integer i = menuMapper.getTotal(bean);
        return i;
    }

	/*
     * @Description: 获取菜单树
	 * 
	 * @param bean
	 * 
	 * @return
	 * 
	 * @see
	 * com.zhiduan.axp.service.manager.MenuService#getZtree(com.zhiduan.axp.
	 * dao.manager.bean.MenuBean)
	 */

    @Override
    public List<Ztree> getZtree(MenuBean bean) {
        List<Ztree> ztrees = menuMapper.getZtree(bean);
        List<Ztree> ztreesPage = new ArrayList<Ztree>();
        if (ztrees!=null) {
        	ztreesPage.addAll(ztrees);
		}
        if (ztrees != null && ztrees.size() > 0) {
            for (Ztree ztree : ztrees) {
                if (!StringUtils.isBlank(ztree.getAction())) {
                    String[] actions = ztree.getAction().split(",");
                    for (String action : actions) {
                        Long id = Permission.getPermissionId(action);
                        if (id != null) {
                            ztreesPage.add(new Ztree(ztree.getId()+"_"+ id, "", Permission.getPermissionName(action), "", false, false, ztree.getId(), ""));
                        }
                    }
                }
            }
            ztreesPage.get(0).setOpen(true);
        }
        return ztreesPage;
    }

	/*
     * @Description: 根据角色获取menuId
	 * 
	 * @param roleId
	 * 
	 * @return
	 * 
	 * @see
	 * com.zhiduan.axp.service.manager.MenuService#getRoleMenu(java.lang.Long)
	 */

    @Override
    public List<MenuInfo> getRoleMenu(Long roleId) {
        List<MenuInfo> list = menuMapper.getRoleMenu(roleId);
        List<MenuInfo> pageList = new ArrayList<MenuInfo>();
        if (list!=null) {
        	pageList.addAll(list);
		}
        if (list != null && list.size() > 0) {
            for (MenuInfo menuInfo : list) {
                if (!StringUtils.isBlank(menuInfo.getAction())) {
                    String[] pers = menuInfo.getAction().split(",");
                    if (pers.length > 0) {
                        for (String per : pers) {
                            if (!StringUtils.isBlank(per)) {
                                pageList.add(new MenuInfo().setMenuIdStr(menuInfo.getMenuId() + "_" + Permission.getPermissionId(per)));
                            }
                        }
                    }
                }
            }
        }
        return pageList;
    }

    @Override
    public List<MenuInfo> selectAllParent() {
        return menuMapper.selectAllParent();
    }
}
