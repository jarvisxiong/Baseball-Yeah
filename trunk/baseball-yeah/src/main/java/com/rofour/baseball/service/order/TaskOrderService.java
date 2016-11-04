package com.rofour.baseball.service.order;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.order.DoOrderTaskDetailInfo;
import com.rofour.baseball.controller.model.order.DoOrderTaskResultInfo;
import com.rofour.baseball.controller.model.order.DoOrderTaskSearchInfo;
import com.rofour.baseball.controller.model.order.TbSysTaskOrderInfo;
import com.rofour.baseball.dao.order.bean.TbTask;
import com.rofour.baseball.dao.order.bean.TbTaskSub;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TaskOrderService {
    List<TbTask> getTasks(TbTask tbTask);
    TbTask getDto(TbTask tbTask);
    Integer getTasksCount(TbTask tbTask);
    Integer del(TbTask tbTask,HttpServletRequest re);
    /**
     * 任务停止
     * @param bean
     * @param re
     * @return
     */
    Integer delSub(TbTaskSub bean,HttpServletRequest re);

    /**
     * 任务停止
     * @param bean
     * @param re
     * @return
     */
    Integer stopSub(TbTaskSub bean,HttpServletRequest re);
    Integer pubSub(TbTaskSub bean,HttpServletRequest re);
    List<TbTaskSub> getTaskSubs(TbTaskSub tbTaskSub);

    ResultInfo<Object> insert (TbTask tbTask,HttpServletRequest re);
    ResultInfo<Object> insertSub (TbTaskSub tbtaskSub,HttpServletRequest re);
    ResultInfo<Object> updateSub (TbTaskSub tbtaskSub,HttpServletRequest re);

    ResultInfo<Object> update (TbTask tbTask,HttpServletRequest re);

    List<DoOrderTaskResultInfo> getDoOrderTaskList(DoOrderTaskSearchInfo info) throws Exception;

    DoOrderTaskDetailInfo getDoOrderTaskDetail(String orderId) throws Exception;

    /**
     * 保存结果
     * @param info
     * @return
     */
    ResultInfo saveAuditResult(TbSysTaskOrderInfo info) throws Exception;

    /**
     * 提交审核结果并评分
     * @return
     */
    ResultInfo submitAuditResult(TbSysTaskOrderInfo info, HttpServletRequest request);

    /**
     * 获取做单任务列表数目
     * @param info
     * @return
     */
    int selectDoOrderTaskCount(DoOrderTaskSearchInfo info) throws  Exception;
}
