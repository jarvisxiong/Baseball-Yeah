package com.rofour.baseball.dao.order.mapper;


import com.rofour.baseball.controller.model.order.DoOrderTaskDetailInfo;
import com.rofour.baseball.controller.model.order.TbSysTaskOrderInfo;
import com.rofour.baseball.dao.order.bean.DoOrderTaskResultBean;
import com.rofour.baseball.dao.order.bean.DoOrderTaskSearchBean;
import com.rofour.baseball.dao.order.bean.TbSysTaskOrder;

import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named("tbSysTaskOrderMapper")
public interface TbSysTaskOrderMapper {
    int insert(TbSysTaskOrderInfo record);


    List<DoOrderTaskResultBean> selectDoOrderTaskListByParams(DoOrderTaskSearchBean bean);

    DoOrderTaskDetailInfo selectDoOrderTaskDetail(Map<String,String> map);

    int updateAuditResult(TbSysTaskOrderInfo info);

    int updateOrderStatus(Map<String,Object> map);

    Long selectTotalPrice(Long orderId);

    Integer checkIsAudit(Long orderId);

    int checkTaskExist(Long taskSubId);

    TbSysTaskOrderInfo selectSysTaskOrder(Long taskSubId);

    List<Long> selectOverallScore(Long orderId);

    int selectDoOrderTaskCount(DoOrderTaskSearchBean bean);

    int checkWaitAudit(Long orderId);
 }