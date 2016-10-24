package com.zhiduan.axp.controller.message;

import com.sun.nio.sctp.MessageInfo;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.controller.model.message.MessageSearchInfo;
import com.zhiduan.axp.controller.model.message.MessageSumInfo;
import com.zhiduan.axp.controller.model.message.SmsInfo;
import com.zhiduan.axp.controller.model.waybill.ScanInfo;
import com.zhiduan.axp.service.message.SmsService;
import com.zhiduan.axp.service.waybill.WaybillScanService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * 短信控制层
 * Created by wny on 2016-06-16.
 */
@Controller
@RequestMapping(value = "/message/sms")
public class SmsController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("smsService")
    private SmsService smsService;

    @Autowired
    @Qualifier("waybillScanService")
    private WaybillScanService scanService;

    @RequestMapping(value = "gotoSmsSearch", method = RequestMethod.GET)
    public ModelAndView smsSearchIndex(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("message/sms/smsSearch");
        } else {
            return new ModelAndView("/");
        }
    }

    @RequestMapping(value = "gotosmsVendorRecon", method = RequestMethod.GET)
    public ModelAndView smsVendorReconIndex(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("message/sms/smsVendorRecon");
        } else {
            return new ModelAndView("/");
        }
    }

    /**
     * @param request
     * @return ResultInfo 操作结果bean
     * @Description: 通过组名获取属性字典列表
     */
    @RequestMapping(value = "/getSmsStatus", method = RequestMethod.GET)
    @ResponseBody
    public void getSmsStatus(HttpServletRequest request, HttpServletResponse response) {
        List<SelectModel> sellist = new ArrayList<>();
        SelectModel sel = new SelectModel();
        sel.setText("请选择");
        sel.setId(" ");
        sellist.add(sel);
        SelectModel sel1 = new SelectModel();
        sel1.setText("发送中");
        sel1.setId("0");
        sellist.add(sel1);
        SelectModel sel2 = new SelectModel();
        sel2.setText("已发送未回执");
        sel2.setId("1");
        sellist.add(sel2);
        SelectModel sel3 = new SelectModel();
        sel3.setText("成功");
        sel3.setId("2");
        sellist.add(sel3);
        SelectModel sel4 = new SelectModel();
        sel4.setText("失败");
        sel4.setId("-1");
        sellist.add(sel4);
        writeJson(sellist, response);
    }

    /**
     * @param request
     * @return ResultInfo 操作结果bean
     * @Description: 获取短信会汇总方式（all:总统计;day:按日;month:按月)
     */
    @RequestMapping(value = "/getSumMethod", method = RequestMethod.GET)
    @ResponseBody
    public void getSmsSumMethod(HttpServletRequest request, HttpServletResponse response) {
        List<SelectModel> sellist = new ArrayList<>();
        SelectModel sel3 = new SelectModel();
        sel3.setText("按月统计");
        sel3.setId("month");
        sellist.add(sel3);
        SelectModel sel2 = new SelectModel();
        sel2.setText("按日统计");
        sel2.setId("day");
        sellist.add(sel2);
        SelectModel sel1 = new SelectModel();
        sel1.setText("总统计");
        sel1.setId("all");
        sellist.add(sel1);


        writeJson(sellist, response);
    }

    @RequestMapping(value = "/querySmsGrid", method = RequestMethod.POST)
    public
    @ResponseBody
    DataGrid<SmsInfo> querySmsGrid(
            HttpServletRequest request, HttpServletResponse response, @RequestBody MessageSearchInfo info) {

        if (null == info.getOffset()) {
            info.setOffset(0);
        }
        if (null == info.getLimit()) {
            info.setLimit(10);
        }
        if (StringUtils.isBlank(info.getSort())) {
            info.setOrder("desc");
            info.setSort("submitTime");
        }

        List<SmsInfo> list = smsService.getSmsList(info);
        Integer total = smsService.getSmsTotal(info);
        DataGrid<SmsInfo> dataList = new DataGrid<>();
        dataList.setRows(list);
        dataList.setTotal(total);
        return dataList;
    }

    @RequestMapping(value = "/querySmsVendorSumGrid", method = RequestMethod.POST)
    public
    @ResponseBody
    void querySmsVendorSumGrid(
            HttpServletRequest request, HttpServletResponse response, @RequestBody MessageSearchInfo info) {

        List<MessageSumInfo> list = smsService.getVendorSmsSumList(info);
        writeJson(list, response);
    }

    /**
     * 根据 bizId和bizType获取业务信息
     *
     * @param request
     * @param response
     * @param bizId    业务id
     * @param bizType  业务类型
     */
    @RequestMapping(value = "/queryBizInfo", method = RequestMethod.POST)
    public
    @ResponseBody
    void queryBizInfo(
            HttpServletRequest request, HttpServletResponse response, @RequestBody MessageSearchInfo info) {

        if (com.zhiduan.axp.common.ConstantFunction.getMessageTypeComscan().equals(info.getType())) {
            //  List<ScanInfo> scanList=new ArrayList<>();
            ScanInfo temp = scanService.getScanInfoById(info.getBizId());
            // scanList.add(temp);
            writeJson(temp, response);
        } else {
            writeJson(null, response);
        }
    }
}
