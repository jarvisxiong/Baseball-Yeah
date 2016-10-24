package com.zhiduan.axp.dao.message.mapper;

import com.zhiduan.axp.dao.message.bean.MsgTypeBean;

import javax.inject.Named;
import java.util.List;

/**
 * 消息类型数据操作接口
 */
@Named("msgTypeMapper")
public interface MsgTypeMapper {

    /**
     * 根据主键物理删除
     * @param id 类型主键
     * @return
     */
    int deleteByPrimaryKey(Integer id);


    int insert(MsgTypeBean record);

    /***
     * 添加消息类型数据
     * @param record 消息类型数据
     * @return 消息类型主键
     */
    int insertSelective(MsgTypeBean record);

    MsgTypeBean selectByPrimaryKey(Integer id);

    /**
     * 更新消息类型
     * @param record
     * @return
     */
    void updateByPrimaryKeySelective(MsgTypeBean record);

    /**
     * 查询可用的所有消息类型
     * @return
     */
    List<MsgTypeBean> getAllList();


}