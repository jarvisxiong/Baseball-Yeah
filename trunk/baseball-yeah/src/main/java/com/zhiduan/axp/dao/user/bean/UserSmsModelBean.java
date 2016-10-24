package com.zhiduan.axp.dao.user.bean;

import com.zhiduan.axp.controller.model.BasePage;

import java.io.Serializable;

/**
* @ClassName: UserSmsModelBean
* @Description: 用户短信模板实体类
* @author 王伟
* @date 2016年3月29日 上午11:15:56 
*
*/
    
public class UserSmsModelBean extends BasePage implements Serializable {

	private static final long serialVersionUID = -5528393730634950279L;
	/**
	 * 用户短信模版主键ID
	 */
	private Long smsModelId;        
    /**
     * 用户ID
     */
    private Long userId;            
    /**
     * 模板名称
     */
    private String templateName;    
    /**
     * 模板内容
     */
    private String modelContent;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    private String endDate;

    private String startDate;

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(String smsCount) {
        this.smsCount = smsCount;
    }

    private  String  submitTime;

    private  String smsCount;

    public UserSmsModelBean(Long smsModelId, Long userId,String templateName,String modelContent) {
        this.smsModelId = smsModelId;
        this.userId = userId;
        this.templateName=templateName;
        this.modelContent = modelContent;
    }

    public UserSmsModelBean() {
        super();
    }

    public Long getSmsModelId() {
        return smsModelId;
    }

    public void setSmsModelId(Long smsModelId) {
        this.smsModelId = smsModelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getModelContent() {
        return modelContent;
    }

    public void setModelContent(String modelContent) {
        this.modelContent = modelContent == null ? null : modelContent.trim();
    }
}