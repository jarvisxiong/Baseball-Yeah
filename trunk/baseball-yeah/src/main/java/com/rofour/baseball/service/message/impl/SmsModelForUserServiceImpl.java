/**
 * Copyright (c) 2016, 指端科技.
 */
package com.rofour.baseball.service.message.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.AttachConstant;
import com.rofour.baseball.common.Constant;
import com.rofour.baseball.common.HttpClientUtils;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.message.SmsModelForUserInfo;
import com.rofour.baseball.controller.model.order.OrderInfo;
import com.rofour.baseball.controller.model.order.RequestOrderInfo;
import com.rofour.baseball.dao.message.bean.SmsModelForUserBean;
import com.rofour.baseball.dao.message.mapper.SmsModelForUserMapper;
import com.rofour.baseball.service.message.SmsModelForUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SmsModelForUserServiceImpl
 * @Description: 用户自定义模板服务实现
 * @author: xulang
 * @date: 2016年09月20日 13:24
 */
@Service("smsModelForUserService")
public class SmsModelForUserServiceImpl implements SmsModelForUserService {

    @Autowired
    @Qualifier("smsModelForUserMapper")
    private SmsModelForUserMapper smsModelForUserMapper;

    @Override
    public int delUpdate(SmsModelForUserBean smsModelId) {
        return smsModelForUserMapper.delUpdate(smsModelId);
    }

    @Override
    public int insert(SmsModelForUserInfo record) {
        SmsModelForUserBean smsModelForUserBean = new SmsModelForUserBean();
        BeanUtils.copyProperties(record, smsModelForUserBean);
        return smsModelForUserMapper.insertSelective(smsModelForUserBean);
    }

    @Override
    public SmsModelForUserInfo selectById(Long smsModelId) {
        SmsModelForUserBean smsModelForUserBean = smsModelForUserMapper.selectByPrimaryKey(smsModelId);
        SmsModelForUserInfo smsModelForUserInfo = new SmsModelForUserInfo();
        BeanUtils.copyProperties(smsModelForUserBean, smsModelForUserInfo);
        return smsModelForUserInfo;
    }

    @Override
    public int update(SmsModelForUserInfo record) {
        SmsModelForUserBean smsModelForUserBean = new SmsModelForUserBean();
        BeanUtils.copyProperties(record, smsModelForUserBean);
        return smsModelForUserMapper.updateByPrimaryKey(smsModelForUserBean);
    }


    @Override
    public int auditSms(SmsModelForUserBean smsModelForUserBean) {
//        SmsModelForUserBean smsModelForUserBean = new SmsModelForUserBean();
//        BeanUtils.copyProperties(record, smsModelForUserBean);
        int i = smsModelForUserMapper.auditSms(smsModelForUserBean);
        try {
            if(i > 0){
                if(smsModelForUserBean.getSmsModelId()!=null && smsModelForUserBean.getUserId()!=null && smsModelForUserBean.getCreateUserPhone()!=null){
                    Map<String, Object> map = new HashMap<String, Object>();
                    if (smsModelForUserBean.getStateNum().equals("1")) {//通过
                        map.put("msgType", "GOODS_SYSNOTICE_SMSTMPL_CHECK_SUCESS");
                    } else if (smsModelForUserBean.getStateNum().equals("3")) {//拒绝
                        map.put("msgType", "GOODS_SYSNOTICE_SMSTMPL_CHECK_FAIL");
                    }
                    map.put("bizId", smsModelForUserBean.getSmsModelId());
                    map.put("receiveUserId", smsModelForUserBean.getUserId());
                    map.put("receivePhone", smsModelForUserBean.getCreateUserPhone());
//                    map.put("receivePhone", "18551723901");
                    map.put("sendUserId", null);
                    String url = Constant.axpurl.get("send_message_serv");
                    // 定义反序列化 数据格式
                    final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {};
                    ResultInfo result = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
      return i;
    }

    @Override
    public int batchAudit(SmsModelForUserBean smsModelForUserBean) {
        int i = 0;
        try{
            i = smsModelForUserMapper.batchAudit(smsModelForUserBean);
            if(i>0){
                Map<String, Object> map = new HashMap<String, Object>();
                if(smsModelForUserBean!=null){
                    List<String> list = smsModelForUserBean.getUserIdPhoneArr();
                    if(list!=null && list.size()>0){
                        for(int n=0;n<list.size();n++){
                            String str[] = list.get(n).split("~",-1);
                            if(str.length>2){
                                String bizId = str[0];
                                String receiveUserId = str[1];
                                String receivePhone = str[2];
                                if(smsModelForUserBean.getStateNum().equals("1")){//通过
                                    map.put("msgType", "GOODS_SYSNOTICE_SMSTMPL_CHECK_SUCESS");
                                }else if(smsModelForUserBean.getStateNum().equals("3")){//拒绝
                                    map.put("msgType", "GOODS_SYSNOTICE_SMSTMPL_CHECK_FAIL");
                                }
                                map.put("bizId", bizId);
                                map.put("receiveUserId",receiveUserId);
                                map.put("receivePhone", receivePhone);
                                map.put("sendUserId",null);
                                String url = Constant.axpurl.get("send_message_serv");
                                // 定义反序列化 数据格式
                                final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {};
                                ResultInfo result = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<SmsModelForUserBean> getSmsAuditList(SmsModelForUserBean smsModelForUserBean){
        List<SmsModelForUserBean> list = smsModelForUserMapper.getSmsAuditList(smsModelForUserBean);//获取短信模板列表
        if(!list.isEmpty()){//循环查询负责人信息
            Map<String,String> map = new HashMap<String,String>();
            SmsModelForUserBean bean = new SmsModelForUserBean();
             for(int i=0;i<list.size();i++){
                 if(list.get(i).getStoreId()!=null){
                     map.put(i+"",list.get(i).getStoreId());
                     /*Map<String,Object> map = smsModelForUserMapper.queryHeadUser(list.get(i));
                     if(!map.isEmpty()){
                         list.get(i).setHeadUserId(map.get("user_id")==null?"":map.get("user_id").toString());//负责人ID
                         list.get(i).setHeadUserPhone(map.get("user_name")==null?"":map.get("user_name").toString());//负责人手机
                         list.get(i).setHeadUserName(map.get("real_name")==null?"":map.get("real_name").toString());//负责人姓名
                     }*/
                 }
             }
            bean.setStoreIdMap(map);
            List<SmsModelForUserBean> resultList = smsModelForUserMapper.queryHeadUser(bean);
            for(int i=0;i<resultList.size();i++){
                for(int j=0;j<list.size();j++){
                    if(resultList.get(i)!=null && list.get(j).getStoreId().equals(resultList.get(i).getHeadUserId())){
                        list.get(j).setHeadUserId(resultList.get(i).getHeadUserId());//负责人ID
                        list.get(j).setHeadUserPhone(resultList.get(i).getHeadUserPhone().toString());//负责人手机
                        list.get(j).setHeadUserName(resultList.get(i).getHeadUserName().toString());//负责人姓名
                    }
                }
            }
        }
        return list;
    }

    @Override
    public int getSmsAuditListTotal(SmsModelForUserBean smsModelForUserBean){
        return smsModelForUserMapper.getSmsAuditListTotal(smsModelForUserBean);
    }

    public SmsModelForUserBean getSmsAuditView(SmsModelForUserBean smsModelForUserBean){
        return smsModelForUserMapper.getSmsAuditView(smsModelForUserBean);
    }
}
