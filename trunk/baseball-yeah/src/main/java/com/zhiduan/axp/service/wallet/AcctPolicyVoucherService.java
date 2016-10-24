/**  
* @Title: AcctPolicyVoucherService.java
* @Package com.zhiduan.axp.service.wallet
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年7月18日 下午3:24:01 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.service.wallet;

import java.util.List;

import com.zhiduan.axp.controller.model.wallet.AcctPolicyVoucherInfo;

/**
* @ClassName: AcctPolicyVoucherService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author heyi
* @date 2016年7月18日 下午3:24:01 
*
*/

public interface AcctPolicyVoucherService {
   List<AcctPolicyVoucherInfo> getVoucherList(AcctPolicyVoucherInfo info);
   int selectPolicyVoucherInfoCount(AcctPolicyVoucherInfo info);
}
