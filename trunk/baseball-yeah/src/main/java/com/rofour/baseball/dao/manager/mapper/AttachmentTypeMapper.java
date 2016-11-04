/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.dao.manager.bean.AttachmentTypeBean;

/**
* @ClassName: AttachmentTypeMapper
* @Description: 附件类型操作接口
* @author 史丹丹
* @date 2016年4月2日 下午5:08:26 
*
*/
    
@Named("attachmentTypeMapper")
public interface AttachmentTypeMapper {
	/**
	 * 
	 * @Description: 根据数量删除
	 * @param attachmentTypeId
	 * @return int 删除的数量
	 */
    int deleteByPrimaryKey(String attachmentTypeId);
    /**
	 * 
	 * @Description: 增加
	 * @param record
	 * @return int 
	 */
    int insert(AttachmentTypeBean record);
    /**
     * 
     * @Description: 根据主键查询
     * @param attachmentTypeId
     * @return AttachmentTypeBean
     */
    AttachmentTypeBean selectByPrimaryKey(String attachmentTypeId);

    /**
     * 
     * @Description: 根据主键更新
     * @param record
     * @return 更新的数量
     */
    int updateByPrimaryKey(AttachmentTypeBean record);

	/**
	 * @Description: 查询所有附件类型
	 * @return List<AttachmentTypeBean>
	 **/
	    
	List<AttachmentTypeBean> selectAll();
	
	/**
	 * @Description: 判断附件类型ID和名称是否存在
	 * @param record
	 * @return 
	 */
    int isAttachmentTypeExist(AttachmentTypeBean record);
    /**
	 * @Description: 判断附件类型名称是否存在
	 * @param record
	 * @return 
	 */
    int isAttachmentTypeNameExist(AttachmentTypeBean record);
	
}