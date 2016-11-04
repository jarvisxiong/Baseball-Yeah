/**
 * Copyright (c) 2016, 指端科技.
 */


package com.rofour.baseball.controller.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rofour.baseball.controller.model.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.manager.DeptInfo;
import com.rofour.baseball.controller.model.manager.MenuInfo;
import com.rofour.baseball.controller.model.manager.PropertyDictInfo;
import com.rofour.baseball.dao.manager.bean.MenuBean;
import com.rofour.baseball.service.manager.DeptService;
import com.rofour.baseball.service.manager.MenuService;
import com.rofour.baseball.service.manager.PropertyDictService;

/**
 * @ClassName: MenuController
 * @Description: 管理中心--菜单操作对外控制器
 * @author xl
 * @date 2016年3月26日 下午12:59:59
 *
 */
@Controller
@RequestMapping(value = "/manage/menu")
public class MenuController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 数据库中菜单状态常量
     */
    private static String MENU_STATUS_CODE = "MENU_STATUS_CODE";
    /**
     * 数据库中菜单开发状态常量
     */
    private static String MENU_DEV_STATUS_CODE = "MENU_DEV_STATUS_CODE";
    /**
     * 数据库中菜单类型常量
     */
    private static String MENU_TYPE_ID = "MENU_TYPE_ID";
    @Autowired
    @Qualifier("tbMenuService")
    private MenuService tbMenuService;
    @Autowired
    @Qualifier("propertyDictService")
    private PropertyDictService propertyDictService;
    @Autowired
    @Qualifier("deptService")
    private DeptService deptService;

    /**
     *
     * @Description: 页面跳转
     * @param req
     * @return
     */
    @RequestMapping(value = "goto", method = RequestMethod.GET)
    public String page(HttpServletRequest req) {
        return "manager/menuManager/menuInfoManager";
    }

    /**
     * @Description: 请求菜单状态
     * @param req
     * @param response
     */
    @RequestMapping(value = "getMenuStatus", method = RequestMethod.GET)
    public void getMenuStatus(HttpServletRequest req, HttpServletResponse response) {
        writeJson(getSelectList(MENU_STATUS_CODE), response);
    }

    /**
     * @Description: 请求菜单开发状态
     * @param req
     * @param response
     */
    @RequestMapping(value = "getMenuDevStatus", method = RequestMethod.GET)
    public void getMenuDevStatus(HttpServletRequest req, HttpServletResponse response) {
        writeJson(getSelectList(MENU_DEV_STATUS_CODE), response);
    }

    /**
     * @Description: 请求上级菜单
     * @param req
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "getParent", method = RequestMethod.GET)
    public List<SelectModel> getParent(HttpServletRequest req, HttpServletResponse response, String menuId) {
        List<SelectModel> sellist = new ArrayList<SelectModel>();
        SelectModel sel = new SelectModel();
        sel.setId(" ");
        sel.setText("请选择");
        sellist.add(sel);
        Long originId = null;
        if (menuId != null && menuId.matches("^\\d+$")) {
            originId = Long.valueOf(menuId);
        }
        try {
            List<MenuInfo> infoList = tbMenuService.selectAllParent();
            for (MenuInfo info : infoList) {
                Long selfId = info.getMenuId();
                if (originId == null || !originId.equals(selfId)) { //存在menuId,就为更新操作,更新时parent不能选择自己
                    SelectModel selectModel = new SelectModel();
                    selectModel.setId(selfId.toString());
                    selectModel.setText(info.getCaption());
                    sellist.add(selectModel);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }

    /**
     * @Description: 请求上级菜单
     * @param req
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "getDept", method = RequestMethod.GET)
    public List<SelectModel> getDept(HttpServletRequest req, HttpServletResponse response) {
        List<SelectModel> sellist = new ArrayList<SelectModel>();
        SelectModel sel = new SelectModel();
        sel.setId(" ");
        sel.setText("请选择");
        sellist.add(sel);
        try {
            List<DeptInfo> infoList = deptService.getAllDept();
            for (DeptInfo info : infoList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(info.getDeptId().toString());
                selectModel.setText(info.getDeptName());
                sellist.add(selectModel);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }

    @ResponseBody
    @RequestMapping(value = "getAllParent", method = RequestMethod.POST)
    public List<SelectModel> getAllParent(HttpServletRequest req, HttpServletResponse response, String menuId) {
        List<SelectModel> sellist = new ArrayList<SelectModel>();

        try {
            MenuBean bean = new MenuBean();
            bean.setSort("menuId");//默认以id排序
            bean.setOffset(0);
            bean.setLimit(100);
            List<MenuBean> infoList = tbMenuService.selectAll(bean);
            for (MenuBean info : infoList) {
                Long selfId = info.getMenuId();
                SelectModel selectModel = new SelectModel();
                selectModel.setId(selfId.toString());
                selectModel.setText(info.getCaption());
                sellist.add(selectModel);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }

    @ResponseBody
    @RequestMapping(value = "getEnumPermission", method = RequestMethod.POST)
    public List<SelectModel> getEnumPermission(HttpServletRequest req, HttpServletResponse response, String menuId) {
        List<SelectModel> sellist = new ArrayList<SelectModel>();

        try {
            for (Permission info : Permission.values()) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(info.getDbName());
                selectModel.setText(info.getName());
                sellist.add(selectModel);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }

    /**
     * @Description: 请求菜单类型
     * @param req
     * @param response
     */
    @RequestMapping(value = "getMenuType", method = RequestMethod.GET)
    public void getMenuType(HttpServletRequest req, HttpServletResponse response) {
        writeJson(getSelectList(MENU_TYPE_ID), response);
    }

    /**
     *
     * @Description: 查询所有菜单接口
     * @param request
     * @return ResultInfo 操作结果bean
     */
    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    public void getMenuList(HttpServletRequest request, HttpServletResponse response, @RequestBody MenuBean bean) {
        if (StringUtils.isBlank(bean.getSort())) {
            bean.setSort("menuId");//默认以id排序
        }
        DataGrid<MenuBean> grid = new DataGrid<MenuBean>();
        try {
            List<MenuBean> menuList = tbMenuService.selectAll(bean);
            grid.setRows(menuList);
            grid.setTotal(tbMenuService.getTotal(bean));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    /**
     *
     * @Description: 新增菜单接口
     * @param request
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultInfo<Object> addMenu(HttpServletRequest request, MenuInfo menu) {
        try {
            return tbMenuService.insertSelective(menu, request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }

    /**
     *
     * @Description: 删除菜单接口
     * @param request
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultInfo<Object> deleteMenu(HttpServletRequest request) {
        try {
            String menuId = request.getParameter("menuId");
            tbMenuService.deleteByPrimaryKey(Long.valueOf(menuId), request);
        } catch (Exception e) {
            return new ResultInfo<Object>(-1, "", "删除失败");
        }
        return new ResultInfo<Object>(0, "", "删除成功");
    }

    /**
     *
     * @Description: 更新菜单接口
     * @param request
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultInfo<Object> updateMenu(HttpServletRequest request, MenuInfo menu) {
        try {
            return tbMenuService.updateByPrimaryKeySelective(menu, request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }

    private List<SelectModel> getSelectList(String alias) {
        List<SelectModel> sellist = new ArrayList<SelectModel>();
        SelectModel sel = new SelectModel();
        sel.setId(" ");
        sel.setText("请选择");
        sellist.add(sel);
        try {
            List<PropertyDictInfo> infoList = propertyDictService.getPropertyDictList();
            for (PropertyDictInfo info : infoList) {
                if (info.getCallAlias().equals(alias)) {
                    SelectModel selectModel = new SelectModel();
                    selectModel.setId(info.getPropertyId());
                    selectModel.setText(info.getPropertyValue());
                    sellist.add(selectModel);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }


    /**
     * @Method: menuZtree
     * @Description: 获取菜单树
     * @param @param request
     * @param @param response
     * @param @return    参数
     * @return List<Ztree>    返回类型
     * @throws
     * @author ZhangLei
     * @date 2016年5月11日 下午8:16:21
     **/

    @RequestMapping(value = "/menuZtree", method = RequestMethod.GET)
    @ResponseBody
    public List<Ztree> menuZtree(MenuBean bean) {
        return tbMenuService.getZtree(bean);
    }


    @RequestMapping(value = "/getRoleMenu", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuInfo> getRoleMenu(Long roleId) {
        return tbMenuService.getRoleMenu(roleId);
    }


}
