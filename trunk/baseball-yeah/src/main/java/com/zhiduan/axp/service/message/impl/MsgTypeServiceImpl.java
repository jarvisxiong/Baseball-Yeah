package com.zhiduan.axp.service.message.impl;

import com.zhiduan.axp.controller.model.message.MsgTypeInfo;
import com.zhiduan.axp.dao.message.bean.MsgTypeBean;
import com.zhiduan.axp.dao.message.mapper.MsgTypeMapper;
import com.zhiduan.axp.service.message.MsgTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息类型服务实现
 * Created by wny on 2016-09-01.
 */
@Service("msgTypeService")
public class MsgTypeServiceImpl implements MsgTypeService {

    @Autowired
    @Qualifier("msgTypeMapper")
    private MsgTypeMapper smsMapper;

    /**
     * 获取所有消息类型列表
     *
     * @return
     */
    @Override
    public List<MsgTypeInfo> getAllData() {

        List<MsgTypeBean> dataBeanList = smsMapper.getAllList();
        List<MsgTypeInfo> dataList = new ArrayList<>();
        for (MsgTypeBean item : dataBeanList
                ) {
            MsgTypeInfo temp = new MsgTypeInfo();
            BeanUtils.copyProperties(item, temp);
            dataList.add(temp);
        }
        return dataList;
    }

    /**
     * 添加消息类型
     *
     * @param info
     * @return 主键
     */
    @Override
    public int addInfo(MsgTypeInfo info) {

        int result = 0;
        if (info != null) {
            MsgTypeBean bean = new MsgTypeBean();
            BeanUtils.copyProperties(info, bean);
            if(bean.getParentType()!=null)
            {
                bean.setTypeLevel((byte)2);
            }
            else
            {
                bean.setTypeLevel((byte)1);
            }
            info.setState((byte) 1);
            info.setId(null);
            result = smsMapper.insertSelective(bean);
        }

        return result;
    }

    /**
     * 更新消息类型
     *
     * @param info
     * @return 成功  or 失败
     */
    @Override
    public boolean updateInfo(MsgTypeInfo info) {
        boolean result = false;
        if (info != null && info.getId() != null) {
            MsgTypeBean bean = new MsgTypeBean();
            BeanUtils.copyProperties(info, bean);
            if(bean.getParentType()!=null)
            {
                bean.setTypeLevel((byte)2);
            }
            else
            {
                bean.setTypeLevel((byte)1);
            }
            smsMapper.updateByPrimaryKeySelective(bean);
            result = true;
        }

        return result;
    }

    /**
     * 删除消息类型 （逻辑删除）
     *
     * @param id 消息类型id
     * @return 成功失败
     */
    @Override
    public boolean delInfo(int id) {
        boolean result = false;
        if (id > 0) {
            MsgTypeBean bean = new MsgTypeBean();
            bean.setId(id);
            bean.setState((byte) 0);
            smsMapper.updateByPrimaryKeySelective(bean);
            result = true;
        }

        return result;
    }
}
