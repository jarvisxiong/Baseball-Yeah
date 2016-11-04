package com.rofour.baseball.controller.order;


import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.AttachConstant;
import com.rofour.baseball.common.Constant;
import com.rofour.baseball.common.HttpClientUtils;
import com.rofour.baseball.common.AxpStream;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.order.AttachmentInfo;
import com.rofour.baseball.dao.order.bean.TbTask;
import com.rofour.baseball.dao.order.bean.TbTaskSub;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.order.TaskOrderService;
import com.rofour.baseball.service.resource.impl.ResourceServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Task order controller.
 *
 * @author will
 * @Description
 * @date 2016 -09-06 10:15:32
 */
@Controller
@RequestMapping("/order/taskorder")
public class TaskOrderController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The Task order service.
     */
    @Resource
    TaskOrderService taskOrderService;

    @Autowired
    @Qualifier("resourceService")
    private ResourceServiceImpl resourceService;


    /**
     * 获取任务
     *
     * @param request  the request
     * @param response the response
     * @param userInfo the user info
     */
    @ResponseBody
    @RequestMapping(value = "/oders")
    public void getManageUsers(HttpServletRequest request, HttpServletResponse response, TbTask userInfo) {
        if (StringUtils.isBlank(userInfo.getSort())) {
            userInfo.setSort("create_time");//默认以注册时间排序
            userInfo.setOrder("desc");//默认倒序
        }

        DataGrid<TbTask> grid = new DataGrid<TbTask>();
        try {
            if ("4".equals(userInfo.getState())) {
                userInfo.setIsDelay("1");
                userInfo.setState("");
            }
            List<TbTask> menuList = taskOrderService.getTasks(userInfo);
            grid.setRows(menuList);
            grid.setTotal(taskOrderService.getTasksCount(userInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }


    /**
     * Add 任务
     *
     * @param req      the req
     * @param response the response
     * @param bean     the bean
     * @return the result info
     */
    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResultInfo<Object> add(HttpServletRequest req, HttpServletResponse response, @RequestBody TbTask bean) {
        ResultInfo<Object> result = new ResultInfo<Object>();

        try {
            if ("".equals(bean.getTheme())) {
                return new ResultInfo<Object>(-1, "0", "任务主题不能为空");
            }
            if ("".equals(bean.getDescription())) {
                return new ResultInfo<Object>(-1, "0", "描述不能为空");
            }
            if ("".equals(bean.getRule())) {
                return new ResultInfo<Object>(-1, "0", "任务规则不能为空");
            }
            if ("".equals(bean.getClaim())) {
                return new ResultInfo<Object>(-1, "0", "要求不能为空");
            }
            if ("".equals(bean.getTaskCover())) {
                return new ResultInfo<Object>(-1, "0", "封面图不能为空");
            }
            bean.setDescription("");
            bean.setCreateTime(new Date());
            List<Long> colleges = new ArrayList<>();
            for (TbTaskSub tbTaskSub : bean.getTbTaskSubs()) {
                if (colleges.contains(tbTaskSub.getCollegeId())) {
                    return new ResultInfo<Object>(-1, "0", "校区不能重复");
                }
                colleges.add(tbTaskSub.getCollegeId());
            }
            result = taskOrderService.insert(bean, req);


            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bizId", bean.getTaskId());
            map.put("attachIds", StringUtils.join(bean.getFiles(), ","));
            String url = Constant.axpurl.get("resource_updattachbizinfo_serv");

            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };

            ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);

            if (result.getSuccess() > 0) {
                return new ResultInfo<Object>(0, "0", "添加成功");
            } else {
                return result;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResultInfo<Object>(0, "0", "添加成功");
    }

    /**
     * Add 子任务
     *
     * @param req      the req
     * @param response the response
     * @param bean     the bean
     * @return the result info
     */
    @RequestMapping(value = "addSub", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResultInfo<Object> addSub(HttpServletRequest req, HttpServletResponse response,
                                     @RequestBody TbTaskSub bean) {
        ResultInfo<Object> result = taskOrderService.insertSub(bean, req);
        if (result.getSuccess() >= 0) {
            return new ResultInfo<Object>(0, "0", "添加成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "添加失败");
        }
    }

    @RequestMapping(value = "updateSub", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResultInfo<Object> updateSub(HttpServletRequest req, HttpServletResponse response,
                                        @RequestBody TbTaskSub bean) {
        ResultInfo<Object> result = taskOrderService.updateSub(bean, req);
        if (result.getSuccess() >= 0) {
            return new ResultInfo<Object>(0, "0", "添加成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "添加失败");
        }
    }


    /**
     * Del 任务
     *
     * @param req      the req
     * @param response the response
     * @param bean     the bean
     * @return the result info
     */
    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ResultInfo<Object> del(HttpServletRequest req, HttpServletResponse response, TbTask bean) {

        if (taskOrderService.del(bean, req) > 0) {
            return new ResultInfo<Object>(0, "0", "删除成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "删除失败,任务含有启用状态；");
        }
    }

    /**
     * 获取单个任务
     *
     * @param request  the request
     * @param response the response
     */
    @RequestMapping(value = "/getdto", method = RequestMethod.POST)
    @ResponseBody
    public void getDto(HttpServletRequest request, HttpServletResponse response) {
        TbTask bean = new TbTask();
        bean.setTaskId(Long.parseLong(request.getParameter("taskId")));
        bean = taskOrderService.getDto(bean);
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bizId", bean.getTaskId());
            map.put("attachType", AttachConstant.TYPE_TASK);
            String url = Constant.axpurl.get("resource_getNailAttachList_serv");
            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };
            ResultInfo<List<AttachmentInfo>> data = (ResultInfo<List<AttachmentInfo>>) HttpClientUtils.post(url, map, TypeRef);
            bean.setAttachmentInfos(data.getData());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        writeJson(bean, response);
    }

    /**
     * 返回子任务
     *
     * @param request  the request
     * @param response the response
     * @param bean     the bean
     */
    @RequestMapping(value = "/postSubList")
    @ResponseBody
    public void postExpInfoList(HttpServletRequest request, HttpServletResponse response, TbTaskSub bean) {
        List<TbTaskSub> tbTaskSubs = taskOrderService.getTaskSubs(bean);
        writeJson(tbTaskSubs, response);
    }

    /**
     * 更新任务
     *
     * @param req      the req
     * @param response the response
     * @param bean     the bean
     * @return the result info
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResultInfo<Object> update(HttpServletRequest req, HttpServletResponse response, @RequestBody TbTask bean) {
        ResultInfo<Object> result = new ResultInfo<Object>(-1, "0", "添加失败");
        if ("".equals(bean.getTaskCover())) {
            return new ResultInfo<Object>(-1, "0", "封面图不能为空");
        }
        result = taskOrderService.update(bean, req);
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bizId", bean.getTaskId());
            map.put("attachIds", StringUtils.join(bean.getFiles(), ","));
            String url = Constant.axpurl.get("resource_updattachbizinfo_serv");
            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };
            ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
            if (bean.getRemoveFiles().size() > 0) {
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("id", StringUtils.join(bean.getRemoveFiles(), ","));
                String url1 = Constant.axpurl.get("resource_del_serv");
                final TypeReference<ResultInfo<?>> TypeRef1 = new TypeReference<ResultInfo<?>>() {
                };
                ResultInfo<?> data1 = (ResultInfo<?>) HttpClientUtils.post(url1, map1, TypeRef1);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (result.getSuccess() > 0) {
            return new ResultInfo<Object>(0, "0", "修改成功");
        } else {
            return result;
        }
    }


    /**
     * 添加子任务
     *
     * @param req      the req
     * @param response the response
     * @param bean     the bean
     * @return the result info
     */
    @ResponseBody
    @RequestMapping(value = "pubSub", method = RequestMethod.POST)
    public ResultInfo<Object> pubSub(HttpServletRequest req, HttpServletResponse response, TbTaskSub bean) {
        int i = taskOrderService.pubSub(bean, req);
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "发布成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "发布失败,任务状态已改变");
        }
    }

    /**
     * Del sub result info.
     *
     * @param req      the req
     * @param response the response
     * @param bean     the bean
     * @return the result info
     */
    @ResponseBody
    @RequestMapping(value = "delSub", method = RequestMethod.POST)
    public ResultInfo<Object> delSub(HttpServletRequest req, HttpServletResponse response, TbTaskSub bean) {

        int i = taskOrderService.delSub(bean, req);
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "删除成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "删除失败,任务状态已改变不能删除");
        }
    }

    /**
     * 停止任务
     * @param req
     * @param response
     * @param bean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "stop", method = RequestMethod.POST)
    public ResultInfo<Object> stop(HttpServletRequest req, HttpServletResponse response, TbTaskSub bean) {

        int i = taskOrderService.stopSub(bean, req);
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "停止成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "停止失败,只能停止已生成任务");
        }
    }


    /**
     * Upload files result info.
     *
     * @param request  the request
     * @param file     the file
     * @param response the response
     * @param model    the model
     * @return the result info
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultInfo<Object> uploadFiles(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file,
                                          HttpServletResponse response, Model model) {

        try {
            UserManagerLoginBean userManagerLoginBean = (UserManagerLoginBean) request.getSession().getAttribute("user");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("attachmentType", AttachConstant.TYPE_TASK);
            map.put("file", AxpStream.getImageStr(file.getInputStream()));
            map.put("fileName", file.getOriginalFilename());
            map.put("userId", userManagerLoginBean.getUserManagerId());
            map.put("userName", userManagerLoginBean.getUserName());
            map.put("sourceId", "operation");

            String url = Constant.axpurl.get("resource_upload_serv");

            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };

            ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
            // 返回参数赋值
            if (data.getSuccess() < 0) {
                return new ResultInfo(-1, "0", "调用AXP接口失败", "");
            }
            return new ResultInfo(0, "0", "", data.getData());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResultInfo(-1, "0", "调用AXP接口失败", "");
    }

}
