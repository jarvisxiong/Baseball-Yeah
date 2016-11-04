/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.SysParameterInfo;

/**
 * @ClassName: TbSysParameterService
 * @Description: 管理中心--系统参数服务接口
 * @author xl
 * @date 2016年3月26日 下午1:55:31
 *
 */

public interface SysParameterService {

	/**
	 * @Description: 按主键ID删除系统参数
	 * @param sysParameterId
	 * @return
	 */

	int deleteByPrimaryKey(Long sysParameterId,HttpServletRequest request);

	/**
	 * @Description: 新增系统参数
	 * @param record
	 * @return
	 */

	ResultInfo insert(SysParameterInfo record,HttpServletRequest request);

	/**
	 * @Description: 按主键ID查询系统参数
	 * @param sysParameterId
	 * @return
	 */

	SysParameterInfo selectByPrimaryKey(Long sysParameterId);

	/**
	 * @Description: 更新系统参数
	 * @param record
	 * @return
	 */

	ResultInfo updateByPrimaryKey(SysParameterInfo record,HttpServletRequest request);

	/**
	 * @Description: 获取所有系统参数
	 * @return
	 */

	List<SysParameterInfo> selectAll();

	/**
	 * @Description: 按名称查询系统参数
	 * @param parameterName
	 * @return
	 */

	SysParameterInfo selectByParaName(String parameterName);

}