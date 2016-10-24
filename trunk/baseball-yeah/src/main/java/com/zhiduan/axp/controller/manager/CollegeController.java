/**
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiduan.axp.controller.model.store.ExpStoreInfo;
import com.zhiduan.axp.dao.manager.bean.CollegeBean;
import com.zhiduan.axp.dao.manager.bean.CollegeManageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiduan.axp.common.JsonUtils;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.controller.model.SelectPageModel;
import com.zhiduan.axp.controller.model.manager.CollegeInfo;
import com.zhiduan.axp.controller.model.manager.CollegeManageInfo;
import com.zhiduan.axp.controller.model.manager.DeptInfo;
import com.zhiduan.axp.service.manager.CollegeService;

/**
 * @author xl
 * @ClassName: CollegeController
 * @Description: 管理中心--学校操作对外控制器
 * @date 2016年3月26日 下午12:50:21
 */

@Controller
@RequestMapping(value = "/manage/college")
public class CollegeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("collegeService")
    private CollegeService collegeService;

    /**
     * @param request
     * @return ResultInfo 操作结果bean
     * @Description: 获取学校列表接口
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getCollegeList(HttpServletRequest request) {
        try {
            List<CollegeInfo> collegeList = collegeService.getCollegeList();
            return new ResultInfo(0, "", "查询成功", collegeList);
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     * @param request
     * @return ResultInfo 操作结果bean
     * @Description: 学校管理查询接口
     */
    @RequestMapping(value = "/cs/query", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo<Object> getManageCollegeList(HttpServletRequest request) {
        try {
            List<CollegeManageInfo> collegeList = collegeService.selectManageCollegeInfo();
            return new ResultInfo<Object>(0, "", "查询成功", collegeList);
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    @ResponseBody
    @RequestMapping("/getcollageforsel")
    public ResultInfo<Object> selectExpStoreInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<CollegeManageInfo> collegeList = collegeService.selectManageCollegeInfo();
            List<SelectModel> sellist = new ArrayList<>();
            for (CollegeManageInfo collegeStoreInfo : collegeList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(collegeStoreInfo.getCollegeId().toString());
                selectModel.setText(collegeStoreInfo.getFullName());
                sellist.add(selectModel);
            }
            return new ResultInfo<Object>(0, "", "查询成功", sellist);
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    @ResponseBody
    @RequestMapping("/getcollageajaxsel")
    public ResultInfo<Object> selectCollageAjaxInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            CollegeBean collegeBean = new CollegeBean();
//            logger.info(request.getParameter("fullName"));
            collegeBean.setFullName(request.getParameter("fullName"));
            collegeBean.setEditCollegeId(request.getParameter("editCollegeId"));
            List<CollegeManageBean> collegeList = collegeService.selectCollegeAJAX(collegeBean);
            List<SelectModel> sellist = new ArrayList<>();
            for (CollegeManageBean collegeStoreInfo : collegeList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(collegeStoreInfo.getCollegeId().toString());
                selectModel.setText(collegeStoreInfo.getFullName());
                sellist.add(selectModel);
            }
            return new ResultInfo<Object>(0, "", "查询成功", sellist);
        } catch (Exception e) {
            return processException(e, logger);
        }
    }


    @ResponseBody
    @RequestMapping("/getCollageForSel")
    public ResultInfo<Object> selectCollegeList(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<CollegeManageInfo> collegeList = collegeService.selectManageCollegeInfo();
            List<SelectModel> sellist = new ArrayList<>();
            SelectModel sel = new SelectModel();
            sel.setId(" ");
            sel.setText("请选择");
            sellist.add(sel);
            for (CollegeManageInfo collegeStoreInfo : collegeList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(collegeStoreInfo.getCollegeId().toString());
                selectModel.setText(collegeStoreInfo.getFullName());
                sellist.add(selectModel);
            }
            return new ResultInfo<Object>(0, "", "查询成功", sellist);
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     * @param request
     * @return
     * @Description: 学校管理新增、修改界面控件数据源
     */
    @RequestMapping(value = "/getPropertyInfos", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo<Object> getPropertyInfos(HttpServletRequest request) {
        try {
            ResultInfo<Object> collegeProperties = collegeService.getPropertyInfos();
            return collegeProperties;
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     * @param request
     * @return ResultInfo 操作结果bean
     * @Description: 新增学校接口
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultInfo addCollege(HttpServletRequest request) {
        CollegeInfo collegeInfo = null;
        try {
            String data = request.getParameter("data");
            collegeInfo = JsonUtils.readValue(data, CollegeInfo.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JSON_ERROR;
        }
        try {
            return collegeService.saveCollege(collegeInfo, request);
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     * @param request
     * @return ResultInfo 操作结果bean
     * @Description: 删除学校接口
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultInfo deleteCollege(HttpServletRequest request) {
        CollegeInfo collegeInfo = null;
        try {
            String data = request.getParameter("data");
            collegeInfo = JsonUtils.readValue(data, CollegeInfo.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JSON_ERROR;
        }

        try {

            collegeService.deleteCollege(collegeInfo.getCollegeId(), request);

        } catch (Exception e) {
            return processException(e, logger);
        }
        return new ResultInfo(0, "", "删除成功");
    }

    /**
     * @param request
     * @return ResultInfo 操作结果bean
     * @Description: 更新学校信息接口
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultInfo updateCollege(HttpServletRequest request) {
        CollegeInfo collegeInfo = null;
        try {
            String data = request.getParameter("data");
            collegeInfo = JsonUtils.readValue(data, CollegeInfo.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JSON_ERROR;
        }

        try {
            ResultInfo resultInfo = collegeService.updateCollege(collegeInfo, request);
            return resultInfo;
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     * @param request
     * @param collegeInfo
     * @return
     * @Description: 新增编辑学校控制器
     */
    @ResponseBody
    @RequestMapping(value = "/presave", method = RequestMethod.POST)
    public ResultInfo presave(HttpServletRequest request, CollegeInfo collegeInfo) {
        try {
            String data = request.getParameter("data");
            collegeInfo = JsonUtils.readValue(data, CollegeInfo.class);
            if (StringUtils.isEmpty(collegeInfo.getCollegeId())) {
                return collegeService.saveCollege(collegeInfo, request);
            } else {
                return collegeService.updateCollege(collegeInfo, request);
            }
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     * @param request
     * @return ResultInfo 操作结果bean
     * @Description: 加载学校下拉列表查询接口
     */
    @RequestMapping(value = "/queryselect", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getSelectCollegeList(HttpServletRequest request) {
        try {
            List<CollegeInfo> collegeList = collegeService.getSelectCollegeList();
            List<SelectModel> sellist = new ArrayList<SelectModel>();
            // SelectPageModel pageModel=new SelectPageModel();
            for (CollegeInfo colInfo : collegeList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(colInfo.getCollegeId().toString());
                selectModel.setText(colInfo.getFullName());
                sellist.add(selectModel);
            }
            // pageModel.setTotal(sellist.size());
            // pageModel.setRows(sellist);
            return new ResultInfo(0, "", "查询成功", sellist);
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/changemode", method = RequestMethod.POST)
    public void changepacketmodel(HttpServletRequest request, HttpServletResponse response) {
        try {
            String data = request.getParameter("collegeIds");
            String isok = request.getParameter("isok");
            String[] collegeIds = JsonUtils.readValue(data, String[].class);
            collegeService.changePacketModel(request, isok, collegeIds);
            writeJson(new ResultInfo(0, "", "操作成功"), response);
        } catch (Exception e) {
            writeJson(processException(e, logger), response);
        }
    }
}
