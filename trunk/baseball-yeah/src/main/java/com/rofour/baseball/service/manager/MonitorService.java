package com.rofour.baseball.service.manager;

import com.rofour.baseball.controller.model.manager.MonitorInfo;
import com.rofour.baseball.dao.manager.bean.MonitorBean;

import java.util.List;

/**
 * @ClassName: MonitorService
 * @Description: 监控项服务接口
 * @author: xulang
 * @Date: 2016-08-22 13:40
 */
public interface MonitorService {
    /**
     * 按主键删除
     *
     * @param monitorId
     * @return
     */
    int deleteById(Long monitorId);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int addMonitor(MonitorInfo record);

    /**
     * 按主键查询
     *
     * @param monitorId
     * @return
     */
    MonitorInfo selectById(Long monitorId);

    /**
     * 查询全部
     * @param monitorName
     * @return
     */
    List<MonitorInfo> selectAll(String monitorName);

    /**
     * 按主键更新
     *
     * @param record
     * @return
     */
    int updateMonitor(MonitorInfo record);
}