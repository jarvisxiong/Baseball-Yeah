/**
 * Copyright (c) 2016, 指端科技.
 */
package com.rofour.baseball.service.manager.impl;

import com.rofour.baseball.common.AxpUtils;
import com.rofour.baseball.common.Tools;
import com.rofour.baseball.controller.model.manager.MonitorContactsInfo;
import com.rofour.baseball.dao.manager.bean.MonitorContactsBean;
import com.rofour.baseball.dao.manager.mapper.MonitorContactsMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.MonitorContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MonitorContactsImpl
 * @Description: 监控联系人服务实现
 * @author: xulang
 * @date: 2016年08月22日 15:56
 */
@Service("monitorContactsService")
public class MonitorContactsImpl implements MonitorContactsService {

    @Autowired
    @Qualifier("monitorContactsMapper")
    private MonitorContactsMapper monitorContactsMapper;

    /**
     * 删除
     *
     * @param monitorId
     * @return
     */
    @Override
    public int deleteByMonitorId(Long monitorId) {
        return monitorContactsMapper.deleteByMonitorId(monitorId);
    }

    /**
     * 批量增加
     *
     * @param monitorContactsInfoList
     * @return
     */
    @Override
    public int addMonitorContacts(List<MonitorContactsInfo> monitorContactsInfoList) {
        List<MonitorContactsBean> beanList = new ArrayList<MonitorContactsBean>();
        if (monitorContactsInfoList.size() > 0) {
            for (MonitorContactsInfo info : monitorContactsInfoList) {
                MonitorContactsBean bean = new MonitorContactsBean();
                AxpUtils.copyProperties(info, bean);
                beanList.add(bean);
            }
            return monitorContactsMapper.insert(beanList);
        }
        return 0;
    }

    /**
     * 查询列表
     *
     * @param monitorId
     * @return
     */
    @Override
    public List<MonitorContactsInfo> selectByMonitorId(Long monitorId) {
        List<MonitorContactsInfo> infos = new ArrayList<MonitorContactsInfo>();
        List<MonitorContactsBean> beans = monitorContactsMapper.selectByMonitorId(monitorId);
        if (beans.size() > 0) {
            for (MonitorContactsBean bean : beans) {
                MonitorContactsInfo info = new MonitorContactsInfo();
                AxpUtils.copyProperties(bean, info);
                infos.add(info);
            }
        }
        return infos;
    }
}
