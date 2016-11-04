/**  
* @Title: ResourceController.java
* @package com.rofour.baseball.acl.resourcescenter
* @Project: axp-acl
* @Description: 资源中心接口
* @author WJ
* @date 2016年3月29日 上午9:41:48 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.controller.resource;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.service.resource.ResourceService;

/**
* @ClassName: ResourceController
* @Description: 资源中心接口
* @author WJ
* @date 2016年3月29日 上午9:41:48 
*
*/
@Controller
@RequestMapping(value="/resource/pic",method=RequestMethod.POST)
public class ResourceController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ResourceService resourceService;
	/**
	 * @Description: 请求已上传的证件图片url
	 * @param  request
	 * @return ResultInfo    返回类型
	 * @date 2016年5月5日 上午10:45:47 
	 *
	 */
	@RequestMapping(value="/geturls",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo getUrls(HttpServletRequest request) {
		String data = request.getParameter("data");
		try{
			String userId = JsonUtils.readValueByName(data, "userId");
			if(StringUtils.isBlank(userId)){
				return new ResultInfo(-1, "111", getMessage("111"));
			} else if(!userId.matches("^\\d+$")){
				return new ResultInfo(-1, "114", getMessage("114"));
			}
			
			ResultInfo result = resourceService.getCredentialUrls(Long.valueOf(userId));
			return processResultInfo(result);
		} catch (Exception e) {
			return processException(e, logger);
		}
	}
}
