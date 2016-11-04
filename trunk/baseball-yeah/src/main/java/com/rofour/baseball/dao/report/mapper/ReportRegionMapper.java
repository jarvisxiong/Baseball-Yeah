/*
 * 文 件 名:  ReportRegionMapper.java
 * 版    权:  Shanghai zhiduan Mdt InfoTech Ltd
 * 描    述:  <描述>
 * 修 改 人:  tongfei
 * 修改时间:  2016年10月12日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.rofour.baseball.dao.report.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.dao.report.bean.ReportRegionBean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  tongfei
 * @version  [版本号, 2016年10月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Named("reportRegionMapper")
public interface ReportRegionMapper
{
    public List<ReportRegionBean> getRegionList();
    public void insert(ReportRegionBean reportRegionBean);
    public void delete(int id);
    public ReportRegionBean getRegionFromName(String regionName);
    public ReportRegionBean getRegionFromId(String regionId);
}
