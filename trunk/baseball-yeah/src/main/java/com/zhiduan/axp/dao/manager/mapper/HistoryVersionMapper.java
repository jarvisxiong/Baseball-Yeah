/**  
* @Title: HistoryVersionMapper.java
* @Package com.zhiduan.axp.dao.manager.mapper
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年6月30日 下午3:02:28 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.zhiduan.axp.controller.model.HistoryVersionInfo;

/**
* @ClassName: HistoryVersionMapper
* @Description: 历史版本
* @author heyi
* @date 2016年6月30日 下午3:02:28 
*
*/

@Named("historyVersionMapper")
public interface HistoryVersionMapper {
   
	List<HistoryVersionInfo> selectVersion(HistoryVersionInfo version);
	
	int insertVersion(HistoryVersionInfo version);
	
	int updateVersion(HistoryVersionInfo version);
	
	int deleteVersion(Integer versionId);
}
