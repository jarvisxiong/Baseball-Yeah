/*
 * 文 件 名:  ReportTransportInfo.java
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
import java.util.Date;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author tongfei
 * @version [版本号, 2016年10月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ReportOrderStatisticsInfo implements Serializable
{
    
    private static final long serialVersionUID = 7032573373820585624L;
    
    
    
    //学校id
    Integer collegeId;
    // 校区全称
    String collegeName;
    
    // 日期
    Date day;
    
    // 小派总数
    Integer puserNum;
    
    // 当天已完成订单数
    Integer orderStatusDone;
    
    // 打赏收入金额（元）
    Integer bonusIncome;
    
    // 运力
    Integer shippingAbility;
    public ReportOrderStatisticsInfo(Integer collegeId,String collegeName,Date day,Integer puserNum,Integer orderStatusDone,Integer bonusIncome,Integer shippingAbility)
    {
       this.collegeId=collegeId;
       this.collegeName=collegeName;
       this.day=day;
       this.puserNum=puserNum;
       this.orderStatusDone=orderStatusDone;
       this.bonusIncome=bonusIncome;
       this.shippingAbility=shippingAbility;
    }
    public ReportOrderStatisticsInfo()
    {
        super();
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
    public Date getDay()
    {
        return day;
    }
    public void setDay(Date day)
    {
        this.day = day;
    }
    public Integer getPuserNum()
    {
        return puserNum;
    }
    public void setPuserNum(Integer puserNum)
    {
        this.puserNum = puserNum;
    }
    public Integer getOrderStatusDone()
    {
        return orderStatusDone;
    }
    public void setOrderStatusDone(Integer orderStatusDone)
    {
        this.orderStatusDone = orderStatusDone;
    }
    public Integer getBonusIncome()
    {
        return bonusIncome;
    }
    public void setBonusIncome(Integer bonusIncome)
    {
        this.bonusIncome = bonusIncome;
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
