package com.zhiduan.axp.service.message;

import com.zhiduan.axp.controller.model.message.MsgTypeInfo;

import java.util.List;

/**
 * 消息类型服务
 * Created by wny on 2016-09-01.
 */
public interface MsgTypeService {

    /**
     * 获取所有消息类型列表
     * @return
     */
    public List<MsgTypeInfo> getAllData();

    /**
     * 添加消息类型
     * @param info
     * @return 主键
     */
    public int addInfo(MsgTypeInfo info);

    /**
     *更新消息类型
     * @param info
     * @return 成功  or 失败
     */
    public  boolean updateInfo(MsgTypeInfo info);

    /**
     * 删除消息类型 （逻辑删除）
     * @param id 消息类型id
     * @return 成功失败
     */
    public  boolean delInfo(int id);

}
