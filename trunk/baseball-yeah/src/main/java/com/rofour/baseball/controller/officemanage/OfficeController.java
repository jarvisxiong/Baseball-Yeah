/**
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.officemanage;


import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.office.OfficeQueryInfo;
import com.rofour.baseball.dao.officemanage.bean.OfficeAuditInfo;
import com.rofour.baseball.dao.officemanage.bean.OfficeBean;
import com.rofour.baseball.dao.officemanage.bean.OfficeStoreBean;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.officemanage.OfficeService;
import com.rofour.baseball.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: OfficeController
 * @Description: 职务管理
 * @author WJ
 * @date 2016-10-11 16:24:31
 *
 */
@Controller
@RequestMapping("/office")
public class OfficeController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OfficeService officeService;
    @Autowired
    private UserService userService;

    /**
     * @Description: 跳转
     * @return ModelAndView
     * @author WJ
     *
     */
    @RequestMapping(value = "/gotoOfficeManage", method = RequestMethod.GET)
    public ModelAndView gotoOfficeManage(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("officemanage/ceoManagerIndex");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }

    /**
     * @Description: 跳转
     * @return ModelAndView
     * @author WJ
     *
     */
    @RequestMapping(value = "/gotoOfficeEdit", method = RequestMethod.GET)
    public ModelAndView gotoOfficeEdit(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            Map<String, String> model = new HashMap<>();
            model.put("userId", request.getParameter("userId"));
            model.put("office", request.getParameter("office"));
            model.put("college", request.getParameter("college"));
            return new ModelAndView("officemanage/officeEdit", model);
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }

    /**
     * @Description: 跳转
     * @return ModelAndView
     * @author WJ
     *
     */
    @RequestMapping(value = "/gotoPuserAdd", method = RequestMethod.GET)
    public ModelAndView gotoPuserAdd(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            Map<String, String> model = new HashMap<>();
            model.put("userId", request.getParameter("userId"));
            model.put("office", request.getParameter("office"));
            model.put("college", request.getParameter("college"));
            return new ModelAndView("officemanage/officePuserAdd", model);
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }

    /**
     * @Description: 跳转审核页面
     * @return ModelAndView
     * @author ZXY
     */
    @RequestMapping(value = "/gotoOfficeAudit", method = RequestMethod.GET)
    public ModelAndView gotoOfficeAudit(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("officemanage/officeAudit");
        } else {
            return new ModelAndView("/error/noPermission");
        }
    }

    /**
     * @Description: 获取职务审核记录
     * @return ModelAndView
     * @author ZXY
     */
    @RequestMapping(value = "/getOfficeAudit", method = RequestMethod.POST)
    public void getOfficeAudit(HttpServletRequest request, HttpServletResponse response, @RequestBody OfficeQueryInfo queryInfo) throws Exception {
        logger.debug("获取职务审核记录");
        if (queryInfo == null) {
            logger.error("传入参数为空");
        } else {
            DataGrid<OfficeAuditInfo> grid = new DataGrid<>();
            try {
                Map retMap = officeService.getOfficeAudit(queryInfo);
                grid.setRows((List<OfficeAuditInfo>) retMap.get("list"));
                grid.setTotal(Integer.parseInt(retMap.get("count").toString()));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            writeJson(grid, response);
        }
    }

    /**
     * @Description: 查询
     *
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void list(HttpServletRequest request, HttpServletResponse response, @RequestBody OfficeQueryInfo info) throws Exception {
        if (StringUtils.isBlank(info.getSort())) {
            info.setOrder("DESC");
            info.setSort("createDate");//默认以创建时间排序
        }
        DataGrid<OfficeBean> grid = new DataGrid<OfficeBean>();
        try {
            List<OfficeBean> result = officeService.list(info);
            grid.setRows(result);
            grid.setTotal(officeService.getTotal(info));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    /**
     * @Description: 查询
     *
     */
    @RequestMapping(value = "/listPuser", method = RequestMethod.POST)
    public void listPuser(HttpServletRequest request, HttpServletResponse response, @RequestBody OfficeQueryInfo info) throws Exception {
        if (StringUtils.isBlank(info.getSort())) {
            info.setOrder("DESC");
            info.setSort("createDate");//默认以创建时间排序
        }
        DataGrid<OfficeBean> grid = new DataGrid<OfficeBean>();
        try {
            List<OfficeBean> result = officeService.listPuser(info);
            grid.setRows(result);
            grid.setTotal(officeService.getPuserTotal(info));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    /**
     * @Description: 启用和禁用
     *
     */
    @ResponseBody
    @RequestMapping(value = "/stateupdate", method = RequestMethod.POST)
    public ResultInfo<Object> stateupdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String userId = request.getParameter("userId");
            String beEnabled = request.getParameter("beEnabled");
            String office = request.getParameter("office");
            if (StringUtils.isBlank(userId) || StringUtils.isBlank(beEnabled)) {
                throw new BusinessException("111");
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("userId", userId);
            map.put("beEnabled", beEnabled);
            map.put("office", office);
            return userService.updatePuserStatus(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }

    /**
     * @Description: 解除职务
     *
     */
    @ResponseBody
    @RequestMapping(value = "/dismiss", method = RequestMethod.POST)
    public ResultInfo<Object> dismiss(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String userId = request.getParameter("userId");
            String office = request.getParameter("office");
            if (StringUtils.isBlank(userId) || !userId.matches("^\\d+$")
                    || StringUtils.isBlank(office) || !office.matches("^\\d+$")) {
                throw new BusinessException("110");
            }
            return officeService.dismiss(Long.valueOf(userId), Integer.parseInt(office));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }

    /**
     * @Description: 审核
     *
     */
    @ResponseBody
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public ResultInfo audit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultInfo resultInfo = null;
        try {
            String auditId = request.getParameter("auditId");
            String isPass = request.getParameter("isPass");
            UserManagerLoginBean user = (UserManagerLoginBean) request.getSession().getAttribute("user");
            if (StringUtils.isBlank(auditId) || StringUtils.isBlank(isPass)) {
                throw new BusinessException("110");
            }
            officeService.audit(Long.parseLong(auditId), Boolean.parseBoolean(isPass), user.getUserManagerId());
            resultInfo = new ResultInfo(0, "", "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultInfo = processException(e, logger);
        }
        return resultInfo;
    }

    /**
     * @Description: 职务编辑详情
     *
     */
    @ResponseBody
    @RequestMapping(value = "/officedetail", method = RequestMethod.POST)
    public ResultInfo<Object> officedetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String userId = request.getParameter("userId");
            if (StringUtils.isBlank(userId) || !userId.matches("^\\d+$")) {
                throw new BusinessException("110");
            }
            return officeService.officeDetail(Long.parseLong(userId));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }

    /**
     * @Description 请求图片
     *
     */
    @ResponseBody
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public ResultInfo<Object> image(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String userId = request.getParameter("userId");
            String type = request.getParameter("type");
            if (StringUtils.isBlank(userId) || !userId.matches("^\\d+$") || StringUtils.isBlank(type)) {
                throw new BusinessException("110");
            }
            return officeService.getUrl(Long.parseLong(userId), type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }

    /**
     * @Description: 查询属下的小派
     *
     */
    @RequestMapping(value = "/attached", method = RequestMethod.POST)
    public void attached(HttpServletRequest request, HttpServletResponse response, @RequestBody OfficeQueryInfo info) throws Exception {
        if (StringUtils.isBlank(info.getSort())) {
            info.setOrder("DESC");
            info.setSort("createDate");//默认以创建时间排序
        }
        DataGrid<OfficeBean> grid = new DataGrid<OfficeBean>();
        try {
            List<OfficeBean> result = officeService.queryAttached(info);
            grid.setRows(result);
            grid.setTotal(officeService.userTotal(info));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    /**
     * @Description: 查询属下的商户
     *
     */
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public void store(HttpServletRequest request, HttpServletResponse response, @RequestBody OfficeQueryInfo info) throws Exception {
        DataGrid<OfficeStoreBean> grid = new DataGrid<OfficeStoreBean>();
        try {
            List<OfficeStoreBean> result = officeService.queryAttachedStores(info);
            grid.setRows(result);
            grid.setTotal(officeService.storeTotal(info));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    /**
     * @Description: 小派删除, 支持批量
     *
     */
    @ResponseBody
    @RequestMapping(value = "/pDel", method = RequestMethod.POST)
    public ResultInfo<Object> pDel(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> map) {
        try {
            return officeService.pDel(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }

    /**
     * @Description: 商户删除, 支持批量
     *
     */
    @ResponseBody
    @RequestMapping(value = "/sDel", method = RequestMethod.POST)
    public ResultInfo<Object> sDel(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> map) {
        try {
            return officeService.sDel(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }

    /**
     * @Description: 小派增加, 支持批量
     *
     */
    @ResponseBody
    @RequestMapping(value = "/pAdd", method = RequestMethod.POST)
    public ResultInfo<Object> pAdd(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> map) {
        try {
            return officeService.pAdd(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }
}
