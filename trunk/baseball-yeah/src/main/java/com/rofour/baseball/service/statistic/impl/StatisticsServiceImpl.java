package com.rofour.baseball.service.statistic.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.AttachConstant;
import com.rofour.baseball.common.Constant;
import com.rofour.baseball.common.HttpClientUtils;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.order.*;
import com.rofour.baseball.controller.model.wallet.AcctFlowInfo;
import com.rofour.baseball.controller.model.wallet.RequestAcctFlow;
import com.rofour.baseball.dao.order.mapper.StatisticsMapper;
import com.rofour.baseball.service.statistic.StatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: StatisticsServiceImpl
 * @Description:
 * @author ZXY
 * @date 2016/10/12 14:47
 */
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

    @Resource(name = "statisticsMapper")
    private StatisticsMapper statisticsMapper;


    /**
     * @Description: 获取订单量统计明细
     * @param requestOrderStatis

     * @return
     * @throws
     */
    @Override
    public Map getOrderStatis(RequestOrderStatis requestOrderStatis) throws Exception {
        if (StringUtils.isBlank(requestOrderStatis.getSort())) {
            requestOrderStatis.setSort("createDate");//默认以注册时间排序
            requestOrderStatis.setOrder("desc");//默认倒序
        }

        String startDate = requestOrderStatis.getStartDate();
        String endDate = requestOrderStatis.getEndDate();
        String updStartDate = requestOrderStatis.getUpdStartDate();
        String updEndDate = requestOrderStatis.getUpdEndDate();

        if(StringUtils.isNotBlank(startDate)){
            requestOrderStatis.setStartDate(startDate + " 00:00:00");
        }
        if(StringUtils.isNotBlank(endDate)){
            requestOrderStatis.setEndDate(endDate + " 23:59:59");
        }

        if(StringUtils.isNotBlank(updStartDate)){
            requestOrderStatis.setUpdStartDate(updStartDate + " 00:00:00");
        }
        if(StringUtils.isNotBlank(updEndDate)){
            requestOrderStatis.setUpdEndDate(updEndDate + " 23:59:59");
        }

        List<OrderStatisInfo> infoList = statisticsMapper.getOrderStatis(requestOrderStatis);
        int count = statisticsMapper.getOrderStatisCount(requestOrderStatis);
        Map map = new HashMap();
        map.put("list", infoList);
        map.put("count", count);
        return map;
    }

    /**
     * @Description: 获取订单量统计明细
     * @param requestOrderComment
     * @return
     * @throws
     */
    @Override
    public Map getOrderComment(RequestOrderComment requestOrderComment) throws Exception {
        if (StringUtils.isBlank(requestOrderComment.getSort())) {
            requestOrderComment.setSort("createDate");//默认以注册时间排序
            requestOrderComment.setOrder("desc");//默认倒序
        }

        String startDate = requestOrderComment.getCommentStartDate();
        String endDate = requestOrderComment.getCommentEndDate();

        if(StringUtils.isNotBlank(startDate)){
            requestOrderComment.setCommentStartDate(startDate + " 00:00:00");
        }
        if(StringUtils.isNotBlank(endDate)){
            requestOrderComment.setCommentEndDate(endDate + " 23:59:59");
        }

        List<OrderCommentInfo> infoList = statisticsMapper.getOrderComment(requestOrderComment);
        int count = statisticsMapper.getOrderCommentCount(requestOrderComment);
        Map map = new HashMap();
        map.put("list", infoList);
        map.put("count", count);
        return map;
    }

    /**
     * @Description: 获取订单评价明细
     * @param appraiseId
     * @return
     * @throws
     */
    @Override
    public OrderAppraiseInfo getOrderCommentDetail(Long appraiseId) throws Exception {
        OrderAppraiseInfo appraiseInfo = statisticsMapper.getOrderCommentDetail(appraiseId);
        Map<String, Object> map = new HashMap<>();
        map.put("bizId", appraiseInfo.getAppraiseId());
        map.put("attachType", AttachConstant.TYPE_PACKET_APPRAISE);

        String url = Constant.axpurl.get("resource_getNailAttachList_serv");
        // 定义反序列化 数据格式
        final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {};
        ResultInfo resultInfo = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
        if (null != resultInfo && resultInfo.getSuccess() == 1) {
            List<Map>  infoList = (List<Map>) resultInfo.getData();
            List urlList = new ArrayList();
            for (Map map1 : infoList){
                urlList.add(map1.get("nailFileUrl"));
            }
            appraiseInfo.setImgList(urlList);
        }
        return appraiseInfo;
    }

    /**
     * @Description: 获取订单量统计明细
     * @param requestAcctFlow
     * @return
     * @throws
     */
    @Override
    public Map getAcctFlow(RequestAcctFlow requestAcctFlow) throws Exception {
        if (StringUtils.isBlank(requestAcctFlow.getSort())) {
            requestAcctFlow.setSort("createTime");//默认以注册时间排序
            requestAcctFlow.setOrder("desc");//默认倒序
        }

        String startDate = requestAcctFlow.getStartDate();
        String endDate = requestAcctFlow.getEndDate();

        if(StringUtils.isNotBlank(startDate)){
            requestAcctFlow.setStartDate(startDate + " 00:00:00");
        }
        if(StringUtils.isNotBlank(endDate)){
            requestAcctFlow.setEndDate(endDate + " 23:59:59");
        }

        List<AcctFlowInfo> infoList = statisticsMapper.getAcctFlow(requestAcctFlow);
        int count = statisticsMapper.getAcctFlowCount(requestAcctFlow);
        Map map = new HashMap();
        map.put("list", infoList);
        map.put("count", count);
        return map;
    }
}
