package com.rofour.baseball.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.user.FeedBackInfo;



/**
* @ClassName: FeedBackService
* @Description: 反馈信息类型逻辑层接口
* @author sdd
* @date 2016年3月27日 下午5:36:34 
*
*/
    
public interface FeedBackService {
        
	public ResultInfo addFeedBack(FeedBackInfo feedBack) throws Exception;

	/**
	 * @Method: selectAll
	 * @Description: 查询所有反馈信息
	 * @param @return    参数
	 * @return List<LoginLogInfo>    返回类型
	 * @throws
	 * @author 史丹丹
	 * @date 2016年3月31日 下午2:32:46 
	 **/
	    
	public List<FeedBackInfo> selectAll(FeedBackInfo feedBackInfo);

	/**
	 * @Method: deleteById
	 * @Description: 删除反馈信息
	 * @param @param feedBack
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 * @author 史丹丹
	 * @date 2016年3月31日 下午3:09:46 
	 **/
	    
	public int deleteBatch(FeedBackInfo feedBack, HttpServletRequest request);

	/**
	 * @Method: selectLoginLog
	 * @Description: 根据用户id查询反馈信息
	 * @param @param feedBack
	 * @param @return    参数
	 * @return List<FeedBackInfo>    返回类型
	 * @throws
	 * @author 史丹丹
	 * @date 2016年3月31日 下午3:25:22 
	 **/
	    
	public List<FeedBackInfo> selectLoginLog(FeedBackInfo feedBack);
	
	/**
	 * @Description: 查询反馈信息总条数
	 * @return 
	 */
	    
	public Integer getfeedBackTotal(FeedBackInfo feedBack); 
}