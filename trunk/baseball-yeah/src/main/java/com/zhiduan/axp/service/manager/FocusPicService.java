package com.zhiduan.axp.service.manager;

import com.zhiduan.axp.controller.model.UserManagerLogModel;
import com.zhiduan.axp.dao.manager.bean.FocusPicBean;
import com.zhiduan.axp.dao.manager.bean.UserManagerLogBean;

import java.util.List;

public interface FocusPicService {

    int insert(FocusPicBean logBean);

    int del(FocusPicBean logBean);

    List<FocusPicBean> getAll(FocusPicBean logBean);

    int selectAllCount(FocusPicBean logBean);
}
