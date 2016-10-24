/**
 * Copyright (c) 2016, 指端科技.
 */


package com.zhiduan.axp.controller.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiduan.axp.controller.model.manager.CityInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiduan.axp.common.JsonUtils;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.controller.model.SimpleZtree;
import com.zhiduan.axp.controller.model.manager.ProvinceInfo;
import com.zhiduan.axp.service.manager.CityService;
import com.zhiduan.axp.service.manager.ProvinceService;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @ClassName: ProvinceController
 * @Description: 省的增删改查对外接口
 * @author liujingbo
 * @date 2016年3月26日 下午8:41:10
 *
 */

@Controller

@RequestMapping(value = "/manage/province")

public class ProvinceController extends BaseController {

    // 日志
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("provinceService")
    private ProvinceService provinceservice;
    @Autowired
    @Qualifier("cityService")
    private CityService cityService;

    /**
     *
     * @Description: 增加省份
     * @param request
     * @param response
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/insertprovince", method = RequestMethod.POST)
    public ResultInfo<Object> insert(HttpServletRequest request, HttpServletResponse response, ProvinceInfo provinceinfo) {
        try {
            provinceservice.insert(provinceinfo);
        } catch (Exception e) {
            return processException(e, logger);
        }
        return new ResultInfo<Object>(0, "", "添加成功", "");
    }

    /**
     *
     * @Description: 删除一个省
     * @param request
     * @param response
     * @param provinceinfo
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/deleteprovince", method = RequestMethod.POST)
    public ResultInfo<Object> deleteMsm(HttpServletRequest request, HttpServletResponse response, ProvinceInfo provinceinfo) {
        try {
            provinceservice.deleteProvinceCascade(provinceinfo.getProvinceId());
        } catch (Exception e) {
            return processException(e, logger);
        }
        return new ResultInfo<Object>(0, "", "删除成功", "");
    }

    /**
     *
     * @Description: 根据id选择一个省
     * @param request
     * @param response
     * @param provinceinfo
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/selectprovince", method = RequestMethod.POST)
    public ResultInfo<Object> selectProvince(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("provinceId");
        if (id == null || !id.matches("^\\d+$")) {
            return new ResultInfo<Object>(-1, "", "省份id不能为空");
        }
        try {
            return provinceservice.selectByPrimaryKey(Long.valueOf(id));
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     *
     * @Description: 更新一个省
     * @param request
     * @param response
     * @param provinceinfo
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/updateprovince", method = RequestMethod.POST)
    public ResultInfo<Object> updateProvince(HttpServletRequest request, HttpServletResponse response, ProvinceInfo provinceinfo) {
        try {
            provinceservice.updateByPrimaryKey(provinceinfo);
        } catch (Exception e) {
            return processException(e, logger);
        }
        return new ResultInfo<Object>(0, "", "修改成功");
    }

    /**
     *
     * @Description: 查询全部省份信息
     * @param request
     * @param response
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/selectallprovince", method = RequestMethod.GET)
    public List<SimpleZtree> selectAllProvince(HttpServletRequest request, HttpServletResponse response) {
        List<SimpleZtree> list2 = new ArrayList<>();
        try {
            List<ProvinceInfo> provinces = provinceservice.selectAllProvince();
            for (ProvinceInfo item : provinces) {
                //省份id由p_开头
                SimpleZtree ztree = new SimpleZtree("p_" + item.getProvinceId(), item.getProvinceName());
                ztree.setIsParent(true);
                list2.add(ztree);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        return list2;
    }

    /**
     *
     * @Description: 根据区域id查询所有此区域的省份
     * @param request
     * @param response
     * @param cityinfo
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/selectprovincesbyareaid", method = RequestMethod.POST)
    public List<SimpleZtree> selectCitiesByProvinceId(HttpServletRequest request, HttpServletResponse response,
                                                      String areaId) {
        List<SimpleZtree> result = new ArrayList<>();
        try {
            List<ProvinceInfo> provinces = provinceservice.selectAllProvince();
            for (ProvinceInfo province : provinces) {
                if (province.getAreaId().equals(Long.valueOf(areaId))) {
                    SimpleZtree ztree = new SimpleZtree("p_" + province.getProvinceId(), province.getProvinceName());
                    ztree.setpId(request.getParameter("id"));
                    ztree.setIsParent(true);
                    result.add(ztree);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        return result;
    }

    /**
     *
     * @Description: 用于下拉列表
     * @return List<SelectModel>
     */
    @ResponseBody
    @RequestMapping(value = "/getprovince", method = RequestMethod.GET)
    public List<SelectModel> getSelectList() {
        List<SelectModel> sellist = new ArrayList<>();
        SelectModel sel = new SelectModel();
        sel.setId(" ");
        sel.setText("请选择");
        sellist.add(sel);
        try {
            List<ProvinceInfo> infoList = provinceservice.selectAllProvince();
            for (ProvinceInfo info : infoList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(info.getProvinceId().toString());
                selectModel.setText(info.getProvinceName());
                sellist.add(selectModel);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }

    @ResponseBody
    @RequestMapping(value = "/getprovinces", method = RequestMethod.POST)
    public List<SelectModel> getList() {
        List<SelectModel> sellist = new ArrayList<>();
        try {
            List<ProvinceInfo> infoList = provinceservice.selectAllProvince();
            for (ProvinceInfo info : infoList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(info.getProvinceId().toString());
                selectModel.setText(info.getProvinceName());
                sellist.add(selectModel);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }


    @ResponseBody
    @RequestMapping(value = "/getcity", method = RequestMethod.POST)
    public List<SelectModel> getSelCityList(HttpServletRequest request) {
        List<SelectModel> sss=   ( List<SelectModel>)RequestContextUtils.getWebApplicationContext(request).getServletContext().getAttribute("cityList");
        if (sss==null){
            List<SelectModel> sellist = new ArrayList<>();
            List<ProvinceInfo> infoList = provinceservice.selectAllProvince();
            for (ProvinceInfo info : infoList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(info.getProvinceId().toString());
                selectModel.setText(info.getProvinceName());
                List<SelectModel> childs = cityService.getCitiesByProvinceId(info.getProvinceId());
                selectModel.setChildren(childs);
                sellist.add(selectModel);
            }
            RequestContextUtils.getWebApplicationContext(request).getServletContext().setAttribute("cityList",sellist);
            return sellist;
        }else {
            return sss;
        }
    }

    /**
     *
     * @Description: 用于下拉列表,没有请选择选项
     * @return List<SelectModel>
     */
    @ResponseBody
    @RequestMapping(value = "/getProvinceNoDefault", method = RequestMethod.GET)
    public List<SelectModel> getSelectListNoDefault() {
        List<SelectModel> sellist = new ArrayList<>();
        SelectModel sel = new SelectModel();
        try {
            List<ProvinceInfo> infoList = provinceservice.selectAllProvince();
            for (ProvinceInfo info : infoList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(info.getProvinceId().toString());
                selectModel.setText(info.getProvinceName());
                sellist.add(selectModel);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }
}
