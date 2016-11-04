package com.rofour.baseball.service.manager.impl;

import com.rofour.baseball.controller.model.UserManagerLogModel;
import com.rofour.baseball.dao.manager.bean.UserManagerLogBean;
import com.rofour.baseball.dao.manager.mapper.UserManagerLogMapper;
import com.rofour.baseball.service.manager.UserManagerLog;
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
