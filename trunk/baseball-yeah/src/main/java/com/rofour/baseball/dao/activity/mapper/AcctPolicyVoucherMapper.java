package com.rofour.baseball.dao.activity.mapper;

import com.rofour.baseball.controller.model.activity.ReturnVoucherInfo;
import com.rofour.baseball.controller.model.activity.UsableVoucherInfo;
import com.rofour.baseball.controller.model.wallet.AcctPolicyVoucherInfo;
import com.rofour.baseball.dao.activity.bean.AcctPolicyVoucherBean;

import java.util.List;

import javax.inject.Named;

@Named("acctPolicyVoucherMapper")
public interface AcctPolicyVoucherMapper {
    int deleteByPrimaryKey(Long voucherId);

    int deleteByPolicyId(Integer policyId);

    int insert(AcctPolicyVoucherBean record);

    int insertSelective(AcctPolicyVoucherBean record);

    int insertSelectiveBatch(List<AcctPolicyVoucherBean> trainRecordList);

    AcctPolicyVoucherBean selectByPrimaryKey(Long voucherId);

    int updateByPrimaryKeySelective(AcctPolicyVoucherBean record);

    int updateByPrimaryKey(AcctPolicyVoucherBean record);

    /**
     * @param acctPolicyVoucherBean
     * @return
     * @Description: 查询用户代金券数量
     */
    Integer getUserVoucherCount(AcctPolicyVoucherBean acctPolicyVoucherBean);

    /**
     * @param acctPolicyVoucherBean
     * @return
     * @Description: 查询用户代金券列表
     */
    List<UsableVoucherInfo> queryUserVoucherList(AcctPolicyVoucherBean acctPolicyVoucherBean);

    /**
     * @param voucherId
     * @return
     * @Description:重新启用代金券
     */

    int cancleVoucher(Long voucherId);


    /**
     * @param voucherId
     * @return
     * @Description: 使用代金券
     */

    int useVoucher(Long voucherId);


    /**
     * @param userId
     * @return
     * @Description: 获取用户代金券
     */

    List<ReturnVoucherInfo> getUserVoucher(Long userId);

    /**
     * @param info
     * @return
     */
    List<AcctPolicyVoucherInfo> selectPolicyVoucherInfo(AcctPolicyVoucherInfo info);

    int selectPolicyVoucherInfoCount(AcctPolicyVoucherInfo info);
}