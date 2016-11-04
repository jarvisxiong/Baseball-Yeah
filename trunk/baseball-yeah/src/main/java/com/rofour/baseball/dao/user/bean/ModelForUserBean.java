package com.rofour.baseball.dao.user.bean;

public class ModelForUserBean {
	private int modelId;
	private int userId;
	private String modelContent;
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getModelContent() {
		return modelContent;
	}
	public void setModelContent(String modelContent) {
		this.modelContent = modelContent;
	}
	@Override
	public String toString() {
		return "ModelForUserBean [modelId=" + modelId + ", userId=" + userId + ", modelContent=" + modelContent + "]";
	}
	
	
	
	
	
	
	
	
}
