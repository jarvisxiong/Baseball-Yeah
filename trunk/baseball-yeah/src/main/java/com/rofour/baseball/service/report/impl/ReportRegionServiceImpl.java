/*
 * 文 件 名:  ReportRegionServiceImpl.java
 * 版    权:  Shanghai zhiduan Mdt InfoTech Ltd
 * 描    述:  <描述>
 * 修 改 人:  tongfei
 * 修改时间:  2016年10月12日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.rofour.baseball.service.report.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.dao.report.bean.ReportRegionBean;
import com.rofour.baseball.dao.report.mapper.ReportRegionMapper;
import com.rofour.baseball.service.report.ReportRegionService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  tongfei
 * @version  [版本号, 2016年10月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("reportRegionService")
public class ReportRegionServiceImpl implements ReportRegionService
{
    @Autowired
    @Qualifier("reportRegionMapper")
    private ReportRegionMapper dao;
    /**
     * 重载方法
     * @return
     */
    @Override
    public List<ReportRegionBean> getRegionList()
    { 
        return dao.getRegionList();
    }

    /**
     * 重载方法
     * @param reportRegionBean
     */
    @Override
    public void insert(ReportRegionBean reportRegionBean)
    {
        dao.insert(reportRegionBean);
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
     * @param regionName
     * @return
     */
    @Override
    public ReportRegionBean getRegionFromName(String regionName)
    {
        return dao.getRegionFromName(regionName);
    }

    /**
     * 重载方法
     * @param regionId
     * @return
     */
    @Override
    public ReportRegionBean getRegionFromId(String regionId)
    {
        return dao.getRegionFromId(regionId);
    }
    
}
