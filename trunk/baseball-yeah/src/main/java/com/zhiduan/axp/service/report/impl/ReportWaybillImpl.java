package com.zhiduan.axp.service.report.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.controller.model.WayBillLogInfo;
import com.zhiduan.axp.controller.model.report.ReportCityPhoneInfo;
import com.zhiduan.axp.controller.model.report.SearchPhoneCountInfo;
import com.zhiduan.axp.dao.report.bean.SearchPhoneCountBean;
import com.zhiduan.axp.dao.report.mapper.ReportWaybillMapper;
import com.zhiduan.axp.service.report.ReportWaybillService;


/**
 * @ClassName: ReportWaybillService
 * @Description: 运单报表
 * @author ZXY
 * @date 2016/4/26 13:21
 */
@Service("reportWaybillService")
public class ReportWaybillImpl implements ReportWaybillService {

    private Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	@Qualifier("reportWaybillMapper")
    private ReportWaybillMapper reportWaybillMapper;
    
	@Override
	public List<SearchPhoneCountInfo> getPhoneCount(SearchPhoneCountInfo info)
			throws Exception {
		 List<SearchPhoneCountInfo> dataList = new ArrayList<>();
	        SearchPhoneCountBean bean = new SearchPhoneCountBean();
	        BeanUtils.copyProperties(info, bean);

	        BeanCopier copier = BeanCopier.create(SearchPhoneCountBean.class, SearchPhoneCountInfo.class, false);
	        List<SearchPhoneCountBean> list = reportWaybillMapper.getPhoneCount(bean);
	        for (SearchPhoneCountBean item : list) {
	            SearchPhoneCountInfo countInfo = new SearchPhoneCountInfo();
	            copier.copy(item, countInfo, null);
	            dataList.add(countInfo);
	        }
	        return dataList;
	}

	@Override
	public List<SearchPhoneCountInfo> getPhoneDetailCount(
			SearchPhoneCountInfo info) throws Exception {
		 List<SearchPhoneCountInfo> dataList = new ArrayList<>();
	        SearchPhoneCountBean bean = new SearchPhoneCountBean();
	        BeanUtils.copyProperties(info, bean);

	        BeanCopier copier = BeanCopier.create(SearchPhoneCountBean.class, SearchPhoneCountInfo.class, false);
	        List<SearchPhoneCountBean> list = reportWaybillMapper.getPhoneDetailCount(bean);
	        for (SearchPhoneCountBean item : list) {
	            SearchPhoneCountInfo countInfo = new SearchPhoneCountInfo();
	            copier.copy(item, countInfo, null);
	            dataList.add(countInfo);
	        }
	        return dataList;
	}

	@Override
	public List<SearchPhoneCountInfo> getPhoneAllCount() throws Exception {
		    List<SearchPhoneCountInfo> dataList = new ArrayList<>();
	        List<SearchPhoneCountBean> list = reportWaybillMapper.getPhoneAllCount();

	        BeanCopier copier = BeanCopier.create(SearchPhoneCountBean.class, SearchPhoneCountInfo.class, false);
	        for (SearchPhoneCountBean item : list) {
	            SearchPhoneCountInfo countInfo = new SearchPhoneCountInfo();
	            copier.copy(item, countInfo, null);
	            dataList.add(countInfo);
	        }
	        return dataList;
	}

	@Override
	public List<Map<String, Object>> getSqlDataList(Map<String, Object> map)
			throws Exception {
		return reportWaybillMapper.getSqlDataList(map);
	}
  
	@Override
	/**
	 * 查询手机号码统计数据
	 */
	public List<ReportCityPhoneInfo> selectCityPhoneCount(Map<String, Object> map) {
		    return reportWaybillMapper.selectCityPhoneCount(map);    
	}

   /**
    * 查询运单日志记录
    */
	@Override
	public List<WayBillLogInfo> selectWayBillLogInfo(WayBillLogInfo info) {
		return reportWaybillMapper.selectWayBillLogInfo(info);
	}


@Override
public int selectWayBillLogTotalCount(WayBillLogInfo info) {
	return reportWaybillMapper.selectWayBillLogTotalCount(info);
}


}
