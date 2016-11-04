/*
 * 文 件 名:  ReportRegionBean.java
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
public class ReportPuserBean implements Serializable
{
    
    private static final long serialVersionUID = 7032573373820585624L;
    
    Long id;
    
    // 日期
    Date day;
    
    // 校区ID
    Integer collegeId;
    
    // 校区全称
    String collegeName;
    
    // 区域ID
    Integer regionId;
    
    // 区域描述: 市
    String regionName;
    
    // 当前小派总数
    Integer puserNum;
    
    // 当天活跃小派总数
    Integer activePuserNumDay;
    
    // 当月活跃的小派总数
    Integer activePuserNumMonth;
    
    // 当天打赏总金额
    Integer bonusIncome;
    
    // 当天运力指数（1/10）
    Integer shippingIndex;
    
    // 当天运力
    Integer shippingAbility;
    
    public ReportPuserBean(Long id,Date day,Integer collegeId,String collegeName,Integer regionId,String regionName,Integer puserNum,Integer activePuserNumDay,
        Integer activePuserNumMonth,Integer bonusIncome,Integer shippingIndex,Integer shippingAbility)
    {
        this.id = id;
        
        this.day = day;
        
        this.collegeId = collegeId;
        
        this.collegeName = collegeName;
        
        this.regionId = regionId;
        
        this.regionName = regionName;
        
        this.puserNum = puserNum;
        
        this.activePuserNumDay = activePuserNumDay;
        
        this.activePuserNumMonth = activePuserNumMonth;
        
        this.bonusIncome = bonusIncome;
        
        this.shippingIndex = shippingIndex;
        
        this.shippingAbility = shippingAbility;
    }
    
    public ReportPuserBean()
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

    public Integer getCollegeId()
    {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId)
    {
        this.collegeId = collegeId;
    }

    public String getCollegeName()
    {
        return collegeName;
    }

    public void setCollegeName(String collegeName)
    {
        this.collegeName = collegeName;
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

    public Integer getActivePuserNumDay()
    {
        return activePuserNumDay;
    }

    public void setActivePuserNumDay(Integer activePuserNumDay)
    {
        this.activePuserNumDay = activePuserNumDay;
    }

    public Integer getActivePuserNumMonth()
    {
        return activePuserNumMonth;
    }

    public void setActivePuserNumMonth(Integer activePuserNumMonth)
    {
        this.activePuserNumMonth = activePuserNumMonth;
    }

    public Integer getBonusIncome()
    {
        return bonusIncome;
    }

    public void setBonusIncome(Integer bonusIncome)
    {
        this.bonusIncome = bonusIncome;
    }

    public Integer getShippingIndex()
    {
        return shippingIndex;
    }

    public void setShippingIndex(Integer shippingIndex)
    {
        this.shippingIndex = shippingIndex;
    }

    public Integer getShippingAbility()
    {
        return shippingAbility;
    }

    public void setShippingAbility(Integer shippingAbility)
    {
        this.shippingAbility = shippingAbility;
    }
    
   
    
  
    
}
