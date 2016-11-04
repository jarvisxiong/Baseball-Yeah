package com.rofour.baseball.dao.user.bean;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: FeedBackBean
* @Description: 反馈信息实体类
* @author sdd
* @date 2016年3月27日 下午6:39:09 
*
*/

public class FeedBackBean implements Serializable{
	private static final long serialVersionUID = -2550432487132921383L;

	/**
	 * 用户反馈信息编号（主键）
	 */
	    
	private Long feedbackId;

    /**
     * 用户id
     */
        
    private Long userId;

    /**
     * 用户姓名
     */
        
    private String name;

    /**
     * 用户联系方式
     */
        
    private String phone;

    /**
     * 反馈信息内容
     */
        
    private String content;

    /**
     * 本地IP地址
     */
        
    private String ip;

    /**
     * 反馈信息提交时间
     */
        
    private Date submittedTime;

    public FeedBackBean(Long feedbackId, Long userId, String name, String phone, String content, String ip, Date submittedTime) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.content = content;
        this.ip = ip;
        this.submittedTime = submittedTime;
    }

    public FeedBackBean() {
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