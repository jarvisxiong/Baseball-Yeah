/**
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rofour.baseball.controller.model.SelectModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SimpleZtree;
import com.rofour.baseball.controller.model.manager.CityInfo;
import com.rofour.baseball.controller.model.manager.CountyInfo;
import com.rofour.baseball.service.manager.CityService;
import com.rofour.baseball.service.manager.CountyService;

/**
 * @ClassName: CountyController
 * @Description: 县的增删改查对外接口
 * @author liujingbo
 * @date 2016年3月26日 下午8:27:54
 *
 */

@Controller

@RequestMapping(value = "/manage/county")

public class CountyController extends BaseController {

    // 日志
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("countyService")
    private CountyService countyService;
    @Autowired
    @Qualifier("cityService")
    private CityService cityService;

    /**
     *
     * @Description: 新增县
     * @param request
     * @param response
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/insertcounty", method = RequestMethod.POST)
    public ResultInfo<Object> insertCounty(HttpServletRequest request, HttpServletResponse response, CountyInfo countyInfo) {
        try {
            countyService.insert(countyInfo);
        } catch (Exception e) {
            return processException(e, logger);
        }
        return new ResultInfo<Object>(0, "", "添加成功", "");
    }

    /**
     *
     * @Description: 删除县
     * @param request
     * @param response
     * @param countyinfo
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/deletecounty", method = RequestMethod.POST)
    public ResultInfo<Object> deleteCounty(HttpServletRequest request, HttpServletResponse response, CountyInfo countyInfo) {
        try {
            if (countyInfo.getCountyId() == null) {
                return new ResultInfo<Object>(-1, "111", getMessage("111"));
            }
            countyService.deleteByPrimaryKey(Long.valueOf(countyInfo.getCountyId()));
            return new ResultInfo<Object>(0, "", "删除成功", "");
        } catch (Exception e) {
            return processException(e, logger);
        }

    }

    /**
     *
     * @Description: 根据县的id选择县
     * @param request
     * @param response
     * @param countyinfo
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/selectcounty", method = RequestMethod.POST)
    public ResultInfo<Object> selectCounty(HttpServletRequest request, HttpServletResponse response, String countyId) {
        if (countyId == null || !countyId.matches("^\\d+$")) {
            return new ResultInfo<Object>(-1, "", "请输入正确的区县id");
        }
        try {
            ResultInfo<Object> countyInfo = countyService.selectByPrimaryKey(Long.valueOf(countyId));
            CountyInfo county = (CountyInfo) countyInfo.getData();
            ResultInfo<Object> cityInfo = cityService.selectByPrimaryKey(county.getCityId());
            CityInfo city = (CityInfo) cityInfo.getData();
            Map<String, String> map = new HashMap<>();
            map.put("countyId", county.getCountyId() == null ? "" : county.getCountyId().toString());
            map.put("countyName", county.getCountyName());
            map.put("cityId", county.getCityId() == null ? "" : county.getCityId().toString());
            map.put("postCode", county.getPostCode());
            map.put("sortNo", county.getSortNo() == null ? "" : county.getSortNo().toString());
            map.put("provinceId", city.getProvinceId() == null ? "" : city.getProvinceId().toString());
            return new ResultInfo<Object>(0, "", "查询成功", map);
        } catch (Exception e) {
            return processException(e, logger);
        }

    }

    /**
     *
     * @Description: 根据县的id选择县
     * @param request
     * @param response
     * @param countyinfo
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/selectcountiesbycityid", method = RequestMethod.POST)
    public List<SimpleZtree> selectCountiesByCityId(HttpServletRequest request, HttpServletResponse response,
                                                    CountyInfo countyInfo) {
        List<SimpleZtree> result = new ArrayList<>();
        try {
            List<CountyInfo> list = countyService.selectCountiesByCityId(countyInfo.getCityId());
            for (CountyInfo country : list) {
                SimpleZtree ztree = new SimpleZtree("co_" + country.getCountyId(), country.getCountyName());
                ztree.setpId(request.getParameter("id"));
                result.add(ztree);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        return result;
    }

    /**
     *
     * @Description: 根据县的id选择县
     * @param request
     * @param response
     * @param countyinfo
     */
    @ResponseBody
    @RequestMapping(value = "/selectcitycounties", method = RequestMethod.POST)
    public void selectCityCounties(HttpServletRequest request, HttpServletResponse response, CountyInfo countyInfo) {
        List<CountyInfo> list = null;
        try {
            list = countyService.selectCountiesByCityId(countyInfo.getCityId());
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        writeJson(list, response);
    }

    /**
     *
     * @Description: 查询所有县区
     * @param request
     * @param countyinfo
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/selectall", method = RequestMethod.POST)
    public ResultInfo selectAll(HttpServletRequest request, CountyInfo countyInfo) {
        try {
            List<CountyInfo> countyInfos = countyService.selectAll();
            return new ResultInfo(0, "", "查询成功", countyInfos);
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     *
     * @Description: 更新县
     * @param request
     * @param response
     * @param countyinfo
     * @return ResultInfo 操作结果bean
     */
    @ResponseBody
    @RequestMapping(value = "/updatecounty", method = RequestMethod.POST)
    public ResultInfo<Object> updateCounty(HttpServletRequest request, HttpServletResponse response, CountyInfo countyInfo) {
        try {
            if (countyInfo.getCountyId() == null) {
                return new ResultInfo<Object>(-1, "111", getMessage("111"));
            }
            countyService.updateByPrimaryKey(countyInfo);
            return new ResultInfo<Object>(0, "", "修改成功");
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     *
     * @Description: 用于下拉列表
     * @return List<SelectModel>
     */
    @ResponseBody
    @RequestMapping(value = "/getcounty", method = RequestMethod.GET)
    public List<SelectModel> getSelectList(String cityId) {
        List<SelectModel> sellist = new ArrayList<>();
        SelectModel sel = new SelectModel();
        sel.setId(" ");
        sel.setText("请选择");
        sellist.add(sel);
        if (cityId == null || cityId.trim().equals("")) {
            return sellist;
        }
        try {
            List<CountyInfo> infoList = countyService.selectCountiesByCityId(Long.valueOf(cityId));
            for (CountyInfo info : infoList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(info.getCountyId().toString());
                selectModel.setText(info.getCountyName());
                sellist.add(selectModel);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }

}
