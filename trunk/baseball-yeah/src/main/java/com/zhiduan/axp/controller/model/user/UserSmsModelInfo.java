package com.zhiduan.axp.controller.model.user;

import java.io.Serializable;

/**
* @ClassName: UserSmsModelInfo
* @Description: 用户短信模板DTO,用作接口返回实体
* @author 王伟
* @date 2016年3月29日 上午11:22:00 
*
*/
    
/**
* @ClassName: UserSmsModelInfo
* @Description: 用户短信模板实体类
* @author 王伟
* @date 2016年4月3日 下午8:06:01 
*
*/
    
public class UserSmsModelInfo implements Serializable{	    
	private static final long serialVersionUID = 3644310009083301539L;
	/**
	 * 短信模板主键ID
	 */
	private Long smsModelId;    

    /**
     *用户ID
     */
    private Long userId;        
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 短信模板内容 
     */
    private String modelContent;

    public UserSmsModelInfo(Long smsModelId, Long userId,String templateName,String modelContent) {
        this.smsModelId = smsModelId;
        this.userId = userId;
        this.templateName=templateName;
        this.modelContent = modelContent;
    }

    public UserSmsModelInfo() {
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
