package com.rofour.baseball.dao.manager.mapper;

import com.rofour.baseball.dao.manager.bean.MonitorBean;

import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MonitorMapper
 * @Description: 监控项数据库操作接口
 * @author: xulang
 * @Date: 2016-08-22 13:40
 */
@Named("monitorMapper")
public interface MonitorMapper {
    /**
     * 按主键删除
     *
     * @param monitorId
     * @return
     */
    int deleteByMonitorId(Long monitorId);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insertMonitor(MonitorBean record);

    /**
     * 按主键查询
     *
     * @param monitorId
     * @return
     */
    MonitorBean selectByPrimaryKey(Long monitorId);

    /**
     * 查询全部
     * @param map
     * @return
     */
    List<MonitorBean> selectAll(Map<String,Object> map);

    /**
     * 按主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(MonitorBean record);
}