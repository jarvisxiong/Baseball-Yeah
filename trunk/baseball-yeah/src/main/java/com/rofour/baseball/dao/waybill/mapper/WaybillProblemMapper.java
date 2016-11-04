package com.rofour.baseball.dao.waybill.mapper;

import javax.inject.Named;

import com.rofour.baseball.dao.waybill.bean.WaybillProblemBean;

/**
 * 异常件
 * 
 * @author wuzhiquan
 *
 */
@Named("waybillProblemMapper")
public interface WaybillProblemMapper {

    /**
     * 查询异常件
     * 
     * @param orderId
     * @return
     * @author wuzhiquan
     */
    WaybillProblemBean getWaybillProblem(Long orderId);

    /**
     * 删除异常件
     * 
     * @param problemId
     * @return
     * @author wuzhiquan
     */
    int deleteWaybillProblem(Long problemId);
}
