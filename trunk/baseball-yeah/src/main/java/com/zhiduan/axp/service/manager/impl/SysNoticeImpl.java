/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhiduan.axp.common.Constant;
import com.zhiduan.axp.common.HttpClientUtils;
import com.zhiduan.axp.common.WebUtils;
import com.zhiduan.axp.controller.model.Permission;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.SysNoticeInfo;
import com.zhiduan.axp.dao.manager.bean.SysNoticeBean;
import com.zhiduan.axp.dao.manager.mapper.SysNoticeMapper;
import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.SysNoticeService;

/**
 * @ClassName: SysNoticeImpl
 * @Description: 系统通知的接口实现类
 * @author 周琦
 * @date 2016年4月20日 上午9:47:22
 */
@Service("sysNoticeService")
public class SysNoticeImpl implements SysNoticeService {

	@Autowired
	@Qualifier("sysNoticeMapper")
	private SysNoticeMapper dao;
	@Autowired
	private WebUtils webUtils;

	/**
	 * @Description: 添加系统通知
	 * @param notice
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SysNoticeService#addNotice(com.zhiduan.axp.idl.managecenter.service.entity.SysNoticeInfo)
	 */
	public int addNotice(SysNoticeInfo notice, HttpServletRequest request) {
		checkSysNotice(notice);
		/*if (!notice.getBePush().equals((byte) 0) && !notice.getBePush().equals((byte) 1)) {
			throw new BusinessException("110");
		}*/
		List<SysNoticeBean> list = new ArrayList<SysNoticeBean>();
		Date date = new Date();
		for (String item : notice.getPushTypeList()) {
			SysNoticeBean sysNoticeBean = new SysNoticeBean();
			BeanUtils.copyProperties(notice, sysNoticeBean);
			sysNoticeBean.setPushType(item);
			sysNoticeBean.setPublishTime(date);
			sysNoticeBean.setBeDeleted((byte) 0);
			list.add(sysNoticeBean);
		}
		// int result = dao.insert(sysNoticeBean);
		int result = dao.insertBatch(list);
		for (SysNoticeBean item : list) {
			webUtils.userAddLog(request, 9, item);
		}
		return result;
	}

	/**
	 * @Description: 删除一个系统通知,逻辑删除
	 * @param notice
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SysNoticeService#deleteNotice(com.zhiduan.axp.idl.managecenter.service.entity.SysNoticeInfo)
	 */
	public int deleteNotice(SysNoticeInfo notice, HttpServletRequest request) {

		int result = 0;
		result = dao.deleteBatch(notice);
		if (result == 0) {
			throw new BusinessException("01015");
		}
		webUtils.userDeleteLog(request, 9, notice);
		return result;
	}

	/**
	 * @Description: 更新系统通知
	 * @param notice
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SysNoticeService#updateNotice(com.zhiduan.axp.idl.managecenter.service.entity.SysNoticeInfo)
	 */
	public int updateNotice(SysNoticeInfo notice, HttpServletRequest request) {
		checkSysNotice(notice);
		if (notice.getSysNoticeId() == null || notice.getBePush() == null || notice.getBePush().toString().equals("")) {
			throw new BusinessException("111");
		} else if (!notice.getBePush().equals((byte) 0) && !notice.getBePush().equals((byte) 1)) {
			throw new BusinessException("110");
		}
		notice.setBeDeleted((byte) 0);
		SysNoticeBean noticePast = dao.selectByPrimaryKey(notice.getSysNoticeId());
		int result = dao.updateByPrimaryKey(notice);
		if (result == 0) {
			throw new BusinessException("01015");
		}

		webUtils.userEditLog(request, 9, noticePast, notice);
		return result;

	}

	/**
	 * @Description: 查询一个系统通知
	 * @param notice
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SysNoticeService#selectNotice(com.zhiduan.axp.idl.managecenter.service.entity.SysNoticeInfo)
	 */
	public SysNoticeInfo selectNotice(SysNoticeInfo notice) {
		if (notice.getSysNoticeId() == null) {
			throw new BusinessException("111");
		}
		SysNoticeBean back = dao.selectByPrimaryKey(notice.getSysNoticeId());
		if (back == null || back.getBeDeleted() == 1) {
			throw new BusinessException("01015");
		} else {
			SysNoticeInfo result = new SysNoticeInfo();
			BeanUtils.copyProperties(back, result);
			return result;
		}
	}

	/**
	 * @Description: 查询所有系统通知
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.SysNoticeService#selectAll()
	 */
	public List<SysNoticeInfo> selectAll(SysNoticeInfo sysNoticeInfo) {

		List<SysNoticeInfo> all = dao.selectAll(sysNoticeInfo);
		if (all == null || all.isEmpty()) {
			throw new BusinessException("01015");
		}

		return all;
	}

	/**
	 * 
	 * @Description: 校验
	 * @param notice
	 */
	private void checkSysNotice(SysNoticeInfo notice) {
		if (StringUtils.isBlank(notice.getCaption()) || StringUtils.isBlank(notice.getContent())
				|| notice.getPushTypeList().size() < 1) {
			throw new BusinessException("111");
		}
		if (notice.getCaption().length() > 100 || (notice.getUrl() != null && notice.getUrl().length() > 200)) {
			throw new BusinessException("112");
		}
	}

	/**
	 * 
	 * @Description:查询条数
	 * @param notice
	 */
	@Override
	public Integer getSysNoticeTotal(SysNoticeInfo notice) {
		return dao.getSysNoticeTotal(notice);
	}

	/**
	 * @Description: 审核
	 * @param notice
	 * @param request
	 * @return
	 * @throws IOException
	 * @see com.zhiduan.axp.service.manager.SysNoticeService#auditNotice(com.zhiduan.axp.controller.model.manager.SysNoticeInfo,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void auditNotice(SysNoticeInfo notice, HttpServletRequest request) throws IOException {
		SysNoticeBean back = dao.selectByPrimaryKey(notice.getSysNoticeId());
		UserManagerLoginBean userManagerBean = (UserManagerLoginBean) request.getSession().getAttribute("user");
		// 定义反序列化 数据格式
		final TypeReference<ResultInfo<?>> walletTypeRef = new TypeReference<ResultInfo<?>>() {
		};
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", back.getPushType());
		map.put("content", back.getContent());
		map.put("title", back.getCaption());
		// /axp-acl/msg/push/2app.htm
		String url = Constant.axpurl.get("appmsg_push_serv");
		// 调用axp接口
		ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, walletTypeRef);
		if (data.getSuccess() < 0) {
			webUtils.userLog(request, String.format("消息通知：%s 审核失败，审核人：%s", notice.getSysNoticeId().toString(),
					userManagerBean.getUserManagerId().toString()), 9, "", "", Permission.AUDIT.name());
			throw new BusinessException("104");
		}
		Map<String, Object> mapAudit = new HashMap<String, Object>();
		mapAudit.put("sysNoticeId", notice.getSysNoticeId());
		mapAudit.put("bePush", "1");
		mapAudit.put("verifyUserId", userManagerBean.getUserManagerId());
		dao.auditUpdate(mapAudit);
		webUtils.userLog(request, String.format("消息通知：%s 审核成功，审核人：%s", notice.getSysNoticeId().toString(),
				userManagerBean.getUserManagerId().toString()), 9, "", "", Permission.AUDIT.name());
	}
}
