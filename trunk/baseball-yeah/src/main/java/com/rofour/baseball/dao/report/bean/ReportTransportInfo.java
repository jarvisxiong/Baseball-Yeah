/*
 * 文 件 名:  ReportTransportInfo.java
 * 版    权:  Shanghai zhiduan Mdt InfoTech Ltd
 * 描    述:  <描述>
 * 修 改 人:  tongfei
 * 修改时间:  2016年10月14日
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
 * @version [版本号, 2016年10月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ReportTransportInfo implements Serializable
{
    
    private static final long serialVersionUID = 7032573373820585624L;
    
    // 校区全称
    String collegeName;
    
    // 日期
    String  day;
    
    // 小派总数
    int puserNum;
    
    // 当天已完成订单数
    int orderStatusDone;
    
    // 打赏收入金额（元）
    float bonusIncome;
    
    // 人均完成订单
    float avgOrder;
    
    // 人均打赏收入
    float avgOrderMoney;
    
    // 运力
    int shippingAbility;
    
    // 环比增长
    float growthPercent;
    
    public String getCollegeName()
    {
        return collegeName;
    }
    
    public void setCollegeName(String collegeName)
    {
        this.collegeName = collegeName;
    }
    
  
    
    public String getDay()
    {
        return day;
    }

    public void setDay(String day)
    {
        this.day = day;
    }

    public int getPuserNum()
    {
        return puserNum;
    }
    
    public void setPuserNum(int puserNum)
    {
        this.puserNum = puserNum;
    }
    
    public int getOrderStatusDone()
    {
        return orderStatusDone;
    }
    
    public void setOrderStatusDone(int orderStatusDone)
    {
        this.orderStatusDone = orderStatusDone;
    }
    
  
    
    

	
    
    public float getBonusIncome()
    {
        return bonusIncome;
    }

    public void setBonusIncome(float bonusIncome)
    {
        this.bonusIncome = bonusIncome;
    }

    public float getAvgOrder()
    {
        return avgOrder;
    }

    public void setAvgOrder(float avgOrder)
    {
        this.avgOrder = avgOrder;
    }

    public float getAvgOrderMoney()
    {
        return avgOrderMoney;
    }
    
    public void setAvgOrderMoney(float avgOrderMoney)
    {
        this.avgOrderMoney = avgOrderMoney;
    }
    
    public int getShippingAbility()
    {
        return shippingAbility;
    }
    
    public void setShippingAbility(int shippingAbility)
    {
        this.shippingAbility = shippingAbility;
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
