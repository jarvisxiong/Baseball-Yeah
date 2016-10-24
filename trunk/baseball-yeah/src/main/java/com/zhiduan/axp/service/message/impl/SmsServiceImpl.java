package com.zhiduan.axp.service.message.impl;

import com.zhiduan.axp.common.StringUtils;
import com.zhiduan.axp.controller.model.message.MessageSearchInfo;
import com.zhiduan.axp.controller.model.message.MessageSumInfo;
import com.zhiduan.axp.controller.model.message.SmsInfo;
import com.zhiduan.axp.dao.message.bean.MessageSearchBean;
import com.zhiduan.axp.dao.message.bean.MessageSumBean;
import com.zhiduan.axp.dao.message.bean.SmsBean;
import com.zhiduan.axp.dao.message.mapper.SmsMapper;
import com.zhiduan.axp.service.message.SmsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.awt.geom.Arc2D;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 短信服务实现
 * Created by wny on 2016-06-16.
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {


    @Autowired
    @Qualifier("smsMapper")
    private SmsMapper smsMapper;

    /**
     * @param where
     * @return
     * @Description: 根据条件查询短信记录
     */
    @Override
    public List<SmsInfo> getSmsList(@Param("where") MessageSearchInfo where) {

        MessageSearchBean beanWhere = new MessageSearchBean();
        BeanUtils.copyProperties(where, beanWhere);
        List<SmsInfo> dataList = new ArrayList<>();
        List<SmsBean> dataBeanList = smsMapper.getSmsList(beanWhere);
        for (SmsBean item : dataBeanList
                ) {
            SmsInfo temp = new SmsInfo();
            BeanUtils.copyProperties(item, temp);
            dataList.add(temp);

        }
        return dataList;

    }

    /**
     * 获取服务商短信分类汇总
     *
     * @param where 汇总条件
     * @return
     */
    @Override
    public List<MessageSumInfo> getVendorSmsSumList(@Param("where") MessageSearchInfo where) {
        MessageSearchBean beanWhere = new MessageSearchBean();
        BeanUtils.copyProperties(where, beanWhere);
        List<MessageSumInfo> dataList = new ArrayList<>();
        List<MessageSumBean> dataBeanList = smsMapper.getVendorSmsSumList(beanWhere);
        for (MessageSumBean item : dataBeanList
                ) {
            if (StringUtils.isEmpty(item.getGroupName()))
                continue;
            MessageSumInfo temp = new MessageSumInfo();
            item.setFailCount(item.getFailCount() == null ? 0 : item.getFailCount());
            item.setSentCount(item.getSentCount() == null ? 0 : item.getSentCount());
            item.setSucessCount(item.getSucessCount() == null ? 0 : item.getSucessCount());
            item.setSendingCount(item.getSendingCount() == null ? 0 : item.getSendingCount());
            BeanUtils.copyProperties(item, temp);
            temp.setSubmitCount(item.getFailCount() + item.getSucessCount() + item.getSentCount()+item.getSendingCount());
            temp.setFailCount(item.getFailCount()+item.getSendingCount());
            if (temp.getSucessCount() != 0) {
                temp.setSucessRate(String.valueOf((float) temp.getSucessCount() / (float) temp.getSubmitCount() * (float) 100) + "%");
            } else {
                temp.setSucessRate("");
            }
            dataList.add(temp);

        }
        return dataList;
    }

    /**
     * 根据条件获取短信记录个数
     *
     * @param where
     * @return
     */
    @Override
    public Integer getSmsTotal(@Param("where") MessageSearchInfo where) {
        MessageSearchBean beanWhere = new MessageSearchBean();
        BeanUtils.copyProperties(where, beanWhere);
        return smsMapper.getSmsTotal(beanWhere);
    }
}
