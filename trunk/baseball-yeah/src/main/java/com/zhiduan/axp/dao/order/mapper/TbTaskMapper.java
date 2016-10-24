package com.zhiduan.axp.dao.order.mapper;

import com.zhiduan.axp.dao.order.bean.TbTask;
import com.zhiduan.axp.dao.order.bean.TbTaskSub;
import com.zhiduan.axp.dao.user.bean.UserCheckBean;

import javax.inject.Named;
import java.util.List;

@Named("tbTaskMapper")
public interface TbTaskMapper {
    int deleteByPrimaryKey(Long taskId);

    int insert(TbTask record);

    int getTaskSubCount(TbTask record);


    int insertSelective(TbTask record);

    TbTask selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(TbTask record);

    int updateTaskSub(TbTask record);

    int updateByPrimaryKey(TbTask record);

    List<TbTask> selectTask(TbTask tbTask);


    int selectTaskCount(TbTask tbTask);
}