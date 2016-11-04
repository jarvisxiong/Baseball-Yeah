package com.rofour.baseball.dao.user.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.dao.user.bean.UserSmsModelBean;
/**
* @ClassName: UserSmsModelMapper
* @Description: 用户短信模板接口
*
*/
    
@Named("userSmsModelMapper")
public interface UserSmsModelMapper {


    /**
     *获取符合条件的短信模板数量
     * @param record
     * @return
     */
    int getUserSmsModelCount(UserSmsModelBean record);


    /**
     *
     * @param record
     * @return
     */
    List<UserSmsModelBean> getUserSmsModel(UserSmsModelBean record);


    int getUserSmsCount(UserSmsModelBean record);


    /**
     *
     * @param record
     * @return
     */
    List<UserSmsModelBean> getUserSms(UserSmsModelBean record);
}