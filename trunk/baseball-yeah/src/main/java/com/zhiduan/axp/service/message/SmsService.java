package com.zhiduan.axp.service.message;

import com.zhiduan.axp.controller.model.message.MessageSearchInfo;
import com.zhiduan.axp.controller.model.message.MessageSumInfo;
import com.zhiduan.axp.controller.model.message.SmsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 短信服务
 * Created by wny on 2016-06-16.
 */
public interface SmsService {

    /**
     * @param where
     * @return
     * @Description: 根据条件查询短信记录
     */
    List<SmsInfo> getSmsList(@Param("where") MessageSearchInfo where);

    /**
     * 获取服务商短信分类汇总
     *
     * @param where 汇总条件
     * @return
     */
    List<MessageSumInfo> getVendorSmsSumList(@Param("where") MessageSearchInfo where);

    /**
     * 根据条件获取短信记录个数
     *
     * @param where
     * @return
     */
    Integer getSmsTotal(@Param("where") MessageSearchInfo where);

}
