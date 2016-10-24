/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.controller.model.manager.AttachmentTypeInfo;
import com.zhiduan.axp.dao.manager.bean.AttachmentTypeBean;
import com.zhiduan.axp.dao.manager.mapper.AttachmentTypeMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.AttachmentTypeService;

/**
 * @ClassName: AttachmentTypeImpl
 * @Description: 附件类型接口实现类
 * @author 史丹丹
 * @date 2016年4月2日 下午5:36:08
 *
 */

@Service("attachmentTypeService")
public class AttachmentTypeImpl implements AttachmentTypeService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("attachmentTypeMapper")
	private AttachmentTypeMapper dao;

	/**
	 * @Description: 查询所有附件服务
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.AttachmentTypeService#selectAll()
	 */
	@Override
	public List<AttachmentTypeInfo> selectAll() {
		logger.info("查询所有附件");
		List<AttachmentTypeBean> beanList = null;
		List<AttachmentTypeInfo> infoList = null;
		beanList = dao.selectAll();
		infoList = new ArrayList<AttachmentTypeInfo>();
		for (AttachmentTypeBean item : beanList) {
			AttachmentTypeInfo at = new AttachmentTypeInfo();
			BeanUtils.copyProperties(item, at);
			infoList.add(at);
		}
		return infoList;
	}

	/**
	 * @Description: 添加附件服务
	 * @param attachment
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.AttachmentTypeService#addAttachment(com.zhiduan.axp.idl.managecenter.service.entity.AttachmentTypeInfo)
	 */
	@Override
	public int addAttachment(AttachmentTypeInfo attachment) {
		validate(attachment);
		logger.info("添加附件名为：" + attachment.getAttachmentTypeName() + "的附件到表tb_attachment_type中");
		AttachmentTypeBean atb = new AttachmentTypeBean();
		int i = 0;

		BeanUtils.copyProperties(attachment, atb);
		i = dao.isAttachmentTypeExist(atb);
		if (i != 0)
			throw new BusinessException("01022");
		i = dao.insert(atb);
		if (i != 0) {
			return i;
		} else {
			throw new BusinessException("01023");
		}
		
	}

	/**
	 * @Description: 查询附件服务
	 * @param attachment
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.AttachmentTypeService#selectAttachment(com.zhiduan.axp.idl.managecenter.service.entity.AttachmentTypeInfo)
	 */
	@Override
	public AttachmentTypeInfo selectAttachment(AttachmentTypeInfo attachment) {
		if (StringUtils.isBlank(attachment.getAttachmentTypeId())) {
			throw new BusinessException("111");
		}
		logger.info("查询附件");
		AttachmentTypeBean atb = new AttachmentTypeBean();
		AttachmentTypeInfo ati = new AttachmentTypeInfo();
		BeanUtils.copyProperties(attachment, atb);
		BeanUtils.copyProperties(dao.selectByPrimaryKey(atb.getAttachmentTypeId()), ati);
		return ati;
	}

	/**
	 * @Description: 删除附件类型
	 * @param attachment
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.AttachmentTypeService#deleteAttachment(com.zhiduan.axp.idl.managecenter.service.entity.AttachmentTypeInfo)
	 */
	@Override
	public int deleteAttachment(AttachmentTypeInfo attachment) {
		AttachmentTypeBean atb = new AttachmentTypeBean();
		int status = 0;
		BeanUtils.copyProperties(attachment, atb);
		status = dao.deleteByPrimaryKey(atb.getAttachmentTypeId());
		if (status != 0) {
			return status;
		} else {
			throw new BusinessException("01023");
		}
		
	}

	/**
	 * @Description: 更新附件类型
	 * @param attachment
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.AttachmentTypeService#updateAttachmentType(com.zhiduan.axp.idl.managecenter.service.entity.AttachmentTypeInfo)
	 */
	@Override
	public int updateAttachmentType(AttachmentTypeInfo attachment) {
		validate(attachment);
		AttachmentTypeBean bean = new AttachmentTypeBean();
		int atta = 0;
		BeanUtils.copyProperties(attachment, bean);
		atta = dao.isAttachmentTypeNameExist(bean);
		if (atta != 0)
			throw new BusinessException("01022");
		atta = dao.updateByPrimaryKey(bean);
		if (atta != 0) {
			return atta;
		} else {
			throw new BusinessException("01023");
		}
		

	}

	/**
	 * 
	 * @Description: 验证参数信息方法
	 * @param attachment
	 * @return ResultInfo 操作结果bean
	 */
	private void validate(AttachmentTypeInfo attachment) {
		if (StringUtils.isBlank(attachment.getAttachmentTypeId())
				|| StringUtils.isBlank(attachment.getAttachmentTypeName())
				|| StringUtils.isBlank(attachment.getUploadFileType()) || attachment.getAllowUploadCount() == null
				|| attachment.getUploadFileSize() == null){
			throw new BusinessException("111");
		}
			

	}

}
