package com.rofour.baseball.service.order.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.AttachConstant;
import com.rofour.baseball.common.Constant;
import com.rofour.baseball.common.HttpClientUtils;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.order.*;
import com.rofour.baseball.dao.order.bean.DoOrderTaskResultBean;
import com.rofour.baseball.dao.order.bean.DoOrderTaskSearchBean;
import com.rofour.baseball.dao.order.bean.TbTask;
import com.rofour.baseball.dao.order.bean.TbTaskSub;
import com.rofour.baseball.dao.order.mapper.TbSysTaskOrderMapper;
import com.rofour.baseball.dao.order.mapper.TbTaskMapper;
import com.rofour.baseball.dao.order.mapper.TbTaskSubMapper;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.order.TaskOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service("taskOrderService")
public class TaskOrderImpl implements TaskOrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("tbTaskMapper")
    TbTaskMapper tbTaskMapper;

    @Resource(name = "tbSysTaskOrderMapper")
    private TbSysTaskOrderMapper tbSysTaskOrderMapper;

    @Resource(name = "tbTaskSubMapper")
    private TbTaskSubMapper tbTaskSubMapper;

    @Override
    public TbTask getDto(TbTask tbTask) {
        TbTask remodel = tbTaskMapper.selectByPrimaryKey(tbTask.getTaskId());
        remodel.setIsPublished(tbTaskMapper.getTaskSubCount(tbTask) > 0 ? (byte) 1 : (byte) 0);
        return remodel;
    }

    @Override
    public List<TbTaskSub> getTaskSubs(TbTaskSub tbTaskSub) {
        List<TbTaskSub> result = tbTaskSubMapper.selectByTask(tbTaskSub);
        for (TbTaskSub tbtaskSub : result) {
            tbtaskSub.setTaskUnitPrice(tbtaskSub.getTaskUnitPrice() / 100);//转为元
        }
        return result;
    }

    @Override
    public List<TbTask> getTasks(TbTask tbTask) {

        return tbTaskMapper.selectTask(tbTask);
    }

    @Override
    public Integer del(TbTask tbTask, HttpServletRequest re) {
//        如果含有启用的子任务不能删除
        if (tbTaskMapper.getTaskSubCount(tbTask) > 0) {
            return -1;
        }
        return tbTaskMapper.deleteByPrimaryKey(tbTask.getTaskId());
    }

    @Override
    public Integer pubSub(TbTaskSub bean, HttpServletRequest re) {

        TbTaskSub model = tbTaskSubMapper.selectByPrimaryKey(bean.getTaskSubId());

        if (model.getState().equals((byte) 0)) {
            bean.setState((byte) 1);
            return tbTaskSubMapper.updateByPrimaryKeySelective(bean);
        } else {
            return -1;
        }
    }

    @Override
    public Integer delSub(TbTaskSub bean, HttpServletRequest re) {
        TbTaskSub model = tbTaskSubMapper.selectByPrimaryKey(bean.getTaskSubId());
        if (model.getState().equals((byte) 0)) {
            return tbTaskSubMapper.deleteByPrimaryKey(bean.getTaskSubId());
        } else {
            return -1;
        }
    }

    //任务停止
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public Integer stopSub(TbTaskSub bean, HttpServletRequest re) {
        TbTaskSub model = tbTaskSubMapper.selectByPrimaryKey(bean.getTaskSubId());
        if (model.getState().equals((byte) 2)) {
            bean.setState((byte) 3);
            tbTaskSubMapper.stopByPrimaryKey(bean.getTaskSubId());
            return tbTaskSubMapper.updateByPrimaryKeySelective(bean);
        } else {
            return -1;
        }
    }

    @Override
    public ResultInfo<Object> update(TbTask tbTask, HttpServletRequest re) {
        try {
            tbTaskMapper.updateByPrimaryKeySelective(tbTask);
            //如果是发布那么把子任务状态修改为发布状态
            if (tbTask.getIsPublished().equals((byte) 1)) {
                tbTaskMapper.updateTaskSub(tbTask);
            }
        } catch (Exception e) {
            return new ResultInfo<Object>(-1, "0", e.getMessage());
        }
        return new ResultInfo<Object>(0, "0", "");
    }

    @Override
    public Integer getTasksCount(TbTask tbTask) {
        return tbTaskMapper.selectTaskCount(tbTask);
    }

    /**
     * 生成订单号： 88+ 当前年月日时分秒+五位随机数
     *
     * @return
     */
    public String getRandomName() {

        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        return "88" + str + rannum;// 当前时间
    }

    @Override
    public ResultInfo<Object> insertSub(TbTaskSub tbtaskSub, HttpServletRequest re) {

        List<TbTaskSub> existList = tbTaskSubMapper.selectByTaskAndCollege(tbtaskSub);
        if (existList.size() > 0) {
            return new ResultInfo<Object>(-1, "0", "学校不能重复");
        }
        UserManagerLoginBean user = ((UserManagerLoginBean) re.getSession().getAttribute("user"));
        tbtaskSub.setTaskUnitPrice(tbtaskSub.getTaskUnitPrice() * 100);//转为分
        tbtaskSub.setTaskNo(Long.parseLong(getRandomName()));
        tbtaskSub.setPublishTime(new Date());
        tbtaskSub.setPublishUserId(user.getUserManagerId());
        tbTaskSubMapper.insertSelective(tbtaskSub);
        return new ResultInfo<Object>(0, "0", "");
    }

    @Override
    public ResultInfo<Object> updateSub(TbTaskSub tbtaskSub, HttpServletRequest re) {

//        List<TbTaskSub> existList = tbTaskSubMapper.selectByTaskAndCollege(tbtaskSub);
//        if (existList.size() > 0) {
//            return new ResultInfo<Object>(-1, "0", "学校不能重复");
//        }

        tbtaskSub.setTaskUnitPrice(tbtaskSub.getTaskUnitPrice() * 100);//转为分

        tbTaskSubMapper.updateByPrimaryKeySelective(tbtaskSub);
        return new ResultInfo<Object>(0, "0", "");
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public ResultInfo<Object> insert(TbTask tbTask, HttpServletRequest re) {
        try {
            UserManagerLoginBean user = ((UserManagerLoginBean) re.getSession().getAttribute("user"));
            tbTask.setTaskNo(Long.parseLong(getRandomName()));
            tbTask.setCreateTime(new Date());
            tbTask.setCreateUserId(user.getUserManagerId());
            tbTaskMapper.insertSelective(tbTask);
            for (TbTaskSub tbtaskSub : tbTask.getTbTaskSubs()) {
                tbtaskSub.setTaskUnitPrice(tbtaskSub.getTaskUnitPrice() * 100);//转为分
                tbtaskSub.setState(tbTask.getIsPublished() == 1 ? Byte.parseByte("1") : Byte.parseByte("0"));
                tbtaskSub.setTaskNo(Long.parseLong(getRandomName()));
                tbtaskSub.setTaskId(tbTask.getTaskId());
                tbtaskSub.setPublishTime(new Date());
                tbtaskSub.setPublishUserId(user.getUserManagerId());
                tbTaskSubMapper.insertSelective(tbtaskSub);
            }
        } catch (Exception e) {
            return new ResultInfo<Object>(-1, "0", e.getMessage());
        }
        return new ResultInfo<Object>(0, "0", "");
    }

    /**
     * 获取做单任务列表
     *
     * @param info
     * @return
     */
    @Override
    public List<DoOrderTaskResultInfo> getDoOrderTaskList(DoOrderTaskSearchInfo info) throws Exception {

        DoOrderTaskSearchBean searchBean = new DoOrderTaskSearchBean();
        BeanUtils.copyProperties(info, searchBean);
        List<DoOrderTaskResultBean> beanList = tbSysTaskOrderMapper.selectDoOrderTaskListByParams(searchBean);
        List<DoOrderTaskResultInfo> newList = new ArrayList<>();
        for (DoOrderTaskResultBean bean : beanList) {
            DoOrderTaskResultInfo newInfo = new DoOrderTaskResultInfo();
            BeanUtils.copyProperties(bean, newInfo);
            newList.add(newInfo);
        }
        return newList;
    }

    /**
     * 获取做单任务详情
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public DoOrderTaskDetailInfo getDoOrderTaskDetail(String orderId) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderId);
        DoOrderTaskDetailInfo info = tbSysTaskOrderMapper.selectDoOrderTaskDetail(map);
        try {
            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("bizId", info.getOrderId());
            parameter.put("attachType", AttachConstant.TYPE_TASK_AUDIT);
            String url = Constant.axpurl.get("resource_getOriginAttachList_serv");
            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };
            ResultInfo<List<AttachmentInfo>> data = (ResultInfo<List<AttachmentInfo>>) HttpClientUtils.post(url, parameter, TypeRef);
            info.setAttachmentInfo(data != null ? data.getData() : null);
        } catch (Exception e) {
            throw new BusinessException("104");
        }
        return info;
    }

    /**
     * 保存审核结果
     *
     * @param info
     * @return
     */
    @Override
    public ResultInfo saveAuditResult(TbSysTaskOrderInfo info) throws Exception {
        int result;
        if (tbSysTaskOrderMapper.checkIsAudit(Long.valueOf(info.getOrderId())) > 0) {
            return new ResultInfo(-1, "", "已审核的不可更改");
        }
        result = tbSysTaskOrderMapper.updateAuditResult(info);
        if (result > 0) {
            return new ResultInfo(0, "", "保存成功");
        }
        return new ResultInfo(-1, "", "保存失败");
    }

    /**
     * 提交审核完成并评分
     *
     * @param info
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResultInfo submitAuditResult(TbSysTaskOrderInfo info, HttpServletRequest request) {
        //获取登录用户信息
        UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
        if (sessionUser == null) {
            return new ResultInfo(-1, "", "用户未登录");
        }
        if (info == null || info.getTaskSubId() == null) {
            return new ResultInfo(-1, "", "传入的参数有错误");
        }

        info.setAuditUser(sessionUser.getUserManagerId());

        //TbSysTaskOrderInfo newInfo=tbSysTaskOrderMapper.selectSysTaskOrder(info.getTaskSubId());
        if (tbSysTaskOrderMapper.checkWaitAudit(Long.valueOf(info.getOrderId())) > 0) {
            return new ResultInfo(-1, "", "该任务不可审核");
        }
        //判断是否已审核
        if (tbSysTaskOrderMapper.checkIsAudit(Long.valueOf(info.getOrderId())) > 0) {
            return new ResultInfo(-1, "", "该任务已审核完成!勿重复操作");
        }
        //保存结果
        info.setAuditDate(new Date());
        tbSysTaskOrderMapper.updateAuditResult(info);
        //计算最终金额
        Long finalMoney = calculationPrice(info);
        Map<String, Object> map = new HashMap<>();
        map.put("finalMoney", finalMoney.toString());
        map.put("orderId", info.getOrderId());
        int result = tbSysTaskOrderMapper.updateOrderStatus(map);
        if (result > 0) {
            return new ResultInfo(0, "", "审核成功", finalMoney.toString());
        }
        return new ResultInfo(-1, "", "审核失败");
    }

    /**
     * 获取做单任务列表数目
     *
     * @param info
     * @return
     * @throws Exception
     */
    @Override
    public int selectDoOrderTaskCount(DoOrderTaskSearchInfo info) throws Exception {
        DoOrderTaskSearchBean searchBean = new DoOrderTaskSearchBean();
        BeanUtils.copyProperties(info, searchBean);
        return tbSysTaskOrderMapper.selectDoOrderTaskCount(searchBean);
    }

    /**
     * 计算金额
     *
     * @param info
     */

    public Long calculationPrice(TbSysTaskOrderInfo info) {
        List<Long> scoreList = tbSysTaskOrderMapper.selectOverallScore(Long.valueOf(info.getOrderId()));
        Long totalMoney = info.getTotalMoney();
        Long finalMoney = Long.valueOf(0);
        for (Long score : scoreList
                ) {
            finalMoney += (totalMoney * score) / 10;
        }
        return finalMoney;
    }
}
