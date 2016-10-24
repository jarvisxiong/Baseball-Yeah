/**
 * Copyright (c) 2016, 指端科技.
 */
package com.zhiduan.axp.service.manager.impl;

import com.zhiduan.axp.common.AxpUtils;
import com.zhiduan.axp.controller.model.manager.MonitorContactsInfo;
import com.zhiduan.axp.controller.model.manager.MonitorInfo;
import com.zhiduan.axp.dao.manager.bean.MonitorBean;
import com.zhiduan.axp.dao.manager.mapper.MonitorMapper;
import com.zhiduan.axp.service.manager.MonitorContactsService;
import com.zhiduan.axp.service.manager.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MonitorImpl
 * @Description: 监控项服务实现
 * @author: xulang
 * @date: 2016年08月22日 16:10
 */
@Service("monitorService")
public class MonitorImpl implements MonitorService {

    @Autowired
    @Qualifier("monitorMapper")
    private MonitorMapper monitorMapper;

    @Autowired
    @Qualifier("monitorContactsService")
    private MonitorContactsService monitorContactsService;

    /**
     * 按主键删除
     *
     * @param monitorId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public int deleteById(Long monitorId) {
        monitorContactsService.deleteByMonitorId(monitorId);
        return monitorMapper.deleteByMonitorId(monitorId);
    }

    /**
     * 新增
     *
     * @param record
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public int addMonitor(MonitorInfo record) {
        MonitorBean bean = new MonitorBean();
        AxpUtils.copyProperties(record, bean);
        int rtn = monitorMapper.insertMonitor(bean);
        for (MonitorContactsInfo info : record.getMonitorContactsInfoList()) {
            info.setMonitorId(bean.getMonitorId());
        }
        monitorContactsService.addMonitorContacts(record.getMonitorContactsInfoList());
        return rtn;
    }

    /**
     * 按主键查询
     *
     * @param monitorId
     * @return
     */
    @Override
    public MonitorInfo selectById(Long monitorId) {
        MonitorBean bean = monitorMapper.selectByPrimaryKey(monitorId);
        MonitorInfo info = new MonitorInfo();
        if (bean != null) {
            AxpUtils.copyProperties(bean, info);
        }
        return info;
    }

    /**
     * 查询列表
     *
     * @return
     */
    @Override
    public List<MonitorInfo> selectAll(String monitorName) {
        List<MonitorInfo> infos = new ArrayList<MonitorInfo>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("monitorName", monitorName);
        List<MonitorBean> beanList = monitorMapper.selectAll(map);
        if (beanList.size() > 0) {
            for (MonitorBean bean : beanList) {
                MonitorInfo info = new MonitorInfo();
                AxpUtils.copyProperties(bean, info);
                infos.add(info);
            }
        }
        return infos;
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public int updateMonitor(MonitorInfo record) {
        MonitorBean bean = new MonitorBean();
        AxpUtils.copyProperties(record, bean);
        monitorContactsService.deleteByMonitorId(record.getMonitorId());
        for (MonitorContactsInfo info : record.getMonitorContactsInfoList()) {
            info.setMonitorId(record.getMonitorId());
        }
        monitorContactsService.addMonitorContacts(record.getMonitorContactsInfoList());
        return monitorMapper.updateByPrimaryKey(bean);
    }
}
