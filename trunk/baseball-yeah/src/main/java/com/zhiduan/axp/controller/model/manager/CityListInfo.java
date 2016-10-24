/**  
 * Copyright (c) 2016, 指端科技.
 */


    
package com.zhiduan.axp.controller.model.manager;

import java.io.Serializable;

/**
* @ClassName: CityListInfo
* @Description: 查询城市列表
* @author 周琦
* @date 2016年4月2日 下午5:15:08 
*
*/

public class CityListInfo implements Serializable{
	
  
	private static final long serialVersionUID = 7396014903335060650L;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 城市ID
	 */
	private Long cityId;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	

}
