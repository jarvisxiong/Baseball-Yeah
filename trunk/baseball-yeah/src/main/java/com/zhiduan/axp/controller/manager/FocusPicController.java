package com.zhiduan.axp.controller.manager;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhiduan.axp.common.*;
import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.resource.ServerInfo;
import com.zhiduan.axp.dao.manager.bean.FocusPicBean;
import com.zhiduan.axp.service.manager.FocusPicService;
import com.zhiduan.axp.service.resource.impl.ResourceServiceImpl;

/**
 * Created by Administrator on 2016-06-30.
 */
@Controller
@RequestMapping("/manage/focuspic")
public class FocusPicController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("focusPicService")
    private FocusPicService focusPicService;
    @Autowired
    @Qualifier("resourceService")
    private ResourceServiceImpl resourceService;


    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    public void getMenuList(HttpServletRequest request, HttpServletResponse response, @RequestBody FocusPicBean bean) {
        if (StringUtils.isBlank(bean.getSort())) {
            bean.setSort("create_time");//默认以id排序
            bean.setOrder("desc");
        }
        DataGrid<FocusPicBean> grid = new DataGrid<FocusPicBean>();
        try {
            List<FocusPicBean> picList = focusPicService.getAll(bean);
            grid.setRows(picList);
            grid.setTotal(focusPicService.selectAllCount(bean));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ResultInfo<Object> del(HttpServletRequest req, HttpServletResponse response, FocusPicBean bean) {
        int i = 0;
        i = focusPicService.del(bean);
//        ResultInfo<Object> result = new ResultInfo<Object>();
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "删除成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "删除失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResultInfo<Object> add(HttpServletRequest req, HttpServletResponse response, FocusPicBean bean) {

        try {
            int i = 0;
            bean.setCreateTime(new Date());
            i = focusPicService.insert(bean);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bizId", bean.getUrl());
            map.put("attachIds", bean.getAdImg());
            String url = Constant.axpurl.get("resource_updattachbizinfo_serv");

            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };

            ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);

            if (i > 0) {
                return new ResultInfo<Object>(0, "0", "添加成功");
            } else {
                return new ResultInfo<Object>(-1, "0", "添加失败");
            }
        } catch (Exception e) {
            return new ResultInfo<Object>(-1, "0", "添加失败");
        }
    }


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultInfo<Object> uploadSampleFiles(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file,
                                                HttpServletResponse response, Model model) {
        try {
            UserManagerLoginBean userManagerLoginBean = (UserManagerLoginBean) request.getSession().getAttribute("user");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("attachmentType", AttachConstant.TYPE_AD);
            map.put("file", axpStream.GetImageStr(file.getInputStream()));
            map.put("fileName", file.getOriginalFilename());
            map.put("userId", userManagerLoginBean.getUserManagerId());
            map.put("userName", userManagerLoginBean.getUserName());
            map.put("sourceId", "operation");

            String url = Constant.axpurl.get("resource_upload_serv");

            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };

            ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
            if (data.getSuccess() < 0) {
                return new ResultInfo(-1, "0", "调用AXP接口失败", "");
            }
            return new ResultInfo(0, "0", "", data.getData());
        } catch (Exception e) {
            return new ResultInfo(-1, "0", "调用AXP接口失败", "");
        }
    }
}
