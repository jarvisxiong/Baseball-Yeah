package com.rofour.baseball.service.order;

import com.rofour.baseball.dao.order.bean.TbTask;
import com.rofour.baseball.dao.order.bean.TbTaskSub;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TbTaskSubService {

    List<TbTask> getTasks(TbTaskSub record);

    Integer getTasksCount(TbTaskSub record);

    int insert(TbTaskSub record);

    int deleteByPrimaryKey(Long taskSubId);

}
