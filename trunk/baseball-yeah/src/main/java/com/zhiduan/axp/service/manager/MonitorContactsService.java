package com.zhiduan.axp.service.manager;

import com.zhiduan.axp.controller.model.manager.MonitorContactsInfo;
import com.zhiduan.axp.dao.manager.bean.MonitorContactsBean;

import java.util.List;

/**
 * @ClassName: MonitorContactsService
 * @Description: 监控联系人服务接口
 * @author: xulang
 * @Date: 2016-08-22 13:25
 */
public interface MonitorContactsService {
    /**
     * 删除
     *
     * @param monitorId
     * @return
     */
    int deleteByMonitorId(Long monitorId);

    /**
     * 新增
     *
     * @param monitorContactsBeanList
     * @return
     */
    int addMonitorContacts(List<MonitorContactsInfo> monitorContactsBeanList);

    /**
     * 按monitorId查询
     *
     * @param monitorId
     * @return
     */
    List<MonitorContactsInfo> selectByMonitorId(Long monitorId);

}