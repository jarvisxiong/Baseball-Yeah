package com.rofour.baseball.controller.report;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.common.ObjectExcelView;
import com.rofour.baseball.common.PageData;
import com.rofour.baseball.common.StringUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.WayBillLogInfo;
import com.rofour.baseball.controller.model.report.ReportOfflinePartyInfo;
import com.rofour.baseball.controller.model.report.SearchPhoneCountInfo;
import com.rofour.baseball.dao.report.bean.ReportVoucherBean;
import com.rofour.baseball.dao.report.mapper.ReportVoucherMapper;
import com.rofour.baseball.dao.user.bean.UserBean;
import com.rofour.baseball.service.report.ReportVoucherService;
import com.rofour.baseball.service.report.ReportWaybillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ReportWaybillController
 * @Description: 运单报表控制器
 * @author ZXY
 * @date 2016/4/26 15:39
 */
@Controller
@RequestMapping("/report/voucher")
public class ReportVoucherController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("messageSource")
    private MessageSource messageSource;

    @Autowired
    @Qualifier("reportVoucherService")
    private ReportVoucherService reportVoucher;

    /**
     * @Description: 学校运单上手机号的统计信息
     * @param  request
     * @return ResultInfo
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/queryReportVoucherList", method = RequestMethod.GET)
    public  DataGrid<ReportVoucherBean> SelectReportVoucherList(HttpServletRequest request,HttpServletResponse response,ReportVoucherBean voucherBean) {
        try {
//            voucherBean.setCollegeId(voucherBean.getCollegeId().trim());
//            voucherBean.setCityId(voucherBean.getCityId().trim());
            List<ReportVoucherBean> list = reportVoucher.getVoucherList(voucherBean);//查询数据列表
            Integer total = reportVoucher.getVoucherListCount(voucherBean);//查询记录总数

            DataGrid<ReportVoucherBean> dataList = new DataGrid<ReportVoucherBean>();
            dataList.setRows(list);
            dataList.setTotal(total);
            return dataList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /*报表导出demon*/
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public ModelAndView exportExcel(HttpServletRequest request,HttpServletResponse response,ReportVoucherBean voucherBean) {
        ModelAndView mv = new ModelAndView();
        try {

            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<String> titles = new ArrayList<String>();

            titles.add("序号"); // 1
            titles.add("代金券ID"); // 2
            titles.add("校区名称"); // 3
            titles.add("城市"); // 4
            titles.add("订单号"); // 5
            titles.add("代金券金额"); // 6
            titles.add("订单状态"); // 7
            titles.add("订单入口"); // 8
            titles.add("渠道来源"); // 9
            titles.add("领用时间"); // 10
            titles.add("使用时间"); // 11
            titles.add("失效时间"); // 12
            dataMap.put("titles", titles);

            List<ReportVoucherBean> reportList = reportVoucher.getAllUsers(voucherBean);
            List<PageData> varList = new ArrayList<PageData>();
            for (int i = 0; i < reportList.size(); i++) {
                PageData vpd = new PageData();
//                vpd.put("var1", i + ""); // 1
                vpd.put("var1", reportList.get(i).getRowNo() + ""); // 1
                vpd.put("var2", reportList.get(i).getVoucherId() + ""); // 2
                vpd.put("var3", reportList.get(i).getCollegeName() + ""); // 3
                vpd.put("var4", reportList.get(i).getCityName() + ""); // 4
                vpd.put("var5", reportList.get(i).getOrderId() + ""); // 5
                vpd.put("var6", reportList.get(i).getMoney() + ""); // 6
                vpd.put("var7", reportList.get(i).getState() + ""); // 7
                vpd.put("var8", reportList.get(i).getChannelName() + ""); // 8
                vpd.put("var9", reportList.get(i).getItemName() + ""); // 9
                vpd.put("var10", reportList.get(i).getReceiveTime() + ""); // 10
                vpd.put("var11", reportList.get(i).getUseTime() + ""); // 11
                vpd.put("var12", reportList.get(i).getExpireTime() + ""); // 12
                varList.add(vpd);
            }
            dataMap.put("varList", varList);
            ObjectExcelView erv = new ObjectExcelView(); // 执行excel操作
            mv = new ModelAndView(erv, dataMap);
        } catch (Exception e) {
        }
        return mv;
    }
}
