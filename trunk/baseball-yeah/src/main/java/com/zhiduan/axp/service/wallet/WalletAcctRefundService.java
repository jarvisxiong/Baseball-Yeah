package com.zhiduan.axp.service.wallet;

import java.util.List;

import com.zhiduan.axp.controller.model.wallet.AcctRefundInfo;
import com.zhiduan.axp.dao.wallet.bean.AcctRefundBean;

/**
 * 
* @ClassName: WalletAcctRefundImpl
* @Description: 钱包退款实现类
* @author WJ
* @date 2016年7月22日 上午10:41:01 
*
 */
public interface WalletAcctRefundService {

	/**
	 * 
	 * @Description: 查询全部记录
	 * @return List<AcctRefundInfo>
	 *
	 */
	public List<AcctRefundBean> list(AcctRefundInfo info);

	/**
	 * @Description: 查询总数
	 * @return int
	 **/
	    
	public int getTotal(AcctRefundInfo info);
}
