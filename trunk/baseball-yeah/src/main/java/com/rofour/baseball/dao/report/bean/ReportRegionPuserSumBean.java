package com.rofour.baseball.dao.report.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 区域下的小派数量,按天,小派数量汇总
 * 
 * @author zhoujie
 *
 */
public class ReportRegionPuserSumBean implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -9155717236752117319L;
    
    private Long id;// 主键id
    
    private Date day;// 日期
    
    private Integer regionId;// 区域id
    
    private String regionName;// 区域名称
    
    private Integer puserNum;// 小派总数
    
    private Integer activePuserNum;// 活跃小派数
    
    public ReportRegionPuserSumBean(Long id, Date day, Integer regionId, String regionName, Integer puserNum,
        Integer activePuserNum)
    {
        this.id = id;
        this.day = day;
        this.regionId = regionId;
        this.regionName = regionName;
        this.puserNum = puserNum;
        this.activePuserNum = activePuserNum;
    }
    
    public ReportRegionPuserSumBean()
    {
        super();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

   

    public Date getDay()
    {
        return day;
    }

    public void setDay(Date day)
    {
        this.day = day;
    }

    public Integer getRegionId()
    {
        return regionId;
    }

    public void setRegionId(Integer regionId)
    {
        this.regionId = regionId;
    }

    public String getRegionName()
    {
        return regionName;
    }

    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

    public Integer getPuserNum()
    {
        return puserNum;
    }

    public void setPuserNum(Integer puserNum)
    {
        this.puserNum = puserNum;
    }

    public Integer getActivePuserNum()
    {
        return activePuserNum;
    }

    public void setActivePuserNum(Integer activePuserNum)
    {
        this.activePuserNum = activePuserNum;
    }
    
  
    
}
