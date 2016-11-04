/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.model.manager;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.rofour.baseball.controller.model.BasePage;

/**
 * @ClassName: SysNoticeInfo
 * @Description: 系统通知外部实体类
 * @author xzy
 * @date 2016年3月27日 上午11:26:55
 *
 */
public class SysNoticeInfo extends BasePage implements Serializable {

	private static final long serialVersionUID = 7363296362372673239L;
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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

	private List<Long> sysNoticeIds;
	/**
	 * 推送类型: axp 货源 axp_packet 众包
	 */
	private String pushType;

	private List<String> pushTypeList;

	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public List<String> getPushTypeList() {
		return pushTypeList;
	}

	public void setPushTypeList(List<String> pushTypeList) {
		this.pushTypeList = pushTypeList;
	}

	public List<Long> getSysNoticeIds() {
		return sysNoticeIds;
	}

	public void setSysNoticeIds(List<Long> sysNoticeIds) {
		this.sysNoticeIds = sysNoticeIds;
	}

	public SysNoticeInfo(Long sysNoticeId, String caption, String url, Byte bePush, Date publishTime, Long userId,
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

	public SysNoticeInfo() {
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
