package com.zhiduan.axp.dao.manager.mapper;

import com.zhiduan.axp.dao.manager.bean.MonitorContactsBean;

import javax.inject.Named;
import java.util.List;

/**
 * @ClassName: MonitorContactsMapper
 * @Description: 监控联系人数据库操作接口
 * @author: xulang
 * @Date: 2016-08-22 13:25
 */
@Named("monitorContactsMapper")
public interface MonitorContactsMapper {
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
     * @param list
     * @return
     */
    int insert(List<MonitorContactsBean> list);

    /**
     * 查询
     *
     * @param monitorContactId
     * @return
     */
    MonitorContactsBean selectByPrimaryKey(Long monitorContactId);

    /**
     * 按monitorId查询
     *
     * @param monitorId
     * @return
     */
    List<MonitorContactsBean> selectByMonitorId(Long monitorId);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(MonitorContactsBean record);
}