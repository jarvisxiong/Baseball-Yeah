/*
 * 文 件 名:  ReportOrderStatisticsBean.java
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
public class ReportOrderStatisticsBean implements Serializable
{
    
    private static final long serialVersionUID = 7032573373820585624L;
    
    Long id;
    
    // 校区ID
    Integer collegeId;
    
    // 校区全称
    String collegeName;
    
    // 日期
    Date day;
    
    // 区域ID
    Integer regionId;
    
    // 区域描述
    String regionName;
    
    // 当天订单总数
    Integer totalOrderNum;
    
    // 历史平均订单数
    Double orderNumAvg;
    
    // 当天订单总金额
    Integer totalOrderMoney;
    
    // 当天支付的总金额
    Integer totalFinalMoney;
    
    // 当天订单状态：创建中
    Integer orderStatusCreating;
    
    // 当天订单状态：待接单
    Integer orderStatusWaittaking;
    
    // 当天订单状态：已接单
    Integer orderStatusToken;
    
    // 当天订单状态：配送中
    Integer orderStatusShipping;
    
    // 当天订单状态：已完成
    Integer orderStatusDone;
    
    // 当天订单状态：已取消
    Integer orderStatusCancel;
    
    // 当天订单状态：异常
    Integer orderStatusAbnormal;
    
    // 当天接单时效（单位：分钟）
    Double takeOrderMins;
    
    // 订单类型：合作商户代取
    Integer orderTypeCoTake;
    
    // 订单类型：非合作商户代取
    Integer orderTypeNoncoTake;
    
    // 订单类型：代寄
    Integer orderTypeSend;
    
    // 当天使用代金券订单数
    Integer voucherOrderNum;
    
    // 当天使用代金券抵消金额
    Integer voucherOrderMoney;
    
    // 当天使用代金券订单实付金额
    Integer voucherOrderActualpay;
    
    // 当天订单来源数：服务号
    Integer orderSourceService;
    
    // 当天订单来源数：商户短信
    Integer orderSourceSms;
    
    // 当天订单来源数：合作链接
    Integer orderSourceColink;
    
    // 有效评论订单数
    Integer commonentOrderNum;
    
    // 有效评论好评订单数
    Integer commonentOrderGoodnum;
    
    // 有效评论用户打分总数
    Integer commonentOrderGetscore;
    
    public ReportOrderStatisticsBean(Long id, Integer collegeId, String collegeName, Date day, Integer regionId,
        String regionName, Integer totalOrderNum, Double orderNumAvg, Integer totalOrderMoney, Integer totalFinalMoney,
        Integer orderStatusCreating, Integer orderStatusWaittaking, Integer orderStatusToken, Integer orderStatusShipping,
        Integer orderStatusDone, Integer orderStatusCancel, Integer orderStatusAbnormal, Double takeOrderMins, Integer orderTypeCoTake,
        Integer orderTypeNoncoTake, Integer orderTypeSend, Integer voucherOrderNum, Integer voucherOrderMoney,
        Integer voucherOrderActualpay, Integer orderSourceService, Integer orderSourceSms, Integer orderSourceColink,
        Integer commonentOrderNum, Integer commonentOrderGoodnum, Integer commonentOrderGetscore)
    {
        this.id = id;
        this.collegeId = collegeId;
        
        this.collegeName = collegeName;
        
        this.day = day;
        
        this.regionId = regionId;
        
        this.regionName = regionName;
        
        this.totalOrderNum = totalOrderNum;
        
        this.orderNumAvg = orderNumAvg;
        
        this.totalOrderMoney = totalOrderMoney;
        
        this.totalFinalMoney = totalFinalMoney;
        
        this.orderStatusCreating = orderStatusCreating;
        
        this.orderStatusWaittaking = orderStatusWaittaking;
        
        this.orderStatusToken = orderStatusToken;
        
        this.orderStatusShipping = orderStatusShipping;
        
        this.orderStatusDone = orderStatusDone;
        
        this.orderStatusCancel = orderStatusCancel;
        
        this.orderStatusAbnormal = orderStatusAbnormal;
        
        this.takeOrderMins = takeOrderMins;
        
        this.orderTypeCoTake = orderTypeCoTake;
        
        this.orderTypeNoncoTake = orderTypeNoncoTake;
        
        this.orderTypeSend = orderTypeSend;
        
        this.voucherOrderNum = voucherOrderNum;
        
        this.voucherOrderMoney = voucherOrderMoney;
        
        this.voucherOrderActualpay = voucherOrderActualpay;
        
        this.orderSourceService = orderSourceService;
        
        this.orderSourceSms = orderSourceSms;
        
        this.orderSourceColink = orderSourceColink;
        
        this.commonentOrderNum = commonentOrderNum;
        
        this.commonentOrderGoodnum = commonentOrderGoodnum;
        
        this.commonentOrderGetscore = commonentOrderGetscore;
        
    }
    
    public ReportOrderStatisticsBean()
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

    public Integer getTotalOrderNum()
    {
        return totalOrderNum;
    }

    public void setTotalOrderNum(Integer totalOrderNum)
    {
        this.totalOrderNum = totalOrderNum;
    }

    public Double getOrderNumAvg()
    {
        return orderNumAvg;
    }

    public void setOrderNumAvg(Double orderNumAvg)
    {
        this.orderNumAvg = orderNumAvg;
    }

    public Integer getTotalOrderMoney()
    {
        return totalOrderMoney;
    }

    public void setTotalOrderMoney(Integer totalOrderMoney)
    {
        this.totalOrderMoney = totalOrderMoney;
    }

    public Integer getTotalFinalMoney()
    {
        return totalFinalMoney;
    }

    public void setTotalFinalMoney(Integer totalFinalMoney)
    {
        this.totalFinalMoney = totalFinalMoney;
    }

    public Integer getOrderStatusCreating()
    {
        return orderStatusCreating;
    }

    public void setOrderStatusCreating(Integer orderStatusCreating)
    {
        this.orderStatusCreating = orderStatusCreating;
    }

    public Integer getOrderStatusWaittaking()
    {
        return orderStatusWaittaking;
    }

    public void setOrderStatusWaittaking(Integer orderStatusWaittaking)
    {
        this.orderStatusWaittaking = orderStatusWaittaking;
    }

    public Integer getOrderStatusToken()
    {
        return orderStatusToken;
    }

    public void setOrderStatusToken(Integer orderStatusToken)
    {
        this.orderStatusToken = orderStatusToken;
    }

    public Integer getOrderStatusShipping()
    {
        return orderStatusShipping;
    }

    public void setOrderStatusShipping(Integer orderStatusShipping)
    {
        this.orderStatusShipping = orderStatusShipping;
    }

    public Integer getOrderStatusDone()
    {
        return orderStatusDone;
    }

    public void setOrderStatusDone(Integer orderStatusDone)
    {
        this.orderStatusDone = orderStatusDone;
    }

    public Integer getOrderStatusCancel()
    {
        return orderStatusCancel;
    }

    public void setOrderStatusCancel(Integer orderStatusCancel)
    {
        this.orderStatusCancel = orderStatusCancel;
    }

    public Integer getOrderStatusAbnormal()
    {
        return orderStatusAbnormal;
    }

    public void setOrderStatusAbnormal(Integer orderStatusAbnormal)
    {
        this.orderStatusAbnormal = orderStatusAbnormal;
    }

    public Double getTakeOrderMins()
    {
        return takeOrderMins;
    }

    public void setTakeOrderMins(Double takeOrderMins)
    {
        this.takeOrderMins = takeOrderMins;
    }

    public Integer getOrderTypeCoTake()
    {
        return orderTypeCoTake;
    }

    public void setOrderTypeCoTake(Integer orderTypeCoTake)
    {
        this.orderTypeCoTake = orderTypeCoTake;
    }

    public Integer getOrderTypeNoncoTake()
    {
        return orderTypeNoncoTake;
    }

    public void setOrderTypeNoncoTake(Integer orderTypeNoncoTake)
    {
        this.orderTypeNoncoTake = orderTypeNoncoTake;
    }

    public Integer getOrderTypeSend()
    {
        return orderTypeSend;
    }

    public void setOrderTypeSend(Integer orderTypeSend)
    {
        this.orderTypeSend = orderTypeSend;
    }

    public Integer getVoucherOrderNum()
    {
        return voucherOrderNum;
    }

    public void setVoucherOrderNum(Integer voucherOrderNum)
    {
        this.voucherOrderNum = voucherOrderNum;
    }

    public Integer getVoucherOrderMoney()
    {
        return voucherOrderMoney;
    }

    public void setVoucherOrderMoney(Integer voucherOrderMoney)
    {
        this.voucherOrderMoney = voucherOrderMoney;
    }

    public Integer getVoucherOrderActualpay()
    {
        return voucherOrderActualpay;
    }

    public void setVoucherOrderActualpay(Integer voucherOrderActualpay)
    {
        this.voucherOrderActualpay = voucherOrderActualpay;
    }

    public Integer getOrderSourceService()
    {
        return orderSourceService;
    }

    public void setOrderSourceService(Integer orderSourceService)
    {
        this.orderSourceService = orderSourceService;
    }

    public Integer getOrderSourceSms()
    {
        return orderSourceSms;
    }

    public void setOrderSourceSms(Integer orderSourceSms)
    {
        this.orderSourceSms = orderSourceSms;
    }

    public Integer getOrderSourceColink()
    {
        return orderSourceColink;
    }

    public void setOrderSourceColink(Integer orderSourceColink)
    {
        this.orderSourceColink = orderSourceColink;
    }

    public Integer getCommonentOrderNum()
    {
        return commonentOrderNum;
    }

    public void setCommonentOrderNum(Integer commonentOrderNum)
    {
        this.commonentOrderNum = commonentOrderNum;
    }

    public Integer getCommonentOrderGoodnum()
    {
        return commonentOrderGoodnum;
    }

    public void setCommonentOrderGoodnum(Integer commonentOrderGoodnum)
    {
        this.commonentOrderGoodnum = commonentOrderGoodnum;
    }

    public Integer getCommonentOrderGetscore()
    {
        return commonentOrderGetscore;
    }

    public void setCommonentOrderGetscore(Integer commonentOrderGetscore)
    {
        this.commonentOrderGetscore = commonentOrderGetscore;
    }
    
  
    
  
}
