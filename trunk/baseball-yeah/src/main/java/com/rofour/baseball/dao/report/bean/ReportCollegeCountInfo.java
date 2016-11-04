/*
 * 文 件 名:  ReportProvinceCountInfo.java
 * 版    权:  Shanghai zhiduan Mdt InfoTech Ltd
 * 描    述:  <描述>
 * 修 改 人:  tongfei
 * 修改时间:  2016年10月13日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.rofour.baseball.dao.report.bean;

import java.io.Serializable;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author tongfei
 * @version [版本号, 2016年10月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ReportCollegeCountInfo implements Serializable
{
    
    private static final long serialVersionUID = 7032573373820585624L;
    //collegeId
    int collegeId;
    
    // 校区名称
    String college;
    
    // 小派总数
    int puserNum;
    
    // 环比增长
    float growthPercent;
    
    String day;
    
    public String getDay()
    {
        return day;
    }

    public void setDay(String day)
    {
        this.day = day;
    }

    public int getCollegeId()
    {
        return collegeId;
    }

    public void setCollegeId(int collegeId)
    {
        this.collegeId = collegeId;
    }

    public String getCollege()
    {
        return college;
    }

    public void setCollege(String college)
    {
        this.college = college;
    }

    public int getPuserNum()
    {
        return puserNum;
    }
    
    public void setPuserNum(int puserNum)
    {
        this.puserNum = puserNum;
    }
    
    public float getGrowthPercent()
    {
        return growthPercent;
    }
    
    public void setGrowthPercent(float growthPercent)
    {
        this.growthPercent = growthPercent;
    }
    
}
