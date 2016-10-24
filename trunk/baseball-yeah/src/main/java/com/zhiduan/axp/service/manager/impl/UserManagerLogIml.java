package com.zhiduan.axp.service.manager.impl;

import com.zhiduan.axp.controller.model.UserManagerLogModel;
import com.zhiduan.axp.dao.manager.bean.UserManagerLogBean;
import com.zhiduan.axp.dao.manager.mapper.UserManagerLogMapper;
import com.zhiduan.axp.service.manager.UserManagerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-05-30.
 */
@Service("userManagerLog")
public class UserManagerLogIml implements UserManagerLog {


    @Autowired
    @Qualifier("userManagerLogMapper")
    private UserManagerLogMapper dao;

    public void insert(UserManagerLogBean LogModel) {
        dao.insert(LogModel);
        dao.insertRemark(LogModel);
    }

    public List<UserManagerLogBean> getLogs(UserManagerLogModel LogModel) {
        return dao.getLogs(LogModel);
    }

    public int getLogsCount(UserManagerLogModel LogModel) {
        return dao.getLogsCount(LogModel);
    }
}
