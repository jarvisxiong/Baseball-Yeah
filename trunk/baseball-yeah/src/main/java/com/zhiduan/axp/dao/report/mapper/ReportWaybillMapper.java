/**
 * @Title: ReportWaybillMapper.java
 * @Package com.zhiduan.axp.idl.reportcenter.dao
 * @Project: axp-idl
 * @Description: (用一句话描述该文件做什么)
 * @author heyi
 * @date 2016年4月18日 下午5:38:35
 * @version V1.0
 * ──────────────────────────────────
 * Copyright (c) 2016, 指端科技.
 */


package com.zhiduan.axp.dao.report.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.controller.model.WayBillLogInfo;
import com.zhiduan.axp.controller.model.report.ReportCityPhoneInfo;
import com.zhiduan.axp.dao.report.bean.SearchPhoneCountBean;


/**
 * @ClassName: ReportWaybillMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author heyi
 * @date 2016年4月18日 下午5:38:35
 *
 */
@Named("reportWaybillMapper")
public interface ReportWaybillMapper {
    /**
     *
     * @Method: getPhoneCount
     * @Description: TODO(统计学校运单手机号)
     * @param @return    参数
     * @return List<SearchPhoneCount>    返回类型
     * @throws
     * @author heyi
     * @date 2016年4月15日 下午4:23:42 
     *
     */
    List<SearchPhoneCountBean> getPhoneCount(SearchPhoneCountBean bean);

    /**
     *
     * @Method: getPhoneDetailCount
     * @Description: 学校手机号统计明细
     * @param @param bean
     * @param @return    参数
     * @return List<SearchPhoneCountBean>    返回类型
     * @throws
     * @author heyi
     * @date 2016年4月18日 上午10:22:52 
     *
     */
    List<SearchPhoneCountBean> getPhoneDetailCount(SearchPhoneCountBean bean);

    /**
     *
     * @Method: getPhoneAllCount
     * @Description:学校手机号统计总数
     * @param @return    参数
     * @return List<SearchPhoneCountBean>    返回类型
     * @throws
     * @author heyi
     * @date 2016年4月18日 上午10:23:16 
     *
     */
    List<SearchPhoneCountBean> getPhoneAllCount();

    List<Map<String, Object>> getSqlDataList(Map<String, Object> map);
    
    List<ReportCityPhoneInfo> selectCityPhoneCount(Map<String,Object> map);
    
    /**
     * 
     * @Method: selectWayBillLogInfo
     * @Description: 运单记录统计
     * @param @param info
     * @param @return    参数
     * @return List<WayBillLogInfo>    返回类型
     * @throws
     * @author heyi
     * @date 2016年6月20日 下午4:32:27 
     *
     */
    List<WayBillLogInfo> selectWayBillLogInfo(WayBillLogInfo info);
    int selectWayBillLogTotalCount(WayBillLogInfo info);
}
