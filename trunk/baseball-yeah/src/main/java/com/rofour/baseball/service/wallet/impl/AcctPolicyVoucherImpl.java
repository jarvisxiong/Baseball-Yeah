/**  
* @Title: AcctPolicyVoucherImpl.java
* @package com.rofour.baseball.service.wallet.impl
* @Project: baseball-yeah
* @Description: (用一句话描述该文件做什么)
* @author heyi
* @date 2016年7月18日 下午3:25:37 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.service.wallet.impl;

import java.util.List;

import javax.annotation.Resource;

import com.rofour.baseball.dao.activity.mapper.AcctPolicyVoucherMapper;
import org.springframework.stereotype.Service;

import com.rofour.baseball.controller.model.wallet.AcctPolicyVoucherInfo;
import com.rofour.baseball.service.wallet.AcctPolicyVoucherService;

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
