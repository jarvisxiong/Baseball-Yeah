package com.rofour.baseball.service.crowdsource;

import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.dao.crowdsource.bean.CollegePieBean;
import com.rofour.baseball.dao.crowdsource.bean.CrowdsourceBean;
import com.rofour.baseball.dao.order.bean.PacketOrderBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
public interface CrowdUserService {

    /**
     * 查询小派列表
     * @param crowdsourceBean
     * @return
     * @throws Exception
     */
    DataGrid<CrowdsourceBean> getCrowdsourceList(CrowdsourceBean crowdsourceBean) throws Exception;

    /**
     * 查询小派列表总数
     * @param crowdsourceBean
     * @return
     * @throws Exception
     */
    int getCrowdsourceListCount(CrowdsourceBean crowdsourceBean) throws Exception;

    /**
     * 根据UserId查询小派详情
     * @param crowdsourceBean
     * @return
     * @throws Exception
     */
    CrowdsourceBean getCrowdUserDetail(CrowdsourceBean crowdsourceBean) throws Exception;

    /**
     * 根据校园ID查询校园详情
     * @param collegePieBean
     * @return
     * @throws Exception
     */
    CollegePieBean getCollegeDetail(CollegePieBean collegePieBean)throws Exception;

    /**
     * 修改账户状态（支持批量）
     * @param crowdsourceBean
     * @return
     * @throws Exception
     */
    int updateState(CrowdsourceBean crowdsourceBean,HttpServletRequest request)throws Exception;
    int UpdateUser(CrowdsourceBean crowdsourceBean)throws Exception;

    /**
     * 查询小派订单详情
     * @param bean
     * @return
     * @throws Exception
     */
    PacketOrderBean getOrderDetail(PacketOrderBean bean)throws Exception;
}
