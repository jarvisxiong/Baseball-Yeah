/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.CityCollegeInfo;
import com.zhiduan.axp.controller.model.manager.CollegeInfo;
import com.zhiduan.axp.controller.model.manager.CollegeManageInfo;
import com.zhiduan.axp.dao.manager.bean.CollegeBean;
import com.zhiduan.axp.dao.manager.bean.CollegeManageBean;

/**
 * @ClassName: TbCollegeService
 * @Description: 管理中心--学校服务接口
 * @author xl
 * @date 2016年3月26日 下午1:45:20
 *
 */

public interface CollegeService {

	/**
	 * @Description: 获取学校列表
	 * @return
	 */

	List<CollegeManageBean> selectCollegeAJAX(CollegeBean collegeBean);
	List<CollegeManageBean> selectCollegeForEdit(CollegeBean collegeBean);
	List<CollegeInfo> getCollegeList();

	/**
	 * @Description: 获取学校下拉列表
	 * @return
	 */

	List<CollegeInfo> getSelectCollegeList();

	/**
	 * @Description: 新增学校
	 * @param college
	 * @param request
	 * @return
	 */

	ResultInfo saveCollege(CollegeInfo college, HttpServletRequest request);

	/**
	 * @Description: 根据主键ID删除学校
	 * @param collegeId
	 * @param request
	 * @return
	 */

	int deleteCollege(Long collegeId, HttpServletRequest request);

	/**
	 * @Description: 更新学校信息
	 * @param college
	 * @param request
	 * @return
	 */

	ResultInfo updateCollege(CollegeInfo college, HttpServletRequest request);

	/**
	 * 
	 * 
	 * /**
	 * 
	 * @Description: 按城市id查询学校
	 * @param cityId
	 * @return
	 */

	List<CityCollegeInfo> selectByCityId(Long cityId);

	/**
	 * 
	 * @Description: 学校管理查询
	 * @return
	 */
	List<CollegeManageInfo> selectManageCollegeInfo();

	/**
	 * 
	 * @Description: 学校新增修改控件数据源
	 * @return
	 */
	public ResultInfo<Object> getPropertyInfos();

	/**
	 * 
	 * @Description: 修改学校众包模式
	 * @param request
	 * @param isOk
	 * @param coolegeIds
	 * @return
	 * @throws IOException 
	 */
	int changePacketModel(HttpServletRequest request, String isOk, String... coolegeIds) throws IOException;
}
