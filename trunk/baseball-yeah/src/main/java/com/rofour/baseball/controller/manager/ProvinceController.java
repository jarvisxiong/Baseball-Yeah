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

import com.rofour.baseball.controller.model.Tree;
import com.rofour.baseball.controller.model.manager.CityCollegeInfo;
import com.rofour.baseball.controller.model.manager.CityInfo;
import com.rofour.baseball.dao.manager.bean.CityBean;
import com.rofour.baseball.dao.manager.bean.ProvinceBean;
import com.rofour.baseball.service.manager.CollegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.SimpleZtree;
import com.rofour.baseball.controller.model.manager.ProvinceInfo;
import com.rofour.baseball.service.manager.CityService;
import com.rofour.baseball.service.manager.ProvinceService;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author liujingbo
 * @ClassName: ProvinceController
 * @Description: 省的增删改查对外接口
 * @date 2016年3月26日 下午8:41:10
 */

@Controller

@RequestMapping(value = "/manage/province")

public class ProvinceController extends BaseController
{
    
    // 日志
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    @Qualifier("provinceService")
    private ProvinceService provinceservice;
    
    @Autowired
    @Qualifier("cityService")
    private CityService cityService;
    
    @Autowired
    @Qualifier("collegeService")
    private CollegeService collegeService;
    
    /**
     * @param request
     * @param response
     * @return ResultInfo 操作结果bean
     * @Description: 增加省份
     */
    @ResponseBody
    @RequestMapping(value = "/insertprovince", method = RequestMethod.POST)
    public ResultInfo<Object> insert(HttpServletRequest request, HttpServletResponse response,
        ProvinceInfo provinceinfo)
    {
        try
        {
            provinceservice.insert(provinceinfo);
        }
        catch (Exception e)
        {
            return processException(e, logger);
        }
        return new ResultInfo<Object>(0, "", "添加成功", "");
    }
    
    /**
     * @param request
     * @param response
     * @param provinceinfo
     * @return ResultInfo 操作结果bean
     * @Description: 删除一个省
     */
    @ResponseBody
    @RequestMapping(value = "/deleteprovince", method = RequestMethod.POST)
    public ResultInfo<Object> deleteMsm(HttpServletRequest request, HttpServletResponse response,
        ProvinceInfo provinceinfo)
    {
        try
        {
            provinceservice.deleteProvinceCascade(provinceinfo.getProvinceId());
        }
        catch (Exception e)
        {
            return processException(e, logger);
        }
        return new ResultInfo<Object>(0, "", "删除成功", "");
    }
    
    /**
     * @param request
     * @param response
     * @param provinceinfo
     * @return ResultInfo 操作结果bean
     * @Description: 根据id选择一个省
     */
    @ResponseBody
    @RequestMapping(value = "/selectprovince", method = RequestMethod.POST)
    public ResultInfo<Object> selectProvince(HttpServletRequest request, HttpServletResponse response)
    {
        String id = request.getParameter("provinceId");
        if (id == null || !id.matches("^\\d+$"))
        {
            return new ResultInfo<Object>(-1, "", "省份id不能为空");
        }
        try
        {
            return provinceservice.selectByPrimaryKey(Long.valueOf(id));
        }
        catch (Exception e)
        {
            return processException(e, logger);
        }
    }
    
    /**
     * @param request
     * @param response
     * @param provinceinfo
     * @return ResultInfo 操作结果bean
     * @Description: 更新一个省
     */
    @ResponseBody
    @RequestMapping(value = "/updateprovince", method = RequestMethod.POST)
    public ResultInfo<Object> updateProvince(HttpServletRequest request, HttpServletResponse response,
        ProvinceInfo provinceinfo)
    {
        try
        {
            provinceservice.updateByPrimaryKey(provinceinfo);
        }
        catch (Exception e)
        {
            return processException(e, logger);
        }
        return new ResultInfo<Object>(0, "", "修改成功");
    }
    
    /**
     * @param request
     * @param response
     * @return ResultInfo 操作结果bean
     * @Description: 查询全部省份信息
     */
    @ResponseBody
    @RequestMapping(value = "/selectallprovince", method = RequestMethod.GET)
    public List<SimpleZtree> selectAllProvince(HttpServletRequest request, HttpServletResponse response)
    {
        List<SimpleZtree> list2 = new ArrayList<>();
        try
        {
            List<ProvinceInfo> provinces = provinceservice.selectAllProvince();
            for (ProvinceInfo item : provinces)
            {
                // 省份id由p_开头
                SimpleZtree ztree = new SimpleZtree("p_" + item.getProvinceId(), item.getProvinceName());
                ztree.setIsParent(true);
                list2.add(ztree);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), logger);
        }
        return list2;
    }
    
    /**
     * @param request
     * @param response
     * @return ResultInfo 操作结果bean
     * @Description: 查询全部省份信息
     */
    @ResponseBody
    @RequestMapping(value = "/selectprovincefortree", method = RequestMethod.POST)
    public List<Tree> selectProvinceForTree(HttpServletRequest request, HttpServletResponse response)
    {
        List<Tree> list = new ArrayList<>();
        try
        {
            List<ProvinceInfo> provinces = provinceservice.selectAllProvince();
            List<CityInfo> citys = cityService.selectAll();
            
            // 生成省市树
            for (ProvinceInfo item : provinces)
            {
                Tree tree = new Tree();
                tree.setId(item.getProvinceId());
                tree.setText(item.getProvinceName());
                tree.setChildren(new ArrayList<Tree>());
                for (CityInfo city : citys)
                {
                    if (city.getProvinceId().longValue() == item.getProvinceId().longValue())
                    {
                        Tree childrenTree = new Tree();
                        childrenTree.setId(city.getCityId());
                        childrenTree.setText(city.getCityName());
                        childrenTree.setState("closed");
                        childrenTree.setChildren(new ArrayList<Tree>());
                        tree.getChildren().add(childrenTree);
                    }
                }
                list.add(tree);
            }
            
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), logger);
        }
        return list;
    }
    
    // 获取学校树
    @ResponseBody
    @RequestMapping(value = "/selectcollegefortree", method = RequestMethod.POST)
    public List<Tree> selectCollegeForTree(HttpServletRequest request, HttpServletResponse response)
    {
        List<Tree> list = new ArrayList<>();
        try
        {
            Long id = Long.parseLong(request.getParameter("parentId"));
            List<CityCollegeInfo> colleges = collegeService.selectByCityId(id);
            for (CityCollegeInfo item : colleges)
            {
                Tree tree = new Tree();
                tree.setId(Long.parseLong(item.getCollegeId()));
                tree.setText(item.getFullName());
                Map<String, String> attributes = new HashMap<String, String>();
                attributes.put("Type", "1");
                tree.setAttributes(attributes);
                list.add(tree);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), logger);
        }
        return list;
    }
    
    /**
     * @param request
     * @param response
     * @param cityinfo
     * @return ResultInfo 操作结果bean
     * @Description: 根据区域id查询所有此区域的省份
     */
    @ResponseBody
    @RequestMapping(value = "/selectprovincesbyareaid", method = RequestMethod.POST)
    public List<SimpleZtree> selectCitiesByProvinceId(HttpServletRequest request, HttpServletResponse response,
        String areaId)
    {
        List<SimpleZtree> result = new ArrayList<>();
        try
        {
            List<ProvinceInfo> provinces = provinceservice.selectAllProvince();
            for (ProvinceInfo province : provinces)
            {
                if (province.getAreaId().equals(Long.valueOf(areaId)))
                {
                    SimpleZtree ztree = new SimpleZtree("p_" + province.getProvinceId(), province.getProvinceName());
                    ztree.setpId(request.getParameter("id"));
                    ztree.setIsParent(true);
                    result.add(ztree);
                }
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), logger);
        }
        return result;
    }
    
    /**
     * @return List<SelectModel>
     * @Description: 用于下拉列表
     */
    @ResponseBody
    @RequestMapping(value = "/getprovincesByAreaId", method = RequestMethod.POST)
    public List<SelectModel> getSelectList(HttpServletRequest request, HttpServletResponse response, String areaId)
    {
        List<SelectModel> sellist = new ArrayList<>();
        SelectModel sel = new SelectModel();
        sel.setId(" ");
        sel.setText("请选择");
        sellist.add(sel);
        try
        {
            List<ProvinceInfo> infoList = provinceservice.selectAllProvince();
            for (ProvinceInfo info : infoList)
            {
                SelectModel selectModel = new SelectModel();
                if (info.getAreaId().equals(Long.valueOf(areaId.substring(1, 2))))
                {
                    selectModel.setId(info.getProvinceId().toString());
                    selectModel.setText(info.getProvinceName());
                    sellist.add(selectModel);
                }
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }
    
    /**
     * @return List<SelectModel>
     * @Description: 用于下拉列表
     */
    @ResponseBody
    @RequestMapping(value = "/getprovince", method = RequestMethod.GET)
    public List<SelectModel> getSelectList()
    {
        List<SelectModel> sellist = new ArrayList<>();
        SelectModel sel = new SelectModel();
        sel.setId(" ");
        sel.setText("请选择");
        sellist.add(sel);
        try
        {
            List<ProvinceInfo> infoList = provinceservice.selectAllProvince();
            for (ProvinceInfo info : infoList)
            {
                SelectModel selectModel = new SelectModel();
                
                selectModel.setId(info.getProvinceId().toString());
                selectModel.setText(info.getProvinceName());
                sellist.add(selectModel);
                
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }
    
    /**
     * @return List<SelectModel>
     * @Description: 用于下拉列表
     */
    @ResponseBody
    @RequestMapping(value = "/getcitysByProvinceId", method = RequestMethod.POST)
    public List<SelectModel> getSelectCityList(HttpServletRequest request, HttpServletResponse response,
        String provinceId)
    {
        List<SelectModel> sellist = new ArrayList<>();
        SelectModel sel = new SelectModel();
        sel.setId(" ");
        sel.setText("请选择");
        sellist.add(sel);
        try
        {
            List<CityInfo> infoList = cityService.selectAll();
            for (CityInfo info : infoList)
            {
                SelectModel selectModel = new SelectModel();
                String id =provinceId.substring(2, 4);
                if (id.equals(info.getProvinceId().toString()))
                {
                    selectModel.setId(info.getCityId().toString());
                    selectModel.setText(info.getCityName());
                    sellist.add(selectModel);
                }
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }
    
    /**
     * @return List<SelectModel>
     * @Description: 查询全国的明日运力
     */
    @ResponseBody
    @RequestMapping(value = "/selectallsignin", method = RequestMethod.GET)
    public SimpleZtree selectAllsignin(HttpServletRequest request, HttpServletResponse response)
    {
        SimpleZtree ztree = new SimpleZtree();
        try
        {
            Long nums = provinceservice.selectAllWorkSignin();
            ztree.setId("root");
            ztree.setName("明日全国运力(" + nums + ")");
            ztree.setpId("");
            ztree.setIsParent(true);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), logger);
        }
        return ztree;
    }
    
    /**
     * @return List<SelectModel>
     * @Description: 查询省和明日运力
     */
    @ResponseBody
    @RequestMapping(value = "/selectprovincesandsignin", method = RequestMethod.GET)
    public List<SimpleZtree> selectProvincesAndSignin(HttpServletRequest request, HttpServletResponse response)
    {
        List<SimpleZtree> result = new ArrayList<>();
        try
        {
            List<ProvinceBean> provinces = provinceservice.selectAllProvinceAndWorkSignin();
            for (ProvinceBean province : provinces)
            {
                SimpleZtree ztree = new SimpleZtree("p_" + province.getProvinceId(),
                    province.getProvinceName() + "(" + province.getWorkSignin() + ")");
                ztree.setpId(request.getParameter("root"));
                ztree.setIsParent(true);
                result.add(ztree);
            }
            
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), logger);
        }
        return result;
    }
    
    @ResponseBody
    @RequestMapping(value = "/selectcitysesandsignin/provinceid/{provinceId}", method = RequestMethod.GET)
    public List<SimpleZtree> selectCitiesAndSignin(HttpServletRequest request, HttpServletResponse respons,
        @PathVariable("provinceId") Long provinceId)
    {
        List<SimpleZtree> result = new ArrayList<>();
        try
        {
            List<CityBean> cityBeanList = provinceservice.selectCityAndWorkSignin(provinceId);
            for (CityBean cityBean : cityBeanList)
            {
                SimpleZtree ztree = new SimpleZtree("c_" + cityBean.getCityId(),
                    cityBean.getCityName() + "(" + cityBean.getWorkSignin() + ")");
                ztree.setpId(provinceId + "");
                ztree.setIsParent(true);
                result.add(ztree);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), logger);
        }
        return result;
    }

    /**
     * 查询所有城市的运力
     * @param request
     * @param respons
     * @param provinceId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectallcitiesandsignin", method = RequestMethod.GET)
    public List<SimpleZtree> selectAllCitiesAndSignin(HttpServletRequest request, HttpServletResponse respons)
    {
        List<SimpleZtree> result = new ArrayList<>();
        try
        {
            List<CityBean> cityBeanList = provinceservice.selectAllCitiesAndSignin();
            for (CityBean cityBean : cityBeanList)
            {
                SimpleZtree ztree = new SimpleZtree("c_" + cityBean.getCityId(),
                        cityBean.getCityName() + "(" + cityBean.getWorkSignin() + ")");
                ztree.setIsParent(true);
                result.add(ztree);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), logger);
        }
        return result;
    }
    
    @ResponseBody
    @RequestMapping(value = "/getprovinces", method = RequestMethod.POST)
    public List<SelectModel> getList()
    {
        List<SelectModel> sellist = new ArrayList<>();
        try
        {
            List<ProvinceInfo> infoList = provinceservice.selectAllProvince();
            for (ProvinceInfo info : infoList)
            {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(info.getProvinceId().toString());
                selectModel.setText(info.getProvinceName());
                sellist.add(selectModel);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }
    
    @ResponseBody
    @RequestMapping(value = "/getcity", method = RequestMethod.POST)
    public List<SelectModel> getSelCityList(HttpServletRequest request)
    {
        List<SelectModel> sss = (List<SelectModel>)RequestContextUtils.getWebApplicationContext(request)
            .getServletContext()
            .getAttribute("cityList");
        if (sss == null)
        {
            List<SelectModel> sellist = new ArrayList<>();
            List<ProvinceInfo> infoList = provinceservice.selectAllProvince();
            for (ProvinceInfo info : infoList)
            {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(info.getProvinceId().toString());
                selectModel.setText(info.getProvinceName());
                List<SelectModel> childs = cityService.getCitiesByProvinceId(info.getProvinceId());
                selectModel.setChildren(childs);
                sellist.add(selectModel);
            }
            RequestContextUtils.getWebApplicationContext(request).getServletContext().setAttribute("cityList", sellist);
            return sellist;
        }
        else
        {
            return sss;
        }
    }
    
    /**
     * @return List<SelectModel>
     * @Description: 用于下拉列表, 没有请选择选项
     */
    @ResponseBody
    @RequestMapping(value = "/getProvinceNoDefault", method = RequestMethod.GET)
    public List<SelectModel> getSelectListNoDefault()
    {
        List<SelectModel> sellist = new ArrayList<>();
        SelectModel sel = new SelectModel();
        try
        {
            List<ProvinceInfo> infoList = provinceservice.selectAllProvince();
            for (ProvinceInfo info : infoList)
            {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(info.getProvinceId().toString());
                selectModel.setText(info.getProvinceName());
                sellist.add(selectModel);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return sellist;
    }
}
