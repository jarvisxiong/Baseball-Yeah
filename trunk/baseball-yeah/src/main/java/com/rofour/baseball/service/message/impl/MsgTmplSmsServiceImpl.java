/**
 * Copyright (c) 2016, 指端科技.
 */
package com.rofour.baseball.service.message.impl;

import com.rofour.baseball.controller.model.message.MsgTmplSmsInfo;
import com.rofour.baseball.controller.model.message.MsgTypeParasInfo;
import com.rofour.baseball.dao.message.bean.MsgTmplSmsBean;
import com.rofour.baseball.dao.message.bean.MsgTypeParasBean;
import com.rofour.baseball.dao.message.mapper.MsgTmplSmsMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.message.MsgTmplSmsService;
import com.rofour.baseball.service.message.MsgTypeParasService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: MsgTmplSmsServiceImpl
 * @Description: 短信模板操作服务实现
 * @author: xulang
 * @date: 2016年09月06日 13:23
 */
@Service("msgTmplSmsService")
public class MsgTmplSmsServiceImpl implements MsgTmplSmsService {

    @Autowired
    @Qualifier("msgTmplSmsMapper")
    private MsgTmplSmsMapper msgTmplSmsMapper;

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
    public int deleteByPrimaryKey(Integer id) {
        MsgTmplSmsBean bean = new MsgTmplSmsBean();
        bean.setId(id);
        bean.setState((byte) 0);
        return msgTmplSmsMapper.updateByPrimaryKey(bean);
    }

    /**
     * 新增
     *
     * @param record
     * @return
     */
    @Override
    public int insert(MsgTmplSmsInfo record) throws Exception {
        int result = 0;
        if (record != null) {
            MsgTmplSmsBean bean = new MsgTmplSmsBean();
            BeanUtils.copyProperties(record, bean);
            bean.setId(null);
            bean.setCreateTime(new Date());
            bean.setPlaceSign(getPlaceSign(record));
            result = msgTmplSmsMapper.insert(bean);
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
    public MsgTmplSmsInfo selectByPrimaryKey(Integer id) {
        MsgTmplSmsBean dataBean = msgTmplSmsMapper.selectByPrimaryKey(id);
        MsgTmplSmsInfo temp = new MsgTmplSmsInfo();
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
    public List<MsgTmplSmsInfo> selectAll(Integer msgType, Integer limit, Integer offset) {
        Map<String, Object> map = new HashMap<>();
        map.put("msgType", msgType);
        if (limit != null) {
            map.put("limit", limit);
            map.put("offset", offset);
        }
        List<MsgTmplSmsBean> dataBeanList = msgTmplSmsMapper.selectAll(map);
        List<MsgTmplSmsInfo> dataList = new ArrayList<MsgTmplSmsInfo>();
        for (MsgTmplSmsBean item : dataBeanList) {
            MsgTmplSmsInfo temp = new MsgTmplSmsInfo();
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
        return msgTmplSmsMapper.selectAllCount(msgType);
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(MsgTmplSmsInfo record) throws Exception {
        int result = 0;
        if (record != null && record.getId() != null) {
            MsgTmplSmsBean bean = new MsgTmplSmsBean();
            BeanUtils.copyProperties(record, bean);
            bean.setUpdateTime(new Date());
            bean.setPlaceSign(getPlaceSign(record));
            result = msgTmplSmsMapper.updateByPrimaryKey(bean);
        }
        return result;
    }

    /**
     * 提取占位符
     *
     * @param record
     * @return
     */
    private String getPlaceSign(MsgTmplSmsInfo record) throws Exception {
        StringBuffer result = new StringBuffer();
        Pattern p = Pattern.compile("&\\w*&");
        Matcher m = p.matcher(record.getTmplContent());
        int count = 0;
        while (m.find()) {
            result.append(",").append(m.group().replaceAll("&", "").replaceAll(" ", ""));
            count++;
        }
        String rtnHolder = result.deleteCharAt(0).toString();
        int validParaCount = msgTypeParasService.queryCount(rtnHolder, record.getMsgType());
        if (validParaCount != count) {
            throw new BusinessException("09004");
        }
        return rtnHolder;
    }
}
