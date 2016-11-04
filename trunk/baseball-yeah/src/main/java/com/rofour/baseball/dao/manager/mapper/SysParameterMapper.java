/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.dao.manager.bean.SysParameterBean;

/**
 * @ClassName: TbSysParameterMapper
 * @Description: 系统参数数据库操作接口
 * @author xl
 * @date 2016年3月26日 下午1:38:59
 *
 */

@Named("tbSysParameterMapper")
public interface SysParameterMapper {

	/**
	 * @Description: 按主键删除系统参数
	 * @param sysParameterId
	 * @return int 
	 **/

	int deleteByPrimaryKey(Long sysParameterId);

	/**
	 * @Description: 新增系统参数
	 * @param record
	 * @return int 
	 **/

	int insert(SysParameterBean record);

	/**
	 * @Description: 按主键ID查询系统参数
	 * @param sysParameterId
	 * @return TbSysParameterBean 
	 **/

	SysParameterBean selectByPrimaryKey(Long sysParameterId);

	/**
	 * @Description: 更新系统参数
	 * @param record
	 * @return 更新的数量
	 **/

	int updateByPrimaryKey(SysParameterBean record);

	/**
	 * @Description: 获取所有系统参数
	 * @return List<TbSysParameterBean> 
	 **/

	List<SysParameterBean> selectAll();

	/**
	 * @Description: 按名称查询系统参数
	 * @param paraName
	 * @return List<TbSysParameterBean> 
	 **/

	SysParameterBean selectByParaName(String paraName);

	/**
	 * @Description: 系统参数名称是否已存在
	 * @param map
	 * @return 重复的数量
	 **/

	int isSysParaNameExists(Map<String, Object> map);

}