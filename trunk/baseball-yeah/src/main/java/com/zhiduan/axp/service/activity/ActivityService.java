package com.zhiduan.axp.service.activity;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.SelectSet;
import com.zhiduan.axp.dao.activity.bean.AcctActivity;
import com.zhiduan.axp.dao.manager.bean.CollegeBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * interface Activity service.
 *
 * @author will
 * @Description
 * @date 2016 -08-08 15:19:30
 */
public interface ActivityService {

    /**
     * 新增活动
     *
     * @param logBean 活动
     * @param request the request
     * @return the int
     */
    ResultInfo<Object> insert(AcctActivity logBean, HttpServletRequest request);

    /**
     * 检查数据格式.
     *
     * @param bean 活动
     * @return the result info
     */
    ResultInfo<Object> beanCheck(AcctActivity bean);

    /**
     * 活动启用调用接口
     *
     * @param bean 活动
     * @return the result info
     */
    ResultInfo<Object> addchersCache(AcctActivity bean);

    /**
     * 活动下架调用接口
     *
     * @param bean 活动
     * @return the result info
     */
    ResultInfo<Object> delchersCache(AcctActivity bean);

    /**
     * 更新活动
     *
     * @param logBean the log bean
     * @param request the request
     * @return the int
     */
    ResultInfo<Object> update(AcctActivity logBean, HttpServletRequest request);

    /**
     * 删除活动.
     *
     * @param logBean the log bean
     * @param request the request
     * @return the int
     */
    int del(AcctActivity logBean, HttpServletRequest request);

    /**
     * 更新活动状态.
     *
     * @param bean    the bean
     * @param request the request
     * @return the int
     */
    int uptateState(AcctActivity bean, HttpServletRequest request);

    /**
     * 生成代金券.
     *
     * @param bean    the bean
     * @param request the request
     * @return the int
     */
    int generate(AcctActivity bean, HttpServletRequest request);

    /**
     * 获取活动.
     *
     * @param bean the bean
     * @return the dto
     */
    AcctActivity getDto(AcctActivity bean);


    CollegeBean getEditColege(AcctActivity bean);

    /**
     * 获取活动列表.
     *
     * @param logBean the log bean
     * @return the all
     */
    List<AcctActivity> getAll(AcctActivity logBean);

    /**
     * 获取活动列表数量.
     *
     * @param logBean the log bean
     * @return the int
     */
    int selectAllCount(AcctActivity logBean);

    /**
     * 获取活动下拉框的数据
     * @return
     */
    List<SelectSet> getActivitySelect()  throws Exception;

    /**
     * 获取策略下拉框的数据
     * @param ActivityId
     * @return
     */
    List<SelectSet> getPolicySelect(Long ActivityId)  throws Exception;
}
