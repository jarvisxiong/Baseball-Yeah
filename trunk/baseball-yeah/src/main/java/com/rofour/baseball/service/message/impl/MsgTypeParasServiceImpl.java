/**
 * Copyright (c) 2016, 指端科技.
 */
package com.rofour.baseball.service.message.impl;

import com.rofour.baseball.controller.model.message.MsgTypeParasInfo;
import com.rofour.baseball.dao.message.bean.MsgTypeParasBean;
import com.rofour.baseball.dao.message.mapper.MsgTypeParasMapper;
import com.rofour.baseball.service.message.MsgTypeParasService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: MsgTypeParasServiceImpl
 * @Description: 消息类型属性服务实现
 * @author: xulang
 * @date: 2016年09月02日 10:57
 */
@Service("msgTypeParasService")
public class MsgTypeParasServiceImpl implements MsgTypeParasService {

    @Autowired
    @Qualifier("msgTypeParasMapper")
    private MsgTypeParasMapper msgTypeParasMapper;

    /**
     * 按主键删除
     *
     * @param parasId
     * @return
     */
    @Override
    public int deleteById(Long parasId) {
        MsgTypeParasBean bean = new MsgTypeParasBean();
        bean.setParasId(parasId);
        bean.setState((byte) 0);
        return msgTypeParasMapper.updateByPrimaryKeySelective(bean);
    }

    /**
     * 新增
     *
     * @param record
     * @return
     */
    @Override
    public int insert(MsgTypeParasInfo record) {
        int result = 0;
        if (record != null) {
            MsgTypeParasBean bean = new MsgTypeParasBean();
            BeanUtils.copyProperties(record, bean);
            bean.setState((byte) 1);
            bean.setParasId(null);
            bean.setInsertTime(new Date());
            result = msgTypeParasMapper.insertSelective(bean);
        }
        return result;
    }

    /**
     * 按主键查询
     *
     * @param parasId
     * @return
     */
    @Override
    public MsgTypeParasInfo selectById(Long parasId) {
        MsgTypeParasBean dataBean = msgTypeParasMapper.selectByPrimaryKey(parasId);
        MsgTypeParasInfo temp = new MsgTypeParasInfo();
        if (dataBean != null) {
            BeanUtils.copyProperties(dataBean, temp);
        }
        return temp;
    }

    /**
     * 按消息类型查询
     *
     * @param msgType
     * @return
     */
    @Override
    public List<MsgTypeParasInfo> getAll(Integer msgType, Integer limit, Integer offset) {
        Map<String, Object> map = new HashMap<>();
        map.put("msgType", msgType);
        if (limit != null) {
            map.put("limit", limit);
            map.put("offset", offset);
        }
        List<MsgTypeParasBean> dataBeanList = msgTypeParasMapper.getAll(map);
        List<MsgTypeParasInfo> dataList = new ArrayList<MsgTypeParasInfo>();
        for (MsgTypeParasBean item : dataBeanList) {
            MsgTypeParasInfo temp = new MsgTypeParasInfo();
            BeanUtils.copyProperties(item, temp);
            dataList.add(temp);
        }
        return dataList;
    }

    /**
     * 按消息类型查询总数
     *
     * @param msgType
     * @return
     */
    @Override
    public int getAllCount(Integer msgType) {
        return msgTypeParasMapper.getAllCount(msgType);
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    public int updateById(MsgTypeParasInfo record) {
        int result = 0;
        if (record != null && record.getParasId() != null) {
            MsgTypeParasBean bean = new MsgTypeParasBean();
            BeanUtils.copyProperties(record, bean);
            bean.setInsertTime(new Date());
            result = msgTypeParasMapper.updateByPrimaryKeySelective(bean);
        }
        return result;
    }

    /**
     * 按条件查询总数
     *
     * @param parasCodeStr
     * @param msgType
     * @return
     */
    @Override
    public int queryCount(String parasCodeStr, Integer msgType) {
        Map<String, String> map = new HashMap<>();
        map.put("msgType", msgType.toString());
        map.put("parasCodeStr", parasCodeStr);
        return msgTypeParasMapper.queryCount(map);
    }
}
