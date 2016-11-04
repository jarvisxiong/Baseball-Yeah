package com.rofour.baseball.service.message;

import com.rofour.baseball.controller.model.message.MsgTmplSmsInfo;
import com.rofour.baseball.controller.model.message.SmsModelForUserInfo;
import com.rofour.baseball.dao.message.bean.SmsModelForUserBean;

import java.util.List;

/**
 * @ClassName: SmsModelForUserService
 * @Description: 用户自定义模板服务
 * @author: xulang
 * @date: 2016年09月20日 13:22
 */

public interface SmsModelForUserService {

    int delUpdate(SmsModelForUserBean record);

    int insert(SmsModelForUserInfo record);

    SmsModelForUserInfo selectById(Long smsModelId);

    int update(SmsModelForUserInfo record);

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
}
