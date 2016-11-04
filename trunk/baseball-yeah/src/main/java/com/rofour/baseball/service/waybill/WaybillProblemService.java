package com.rofour.baseball.service.waybill;

/**
 * 异常件
 * 
 * @author wuzhiquan
 *
 */
public interface WaybillProblemService {

    /**
     * 取消异常件
     * 
     * @param orderId
     * @param orderType
     * @throws Exception
     * @author wuzhiquan
     */
    void cancelWaybillProblem(Long orderId, String orderType) throws Exception;
}
