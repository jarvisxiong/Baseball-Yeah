/**  
* @Title: SelectPageModel.java
* @Package com.zhiduan.axp.controller.model
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年5月13日 下午2:56:55 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.controller.model;

import java.util.List;

/**
* @ClassName: SelectPageModel
* @Description: 下拉带分页
* @author heyi
* @date 2016年5月13日 下午2:56:55 
*
*/

public class SelectPageModel {
	/**
	 * 总页数
	 */
    private int total;
    /**
     * 下拉框选项
     */
    private List<SelectModel> rows;
    
	
	public List<SelectModel> getRows() {
		return rows;
	}
	public void setRows(List<SelectModel> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

}
