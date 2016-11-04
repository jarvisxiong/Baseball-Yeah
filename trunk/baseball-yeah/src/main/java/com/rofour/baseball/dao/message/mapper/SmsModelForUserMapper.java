package com.rofour.baseball.dao.message.mapper;


import com.rofour.baseball.controller.model.message.SmsModelForUserInfo;
import com.rofour.baseball.dao.message.bean.SmsModelForUserBean;

import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SmsModelForUserMapper
 * @Description: 用户自定义模板数据库操作接口
 * @author: xulang
 * @Date: 2016-09-20 13:21
 */
@Named("smsModelForUserMapper")
public interface SmsModelForUserMapper {
    int delUpdate(SmsModelForUserBean smsModelBean);

    int insertSelective(SmsModelForUserBean record);

    SmsModelForUserBean selectByPrimaryKey(Long smsModelId);

    int updateByPrimaryKey(SmsModelForUserBean record);

    /**
     * 审核短信模板保存操作
     * @param record
     * @return
     */
    int auditSms(SmsModelForUserBean record);

    int batchAudit(SmsModelForUserBean record);

    /**
     * 按条件查询短信模板列表
     *
     * @param smsModelForUserBean
     * @return
     */
    List<SmsModelForUserBean> getSmsAuditList(SmsModelForUserBean smsModelForUserBean);

    /**
     * 查询短信模板总数
     * @param smsModelForUserBean
     * @return
     */
    int getSmsAuditListTotal(SmsModelForUserBean smsModelForUserBean);

    /**
     * 根据短信模板Id查询详情
     * @param smsModelForUserBean
     * @return
     */
    SmsModelForUserBean getSmsAuditView(SmsModelForUserBean smsModelForUserBean);

    /**
     * 查询负责人详细，根据storeId
     * @param smsModelForUserBean
     * @return
     */
    List<SmsModelForUserBean> queryHeadUser(SmsModelForUserBean smsModelForUserBean);
}