/**  
* @Title: HistoryVersionImpl.java
* @package com.rofour.baseball.service.manager.impl
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月30日 下午3:39:03 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.service.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.model.HistoryVersionInfo;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.dao.manager.mapper.HistoryVersionMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.HistoryVersionService;

/**
* @ClassName: HistoryVersionImpl
* @Description: 版本接口实现
* @author heyi
* @date 2016年6月30日 下午3:39:03 
*
*/

@Service("historyVersionService")
public class HistoryVersionImpl implements HistoryVersionService {

	@Resource(name="historyVersionMapper")
	private HistoryVersionMapper historyVersionMapper;

	
	@Override
	public List<HistoryVersionInfo> getList(HistoryVersionInfo version) {
		
		try {
			return historyVersionMapper.selectVersion(version);
		} catch (Exception e) {
			return new ArrayList<HistoryVersionInfo>();
		}
	}

	
	@Override
	public ResultInfo<String> addVersion(HttpServletRequest request) {
		try {
			String version=request.getParameter("version");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			Byte sortNo=Byte.parseByte(request.getParameter("sortNo"));
			HistoryVersionInfo versionInfo=new HistoryVersionInfo();
			versionInfo.setContent(content);
			versionInfo.setSortNo(sortNo);
			versionInfo.setTitle(title);
			versionInfo.setVersion(version);
			/*HistoryVersionInfo version=JsonUtils.readValue(data, HistoryVersionInfo.class);*/
			Boolean result=historyVersionMapper.insertVersion(versionInfo)>0;
			if(result)
			{
				return new ResultInfo<String>(0,"","添加成功","");
			}
			else
			{
				return new ResultInfo<String>(-1,"","添加失败","");
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}

	
	@Override
	public ResultInfo<String> updateVersion(HttpServletRequest request) {
		try {
			Long versionId=Long.valueOf(request.getParameter("versionId"));
			String version=request.getParameter("version");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			Byte sortNo=Byte.parseByte(request.getParameter("sortNo"));
			HistoryVersionInfo versionInfo=new HistoryVersionInfo();
			versionInfo.setContent(content);
			versionInfo.setSortNo(sortNo);
			versionInfo.setTitle(title);
			versionInfo.setVersion(version);
			versionInfo.setVersionId(versionId);
			/*HistoryVersionInfo version=JsonUtils.readValue(data, HistoryVersionInfo.class);*/
			Boolean result=historyVersionMapper.updateVersion(versionInfo)>0;
			if(result)
			{
				return new ResultInfo<String>(0,"","修改成功","");
			}
			else
			{
				return new ResultInfo<String>(-1,"","修改失败","");
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}


	    
	@Override
	public ResultInfo<String> removeVersion(HttpServletRequest request) {
		try {
			Integer versionId=Integer.valueOf(request.getParameter("versionId"));
			Boolean result=historyVersionMapper.deleteVersion(versionId)>0;
			if(result)
			{
				return new ResultInfo<String>(0,"","删除成功","");
			}
			else
			{
				return new ResultInfo<String>(-1,"","删除失败","");
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
}
