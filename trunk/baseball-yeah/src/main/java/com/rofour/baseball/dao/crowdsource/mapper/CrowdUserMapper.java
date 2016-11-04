package com.rofour.baseball.dao.crowdsource.mapper;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.dao.crowdsource.bean.CollegePieBean;
import com.rofour.baseball.dao.crowdsource.bean.CrowdsourceBean;
import com.rofour.baseball.dao.order.bean.PacketOrderBean;

import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */

@Named("crowdUserMapper")
public interface CrowdUserMapper {

    List<CrowdsourceBean> getCrowdsourceList(CrowdsourceBean crowdsourceBean);

    List<CrowdsourceBean> getListForCoOrCollege(CrowdsourceBean crowdsourceBean);

    int getCrowdsourceListCount(CrowdsourceBean crowdsourceBean);

    int getListForCoOrCollegeCount(CrowdsourceBean crowdsourceBean);

    CrowdsourceBean getCrowdUserDetail(CrowdsourceBean crowdsourceBean);

    CollegePieBean getCollegeDetail(CollegePieBean collegePieBean);

    int updateState(CrowdsourceBean crowdsourceBean);

    int updateUser(CrowdsourceBean crowdsourceBean);


    PacketOrderBean getOrderDetail(PacketOrderBean bean);
    /**
     *
     * @Description: 获取已上传的用户证件的url
     * @param userId
     * @return 操作结果
     */
    public ResultInfo getCredentialUrls(Long userId);
}
