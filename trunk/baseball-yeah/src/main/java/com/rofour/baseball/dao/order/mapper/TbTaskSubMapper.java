package com.rofour.baseball.dao.order.mapper;


import java.util.List;
import com.rofour.baseball.dao.order.bean.TbTaskSub;

import javax.inject.Named;

@Named("tbTaskSubMapper")
public interface TbTaskSubMapper {
    int deleteByPrimaryKey(Long taskSubId);
    int stopByPrimaryKey(Long taskSubId);

    int insert(TbTaskSub record);

    int insertSelective(TbTaskSub record);

    TbTaskSub selectByPrimaryKey(Long taskSubId);

    List<TbTaskSub> selectByTask(TbTaskSub record);

    List<TbTaskSub> selectByTaskAndCollege(TbTaskSub record);


    int updateByPrimaryKeySelective(TbTaskSub record);

    int updateByPrimaryKey(TbTaskSub record);
}