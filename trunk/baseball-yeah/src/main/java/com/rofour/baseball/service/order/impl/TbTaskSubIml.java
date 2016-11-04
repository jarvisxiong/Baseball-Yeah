package com.rofour.baseball.service.order.impl;

import com.rofour.baseball.dao.order.bean.TbTask;
import com.rofour.baseball.dao.order.bean.TbTaskSub;
import com.rofour.baseball.dao.order.mapper.TbTaskSubMapper;
import com.rofour.baseball.service.order.TbTaskSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class TbTaskSubIml implements TbTaskSubService {

    @Autowired
    @Qualifier("tbTaskSubMapper")
    TbTaskSubMapper  tbTaskSubMapper;

    @Override
    public int deleteByPrimaryKey(Long taskSubId) {
        return 0;
    }

    @Override
    public List<TbTask> getTasks(TbTaskSub record) {
        return null;
    }

    @Override
    public Integer getTasksCount(TbTaskSub record) {
        return null;
    }

    @Override
    public int insert(TbTaskSub record) {
        return 0;
    }
}
