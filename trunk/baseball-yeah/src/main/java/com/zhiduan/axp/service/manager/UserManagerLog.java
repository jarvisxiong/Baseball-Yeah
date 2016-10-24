package com.zhiduan.axp.service.manager;

import com.zhiduan.axp.controller.model.UserManagerLogModel;
import com.zhiduan.axp.dao.manager.bean.UserManagerLogBean;

import java.util.List;


/**
 * Created by Administrator on 2016-05-30.
 */

public interface UserManagerLog {

    void insert(UserManagerLogBean logBean);

    List<UserManagerLogBean> getLogs(UserManagerLogModel logBean);

    int getLogsCount(UserManagerLogModel logBean);

}
