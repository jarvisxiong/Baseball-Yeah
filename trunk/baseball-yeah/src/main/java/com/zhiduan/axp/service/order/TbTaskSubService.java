package com.zhiduan.axp.service.order;

import com.zhiduan.axp.dao.order.bean.TbTask;
import com.zhiduan.axp.dao.order.bean.TbTaskSub;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TbTaskSubService {

    List<TbTask> getTasks(TbTaskSub record);

    Integer getTasksCount(TbTaskSub record);

    int insert(TbTaskSub record);

    int deleteByPrimaryKey(Long taskSubId);

}
