/**
 * @Title: ReportWaybillMapper.java
 * @package com.rofour.baseball.idl.reportcenter.dao
 * @Project: axp-idl
 * @Description: (用一句话描述该文件做什么)
 * @author heyi
 * @date 2016年4月18日 下午5:38:35
 * @version V1.0
 * ──────────────────────────────────
 * Copyright (c) 2016, 指端科技.
 */


package com.rofour.baseball.dao.report.mapper;

import com.rofour.baseball.controller.model.WayBillLogInfo;
import com.rofour.baseball.controller.model.report.ReportCityPhoneInfo;
import com.rofour.baseball.dao.report.bean.ReportVoucherBean;
import com.rofour.baseball.dao.report.bean.SearchPhoneCountBean;

import javax.inject.Named;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: ReportVoucherMapper
 * @Description: 代金券报表
 * @author lijun
 * @date 2016/9/29 09:31
 *
 */
@Named("reportVoucherMapper")
public interface ReportVoucherMapper {
    /**
     *
     * @Method: getVoucherList
     * @Description:
     * @param @return    参数
     * @return List<ReportVoucherBean>    返回类型
     * @throws
     * @author lijun
     * @date 2016/9/29 09:31
     *
     */
    List<ReportVoucherBean> getVoucherList(ReportVoucherBean bean);

    /**
     * 查询数据记录总数
     * @param bean
     * @return
     * @throws Exception
     */
    int getVoucherListCount(ReportVoucherBean bean);

    List<ReportVoucherBean> getAllUsers(ReportVoucherBean bean);

}
