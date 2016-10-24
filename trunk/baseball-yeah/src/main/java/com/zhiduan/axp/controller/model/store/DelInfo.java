/**  
* @Title: DelInfo.java
* @Package com.zhiduan.axp.controller.model.store
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年5月20日 下午3:43:19 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.controller.model.store;

import java.io.Serializable;
import java.util.List;

/**
* @ClassName: DelInfo
* @Description:批量操作ID 
* @author heyi
* @date 2016年5月20日 下午3:43:19 
*
*/

public class DelInfo implements Serializable {

	private static final long serialVersionUID = 2848795391039557559L;
    private List<Integer> ids;
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
}
