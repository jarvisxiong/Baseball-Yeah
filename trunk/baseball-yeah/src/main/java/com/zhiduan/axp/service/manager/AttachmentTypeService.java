/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager;

import java.util.List;

import com.zhiduan.axp.controller.model.manager.AttachmentTypeInfo;

/**
 * @ClassName: AttachmentTypeService
 * @Description: 附件类型操作接口
 * @author 史丹丹
 * @date 2016年4月2日 下午5:21:31
 *
 */

public interface AttachmentTypeService {

	/**
	 * @Description: 查询所有附件类型
	 * @return
	 */

	public List<AttachmentTypeInfo> selectAll();

	/**
	 * @Description: 添加附件类型
	 * @param attachment
	 * @return
	 */

	public int addAttachment(AttachmentTypeInfo attachment);

	/**
	 * @Description: 根据主键查询附件类型信息
	 * @param attachment
	 * @return
	 */

	public AttachmentTypeInfo selectAttachment(AttachmentTypeInfo attachment);

	/**
	 * @Description: 删除附件类型
	 * @param attachment
	 * @return
	 */

	public int deleteAttachment(AttachmentTypeInfo attachment);

	/**
	 * @Description: 更新附件类型信息
	 * @param attachment
	 * @return
	 */

	public int updateAttachmentType(AttachmentTypeInfo attachment);

}
