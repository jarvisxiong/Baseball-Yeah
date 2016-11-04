/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.manager;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.AttachmentTypeInfo;
import com.rofour.baseball.service.manager.AttachmentTypeService;

/**
 * @ClassName: AttachmentTypeController
 * @Description: 附件类型 对外控制器
 * @author 史丹丹
 * @date 2016年4月2日 下午5:41:32
 *
 */

@Controller
@RequestMapping("/manage/attachmenttype")
public class AttachmentTypeController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("attachmentTypeService")
	private AttachmentTypeService attachmentTypeService;

	/**
	 * 
	 * @Description: 新增附件
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/addattachmenttype", method = RequestMethod.POST)
	public ResultInfo addAttachmentType(HttpServletRequest request) {
		AttachmentTypeInfo attachment = null;
		try {
			String data = request.getParameter("data");
			attachment = JsonUtils.readValue(data, AttachmentTypeInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try {
			attachmentTypeService.addAttachment(attachment);

			return new ResultInfo(0, "", "添加成功", "");

		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 根据主键查找附件
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/selectattachmenttype", method = RequestMethod.POST)
	public ResultInfo selectTemplateType(HttpServletRequest request) {
		AttachmentTypeInfo attachment = null;
		try {
			String data = request.getParameter("data");
			attachment = JsonUtils.readValue(data, AttachmentTypeInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try {

			AttachmentTypeInfo att = attachmentTypeService.selectAttachment(attachment);
			return new ResultInfo(0, "", "查找成功", att);

		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 根据附件编号删除相应附件
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteattachmenttype", method = RequestMethod.POST)
	public ResultInfo deleteTemplateType(HttpServletRequest request) {
		AttachmentTypeInfo attachment = null;
		try {
			String data = request.getParameter("data");
			attachment = JsonUtils.readValue(data, AttachmentTypeInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try {

			attachmentTypeService.deleteAttachment(attachment);
			return new ResultInfo(0, "", "删除成功", "");

		} catch (Exception e) {
			return processException(e, logger);
		}
	}

	/**
	 * 
	 * @Description: 修改已经上传的附件信息
	 * @param request
	 * @return ResultInfo 操作结果bean
	 */
	@ResponseBody
	@RequestMapping(value = "/updateattachmenttype", method = RequestMethod.POST)
	public ResultInfo updateTemplateType(HttpServletRequest request) {
		AttachmentTypeInfo attachment = null;
		try {
			String data = request.getParameter("data");
			attachment = JsonUtils.readValue(data, AttachmentTypeInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSON_ERROR;
		}
		try {
			attachmentTypeService.updateAttachmentType(attachment);
			return new ResultInfo(0, "修改成功", "");

		} catch (Exception e) {
			return processException(e, logger);
		}
	}

}
