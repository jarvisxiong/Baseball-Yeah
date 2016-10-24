/**
 * Copyright (c) 2016, 指端科技.
 */
package com.zhiduan.axp.service.message;

import com.zhiduan.axp.controller.model.message.MsgTypeParasInfo;

import java.util.List;

/**
 * @ClassName: MsgTypeParasService
 * @Description: 消息类型属性服务
 * @author: xulang
 * @date: 2016年09月02日 10:54
 */

public interface MsgTypeParasService {
    /**
     * 按主键删除
     *
     * @param parasId
     * @return
     */
    int deleteById(Long parasId);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(MsgTypeParasInfo record);

    /**
     * 按主键查询
     *
     * @param parasId
     * @return
     */
    MsgTypeParasInfo selectById(Long parasId);

    /**
     * 按消息类型查询
     *
     * @param msgType
     * @return
     */
    List<MsgTypeParasInfo> getAll(Integer msgType, Integer limit, Integer offset);

    /**
     * 按消息类型查询总数
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
    int updateById(MsgTypeParasInfo record);

    /**
     * 查询参数数量
     *
     * @param parasCodeStr
     * @param msgType
     * @return
     */
    int queryCount(String parasCodeStr, Integer msgType);
}
