package com.rofour.baseball.service.manager;

import com.rofour.baseball.controller.model.UserManagerLogModel;
import com.rofour.baseball.dao.manager.bean.FocusPicBean;
import com.rofour.baseball.dao.manager.bean.UserManagerLogBean;

import java.util.List;

public interface FocusPicService {

    int insert(FocusPicBean logBean);

    int del(FocusPicBean logBean);

    List<FocusPicBean> getAll(FocusPicBean logBean);

    int selectAllCount(FocusPicBean logBean);
}
