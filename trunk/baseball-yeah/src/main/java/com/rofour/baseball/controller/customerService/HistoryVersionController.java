/**  
* @Title: HistoryVersionController.java
* @package com.rofour.baseball.controller.customerService
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月30日 下午4:05:33 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.controller.customerService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.HistoryVersionInfo;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.service.manager.HistoryVersionService;

/**
* @ClassName: HistoryVersionController
* @Description: 版本控制 
* @author heyi
* @date 2016年6月30日 下午4:05:33 
*
*/
@Controller
@RequestMapping("/version")
public class HistoryVersionController extends BaseController {
     
	@Resource(name="historyVersionService")
	private HistoryVersionService historyVersionService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<HistoryVersionInfo> getList(HttpServletRequest request,HistoryVersionInfo version)
	{
		try {
			return historyVersionService.getList(version);
		} catch (Exception e) {
			return null;
		}
	}
	@RequestMapping("/add")
	@ResponseBody
	public ResultInfo<String> add(HttpServletRequest request){
		try {
			return historyVersionService.addVersion(request);
		} catch (Exception e) {
			return new ResultInfo<String>(-1,"","请求服务器失败","");
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	public ResultInfo<String> update(HttpServletRequest request){
		try {
			return historyVersionService.updateVersion(request);
		} catch (Exception e) {
			return new ResultInfo<String>(-1,"","请求服务器失败","");
		}
	}
	@RequestMapping("/remove")
	@ResponseBody
	public ResultInfo<String> remove(HttpServletRequest request){
		try {
			return historyVersionService.removeVersion(request);
		} catch (Exception e) {
			return new ResultInfo<String>(-1,"","请求服务器失败","");
		}
	}
}
