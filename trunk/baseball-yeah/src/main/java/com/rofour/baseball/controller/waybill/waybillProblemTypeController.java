/**  
* @Title: waybillProblemTypeController.java
* @package com.rofour.baseball.controller.waybill
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年7月20日 上午10:35:52 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.controller.waybill;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.waybill.WayBillProblemTypeInfo;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.waybill.WaybillProblemTypeService;

/**
* @ClassName: waybillProblemTypeController
* @Description: 问题件类型控制器
* @author heyi
* @date 2016年7月20日 上午10:35:52 
*
*/

@Controller
@RequestMapping("/waybill/problem")
public class waybillProblemTypeController {

	@Resource(name = "waybillProblemTypeService")
	private WaybillProblemTypeService waybillProblemTypeService;

	@RequestMapping("/query")
	@ResponseBody
	public List<WayBillProblemTypeInfo> query(HttpServletRequest request) {
		try {
			String updateStartTime = request.getParameter("updateStartTime");
			String updateEndTime = request.getParameter("updateEndTime");
			String problemTypeGroup = request.getParameter("problemTypeGroup");
			WayBillProblemTypeInfo info = new WayBillProblemTypeInfo();
			info.setUpdateStartTime(updateStartTime);
			info.setUpdateEndTime(updateEndTime);
			if (problemTypeGroup != null && !"".equals(problemTypeGroup)) {
				info.setProblemTypeGroup(Integer.valueOf(problemTypeGroup));
			}
			return waybillProblemTypeService.selectWaybillProblemTypeList(info);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * insert
	 * @Method: insert
	 * @Description: 新增问题类型
	 * @param @param request
	 * @param @return    参数
	 * @return ResultInfo<String>    返回类型
	 * @throws
	 * @author heyi
	 * @date 2016年7月20日 上午10:47:14 
	 *
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public ResultInfo<String> insert(HttpServletRequest request) {
		try {
			String data = request.getParameter("data");
			WayBillProblemTypeInfo info = JsonUtils.readValue(data, WayBillProblemTypeInfo.class);
			UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
			if (sessionUser != null) {
				info.setUpdateUserId(Integer.valueOf(sessionUser.getUserManagerId().toString()));
			}
			// 检索异常件ID是否重复
			WayBillProblemTypeInfo infoForSearch = new WayBillProblemTypeInfo();
			infoForSearch.setProblemTypeCode(info.getProblemTypeCode());
			List<WayBillProblemTypeInfo> dbList = waybillProblemTypeService.selectWaybillProblemTypeList(info);
			if (null != dbList && !dbList.isEmpty()) {
				return new ResultInfo<String>(-1, "", "异常类型编码重复，请修改", "");
			}
			return waybillProblemTypeService.insertWaybillProblemType(info) > 0 ? new ResultInfo<String>(0, "", "添加成功", "") : new ResultInfo<String>(-1, "", "添加失败", "");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultInfo<String>(-1, "", "服务器异常", "");
		}
	}

	/**
	 * 
	 * @Method:update
	 * @Description: 修改问题件类型
	 * @param @param request
	 * @param @return    参数
	 * @return ResultInfo<String>    返回类型
	 * @throws
	 * @author heyi
	 * @date 2016年7月20日 上午10:47:14 
	 *
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResultInfo<String> update(HttpServletRequest request) {
		try {
			String data = request.getParameter("data");
			WayBillProblemTypeInfo info = JsonUtils.readValue(data, WayBillProblemTypeInfo.class);
			UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
			if (sessionUser != null) {
				info.setUpdateUserId(Integer.valueOf(sessionUser.getUserManagerId().toString()));
			}
			return waybillProblemTypeService.updateWaybillProblemType(info) > 0 ? new ResultInfo<String>(0, "", "修改成功", "") : new ResultInfo<String>(-1, "", "修改失败", "");
		} catch (Exception e) {
			return new ResultInfo<String>(-1, "", "服务器异常", "");
		}
	}

	/**
	 * 
	 * @Method: delete
	 * @Description: 删除问题类型
	 * @param @param request
	 * @param @return    参数
	 * @return ResultInfo<String>    返回类型
	 * @throws
	 * @author heyi
	 * @date 2016年7月20日 上午10:51:20 
	 *
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResultInfo<String> delete(HttpServletRequest request) {
		try {
			String data = request.getParameter("problemTypeCode");
			return waybillProblemTypeService.deleteWaybillProblemType(data.toString()) > 0 ? new ResultInfo<String>(0, "", "删除成功", "") : new ResultInfo<String>(-1, "", "删除失败", "");
		} catch (Exception e) {
			return new ResultInfo<String>(-1, "", "服务器异常", "");
		}
	}
}
