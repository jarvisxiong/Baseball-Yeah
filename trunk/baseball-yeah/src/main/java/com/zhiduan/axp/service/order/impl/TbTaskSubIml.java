package com.zhiduan.axp.service.order.impl;

import com.zhiduan.axp.dao.order.bean.TbTask;
import com.zhiduan.axp.dao.order.bean.TbTaskSub;
import com.zhiduan.axp.dao.order.mapper.TbTaskSubMapper;
import com.zhiduan.axp.service.order.TbTaskSubService;
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
