package com.rofour.baseball.service.manager;

import com.rofour.baseball.controller.model.UserManagerLogModel;
import com.rofour.baseball.dao.manager.bean.UserManagerLogBean;

import java.util.List;


/**
 * Created by Administrator on 2016-05-30.
 */

public interface UserManagerLog {

    void insert(UserManagerLogBean logBean);

    List<UserManagerLogBean> getLogs(UserManagerLogModel logBean);

    int getLogsCount(UserManagerLogModel logBean);

}
