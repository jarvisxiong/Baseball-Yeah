package com.rofour.baseball.dao.waybill.mapper;

import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.controller.model.waybill.WaybillLogInfo;
import com.rofour.baseball.dao.waybill.bean.WaybillBean;

/**
 * 运单
 * 
 * @author wuzhiquan
 *
 */
@Named("waybillMapper")
public interface WaybillMapper {

    /**
     * 查询运单
     * 
     * @param waybillId
     * @return
     * @author wuzhiquan
     */
    WaybillBean getWaybill(Long waybillId);

    /**
     * 更新运单状态
     * 
     * @param map
     * @return
     * @author wuzhiquan
     */
    int updateWaybillState(Map<String, Object> map);

    /**
     * 插入运单日志
     * 
     * @param waybillLogInfo
     * @return
     * @author wuzhiquan
     */
    int addWaybillLog(WaybillLogInfo waybillLogInfo);
}
