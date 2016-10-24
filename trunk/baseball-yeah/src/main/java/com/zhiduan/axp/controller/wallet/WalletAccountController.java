/**
 * @Title: WalletAccountController.java
 * @Package com.zhiduan.axp.controller.wallet
 * @Project: axp-operation
 * @Description: (用一句话描述该文件做什么)
 * @author heyi
 * @date 2016年6月13日 下午4:22:36
 * @version V1.0
 * ──────────────────────────────────
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.controller.wallet;

import com.zhiduan.axp.common.JsonUtils;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.CapitalChangeInfo;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.wallet.AcctPolicyVoucherInfo;
import com.zhiduan.axp.controller.model.wallet.WalletAccountInfo;
import com.zhiduan.axp.controller.model.wallet.WalletCashShowInfo;
import com.zhiduan.axp.service.wallet.AcctPolicyVoucherService;
import com.zhiduan.axp.service.wallet.WalletAccountService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: WalletAccountController
 * @Description: 钱包账户控制器
 * @author heyi
 * @date 2016年6月13日 下午4:22:36
 *
 */
@Controller
@RequestMapping(value = "/wallet/account")
public class WalletAccountController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource(name = "walletAccountService")
	private WalletAccountService walletAccountService;

	@Resource(name = "acctPolicyVoucherService")
	private AcctPolicyVoucherService acctPolicyVoucherService;

	@RequestMapping(value = "/postwalletaccountlist")
	@ResponseBody
	public List<WalletAccountInfo> postWalletAccountList(HttpServletRequest request, WalletAccountInfo searchInfo) {
		/*
		 * String data=request.getParameter("data"); WalletVerifyInfo
		 * searchInfo=JsonUtils.readValue(data, WalletVerifyInfo.class);
		 */
		try {

			if (request.getSession().getAttribute("user") != null) {
				List<WalletAccountInfo> infoList = walletAccountService.selectWalletAccount(searchInfo);
				return infoList;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}

	@RequestMapping(value = "/postcashinfolist")
	@ResponseBody
	public List<WalletCashShowInfo> postWalletAccountList(HttpServletRequest request) {
		String data = request.getParameter("acctId");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("acctId", data);
		try {

			if (request.getSession().getAttribute("user") != null) {
				List<WalletCashShowInfo> infoList = walletAccountService.selectCashShow(map);
				return infoList;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 *
	 * @Method: lockAccount
	 * @Description: 冻结账户
	 * @param @param
	 *            request
	 * @param @return
	 *            参数
	 * @return ResultInfo 返回类型
	 * @throws @author
	 *             heyi
	 * @date 2016年6月14日 上午11:05:47
	 *
	 */
	@RequestMapping(value = "/lock")
	@ResponseBody
	public ResultInfo lockAccount(HttpServletRequest request) {
		Integer acctId = null;
		if (request.getParameter("acctId") != null) {
			acctId = Integer.valueOf(request.getParameter("acctId"));
		}
		try {
			return walletAccountService.lockAccount(acctId) > 0 ? new ResultInfo(0, "", "冻结成功")
					: new ResultInfo(-1, "", "操作失败");
		} catch (Exception e) {
			return new ResultInfo(-1, "", "操作失败，请联系系统管理员" + e.getMessage());
		}
	}

	/**
	 *
	 * @Method: lockAccount
	 * @Description: 冻结账户
	 * @param @param
	 *            request
	 * @param @return
	 *            参数
	 * @return ResultInfo 返回类型
	 * @throws @author
	 *             heyi
	 * @date 2016年6月14日 上午11:05:47
	 *
	 */
	@RequestMapping(value = "/unlock")
	@ResponseBody
	public ResultInfo unlockAccount(HttpServletRequest request) {
		Integer acctId = null;
		if (request.getParameter("acctId") != null) {
			acctId = Integer.valueOf(request.getParameter("acctId"));
		}
		try {
			return walletAccountService.ulockAccount(acctId) > 0 ? new ResultInfo(0, "", "解锁成功")
					: new ResultInfo(-1, "", "操作失败");
		} catch (Exception e) {
			return new ResultInfo(-1, "", "操作失败，请联系系统管理员" + e.getMessage());
		}
	}

	@RequestMapping(value = "/capitalchangelist")
	@ResponseBody
	public List<CapitalChangeInfo> postcapitalchangelist(HttpServletRequest request) {
		String data = request.getParameter("data");

		try {
			CapitalChangeInfo info = JsonUtils.readValue(data, CapitalChangeInfo.class);
			if (request.getSession().getAttribute("user") != null) {
				List<CapitalChangeInfo> infoList = walletAccountService.GetCapitalChangeByPaprames(info);
				return infoList;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 
	 * @Method: postPolicyVoucherList
	 * @Description: 获取代金券信息
	 * @param @param
	 *            request
	 * @param @return
	 *            参数
	 * @return List<AcctPolicyVoucherInfo> 返回类型
	 * @throws @author
	 *             heyi
	 * @date 2016年7月18日 下午3:31:36
	 *
	 */
	@RequestMapping(value = "/policyVoucherList")
	@ResponseBody
	public void postPolicyVoucherList(HttpServletRequest request,HttpServletResponse response,AcctPolicyVoucherInfo acctPolicyVoucherInfo) {
		String data = request.getParameter("data");
		String offset=request.getParameter("offset");
		String limit=request.getParameter("limit");
		if (StringUtils.isBlank(acctPolicyVoucherInfo.getSort())) {
			acctPolicyVoucherInfo.setSort("activityId");//默认以活动I的排序
			acctPolicyVoucherInfo.setOrder("desc");//默认倒序
		}
		DataGrid<AcctPolicyVoucherInfo> grid = new DataGrid<>();
		try {
			AcctPolicyVoucherInfo info = JsonUtils.readValue(data,
					AcctPolicyVoucherInfo.class);
			info.setLimit(Integer.valueOf(limit));
			info.setOffset(Integer.valueOf(offset));
			if (StringUtils.isBlank(acctPolicyVoucherInfo.getSort())) {
				info.setSort("activityId");//默认以活动I的排序
				info.setOrder("desc");//默认倒序
			}else {
				info.setSort(acctPolicyVoucherInfo.getSort());
				info.setOrder(acctPolicyVoucherInfo.getOrder());
			}
			List<AcctPolicyVoucherInfo> infoList =acctPolicyVoucherService.getVoucherList(info);
			grid.setRows(infoList);
            grid.setTotal(acctPolicyVoucherService.selectPolicyVoucherInfoCount(info));
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeJson(grid,response);
	}
	//
	// @RequestMapping(value = "/getall", method = RequestMethod.POST)
	// public void getMenuList(HttpServletRequest request, HttpServletResponse
	// response, @RequestBody FocusPicBean bean) {
	// if (StringUtils.isBlank(bean.getSort())) {
	// bean.setSort("create_time");//默认以id排序
	// bean.setOrder("desc");
	// }
	// DataGrid<FocusPicBean> grid = new DataGrid<FocusPicBean>();
	// try {
	// List<FocusPicBean> picList = focusPicService.getAll(bean);
	// grid.setRows(picList);
	// grid.setTotal(focusPicService.selectAllCount(bean));
	// } catch (Exception e) {
	// logger.error(e.getMessage(), e);
	// }
	// writeJson(grid, response);
	// }
}
