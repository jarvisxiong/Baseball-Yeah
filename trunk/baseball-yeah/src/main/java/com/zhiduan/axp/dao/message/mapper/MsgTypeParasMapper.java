package com.zhiduan.axp.dao.message.mapper;

import com.zhiduan.axp.dao.message.bean.MsgTypeParasBean;

import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MsgTypeParasMapper
 * @Description: 消息类型属性数据库操作接口
 * @author: xulang
 * @Date: 2016-09-02 10:45
 */
@Named("msgTypeParasMapper")
public interface MsgTypeParasMapper {
    /**
     * 按主键删除
     *
     * @param parasId
     * @return
     */
    int deleteByPrimaryKey(Long parasId);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insertSelective(MsgTypeParasBean record);

    /**
     * 按主键查询
     *
     * @param parasId
     * @return
     */
    MsgTypeParasBean selectByPrimaryKey(Long parasId);

    /**
     * 按消息类型查询
     *
     * @param map
     * @return
     */
    List<MsgTypeParasBean> getAll(Map<String, Object> map);

    /**
     * 按消息类型查询属性总数
     *
     * @param msgType
     * @return
     */
    int getAllCount(Integer msgType);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MsgTypeParasBean record);

    /**
     * 按条件查询属性条数
     *
     * @param map
     * @return
     */
    int queryCount(Map<String, String> map);

}