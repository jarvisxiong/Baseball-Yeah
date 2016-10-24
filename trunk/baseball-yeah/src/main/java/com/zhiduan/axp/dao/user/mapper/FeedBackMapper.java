package com.zhiduan.axp.dao.user.mapper;

import java.util.List;

import javax.inject.Named;

import com.zhiduan.axp.controller.model.user.FeedBackInfo;
import com.zhiduan.axp.dao.user.bean.FeedBackBean;

/**
* @ClassName: FeedBackMapper
* @Description: 反馈信息类型操作接口
* @author sdd
* @date 2016年3月27日 下午6:38:50 
*
*/
@Named("feedBackMapper") 
public interface FeedBackMapper {
    int deleteBatch(FeedBackInfo feedBackInfo);

    int insert(FeedBackBean record);

    int insertSelective(FeedBackBean record);

    FeedBackBean selectByPrimaryKey(Long feedbackId);

    int updateByPrimaryKeySelective(FeedBackBean record);

    int updateByPrimaryKey(FeedBackBean record);

	/**
	 * @Method: selectAll
	 * @Description: 查询所有反馈信息
	 * @param @return    参数
	 * @return List<FeedBackBean>    返回类型
	 * @throws
	 * @author sdd 
	 * @date 2016年3月31日 下午2:38:25 
	 **/
	    
	public List<FeedBackInfo> selectAll(FeedBackInfo feedBackInfo);

	/**
	 * @Method: selectByUserId
	 * @Description: 根据用户ID查询反馈信息
	 * @param @param userId
	 * @param @return    参数
	 * @return List<FeedBackBean>    返回类型
	 * @throws
	 * @author 史丹丹
	 * @date 2016年3月31日 下午3:28:24 
	 **/
	    
	List<FeedBackBean> selectByUserId(Long userId);
	
	/**
	 * @Description: 查询反馈信息总条数
	 * @return 
	 */
	    
	Integer getfeedBackTotal(FeedBackInfo feedBackInfo);
}