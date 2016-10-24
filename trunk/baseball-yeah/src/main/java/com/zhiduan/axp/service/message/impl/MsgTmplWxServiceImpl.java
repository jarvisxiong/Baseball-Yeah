/**
 * Copyright (c) 2016, 指端科技.
 */
package com.zhiduan.axp.service.message.impl;

import com.zhiduan.axp.controller.model.message.MsgTmplWxInfo;
import com.zhiduan.axp.dao.message.bean.MsgTmplWxBean;
import com.zhiduan.axp.dao.message.mapper.MsgTmplWxMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.message.MsgTmplWxService;
import com.zhiduan.axp.service.message.MsgTypeParasService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: MsgTmplWxServiceImpl
 * @Description: 微信模板操作服务实现
 * @author: xulang
 * @date: 2016年09月06日 13:23
 */
@Service("msgTmplWxService")
public class MsgTmplWxServiceImpl implements MsgTmplWxService {

    @Autowired
    @Qualifier("msgTmplWxMapper")
    private MsgTmplWxMapper msgTmplWxMapper;

    @Autowired
    @Qualifier("msgTypeParasService")
    private MsgTypeParasService msgTypeParasService;


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Integer id) {
        MsgTmplWxBean bean = new MsgTmplWxBean();
        bean.setId(id);
        bean.setState((byte) 0);
        return msgTmplWxMapper.updateByPrimaryKey(bean);
    }

    /**
     * 新增
     *
     * @param record
     * @return
     */
    @Override
    public int insert(MsgTmplWxInfo record) throws Exception {
        int result = 0;
        if (record != null) {
            MsgTmplWxBean bean = new MsgTmplWxBean();
            BeanUtils.copyProperties(record, bean);
            bean.setId(null);
            bean.setCreateTime(new Date());
            bean.setPlaceSign(getPlaceSign(record));
            result = msgTmplWxMapper.insertSelective(bean);
        }
        return result;
    }

    /**
     * 按主键查询
     *
     * @param id
     * @return
     */
    @Override
    public MsgTmplWxInfo selectByPrimaryKey(Integer id) {
        MsgTmplWxBean dataBean = msgTmplWxMapper.selectByPrimaryKey(id);
        MsgTmplWxInfo temp = new MsgTmplWxInfo();
        if (dataBean != null) {
            BeanUtils.copyProperties(dataBean, temp);
        }
        return temp;
    }

    /**
     * 按条件查询
     *
     * @param msgType
     * @param limit
     * @param offset
     * @return
     */
    @Override
    public List<MsgTmplWxInfo> selectAll(Integer msgType, Integer limit, Integer offset) {
        Map<String, Object> map = new HashMap<>();
        map.put("msgType", msgType);
        if (limit != null) {
            map.put("limit", limit);
            map.put("offset", offset);
        }
        List<MsgTmplWxBean> dataBeanList = msgTmplWxMapper.selectList(map);
        List<MsgTmplWxInfo> dataList = new ArrayList<MsgTmplWxInfo>();
        for (MsgTmplWxBean item : dataBeanList) {
            MsgTmplWxInfo temp = new MsgTmplWxInfo();
            BeanUtils.copyProperties(item, temp);
            dataList.add(temp);
        }
        return dataList;
    }

    /**
     * 按条件查询列表条数
     *
     * @param msgType
     * @return
     */
    @Override
    public int selectAllCount(Integer msgType) {
        return msgTmplWxMapper.selectAllCount(msgType);
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    public int update(MsgTmplWxInfo record) throws Exception {
        int result = 0;
        if (record != null && record.getId() != null) {
            MsgTmplWxBean bean = new MsgTmplWxBean();
            BeanUtils.copyProperties(record, bean);
            bean.setUpdateTime(new Date());
            bean.setPlaceSign(getPlaceSign(record));
            result = msgTmplWxMapper.updateByPrimaryKey(bean);
        }
        return result;
    }

    /**
     * 提取占位符
     *
     * @param record
     * @return
     */
    private String getPlaceSign(MsgTmplWxInfo record) throws Exception {
        StringBuffer result = new StringBuffer();
        Pattern p = Pattern.compile("&\\w*&");
        Matcher m = p.matcher(record.getTmplContent() + record.getWxDetailUrl());
        int count = 0;
        while (m.find()) {
            String holderStr = m.group().replaceAll("&", "").replaceAll(" ", "");
            if (result.indexOf(holderStr) < 0) {
                result.append(",").append(holderStr);
                count++;
            }
        }
        String rtnHolder = result.deleteCharAt(0).toString();
        int validParaCount = msgTypeParasService.queryCount(rtnHolder, record.getMsgType());
        if (validParaCount != count) {
            throw new BusinessException("09004");
        }
        return rtnHolder;
    }
}
