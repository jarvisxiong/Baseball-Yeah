package com.rofour.baseball.service.activity.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.*;
import com.rofour.baseball.controller.model.Permission;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectSet;
import com.rofour.baseball.dao.activity.bean.*;
import com.rofour.baseball.dao.activity.mapper.*;
import com.rofour.baseball.dao.manager.bean.CollegeBean;
import com.rofour.baseball.service.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Service("activityService")
public class ActivityServerImpl implements ActivityService {
    @Autowired
    @Qualifier("acctActivityMapper")
    private AcctActivityMapper acctActivityMapper;

    @Autowired
    @Qualifier("acctActivityPolicyMapper")
    private AcctActivityPolicyMapper acctActivityPolicyMapper;

    @Autowired
    @Qualifier("acctPolicyPutRuleMapper")
    private AcctPolicyPutRuleMapper acctPolicyPutRuleMapper;

    @Autowired
    @Qualifier("acctPolicyReceiveRuleMapper")
    private AcctPolicyReceiveRuleMapper acctPolicyReceiveRuleMapper;

    @Autowired
    @Qualifier("acctPolicyUseRuleMapper")
    private AcctPolicyUseRuleMapper acctPolicyUseRuleMapper;

    @Resource(name = "acctPolicyVoucherMapper")
    private AcctPolicyVoucherMapper acctPolicyVoucherMapper;

    @Autowired
    private WebUtils webUtils;

    private ResultInfo<Object> setErrorResult(String message) {
        return new ResultInfo<Object>(-1, "0", message);
    }

    //新增 编辑 bean校验
    public ResultInfo<Object> beanCheck(AcctActivity bean) {
        ResultInfo<Object> resultInfo = new ResultInfo<Object>(0, "0", "");
        if (bean.getActivityName() == null || bean.getActivityName().isEmpty()) {
            return setErrorResult("活动名称必填");
        }
        if (bean.getPutAmount() == null) {
            return setErrorResult("总金额必填");
        }
        if (bean.getPutAmount() <= 0) {
            return setErrorResult("总金额必须大于0");
        }

        if (bean.getProvinceId() == null) {
            return setErrorResult("省份必填");
        }
        if (bean.getStartTime() == null) {
            return setErrorResult("开始时间必填");
        }
        if (bean.getEndTime() == null) {
            return setErrorResult("结束时间必填");
        }
        if (bean.getContacts() == null || bean.getContacts().size() == 0) {
            return setErrorResult("必须要有一个策略");
        }
        if (bean.getStartTime().getTime() > bean.getEndTime().getTime()) {
            return setErrorResult("结束时间要大于开始时间");
        }

        Long total = Long.valueOf(0);
        for (AcctActivityPolicyBean policyBean : bean.getContacts()) {
            if (policyBean.getPolicyName() == null || policyBean.getPolicyName().isEmpty()) {
                return setErrorResult("策略名称必填");
            }
            if (policyBean.getPolicyPriority() == null) {
                return setErrorResult("策略优先级必填");
            }
            if (policyBean.getPolicyPriority() > 100 || policyBean.getPolicyPriority() <= 0) {
                return setErrorResult("策略优先级为1-9之间");
            }


            if (policyBean.getDeliverys() == null || policyBean.getDeliverys().size() == 0) {
                return setErrorResult("策略必须要有一个投放规则");
            } else {
                for (AcctPolicyPutRuleBean ruleBean : policyBean.getDeliverys()) {
                    if (ruleBean.getFaceValue() == null) {
                        return setErrorResult("投放规则金额必填");
                    }
                    if (ruleBean.getTotalAmount() == null) {
                        return setErrorResult("投放规则数量必填");
                    }
                    total += ruleBean.getFaceValue() * ruleBean.getTotalAmount();
                }
            }
            //领取规则
            for (AcctPolicyReceiveRuleBean receiveRuleBean : policyBean.getReceives()) {
                receiveRuleBean.setPolicyId(policyBean.getPolicyId());
                switch (receiveRuleBean.getQuotaField()) {
                    case "ORDER_TYPE":
                        receiveRuleBean.setQuotaValue(receiveRuleBean.getQuotaDivOrderTypeValue());
                        receiveRuleBean.setQuotaId(10);
                        if (!(receiveRuleBean.getOperator().equals("=") || receiveRuleBean.getOperator().equals("in"))) {
                            return setErrorResult("领取规则'订单类型'只能使用'等于''包含'");
                        }
                        break;
                    case "LEAD_TIMES":
                        receiveRuleBean.setQuotaValue(receiveRuleBean.getQuotaTextValue());
                        receiveRuleBean.setQuotaId(9);
                        if (!(receiveRuleBean.getOperator().equals("<") || receiveRuleBean.getOperator().equals("<="))) {
                            return setErrorResult("领取规则'领取次数'只能使用'小于' '小于等于'");
                        }
                        break;
                    case "ORDER_COUNT":
                        receiveRuleBean.setQuotaValue(receiveRuleBean.getQuotaTextValue());
                        receiveRuleBean.setQuotaId(7);
//                        if (!(receiveRuleBean.getOperator().equals("<") || receiveRuleBean.getOperator().equals("<="))) {
//                            return setErrorResult("领取规则'订单量'只能使用'小于' '小于等于'");
//                        }
                        break;
                    case "CAN_USE":
                        receiveRuleBean.setQuotaValue(receiveRuleBean.getQuotaBoolValue());
                        receiveRuleBean.setQuotaId(8);
                        if (!(receiveRuleBean.getOperator().equals("="))) {
                            return setErrorResult("领取规则'是否可用'只能使用'等于'");
                        }
                        break;
                    case "IS_NEW":
                        receiveRuleBean.setQuotaValue(receiveRuleBean.getQuotaBoolValue());
                        receiveRuleBean.setQuotaId(6);
                        if (!(receiveRuleBean.getOperator().equals("="))) {
                            return setErrorResult("领取规则'新用户'只能使用'等于'");
                        }
                        break;
                    case "PROVINCE_ID":
                        receiveRuleBean.setQuotaValue(receiveRuleBean.getQuotaProvinceField());
                        receiveRuleBean.setQuotaId(5);
                        if (!(receiveRuleBean.getOperator().equals("=") || receiveRuleBean.getOperator().equals("in") || receiveRuleBean.getOperator().equals("notin"))) {
                            return setErrorResult("领取规则'省份编号'只能使用'等于' '包含' '不包含'");
                        }
                        break;
                    case "CITY_ID":
                        receiveRuleBean.setQuotaValue(receiveRuleBean.getQuotaCityField());
                        receiveRuleBean.setQuotaId(4);
                        if (!(receiveRuleBean.getOperator().equals("=") || receiveRuleBean.getOperator().equals("in") || receiveRuleBean.getOperator().equals("notin"))) {
                            return setErrorResult("领取规则'城市'只能使用'等于' '包含' '不包含'");
                        }
                        break;
                    case "COLLEGE_ID":
                        receiveRuleBean.setQuotaValue(receiveRuleBean.getQuotaCollegeField());
                        receiveRuleBean.setQuotaId(3);
                        if (!(receiveRuleBean.getOperator().equals("=") || receiveRuleBean.getOperator().equals("in") || receiveRuleBean.getOperator().equals("notin"))) {
                            return setErrorResult("领取规则'学校编号'只能使用'等于' '包含' '不包含'");
                        }
                        break;
                    case "END_TIME":
                        receiveRuleBean.setQuotaValue(receiveRuleBean.getQuotaTimeValue());
                        receiveRuleBean.setQuotaId(2);
                        if (!(receiveRuleBean.getOperator().equals("<") || receiveRuleBean.getOperator().equals("<="))) {
                            return setErrorResult("领取规则'结束时间'只能使用'小于' '小于等于'");
                        }
                        break;
                    case "START_TIME":
                        receiveRuleBean.setQuotaValue(receiveRuleBean.getQuotaTimeValue());
                        receiveRuleBean.setQuotaId(1);
                        if (!(receiveRuleBean.getOperator().equals(">") || receiveRuleBean.getOperator().equals(">="))) {
                            return setErrorResult("领取规则'开始时间'只能使用'大于' '大于等于'");
                        }
                        break;
                }
                if ("".equals(receiveRuleBean.getQuotaValue()) || receiveRuleBean.getQuotaValue() == null) {
                    return setErrorResult("领取规则值不能为空");
                }
            }
            //使用规则
            for (AcctPolicyUseRuleBean useRuleBean : policyBean.getUses()) {
                useRuleBean.setPolicyId(policyBean.getPolicyId());
                switch (useRuleBean.getQuotaField()) {
                    case "ORDER_TYPE":
                        useRuleBean.setQuotaValue(useRuleBean.getQuotaDivOrderTypeValue());
                        useRuleBean.setQuotaId(10);
                        if (!(useRuleBean.getOperator().equals("=") || useRuleBean.getOperator().equals("in"))) {
                            return setErrorResult("使用规则'订单类型'只能使用'等于''包含'");
                        }
                        break;
                    case "LEAD_TIMES":
                        useRuleBean.setQuotaValue(useRuleBean.getQuotaTextValue());
                        useRuleBean.setQuotaId(9);
                        if (!(useRuleBean.getOperator().equals("<") || useRuleBean.getOperator().equals("<="))) {
                            return setErrorResult("使用规则'领取次数'只能使用'小于' '小于等于'");
                        }
                        break;
                    case "ORDER_COUNT":
                        useRuleBean.setQuotaValue(useRuleBean.getQuotaTextValue());
                        useRuleBean.setQuotaId(7);
//                        if (!(useRuleBean.getOperator().equals("<") || useRuleBean.getOperator().equals("<="))) {
//                            return setErrorResult("使用规则'订单量'只能使用'小于' '小于等于'");
//                        }
                        break;
                    case "CAN_USE":
                        useRuleBean.setQuotaValue(useRuleBean.getQuotaBoolValue());
                        useRuleBean.setQuotaId(8);
                        if (!(useRuleBean.getOperator().equals("="))) {
                            return setErrorResult("使用规则'是否可用'只能使用'等于'");
                        }
                        break;
                    case "IS_NEW":
                        useRuleBean.setQuotaValue(useRuleBean.getQuotaBoolValue());
                        useRuleBean.setQuotaId(6);
                        if (!(useRuleBean.getOperator().equals("="))) {
                            return setErrorResult("使用规则'新用户'只能使用'等于'");
                        }
                        break;
                    case "PROVINCE_ID":
                        useRuleBean.setQuotaValue(useRuleBean.getQuotaProvinceField());
                        useRuleBean.setQuotaId(5);
                        if (!(useRuleBean.getOperator().equals("=") || useRuleBean.getOperator().equals("in") || useRuleBean.getOperator().equals("notin"))) {
                            return setErrorResult("使用规则'省份编号'只能使用'等于' '包含' '不包含'");
                        }
                        break;
                    case "CITY_ID":
                        useRuleBean.setQuotaValue(useRuleBean.getQuotaCityField());
                        useRuleBean.setQuotaId(4);
                        if (!(useRuleBean.getOperator().equals("=") || useRuleBean.getOperator().equals("in") || useRuleBean.getOperator().equals("notin"))) {
                            return setErrorResult("使用规则'城市'只能使用'等于' '包含' '不包含'");
                        }
                        break;
                    case "COLLEGE_ID":
                        useRuleBean.setQuotaValue(useRuleBean.getQuotaCollegeField());
                        useRuleBean.setQuotaId(3);
                        if (!(useRuleBean.getOperator().equals("=") || useRuleBean.getOperator().equals("in") || useRuleBean.getOperator().equals("notin"))) {
                            return setErrorResult("使用规则'学校编号'只能使用'等于' '包含' '不包含'");
                        }
                        break;
                    case "END_TIME":
                        useRuleBean.setQuotaValue(useRuleBean.getQuotaTimeValue());
                        useRuleBean.setQuotaId(2);
                        if (!(useRuleBean.getOperator().equals("<") || useRuleBean.getOperator().equals("<="))) {
                            return setErrorResult("使用规则'结束时间'只能使用'小于' '小于等于'");
                        }
                        break;
                    case "START_TIME":
                        useRuleBean.setQuotaValue(useRuleBean.getQuotaTimeValue());
                        useRuleBean.setQuotaId(1);
                        if (!(useRuleBean.getOperator().equals(">") || useRuleBean.getOperator().equals(">="))) {
                            return setErrorResult("使用规则'开始时间'只能使用'大于' '大于等于'");
                        }
                        break;
                }
                if ("".equals(useRuleBean.getQuotaValue()) || useRuleBean.getQuotaValue() == null) {
                    return setErrorResult("使用规则值不能为空");
                }
            }

            if (policyBean.getReceives() == null || policyBean.getReceives().size() == 0) {
                return setErrorResult("策略必须要有一个领取规则");
            }

            if (policyBean.getUses() == null || policyBean.getUses().size() == 0) {
                return setErrorResult("策略必须要有一个使用规则");
            }
        }
        if (!total.equals(bean.getPutAmount())) {
            return setErrorResult("投放规则金额总数和活动总金额不一致");
        }
        return resultInfo;
    }

    public ResultInfo<Object> addchersCache(AcctActivity bean) {
        List<AcctActivityPolicyBean> cons = acctActivityPolicyMapper.selectByActivityId(bean.getActivityId());
        ResultInfo<Object> resultInfo = new ResultInfo<Object>(0, "0", "");
        for (AcctActivityPolicyBean policyBean : cons) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("policyId", policyBean.getPolicyId().toString());
            String url = Constant.axpurl.get("add_vouchers_cache_serv");
            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };
            ResultInfo<?> data = null;
            try {
                data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
                // 返回参数赋值
                if (data.getSuccess() < 0) {
                    return new ResultInfo(-1, "0", "活动没有可用券", "");
                }
            } catch (IOException e) {
                return new ResultInfo(-1, "0", "调用AXP接口失败", "");
            }
        }
        return resultInfo;
    }

    public ResultInfo<Object> delchersCache(AcctActivity bean) {
        List<AcctActivityPolicyBean> cons = acctActivityPolicyMapper.selectByActivityId(bean.getActivityId());
        ResultInfo<Object> resultInfo = new ResultInfo<Object>(0, "0", "");
        for (AcctActivityPolicyBean policyBean : cons) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("policyId", policyBean.getPolicyId().toString());
            String url = Constant.axpurl.get("del_vouchers_cache_serv");
            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };
            ResultInfo<?> data = null;
            try {
                data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
                // 返回参数赋值
                if (data.getSuccess() < 0) {
                    return new ResultInfo(-1, "0", "调用AXP接口失败", "");
                }
            } catch (IOException e) {

            }
        }
        return resultInfo;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public ResultInfo<Object> insert(AcctActivity bean, HttpServletRequest request) {
        //新增活动
        bean.setPutAmount(bean.getPutAmount() * 100);
        bean.setState(0);
        bean.setBeDeleted(0);
        acctActivityMapper.insert(bean);//返回的是受影响的行数 id直接赋值到bean了
        //新增策略
        for (AcctActivityPolicyBean activityPolicyBean : bean.getContacts()) {
            activityPolicyBean.setBeDeleted(0);
            activityPolicyBean.setActivityId(bean.getActivityId());
            acctActivityPolicyMapper.insert(activityPolicyBean);
            //投放规则
            for (AcctPolicyPutRuleBean putRuleBean : activityPolicyBean.getDeliverys()) {
                putRuleBean.setPolicyId(activityPolicyBean.getPolicyId());
                putRuleBean.setFaceValue(putRuleBean.getFaceValue() * 100);//数据库存分
                putRuleBean.setInitialAmount(putRuleBean.getTotalAmount());
                putRuleBean.setReceivedAmount(0);//已领取 已使用 已失效为0
                putRuleBean.setUsedAmount(0);
                putRuleBean.setExpiredAmount(0);
                acctPolicyPutRuleMapper.insert(putRuleBean);
            }
            //领取规则
            for (AcctPolicyReceiveRuleBean receiveRuleBean : activityPolicyBean.getReceives()) {
                receiveRuleBean.setPolicyId(activityPolicyBean.getPolicyId());

                acctPolicyReceiveRuleMapper.insert(receiveRuleBean);
            }
            //使用规则
            for (AcctPolicyUseRuleBean useRuleBean : activityPolicyBean.getUses()) {
                useRuleBean.setPolicyId(activityPolicyBean.getPolicyId());

                acctPolicyUseRuleMapper.insert(useRuleBean);
            }
        }
        webUtils.userAddLog(request, 160, bean);
        return new ResultInfo<Object>(0, "0", "");
    }

    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public ResultInfo<Object> update(AcctActivity bean, HttpServletRequest request) {
        //新增活动
        bean.setPutAmount(bean.getPutAmount() * 100);
        AcctActivity oldDto = acctActivityMapper.selectByPrimaryKey(bean.getActivityId());

        // 只能编辑未生成代金券的活动 规则直接删除后新增
        acctActivityMapper.updateByPrimaryKey(bean);


        //新增策略
        for (AcctActivityPolicyBean activityPolicyBean : bean.getContacts()) {
            activityPolicyBean.setActivityId(bean.getActivityId());
            acctActivityPolicyMapper.updateByPrimaryKey(activityPolicyBean);
            acctPolicyPutRuleMapper.deleteByPrimaryKey(activityPolicyBean.getPolicyId());
            acctPolicyReceiveRuleMapper.deleteByPrimaryKey(activityPolicyBean.getPolicyId());
            acctPolicyUseRuleMapper.deleteByPrimaryKey(activityPolicyBean.getPolicyId());

            List<Integer> polids = new ArrayList<>();
            bean.setPolicyIds(polids);
            if (activityPolicyBean.getPolicyId() == null) {
                activityPolicyBean.setBeDeleted(0);
                acctActivityPolicyMapper.insert(activityPolicyBean);
            } else {
                bean.getPolicyIds().add(activityPolicyBean.getPolicyId());
            }
            //投放规则
            for (AcctPolicyPutRuleBean putRuleBean : activityPolicyBean.getDeliverys()) {
                putRuleBean.setPolicyId(activityPolicyBean.getPolicyId());
                putRuleBean.setFaceValue(putRuleBean.getFaceValue() * 100);//数据库存分
                putRuleBean.setInitialAmount(putRuleBean.getTotalAmount());
                putRuleBean.setReceivedAmount(0);//已领取 已使用 已失效为0
                putRuleBean.setUsedAmount(0);
                putRuleBean.setExpiredAmount(0);
                acctPolicyPutRuleMapper.insert(putRuleBean);
            }
            //领取规则
            for (AcctPolicyReceiveRuleBean receiveRuleBean : activityPolicyBean.getReceives()) {
                receiveRuleBean.setPolicyId(activityPolicyBean.getPolicyId());

                acctPolicyReceiveRuleMapper.insert(receiveRuleBean);
            }
            //使用规则
            for (AcctPolicyUseRuleBean useRuleBean : activityPolicyBean.getUses()) {
                useRuleBean.setPolicyId(activityPolicyBean.getPolicyId());

                acctPolicyUseRuleMapper.insert(useRuleBean);
            }
        }

        //删除没有包含的策略
        if (bean.getPolicyIds().size() > 0) {
            acctActivityMapper.deleteActivityPolicy(bean);
        }
        webUtils.userEditLog(request, 160, oldDto, bean);
        return new ResultInfo<Object>(0, "0", "");
    }

    public List<AcctActivity> getAll(AcctActivity bean) {
        return acctActivityMapper.getAll(bean);
    }


    public CollegeBean getEditColege(AcctActivity bean) {
        CollegeBean collegeBean = new CollegeBean();
        AcctActivity retDto;
        List<String> collegeIds = new ArrayList<>();
        retDto = acctActivityMapper.selectByPrimaryKey(bean.getActivityId());

        List<AcctActivityPolicyBean> cons = acctActivityPolicyMapper.selectByActivityId(retDto.getActivityId());
        for (AcctActivityPolicyBean policyBean : cons) {

            policyBean.setReceives(acctPolicyReceiveRuleMapper.selectByPolicyId(policyBean.getPolicyId()));
            for (AcctPolicyReceiveRuleBean receiveRuleBean : policyBean.getReceives()) {
                if (receiveRuleBean.getQuotaField().equals("COLLEGE_ID")) {
                    collegeIds.add(receiveRuleBean.getQuotaValue());
                }
            }
            policyBean.setUses(acctPolicyUseRuleMapper.selectByPolicyId(policyBean.getPolicyId()));
            for (AcctPolicyUseRuleBean userBean : policyBean.getUses()) {
                if (userBean.getQuotaField().equals("COLLEGE_ID")) {
                    collegeIds.add(userBean.getQuotaValue());
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < collegeIds.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(collegeIds.get(i));
        }
        collegeBean.setEditCollegeId(sb.toString());
        collegeBean.setEditCollegeList(Arrays.asList(sb.toString().split(",")));
        return collegeBean;
    }

    public AcctActivity getDto(AcctActivity bean) {


        AcctActivity retDto;
        retDto = acctActivityMapper.selectByPrimaryKey(bean.getActivityId());
        retDto.setPutAmount(retDto.getPutAmount() / 100);
        List<AcctActivityPolicyBean> cons = acctActivityPolicyMapper.selectByActivityId(retDto.getActivityId());
        for (AcctActivityPolicyBean policyBean : cons) {
            //投放规则
            policyBean.setDeliverys(acctPolicyPutRuleMapper.selectByPolicyId(policyBean.getPolicyId()));
            //计算投放的比例
            for (AcctPolicyPutRuleBean ruleBean : policyBean.getDeliverys()) {
                ruleBean.setFaceValue(ruleBean.getFaceValue() / 100);
                ruleBean.setScale(ruleBean.getFaceValue() * ruleBean.getTotalAmount() * 100 / retDto.getPutAmount());
            }
            policyBean.setReceives(acctPolicyReceiveRuleMapper.selectByPolicyId(policyBean.getPolicyId()));
            for (AcctPolicyReceiveRuleBean receiveRuleBean : policyBean.getReceives()) {
                switch (receiveRuleBean.getQuotaField()) {
                    case "ORDER_TYPE":
                        receiveRuleBean.setQuotaDivOrderTypeValue(receiveRuleBean.getQuotaValue());
                        break;
                    case "LEAD_TIMES":
                        receiveRuleBean.setQuotaTextValue(receiveRuleBean.getQuotaValue());
                        break;
                    case "ORDER_COUNT":
                        receiveRuleBean.setQuotaTextValue(receiveRuleBean.getQuotaValue());
                        break;
                    case "CAN_USE":
                        receiveRuleBean.setQuotaBoolValue(receiveRuleBean.getQuotaValue());
                        break;
                    case "IS_NEW":
                        receiveRuleBean.setQuotaBoolValue(receiveRuleBean.getQuotaValue());
                        break;
                    case "PROVINCE_ID":
                        receiveRuleBean.setQuotaProvinceField(receiveRuleBean.getQuotaValue());
                        break;
                    case "CITY_ID":
                        receiveRuleBean.setQuotaCityField(receiveRuleBean.getQuotaValue());
                        break;
                    case "COLLEGE_ID":
                        receiveRuleBean.setQuotaCollegeField(receiveRuleBean.getQuotaValue());
                        break;
                    case "END_TIME":
                        receiveRuleBean.setQuotaTimeValue(receiveRuleBean.getQuotaValue());
                        break;
                    case "START_TIME":
                        receiveRuleBean.setQuotaTimeValue(receiveRuleBean.getQuotaValue());
                        break;
                }
            }
            policyBean.setUses(acctPolicyUseRuleMapper.selectByPolicyId(policyBean.getPolicyId()));
            for (AcctPolicyUseRuleBean userBean : policyBean.getUses()) {
                switch (userBean.getQuotaField()) {
                    case "ORDER_COUNT":
                        userBean.setQuotaTextValue(userBean.getQuotaValue());
                        break;
                    case "CAN_USE":
                        userBean.setQuotaBoolValue(userBean.getQuotaValue());
                        break;
                    case "IS_NEW":
                        userBean.setQuotaBoolValue(userBean.getQuotaValue());
                        break;
                    case "PROVINCE_ID":
                        userBean.setQuotaProvinceField(userBean.getQuotaValue());
                        break;
                    case "CITY_ID":
                        userBean.setQuotaCityField(userBean.getQuotaValue());
                        break;
                    case "COLLEGE_ID":
                        userBean.setQuotaCollegeField(userBean.getQuotaValue());
                        break;
                    case "END_TIME":
                        userBean.setQuotaTimeValue(userBean.getQuotaValue());
                        break;
                    case "START_TIME":
                        userBean.setQuotaTimeValue(userBean.getQuotaValue());
                        break;
                    case "LEAD_TIMES":
                        userBean.setQuotaTextValue(userBean.getQuotaValue());
                        break;
                    case "ORDER_TYPE":
                        userBean.setQuotaDivOrderTypeValue(userBean.getQuotaValue());
                        break;
                }
            }
        }
        retDto.setContacts(cons);
        return retDto;
    }

    /**
     *
     * @param bean
     * @param request the request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public int del(AcctActivity bean, HttpServletRequest request) {
        //逻辑删除活动 策略 代金券
        acctActivityMapper.deleteByPrimaryKey(bean.getActivityId());
        List<AcctActivityPolicyBean> cons = acctActivityPolicyMapper.selectByActivityId(bean.getActivityId());
        for (AcctActivityPolicyBean policyBean : cons) {
            acctActivityPolicyMapper.deleteByPrimaryKey(policyBean.getPolicyId());
            acctPolicyVoucherMapper.deleteByPolicyId(policyBean.getPolicyId());
        }
        webUtils.userDeleteLog(request, 160, bean);
        return 1;
    }

    public int uptateState(AcctActivity bean, HttpServletRequest request) {
        AcctActivity retDto = acctActivityMapper.selectByPrimaryKey(bean.getActivityId());
        if (bean.getState().equals(2)) {
            webUtils.userLog(request, String.format("活动ID:%s状态由：%s修改为%s", bean.getActivityId(), retDto.getState(),
                    bean.getState()), 160, "", "", Permission.ENABLE.name());
        }
        if (bean.getState().equals(3)) {
            webUtils.userLog(request, String.format("活动ID:%s状态由：%s修改为%s", bean.getActivityId(), retDto.getState(),
                    bean.getState()), 160, "", "", Permission.FROZEN.name());
        }
        return acctActivityMapper.updateByPrimaryKeySelective(bean);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public int generate(AcctActivity bean, HttpServletRequest request) {
        try {
            //修改活动状态为已生成
            bean.setState(1);
            acctActivityMapper.updateByPrimaryKeySelective(bean);
            List<AcctActivityPolicyBean> cons = acctActivityPolicyMapper.selectByActivityId(bean.getActivityId());
            for (AcctActivityPolicyBean policyBean : cons) {
                policyBean.setDeliverys(acctPolicyPutRuleMapper.selectByPolicyId(policyBean.getPolicyId()));
                List<AcctPolicyVoucherBean> voucherBeanList = new ArrayList<AcctPolicyVoucherBean>();
                for (AcctPolicyPutRuleBean policyPutRuleBean : policyBean.getDeliverys()) {
                    AcctPolicyVoucherBean voucherBean = new AcctPolicyVoucherBean();
                    for (int i = 0; i < policyPutRuleBean.getTotalAmount(); i++) {//遍历数量产生对应的张数
                        voucherBean.setPolicyId(policyBean.getPolicyId());
                        voucherBean.setState(Byte.parseByte("3"));
                        voucherBean.setBeDeleted(0);
                        voucherBean.setFaceValue(policyPutRuleBean.getFaceValue());
                        voucherBeanList.add(voucherBean);
                    }
                }
                Collections.shuffle(voucherBeanList);//打乱排序
                int page = 100000;
                if (voucherBeanList.size() > page) {
                    double a = voucherBeanList.size() / page;
                    //已10W做分割批次插入
                    if (voucherBeanList.size() > page) {
                        for (int i = 0; i < a; i++) {
                            List<AcctPolicyVoucherBean> insertlist = voucherBeanList.subList(i * page, (i + 1) * page);
                            acctPolicyVoucherMapper.insertSelectiveBatch(insertlist);
                        }
                        //插入余数
                        List<AcctPolicyVoucherBean> insertlist = voucherBeanList.subList((int) a * page, voucherBeanList.size());

                        acctPolicyVoucherMapper.insertSelectiveBatch(insertlist);
                    }
                } else {
                    //批量插入
                    acctPolicyVoucherMapper.insertSelectiveBatch(voucherBeanList);
                }


            }
            webUtils.userLog(request, String.format("活动ID:%s生成代金券", bean.getActivityId()), 160, "", "", Permission.GENERATE.name());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public int selectAllCount(AcctActivity bean) {
        return acctActivityMapper.selectAllCount(bean);
    }

    /**
     * 获取活动下拉框的数据
     *
     * @return
     */
    @Override
    public List<SelectSet> getActivitySelect() throws Exception {
        return acctActivityPolicyMapper.selectActivity();
    }

    /**
     * 获取策略下拉框的数据
     *
     * @param ActivityId
     * @return
     */
    @Override
    public List<SelectSet> getPolicySelect(Long ActivityId) throws Exception {
        return acctActivityPolicyMapper.selectPolicy(ActivityId);
    }
}
