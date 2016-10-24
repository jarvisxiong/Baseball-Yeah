/**
 * Copyright (c) 2016, 指端科技.
 */
package com.zhiduan.axp.service.message.impl;

import com.zhiduan.axp.controller.model.message.MsgTmplMymsgInfo;
import com.zhiduan.axp.controller.model.message.MsgTmplWxInfo;
import com.zhiduan.axp.dao.message.bean.MsgTmplMymsgBean;
import com.zhiduan.axp.dao.message.bean.MsgTmplWxBean;
import com.zhiduan.axp.dao.message.mapper.MsgTmplMymsgMapper;
import com.zhiduan.axp.dao.message.mapper.MsgTmplWxMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.message.MsgTmplMymsgService;
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
 * @ClassName: MsgTmplMymsgServiceImpl
 * @Description: 用户消息模板操作服务实现
 * @author: xulang
 * @date: 2016年09月06日 13:23
 */
@Service("msgTmplMymsgService")
public class MsgTmplMymsgServiceImpl implements MsgTmplMymsgService {

    @Autowired
    @Qualifier("msgTmplMymsgMapper")
    private MsgTmplMymsgMapper msgTmplMymsgMapper;

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
        MsgTmplMymsgBean bean = new MsgTmplMymsgBean();
        bean.setId(id);
        bean.setState((byte) 0);
        return msgTmplMymsgMapper.updateByPrimaryKey(bean);
    }

    /**
     * 新增
     *
     * @param record
     * @return
     */
    @Override
    public int insert(MsgTmplMymsgInfo record) throws Exception {
        int result = 0;
        if (record != null) {
            MsgTmplMymsgBean bean = new MsgTmplMymsgBean();
            BeanUtils.copyProperties(record, bean);
            bean.setId(null);
            bean.setCreateTime(new Date());
            bean.setPlaceSign(getPlaceSign(record));
            result = msgTmplMymsgMapper.insertSelective(bean);
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
    public MsgTmplMymsgInfo selectByPrimaryKey(Integer id) {
        MsgTmplMymsgBean dataBean = msgTmplMymsgMapper.selectByPrimaryKey(id);
        MsgTmplMymsgInfo temp = new MsgTmplMymsgInfo();
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
    public List<MsgTmplMymsgInfo> selectAll(Integer msgType, Integer limit, Integer offset) {
        Map<String, Object> map = new HashMap<>();
        map.put("msgType", msgType);
        if (limit != null) {
            map.put("limit", limit);
            map.put("offset", offset);
        }
        List<MsgTmplMymsgBean> dataBeanList = msgTmplMymsgMapper.selectList(map);
        List<MsgTmplMymsgInfo> dataList = new ArrayList<MsgTmplMymsgInfo>();
        for (MsgTmplMymsgBean item : dataBeanList) {
            MsgTmplMymsgInfo temp = new MsgTmplMymsgInfo();
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
        return msgTmplMymsgMapper.selectAllCount(msgType);
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    public int update(MsgTmplMymsgInfo record) throws Exception {
        int result = 0;
        if (record != null && record.getId() != null) {
            MsgTmplMymsgBean bean = new MsgTmplMymsgBean();
            BeanUtils.copyProperties(record, bean);
            bean.setUpdateTime(new Date());
            bean.setPlaceSign(getPlaceSign(record));
            result = msgTmplMymsgMapper.updateByPrimaryKey(bean);
        }
        return result;
    }

    /**
     * 提取占位符
     *
     * @param record
     * @return
     */
    private String getPlaceSign(MsgTmplMymsgInfo record) throws Exception {
        StringBuffer result = new StringBuffer();
        Pattern p = Pattern.compile("&\\w*&");
        Matcher m = p.matcher(record.getTmplContent() + record.getExtendContent());
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
