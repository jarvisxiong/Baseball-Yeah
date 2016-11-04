package com.rofour.baseball.service.manager.impl;

import com.rofour.baseball.controller.model.UserManagerLogModel;
import com.rofour.baseball.dao.manager.bean.FocusPicBean;
import com.rofour.baseball.dao.manager.bean.UserManagerLogBean;
import com.rofour.baseball.dao.manager.mapper.FocusPicMapper;
import com.rofour.baseball.service.manager.FocusPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016-06-30.
 */
@Service("focusPicService")
public class FocusPicImpl implements FocusPicService {

    @Autowired
    @Qualifier("focusPicMapper")
    private FocusPicMapper focus;

    public int insert(FocusPicBean bean) {
     return    focus.insert(bean);
    }

    public List<FocusPicBean> getAll(FocusPicBean picBean) {
        return focus.selectAll(picBean);
    }

    public int del(FocusPicBean logBean){
        //逻辑删除活动 策略 代金券
        return  focus.deleteByPrimaryKey(logBean.getAdId());
    }
    public int selectAllCount(FocusPicBean picBean) {
        return focus.selectAllCount(picBean);
    }
}
