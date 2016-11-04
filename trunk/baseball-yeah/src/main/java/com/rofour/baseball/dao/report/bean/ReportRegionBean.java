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
public class ReportRegionBean implements Serializable
{
    
    private static final long serialVersionUID = 7032573373820585624L;
    
    Long id;
    
    // 区域ID
    Integer regionId;
    
    // 区域描述: 大区，省，市
    String regionName;
    
    // 创建人
    String createUser;
    
    // 创建时间
    Date createTime;
    
    // 是否常用，如果常用则每日定时生成
    Integer isCommon;
    
    public ReportRegionBean(Long id,Integer regionId,String regionName,String createUser,Date createTime,Integer isCommon)
    {
       this.id=id;
       this.regionId=regionId;
       this.regionName=regionName;
       this.createUser=createUser;
       this.createTime=createTime;
       this.isCommon=isCommon;
    }
    public ReportRegionBean()
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
    public String getCreateUser()
    {
        return createUser;
    }
    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }
    public Date getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    public Integer getIsCommon()
    {
        return isCommon;
    }
    public void setIsCommon(Integer isCommon)
    {
        this.isCommon = isCommon;
    }
   
    
   
    
}
