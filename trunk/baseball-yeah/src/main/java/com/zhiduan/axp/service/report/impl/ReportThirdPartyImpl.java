package com.zhiduan.axp.service.report.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiduan.axp.controller.model.report.ReportThirdPartyInfo;
import com.zhiduan.axp.dao.report.bean.ReportThirdPartyBean;
import com.zhiduan.axp.dao.report.mapper.ReportStoreSmsMapper;
import com.zhiduan.axp.dao.report.mapper.ReportThirdPartyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.common.StringUtils;
import com.zhiduan.axp.controller.model.report.ReportThirdPartyPhoneInfo;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.report.ReportThirdPartyService;

/**
 * @author ZXY
 * @ClassName: ReportThirdPartyImpl
 * @Description: 第三方报表服务实现
 * @date 2016-04-16 09:13:24
 */
@Service("reportThirdParty")
public class ReportThirdPartyImpl implements ReportThirdPartyService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("reportThirdPartyMapper")
	private ReportThirdPartyMapper dao;

	/**
	 * 根据条件查询报表
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param collegeIds 学校
	 * @param areaIds 区域
	 */
	@Override
	public Map getReportByParam(String startDate, String endDate, String collegeIds, String areaIds) throws Exception {
		if (StringUtils.isEmpty(startDate)) {
			//开始日期不能为空
			throw new BusinessException("08002");
		} else if (StringUtils.isEmpty(endDate)) {
			//结束日期不能为空
			throw new BusinessException("08003");
		}
		Map retMap = new HashMap();
		List<Integer> countList = dao.selectCountOfAll();
		// 潜在表电话号码不重复
		int totalCount = 0;
		// 第三方表电话号码要去重
		int phoneCount = 0;
		if (countList.size() == 2) {
			totalCount = countList.get(0);
			phoneCount = countList.get(1);
		}
		retMap.put("totalNum", totalCount);
		retMap.put("totalRegNum", phoneCount);
		retMap.put("totalUnregNum", totalCount - phoneCount);
		BigDecimal num = BigDecimal.valueOf((double) phoneCount / (double) totalCount * 100);
		DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
		retMap.put("rate", df.format(num));

		List<ReportThirdPartyInfo> infoList = new ArrayList<>();
		Map paraMap = new HashMap();
		paraMap.put("startDate", startDate);
		paraMap.put("endDate", endDate);
		if (!StringUtils.isEmpty(collegeIds)) {
			paraMap.put("collegeIds", collegeIds.split(","));
		}
		if (!StringUtils.isEmpty(areaIds)) {
			paraMap.put("areaIds", areaIds.split(","));
		}

		List<ReportThirdPartyBean> beanList = dao.getReportByParam(paraMap);
		ReportThirdPartyInfo info = null;
		for (ReportThirdPartyBean bean : beanList) {
			info = new ReportThirdPartyInfo();
			BeanUtils.copyProperties(bean, info);
			infoList.add(info);
		}
		retMap.put("reportList", infoList);
		return retMap;
	}

	/**
	 * 截至当天，按学校查询所有号码，潜在用户
	 *
	 * @param collegeId
	 * @param reportDate
	 */
	@Override
	public List<ReportThirdPartyPhoneInfo> getPhoneByCollege(String collegeId, String reportDate) throws Exception {
		checkParams(collegeId, reportDate);
		List<ReportThirdPartyPhoneInfo> infoList = dao.getPhoneByCollege(Long.valueOf(collegeId), reportDate);
		return infoList;
	}

	/**
	 * 截至当天，按学校查询所有注册号码，在第三方注册
	 *
	 * @param collegeId
	 * @param reportDate
	 */
	@Override
	public List<ReportThirdPartyPhoneInfo> getRegPhoneByCollege(String collegeId, String reportDate) throws Exception {
		checkParams(collegeId, reportDate);
		List<ReportThirdPartyPhoneInfo> infoList = dao.getRegPhoneByCollege(Long.valueOf(collegeId), reportDate);
		return infoList;
	}

	/**
	 * 截至当天，按学校查询所有未注册号码，潜在未注册
	 *
	 * @param collegeId
	 * @param reportDate
	 */
	@Override
	public List<ReportThirdPartyPhoneInfo> getUnregPhoneByCollege(String collegeId, String reportDate) throws Exception {
		checkParams(collegeId, reportDate);
		List<ReportThirdPartyPhoneInfo> infoList = dao.getUnregPhoneByCollege(Long.valueOf(collegeId), reportDate);
		return infoList;
	}

	/**
	 * 当天，按学校查询新增号码
	 *
	 * @param collegeId
	 * @param reportDate
	 */
	@Override
	public List<ReportThirdPartyPhoneInfo> getNewPhoneByCollege(String collegeId, String reportDate) throws Exception {
		checkParams(collegeId, reportDate);
		List<ReportThirdPartyPhoneInfo> infoList = dao.getNewPhoneByCollege(Long.valueOf(collegeId), reportDate);
		return infoList;
	}

	/**
	 * @Description: 校验传入参数
	 * @param  collegeId
	 * @param  reportDate
	 * @return
	 * @throws
	 */
	private void checkParams(String collegeId, String reportDate) throws Exception {
		if (StringUtils.isEmpty(collegeId)) {
			//学校编码不能为空
			throw new BusinessException("08004");
		} else if (!StringUtils.isNumber(collegeId)) {
			//学校编码必须为数字
			throw new BusinessException("08005");
		} else if (StringUtils.isEmpty(reportDate)) {
			//报表查询日期不能为空
			throw new BusinessException("08001");
		}
	}
}