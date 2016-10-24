package com.zhiduan.axp.service.report.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiduan.axp.controller.model.report.ReportUserSmsModelInfo;
import com.zhiduan.axp.dao.report.bean.ReportUserSmsModelBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.controller.model.report.ReportStoreSmsInfo;
import com.zhiduan.axp.controller.model.report.SearchStoreSmsDetailInfo;
import com.zhiduan.axp.controller.model.report.SearchStoreSmsTotalInfo;
import com.zhiduan.axp.dao.report.bean.ReportStoreSmsBean;
import com.zhiduan.axp.dao.report.bean.SearchStoreSmsDetailBean;
import com.zhiduan.axp.dao.report.bean.SearchStoreSmsTotalBean;
import com.zhiduan.axp.dao.report.mapper.ReportStoreSmsMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.report.ReportStoreSmsService;

/**
 * @author ZXY
 * @ClassName: ReportStoreSmsImpl
 * @Description: 货源短信日报表服务实现
 * @date 2016/4/26 13:48
 */
@Service("reportStoreSmsService")
public class ReportStoreSmsImpl implements ReportStoreSmsService {

    @Autowired
    @Qualifier("reportStoreSmsMapper")
    private ReportStoreSmsMapper dao;

    /**
     * @param storeName
     * @param storeId
     * @param supervisor
     * @param expressId
     * @param startDate
     * @param endDate
     * @param status
     * @param limit
     * @param offset
     * @return
     * @throws Exception
     * @Description: 货源短信报表
     * @see com.zhiduan.axp.service.report.ReportStoreSmsService#getStoreSmsDayReport(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<ReportStoreSmsInfo> getStoreSmsDayReport(String storeName, String storeId, String supervisor,
                                                         String expressId, String startDate, String endDate, String status, Integer limit, Integer offset)
            throws Exception {
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
            // 查询日期不能为空
            throw new BusinessException("08001");
        }
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isBlank(storeName)) {
            map.put("storeName", storeName);
        }
        if (!StringUtils.isBlank(storeId)) {
            map.put("storeId", storeId);
        }
        if (!StringUtils.isBlank(supervisor)) {
            map.put("supervisor", supervisor);
        }
        if (!StringUtils.isBlank(expressId)) {
            map.put("expressId", expressId);
        }
        if (!StringUtils.isBlank(status)) {
            map.put("status", status);
        }
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("limit", limit);
        map.put("offset", offset);
        List<ReportStoreSmsBean> smsBeans = dao.getStoreSmsDayReport(map);
        List<ReportStoreSmsInfo> smsInfos = new ArrayList<ReportStoreSmsInfo>();
        ReportStoreSmsInfo info;
        for (ReportStoreSmsBean smsBean : smsBeans) {
            info = new ReportStoreSmsInfo();
            BeanUtils.copyProperties(smsBean, info);
            smsInfos.add(info);
        }
        return smsInfos;
    }

    public int getStoreSmsReportCount(String storeName, String storeId, String supervisor, String expressId, String startDate,
                                      String endDate, String status) {
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
            // 查询日期不能为空
            throw new BusinessException("08001");
        }
        Map<String, String> map = new HashMap<>();
        if (!StringUtils.isBlank(storeName)) {
            map.put("siteId", storeName);
        }
        if (!StringUtils.isBlank(storeId)) {
            map.put("storeId", storeId);
        }
        if (!StringUtils.isBlank(supervisor)) {
            map.put("supervisor", supervisor);
        }
        if (!StringUtils.isBlank(expressId)) {
            map.put("expressId", expressId);
        }
        if (!StringUtils.isBlank(status)) {
            map.put("status", status);
        }
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        int totalCount = dao.getStoreSmsReportCount(map);
        return totalCount;
    }

    /**
     * @param storeId
     * @param startDate
     * @param endDate
     * @return
     * @Description: 短信报表汇总
     * @see com.zhiduan.axp.service.report.ReportStoreSmsService#selectStoreSmsTotalInfo(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public List<SearchStoreSmsTotalInfo> selectStoreSmsTotalInfo(String storeId, String startDate, String endDate)
            throws Exception {
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
            // 查询日期不能为空
            throw new BusinessException("08001");
        }
        Map<String, String> map = new HashMap<>();
        if (!StringUtils.isBlank(storeId)) {
            map.put("storeId", storeId);
        }
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        List<SearchStoreSmsTotalBean> smsBeans = dao.selectStoreSmsTotalInfo(map);
        List<SearchStoreSmsTotalInfo> smsInfos = new ArrayList<SearchStoreSmsTotalInfo>();
        SearchStoreSmsTotalInfo info;
        for (SearchStoreSmsTotalBean smsBean : smsBeans) {
            info = new SearchStoreSmsTotalInfo();
            BeanUtils.copyProperties(smsBean, info);
            smsInfos.add(info);
        }
        return smsInfos;
    }

    /**
     * @param storeId
     * @param startDate
     * @param endDate
     * @param status
     * @param beVirtual
     * @return
     * @Description: 短信统计--明细
     * @see com.zhiduan.axp.service.report.ReportStoreSmsService#searchStoreSmsDetails(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<SearchStoreSmsDetailInfo> searchStoreSmsDetails(String storeId, String startDate, String endDate,
                                                                String status, String beVirtual) throws Exception {
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
            // 查询日期不能为空
            throw new BusinessException("08001");
        }
        Map<String, String> map = new HashMap<>();
        if (!StringUtils.isBlank(storeId)) {
            map.put("storeId", storeId);
        }
        if (!StringUtils.isBlank(status)) {
            map.put("status", status);
        }
        if (!StringUtils.isBlank(beVirtual)) {
            map.put("beVirtual", beVirtual);
        }
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        List<SearchStoreSmsDetailBean> smsDetailBeans = dao.selectStoreSmsDetailInfo(map);
        List<SearchStoreSmsDetailInfo> smsDetailInfos = new ArrayList<SearchStoreSmsDetailInfo>();
        SearchStoreSmsDetailInfo info;
        for (SearchStoreSmsDetailBean smsBean : smsDetailBeans) {
            info = new SearchStoreSmsDetailInfo();
            BeanUtils.copyProperties(smsBean, info);
            smsDetailInfos.add(info);
        }
        return smsDetailInfos;
    }

    /**
     * 查询用户自定义短信模板
     *
     * @param storeId
     * @param userName
     * @param phone
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public List<ReportUserSmsModelInfo> searchUserSmsModelList(String storeId, String userName, String phone, String modelContent, Integer offset, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isBlank(storeId)) {
            map.put("storeId", storeId);
        }
        if (!StringUtils.isBlank(userName)) {
            map.put("userName", userName);
        }
        if (!StringUtils.isBlank(phone)) {
            map.put("phone", phone);
        }
        if (!StringUtils.isBlank(modelContent)) {
            map.put("modelContent", modelContent);
        }
        map.put("offset", offset);
        map.put("limit", limit);
        List<ReportUserSmsModelBean> userSmsModelBeens = dao.getuserSmsModelList(map);
        List<ReportUserSmsModelInfo> userSmsModelInfos = new ArrayList<ReportUserSmsModelInfo>();
        ReportUserSmsModelInfo info;
        for (ReportUserSmsModelBean smsBean : userSmsModelBeens) {
            info = new ReportUserSmsModelInfo();
            BeanUtils.copyProperties(smsBean, info);
            userSmsModelInfos.add(info);
        }
        return userSmsModelInfos;
    }

    /**
     * 查询用户自定义短信模板数量
     *
     * @param storeId
     * @param userName
     * @param phone
     * @return
     */
    @Override
    public int getUserSmsModelListCount(String storeId, String userName, String phone, String modelContent) {
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isBlank(storeId)) {
            map.put("storeId", storeId);
        }
        if (!StringUtils.isBlank(userName)) {
            map.put("userName", userName);
        }
        if (!StringUtils.isBlank(phone)) {
            map.put("phone", phone);
        }
        if (!StringUtils.isBlank(modelContent)) {
            map.put("modelContent", modelContent);
        }
        return dao.getuserSmsModelListCount(map);
    }
}
