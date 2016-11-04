package com.rofour.baseball.service.report;

import com.rofour.baseball.controller.model.WayBillLogInfo;
import com.rofour.baseball.controller.model.report.ReportCityPhoneInfo;
import com.rofour.baseball.controller.model.report.SearchPhoneCountInfo;
import com.rofour.baseball.dao.report.bean.ReportVoucherBean;

import java.util.List;
import java.util.Map;


/**
 * @ClassName: ReportVoucherService
 * @Description: 代金券报表
 * @author lijun
 * @date 2016/9/29 09:31
 */
public interface ReportVoucherService {
    /**
     * @Description: 统计学校运单手机号
     * @param info
     * @return List<ReportVoucherBean>
     * @throws
     */
    List<ReportVoucherBean> getVoucherList(ReportVoucherBean info) throws Exception;

    /**
     * 查询数据记录总数
     * @param info
     * @return
     * @throws Exception
     */
    int getVoucherListCount(ReportVoucherBean info) throws Exception;

    List<ReportVoucherBean> getAllUsers(ReportVoucherBean info);

}
