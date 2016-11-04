package com.rofour.baseball.service.order;


import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.user.ExpUserAuditInfo;
import com.rofour.baseball.controller.model.user.UserCheckInfo;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderVerifyService {
    /**
     * 获取用户审核数据
     * @return
     * @throws Exception
     */
    public List<UserCheckInfo> getAuditUsers(UserCheckInfo user)throws Exception;

    /**
     * 获取用户审核数量
     * @return
     * @throws Exception
     */
    public int getAuditUsersCount(UserCheckInfo user)throws Exception;

    /**
     * 获取用户审核信息
     * @param userId
     * @return
     * @throws Exception
     */
    public ResultInfo getAudit(long userId) throws Exception;

    /**
     * 众包信息审核
     * @param userAuditInfo 用户审核信息
     * @return
     * @throws Exception
     */
    public ResultInfo expAudit(ExpUserAuditInfo userAuditInfo, UserManagerLoginBean loginBean, HttpServletRequest request) throws Exception;

    List<UserCheckInfo> getManageUsers(UserCheckInfo user)throws Exception;

    int getManageUsersCount(UserCheckInfo user)throws Exception;

    ResultInfo activation(UserCheckInfo userInfo, HttpServletRequest request) ;

    /**
     * @Description: 设置小派标签
     * @param
     * @return
     * @throws
     */
    void setLabel(long pUserId, String remark) throws Exception;

}
