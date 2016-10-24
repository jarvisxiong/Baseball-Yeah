package com.zhiduan.axp.dao.message.mapper;

import java.util.List;

import com.zhiduan.axp.dao.message.bean.MessageSearchBean;
import com.zhiduan.axp.dao.message.bean.MessageSumBean;
import com.zhiduan.axp.dao.message.bean.SmsBean;
import org.apache.ibatis.annotations.Param;

import javax.inject.Named;

/**
 * @author wny
 * @ClassName: SmsMapper
 * @Description: 短信数据操作服务
 * @date 2016年4月5日 下午3:52:18
 */
@Named("smsMapper")
public interface SmsMapper {


    /**
     * @param where
     * @return
     * @Description: 根据条件查询短信记录
     */
    List<SmsBean> getSmsList( MessageSearchBean where);

    /**
     * 获取服务商短信分类汇总
     *
     * @param where 汇总条件
     * @return
     */
    List<MessageSumBean> getVendorSmsSumList(MessageSearchBean where);

    /**
     * 根据条件获取短信记录个数
     *
     * @param where
     * @return
     */
    Integer getSmsTotal(MessageSearchBean where);

}