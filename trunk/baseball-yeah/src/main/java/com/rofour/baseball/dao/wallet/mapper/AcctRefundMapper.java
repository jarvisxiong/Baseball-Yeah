package com.rofour.baseball.dao.wallet.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.controller.model.wallet.AcctRefundInfo;
import com.rofour.baseball.dao.wallet.bean.AcctRefundBean;

/**
 * 
* @ClassName: AcctRefundMapper
* @Description: 钱包退款数据访问
* @author WJ
* @date 2016年7月22日 上午10:42:08 
*
 */
@Named("acctRefundMapper")
public interface AcctRefundMapper {
	
	/**
	 * 
	 * @Description 查询全部记录
	 * @param @param info
	 * @param @return
	 * @return List<AcctRefundBean>
	 *
	 */
	public List<AcctRefundBean> queryAll(AcctRefundInfo info);

	/**
	 * @Description: 查询总数
	 * @param @param info
	 * @return int
	 **/
	    
	public int getTotal(AcctRefundInfo info);
}
