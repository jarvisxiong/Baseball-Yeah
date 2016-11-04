package com.rofour.baseball.dao.manager.mapper;

import com.rofour.baseball.controller.model.UserManagerLogModel;
import com.rofour.baseball.dao.manager.bean.UserManagerLogBean;

import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016-05-30.
 */
@Named("userManagerLogMapper")
public interface UserManagerLogMapper {
    /**
     *
     * @Description: 增加
     * @param logBean
     * @return int
     */
    int insert(UserManagerLogBean logBean);

    int insertRemark(UserManagerLogBean logBean);

    List<UserManagerLogBean> getLogs(UserManagerLogModel logBean);

    int getLogsCount(UserManagerLogModel logBean);
}
