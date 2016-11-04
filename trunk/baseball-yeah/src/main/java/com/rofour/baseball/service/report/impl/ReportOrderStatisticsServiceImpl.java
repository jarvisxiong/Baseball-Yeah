/*
 * 文 件 名:  ReportOrderStatisticsServiceImpl.java
 * 版    权:  Shanghai zhiduan Mdt InfoTech Ltd
 * 描    述:  <描述>
 * 修 改 人:  tongfei
 * 修改时间:  2016年10月14日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.rofour.baseball.service.report.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.dao.report.bean.ReportOrderStatisticsBean;
import com.rofour.baseball.dao.report.bean.ReportOrderStatisticsInfo;
import com.rofour.baseball.dao.report.bean.SearchCriteria;
import com.rofour.baseball.dao.report.mapper.ReportOrderStatisticsMapper;
import com.rofour.baseball.service.report.ReportOrderStatisticsService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  tongfei
 * @version  [版本号, 2016年10月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("reportOrderStatisticsService")
public class ReportOrderStatisticsServiceImpl implements ReportOrderStatisticsService
{
    @Autowired
    @Qualifier(value="reportOrderStatisticsMapper")
    private ReportOrderStatisticsMapper dao;
    /**
     * 重载方法
     * @param reportPuserBean
     */
    @Override
    public void insert(ReportOrderStatisticsBean reportOrderStatisticsBean)
    {
       dao.insert(reportOrderStatisticsBean);
        
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
    public List<ReportOrderStatisticsBean> getOrderFromName(SearchCriteria searchCriteria)
    {
       
        return dao.getOrderFromName(searchCriteria);
    }

    /**
     * 重载方法
     * @param searchCriteria
     * @return
     */
    @Override
    public ReportOrderStatisticsBean getOrderFromId(SearchCriteria searchCriteria)
    {
       
        return dao.getOrderFromId(searchCriteria);
    }

    /**
     * 重载方法
     * @param searchCriteria
     * @return
     */
    @Override
    public List<ReportOrderStatisticsInfo> getOrderStatisticsInfo(SearchCriteria searchCriteria)
    {
       
        return dao.getOrderStatisticsInfo(searchCriteria);
    }
    
}
