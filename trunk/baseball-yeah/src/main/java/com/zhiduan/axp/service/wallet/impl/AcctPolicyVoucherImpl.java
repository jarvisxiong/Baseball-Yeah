/**  
* @Title: AcctPolicyVoucherImpl.java
* @Package com.zhiduan.axp.service.wallet.impl
* @Project: axp-operation
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年7月18日 下午3:25:37 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.service.wallet.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zhiduan.axp.dao.activity.mapper.AcctPolicyVoucherMapper;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.controller.model.wallet.AcctPolicyVoucherInfo;
import com.zhiduan.axp.service.wallet.AcctPolicyVoucherService;

/**
* @ClassName: AcctPolicyVoucherImpl
* @Description: 代金券服务
* @author heyi
* @date 2016年7月18日 下午3:25:37 
*
*/

@Service("acctPolicyVoucherService")
public class AcctPolicyVoucherImpl implements AcctPolicyVoucherService {

	
	@Resource(name="acctPolicyVoucherMapper")
	private AcctPolicyVoucherMapper acctPolicyVoucherMapper;
	    
	@Override
	public List<AcctPolicyVoucherInfo> getVoucherList(AcctPolicyVoucherInfo info) {
		try {
			return acctPolicyVoucherMapper.selectPolicyVoucherInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int selectPolicyVoucherInfoCount(AcctPolicyVoucherInfo info) {
		return acctPolicyVoucherMapper.selectPolicyVoucherInfoCount(info);
	}

}
