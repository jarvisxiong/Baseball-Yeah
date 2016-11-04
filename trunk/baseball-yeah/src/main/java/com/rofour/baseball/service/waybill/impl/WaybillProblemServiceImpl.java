package com.rofour.baseball.service.waybill.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rofour.baseball.controller.model.waybill.WaybillLogInfo;
import com.rofour.baseball.dao.waybill.bean.WaybillBean;
import com.rofour.baseball.dao.waybill.bean.WaybillProblemBean;
import com.rofour.baseball.dao.waybill.mapper.WaybillMapper;
import com.rofour.baseball.dao.waybill.mapper.WaybillProblemMapper;
import com.rofour.baseball.service.waybill.WaybillProblemService;

/**
 * 异常件
 * 
 * @author wuzhiquan
 *
 */
@Service("waybillProblemService")
public class WaybillProblemServiceImpl implements WaybillProblemService {
    /**
     * 订单类型：众包类型
     */
    private static final String TYPE_PACKET = "packet";
    /**
     * 0: 非问题件
     */
    private static final int PROBLEM_STATE_NORMAL = 0;
    /**
     * 运单状态：04众包取件
     */
    private static final String WAYBILL_STATE_DELEVITY = "04";
    /**
     * 订单侧问题件
     */
    private static final String STATE_QUESTION_BILL = "08";

    @Autowired
    @Qualifier("waybillProblemMapper")
    private WaybillProblemMapper waybillProblemMapper;

    @Autowired
    @Qualifier("waybillMapper")
    private WaybillMapper waybillMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public void cancelWaybillProblem(Long orderId, String orderType) throws Exception {
        // 查询异常件
        WaybillProblemBean waybillProblemBean = waybillProblemMapper.getWaybillProblem(orderId);

        if (null != waybillProblemBean) {
            if (TYPE_PACKET.equals(orderType)) {
                // 查询运单
                WaybillBean waybillBean = waybillMapper.getWaybill(waybillProblemBean.getWaybillId());

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("waybillId", waybillProblemBean.getWaybillId());
                map.put("problemState", PROBLEM_STATE_NORMAL);
                map.put("state", WAYBILL_STATE_DELEVITY);
                // 更新运单状态
                int result = waybillMapper.updateWaybillState(map);

                if (result > 0) {
                    // 更新运单日志
                    WaybillLogInfo waybillLogInfo = new WaybillLogInfo();
                    waybillLogInfo.setWaybillNo(waybillBean.getWaybillNo());
                    waybillLogInfo.setOperation(STATE_QUESTION_BILL);
                    waybillLogInfo.setContent("取消异常件");
                    waybillLogInfo.setLogTime(new Date());
                    waybillLogInfo.setExpressCompanyId(waybillBean.getExpressCompanyId());
                    // 插入运单日志
                    waybillMapper.addWaybillLog(waybillLogInfo);
                }
            }

            // 删除异常件
            waybillProblemMapper.deleteWaybillProblem(waybillProblemBean.getProblemId());
        }
    }
}
