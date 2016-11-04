package com.rofour.baseball.controller.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.rofour.baseball.controller.model.BasePage;

/**
* @ClassName: FeedBackBean
* @Description: 反馈信息实体类
* @author sdd
* @date 2016年3月27日 下午6:39:09 
*
*/

public class FeedBackInfo extends BasePage implements Serializable{
	
	private static final long serialVersionUID = -8881786588326113376L;

	/**
	 * 用户反馈信息编号（主键）
	 */
	private Long feedbackId;

    /**
     * 用户id
     */
        
    private Long userId;

    /**
     * 用户名
     */
        
    private String name;

    /**
     * 用户联系电话
     */
    private String phone;

    /**
     * 反馈信息内容
     */
    private String content;

    /**
     * ip地址
     */
    private String ip;
    private List<?> feedbackIds;

    public List<?> getFeedbackIds() {
		return feedbackIds;
	}

	public void setFeedbackIds(List<?> feedbackIds) {
		this.feedbackIds = feedbackIds;
	}

	/**
     * 反馈信息提交时间
     */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date submittedTime;

    public FeedBackInfo(Long feedbackId, Long userId, String name, String phone, String content, String ip, Date submittedTime) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.content = content;
        this.ip = ip;
        this.submittedTime = submittedTime;
    }

    public FeedBackInfo() {
        super();
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(Date submittedTime) {
        this.submittedTime = submittedTime;
    }
}