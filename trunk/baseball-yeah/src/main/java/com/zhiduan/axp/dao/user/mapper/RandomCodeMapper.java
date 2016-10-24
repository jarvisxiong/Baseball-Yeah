/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.user.mapper;


import javax.inject.Named;

import com.zhiduan.axp.dao.user.bean.RandomCodeBean;

/**
* @ClassName: RandomCodeForUserMapper
* @Description: 用户随机码mapper接口
* @author 周琦
* @date 2016年3月26日 下午3:22:59 
*
*/
@Named("randomCodeMapper")      
public interface RandomCodeMapper {

	/**
	 * 新增验证码信息
	 */
	int insertSelective(RandomCodeBean randomCodeBean);

	/**
	 * 获取当前用户有效验证码
	 */
	RandomCodeBean getByParam(RandomCodeBean randomCodeBean);

	/**
	 * 更新验证码状态
	 */
	int updateCodeStatus(RandomCodeBean randomCodeBean);
}