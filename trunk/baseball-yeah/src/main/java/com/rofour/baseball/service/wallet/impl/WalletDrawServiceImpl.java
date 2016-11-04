package com.rofour.baseball.service.wallet.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.*;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.wallet.UserDrawCheckInfo;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.dao.wallet.bean.AcctExchangeCashBean;
import com.rofour.baseball.dao.wallet.bean.UserDrawCheckBean;
import com.rofour.baseball.dao.wallet.mapper.AcctExchangeCashMapper;
import com.rofour.baseball.dao.wallet.mapper.WalletDrawMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.wallet.WalletDrawService;
import org.apache.commons.lang3.StringUtils;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("walletDrawService")
public class WalletDrawServiceImpl implements WalletDrawService {

    Logger logger = LoggerFactory.getLogger(WalletDrawServiceImpl.class);
    @Autowired
    @Qualifier(value = "walletDrawMapper")
    WalletDrawMapper walletDrawMapper;
    @Resource(name = "acctExchangeCashMapper")
    AcctExchangeCashMapper acctExchangeCashMapper;
    @Resource(name = "redisCommons")
    private RedisCommons redisCommons;
    @Autowired
    private WebUtils webUtils;

    /**
     * @param thdType
     * @param state
     * @param userId
     * @param checkerId
     * @param thdFlowId
     * @param applybgnTime
     * @param applyendTime
     * @param checkbgnTime
     * @param checkendTime
     * @param arrivebgnTime
     * @param arriveendTime
     * @param limit
     * @param offset
     * @return
     * @Description: 查询用户提现申请信息
     * @see com.rofour.baseball.service.wallet.WalletDrawService#getUserDrawList(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.Integer, java.lang.Integer,
     * java.lang.String, java.lang.String)
     */
    @Override
    public List<UserDrawCheckInfo> getUserDrawList(String thdType, String state, String userId, String checkerId,
                                                   String thdFlowId, String applybgnTime, String applyendTime, String checkbgnTime, String checkendTime,
                                                   String arrivebgnTime, String arriveendTime, Integer limit, Integer offset, String sort, String order) {
        List<UserDrawCheckInfo> drawCheckInfos = new ArrayList<UserDrawCheckInfo>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isBlank(thdType)) {
            map.put("thdType", thdType);
        }
        if (!StringUtils.isBlank(state)) {
            map.put("state", state);
        }
        if (!StringUtils.isBlank(userId)) {
            map.put("userId", userId);
        }
        if (!StringUtils.isBlank(checkerId)) {
            map.put("checkerId", checkerId);
        }
        if (!StringUtils.isBlank(thdFlowId)) {
            map.put("thdFlowId", thdFlowId);
        }
        if (!StringUtils.isBlank(applybgnTime)) {
            map.put("applybgnTime", applybgnTime);
        }
        if (!StringUtils.isBlank(applyendTime)) {
            map.put("applyendTime", applyendTime);
        }
        if (!StringUtils.isBlank(checkbgnTime)) {
            map.put("checkbgnTime", checkbgnTime);
        }
        if (!StringUtils.isBlank(checkendTime)) {
            map.put("checkendTime", checkendTime);
        }
        if (!StringUtils.isBlank(arrivebgnTime)) {
            map.put("arrivebgnTime", arrivebgnTime);
        }
        if (!StringUtils.isBlank(arriveendTime)) {
            map.put("arriveendTime", arriveendTime);
        }
        if (!StringUtils.isBlank(sort)) {
            map.put("sort", sort);
        }
        if (!StringUtils.isBlank(order)) {
            map.put("order", order);
        }
        map.put("offset", offset);
        map.put("limit", limit);
        List<UserDrawCheckBean> drawCheckBeans = walletDrawMapper.getUserDrawList(map);
        for (UserDrawCheckBean item : drawCheckBeans) {
            UserDrawCheckInfo checkInfo = new UserDrawCheckInfo();
            BeanUtils.copyProperties(item, checkInfo);
            drawCheckInfos.add(checkInfo);
        }
        return drawCheckInfos;
    }

    /**
     * @param thdType
     * @param state
     * @param userId
     * @param checkerId
     * @param thdFlowId
     * @param applybgnTime
     * @param applyendTime
     * @param checkbgnTime
     * @param checkendTime
     * @param arrivebgnTime
     * @param arriveendTime
     * @return
     * @Description: 查询用户提现申请信息总数
     * @see com.rofour.baseball.service.wallet.WalletDrawService#getUserDrawListCount(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public int getUserDrawListCount(String thdType, String state, String userId, String checkerId, String thdFlowId,
                                    String applybgnTime, String applyendTime, String checkbgnTime, String checkendTime, String arrivebgnTime,
                                    String arriveendTime) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isBlank(thdType)) {
            map.put("thdType", thdType);
        }
        if (!StringUtils.isBlank(state)) {
            map.put("state", state);
        }
        if (!StringUtils.isBlank(userId)) {
            map.put("userId", userId);
        }
        if (!StringUtils.isBlank(checkerId)) {
            map.put("checkerId", checkerId);
        }
        if (!StringUtils.isBlank(thdFlowId)) {
            map.put("thdFlowId", thdFlowId);
        }
        if (!StringUtils.isBlank(applybgnTime)) {
            map.put("applybgnTime", applybgnTime);
        }
        if (!StringUtils.isBlank(applyendTime)) {
            map.put("applyendTime", applyendTime);
        }
        if (!StringUtils.isBlank(checkbgnTime)) {
            map.put("checkbgnTime", checkbgnTime);
        }
        if (!StringUtils.isBlank(checkendTime)) {
            map.put("checkendTime", checkendTime);
        }
        if (!StringUtils.isBlank(arrivebgnTime)) {
            map.put("arrivebgnTime", arrivebgnTime);
        }
        if (!StringUtils.isBlank(arriveendTime)) {
            map.put("arriveendTime", arriveendTime);
        }
        int totalCount = walletDrawMapper.getUserDrawListCount(map);
        return totalCount;
    }

    /**
     * @return
     * @Description: 获取交易类型
     * @see com.rofour.baseball.service.wallet.WalletDrawService#getThdPaymentType()
     */
    @Override
    public List<SelectModel> getThdPaymentType() {
        List<Map<String, String>> payTypeList = walletDrawMapper.getThdPaymentType();
        List<SelectModel> selList = new ArrayList<SelectModel>();
        SelectModel sel = new SelectModel();
        sel.setId(" ");
        sel.setText("全部");
        selList.add(sel);
        for (Map<String, String> item : payTypeList) {
            String typeCode = String.valueOf(item.get("key"));
            //去掉指端支付(3)，现金(4)
            if (checkPaymentType(typeCode)) {
                sel = new SelectModel();
                sel.setId(typeCode);
                sel.setText(item.get("value"));
                selList.add(sel);
            }
        }
        return selList;
    }

    /**
     * @param exchangeId
     * @param isok          0失败，1成功
     * @param operationId
     * @param operationName
     * @param request
     * @throws IOException
     * @Description: 用户提现审核
     * @see com.rofour.baseball.service.wallet.WalletDrawService#userDrawCheck(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,java.lang.String,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public void userDrawCheck(String exchangeId, String isok, String operationId, String operationName, String flowId,
                              HttpServletRequest request) throws IOException {
        if (StringUtils.isBlank(isok) || StringUtils.isBlank(exchangeId)) {
            throw new BusinessException("110");
        }
        if ("0".equals(isok)) {// 审核失败,调用AXP接口
            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> walletTypeRef = new TypeReference<ResultInfo<?>>() {
            };
            Map<String, String> map = new HashMap<String, String>();
            map.put("thdFlowId", "");
            map.put("flowId", flowId);
            map.put("isPass", isok); // isPass 0-审批不通过 1-审批通过
            // http://15151m41o9.iok.la/axp-acl/acct/pay/dowebnotify.htm
            String url = Constant.axpurl.get("wallet_draw_serv");
            // 调用AXP收银台接口
            ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, walletTypeRef);
            // 返回参数赋值
            if (data.getSuccess() < 0) {
                logger.error("调用AXP接口失败:" + data.getCode() + "," + data.getMessage());
                throw new BusinessException("104");
            }
        }
        AcctExchangeCashBean editModel = walletDrawMapper.getUserDrawById(Long.parseLong(exchangeId));
        AcctExchangeCashBean addModel = new AcctExchangeCashBean();
        BeanUtils.copyProperties(editModel, addModel);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exchangeId", exchangeId);
        int state = CheckState.CHECKSUCCESS.getState(); // 审核成功
        if ("0".equals(isok)) {// 审核失败
            state = CheckState.CHECKFAIL.getState();
        }
        map.put("state", state);
        map.put("operationId", operationId);
        map.put("operationName", operationName);
        walletDrawMapper.updateStateById(map);
              // 记录日志
        addModel.setState((byte) state);
        addModel.setOperationId(Long.valueOf(operationId));
        addModel.setOperationName(operationName);
        webUtils.userEditLog(request, 136, editModel, addModel);
        if ("1".equals(isok)) {// 审核成功
            map.put("state", CheckState.CHECKSUCCESS.getState() + 1);
        } else if ("0".equals(isok)) {// 审核失败
            map.put("state", CheckState.CHECKFAIL.getState() + 1);
        }
        walletDrawMapper.insertExchangeState(map);
        webUtils.userAddLog(request, 136, map);

        if (isok.equals("0")) {
            String currentDate = DateTimeUtils.getCurrentDateString(DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT);
            Long userId = acctExchangeCashMapper.selectByExchangeId(Long.valueOf(exchangeId));
            redisCommons.hDecr(RedisKeyConstants.EXCHANGE_COUNT + userId, currentDate, 1);
            redisCommons.hDecr(RedisKeyConstants.EXCHANGE_BALANCE + userId, currentDate,
                    editModel.getBalance());
        }
    }

    /**
     * @param isok
     * @param operationId
     * @param operationName
     * @param request
     * @param exchangeId
     * @Description: 用户提现批量审核
     * @see com.rofour.baseball.service.wallet.WalletDrawService#BatchUserDrawCheck(java.lang.String,
     * java.lang.String, java.lang.String,
     * javax.servlet.http.HttpServletRequest,java.lang.String, java.lang.String[])
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public void BatchUserDrawCheck(String isok, String operationId, String operationName, HttpServletRequest request,
                                   String flowId, String... exchangeId) {
        if (StringUtils.isBlank(isok) || exchangeId.length < 1) {
            throw new BusinessException("110");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exchangeId", exchangeId);
        int state = CheckState.CHECKSUCCESS.getState(); // 审核成功
        if ("0".equals(isok)) {// 审核失败
            state = CheckState.CHECKFAIL.getState();
        }
        map.put("state", state);
        map.put("operationId", operationId);
        map.put("operationName", operationName);
        walletDrawMapper.BatchUpdateStateById(map);
        if ("1".equals(isok)) {// 审核成功
            map.put("state", CheckState.CHECKSUCCESS.getState() + 1);
        } else if ("0".equals(isok)) {// 审核失败
            map.put("state", CheckState.CHECKFAIL.getState() + 1);
        }
        walletDrawMapper.BatchInsertExchangeState(map);
    }

    /**
     * @param exchangeId
     * @param thdId
     * @throws IOException
     * @Description: 录入第三方交易流水
     * @see com.rofour.baseball.service.wallet.WalletDrawService#addThdId(java.lang.String, java.lang.String, java.lang.String,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public void addThdId(String exchangeId, String thdId, String flowId, HttpServletRequest request)
            throws IOException {
        if (StringUtils.isBlank(thdId) || StringUtils.isBlank(exchangeId)) {
            throw new BusinessException("110");
        }
        AcctExchangeCashBean editModel = walletDrawMapper.getUserDrawById(Long.parseLong(exchangeId));
        AcctExchangeCashBean addModel = new AcctExchangeCashBean();
        UserManagerLoginBean user = getCurrent(request);
        BeanUtils.copyProperties(editModel, addModel);
        // 定义反序列化 数据格式
        final TypeReference<ResultInfo<?>> walletTypeRef = new TypeReference<ResultInfo<?>>() {
        };
        Map<String, String> map = new HashMap<String, String>();
        map.put("thdFlowId", thdId);
        map.put("flowId", flowId);
        map.put("isPass", "1"); // isPass 0-审批不通过 1-审批通过
        // http://15151m41o9.iok.la/axp-acl/acct/pay/dowebnotify.htm
        String url = Constant.axpurl.get("wallet_draw_serv");
        // 调用AXP收银台接口
        ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, walletTypeRef);
        // 返回参数赋值
        if (data.getSuccess() < 0) {
            logger.error("调用AXP接口失败:" + data.getCode() + "," + data.getMessage());
            throw new BusinessException("104");
        }
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("exchangeId", exchangeId);
        map1.put("state", CheckState.ARRIVAL.getState());
        map1.put("thdFlowId", thdId);
        walletDrawMapper.updateStateById(map1);

        addModel.setState((byte) CheckState.ARRIVAL.getState());
        addModel.setThdFlowId(thdId);
        addModel.setOperationId(user.getUserManagerId());
        addModel.setOperationName(user.getUserName());
        // 记录日志
        webUtils.userEditLog(request, 136, editModel, addModel);
        map1.put("state", CheckState.ARRIVAL.getState() + 1);
        walletDrawMapper.insertExchangeState(map1);
        webUtils.userAddLog(request, 136, map1);
    }

    public UserManagerLoginBean getCurrent(HttpServletRequest request) {
        return (UserManagerLoginBean) request.getSession().getAttribute("user");
    }

    /**
     * 校验支付方式,只取微信，支付宝
     *
     * @param payMentType
     * @return
     */
    private boolean checkPaymentType(String payMentType) {
        ThirdPayType[] thridPayTypes = ThirdPayType.values();
        for (ThirdPayType thridPayType : thridPayTypes) {
            if (thridPayType.getPayMentType().equals(payMentType)) {
                return true;
            }
        }
        return false;
    }

    // 提现审核状态
    private enum CheckState {
        // 0 审核中 ，1审核成功， 2已到帐，3审核失败
        APPROVE(0), CHECKFAIL(3), CHECKSUCCESS(1), ARRIVAL(2);
        private int state;

        private CheckState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }

    /**
     * 忽略的支付方式
     */
    private enum ThirdPayType {
        WEIXIN_PAY("1", "微信"), ALI_PAY("2", "支付宝");
        private String payMentType;
        private String msg;

        private ThirdPayType(String payMentType, String msg) {
            this.payMentType = payMentType;
            this.msg = msg;
        }

        public String getPayMentType() {
            return payMentType;
        }

        public String getMsg() {
            return msg;
        }
    }
}
