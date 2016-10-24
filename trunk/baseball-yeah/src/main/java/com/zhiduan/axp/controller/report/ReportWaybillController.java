package com.zhiduan.axp.controller.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiduan.axp.common.JsonUtils;
import com.zhiduan.axp.common.StringUtils;
import com.zhiduan.axp.common.Tools;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.WayBillLogInfo;
import com.zhiduan.axp.controller.model.report.ReportStoreSmsInfo;
import com.zhiduan.axp.controller.model.report.SearchPhoneCountInfo;
import com.zhiduan.axp.service.report.ReportWaybillService;

/**
 * @ClassName: ReportWaybillController
 * @Description: 运单报表控制器
 * @author ZXY
 * @date 2016/4/26 15:39
 */
@Controller
@RequestMapping("/report/waybill")
public class ReportWaybillController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("messageSource")
    private MessageSource messageSource;

    @Autowired
    @Qualifier("reportWaybillService")
    private ReportWaybillService reportWaybill;

    /**
     * @Description: 学校运单上手机号的统计信息
     * @param  request
     * @return ResultInfo
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/phoneCountInfo", method = RequestMethod.GET)
    public  List<SearchPhoneCountInfo> SelectPhoneCountInfoFromBill(HttpServletRequest request,HttpServletResponse response) {
   	 List<SearchPhoneCountInfo> dataList=new ArrayList<SearchPhoneCountInfo>();
        try {
            String data = request.getParameter("data");
           /* if (StringUtils.isEmpty(data)) {
                return new ResultInfo(-1, "111", "参数不能为空");
            }*/
            SearchPhoneCountInfo info = JsonUtils.readValue(data, SearchPhoneCountInfo.class);
            dataList = reportWaybill.getPhoneCount(info);
            return dataList;
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        	 return dataList;
        }
    }

    /**
     * @Description: 学校运单上手机号的明细信息
     * @param  request
     * @return ResultInfo
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/phoneCountDetail", method = RequestMethod.POST)
    public  List<SearchPhoneCountInfo> SelectPhoneCountDetailFromBill(HttpServletRequest request,HttpServletResponse response) {
        //DataGrid<SearchPhoneCountInfo> grid = new DataGrid<>();
    	   List<SearchPhoneCountInfo> dataList=new ArrayList<SearchPhoneCountInfo>();
        try {
            String data = request.getParameter("data");
            SearchPhoneCountInfo info = JsonUtils.readValue(data, SearchPhoneCountInfo.class);
            //info.setLimit(Integer.valueOf(request.getParameter("limit")));
			//info.setOffset(Integer.valueOf(request.getParameter("offset")));
            dataList = reportWaybill.getPhoneDetailCount(info);
            return dataList;
           //grid.setRows(dataList);
            //grid.setTotal(100);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        	return dataList;
        }
        //writeJson(grid, response);
    }

    /**
     * @Description: 学校运单上手机号的明细信息
     * @param request
     * @return ResultInfo
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/phoneCountAll", method = RequestMethod.POST)
    public ResultInfo SelectPhoneAllCountFromBill(HttpServletRequest request) {
        ResultInfo result;
        try {
            List<SearchPhoneCountInfo> dataList = reportWaybill.getPhoneAllCount();
            result = new ResultInfo(0, "", "", dataList);
        } catch (Exception e) {
            result = processException(e, logger);
        }
        return result;
    }
  
    /**
     *
     * @Method: getSqlDataList
     * @Description: 执行sql查询数据
     * @param @param request
     * @param @return    参数
     * @return ResultInfo    返回类型
     * @throws
     * @author heyi
     * @date 2016年4月21日 下午4:40:56
     *
     */
    @ResponseBody
    @RequestMapping(value = "/getSqlData", method = RequestMethod.POST)
    public ResultInfo getSqlDataList(HttpServletRequest request) {
        ResultInfo result;
        try {
            String data = request.getParameter("data");
            if (StringUtils.isEmpty(data)) {
                return new ResultInfo(-1, "111", "参数不能为空");
            }
            String value = JsonUtils.readValueByName(data, "sqlString");
            if (keywordFilter(value)) {
                return new ResultInfo(-1, "", "存在非法字符", "");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("sqlString", value);

            List<Map<String, Object>> dataList = reportWaybill.getSqlDataList(map);
            result = new ResultInfo(0, "", "", dataList);
        } catch (Exception e) {
            result = processException(e, logger);
        }
        return result;
    }

    /**
     * @Description: 关键字校验
     * @param str
     * @return boolean
     * @throws
     */
    public boolean keywordFilter(String str) {
        String keywords[] = "exec|insert|delete|update|chr|mid|master|truncate|declare".split("\\|");
        for (int i = 0; i < keywords.length; i++) {
            if (str.indexOf(keywords[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * @throws IOException 
     * @Description: 运单日志记录
     * @param  request
     * @return ResultInfo
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/waybilllog", method = RequestMethod.GET)
    public  void SelectWayBillLog(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	String data=request.getParameter("data");
    	WayBillLogInfo info=new WayBillLogInfo();
    	Integer offset=Integer.valueOf( request.getParameter("offset"));
    	Integer limit=Integer.valueOf(request.getParameter("limit"));
    	if(data!=null){
    	 info=JsonUtils.readValue(data, WayBillLogInfo.class);
    	}
    	
    	info.setOffset(offset);
    	info.setLimit(limit);
    	DataGrid<WayBillLogInfo> grid = new DataGrid<>();
		List<WayBillLogInfo> infoList = new ArrayList<WayBillLogInfo>();
		try {
			infoList = reportWaybill.selectWayBillLogInfo(info);
			int totalCount = reportWaybill.selectWayBillLogTotalCount(info);
			grid.setRows(infoList);
			grid.setTotal(totalCount);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		writeJson(grid, response);
  
    }
}
