package com.rofour.baseball.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.common.IpUtils;
import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.SysNoticeInfo;
import com.rofour.baseball.controller.model.user.FeedBackInfo;
import com.rofour.baseball.service.user.FeedBackService;;


/**
 * 反馈信息 对外控制器。
 */
@Controller
@RequestMapping(value = "/user/feedback", method = RequestMethod.POST)
public class FeedBackController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(FeedBackController.class);


	@Autowired
	@Qualifier("feedBackService")
	private FeedBackService feedBackService;

	private FeedBackInfo read(HttpServletRequest request) {
		try {
			String data = request.getParameter("data");
			if (StringUtils.isEmpty(data)) {
				return null;
			}
			return JsonUtils.readValue(data, FeedBackInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 添加反馈信息
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addfeedback")
	public ResultInfo addFeedBack(HttpServletRequest request,FeedBackInfo feedBack) {

		try {

			ResultInfo x = checkParam(request, feedBack);
			if (x != null) return x;
			feedBack.setIp(IpUtils.getIpAddr(request));
			// 调用service层方法
			return feedBackService.addFeedBack(feedBack);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResultInfo(-1, "100", "用户未登陆，请重新登陆");
		}
	}

	/**
	 * 参数校验
	 *
	 * @param request
	 * @param feedBack
	 * @return
	 */
	private ResultInfo checkParam(HttpServletRequest request, FeedBackInfo feedBack) {
		if (null == feedBack.getUserId() || StringUtils.isEmpty(IpUtils.getIpAddr(request))) {
			return new ResultInfo(-1, "111", "参数不能为空!");
		}
		if (StringUtils.isBlank(feedBack.getContent()) && feedBack.getContent().length() > 500) {
			return new ResultInfo(-1, "110", "反馈信息不能为空且不能超长!");
		}
		return null;
	}

	/**
	 * 查询全部反馈信息
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectall", method = RequestMethod.POST)
	public void selectAllFeedBack(HttpServletResponse response,@RequestBody FeedBackInfo feedBackInfo) {
		if (StringUtils.isBlank(feedBackInfo.getSort())){
			feedBackInfo.setSort("feedbackId");//默认以id排序
		}
		List<FeedBackInfo> list = null;
		DataGrid<FeedBackInfo> grid=new DataGrid<FeedBackInfo>();
		try {
			list  = feedBackService.selectAll(feedBackInfo);
			grid.setRows(list);
			grid.setTotal(feedBackService.getfeedBackTotal(feedBackInfo));
		} catch (Exception e) {
			logger.error(e.getMessage(), logger);
		} 
		writeJson(grid, response);
	}




	/**
	 * 根据用户id查询该用户的反馈信息
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectfeedback")
	public ResultInfo selectFeedBackByUserId(HttpServletRequest request) {
		FeedBackInfo feedBack = read(request);
		if (null == feedBack) {
			return new ResultInfo(-1, "110", "参数不合法");
		}
		List<FeedBackInfo> info = feedBackService.selectLoginLog(feedBack);
		if (CollectionUtils.isEmpty(info)) {
			return new ResultInfo(-1, "115", "查无此用户的反馈信息!");
		}
		return new ResultInfo(0, "", "查询成功", info);
	}

	/**
	 * 根据反馈信息id删除反馈信息数据
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deletefeedback")
	public ResultInfo deleteFeedBackById(HttpServletRequest request,FeedBackInfo feedBack) {
		int result = feedBackService.deleteBatch(feedBack,request);
		if (result <= 0) {
			return new ResultInfo(-1, "-1", "删除失败!");
		}
		return new ResultInfo(0, "", "刪除成功");
	}

}
