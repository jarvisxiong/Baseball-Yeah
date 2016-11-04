/*
 * 文 件 名:  ReportDeliveryCountBean.java
 * 版    权:  Shanghai zhiduan Mdt InfoTech Ltd
 * 描    述:  <描述>
 * 修 改 人:  tongfei
 * 修改时间:  2016年10月12日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.rofour.baseball.dao.report.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author tongfei
 * @version [版本号, 2016年10月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ReportDeliveryCountInfo implements Serializable
{
    
    private static final long serialVersionUID = 7032573373820585624L;
    
    // 时间段
    String time;
    
    int flag;
    
    int regionId;
    
    // 小派数量
    int puserNum;
    
    Date day;
    
    // 渠道来源
    String channel;
    
    // 区域名称
    String region;
    
    // 区域百分比
    float regionPercent;
    
    // 环比增长
    float growthPercent;
    
  
    
  

    public Date getDay()
    {
        return day;
    }

    public void setDay(Date day)
    {
        this.day = day;
    }

    public int getFlag()
    {
        return flag;
    }
    
    public void setFlag(int flag)
    {
        this.flag = flag;
    }
    
    public int getRegionId()
    {
        return regionId;
    }
    
    public void setRegionId(int regionId)
    {
        this.regionId = regionId;
    }
    
    public String getTime()
    {
        return time;
    }
    
    public void setTime(String time)
    {
        this.time = time;
    }
    
    public int getPuserNum()
    {
        return puserNum;
    }
    
    public void setPuserNum(int puserNum)
    {
        this.puserNum = puserNum;
    }
    
    public String getChannel()
    {
        return channel;
    }
    
    public void setChannel(String channel)
    {
        this.channel = channel;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    public float getRegionPercent()
    {
        return regionPercent;
    }
    
    public void setRegionPercent(float regionPercent)
    {
        this.regionPercent = regionPercent;
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
