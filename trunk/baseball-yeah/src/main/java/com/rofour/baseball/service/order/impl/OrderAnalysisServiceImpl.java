package com.rofour.baseball.service.order.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.common.DateUtil;
import com.rofour.baseball.dao.report.mapper.OrderAnalysisMapper;
import com.rofour.baseball.service.order.OrderAnalysisService;

/**
 * 订单运营统计分析
 * 
 * @author wuzhiquan
 *
 */
@Service("orderAnalysisService")
public class OrderAnalysisServiceImpl implements OrderAnalysisService {

    @Autowired
    @Qualifier("orderAnalysisMapper")
    OrderAnalysisMapper orderAnalysisMapper;

    @Override
    public Map<String, Object> getOrder(String day) {
        // 订单统计分析
        Map<String, Object> map = orderAnalysisMapper.getOrder(day);

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);

        if (null != map) {
            // “订单增长环比”转换为百分比
            map.put("orderNumQoq", nf.format(
                    null == map.get("orderNumQoq") ? 0D : Double.parseDouble(String.valueOf(map.get("orderNumQoq")))));
            // “订单增长同比”转换为百分比
            map.put("orderNumYoy", nf.format(
                    null == map.get("orderNumYoy") ? 0D : Double.parseDouble(String.valueOf(map.get("orderNumYoy")))));
            // 当天总订单数
            BigDecimal orderNum = null == map.get("orderNum") ? BigDecimal.ZERO
                    : new BigDecimal(String.valueOf(map.get("orderNum")));
            // 当天订单来源数：服务号
            BigDecimal orderSourceWeixin = null == map.get("orderSourceWeixin") ? BigDecimal.ZERO
                    : new BigDecimal(String.valueOf(map.get("orderSourceWeixin")));
            // 当天订单来源数：商户短信
            BigDecimal orderSourceSms = null == map.get("orderSourceSms") ? BigDecimal.ZERO
                    : new BigDecimal(String.valueOf(map.get("orderSourceSms")));
            // “当天订单来源数：服务号”计算百分比
            map.put("orderSourceWeixin", nf.format(orderNum.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO
                    : orderSourceWeixin.divide(orderNum, 4, BigDecimal.ROUND_HALF_UP)));
            // “当天订单来源数：商户短信”计算百分比
            map.put("orderSourceSms", nf.format(orderNum.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO
                    : orderSourceSms.divide(orderNum, 4, BigDecimal.ROUND_HALF_UP)));
            // “当天订单来源数：其他”计算百分比
            map.put("orderSourceOther",
                    nf.format(orderNum.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO
                            : orderNum.subtract(orderSourceWeixin).subtract(orderSourceSms).divide(orderNum, 4,
                                    BigDecimal.ROUND_HALF_UP)));
            // “订单类型：代取”计算百分比
            map.put("orderTypeTake",
                    nf.format(null == map.get("orderTypeTake") || orderNum.compareTo(BigDecimal.ZERO) <= 0
                            ? BigDecimal.ZERO
                            : new BigDecimal(String.valueOf(map.get("orderTypeTake"))).divide(orderNum, 4,
                                    BigDecimal.ROUND_HALF_UP)));
            // “订单类型：代寄”计算百分比
            map.put("orderTypeSend",
                    nf.format(null == map.get("orderTypeSend") || orderNum.compareTo(BigDecimal.ZERO) <= 0
                            ? BigDecimal.ZERO
                            : new BigDecimal(String.valueOf(map.get("orderTypeSend"))).divide(orderNum, 4,
                                    BigDecimal.ROUND_HALF_UP)));
            // “全国历史接单时效”取整
            BigDecimal takeOrderMinsAvg = null == map.get("takeOrderMinsAvg") ? BigDecimal.ZERO
                    : new BigDecimal(String.valueOf(map.get("takeOrderMinsAvg")));
            map.put("takeOrderMinsAvg", takeOrderMinsAvg.setScale(0, BigDecimal.ROUND_HALF_UP));
            // “当天接单时效”取整
            BigDecimal takeOrderMins = (null == map.get("takeOrderMins") ? BigDecimal.ZERO
                    : new BigDecimal(String.valueOf(map.get("takeOrderMins")))).subtract(takeOrderMinsAvg).setScale(0,
                            BigDecimal.ROUND_HALF_UP);
            map.put("takeOrderMins", takeOrderMins.compareTo(BigDecimal.ZERO) >= 0 ? "+" + takeOrderMins
                    : String.valueOf(takeOrderMins));
        }

        return map;
    }

    @Override
    public List<Map<String, Object>> getOrderByHours(String day) {
        // 各时段订单统计分析
        List<Map<String, Object>> list = orderAnalysisMapper.getOrderByHours(day);

        // 补齐0-23点数据
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, Map<String, Object>> hoursMap = new HashMap<String, Map<String, Object>>();
            Set<String> keySet = null;
            for (Map<String, Object> map : list) {
                if (null != map.get("hour")) {
                    BigDecimal payNum = null == map.get("payNum") ? BigDecimal.ZERO
                            : new BigDecimal(String.valueOf(map.get("payNum")));
                    map.put("payNum", payNum.divide(new BigDecimal(100), 0, BigDecimal.ROUND_HALF_UP));
                    map.put("hour", String.valueOf(map.get("hour")) + ":00");
                    hoursMap.put(String.valueOf(map.get("hour")), map);
                    if (null == keySet) {
                        keySet = map.keySet();
                    }
                }
            }
            list.clear();
            for (int i = 0; i < 24; i++) {
                Map<String, Object> map = hoursMap.get(i + ":00");
                if (null == map) {
                    map = new HashMap<String, Object>();
                    for (String key : keySet) {
                        map.put(key, 0);
                    }
                    map.put("hour", i + ":00");
                }
                list.add(map);
            }
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> getOrderAmountByTenDay(String day) {
        // 前10天订单金额统计分析
        List<Map<String, Object>> list = orderAnalysisMapper.getOrderAmountByTenDay(day);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map<String, Object> map : list) {
                BigDecimal payNum = null == map.get("payNum") ? BigDecimal.ZERO
                        : new BigDecimal(String.valueOf(map.get("payNum")));
                map.put("payNum", payNum.divide(new BigDecimal(100), 0, BigDecimal.ROUND_HALF_UP));
            }
        }
        // 处理日期
        disposeDays(list, day);
        return list;
    }

    @Override
    public List<Map<String, Object>> getOrderTypeByTenDay(String day) {
        // 前10天订单类型统计分析
        List<Map<String, Object>> list = orderAnalysisMapper.getOrderTypeByTenDay(day);
        // 处理日期
        disposeDays(list, day);
        return list;
    }

    @Override
    public List<Map<String, Object>> getOrderByAreas(String day) {
        // 各地区订单统计分析
        List<Map<String, Object>> list = orderAnalysisMapper.getOrderByAreas(day);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map<String, Object> map : list) {
                // “历史平均订单数”保留2位小数
                map.put("orderNumAvg", null == map.get("orderNumAvg") ? BigDecimal.ZERO
                        : new BigDecimal(String.valueOf(map.get("orderNumAvg"))).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getOrderWinnerByTenDay(String day) {
        // 前10天订单小派统计分析
        List<Map<String, Object>> list = orderAnalysisMapper.getOrderWinnerByTenDay(day);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map<String, Object> map : list) {
                // “接单时效”取整
                map.put("takeOrderMins",
                        null == map.get("takeOrderMins") ? BigDecimal.ZERO
                                : new BigDecimal(String.valueOf(map.get("takeOrderMins"))).setScale(0,
                                        BigDecimal.ROUND_HALF_UP));
            }
        }

        // 处理日期
        disposeDays(list, day);
        return list;
    }

    /**
     * 处理日期，补齐前10天数据
     * 
     * @param list
     * @param day
     * @author wuzhiquan
     */
    private void disposeDays(List<Map<String, Object>> list, String day) {
        Date date = DateUtil.fomatDate(day);
        if (CollectionUtils.isNotEmpty(list) && null != date) {

            Map<String, Map<String, Object>> daysMap = new HashMap<String, Map<String, Object>>();
            Set<String> keySet = null;
            for (Map<String, Object> map : list) {
                if (null != map.get("day")) {
                    daysMap.put(String.valueOf(map.get("day")), map);
                    if (null == keySet) {
                        keySet = map.keySet();
                    }
                }
            }

            list.clear();
            for (int i = 0; i < 10; i++) {
                String days = DateUtil.getAfterDay(date, i);
                Map<String, Object> map = daysMap.get(days);
                if (null == map) {
                    map = new HashMap<String, Object>();
                    for (String key : keySet) {
                        map.put(key, 0);
                    }
                    map.put("day", days);
                }
                list.add(map);
            }
        }
    }
}
