/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.dao.manager.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: SysNoticeBean
 * @Description: 系统通知实体类
 * @author xzy
 * @date 2016年3月27日 下午12:04:49
 *
 */
public class SysNoticeBean implements Serializable {

	private static final long serialVersionUID = 3883548187058157384L;
	/**
	 * 编号
	 */
	private Long sysNoticeId;
	/**
	 * 标题
	 */
	private String caption;
	/**
	 * 网址
	 */
	private String url;
	/**
	 * 是否推送
	 */
	private Byte bePush;
	/**
	 * 发布日期
	 */
	private Date publishTime;
	/**
	 * 用户地址
	 */
	private Long userId;
	/**
	 * 是否删除
	 */
	private Byte beDeleted;
	/**
	 * 内容
	 */
	private String content;

	/**
	 * 推送类型: axp 货源 axp_packet 众包
	 */
	private String pushType;

	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public SysNoticeBean(Long sysNoticeId, String caption, String url, Byte bePush, Date publishTime, Long userId,
			Byte beDeleted, String content, String pushType) {
		this.sysNoticeId = sysNoticeId;
		this.caption = caption;
		this.url = url;
		this.bePush = bePush;
		this.publishTime = publishTime;
		this.userId = userId;
		this.beDeleted = beDeleted;
		this.content = content;
		this.pushType = pushType;
	}

	public SysNoticeBean() {
		super();
	}

	public Long getSysNoticeId() {
		return sysNoticeId;
	}

	public void setSysNoticeId(Long sysNoticeId) {
		this.sysNoticeId = sysNoticeId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption == null ? null : caption.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Byte getBePush() {
		return bePush;
	}

	public void setBePush(Byte bePush) {
		this.bePush = bePush;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Byte getBeDeleted() {
		return beDeleted;
	}

	public void setBeDeleted(Byte beDeleted) {
		this.beDeleted = beDeleted;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}