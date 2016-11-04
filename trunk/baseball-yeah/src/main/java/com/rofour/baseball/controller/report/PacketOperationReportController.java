/*
 * 文 件 名:  PacketOperationReport.java
 * 版    权:  Shanghai zhiduan Mdt InfoTech Ltd
 * 描    述:  <描述>
 * 修 改 人:  tongfei
 * 修改时间:  2016年10月12日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.rofour.baseball.controller.report;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rofour.baseball.common.DateUtil;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.dao.manager.bean.AreaBean;
import com.rofour.baseball.dao.manager.bean.CityBean;
import com.rofour.baseball.dao.manager.bean.CityCollegeBean;
import com.rofour.baseball.dao.manager.bean.CollegeBean;
import com.rofour.baseball.dao.manager.bean.ProvinceBean;
import com.rofour.baseball.dao.report.bean.ReportCityCountInfo;
import com.rofour.baseball.dao.report.bean.ReportCollegeCountInfo;
import com.rofour.baseball.dao.report.bean.ReportDeliveryCountInfo;
import com.rofour.baseball.dao.report.bean.ReportOrderStatisticsInfo;
import com.rofour.baseball.dao.report.bean.ReportProvinceCountInfo;
import com.rofour.baseball.dao.report.bean.ReportPuserBean;
import com.rofour.baseball.dao.report.bean.ReportRegionPuserSumBean;
import com.rofour.baseball.dao.report.bean.ReportTransportInfo;
import com.rofour.baseball.dao.report.bean.SearchCriteria;
import com.rofour.baseball.service.manager.Area;
import com.rofour.baseball.service.manager.CityService;
import com.rofour.baseball.service.manager.CollegeService;
import com.rofour.baseball.service.manager.ProvinceService;
import com.rofour.baseball.service.report.ReportOrderStatisticsService;
import com.rofour.baseball.service.report.ReportPuserService;
import com.rofour.baseball.service.report.ReportRegionPuserSumService;
import com.rofour.baseball.service.report.ReportRegionService;

/**
 * <小派数量，运力新增的页面接入数据处理> <功能详细描述>
 * 
 * @author tongfei
 * @version [版本号, 2016年10月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

@Controller
@RequestMapping("/report/delivery")
public class PacketOperationReportController extends BaseController
{
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private DecimalFormat df = new DecimalFormat("######0.00");
    
    private DecimalFormat df1 = new DecimalFormat("######0.0");
    
    private DecimalFormat df2 = new DecimalFormat("######0.000");
    
    @Autowired
    @Qualifier(value = "reportPuserService")
    private ReportPuserService reportPuserService;
    
    @Autowired
    @Qualifier(value = "reportRegionService")
    private ReportRegionService reportRegionService;
    
    @Autowired
    @Qualifier(value = "reportRegionPuserSumService")
    private ReportRegionPuserSumService reportRegionPuserSumService;
    
    @Autowired
    @Qualifier(value = "reportOrderStatisticsService")
    private ReportOrderStatisticsService reportOrderStatisticsService;
    
    @Autowired
    @Qualifier(value = "area")
    private Area area;
    
    @Autowired
    @Qualifier(value = "provinceService")
    private ProvinceService provinceService;
    
    @Autowired
    @Qualifier(value = "cityService")
    private CityService cityService;
    
    @Autowired
    @Qualifier(value = "collegeService")
    private CollegeService collegeService;
    
    // 跳转小派数量页面
    @RequestMapping(value = "/deliveryManReport", method = RequestMethod.GET)
    public ModelAndView gotoOrderCount(HttpServletRequest request)
        throws Exception
    {
        String flag = request.getParameter("flag");
        ModelAndView mav;
        if (request.getSession().getAttribute("user") != null)
        {
            mav = new ModelAndView("statistics/deliveryMan/deliveryManReport");
            mav.addObject("flag", flag);
        }
        else
        {
            mav = new ModelAndView("/error/noPermission");
        }
        return mav;
    }
    
    // 跳转运力新增页面
    @RequestMapping(value = "/transportReport", method = RequestMethod.GET)
    public ModelAndView gotoTransportCount(HttpServletRequest request)
        throws Exception
    {
        String flag = request.getParameter("flag");
        ModelAndView mav;
        if (request.getSession().getAttribute("user") != null)
        {
            mav = new ModelAndView("statistics/deliveryMan/transportReport");
            mav.addObject("flag", flag);
        }
        else
        {
            mav = new ModelAndView("/error/noPermission");
        }
        return mav;
    }
    
    // 跳转到省份数量页面
    @RequestMapping(value = "/gotoProvinceReport", method = RequestMethod.GET)
    public ModelAndView gotoProvinceReport(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("statistics/deliveryMan/provinceReport");
        if (request.getSession().getAttribute("user") != null)
        {
            model.addObject("id", request.getParameter("id"));
            model.addObject("time", request.getParameter("time"));
            model.addObject("provinceflag", request.getParameter("provinceflag"));
            return model;
        }
        else
        {
            return new ModelAndView("/");
        }
    }
    
    // 跳转到城市数量页面
    @RequestMapping(value = "/gotoCityReport", method = RequestMethod.GET)
    public ModelAndView gotoCityReport(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("statistics/deliveryMan/cityReport");
        if (request.getSession().getAttribute("user") != null)
        {
            model.addObject("id", request.getParameter("id"));
            model.addObject("time", request.getParameter("time"));
            model.addObject("cityflag", request.getParameter("cityflag"));
            return model;
        }
        else
        {
            return new ModelAndView("/");
        }
    }
    
    // 跳转到校区数量页面
    @RequestMapping(value = "/gotoCollegeReport", method = RequestMethod.GET)
    public ModelAndView gotoCollegeReport(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("statistics/deliveryMan/collegeReport");
        if (request.getSession().getAttribute("user") != null)
        {
            model.addObject("id", request.getParameter("id"));
            model.addObject("time", request.getParameter("time"));
            model.addObject("collegeflag", request.getParameter("collegeflag"));
            return model;
        }
        else
        {
            return new ModelAndView("/");
        }
    }
    
    /**
     * @Description: 小派数量页面 @param request @return ResultInfo @throws
     */
    @ResponseBody
    @RequestMapping(value = "/getdelivery", method = RequestMethod.POST)
    public List<ReportDeliveryCountInfo> SelectdeliveryCount(HttpServletRequest request, HttpServletResponse response)
    {
        List<ReportDeliveryCountInfo> dataList = new ArrayList<ReportDeliveryCountInfo>();
        try
        {
            // 页面传输的入参
            String selectMonth = request.getParameter("selectMonth");
            String selectStartDate = request.getParameter("selectStartDate");
            String selectEndDate = request.getParameter("selectEndDate");
            String region = request.getParameter("region");
            String province = request.getParameter("province");
            String city = request.getParameter("city");
            String college = request.getParameter("college");
            // 隐参，用来判别是否为第一次进入该接入
            String flag = request.getParameter("flag");
            if (!flag.equals("true"))// 不是第一次进入
            {
                int regionFlag = 0;
                // 获取起止时间
                
                // 按照区域由小到大的优先级，获取区域小派数量的列表
                if (!(college.equals("") || college.equals(" ")))
                {
                    SearchCriteria searchCriteria = getSearchCriteria(selectMonth, selectStartDate, selectEndDate);
                    CollegeBean collegeBean = collegeService.selectByCollegeId(Long.parseLong(college));
                    searchCriteria.setRegion(collegeBean.getFullName());
                    dataList = getInfoList(searchCriteria, true, 1);
                    return doResult(selectMonth, dataList);
                    
                }
                else if (!(city.equals("") || city.equals(" ")))
                {
                    SearchCriteria searchCriteria = getSearchCriteria(selectMonth, selectStartDate, selectEndDate);
                    CityBean cityBean = cityService.selectById(Long.parseLong(city));
                    searchCriteria.setRegion(cityBean.getCityName());
                    dataList = getInfoList(searchCriteria, false, 2);
                    return doResult(selectMonth, dataList);
                }
                else if (!(province.equals("") || province.equals(" ")))
                {
                    SearchCriteria searchCriteria = getSearchCriteria(selectMonth, selectStartDate, selectEndDate);
                    ProvinceBean provinceBean = provinceService.selectById(Long.parseLong(province));
                    searchCriteria.setRegion(provinceBean.getProvinceName());
                    dataList = getInfoList(searchCriteria, false, 3);
                    return doResult(selectMonth, dataList);
                }
                else if (!(region.equals("") || region.equals(" ")))
                {
                    SearchCriteria searchCriteria = getSearchCriteria(selectMonth, selectStartDate, selectEndDate);
                    AreaBean areaBean = area.getAreaById(Long.parseLong(region));
                    searchCriteria.setRegion(areaBean.getAreaName());
                    dataList = getInfoList(searchCriteria, false, 4);
                    return doResult(selectMonth, dataList);
                }
                else
                {
                    dataList = getAllAreaPage(false, selectMonth, selectStartDate, selectEndDate);
                    return dataList;
                }
            }
            else // 第一次进入页面
            {
                dataList = getAllAreaPage(true, selectMonth, selectStartDate, selectEndDate);
                return dataList;
            }
            
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return dataList;
        }
    }
    
    // 显示所有大区的最新数据表
    private List<ReportDeliveryCountInfo> getAllAreaPage(boolean first, String selectMonth, String selectStartDate,
        String selectEndDate)
        throws Exception
    {
        List<ReportDeliveryCountInfo> dataList = new ArrayList<ReportDeliveryCountInfo>();
        if (selectMonth.equals("请选择") && selectStartDate.equals("") && selectEndDate.equals(""))
        {
            first = true;
        }
        SearchCriteria searchCriteria = new SearchCriteria();
        if (first)
        {
            searchCriteria = searchCurrMonth();
        }
        else
        {
            searchCriteria = getSearchCriteria(selectMonth, selectStartDate, selectEndDate);
        }
        // 获取所有大区
        List<AreaBean> areaBeans = area.getAllAreas();
        // 遍历各个大区，获取每个大区的小派数量
        for (AreaBean bean : areaBeans)
        {
            List<ReportDeliveryCountInfo> oneDate = new ArrayList<ReportDeliveryCountInfo>();
            searchCriteria.setRegion(bean.getAreaName());
            oneDate = getInfoList(searchCriteria, false, 4);
            // 大区没有取得对应的小派记录
            if (oneDate.isEmpty() || oneDate == null)
            {
                continue;
            }
            else // 获取到对应的小派记录，并添加到返回列表中
            {
                if (first)
                {
                    ReportDeliveryCountInfo data = oneDate.get(0);
                    data.setRegionId(Integer.parseInt(1 + bean.getAreaId().toString()));
                    data.setTime(String.valueOf(new Date().getMonth() + 1) + "月");
                    dataList.add(data);
                }
                else
                {
                    
                    dataList.addAll(doResult(selectMonth, oneDate));
                }
            }
        }
        
        // 按照小派数量倒序
        if (first)
        {
            if (!dataList.isEmpty())
            {
                Collections.sort(dataList, new Comparator()
                {
                    public int compare(Object a, Object b)
                    {
                        int one = ((ReportDeliveryCountInfo)a).getPuserNum();
                        int two = ((ReportDeliveryCountInfo)b).getPuserNum();
                        return two - one;
                    }
                });
            }
        }
        return dataList;
        
    }
    
    // 返回类型判断
    private List<ReportDeliveryCountInfo> doResult(String selectMonth, List<ReportDeliveryCountInfo> dataList)
    {
        if (dataList == null || dataList.isEmpty())
        {
            return dataList;
        }
        else // 获取到对应的小派记录，并添加到返回列表中
        {
            if (!selectMonth.equals("请选择"))
            {
                ReportDeliveryCountInfo oneData = dataList.get(0);
                oneData.setTime(selectMonth);
                dataList.clear();
                dataList.add(oneData);
                return dataList;
            }
            else
            {
                return dataList;
            }
        }
    }
    
    /**
     * @Description: 省份数量页面 @param request @return ResultInfo @throws
     */
    @ResponseBody
    @RequestMapping(value = "/getprovince", method = RequestMethod.POST)
    public List<ReportProvinceCountInfo> SelectProvinceCount(HttpServletRequest request, HttpServletResponse response)
    {
        List<ReportProvinceCountInfo> dataList = new ArrayList<ReportProvinceCountInfo>();
        try
        {
            // 页面传输的入参
            
            String province = request.getParameter("province");
            // 隐参，用来判别是否为第一次进入该接入
            String time = request.getParameter("time");
            String provinceflag = request.getParameter("provinceflag");
            String area = request.getParameter("firstArea");
            
            SearchCriteria searchCriteria = getSearchCriteria(time);
            // 获取该大区下的所有省份
            int id = Integer.parseInt(area.substring(1, 2));
            String id_before = area.substring(0, 1);
            if (!provinceflag.equals("true"))// 不是第一次进入
            {
                // 获取区域小派数量的列表
                if (!(province.equals("") || province.equals(" ")))
                {
                    ProvinceBean provinceBean = provinceService.selectById(Long.parseLong(province));
                    searchCriteria.setRegion(provinceBean.getProvinceName());
                    List<ReportProvinceCountInfo> oneDate = new ArrayList<ReportProvinceCountInfo>();
                    List<ReportDeliveryCountInfo> list = getInfoList(searchCriteria, false, 3);
                    if (!(list.isEmpty() || list == null))
                    {
                        oneDate = (List<ReportProvinceCountInfo>)changeFromReportDeliveryCountInfo(list, 0);
                        ReportProvinceCountInfo data = oneDate.get(0);
                        data.setProvinceId(Integer.parseInt(area + province));
                        data.setDay(time);
                        dataList.add(data);
                    }
                    
                }
                else
                {
                    dataList = getAllProvincePage(searchCriteria, area, time);
                }
            }
            else // 第一次进入
            {
                
                dataList = getAllProvincePage(searchCriteria, area, time);
                
            }
            // 按照小派数量倒序
            if (!dataList.isEmpty())
            {
                Collections.sort(dataList, new Comparator()
                {
                    public int compare(Object a, Object b)
                    {
                        int one = ((ReportProvinceCountInfo)a).getPuserNum();
                        int two = ((ReportProvinceCountInfo)b).getPuserNum();
                        return two - one;
                    }
                });
            }
            return dataList;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return dataList;
        }
        
    }
    
    // 显示所有省份的最新数据表
    private List<ReportProvinceCountInfo> getAllProvincePage(SearchCriteria searchCriteria, String area, String time)
    {
        List<ReportProvinceCountInfo> dataList = new ArrayList<ReportProvinceCountInfo>();
        
        // 获取该大区下的所有省份
        int id = Integer.parseInt(area.substring(1, 2));
        List<ProvinceBean> prvoinceBeans = provinceService.selectProvincesFromAreaId(id);
        // 遍历所有省份的小派数量
        for (ProvinceBean bean : prvoinceBeans)
        {
            List<ReportProvinceCountInfo> oneDate = new ArrayList<ReportProvinceCountInfo>();
            searchCriteria.setRegion(bean.getProvinceName());
            List<ReportDeliveryCountInfo> list = getInfoList(searchCriteria, false, 3);
            if (!(list.isEmpty() || list == null))
            {
                oneDate = (List<ReportProvinceCountInfo>)changeFromReportDeliveryCountInfo(list, 0);
            }
            // 省份没有取得对应的小派记录
            if (oneDate == null || oneDate.isEmpty())
            {
                continue;
            }
            else // 省份取得对应的小派记录
            {
                ReportProvinceCountInfo data = oneDate.get(0);
                data.setProvinceId(Integer.parseInt(area + bean.getProvinceId()));
                data.setDay(time);
                dataList.add(data);
            }
        }
        return dataList;
    }
    
    /**
     * @Description: 城市数量页面@param request @return ResultInfo @throws
     */
    @ResponseBody
    @RequestMapping(value = "/getcity", method = RequestMethod.POST)
    public List<ReportCityCountInfo> SelectCityCount(HttpServletRequest request, HttpServletResponse response)
    {
        List<ReportCityCountInfo> dataList = new ArrayList<ReportCityCountInfo>();
        try
        {
            // 页面传输的入参
            
            String city = request.getParameter("city");
            // 隐参，用来判别是否为第一次进入该接入
            String time = request.getParameter("time");
            String cityflag = request.getParameter("cityflag");
            String provinceId = request.getParameter("firstProvince");
            
            SearchCriteria searchCriteria = getSearchCriteria(time);
            Long id = Long.parseLong(provinceId.substring(2, 4));
            String id_before = provinceId.substring(0, 2);
            if (!cityflag.equals("true"))// 不是第一次进入
            // 获取起止时间
            {
                // 获取区域小派数量的列表
                if (!(city.equals("") || city.equals(" ")))
                {
                    CityBean cityBean = cityService.selectById(Long.parseLong(city));
                    searchCriteria.setRegion(cityBean.getCityName());
                    List<ReportDeliveryCountInfo> list = getInfoList(searchCriteria, false, 2);
                    List<ReportCityCountInfo> oneDate = new ArrayList<ReportCityCountInfo>();
                    // 转化为返回列表
                    if (!(list.isEmpty() || list == null))
                    {
                        oneDate = (List<ReportCityCountInfo>)changeFromReportDeliveryCountInfo(list, 1);
                        ReportCityCountInfo data = oneDate.get(0);
                        data.setCityId(Integer.parseInt(id_before + cityBean.getCityId().toString()));
                        data.setDay(time);
                        dataList.add(data);
                    }
                    
                }
                else
                {
                    dataList = getAllCityPage(searchCriteria, provinceId, time);
                }
            }
            else
            {
                
                dataList = getAllCityPage(searchCriteria, provinceId, time);
                
            }
            
            // 按照小派数量倒序
            if (!dataList.isEmpty())
            {
                Collections.sort(dataList, new Comparator()
                {
                    public int compare(Object a, Object b)
                    {
                        int one = ((ReportCityCountInfo)a).getPuserNum();
                        int two = ((ReportCityCountInfo)b).getPuserNum();
                        return two - one;
                    }
                });
            }
            return dataList;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return dataList;
        }
        
    }
    
    // 显示所有城市的最新数据表
    private List<ReportCityCountInfo> getAllCityPage(SearchCriteria searchCriteria, String provinceId, String time)
    {
        List<ReportCityCountInfo> dataList = new ArrayList<ReportCityCountInfo>();
        Long id = Long.parseLong(provinceId.substring(2, 4));
        String id_before = provinceId.substring(0, 2);
        // 获取该省份下的所有城市列表
        List<CityBean> cityBeans = provinceService.selectCitiesByProvinceId(id);
        // 遍历城市，获取每个城市的小派数量
        for (CityBean bean : cityBeans)
        {
            List<ReportCityCountInfo> oneDate = new ArrayList<ReportCityCountInfo>();
            searchCriteria.setRegion(bean.getCityName());
            List<ReportDeliveryCountInfo> list = getInfoList(searchCriteria, false, 3);
            if (!(list.isEmpty() || list == null))
            {
                oneDate = (List<ReportCityCountInfo>)changeFromReportDeliveryCountInfo(list, 1);
            }
            // 没有获取到该城市的小派记录
            if (oneDate == null || oneDate.isEmpty())
            {
                continue;
            }
            else // 获取到该城市的小派记录
            {
                ReportCityCountInfo data = oneDate.get(0);
                data.setCityId(Integer.parseInt(id_before + bean.getCityId()));
                data.setDay(time);
                dataList.add(data);
            }
        }
        
        return dataList;
    }
    
    /**
     * @Description: 获取小派数量 @param request @return ResultInfo @throws
     */
    @ResponseBody
    @RequestMapping(value = "/getcollege", method = RequestMethod.POST)
    public List<ReportCollegeCountInfo> SelectCollegeCount(HttpServletRequest request, HttpServletResponse response)
    {
        List<ReportCollegeCountInfo> dataList = new ArrayList<ReportCollegeCountInfo>();
        try
        {
            // 页面传输的入参
            
            String college = request.getParameter("college");
            String collegeflag = request.getParameter("collegeflag");
            // 隐参，用来判别是否为第一次进入该接入
            String time = request.getParameter("time");
            String cityId = request.getParameter("firstCity");
            // 获取起止时间
            SearchCriteria searchCriteria = getSearchCriteria(time);
            
            if (!collegeflag.equals("true"))// 不是第一次进入
            {
                // 获取区域小派数量的列表
                if (!(college.equals("") || college.equals(" ")))
                {
                    CollegeBean collegeBean = collegeService.selectByCollegeId(Long.parseLong(college));
                    searchCriteria.setRegion(collegeBean.getFullName());
                    List<ReportDeliveryCountInfo> list = getInfoList(searchCriteria, true, 1);
                    List<ReportCollegeCountInfo> oneDate = new ArrayList<ReportCollegeCountInfo>();
                    if (!(list.isEmpty() || list == null))
                    {
                        oneDate = (List<ReportCollegeCountInfo>)changeFromReportDeliveryCountInfo(list, 2);
                        ReportCollegeCountInfo data = oneDate.get(0);
                        data.setCollegeId(data.getCollegeId());
                        data.setDay(time);
                        dataList.add(data);
                    }
                    
                }
                else
                {
                    dataList = getAllCollegePage(searchCriteria, cityId, time);
                }
            }
            else// 第一次进入
            {
                
                dataList = getAllCollegePage(searchCriteria, cityId, time);
                
            }
            
            // 按照小派数量倒序
            if (!dataList.isEmpty())
            {
                Collections.sort(dataList, new Comparator()
                {
                    public int compare(Object a, Object b)
                    {
                        int one = ((ReportCollegeCountInfo)a).getPuserNum();
                        int two = ((ReportCollegeCountInfo)b).getPuserNum();
                        return two - one;
                    }
                });
            }
            return dataList;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return dataList;
        }
        
    }
    
    // 显示所有校区的最新数据表
    private List<ReportCollegeCountInfo> getAllCollegePage(SearchCriteria searchCriteria, String cityId, String time)
    {
        List<ReportCollegeCountInfo> dataList = new ArrayList<ReportCollegeCountInfo>();
        // 获取该城市下的所有校区列表
        Long id = Long.parseLong(cityId.substring(2, 6));
        List<CityCollegeBean> collegeBeans = collegeService.selectFromCityId(id);
        // 遍历每个校区的小派数量
        for (CityCollegeBean bean : collegeBeans)
        {
            List<ReportCollegeCountInfo> oneDate = new ArrayList<ReportCollegeCountInfo>();
            searchCriteria.setRegion(bean.getFullName());
            List<ReportDeliveryCountInfo> list = getInfoList(searchCriteria, true, 1);
            // 转化为返回列表
            if (!(list.isEmpty() || list == null))
            {
                oneDate = (List<ReportCollegeCountInfo>)changeFromReportDeliveryCountInfo(list, 2);
            }
            // 没有找到该校区的小派记录
            if (oneDate == null || oneDate.isEmpty())
            {
                continue;
            }
            else // 找到该校区的小派记录
            {
                ReportCollegeCountInfo data = oneDate.get(0);
                data.setCollegeId(Integer.parseInt(bean.getCollegeId()));
                data.setDay(time);
                dataList.add(data);
            }
        }
        return dataList;
    }
    
    // 转化为各自的显示列表类
    private List<?> changeFromReportDeliveryCountInfo(List<ReportDeliveryCountInfo> list, int flag)
    {
        
        if (flag == 0)
        {
            List<ReportProvinceCountInfo> dataList = new ArrayList<ReportProvinceCountInfo>();
            for (ReportDeliveryCountInfo info : list)
            {
                ReportProvinceCountInfo provinceInfo = new ReportProvinceCountInfo();
                provinceInfo.setProvince(info.getRegion());
                provinceInfo.setPuserNum(info.getPuserNum());
                provinceInfo.setGrowthPercent(info.getGrowthPercent());
                provinceInfo.setDay(info.getTime());
                dataList.add(provinceInfo);
            }
            return dataList;
        }
        else if (flag == 1)
        {
            List<ReportCityCountInfo> dataList = new ArrayList<ReportCityCountInfo>();
            for (ReportDeliveryCountInfo info : list)
            {
                ReportCityCountInfo cityInfo = new ReportCityCountInfo();
                cityInfo.setCity(info.getRegion());
                cityInfo.setPuserNum(info.getPuserNum());
                cityInfo.setGrowthPercent(info.getGrowthPercent());
                cityInfo.setDay(info.getTime());
                dataList.add(cityInfo);
            }
            return dataList;
            
        }
        else if (flag == 2)
        {
            List<ReportCollegeCountInfo> dataList = new ArrayList<ReportCollegeCountInfo>();
            for (ReportDeliveryCountInfo info : list)
            {
                ReportCollegeCountInfo collegeInfo = new ReportCollegeCountInfo();
                collegeInfo.setCollege(info.getRegion());
                collegeInfo.setPuserNum(info.getPuserNum());
                collegeInfo.setGrowthPercent(info.getGrowthPercent());
                collegeInfo.setDay(info.getTime());
                dataList.add(collegeInfo);
            }
            return dataList;
        }
        return null;
        
    }
    
    /**
     * @Description: 获取小派数量 @param request @return ResultInfo @throws
     */
    @ResponseBody
    @RequestMapping(value = "/transport", method = RequestMethod.POST)
    public List<ReportTransportInfo> SelectTransportCount(HttpServletRequest request, HttpServletResponse response)
    {
        List<ReportTransportInfo> dataList = new ArrayList<ReportTransportInfo>();
        try
        {
            String flag = request.getParameter("flag");
            String selectStartDate = request.getParameter("selectStartDate");
            String selectEndDate = request.getParameter("selectEndDate");
            String college = request.getParameter("college");
            if (!flag.equals("true"))
            {
                // 校区不为空，日期不为空
                if (!college.equals("") && (!selectStartDate.equals("")) && (!selectEndDate.equals("")))
                {
                    // 获取起止时间
                    SearchCriteria searchCriteria = getSearchCriteria(selectStartDate, selectEndDate);
                    String[] collegeIds = college.split("\\,");
                    for (int i = 0; i < collegeIds.length; i++)
                    {
                        CollegeBean collegeBean = collegeService.selectByCollegeId(Long.parseLong(collegeIds[i]));
                        dataList.addAll(doTransportSearchCollege(collegeBean, searchCriteria));
                    }
                } // 校区不为空，日期有空
                else if (!college.equals("") && (selectStartDate.equals("") || selectEndDate.equals("")))
                {
                    SearchCriteria searchCriteria = searchCurrMonth();
                    String[] collegeIds = college.split("\\,");
                    for (int i = 0; i < collegeIds.length; i++)
                    {
                        CollegeBean collegeBean = collegeService.selectByCollegeId(Long.parseLong(collegeIds[i]));
                        dataList.addAll(doTransportSearchCollege(collegeBean, searchCriteria));
                    }
                } // 校区为空，日期不为空
                else if (college.equals("") && (!selectStartDate.equals("")) && (!selectEndDate.equals("")))
                {
                    // 获取起止时间
                    SearchCriteria searchCriteria = getSearchCriteria(selectStartDate, selectEndDate);
                    List<CollegeBean> collegeBeans = collegeService.getCollegeBeanList();
                    for (CollegeBean collegeBean : collegeBeans)
                    {
                        dataList.addAll(doTransportSearchCollege(collegeBean, searchCriteria));
                    }
                }
                else // 校区为空，日期为空 ，首页
                {
                    // 获取起止时间
                    dataList = getTransportFristPage();
                    Collections.sort(dataList, new Comparator()
                    {
                        public int compare(Object a, Object b)
                        {
                            int one = ((ReportTransportInfo)a).getPuserNum();
                            int two = ((ReportTransportInfo)b).getPuserNum();
                            return two - one;
                        }
                    });
                }
            }
            else // 首页
            {
                // 获取起止时间
                dataList = getTransportFristPage();
                Collections.sort(dataList, new Comparator()
                {
                    public int compare(Object a, Object b)
                    {
                        int one = ((ReportTransportInfo)a).getPuserNum();
                        int two = ((ReportTransportInfo)b).getPuserNum();
                        return two - one;
                    }
                });
                
            }
            
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return dataList;
        }
        return dataList;
    }
    
    // 处理校区检索
    private List<ReportTransportInfo> getTransportFristPage()
    {
        List<ReportTransportInfo> dataList = new ArrayList<ReportTransportInfo>();
        // 获取起止时间
        SearchCriteria searchCriteria = searchCurrMonth();
        List<CollegeBean> collegeBeans = collegeService.getCollegeBeanList();
        for (CollegeBean collegeBean : collegeBeans)
        {
            searchCriteria.setRegion(collegeBean.getFullName());
            List<ReportOrderStatisticsInfo> orderList = new ArrayList<ReportOrderStatisticsInfo>();
            orderList = reportOrderStatisticsService.getOrderStatisticsInfo(searchCriteria);
            if (orderList == null || orderList.isEmpty())
            {
                continue;
            }
            ReportTransportInfo info = createReportTransportInfo(orderList.get(0));
            dataList.add(info);
            
        }
        return dataList;
    }
    
    // 获取校区的运力新增表
    private List<ReportTransportInfo> doTransportSearchCollege(CollegeBean collegeBean, SearchCriteria searchCriteria)
    {
        List<ReportTransportInfo> dataList = new ArrayList<ReportTransportInfo>();
        searchCriteria.setRegion(collegeBean.getFullName());
        List<ReportOrderStatisticsInfo> orderList = new ArrayList<ReportOrderStatisticsInfo>();
        orderList = reportOrderStatisticsService.getOrderStatisticsInfo(searchCriteria);
        for (ReportOrderStatisticsInfo bean : orderList)
        {
            ReportTransportInfo info = createReportTransportInfo(bean);
            dataList.add(info);
        }
        return dataList;
    }
    
    // 小派首页的时间段获取
    private SearchCriteria getSearchCriteria(String selectMonth, String selectStartDate, String selectEndDate)
        throws Exception
    {
        SearchCriteria searchCriteria = new SearchCriteria();
        if (!selectMonth.equals("请选择"))
        {
            // 月份有效时，取所选月的最后一天，如果是当前月则现在当天的小派数量
            if (!selectStartDate.equals("") || !selectEndDate.equals(""))
            {
                throw new Exception("月份和日期两者不能同时选择！");
            }
            Date curr = new Date();
            int month = Integer.parseInt(selectMonth.substring(0, selectMonth.length() - 1));
            int currMonth = curr.getMonth() + 1;
            Date startDate;
            Date endDate;
            if (curr.getMonth() + 1 == month)
            {
                startDate = DateUtil.getDate(DateUtil.getFirstDayOfMonth(curr.getYear() + 1900, curr.getMonth())); // 月初;
                endDate = DateUtil.getDate(DateUtil.getDateStr(curr));
                endDate = DateUtil.getDayAfter(endDate);
            }
            else
            {
                startDate = DateUtil.getDate(DateUtil.getFirstDayOfMonth(curr.getYear() + 1900, month - 1)); // 查询月月初;
                endDate = DateUtil.getDate(DateUtil.getFirstDayOfMonth(curr.getYear() + 1900, month)); // 下个月月初;
            }
            searchCriteria.setStart(startDate);
            searchCriteria.setEnd(endDate);
        }
        else
        {
            if (selectStartDate.equals("") && selectEndDate.equals(""))
            {
                searchCriteria = searchCurrMonth();
            }
            else if (selectStartDate.equals("") || selectEndDate.equals(""))
            {
                throw new Exception("日期的起止时间不能单选！");
            }
            else
            {
                Date startDate = DateUtil.getDate(selectStartDate);
                Date endDate = DateUtil.getDate(selectEndDate);
                endDate = DateUtil.getDayAfter(endDate);
                searchCriteria.setStart(startDate);
                searchCriteria.setEnd(endDate);
            }
        }
        return searchCriteria;
    }
    
    // 获取起止时间
    private SearchCriteria getSearchCriteria(String selectStartDate, String selectEndDate)
        throws Exception
    {
        Date curr = new Date();
        Date startDate = null;
        Date endDate = null;
        // 获取起止时间
        
        if (selectStartDate.equals("") || selectEndDate.equals(""))
        {
            throw new Exception("日期必须有开始时间与结束时间，不允许只填写其中一个时间！");
        }
        else
        {
            startDate = DateUtil.getDate(selectStartDate);
            endDate = DateUtil.getDate(selectEndDate);
            if (startDate.after(curr) || endDate.before(startDate))
            {
                throw new Exception("请正确选择起止日期，截止日期必须大于起始日期，并且起始日期必须小于等于当前日期！");
            }
            endDate = DateUtil.getDayAfter(endDate);
        }
        
        // 查询条件
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setStart(startDate);
        searchCriteria.setEnd(endDate);
        return searchCriteria;
    }
    
    // 获取时间段
    private SearchCriteria getSearchCriteria(String time)
        throws Exception
    {
        Date startDay;
        Date afterDay;
        // 获取起止时间
        if (time.contains("月"))
        {
            int month = Integer.parseInt(time.substring(0, time.length() - 1));
            Date curr = new Date();
            if (curr.getMonth() + 1 == month)
            {
                startDay = DateUtil.getDate(DateUtil.getFirstDayOfMonth(curr.getYear() + 1900, curr.getMonth())); // 月初;
                afterDay = DateUtil.getDate(DateUtil.getDateStr(curr));
            }
            else
            {
                startDay = DateUtil.getDate(DateUtil.getFirstDayOfMonth(curr.getYear() + 1900, month - 1)); // 查询月月初;
                afterDay = DateUtil.getDate(DateUtil.getFirstDayOfMonth(curr.getYear() + 1900, month)); // 下个月月初;
            }
        }
        else
        {
            startDay = DateUtil.getDate(time);
            afterDay = DateUtil.getDayAfter(startDay);
        }
        
        // 获取起止时间
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setStart(startDay);
        searchCriteria.setEnd(afterDay);
        return searchCriteria;
    }
    
    // 获取当月的时间段
    private SearchCriteria searchCurrMonth()
    {
        Date curr = new Date();
        Date startDate = DateUtil.getDate(DateUtil.getFirstDayOfMonth(curr.getYear() + 1900, curr.getMonth())); // 月初
        Date endDate = DateUtil.getDate(DateUtil.getDateStr(curr));// 当天
        endDate = DateUtil.getDayAfter(endDate);
        // 获取起止时间
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setStart(startDate);
        searchCriteria.setEnd(endDate);
        return searchCriteria;
    }
    
    // 创建小派数量页面的返回列表，
    private List<ReportDeliveryCountInfo> getInfoList(SearchCriteria searchCriteria, boolean isCollege, int regionFlag)
    {
        List<ReportDeliveryCountInfo> dataList = new ArrayList<ReportDeliveryCountInfo>();
        if (isCollege)
        {
            List<ReportPuserBean> puserList = new ArrayList<ReportPuserBean>();
            puserList = reportPuserService.getPuserFromName(searchCriteria);
            for (ReportPuserBean bean : puserList)
            {
                ReportDeliveryCountInfo info = createReportDeliveryCountInfo(bean, true);
                info.setFlag(regionFlag);
                dataList.add(info);
            }
        }
        else
        {
            List<ReportRegionPuserSumBean> puserList = new ArrayList<ReportRegionPuserSumBean>();
            puserList = reportRegionPuserSumService.getPuserFromName(searchCriteria);
            for (ReportRegionPuserSumBean bean : puserList)
            {
                ReportDeliveryCountInfo info = createReportDeliveryCountInfo(bean, false);
                info.setFlag(regionFlag);
                dataList.add(info);
            }
        }
        return dataList;
    }
    
    // 创建返回新增运力页面的返回列表
    private ReportTransportInfo createReportTransportInfo(ReportOrderStatisticsInfo reportOrderStatisticsInfo)
    {
        float growthPercent = 0;
        int puserNum = reportOrderStatisticsInfo.getPuserNum();
        int shippingAbility = reportOrderStatisticsInfo.getShippingAbility();
        int collegeId = reportOrderStatisticsInfo.getCollegeId();
        Date day = reportOrderStatisticsInfo.getDay();
        Date beforeDay = DateUtil.getDayBefore(day);
        // 设定查询条件
        SearchCriteria searchDate = new SearchCriteria();
        searchDate.setId(collegeId);
        searchDate.setStart(beforeDay);// 日期
        searchDate.setEnd(day);
        int beforeShippingAbility = 0;
        try
        {
            ReportPuserBean reportPuser = reportPuserService.getPuserFromId(searchDate);            
            if (reportPuser != null)
            {
                beforeShippingAbility = reportPuser.getShippingAbility();
            }
        }
        catch (Exception e)
        {
            logger.error("获取"+beforeDay+",校区id为："+collegeId+"的小派数量失败！",e);
            
        }
        growthPercent = getPercent(shippingAbility, beforeShippingAbility);
        // 人均完成订单，保留1位有效数字，四舍五入
        int orderStatusDone = reportOrderStatisticsInfo.getOrderStatusDone();
        float avgOrder = 0;
        if (puserNum != 0)
        {
            avgOrder = (float)(orderStatusDone / (puserNum * 1.00));
            avgOrder = Float.valueOf(df.format(avgOrder).toString());
            float avgOrder1 = Float.valueOf(df1.format(avgOrder).toString());
            avgOrder1 = (float)(avgOrder1 + 0.05);
            if (avgOrder >= avgOrder1)
            {
                avgOrder = (float)(avgOrder + 0.1);
            }
            avgOrder = Float.valueOf(df1.format(avgOrder).toString());
            
        }
        // 打赏金额，两位小数
        float bonusIncome = (float)(reportOrderStatisticsInfo.getBonusIncome() / (100 * 1.00));
        bonusIncome = Float.valueOf(df.format(bonusIncome).toString());
        // 人均打赏收入,保留2位有效数字，四舍五入
        float avgOrderMoney = 0;
        if (puserNum != 0)
        {
            avgOrderMoney = (float)(reportOrderStatisticsInfo.getBonusIncome() / (puserNum * 100.00));
            avgOrderMoney = Float.valueOf(df2.format(avgOrderMoney).toString());
            float avgOrderMoney1 = Float.valueOf(df.format(avgOrderMoney).toString());
            avgOrderMoney1 = (float)(avgOrderMoney1 + 0.005);
            if (avgOrderMoney > avgOrderMoney1)
            {
                avgOrderMoney = (float)(avgOrderMoney + 0.01);
            }
            avgOrderMoney = Float.valueOf(df.format(avgOrderMoney).toString());
        }
        
        // 新建ReportTransportInfo
        ReportTransportInfo reportTransportInfo = new ReportTransportInfo();
        reportTransportInfo.setBonusIncome(bonusIncome);
        reportTransportInfo.setCollegeName(reportOrderStatisticsInfo.getCollegeName());
        reportTransportInfo.setDay(DateUtil.getDateStr(day));
        reportTransportInfo.setGrowthPercent(growthPercent);
        reportTransportInfo.setOrderStatusDone(orderStatusDone);
        reportTransportInfo.setPuserNum(puserNum);
        reportTransportInfo.setShippingAbility(reportOrderStatisticsInfo.getShippingAbility());
        reportTransportInfo.setAvgOrder(avgOrder);
        reportTransportInfo.setAvgOrderMoney(avgOrderMoney);
        return reportTransportInfo;
    }
    
    // 创建小派数量页面返回列表
    private ReportDeliveryCountInfo createReportDeliveryCountInfo(Object reportPuser, boolean isCollege)
    {
        ReportDeliveryCountInfo info = new ReportDeliveryCountInfo();
        // 小派数量
        int puserNum = 0;
        // 来源
        String channel = "待查";
        // 区域名称
        String region;
        // 区域id
        int regionId;
        // 上层区域id
        int upId = 1;
        // 记录日期
        Date date;
        // 区域百分比
        float regionPercent = 0;
        // 环比增长
        float growthPercent = 0;
        if (isCollege)
        {
            ReportPuserBean reportPuserBean = (ReportPuserBean)reportPuser;
            puserNum = reportPuserBean.getPuserNum();
            region = reportPuserBean.getCollegeName();
            // 上层区域的region_id
            upId = reportPuserBean.getRegionId();
            date = reportPuserBean.getDay();
            regionId = reportPuserBean.getCollegeId();
        }
        else
        {
            ReportRegionPuserSumBean reportPuserBean = (ReportRegionPuserSumBean)reportPuser;
            puserNum = reportPuserBean.getPuserNum();
            region = reportPuserBean.getRegionName();
            regionId = reportPuserBean.getRegionId();
            // 获取上层区域的region_id
            int id = reportPuserBean.getRegionId();
            String idStr = String.valueOf(id);
            if (idStr.length() == 2)
            {
                upId = Integer.parseInt(idStr.substring(0, 1));
            }
            else if (idStr.length() == 4)
            {
                upId = Integer.parseInt(idStr.substring(0, 2));
            }
            else if (idStr.length() == 6)
            {
                upId = Integer.parseInt(idStr.substring(0, 4));
            }
            date = reportPuserBean.getDay();
        }
        
        // 设定查询上层区域小派数量的条件
        SearchCriteria searchDate = new SearchCriteria();
        searchDate.setId(upId);
        searchDate.setStart(date);// 日期
        searchDate.setEnd(DateUtil.getDayAfter(date));
        
        Date curr = new Date();
        // 查询日期的当前月最后天
        Date monthDay;
        // 查询日期的前一个月的最后一天
        Date lastMonthDay;
        if (date.getMonth() == curr.getMonth())
        {
            monthDay = DateUtil.getDayBefore(curr);
            lastMonthDay = DateUtil.getDate(DateUtil.getLastDayOfMonth(curr.getYear() + 1900, curr.getMonth()));
        }
        else
        {
            monthDay = DateUtil.getDate(DateUtil.getLastDayOfMonth(date.getYear() + 1900, date.getMonth()));
            lastMonthDay = DateUtil.getDate(DateUtil.getLastDayOfMonth(date.getYear() + 1900, date.getMonth() - 1));
        }
        // 获取区域百分比
        regionPercent = getRegionPercent(puserNum, searchDate);
        // 获取环比增长百分比
        if (isCollege)
        {
            growthPercent = getGrowthPercent(monthDay, lastMonthDay, regionId, true);
        }
        else
        {
            growthPercent = getGrowthPercent(monthDay, lastMonthDay, regionId, false);
        }
        // 设置info
        info.setTime(DateUtil.getDateStr(date));
        info.setPuserNum(puserNum);
        info.setChannel(channel);
        info.setRegion(region);
        info.setRegionPercent(regionPercent);
        info.setGrowthPercent(growthPercent);
        info.setRegionId(regionId);
        return info;
    }
    
    // puserNum为当前区域的小派数量，regionId为当前区域的上层区域的region_id,searchDate
    private float getRegionPercent(int puserNum, SearchCriteria searchDate)
    {
        ReportRegionPuserSumBean reportRegionPuserSum;
        try
        {
            reportRegionPuserSum = reportRegionPuserSumService.getPuserBeanFromId(searchDate);
            int upPuserNum = 0;
            if (reportRegionPuserSum != null)
            {
                upPuserNum = reportRegionPuserSum.getPuserNum();
            }
            if (upPuserNum == 0)
            {
                return 100;
            }
            float percent = (float)(puserNum / (upPuserNum * 1.00));
            percent = percent * 100;
            percent = Float.valueOf(df.format(percent).toString());
            return percent;
        }
        catch (Exception e)
        {
            logger.error("获取百分比失败！", e);
            return 100;
        }
        
    }
    
    // monthDay位检索的日期所在月份的最后一天，lastMonthDay为检索日期的上一个月的最后一天，region为所检索的区域
    private float getGrowthPercent(Date monthDay, Date lastMonthDay, int id, boolean isCollege)
    {
        float percent = 0;
        SearchCriteria searchMonthDay = new SearchCriteria();
        searchMonthDay.setStart(monthDay);
        searchMonthDay.setEnd(DateUtil.getDayAfter(monthDay));
        searchMonthDay.setId(id);
        SearchCriteria searchLastMonthDay = new SearchCriteria();
        searchLastMonthDay.setStart(lastMonthDay);
        searchLastMonthDay.setEnd(DateUtil.getDayAfter(lastMonthDay));
        searchLastMonthDay.setId(id);
        if (isCollege)
        {
            ReportPuserBean reportPuser = reportPuserService.getPuserFromId(searchMonthDay);
            int puserNumMonth = 0;
            if (reportPuser != null)
            {
                puserNumMonth = reportPuser.getPuserNum();
            }
            
            ReportPuserBean LastreportPuser = reportPuserService.getPuserFromId(searchLastMonthDay);
            int puserNumLastMonth = 0;
            if (LastreportPuser != null)
            {
                puserNumLastMonth = LastreportPuser.getPuserNum();
            }
            
            percent = getPercent(puserNumMonth, puserNumLastMonth);
            return percent;
        }
        else
        {
            ReportRegionPuserSumBean reportRegionPuserSum =
                reportRegionPuserSumService.getPuserBeanFromId(searchMonthDay);
            int puserNumMonth = 0;
            if (reportRegionPuserSum != null)
            {
                puserNumMonth = reportRegionPuserSum.getPuserNum();
            }
            ReportRegionPuserSumBean lastReportRegionPuserSum =
                reportRegionPuserSumService.getPuserBeanFromId(searchLastMonthDay);
            int puserNumLastMonth = 0;
            if (lastReportRegionPuserSum != null)
            {
                puserNumLastMonth = lastReportRegionPuserSum.getPuserNum();
            }
            percent = getPercent(puserNumMonth, puserNumLastMonth);
            return percent;
        }
        
    }
    
    public float getPercent(int puserNumMonth, int puserNumLastMonth)
    {
        if (puserNumLastMonth == 0)
        {
            return 100;
        }
        float percent = (float)((puserNumMonth - puserNumLastMonth) / (puserNumLastMonth * 1.00));
        percent = percent * 100;
        percent = Float.valueOf(df.format(percent).toString());
        return percent;
    }
    
}
