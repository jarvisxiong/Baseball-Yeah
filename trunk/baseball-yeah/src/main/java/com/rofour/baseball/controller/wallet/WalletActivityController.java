package com.rofour.baseball.controller.wallet;


import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.Constant;
import com.rofour.baseball.common.HttpClientUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.*;
import com.rofour.baseball.dao.activity.bean.AcctActivity;
import com.rofour.baseball.dao.activity.mapper.AcctActivityMapper;
import com.rofour.baseball.dao.manager.bean.CollegeBean;
import com.rofour.baseball.dao.manager.bean.CollegeManageBean;
import com.rofour.baseball.dao.manager.bean.FocusPicBean;
import com.rofour.baseball.service.activity.ActivityService;
import com.rofour.baseball.service.manager.CollegeService;
import com.rofour.baseball.service.manager.FocusPicService;
import com.rofour.baseball.service.resource.impl.ResourceServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 活动路由 controller.
 *
 * @author will
 * @Description
 * @date 2016 -08-08 15:24:31
 */
@Controller
@RequestMapping(value = "/wallet/activity")
public class WalletActivityController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("activityService")
    private ActivityService activityService;

    @Autowired
    @Qualifier("acctActivityMapper")
    private AcctActivityMapper acctActivityMapper;
    @Autowired
    @Qualifier("collegeService")
    private CollegeService collegeService;

    /**
     * 获取活动列表.
     *
     * @param request  the request
     * @param response the response
     * @param bean     the bean
     */
    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    public void getMenuList(HttpServletRequest request, HttpServletResponse response, @RequestBody AcctActivity bean) {
        if (StringUtils.isBlank(bean.getSort())) {
            bean.setSort("createTime");
            bean.setOrder("desc");
        }
        DataGrid<AcctActivity> grid = new DataGrid<AcctActivity>();
        try {
            List<AcctActivity> picList = activityService.getAll(bean);
            grid.setRows(picList);
            grid.setTotal(activityService.selectAllCount(bean));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    /**
     * 获取单个活动模型.
     *
     * @param request  the request
     * @param response the response
     */
    @RequestMapping(value = "/getdto", method = RequestMethod.POST)
    @ResponseBody
    public void getDto(HttpServletRequest request, HttpServletResponse response) {
        AcctActivity bean = new AcctActivity();
        bean.setActivityId(Integer.parseInt(request.getParameter("activityId")));
        bean = activityService.getDto(bean);

        writeJson(bean, response);
    }

    @RequestMapping(value = "/getColegedto", method = RequestMethod.POST)
    @ResponseBody
    public void getColegeDto(HttpServletRequest request, HttpServletResponse response) {
        AcctActivity bean = new AcctActivity();
        bean.setActivityId(Integer.parseInt(request.getParameter("activityId")));
        CollegeBean collegeBean = activityService.getEditColege(bean);

        List<CollegeManageBean> collegeList = collegeService.selectCollegeForEdit(collegeBean);
        List<SelectModel> sellist = new ArrayList<>();
        for (CollegeManageBean collegeStoreInfo : collegeList) {
            SelectModel selectModel = new SelectModel();
            selectModel.setId(collegeStoreInfo.getCollegeId().toString());
            selectModel.setText(collegeStoreInfo.getFullName());
            sellist.add(selectModel);
        }
        writeJson(sellist, response);
    }

    /**
     * 删除活动.
     *
     * @param req      the req
     * @param response the response
     * @param bean     the bean
     * @return the result info
     */
    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ResultInfo<Object> del(HttpServletRequest req, HttpServletResponse response, AcctActivity bean) {
        int i = 0;
        i = activityService.del(bean, req);
        if (i > 0) {
            ResultInfo<Object> resultCall = activityService.delchersCache(bean);
            if (resultCall.getSuccess() < 0) {
                return resultCall;
            }
            return new ResultInfo<Object>(0, "0", "删除成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "删除失败,活动为启用状态；");
        }
    }

    /**
     * 更改活动状态 .
     *
     * @param req      the req
     * @param response the response
     * @param bean     the bean
     * @return the
     */
    @ResponseBody
    @RequestMapping(value = "setstate", method = RequestMethod.POST)
    public ResultInfo<Object> setstate(HttpServletRequest req, HttpServletResponse response, AcctActivity bean) {

        int i = 0;
        AcctActivity oldBean = acctActivityMapper.selectByPrimaryKey(bean.getActivityId());
        if (bean.getState().equals(3) || bean.getState().equals(2)) {
            i = activityService.uptateState(bean, req);
        } else {
            return new ResultInfo<Object>(-1, "0", "修改失败");
        }
        ResultInfo<Object> result = new ResultInfo<Object>();
        if (i > 0) {
            //调用服务出错时要改回原来的状态
            try {
                ResultInfo<Object> resultCall;
                if (bean.getState().equals(3)) {
                    resultCall = activityService.delchersCache(bean);
                } else {
                    resultCall = activityService.addchersCache(bean);
                }
                if (resultCall.getSuccess() < 0) {
                    //改回原来的状态
                    activityService.uptateState(oldBean, req);
                    return resultCall;
                }
            } catch (Exception e) {
                //改回原来的状态
                activityService.uptateState(oldBean, req);
                return new ResultInfo<Object>(-1, "0", "修改失败，接口调用失败；");
            }


            return new ResultInfo<Object>(0, "0", "修改成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "修改失败");
        }
    }

    /**
     * 生成活动代金券
     *
     * @param req      the req
     * @param response the response
     * @param bean     the bean
     * @return the result info
     */
    @ResponseBody
    @RequestMapping(value = "generate", method = RequestMethod.POST)
    public ResultInfo<Object> generate(HttpServletRequest req, HttpServletResponse response, AcctActivity bean) {
        int i = 0;
        AcctActivity retDto = acctActivityMapper.selectByPrimaryKey(bean.getActivityId());
        if (!retDto.getState().equals(0)) {
            return new ResultInfo<Object>(0, "0", "只有新添加状态可以生成代金券!");
        }
        i = activityService.generate(bean, req);
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "删除成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "删除失败");
        }
    }


    /**
     * 新增活动.
     *
     * @param req      the req
     * @param response the response
     * @param bean     the bean
     * @return the result info
     */
    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResultInfo<Object> add(HttpServletRequest req, HttpServletResponse response, @RequestBody AcctActivity bean) {
        ResultInfo<Object> result = new ResultInfo<Object>();
        AcctActivity isExtistBean = new AcctActivity();
        isExtistBean.setActivityName(bean.getActivityName());
        Integer i = acctActivityMapper.selectIsExtisNameCount(isExtistBean);
        if (i > 0) {
            return new ResultInfo<Object>(-1, "0", "活动名称不能重复");
        }
        result = activityService.beanCheck(bean);
        if (result.getSuccess() >= 0) {
            bean.setCreateTime(new Date());
            result = activityService.insert(bean, req);
            if (result.getSuccess() > 0) {
                return new ResultInfo<Object>(0, "0", "添加成功");
            } else {
                return result;
            }
        } else {
            return result;
        }

    }


    /**
     * 编辑活动
     *
     * @param req      the req
     * @param response the response
     * @param bean     the bean
     * @return the result info
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResultInfo<Object> update(HttpServletRequest req, HttpServletResponse response, @RequestBody AcctActivity bean) {
        ResultInfo<Object> result = new ResultInfo<Object>();
        AcctActivity isExtistBean = new AcctActivity();
        isExtistBean.setActivityName(bean.getActivityName());
        isExtistBean.setActivityId(bean.getActivityId());
        Integer i = acctActivityMapper.selectIsExtisNameUpdate(isExtistBean);
        if (i > 0) {
            return new ResultInfo<Object>(-1, "0", "活动名称不能重复");
        }
        result = activityService.beanCheck(bean);
        if (result.getSuccess() >= 0) {
            result = activityService.update(bean, req);
            if (result.getSuccess() > 0) {
                return new ResultInfo<Object>(0, "0", "修改成功");
            } else {
                return result;
            }
        } else {
            return result;
        }
    }

    /**
     * 获取活动下拉框数据
     *
     * @return
     */
    @RequestMapping(value = "/activityselect", method = RequestMethod.POST)
    @ResponseBody
    public List<SelectSet> activitySelect() throws Exception {
        return activityService.getActivitySelect();
    }

    /**
     * 获区策略下拉框数据
     *
     * @return
     */
    @RequestMapping(value = "/policyselect", method = RequestMethod.POST)
    @ResponseBody
    public List<SelectSet> policySelect(HttpServletRequest request) throws Exception {
        String activityId = request.getParameter("activityId");
        return activityService.getPolicySelect(Long.valueOf(activityId));
    }
}
