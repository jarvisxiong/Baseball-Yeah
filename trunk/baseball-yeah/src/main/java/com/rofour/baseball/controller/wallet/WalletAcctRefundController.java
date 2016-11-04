package com.rofour.baseball.controller.wallet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.Constant;
import com.rofour.baseball.common.HttpClientUtils;
import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.common.WebUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.wallet.AcctRefundInfo;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.dao.wallet.bean.AcctRefundBean;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.wallet.WalletAcctRefundService;

/**
 * 
* @ClassName: WalletAcctRefundController
* @Description: 钱包退款控制器
* @author WJ
* @date 2016年7月22日 上午10:06:46 
*
 */
@Controller
@RequestMapping(value = "/wallet/refund")
public class WalletAcctRefundController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
    private WebUtils webUtils;
	@Autowired
	private WalletAcctRefundService walletAcctRefundService;
	/**
	 * @Description: 跳转
	 * @return ModelAndView
	 * @author WJ
	 *
	 */
	@RequestMapping(value = "/gotoRefund", method = RequestMethod.GET)
	public ModelAndView toRefundPage(HttpServletRequest request) throws Exception {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("wallet/walletAcctRefundManager/walletAcctRefundManager");
		}else {
			return new ModelAndView("/error/noPermission");
		}
	}
	
	/**
	 * @Description: 查询
	 *
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void list(HttpServletRequest request,HttpServletResponse response,@RequestBody AcctRefundInfo info) throws Exception {
			if (StringUtils.isBlank(info.getSort())){
				info.setOrder("DESC");
				info.setSort("createTime");//默认以创建时间排序
			}
			DataGrid<AcctRefundBean> grid = new DataGrid<AcctRefundBean>();
			try {
				List<AcctRefundBean> result = walletAcctRefundService.list(info);;
				grid.setRows(result);
				grid.setTotal(walletAcctRefundService.getTotal(info));
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			writeJson(grid, response);
	}
	
	/**
	 * @Description: 请求axp系统进行退款
	 *
	 */
	@ResponseBody
	@RequestMapping(value = "/doRefund", method = RequestMethod.POST)
	public ResultInfo<Object> doRefund(HttpServletRequest request, HttpServletResponse response, String data)
			throws Exception {
		try {
			if (StringUtils.isBlank(data)) {
				throw new BusinessException("111");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, String> refundMap = JsonUtils.readValue(data, Map.class);
			UserManagerLoginBean userManagerLoginBean = (UserManagerLoginBean) request.getSession().getAttribute("user");
			map.put("ipAddress", request.getRemoteAddr());
			map.put("operationId", userManagerLoginBean.getUserManagerId());
			map.put("operationName", userManagerLoginBean.getUserName());
			map.put("refundMap", refundMap);
			String url = Constant.axpurl.get("refund_url");

			// 定义反序列化 数据格式
			final TypeReference<ResultInfo<Object>> TypeRef = new TypeReference<ResultInfo<Object>>() {
			};
			ResultInfo<Object> result = (ResultInfo<Object>) HttpClientUtils.post(url, map, TypeRef);
			webUtils.userEditLog(request, 164, map, map);
			return result;
		} catch (Exception e) {
			return processException(e, logger);
		}
	}
	
	/**
	 * @Description: 跳转
	 * @return ModelAndView
	 * @author WJ
	 *
	 */
	@RequestMapping(value = "/gotoAliPay", method = RequestMethod.GET)
	public ModelAndView toPay(HttpServletRequest request,HttpServletResponse response ,String data) throws Exception {
		ResultInfo<Object> result = doRefund(request, response, data);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wallet/pay");
		mav.addObject("success", result.getSuccess());
		mav.addObject("message", result.getMessage());
		mav.addObject("data", result.getData().toString());
		return mav;
	}
}
