/*
 * 文 件 名:  ReportPuserServiceImpl.java
 * 版    权:  Shanghai zhiduan Mdt InfoTech Ltd
 * 描    述:  <描述>
 * 修 改 人:  tongfei
 * 修改时间:  2016年10月12日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.rofour.baseball.service.report.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.dao.report.bean.ReportPuserBean;
import com.rofour.baseball.dao.report.bean.ReportRegionPuserSumBean;
import com.rofour.baseball.dao.report.bean.SearchCriteria;
import com.rofour.baseball.dao.report.mapper.ReportPuserMapper;
import com.rofour.baseball.dao.report.mapper.ReportRegionMapper;
import com.rofour.baseball.dao.report.mapper.ReportRegionPuserSumMapper;
import com.rofour.baseball.service.report.ReportPuserService;
import com.rofour.baseball.service.report.ReportRegionPuserSumService;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author tongfei
 * @version [版本号, 2016年10月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("reportRegionPuserSumService")
public class ReportRegionPuserSumServiceImpl implements ReportRegionPuserSumService
{
    @Autowired
    @Qualifier("reportRegionPuserSumMapper")
    private ReportRegionPuserSumMapper dao;

    /**
     * 重载方法
     * @param reportRegionPuserSumBean
     */
    @Override
    public void insert(ReportRegionPuserSumBean reportRegionPuserSumBean)
    {
        dao.insert(reportRegionPuserSumBean);
        
    }

    /**
     * 重载方法
     * @param id
     */
    @Override
    public void delete(int id)
    {
       dao.delete(id);
        
    }

    /**
     * 重载方法
     * @param searchCriteria
     * @return
     */
    @Override
    public List<ReportRegionPuserSumBean> getPuserFromName(SearchCriteria searchCriteria)
    {
        
        return dao.getPuserFromName(searchCriteria);
    }

    /**
     * 重载方法
     * @param searchCriteria
     * @return
     */
    @Override
    public ReportRegionPuserSumBean getPuserBeanFromName(SearchCriteria searchCriteria)
    {
        
        return dao.getPuserBeanFromName(searchCriteria);
    }

    /**
     * 重载方法
     * @param searchCriteria
     * @return
     */
    @Override
    public ReportRegionPuserSumBean getPuserBeanFromId(SearchCriteria searchCriteria)
    {
     
        return dao.getPuserBeanFromId(searchCriteria);
    }
    
    
}
