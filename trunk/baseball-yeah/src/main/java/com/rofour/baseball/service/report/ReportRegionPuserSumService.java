/*
 * 文 件 名:  ReportPuserService.java
 * 版    权:  Shanghai zhiduan Mdt InfoTech Ltd
 * 描    述:  <描述>
 * 修 改 人:  tongfei
 * 修改时间:  2016年10月12日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.rofour.baseball.service.report;

import java.util.Date;
import java.util.List;

import com.rofour.baseball.dao.report.bean.ReportPuserBean;
import com.rofour.baseball.dao.report.bean.ReportRegionPuserSumBean;
import com.rofour.baseball.dao.report.bean.SearchCriteria;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  tongfei
 * @version  [版本号, 2016年10月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ReportRegionPuserSumService
{
 public void insert(ReportRegionPuserSumBean reportRegionPuserSumBean);
    
    public void delete(int id);
    
    public List<ReportRegionPuserSumBean> getPuserFromName(SearchCriteria searchCriteria);
    
    public ReportRegionPuserSumBean getPuserBeanFromName(SearchCriteria searchCriteria);
    
    public ReportRegionPuserSumBean getPuserBeanFromId(SearchCriteria searchCriteria);
}
